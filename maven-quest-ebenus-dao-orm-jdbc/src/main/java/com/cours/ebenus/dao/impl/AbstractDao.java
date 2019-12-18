/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.annotations.DBTable;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @param <T>
 * @author ElHadji
 */
public abstract class AbstractDao<T> implements IDao<T> {

    private Class<T> myClass = null;
    private static final Log log = LogFactory.getLog(AbstractDao.class);

    public AbstractDao(Class<T> myClass) {
        this.myClass = myClass;
    }

    public List<T> getFieldObject(ResultSet rs) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<T> objects = new ArrayList<>();
        while (rs.next()) {
            //Récupération de l'instance T
            T obj = myClass.getDeclaredConstructor().newInstance();

            // Parcourt de tous les fields de la class
            for (Field field : myClass.getDeclaredFields()) {
                //Avoir accés à la classe utilisateur meme en privé
                field.setAccessible(true);

                //Récupération de l'annotation à partir des champs déclarées dans la class T
                DBTable annotation = field.getAnnotation(DBTable.class);
//                log.debug("Annotation: " + annotation);

                //Récupération de la valeur de la colonne spécifié dans l'annotation
                Object value = rs.getObject(annotation.columnName());
//                log.debug("Valeur: " + value);

                //Récupération du type de la valeur
                Class<?> type = field.getType();
//                log.debug("Type: " + type);


                if (type == Role.class) {

                    try {
                        Field roleField = myClass.getDeclaredField("role");
                        roleField.setAccessible(true);
                        Role r = new Role((int) value);

                        /* Récup des champs de role */

                        Field roleIdentifiantField = r.getClass().getDeclaredField("identifiant");
                        Field roleDescriptionField = r.getClass().getDeclaredField("description");
                        roleIdentifiantField.setAccessible(true);
                        roleDescriptionField.setAccessible(true);

                        DBTable annotation2 = roleIdentifiantField.getAnnotation(DBTable.class);
                        DBTable annotation3 = roleDescriptionField.getAnnotation(DBTable.class);

                        Object value2 = rs.getObject(annotation2.columnName());
                        Object value3 = rs.getObject(annotation3.columnName());

                        r.setIdentifiant((String) value2);
                        r.setDescription((String) value3);

                        roleField.set(obj, r);
                    } catch (NoSuchFieldException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    field.set(obj, value);
                }

                //A quoi ça sert? Si Type primitif
//                        if (isPrimitive(type)) {//check primitive type
//                            Class<?> boxed = boxPrimitiveClass(type);//box if primitive
//                            value = boxed.cast(value);
//                            log.debug("Valeur (primitif): " + value);
//                        }


            }
            objects.add(obj);

        }
        return objects;
    }

