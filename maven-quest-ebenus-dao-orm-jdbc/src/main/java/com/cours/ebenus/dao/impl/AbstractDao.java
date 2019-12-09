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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @param <T>
 * @author ElHadji
 */
public abstract class AbstractDao<T> implements IDao<T> {

    private Class<T> myClass = null;

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
                    T obj = myClass.newInstance();
                    // Parcour tous les fields de la class
                    for (Field field : myClass.getDeclaredFields()) {
                        //Avoir accés à la classe utilisateur meme en privé
	                    field.setAccessible(true);
                        DBTable column =  field.getAnnotation(DBTable.class);
	                    Object value = rs.getObject(column.columnName());
                        Class<?> type = field.getType();
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
                for (int i = 0; i < myClass.getDeclaredFields().length; i++) {
                    //Get each field of represented class <T>
                    System.out.println(myClass.getDeclaredFields()[i].getType());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
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
