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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public List<T> findAll(String query) {
        List<T> objects = new ArrayList<>();
        Connection connection;
        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            PreparedStatement prep = null;
            ResultSet rs = null;
            try {
                prep = connection.prepareStatement(query);
                rs = prep.executeQuery();
                while (rs.next()) {
                	//Récupération de l'instance T
                    T obj = myClass.getDeclaredConstructor().newInstance();
                    
                    // Parcourt de tous les fields de la class
                    for (Field field : myClass.getDeclaredFields()) {
                        //Avoir accés à la classe utilisateur meme en privé
	                    field.setAccessible(true);
	                    
	                    //Récupération de l'annotation à partir des champs déclarées dans la class T
                        DBTable annotation =  field.getAnnotation(DBTable.class);
                        //log.debug(annotation);
                        
                        //Récupération de la valeur spécifié dans l'annotation
	                    Object value = rs.getObject(annotation.columnName());
	                    //log.debug(value);
	                    
	                    //Récupération du type de la valeur 
                        Class<?> type = field.getType();
                        //log.debug(type);
                        
                        if (type == Role.class) {
                        	
                          continue;// TODO J'ai pas encore reussi custom class

                        }
                        if (isPrimitive(type)) {//check primitive type
                            Class<?> boxed = boxPrimitiveClass(type);//box if primitive
                            value = boxed.cast(value);
                        }
                        
                        field.set(obj, value);
                    }
                    objects.add(obj);

                }
            } catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
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

    public static boolean isPrimitive(Class<?> type) {
        return (type == int.class || type == long.class || type == double.class || type == float.class
                || type == boolean.class || type == byte.class || type == char.class || type == short.class);
    }

    @Override
    public T findById(String query, int id) {
        return null;
    }

    @Override
    public List<T> findByCriteria(String query, String criteria, Object valueCriteria) {
        return null;
    }

    @Override
    public T create(String query, T t) {
        return null;
    }

    @Override
    public T update(String query, T t) {
        return null;
    }

    @Override
    public boolean delete(String query, T t) {
        return false;
    }
}
