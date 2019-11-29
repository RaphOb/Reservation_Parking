/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;


import com.cours.ebenus.dao.DriverManagerSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ElHadji
 */
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    private static final Log log = LogFactory.getLog(RoleDao.class);
    private List<Role> rolesListDataSource = new ArrayList<Role>();
    

    public RoleDao() {
        super(Role.class);
    }

    @Override
    public List<Role> findAllRoles() {
    	rolesListDataSource.clear();
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String selectSQL = "SELECT * FROM Role;";
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolesListDataSource;
    }

    @Override
    public Role findRoleById(int idRole) {
    	rolesListDataSource.clear();
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String selectSQL = "SELECT * FROM Role WHERE id='" + idRole + "\'";
        Role role;
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
    	rolesListDataSource.clear();
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String selectSQL = "SELECT * FROM Role WHERE identifiant='" + identifiantRole + "\'";
        Role role;
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolesListDataSource;
    }

    @Override
    public Role createRole(Role role) {
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        if (role != null) {
            String identifiant = role.getIdentifiant();
            String description = role.getDescription();
            Integer version = 1;

            String insertSQL = "INSERT INTO Role (identifiant, description, version) VALUES (?,?,?);";
            String lastId = "SELECT LAST_INSERT_ID() AS id;";
            
            try {
                PreparedStatement psInsert = connection.prepareStatement(insertSQL);
                
                psInsert.setString(1, identifiant);
                psInsert.setString(2, description);
                psInsert.setInt(3, version);
                psInsert.executeUpdate();
                
                //Récupération de l'id courant
                PreparedStatement psLastId = connection.prepareStatement(lastId);
                ResultSet rsLastId = psLastId.executeQuery();
                while(rsLastId.next())
                {
                	role.setIdRole(rsLastId.getInt("id"));
                }
                return role;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Role updateRole(Role role) {
        String sql = "UPDATE Role SET identidiant = ?, description = ? where id = ? ";
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setString(1,role.getIdentifiant());
            prep.setString(2,role.getDescription());
            prep.setInt(3,role.getIdRole());
            prep.executeUpdate();
            return  role;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteRole(Role role) {
        String sql = "DELETE FROM Role WHERE idRole =?";
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement prep;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, role.getIdRole());
            prep.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}