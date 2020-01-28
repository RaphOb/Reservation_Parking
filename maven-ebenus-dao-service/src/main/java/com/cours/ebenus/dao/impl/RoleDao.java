/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;

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
    	return super.findByCriteria(identifiantRole, "identifiant");
    }

    @Override
    public Role createRole(Role role) {

        return super.create(role);
    }

    @Override
    public Role updateRole(Role role) {
        return super.update(role);
    }

    @Override
    public boolean deleteRole(Role role) {
        return super.delete(role);
    }
}
