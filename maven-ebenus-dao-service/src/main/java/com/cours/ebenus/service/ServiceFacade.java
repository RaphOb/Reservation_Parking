/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.service;

import com.cours.ebenus.dao.IPlaceParkingDao;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.IVoitureDao;
import com.cours.ebenus.factory.AbstractDaoFactory;
import com.cours.ebenus.factory.AbstractDaoFactory.FactoryDaoType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ServiceFacade implements IServiceFacade {

    private static final Log log = LogFactory.getLog(ServiceFacade.class);
    private final AbstractDaoFactory.FactoryDaoType DEFAULT_IMPLEMENTATION = AbstractDaoFactory.FactoryDaoType.JDBC_DAO_FACTORY;

    // On liste toutes les DAO : un DAO pour chaque entité (Utilisateur,Role ect ....)
    private IUtilisateurDao utilisateurDao = null;

    private IRoleDao roleDao = null;
    
    private IPlaceParkingDao placeParkingDao = null;
    
    private IVoitureDao voitureDao = null;

    public ServiceFacade() {
        // mettre tous les DAO
        utilisateurDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getUtilisateurDao();
        roleDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getRoleDao();
        placeParkingDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getPlaceParkingDao();
        voitureDao = AbstractDaoFactory.getFactory(DEFAULT_IMPLEMENTATION).getVoitureDao();
    }

    public ServiceFacade(FactoryDaoType daoType) {
        // mettre tous les DAO
        utilisateurDao = AbstractDaoFactory.getFactory(daoType).getUtilisateurDao();
        roleDao = AbstractDaoFactory.getFactory(daoType).getRoleDao();
        placeParkingDao = AbstractDaoFactory.getFactory(daoType).getPlaceParkingDao();
        voitureDao = AbstractDaoFactory.getFactory(daoType).getVoitureDao();
    }

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    @Override
    public IRoleDao getRoleDao() {
        return roleDao;
    }

	@Override
	public IPlaceParkingDao getPlaceParkingDao() {
		return placeParkingDao;
	}
	
	@Override
	public IVoitureDao getVoitureDao() {
		// TODO Auto-generated method stub
		return voitureDao;
	}
}
