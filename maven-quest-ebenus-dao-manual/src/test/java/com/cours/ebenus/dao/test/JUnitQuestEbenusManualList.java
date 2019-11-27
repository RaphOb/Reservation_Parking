package com.cours.ebenus.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;

public class JUnitQuestEbenusManualList extends JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitQuestEbenusManualList.class);

    @BeforeClass
    public static void init() throws Exception {
        JUnitQuestEbenus.serviceFacade  = new ServiceFacade();
        JUnitQuestEbenus.utilisateurs = JUnitQuestEbenus.serviceFacade.getUtilisateurDao().findAllUtilisateurs();
        JUnitQuestEbenus.roles = JUnitQuestEbenus.serviceFacade.getRoleDao().findAllRoles();
        JUnitQuestEbenus.roles.forEach(System.out::println);

    }
}
