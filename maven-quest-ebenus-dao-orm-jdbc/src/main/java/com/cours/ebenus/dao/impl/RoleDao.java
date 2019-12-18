/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author ElHadji
 */
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    private static final Log log = LogFactory.getLog(RoleDao.class);

    public RoleDao() {
        super(Role.class);
    }

    @Override
    public List<Role> findAllRoles() {
    	return super.findAll();
    }

    @Override
    public Role findRoleById(int idRole) {
        return super.findById(idRole);
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
    	Object obj = null;
        try {
            obj = Role.class.getDeclaredField("identifiant");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return super.findByCriteria(obj , identifiantRole);
    }

    @Override
    public Role createRole(Role role) {

        return super.create(role);
    }

    @Override
    public Role updateRole(Role role) {
    	String query = "UPDATE Role SET identifiant = ?, description = ? " +
    				   "WHERE idRole = ? ";
        return super.update(query, role);
    }

    @Override
    public boolean deleteRole(Role role) {

        String query = "DELETE FROM Role WHERE idRole = ?";
        return super.delete(query,role);
    }
}
