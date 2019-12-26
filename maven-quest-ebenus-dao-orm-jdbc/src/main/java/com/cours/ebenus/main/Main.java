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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

/**
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

        //TEST CREATE
        Role role = new Role();
        role.setDescription("Une desription");
        role.setIdentifiant("Un identifiant");
        role.setIdRole(13);
//        s.getRoleDao().createRole(role);

        Utilisateur u = new Utilisateur();
		u.setCivilite("M");
		u.setNom("raphael");
		u.setPrenom("raph");
		u.setMotPasse("pass");
		u.setIdentifiant("rapgou@gmail.com");
		Date date = new Date(System.currentTimeMillis());
		u.setDateNaissance(date);
		u.setRole(role);
//		s.getUtilisateurDao().createUtilisateur(u);

		
		//TEST UPDATE
		Role role2 = s.getRoleDao().findRoleById(5);
		log.debug(role2.toString());
		role2.setDescription("Une desription");
		role2.setIdentifiant("Un identifiant");
		role2 = s.getRoleDao().updateRole(role2);
		log.debug(role2.toString());
		
		Utilisateur u2 = s.getUtilisateurDao().findUtilisateurById(21);
		log.debug(u2.toString());
		u2.setCivilite("Mme");
		u2.setNom("Test");
		u2.setMotPasse("Essai");
		u2.setIdentifiant("rapgou@gmail.com");
		Date date2;
		try {
			date2 = new SimpleDateFormat("2014-02-14").parse("2014-02-14");
			u2.setDateNaissance(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Date date3 = new Date(System.currentTimeMillis());
		u2.setDateModification(date3);
		
		Date date4 = new Date(System.currentTimeMillis());
		u2.setDateNaissance(date4);
		
		u2.setRole(role2);
		u2 = s.getUtilisateurDao().updateUtilisateur(u2);
		log.debug(u2.toString());
		
//    	List<Utilisateur> u = s.getUtilisateurDao().findAllUtilisateurs();
//    	List<Utilisateur> u2 = s.getUtilisateurDao().findUtilisateurByIdentifiant("nicolas.berger@gmail.com");
//
//    	List<Utilisateur> u3 = s.getUtilisateurDao().findUtilisateursByNom("Berger");
//		List<Utilisateur> u4 = s.getUtilisateurDao().findUtilisateursByPrenom("Nicolas");
//		List<Utilisateur> u5 = s.getUtilisateurDao().findUtilisateursByIdRole(1);
//		List<Utilisateur> u6 = s.getUtilisateurDao().findUtilisateursByIdentifiantRole("Administrateur");
//		Utilisateur u7 = s.getUtilisateurDao().findUtilisateurById(14);
//
//    	System.out.println("1");
//    	u.forEach(System.out::println);
//    	System.out.println("2");
//    	u2.forEach(System.out::println);
//    	System.out.println("3");
//    	u3.forEach(System.out::println);
//    	System.out.println("4");
//    	u4.forEach(System.out::println);
//    	System.out.println("5");
//    	u5.forEach(System.out::println);
//    	System.out.println("6");
//    	u6.forEach(System.out::println);
//    	System.out.println("7");
//    	System.out.println(u7.toString());
//
//
//
//    	List <Role> r = s.getRoleDao().findAllRoles();
//    	Role r2 = s.getRoleDao().findRoleById(3);
//    	List <Role> r3 = s.getRoleDao().findRoleByIdentifiant("Administrateur");
//
//    	System.out.println("1");
//    	r.forEach(System.out::println);
//    	System.out.println("2");
//    	System.out.println(r2);
//    	System.out.println("3");
//    	r3.forEach(System.out::println);


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