    public <E> List<T> applyQueryFromParameter(String query, E param) {
        List<T> objects = new ArrayList<>();
        Connection connection;
        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            PreparedStatement prep = null;
            ResultSet rs = null;
            try {
                prep = connection.prepareStatement(query);
                if (param != null) {
                    prep.setObject(1, param);
                }

                //Check start of sql request
                if (query.startsWith("UPDATE") || query.startsWith("DELETE") || query.startsWith("INSERT")) {
                    prep.executeUpdate();
                } else {
                    rs = prep.executeQuery();
                    objects = getFieldObject(rs);
                }


            } catch (IllegalArgumentException | InvocationTargetException
                    | NoSuchMethodException | SecurityException
                    | InstantiationException | IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                ConnectionHelper.closeSqlResources(connection, prep, rs);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return objects;
    }


    public void applyQueryFromParameters(String query, List<Object> params) {
        Connection connection = null;
        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            PreparedStatement prep = null;
            prep = connection.prepareStatement(query);
            if (params != null && !params.isEmpty()) {
                //Prépare la requête
                int cpt = 1;
                for (Object param : params) {
                    //Pour chaque paramètre
                    prep.setObject(cpt, param);
                    cpt++;
                }
                prep.executeUpdate();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //SI jamais type primitif prochaines etapes
    public static boolean isPrimitive(Class<?> type) {
        return (type == int.class || type == long.class || type == double.class || type == float.class
                || type == boolean.class || type == byte.class || type == char.class || type == short.class);
    }

    //SI jamais on a type primitif plus tard
    public static Class<?> boxPrimitiveClass(Class<?> type) {
        if (type == int.class) {
            return Integer.class;
        } else if (type == long.class) {
            return Long.class;
        } else if (type == double.class) {
            return Double.class;
        } else if (type == float.class) {
            return Float.class;
        } else if (type == boolean.class) {
            return Boolean.class;
        } else if (type == byte.class) {
            return Byte.class;
        } else if (type == char.class) {
            return Character.class;
        } else if (type == short.class) {
            return Short.class;
        } else {
            String string = "class '" + type.getName() + "' is not a primitive";
            throw new IllegalArgumentException(string);
        }
    }

    @Override
    public List<T> findAll() {
        String query = null;
        if (myClass.getName().equals(Utilisateur.class.getName())) {
            query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description " +
                    "FROM Utilisateur " +
                    "LEFT JOIN Role r on r.idRole= Utilisateur.idRole";
        } else if (myClass.getName().equals(Role.class.getName())) {
            query = "SELECT identifiant AS roleIdent, idRole, description, version FROM Role;";
        }
        return applyQueryFromParameter(query, null);
    }


    @Override
    public T findById(int id) {
        String query = null;
        if (myClass.getName().equals(Utilisateur.class.getName())) {
            query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                    "LEFT JOIN Role r on r.idRole = Utilisateur.idRole " +
                    "where Utilisateur.idUtilisateur = ? ";
        } else if (myClass.getName().equals(Role.class.getName())) {
            query = "SELECT identifiant AS roleIdent, idRole, description, version FROM Role" +
                    " WHERE idRole = ?;";
        }

        List<T> obj = applyQueryFromParameter(query, id);
        if (!obj.isEmpty()) {
            return obj.get(0);
        } else {
            return null;
        }
    }


    public List<T> findByCriteria(Object ob, String criteria) {
        String query = null;
        if (myClass.getName().equals(Utilisateur.class.getName())) {
            query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                    "LEFT JOIN Role r on r.idRole = Utilisateur.idRole " +
                    "WHERE ";
            
            System.out.println("TO STRING : " + ob.toString() + "\n");
            
            DBTable annotation = ((Field) ob).getAnnotation(DBTable.class);
            String columnName = annotation.columnName();
            
            if (columnName.equals("roleIdent"))
            {
            	query += "r." + "identifiant" + " = ?";
            	
            }
            else
            {
            	query += myClass.getSimpleName() + "." + columnName + " = ?";
            }
            
            System.out.println("FINAL QUERY : " + query);
            
        } else if (myClass.getName().equals(Role.class.getName())) {
        
        	query = "SELECT identifiant AS roleIdent, idRole, description, version FROM Role" +
                    " WHERE identifiant = ?";
            
            System.out.println("FINAL QUERY : " + query);
        }
        List<T> obj = applyQueryFromParameter(query, criteria);
        if (!obj.isEmpty()) {
            return obj;
        } else {
            return null;
        }
    }

    @Override
    public T create(T t) {
        return null;
    }

    @Override
    public T update(T t) {
    	String query = null;
        try {
            List<Object> parameters = new ArrayList<Object>();
            //Build only necessary parameters
            if (t.getClass() == Utilisateur.class) {

            	query = "UPDATE Utilisateur " +
                        "SET idRole = ?, civilite = ?, prenom = ?, nom = ?, identifiant = ?, motPasse = ?, dateModification = ? " +
                        "WHERE idUtilisateur = ?";
            	
                Field role = t.getClass().getDeclaredField("role");
                Field civilite = t.getClass().getDeclaredField("civilite");
                Field prenom = t.getClass().getDeclaredField("prenom");
                Field nom = t.getClass().getDeclaredField("nom");
                Field identifiant = t.getClass().getDeclaredField("identifiant");
                Field motPasse = t.getClass().getDeclaredField("motPasse");
                Field dateModif = t.getClass().getDeclaredField("dateModification");
                Field idUtilisateur = t.getClass().getDeclaredField("idUtilisateur");

                role.setAccessible(true);
                System.out.println(role.get(t).getClass());
                Field roleID = role.get(t).getClass().getDeclaredField("idRole");

                roleID.setAccessible(true);
                civilite.setAccessible(true);
                prenom.setAccessible(true);
                nom.setAccessible(true);
                identifiant.setAccessible(true);
                motPasse.setAccessible(true);
                dateModif.setAccessible(true);
                idUtilisateur.setAccessible(true);

                parameters.add(roleID.get(role.get(t)));
                parameters.add(civilite.get(t));
                parameters.add(prenom.get(t));
                parameters.add(nom.get(t));
                parameters.add(identifiant.get(t));
                parameters.add(motPasse.get(t));
                parameters.add(dateModif.get(t));
                parameters.add(idUtilisateur.get(t));

            } else if (t.getClass() == Role.class) {
            	
            	query = "UPDATE Role SET identifiant = ?, description = ? " +
     				   "WHERE idRole = ? ";
            	
                Field identifiant = t.getClass().getDeclaredField("identifiant");
                Field description = t.getClass().getDeclaredField("description");
                Field idRole = t.getClass().getDeclaredField("idRole");

                identifiant.setAccessible(true);
                description.setAccessible(true);
                idRole.setAccessible(true);

                parameters.add(identifiant.get(t));
                parameters.add(description.get(t));
                parameters.add(idRole.get(t));
            }

            applyQueryFromParameters(query, parameters);

        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean delete(T t) {
        Field field = null;
        String query = null;
        try {
            //Detect which class work with and find field
            if (t.getClass() == Utilisateur.class) {
            	query = "DELETE FROM Utilisateur " +
                        "WHERE idUtilisateur = ?";
                field = t.getClass().getDeclaredField("idUtilisateur");
            } else if (t.getClass() == Role.class) {
            	query = "DELETE FROM Role WHERE idRole = ?";
                field = t.getClass().getDeclaredField("idRole");
            }
            field.setAccessible(true);
            applyQueryFromParameter(query, field.get(t));

        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
