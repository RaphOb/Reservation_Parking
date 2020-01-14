/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import com.cours.ebenus.dao.entities.PlaceParking;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;

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

        //TESTS FIND
        System.out.println(s.getUtilisateurDao().findUtilisateursByNom("Petit"));
        System.out.println(s.getUtilisateurDao().findUtilisateursByPrenom("Kamel"));
        System.out.println(s.getUtilisateurDao().findUtilisateurByEmail("admin@gmail.com"));
        System.out.println(s.getUtilisateurDao().findUtilisateursByIdRole(2));
        System.out.println(s.getUtilisateurDao().findUtilisateursByIdentifiantRole("Administrateur"));
        
        System.out.println(s.getRoleDao().findRoleByIdentifiant("Administrateur"));
        
        System.out.println(s.getVoitureDao().findVoitureByIdUtilisateur(2));
        System.out.println(s.getVoitureDao().findVoituresByMarque("Fiat"));
        System.out.println(s.getVoitureDao().findVoitureByImmatriculation("QZ-896-L0"));
        
        System.out.println(s.getPlaceParkingDao().findPlaceParkingByNumero("3"));
        System.out.println(s.getPlaceParkingDao().findPlaceParkingByIdVoiture(2));
        
        
        //TESTS DELETE (re-execute script to test, and put idVoiture to 3 on a place)
        
        List<PlaceParking> l = s.getPlaceParkingDao().findPlaceParkingByIdVoiture(3);
        
        for(PlaceParking p : l)
        {
        	s.getPlaceParkingDao().deletePlaceParking(p);
        }
    	s.getVoitureDao().deleteVoiture(s.getVoitureDao().findVoitureById(3));
    	
		List<Utilisateur> l2 = s.getUtilisateurDao().findUtilisateursByIdRole(3);
		    	
    	for(Utilisateur u : l2)
    	{
    		s.getUtilisateurDao().deleteUtilisateur(u);
    	}
    	s.getRoleDao().deleteRole(s.getRoleDao().findRoleById(3));
    	
    	
    	//TESTS CREATE
    	Role r = new Role("Role Test", "Description");
    	r = s.getRoleDao().createRole(r);
    	System.out.println("Role " + r);
    	
    	
    	Utilisateur u = new Utilisateur("Mr", "Prenom", "nom", "email", "motPasse", r);
    	u = s.getUtilisateurDao().createUtilisateur(u);
    	System.out.println("Utilisateur " + u);
    	
    	PlaceParking p = new PlaceParking(55, true);
    	p = s.getPlaceParkingDao().createPlaceParking(p);
    	System.out.println("Place " + p);
    	
    	Voiture v = new Voiture("Fiat Panda", "GG-767-XA", 4);
    	v = s.getVoitureDao().createVoiture(v);
    	System.out.println("Voiture " + v);
    	
    	//TESTS UPDATE
    	r.setDescription("descr modif");
    	r.setIdentifiant("Modif");
    	s.getRoleDao().updateRole(r);
    	
    	u.setNom("Update");
		u.setPrenom("Update bis");
    	s.getUtilisateurDao().updateUtilisateur(u);
    	
    	p.setAvailable(false);
    	p.setNum(49);
    	s.getPlaceParkingDao().updatePlaceParking(p);
    	
    	v.setMarque("Test");
    	s.getVoitureDao().updateVoiture(v);
    }
}
