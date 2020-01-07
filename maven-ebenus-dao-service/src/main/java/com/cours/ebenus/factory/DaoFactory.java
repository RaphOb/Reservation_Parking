/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.factory;

import com.cours.ebenus.dao.IPlaceParkingDao;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.IVoitureDao;
import com.cours.ebenus.dao.impl.PlaceParkingDao;
import com.cours.ebenus.dao.impl.RoleDao;
import com.cours.ebenus.dao.impl.UtilisateurDao;
import com.cours.ebenus.dao.impl.VoitureDao;

/**
 *
 * @author ElHadji
 */
public class DaoFactory extends AbstractDaoFactory {

    @Override
    public IUtilisateurDao getUtilisateurDao() {
        return new UtilisateurDao();
    }

    @Override
    public IRoleDao getRoleDao() {
        return new RoleDao();
    }
    
    public IPlaceParkingDao getPlaceParkingDao() {
        return new PlaceParkingDao();
    }
    
    public IVoitureDao getVoitureDao() {
        return new VoitureDao();
    }
}
