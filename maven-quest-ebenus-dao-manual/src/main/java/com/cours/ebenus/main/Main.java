/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.manual.list.impl.AbstractListDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListRoleDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListUtilisateurDao;
import com.cours.ebenus.dao.manual.map.impl.ManualMapRoleDao;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

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
    	
//    	AbstractListDao m = new ManualListRoleDao();
    	
    	
		//LISTS
    	IRoleDao rDao = new ManualListRoleDao();
    	IUtilisateurDao uDao = new ManualListUtilisateurDao();
			
    		//Role OK sauf update
//	    	Role role = new Role(6, "Dieu", "Créateur");
//	        rDao.createRole(role);
//	        System.out.println(rDao.findAllRoles());
//	        System.out.println(rDao.findRoleById(2));
//	        System.out.println(rDao.findRoleByIdentifiant("Créateur"));
//	        rDao.updateRole(role);
//	        System.out.println(rDao.findRoleById(6));
//	        rDao.deleteRole(role);
//	        System.out.println(rDao.findAllRoles());
        	
        	//Users OK sauf update
    		Role role = new Role( "Dieu", "Créateur");
//	    	Utilisateur u = new Utilisateur(29, "Francais", "Franck", "Dupont", "Toss01", "passw0rd", new Date(System.currentTimeMillis()), role);
	    	rDao.createRole(role);
        System.out.println( rDao.findAllRoles());
//	    	uDao.createUtilisateur(u);
//	      	System.out.println(uDao.findAllUtilisateurs());
//	      	System.out.println(uDao.findUtilisateursByPrenom("Franck"));
//	      	System.out.println(uDao.findUtilisateursByNom("Dupont"));
//	      	System.out.println(uDao.findUtilisateurByIdentifiant("Toss01"));
//	      	System.out.println(uDao.findUtilisateursByIdRole(6));
//	      	System.out.println(uDao.findUtilisateursByIdentifiantRole("Créateur"));
//	      	uDao.updateUtilisateur(u);
//	      	uDao.deleteUtilisateur(u);
//	      	System.out.println(uDao.findAllUtilisateurs());
        

		//MAPS
//    	IRoleDao rDao2 = new ManualMapRoleDao();
//    	IUtilisateurDao uDao2 = new ManualMapUtilisateurDao();
//	
//	      	//Role
//	    	Role role2 = new Role(1, "Standard", "un autre truc");
//	    	rDao.createRole(role2);
//	        System.out.println(rDao.findAllRoles());
//	        
	        //Users
	        
	        
	
		//ARRAYS
    	
	        //Role
	        
	        //Users
        
        
        //Test Facade
        IServiceFacade serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.MANUAL_LIST_DAO_FACTORY);
        System.out.println(serviceFacade.getUtilisateurDao().findAllUtilisateurs());

    }
}
