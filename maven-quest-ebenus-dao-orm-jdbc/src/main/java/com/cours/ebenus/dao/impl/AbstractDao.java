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
                DBTable annotation =  field.getAnnotation(DBTable.class);
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
                        Role r = new Role((int)value);

                        /* Récup des champs de role */

                        Field roleIdentifiantField = r.getClass().getDeclaredField("identifiant");
                        Field roleDescriptionField = r.getClass().getDeclaredField("description");
                        roleIdentifiantField.setAccessible(true);
                        roleDescriptionField.setAccessible(true);

                        DBTable annotation2 =  roleIdentifiantField.getAnnotation(DBTable.class);
                        DBTable annotation3 =  roleDescriptionField.getAnnotation(DBTable.class);

                        Object value2 = rs.getObject(annotation2.columnName());
                        Object value3 = rs.getObject(annotation3.columnName());

                        r.setIdentifiant((String) value2);
                        r.setDescription((String) value3);

                        roleField.set(obj, r);
                    }
                    catch (NoSuchFieldException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else
                {
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

    public <E>List<T> applyQueryFromParameter(String query, E param) {
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
                if (query.startsWith("UPDATE") || query.startsWith("DELETE") || query.startsWith("INSERT"))
                {
                	prep.executeUpdate();
                }
                else
                {
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
            	for (Object param : params)
            	{
            		//Pour chaque paramètre
					prep.setObject(cpt, param);
            		cpt ++;
            	}
            }
        }
        catch (SQLException e) {
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
    public List<T> findAll(String query) {
        return applyQueryFromParameter(query, null);
    }



    @Override
    public T findById(String query, int id) {
        List<T> obj = applyQueryFromParameter(query, id);
        if(!obj.isEmpty()) {
            return obj.get(0);
        } else {
            return null;
        }
    }


    public List<T> findByCriteria(String query, String criteria) {
        List<T> obj = applyQueryFromParameter(query, criteria);
        if(!obj.isEmpty()) {
            return obj;
        } else {
            return null;
        }
    }

    @Override
    public T create(String query, T t) {
        return null;
    }

    @Override
    public T update(String query, T t) {
    	Field[] fields = t.getClass().getDeclaredFields();
    	
		try {
			List<Object> parameters = new ArrayList<Object>();
			//Build only necessary parameters
			if (t.getClass() == Utilisateur.class)
			{
				//Builds Parameters for Utilisateur query
				for(Field field : fields)
				{
					//Role, civilité, nom, prénom, identifiant, motPasse, dateModif, idUtilisateur
					field.setAccessible(true);
					System.out.println(field);
					parameters.add(field.get(t));
				}
			}
			else if(t.getClass() == Role.class)
			{
				//Builds Parameters for Role query
				for(Field field : fields)
				{
					//Identifiant, description, idRole
					field.setAccessible(true);
					System.out.println(field);
					parameters.add(field.get(t));
				}
			}
			//applyQueryFromParameters(query, parameters);
			
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

    
    @Override
    public boolean delete(String query, T t) {
    	Field field = null;
		try {
			//Detect which class work with and find field
			if (t.getClass() == Utilisateur.class)
			{
				field = t.getClass().getDeclaredField("idUtilisateur");
			}
			else if(t.getClass() == Role.class)
			{
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
