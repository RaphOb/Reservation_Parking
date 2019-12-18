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
    	
    	List<Utilisateur> u = s.getUtilisateurDao().findAllUtilisateurs();
    	List<Utilisateur> u2 = s.getUtilisateurDao().findUtilisateurByIdentifiant("nicolas.berger@gmail.com");
    	
    	List<Utilisateur> u3 = s.getUtilisateurDao().findUtilisateursByNom("Berger");
		List<Utilisateur> u4 = s.getUtilisateurDao().findUtilisateursByPrenom("Nicolas");
		List<Utilisateur> u5 = s.getUtilisateurDao().findUtilisateursByIdRole(1);
		List<Utilisateur> u6 = s.getUtilisateurDao().findUtilisateursByIdentifiantRole("Administrateur");
		Utilisateur u7 = s.getUtilisateurDao().findUtilisateurById(14);
		
    	System.out.println("1");
    	u.forEach(System.out::println);
    	System.out.println("2");
    	u2.forEach(System.out::println);
    	System.out.println("3");
    	u3.forEach(System.out::println);
    	System.out.println("4");
    	u4.forEach(System.out::println);
    	System.out.println("5");
    	u5.forEach(System.out::println);
    	System.out.println("6");
    	u6.forEach(System.out::println);
    	System.out.println("7");
    	System.out.println(u7.toString());
    	
    	
    	
    	List <Role> r = s.getRoleDao().findAllRoles();
    	Role r2 = s.getRoleDao().findRoleById(3);
    	List <Role> r3 = s.getRoleDao().findRoleByIdentifiant("Administrateur");
    	
    	System.out.println("1");
    	r.forEach(System.out::println);
    	System.out.println("2");
    	System.out.println(r2);
    	System.out.println("3");
    	r3.forEach(System.out::println);
    	
    	
//    	us.forEach(System.out::println);
//        ros.forEach(System.out::println);
//        List<Utilisateur> u = s.getUtilisateurDao().findUtilisateursByIdRole(1);
//        Role r = s.getRoleDao().findRoleById(1);
//        System.out.println(r.getDescription());

//    	Utilisateur user = s.getUtilisateurDao().findUtilisateurById(6);
//    	Role role = s.getRoleDao().findRoleById(1);
//
//    	role.setDescription("Le rôle administrateur");
//    	s.getRoleDao().updateRole(role);
//
//    	user.setPrenom("Jimmy");
//    	s.getUtilisateurDao().updateUtilisateur(user);

    	
//    	s.getUtilisateurDao().deleteUtilisateur(user);
    	
    	
    }
}
