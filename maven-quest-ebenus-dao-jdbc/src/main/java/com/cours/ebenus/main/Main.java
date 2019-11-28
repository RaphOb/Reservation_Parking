/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import com.cours.ebenus.utils.Constants;
import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.DriverManagerSingleton;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author elhad
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    	//Pour ta culture :p -> Traite l'auto fermeture de la connexion
		//		try (Connection connection = DriverManager.getConnection(Constants.DATABASE_URL, Constants.DATABASE_USER, Constants.DATABASE_PASSWORD)){
		//		//Do stuff
		//	} 
		//	catch (SQLException e){
		//		e.printStackTrace();
		//	}
    	
    	//Test de connexion unique OK
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
    	
    	Connection connection2 = DriverManagerSingleton.getConnectionInstance();
    }
}
