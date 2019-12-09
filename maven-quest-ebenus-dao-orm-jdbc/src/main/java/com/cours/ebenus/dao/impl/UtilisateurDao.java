/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Utilisateur;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class UtilisateurDao extends AbstractDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);

    public UtilisateurDao() {
        super(Utilisateur.class);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description " +
                "FROM Utilisateur " +
                "LEFT JOIN Role r on r.idRole= Utilisateur.idRole";
    	return super.findAll(query);
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "LEFT JOIN Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.idUtilisateur = ? ";
        return super.findById(query, idUtilisateur);
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "LEFT JOIN Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.prenom= ? ";
        return super.findByCriteria(query, prenom, Utilisateur.class);
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.nom= ? ";
    	return super.findByCriteria(query, nom, Utilisateur.class);
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.identifiant= ? ";
    	return super.findByCriteria(query, identifiant, Utilisateur.class);
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
                "where r.idRole= ? ";
    	return super.findByCriteria(query, Integer.toString(idRole), Utilisateur.class);
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
                "where r.identifiant= ? ";
    	return super.findByCriteria(query, identifiantRole, Utilisateur.class);
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	String query = "INSERT  into Utilisateur (idRole, civilite, prenom, nom, identifiant, motPasse,dateNaissance, dateCreation , dateModification) " +
                "SELECT ?,?,?,?,?,?,?,?,? " +
                "FROM  Utilisateur " +
                "WHERE NOT EXISTS (SELECT 1 FROM Utilisateur WHERE " +
                " identifiant = ?) LIMIT 1";
        return super.create(query, user);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	String query = "UPDATE Utilisateur " +
                "SET idRole = ?, civilite = ?, prenom = ?, nom = ?, identifiant = ?, motPasse = ?, dateModification = ? " +
                "WHERE idUtilisateur = ?";
    	return super.update(query, user);
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	String query = "DELETE FROM Utilisateur " +
                "WHERE idUtilisateur = ?";
    	return super.delete(query, user);
    }

    /**
     * Méthode qui vérifie les logs email / password d'un utilisateur dans la
     * base de données
     *
     * @param email L'email de l'utilisateur
     * @param password Le password de l'utilisateur
     * @return L'utilisateur qui tente de se logger si trouvé, null sinon
     */
    @Override
    public Utilisateur authenticate(String email, String password) {
        return null;
    }
}
