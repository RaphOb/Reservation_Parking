/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.cours.ebenus.dao.DriverManagerSingleton.MySingletonHolder;
import com.cours.ebenus.utils.Constants;

/**
 *
 * @author elhad
 */
public class DataSourceSingleton {

    private static final Log log = LogFactory.getLog(DataSourceSingleton.class);
    public final static String className = DataSourceSingleton.class.getName();
    
    /**
     * Holder
     */
    private static class DataSourceSingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
    	private static BasicDataSource dataSource = null;
    }
    
    public synchronized static BasicDataSource getInstance()
    {
    	/* Si l'instance du Singleton n'a pas été créer, on peut instancier la connexion */
    	if (DataSourceSingletonHolder.dataSource == null) {
    		log.debug("Création d'une pool de connexion");
    		DataSourceSingletonHolder.dataSource = new BasicDataSource();
    		DataSourceSingletonHolder.dataSource.setDriverClassName(Constants.JDBC_DRIVER);
    		DataSourceSingletonHolder.dataSource.setUrl(Constants.DATABASE_URL);
    		DataSourceSingletonHolder.dataSource.setUsername(Constants.DATABASE_USER);
    		DataSourceSingletonHolder.dataSource.setPassword(Constants.DATABASE_PASSWORD);
    		DataSourceSingletonHolder.dataSource.setMaxIdle(20);
    	}
    	else
    	{
    		log.debug("Pool déja initialisé");
    	}
        return DataSourceSingletonHolder.dataSource;
    }
}
