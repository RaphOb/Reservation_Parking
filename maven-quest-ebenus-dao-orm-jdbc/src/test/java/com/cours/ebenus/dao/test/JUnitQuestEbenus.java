package com.cours.ebenus.dao.test;

import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.DriverManagerSingleton;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.exception.EbenusException;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.utils.Constants;
import com.ibatis.common.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitQuestEbenus.class);
    private static IServiceFacade serviceFacade = null;
    

    @BeforeClass
    public static void init() throws Exception {
        // Configuration de l'application
    	serviceFacade = new ServiceFacade();
    }

    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        // Initialiser les données de la base de données
    	String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        ScriptRunner sr = new ScriptRunner(DataSourceSingleton.getInstance().getConnection(), false, false);
        Reader reader = new BufferedReader(new FileReader(scriptSqlPath));
        //Running the script
        sr.runScript(reader);
    }

    
    
    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        serviceFacade = null;
        log.debug("Sortie de la methode");
    }
}
