/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.main;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.manual.list.impl.AbstractListDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListRoleDao;
import com.cours.ebenus.dao.manual.list.impl.ManualListUtilisateurDao;
import com.cours.ebenus.service.IServiceFacade;
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
    	
    	IRoleDao m = new ManualListRoleDao();
    	IUtilisateurDao r = new ManualListUtilisateurDao();

        Role role = new Role(9, "un truc", "un autre truc");
        Utilisateur u = new Utilisateur(1, "ss", "sasasa", "sasasa", "sasasasa", "passw0rd", new Date(System.currentTimeMillis()),role);
//        r.createRole(role);
        r.createUtilisateur(u);
        System.out.println(r.findAllUtilisateurs());

        Utilisateur f = new Utilisateur(29, "ss", "rererere", "rerere", "rererere", "passw0rd", new Date(System.currentTimeMillis()),role);
        r.updateUtilisateur(f);
        System.out.println(r.findAllUtilisateurs());
//        r.deleteUtilisateur(u);
//        System.out.println(r.findAllUtilisateurs());



        IServiceFacade serviceFacade = null;

    }
}
