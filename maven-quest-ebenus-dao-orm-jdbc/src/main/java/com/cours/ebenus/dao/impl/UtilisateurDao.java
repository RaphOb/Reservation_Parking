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
    	return super.findAll();
    }

    @Override
    public Utilisateur findUtilisateurById(int idUtilisateur) {
        return super.findById(idUtilisateur);
    }

    @Override
    public List<Utilisateur> findUtilisateursByPrenom(String prenom) {
        Object obj = null;
        try {
            obj = Utilisateur.class.getDeclaredField("prenom");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return super.findByCriteria(obj, prenom);
    }

    @Override
    public List<Utilisateur> findUtilisateursByNom(String nom) {
        Object obj = null;
        try {
            obj = Utilisateur.class.getDeclaredField("nom");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    	return super.findByCriteria(obj, nom);
    }

    @Override
    public List<Utilisateur> findUtilisateurByIdentifiant(String identifiant) {
        Object obj = null;
        try {
            obj = Utilisateur.class.getDeclaredField("identifiant");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    	return super.findByCriteria(obj, identifiant);
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdRole(int idRole) {
        Object obj = null;
        try {
            obj = Utilisateur.class.getDeclaredField("role");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    	return super.findByCriteria(obj, Integer.toString(idRole));
    }

    @Override
    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole) {
    	
    	Object obj = null;
        try {
            obj = Role.class.getDeclaredField("identifiant");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    	return super.findByCriteria(obj, identifiantRole);
    	
//    	String query = "SELECT Utilisateur.*, r.identifiant AS roleIdent, r.idRole, r.description FROM Utilisateur " +
//                "left join Role r on r.idRole = Utilisateur.idRole " +
//                "where r.identifiant= ? ";
//    	return super.findByCriteria(query, identifiantRole);
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur user) {
    	String query = "INSERT  into Utilisateur (idRole, civilite, prenom, nom, identifiant, motPasse,dateNaissance, dateCreation , dateModification) " +
                "SELECT ?,?,?,?,?,?,?,?,? " +
                "FROM  Utilisateur " +
                "WHERE NOT EXISTS (SELECT 1 FROM Utilisateur WHERE " +
                " identifiant = ?) LIMIT 1";
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
	                "WHERE identifiant = ? AND motPasse = ?";
	        PreparedStatement prep = null;
	        ResultSet rs = null;
	        
	        try {
	            prep = connection.prepareStatement(sql);
	            prep.setString(1,  email);
	            prep.setString(2,  password);
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
