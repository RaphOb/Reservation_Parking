/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import com.cours.ebenus.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class DriverManagerSingleton {

    private static final Log log = LogFactory.getLog(DriverManagerSingleton.class);

    public final static String className = DriverManagerSingleton.class.getName();
    // Url de connexion en base de donnée
    private static final String url = Constants.DATABASE_URL;

    // Utilisateur de la base de données
    private static final String user = Constants.DATABASE_USER;

    // Mot de passe de la base de données
    private static final String password = Constants.DATABASE_PASSWORD;

    // Drivers Jdbc
    private static final String jdbcDriver = Constants.JDBC_DRIVER;
    
    
    /**
     * Holder
     */
    private static class MySingletonHolder {

        /**
         * Instance unique non préinitialisée
         */
    	private static Connection connection = null;
    }
    
    // Renvoie une connexion à la base de données
    public synchronized static Connection getConnectionInstance()
    {
        try 
        {
        	/* Si l'instance du Singleton n'a pas été créer, on peut instancier la connexion */
        	if (MySingletonHolder.connection == null) {
        		log.debug("Création d'une connection");
	    		Class.forName(jdbcDriver);
	    		MySingletonHolder.connection = DriverManager.getConnection(url, user, password);
        	}
        	else
        	{
        		log.debug("Utilisation d'une connexion déja existante");
        	}
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return MySingletonHolder.connection;
    }
}
