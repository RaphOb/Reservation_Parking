/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cours.ebenus.service.*;

import java.util.List;

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
    	ServiceFacade s = new ServiceFacade();
    	
    	Utilisateur user = s.getUtilisateurDao().findUtilisateurById(6);
    	Role role = s.getRoleDao().findRoleById(1);
    	
//    	role.setDescription("Le rôle administrateur");
//    	s.getRoleDao().updateRole(role);
//    	
//    	user.setPrenom("Jimmy");
//    	s.getUtilisateurDao().updateUtilisateur(user);
    	
//    	s.getUtilisateurDao().deleteUtilisateur(user);
    	
    	
    }
}
