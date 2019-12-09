/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.IDao;
import com.cours.ebenus.dao.entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ElHadji
 * @param <T>
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
	            for(int i =0; i < myClass.getDeclaredFields().length; i++)
	            {
	            	//Get each field of represented class <T>
	            	System.out.println(myClass.getDeclaredFields()[i].getName());
	            	
	            }
	        } catch (SQLException e) {
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
