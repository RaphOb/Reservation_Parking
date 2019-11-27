/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.exception.EbenusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class ManualMapUtilisateurDao extends AbstractMapDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualMapUtilisateurDao.class);
    private Map<Integer, Utilisateur> utilisateursMapDataSource = null;

    public ManualMapUtilisateurDao() {
      	super(Utilisateur.class, DataSource.getInstance().getUtilisateursMapDataSource());
      	utilisateursMapDataSource = super.getmyMap();
    }
    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
    	return new ArrayList<>(utilisateursMapDataSource.values());
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursMapDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        return utilisateursMapDataSource.get(idUtilisateur);
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	return utilisateursMapDataSource.values().stream()
    			.filter(u -> u.getPrenom().equals(prenom))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	return utilisateursMapDataSource.values().stream()
    			.filter(u -> u.getNom().equals(nom))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont l'identifiant est égal au paramètre
     * passé
     *
     * @param identifiant Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
    	return utilisateursMapDataSource.values().stream()
    			.filter(u -> u.getIdentifiant().equals(identifiant))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	return utilisateursMapDataSource.values().stream()
    			.filter(u -> u.getRole().getIdRole().equals(idRole))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursMapDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	return utilisateursMapDataSource.values().stream()
    			.filter(u -> u.getRole().getIdentifiant().equals(identifiantRole))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursMapDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	boolean exist = this.utilisateursMapDataSource.entrySet().stream().anyMatch(t -> t.getValue().getIdentifiant().equals(user.getIdentifiant()));
        if (exist) {
            throw new EbenusException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant " + user.getIdentifiant() + " dans l’application");
        } else {
	        Utilisateur u = new Utilisateur(utilisateursMapDataSource.size() + 1,  user.getCivilite(), user.getPrenom(), user.getNom(), user.getIdentifiant(), user.getMotPasse(), user.getDateNaissance(), user.getRole());
	        Date d = new Date(System.currentTimeMillis());
	        u.setDateCreation(d);
	        u.setDateModification(d);
	    	utilisateursMapDataSource.put(utilisateursMapDataSource.size() + 1, u);
	        return u;
        }
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursMapDataSource)return null;
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	if (utilisateursMapDataSource.get(utilisateursMapDataSource.size()) == null) {
    		log.debug("in");
            return null;
        } else {
            return user;
        }
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursMapDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        if (utilisateursMapDataSource.remove(utilisateursMapDataSource.size()) != null)
        	return true;
        return false;
    }
}
