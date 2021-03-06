/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.cours.ebenus.dao.annotations.FetchChild;
import com.cours.ebenus.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.annotations.DBTable;
import com.cours.ebenus.dao.entities.Entities;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;


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
        System.out.println(query);
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


    public int applyQueryFromParameters(String query, List<Object> params) {
        System.out.println(query);
        Connection connection = null;
        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            PreparedStatement prep = null;
            prep = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (params != null && !params.isEmpty()) {
                //Prépare la requête
                int cpt = 1;
                for (Object param : params) {
                    //Pour chaque paramètre
                    prep.setObject(cpt, param);
                    cpt++;
                }
                prep.executeUpdate();
                ResultSet rs = prep.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
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

    public String sqlBuilder() {
        StringBuilder querytry = new StringBuilder("SELECT ");
        Field[] fs = myClass.getDeclaredFields();
        List<Field> fl = new ArrayList<>();

        for (Field f : fs) {
            if (f.getType().getGenericSuperclass().equals(Entities.class)) {
                fl.add(f);
            } else {
                querytry.append(myClass.getSimpleName()).append(".").append(f.getName());
                querytry.append(", ");
            }
        }
        for (Field f1 : fl) {
            for(Field innerField : f1.getType().getDeclaredFields()) {
                querytry.append(f1.getName()).append(".").append(innerField.getName());
                querytry.append(", ");
            }
        }
        querytry.deleteCharAt(querytry.lastIndexOf(",")).append(" FROM ").append(myClass.getSimpleName());
        for(Field f : fl) {
            DBTable dbTable = f.getAnnotation(DBTable.class);
            System.out.println(dbTable);
            querytry.append(" JOIN ").append(f.getName()).append(" USING (").append(dbTable.columnName()).append(")");
        }
       return querytry.toString();
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

            DBTable annotation = ((Field) ob).getAnnotation(DBTable.class);
            String columnName = annotation.columnName();

            if (columnName.equals("roleIdent")) {
                query += "r." + "identifiant" + " = ?";

            } else {
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
        String query = null;
        List<Object> params = new ArrayList<>();
        List<String> annotationsClass = new ArrayList<>();

        Field[] fs = t.getClass().getDeclaredFields();


        //Parcours tous les champs de la class reçu en param
        for (Field f : fs) {
            try {
                DBTable db = f.getAnnotation(DBTable.class);
                annotationsClass.add(db.columnName());
                Field temp = t.getClass().getDeclaredField(f.getName());
                temp.setAccessible(true);

                Object param = null;
                //check si le field est une custom class( e.i Role ou Utilisateur ici)
                if (temp.getType().getGenericSuperclass() == Entities.class) {
                    String getId = "getId" + temp.getType().getSimpleName();
                    //getter Id de la relation entre les entité (e.i getIdRole)
                    Method maa = temp.getType().getMethod(getId);
                    // proc la fonction pour recuperer l'id
                    param = maa.invoke(temp.get(t));
//                    temp.getClass().getDeclaredMethods();
                } else {
                    //si type normal
                    param = temp.get(t);
                }
                //si le field est une date et qu'elle est vide elle sera remplis avec la date today
                if (temp.getType() == Date.class && param == null) {
                    param = new java.sql.Timestamp(System.currentTimeMillis());
                }
                params.add(param);
            } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        String d = "?";
        String separator = ",";
        //Construit la string de la query
        String fieldsName = String.join(",", annotationsClass);
        //Construit la chaine de "?" avec le nombre de fields
        String nbValue = IntStream.range(0, t.getClass().getDeclaredFields().length).mapToObj(i -> d).collect(Collectors.joining(separator));
        query = "INSERT INTO " + t.getClass().getSimpleName() + " (" + fieldsName + ") VALUES (" + nbValue + ")";
        query = query.replaceAll("roleIdent", "identifiant");
        int id = applyQueryFromParameters(query, params);

        return findById(id);
    }

    @Override
    public T update(T t) {
        String query = null;
        List<Object> params = new ArrayList<>();
        List<String> annotationsClass = new ArrayList<>();

        Field[] fs = t.getClass().getDeclaredFields();

        //Build SET parameters
        for (Field f : fs) {
            try {
                DBTable db = f.getAnnotation(DBTable.class);

                annotationsClass.add(db.columnName());

                Field temp = t.getClass().getDeclaredField(f.getName());
                temp.setAccessible(true);

                Object param = temp.get(t);
                if (f.getName() == "role") {
                    Field temp2 = temp.get(t).getClass().getDeclaredField("idRole");
                    temp2.setAccessible(true);
                    params.add(temp2.get(temp.get(t)));
                } else {
                    params.add(param);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //For WHERE parameter, get current id of T
        try {
            Field temp = t.getClass().getDeclaredField("id" + t.getClass().getSimpleName());
            temp.setAccessible(true);
            String getId = "getId" + t.getClass().getSimpleName();
            Method maa = t.getClass().getMethod(getId);
            Object param = maa.invoke(t);
            params.add(param);
        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String fieldsName = String.join(" = ?, ", annotationsClass);
        query = "UPDATE " + t.getClass().getSimpleName() +
                " SET " + fieldsName + " = ?" +
                " WHERE id" + t.getClass().getSimpleName() + " = ?";

        query = query.replaceAll("roleIdent", "identifiant");
        applyQueryFromParameters(query, params);

        return t;
    }


    @Override
    public boolean delete(T t) {
        String query = null;
        try {
            Field temp = t.getClass().getDeclaredField("id" + t.getClass().getSimpleName());
            temp.setAccessible(true);
            String getId = "getId" + t.getClass().getSimpleName();
            Method maa = t.getClass().getMethod(getId);
            Object id = maa.invoke(t);
            query = "DELETE FROM " + t.getClass().getSimpleName() +
                    " WHERE id" + t.getClass().getSimpleName() + " = ?";
            applyQueryFromParameter(query, id);
            return true;
        } catch (NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
