package com.cours.ebenus.dao.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.ebenus.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cours.ebenus.dao.DataSourceSingleton;
import com.ibatis.common.jdbc.ScriptRunner;

@RunWith(Suite.class)
@SuiteClasses({ JUnitUtilisateurDao.class, 
				JUnitRoleDao.class, 
				JUnitVoitureDao.class,
				JUnitPlaceParkingDao.class,
                JUnitHistoryDao.class
})
public class JUnitDao {

    private static final Log log = LogFactory.getLog(JUnitDao.class);
    
    @BeforeClass
    public static void initDataBase() throws FileNotFoundException, IOException, SQLException {
        // Initialiser les données de la base de données
    	String scriptSqlPath = Constants.SQL_JUNIT_PATH_FILE;
        ScriptRunner sr = new ScriptRunner(DataSourceSingleton.getInstance().getConnection(), false, false);
        Reader reader = new BufferedReader(new FileReader(scriptSqlPath));
        //Running the script
        sr.runScript(reader);
    }
}
