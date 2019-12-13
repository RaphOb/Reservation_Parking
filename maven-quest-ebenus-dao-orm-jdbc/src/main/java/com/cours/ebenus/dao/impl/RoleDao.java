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
    	String query = "SELECT identifiant AS roleIdent, idRole, description, version FROM Role;";
    	return super.findAll(query);
    }

    @Override
    public Role findRoleById(int idRole) {
        String query = "SELECT identifiant AS roleIdent, idRole, description, version FROM Role" +
                " WHERE idRole = ?;";
        return super.findById(query,idRole);
    }

    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
        String query = "SELECT identifiant AS roleIdent, idRole, description, version FROM Role" +
                " WHERE identifiant = ?;";
        return super.findByCriteria(query,identifiantRole);
    }

    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public Role updateRole(Role role) {
        return null;
    }

    @Override
    public boolean deleteRole(Role role) {

        String query = "DELETE FROM Role WHERE idRole = ?";
        return super.delete(query,role);
    }
}
