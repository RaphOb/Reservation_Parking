/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.IUtilisateurDao;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cours.ebenus.exception.EbenusException;
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

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return super.findAll();
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        return super.findById(idUtilisateur);
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        return super.findByCriteria(prenom, "prenom");
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
    	return super.findByCriteria(nom, "nom");
    }

    @Override
    public List<Utilisateur> findUtilisateurByEmail(String email) {
    	return super.findByCriteria(email, "email");
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
    	return super.findByCriteria(idRole, "idRole");
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
        return super.findByCriteria(identifiantRole, "identifiant");
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
        Connection connection = null;
        try {
            connection = DataSourceSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql1 = "SELECT count(*) FROM Utilisateur " +
                "WHERE email = ?";
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            p = connection.prepareStatement(sql1);
            p.setString(1, user.getEmail());
            r = p.executeQuery();
            while (r.next()) {
                if (r.getInt(1) > 0) {
                    throw new EbenusException("Already exist", -1);
                }
            }

        } catch (SQLException a) {
            a.printStackTrace();
        }
        finally {
            ConnectionHelper.closeSqlResources(connection,p,r);
        }
        return super.create(user);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur user) {

        return super.update(user);
    }

    @Override
    public boolean deleteUtilisateur(Utilisateur user) {
        return super.delete(user);
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
        Connection connection;
        Utilisateur u = null;
        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            String sql = "SELECT * FROM Utilisateur " +
                    "WHERE email = ? AND motPasse = ?";
            PreparedStatement prep = null;
            ResultSet rs = null;

            try {
                prep = connection.prepareStatement(sql);
                prep.setString(1, email);
                prep.setString(2, password);
                rs = prep.executeQuery();
                if (rs.next() != false) {
                    u = findUtilisateurById(rs.getInt("idUtilisateur"));
                } else {
                    log.debug("No user found with theses identifiers...");
                    u = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConnectionHelper.closeSqlResources(connection, prep, rs);
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return u;
    }
}
