/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.list.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.cours.ebenus.dao.exception.EbenusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ElHadji
 */
public class ManualListUtilisateurDao extends AbstractListDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(ManualListUtilisateurDao.class);
    private List<Utilisateur> utilisateursListDataSource = null;

    public ManualListUtilisateurDao() {
        super(Utilisateur.class, DataSource.getInstance().getUtilisateursListDataSource());
        utilisateursListDataSource = super.findAll();
    }

    /**
     * Méthode qui retourne la liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource)
     *
     * @return La liste de tous les utilisateurs de la database
     */
    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilisateursListDataSource;
    }

    /**
     * Méthode qui retourne l'utilisateur d'id passé en paramètre de la database
     * (ici utilisateursListDataSource)
     *
     * @param idUtilisateur L'id de l'user à récupérer
     * @return L'utilisateur d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        return utilisateursListDataSource.stream()
                .filter(u -> u.getIdUtilisateur().equals(idUtilisateur))
                .findAny().orElse(null);
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le prénom est égal au paramètre
     * passé
     *
     * @param prenom Le prénom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le prénom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        return utilisateursListDataSource.stream()
                .filter(u -> u.getPrenom().equals(prenom))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le nom est égal au paramètre passé
     *
     * @param nom Le nom des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le nom est égal au
     * paramètre passé
     */
    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        return utilisateursListDataSource.stream()
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
        return utilisateursListDataSource.stream()
                .filter(u -> u.getIdentifiant().equals(identifiant))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à ajouter
     * @return L'utilisateur ajouté ou null si échec
     */
    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        boolean exist = this.utilisateursListDataSource.stream().anyMatch(t -> t.getIdentifiant().equals(user.getIdentifiant()));
        if (exist) {
            throw new EbenusException("Une erreur s’est produite, il existe déjà un utilisateur avec l’identifiant " + user.getIdentifiant() + " dans l’application");
        } else {
            Utilisateur u = new Utilisateur(utilisateursListDataSource.size() + 1, user.getCivilite(), user.getPrenom(), user.getNom(), user.getIdentifiant(), user.getMotPasse(), user.getDateNaissance(), user.getRole());
            Date d = new Date(System.currentTimeMillis());
            u.setDateCreation(d);
            u.setDateModification(d);
            this.utilisateursListDataSource.add(u);
            return u;
        }
    }

    /**
     * Méthode qui permet d'update un utilisateur existant dans la database (ici
     * utilisateursListDataSource)
     *
     * @param user L'utilisateur à modifier
     * @return L'utilisateur modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        for (int i = 0; i < utilisateursListDataSource.size(); i++) {
            if (utilisateursListDataSource.get(i).getIdUtilisateur().equals(user.getIdUtilisateur())) {
                utilisateursListDataSource.set(i, user);
                utilisateursListDataSource.get(i).setDateModification(new Date(System.currentTimeMillis()));
            }
        }
        return user;
    }

    /**
     * Méthode qui permet de supprimer un utilisateur existant dans la database
     * (ici utilisateursListDataSource)
     *
     * @param user L'utilisateur à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        return utilisateursListDataSource.remove(user);
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme id celui passé en
     * paramètre
     *
     * @param idRole L'id du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme id celui
     * passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
        return utilisateursListDataSource.stream().
                filter(u -> u.getRole().getIdRole().equals(idRole))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui retourne une liste de tous les utilisateurs de la database
     * (ici utilisateursListDataSource) dont le rôle a comme identifiant celui
     * passé en paramètre
     *
     * @param identifiantRole L'identifiant du rôle des utilisateurs à récupérer
     * @return Une liste de tous les utilisateurs dont le rôle a comme
     * identifiant celui passé en paramètre
     */
    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
        return utilisateursListDataSource.stream()
                .filter(u -> u.getRole().getIdentifiant().equals(identifiantRole))
                .collect(Collectors.toList());
    }
}
