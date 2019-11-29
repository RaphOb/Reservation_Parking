/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DriverManagerSingleton;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ElHadji
 */
public class UtilisateurDao extends AbstractDao<Utilisateur> implements IUtilisateurDao {

    private static final Log log = LogFactory.getLog(UtilisateurDao.class);

    public UtilisateurDao() {
        super(Utilisateur.class);
    }

    public List<Utilisateur> queryManagerResponse(ResultSet rs) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                Role r = new Role();
                u.setIdUtilisateur(rs.getInt("idUtilisateur"));
                r.setIdRole(rs.getInt("idRole"));
                r.setIdentifiant(rs.getString("roleIdent"));
                r.setDescription(rs.getString("description"));
                u.setRole(r);
                u.setCivilite((rs.getString("civilite")));
                u.setIdentifiant(rs.getString("identifiant"));
                u.setPrenom(rs.getString("prenom"));
                u.setNom(rs.getString("nom"));
                u.setMotPasse(rs.getString("motPasse"));
                u.setDateCreation(rs.getTimestamp("dateCreation"));
                u.setDateNaissance(rs.getTimestamp("dateNaissance"));
                u.setDateCreation(rs.getTimestamp("dateModification"));
                utilisateurs.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        String sql = "SELECT utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description " +
                "FROM utilisateur " +
                "left join Role r on r.idRole= utilisateur.idRole";
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            return queryManagerResponse(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        List<Utilisateur> users = new ArrayList<>();
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM utilisateur" +
                "left join Role r on r.idRole = utilisateur.idRole " +
                "where utilisateur.idUtilisateur = ? ";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, idUtilisateur);
            users = queryManagerResponse(prep.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users.get(0) != null) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM utilisateur" +
                "left join Role r on r.idRole = utilisateur.idRole " +
                "where utilisateur.prenom= ? ";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setString(1, prenom);
            return queryManagerResponse(prep.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM utilisateur" +
                "left join Role r on r.idRole = utilisateur.idRole " +
                "where utilisateur.nom= ? ";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setString(1, nom);
            return queryManagerResponse(prep.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM utilisateur" +
                "left join Role r on r.idRole = utilisateur.idRole " +
                "where utilisateur.identifiant= ? ";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setString(1, identifiant);
            return queryManagerResponse(prep.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM utilisateur" +
                "left join Role r on r.idRole = utilisateur.idRole " +
                "where r.idRole= ? ";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, idRole);
            return queryManagerResponse(prep.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM utilisateur" +
                "left join Role r on r.idRole = utilisateur.idRole " +
                "where r.identifiant= ? ";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setString(1, identifiantRole);
            return queryManagerResponse(prep.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "INSERT into utilisateur (idRole, civilite, prenom, nom, identifiant, motPasse)" +
                "values (?,?,?,?,?,?)";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1,user.getRole().getIdRole());
            prep.setString(2,user.getCivilite());
            prep.setString(3,user.getPrenom());
            prep.setString(4,user.getNom());
            prep.setString(5,user.getIdentifiant());
            prep.setString(6,user.getMotPasse());
            ResultSet rs = prep.executeQuery(sql);
            while (rs.next()){
                Utilisateur u = new Utilisateur();
                u.setIdUtilisateur(rs.getInt("idUtilisateur"));
                u.setPrenom(rs.getString("prenom"));
                u.setNom(rs.getString("nom"));
                u.setDateCreation(rs.getTimestamp("dateCreation"));
                u.setDateModification(rs.getTime("dateModification"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "UPDATE utilisateur" + 
        		"SET idRole = ?, civilite = ?, prenom = ?, nom = ?, identifiant = ?, motPasse = ?, dateModification = ?" + 
        		"WHERE id = ?";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1,user.getRole().getIdRole());
            prep.setString(2,user.getCivilite());
            prep.setString(3,user.getPrenom());
            prep.setString(4,user.getNom());
            prep.setString(5,user.getIdentifiant());
            prep.setString(6,user.getMotPasse());
            prep.setInt(7,user.getIdUtilisateur());
            prep.setDate(8, (java.sql.Date) new Date(System.currentTimeMillis()));
            prep.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "DELETE FROM utilisateur" + 
        		"WHERE id = ?";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, user.getIdUtilisateur());
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Méthode qui vérifie les logs email / password d'un utilisateur dans la
     * base de données
     *
     * @param email    L'email de l'utilisateur
     * @param password Le password de l'utilisateur
     * @return L'utilisateur qui tente de se logger si trouvé, null sinon
     */
    @Override
    public Utilisateur authenticate(String email, String password) {
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT * FROM utilisateur" + 
        		"WHERE identifiant = ? AND motPasse = ?";
        PreparedStatement prep;
        try {
        	prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            if (rs.next() != false) {
            	return findUtilisateurById(rs.getInt("idUtilisateur"));
            }
            else
            {
            	log.debug("No user found with theses identifiers...");
            	return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
