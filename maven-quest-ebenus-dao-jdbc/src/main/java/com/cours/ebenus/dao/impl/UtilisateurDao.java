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

import com.cours.ebenus.dao.exception.EbenusException;
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
        String sql = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description " +
                "FROM Utilisateur " +
                "LEFT JOIN Role r on r.idRole= Utilisateur.idRole";
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
        String sql = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "LEFT JOIN Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.idUtilisateur = ? ";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, idUtilisateur);
            users = queryManagerResponse(prep.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "LEFT JOIN Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.prenom= ? ";
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
        String sql = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.nom= ? ";
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
        String sql = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
                "where Utilisateur.identifiant= ? ";
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
        String sql = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
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
        String sql = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
                "left join Role r on r.idRole = Utilisateur.idRole " +
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

        String sql1 = "SELECT count(*) from Utilisateur " +
                "where identifiant = ?";
        PreparedStatement p;
        try {
            p = connection.prepareStatement(sql1);
            p.setString(1, user.getIdentifiant());
            ResultSet r = p.executeQuery();
            while (r.next()) {
                if (r.getInt(1) > 0) {
                    throw new EbenusException("Already exist", -1);
                }
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }


        String sql = "INSERT  into Utilisateur (idRole, civilite, prenom, nom, identifiant, motPasse, dateCreation , dateModification) " +
                "select ?,?,?,?,?,?,?,? " +
                "from  utilisateur " +
                "where not exists (select 1 from Utilisateur where " +
                " identifiant = ?) LIMIT 1";
        String lastId = "SELECT LAST_INSERT_ID() AS id;";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, user.getRole().getIdRole());
            prep.setString(2, user.getCivilite());
            prep.setString(3, user.getPrenom());
            prep.setString(4, user.getNom());
            prep.setString(5, user.getIdentifiant());
            prep.setString(6, user.getMotPasse());
            Date dateCreation = new java.sql.Date(System.currentTimeMillis());
            Date dateModification = new java.sql.Date(System.currentTimeMillis());
            prep.setDate(7, (java.sql.Date) dateCreation);
            prep.setDate(8, (java.sql.Date) dateModification);
            prep.setString(9, user.getIdentifiant());
            prep.executeUpdate();

            user.setDateCreation((dateCreation));
            user.setDateModification(dateModification);

            //Récupération de l'id courant
            PreparedStatement psLastId = connection.prepareStatement(lastId);
            ResultSet rsLastId = psLastId.executeQuery();
            while (rsLastId.next()) {
                System.out.println(rsLastId.getInt("id"));
                user.setIdUtilisateur(rsLastId.getInt("id"));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "UPDATE Utilisateur " +
                "SET idRole = ?, civilite = ?, prenom = ?, nom = ?, identifiant = ?, motPasse = ?, dateModification = ? " +
                "WHERE idUtilisateur = ?";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, user.getRole().getIdRole());
            prep.setString(2, user.getCivilite());
            prep.setString(3, user.getPrenom());
            prep.setString(4, user.getNom());
            prep.setString(5, user.getIdentifiant());
            prep.setString(6, user.getMotPasse());
            Date dateModification = new java.sql.Date(System.currentTimeMillis());
            prep.setDate(7, (java.sql.Date) dateModification);
            prep.setInt(8, user.getIdUtilisateur());
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
        String sql = "DELETE FROM Utilisateur " +
                "WHERE idUtilisateur = ?";
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
     * Méthode qui vérifie les logs email / password d'un Utilisateur dans la
     * base de données
     *
     * @param email    L'email de l'Utilisateur
     * @param password Le password de l'Utilisateur
     * @return L'Utilisateur qui tente de se logger si trouvé, null sinon
     */
    @Override
    public Utilisateur authenticate(String email, String password) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String sql = "SELECT * FROM Utilisateur" +
                "WHERE identifiant = ? AND motPasse = ?";
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            if (rs.next() != false) {
                return findUtilisateurById(rs.getInt("idUtilisateur"));
            } else {
                log.debug("No user found with theses identifiers...");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
