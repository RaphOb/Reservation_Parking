/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;


import com.cours.ebenus.dao.DriverManagerSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    private static final Log log = LogFactory.getLog(RoleDao.class);
    private List<Role> rolesListDataSource;
    
    
    public RoleDao() {
        super(Role.class);
    }
    @Override
    public List<Role> findAllRoles() {
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
    	String selectSQL = "SELECT * FROM Role;";
    	try
    	{
    		PreparedStatement ps = connection.prepareStatement(selectSQL);
        	ResultSet rs = ps.executeQuery(selectSQL);
        	//Iterate on result
        	while (rs.next()) {
        		//Get fields from DB
        		Integer id = rs.getInt("idRole");
        		String identifiant = rs.getString("identifiant");
        		String description = rs.getString("description");
        		Integer version = rs.getInt("version");
        		//Build Role
        		Role role = new Role(id, identifiant, description, version);
        		//And build the list with each new role created
        		rolesListDataSource.add(role);
        	}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
        return rolesListDataSource;
    }

    @Override
    public Role findRoleById(int idRole) {
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
    	String selectSQL = "SELECT * FROM Role WHERE id='" + idRole + "\'";
    	Role role;
    	try
    	{
    		PreparedStatement ps = connection.prepareStatement(selectSQL);
        	ResultSet rs = ps.executeQuery(selectSQL);
    		//Get fields from DB
    		Integer id = rs.getInt("idRole");
    		String identifiant = rs.getString("identifiant");
    		String description = rs.getString("description");
    		Integer version = rs.getInt("version");
    		//Build Role
    		role = new Role(id, identifiant, description, version);
    		return role;
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
        return null;
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
    	String selectSQL = "SELECT * FROM Role WHERE identifiant='" + identifiantRole + "\'";
    	Role role;
    	try
    	{
    		PreparedStatement ps = connection.prepareStatement(selectSQL);
        	ResultSet rs = ps.executeQuery(selectSQL);
        	//Iterate on result
        	while (rs.next()) {
	    		//Get fields from DB
	    		Integer id = rs.getInt("idRole");
	    		String identifiant = rs.getString("identifiant");
	    		String description = rs.getString("description");
	    		Integer version = rs.getInt("version");
	    		//Build Role
	    		role = new Role(id, identifiant, description, version);
	    		//And build the list with each new role created
	    		rolesListDataSource.add(role);
        	}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
        return rolesListDataSource;
    }

    @Override
    public Role createRole(Role role) {
    	
    	Connection connection = DriverManagerSingleton.getConnectionInstance();
    	if(role != null)
    	{
    		//Je sais plus si il faut calculer l'id, en recréant un nouveau role comme la step d'avant
    		Integer idRole = role.getIdRole();
    		
    		String identifiant = role.getIdentifiant();
    		String description = role.getDescription();
        	Integer version = role.getVersion();
        	
        	String selectSQL = "INSERT INTO Role (idRole, identifiant, description, version) VALUES ('" + idRole + "\', '" + identifiant + "\', '" + description + "\', '" + version + "');";
        	try {
        		PreparedStatement ps = connection.prepareStatement(selectSQL);
        		ps.executeQuery(selectSQL);
        	}
        	catch(SQLException e)
        	{
        		e.printStackTrace();
        	}
    	}
    	
        return null;
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public boolean deleteRole(Role role) {
        return false;
    }
}
