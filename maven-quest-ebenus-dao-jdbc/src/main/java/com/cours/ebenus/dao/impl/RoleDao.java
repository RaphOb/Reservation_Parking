/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.ConnectionHelper;
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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectSQL);
            rs = ps.executeQuery(selectSQL);
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
        } finally {
            ConnectionHelper.closeSqlResources(ps, rs);
        }
        return rolesListDataSource;
    }

    @Override
    public Role findRoleById(int idRole) {
        rolesListDataSource.clear();
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String selectSQL = "SELECT * FROM Role WHERE idRole='" + idRole + "\'";
        Role role = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectSQL);
            rs = ps.executeQuery(selectSQL);
            if (rs.next()) {
                //Get fields from DB
                Integer id = rs.getInt("idRole");
                String identifiant = rs.getString("identifiant");
                String description = rs.getString("description");
                Integer version = rs.getInt("version");
                //Build Role
                role = new Role(id, identifiant, description, version);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionHelper.closeSqlResources(ps, rs);
        }
        return role;
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
        rolesListDataSource.clear();
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        String selectSQL = "SELECT * FROM Role WHERE identifiant='" + identifiantRole + "\'";
        Role role;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(selectSQL);
            rs = ps.executeQuery(selectSQL);
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
        } finally {
            ConnectionHelper.closeSqlResources(ps, rs);
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
            PreparedStatement psInsert = null;
            ResultSet rsLastId = null;
            PreparedStatement psLastId = null;
            try {
                psInsert = connection.prepareStatement(insertSQL);

                psInsert.setString(1, identifiant);
                psInsert.setString(2, description);
                psInsert.setInt(3, version);
                psInsert.executeUpdate();

                //Récupération de l'id courant
                psLastId = connection.prepareStatement(lastId);
                rsLastId = psLastId.executeQuery();
                while (rsLastId.next()) {
                    role.setIdRole(rsLastId.getInt("id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert psInsert != null;
                    psInsert.close();
                    assert rsLastId != null;
                    rsLastId.close();
                    psLastId.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        String sql = "UPDATE Role SET identifiant = ?, description = ? where idRole = ? ";
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement prep = null;
        try {
            prep = connection.prepareStatement(sql);
            prep.setString(1, role.getIdentifiant());
            prep.setString(2, role.getDescription());
            prep.setInt(3, role.getIdRole());
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
            return role;
    }

    @Override
    public boolean deleteRole(Role role) {
        String sql = "DELETE FROM Role WHERE idRole =?";
        Connection connection = DriverManagerSingleton.getConnectionInstance();
        PreparedStatement prep = null;
        try {
            prep = connection.prepareStatement(sql);
            prep.setInt(1, role.getIdRole());
            prep.executeUpdate();
            prep.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                prep.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}