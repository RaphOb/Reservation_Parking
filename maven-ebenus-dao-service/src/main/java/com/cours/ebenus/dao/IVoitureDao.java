/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.util.List;

import com.cours.ebenus.dao.entities.Voiture;

/**
 *
 * @author ElHadji
 */
public interface IVoitureDao {

    public List<Voiture> findAllVoitures();

    public Voiture findVoitureById(int idVoiture);
    
    public List<Voiture> findVoitureByIdUtilisateur(int user);

    public List<Voiture> findVoitureByImmatriculation(String immatriculation);

    public List<Voiture> findVoituresByMarque(String marque);

    public Voiture createVoiture(Voiture user);

    public Voiture updateVoiture(Voiture user);

    public boolean deleteVoiture(Voiture user);


}
