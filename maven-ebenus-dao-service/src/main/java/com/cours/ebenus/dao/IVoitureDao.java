/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import java.util.List;

/**
 *
 * @author ElHadji
 */
public interface IVoitureDao {

    public List<Voiture> findAllVoitures();

    public Voiture findVoitureById(int idVoiture);
    
    public Voiture findVoitureByUtilisateur(Utilisateur user);

    public Voiture findVoitureByImmatriculation(String immatriculation);

    public List<Voiture> findVoituresByMarque(String marque);

    public Voiture createVoiture(Voiture user);

    public Voiture updateVoiture(Voiture user);

    public boolean deleteVoiture(Voiture user);


}
