/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IVoitureDao;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class VoitureDao extends AbstractDao<Voiture> implements IVoitureDao {

    private static final Log log = LogFactory.getLog(VoitureDao.class);

    public VoitureDao() {
        super(Voiture.class);
    }

    @Override
    public List<Voiture> findAllVoitures() {
    	return super.findAll();
    }

    @Override
    public Voiture findVoitureById(int idVoiture) {
        return null;
    }
    
    @Override
	public Voiture findVoitureByUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Voiture findVoitureByImmatriculation(String immatriculation) {
        return null;
    }
    
	@Override
	public List<Voiture> findVoituresByMarque(String marque) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Voiture createVoiture(Voiture Voiture) {

        return super.create(Voiture);
    }

    @Override
    public Voiture updateVoiture(Voiture Voiture) {
        return super.update(Voiture);
    }

    @Override
    public boolean deleteVoiture(Voiture Voiture) {
        return super.delete(Voiture);
    }

	
}
