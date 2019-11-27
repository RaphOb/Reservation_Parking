package com.cours.ebenus.dao.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;

public class JUnitQuestEbenusManualMap extends JUnitQuestEbenus {

    private static final Log log = LogFactory.getLog(JUnitQuestEbenusManualMap.class);

    @BeforeClass
    public static void init() throws Exception {
        JUnitQuestEbenus.serviceFacade = new ServiceFacade(AbstractDaoFactory.FactoryDaoType.MANUAL_MAP_DAO_FACTORY);
        JUnitQuestEbenus.roles = JUnitQuestEbenus.serviceFacade.getRoleDao().findAllRoles();

    }
}
