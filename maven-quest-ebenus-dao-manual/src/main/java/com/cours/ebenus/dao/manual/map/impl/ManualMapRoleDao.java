/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.manual.map.impl;

import com.cours.ebenus.dao.DataSource;
import com.cours.ebenus.dao.IRoleDao;
import com.cours.ebenus.dao.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ElHadji
 */
public class ManualMapRoleDao extends AbstractMapDao<Role> implements IRoleDao {

    private static final Log log = LogFactory.getLog(ManualMapRoleDao.class);
    private Map<Integer, Role> rolesListDataSource = null;

    public ManualMapRoleDao() {
        super(Role.class, DataSource.getInstance().getRolesMapDataSource());
        rolesListDataSource = super.getmyMap();
    }

    /**
     * Méthode qui retourne la liste de tous les rôles de la database (ici
     * rolesListDataSource)
     *
     * @return La liste de tous les rôles de la database
     */
    @Override
    public List<Role> findAllRoles() {
        return new ArrayList<>(rolesListDataSource.values());
    }

    /**
     * Méthode qui retourne le rôle d'id passé en paramètre de la database (ici
     * rolesListDataSource)
     *
     * @param idRole L'id du rôle à récupérer
     * @return Le rôle d'id passé en paramètre, null si non trouvé
     */
    @Override
    public Role findRoleById(int idRole) {
        return rolesListDataSource.get(idRole);
    }

    /**
     * Méthode qui retourne une liste de tous les rôles de la database (ici
     * rolesListDataSource) dont l'identifiant est égal au paramètre passé
     *
     * @param identifiantRole L'identifiant des rôles à récupérer
     * @return Une liste de tous les rôles dont l'identifiant est égal au
     * paramètre passé
     */
    @Override
    public List<Role> findRoleByIdentifiant(String identifiantRole) {
        return rolesListDataSource.values().stream()
                .filter(r -> r.getIdentifiant().equals(identifiantRole))
                .collect(Collectors.toList());
    }

    /**
     * Méthode qui permet d'ajouter à rôle dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à ajouter
     * @return Le rôle ajouté ou null si échec
     */
    @Override
    public Role createRole(Role role) {
        Role r = new Role(rolesListDataSource.size() + 1, role.getIdentifiant(), role.getDescription());
        rolesListDataSource.put(rolesListDataSource.size() + 1, r);
        return r;

    }

    /**
     * Méthode qui permet d'update un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à modifier
     * @return Le rôle modifié ou null si ce dernier n'existe pas dans la
     * database
     */
    @Override
    public Role updateRole(Role role) {
        for (Map.Entry<Integer, Role> r :rolesListDataSource.entrySet()) {
            if (role.getIdRole().equals(r.getKey())) {
                rolesListDataSource.replace(r.getKey(), role);
                return  role;
            }
        }
        return null;
    }

    /**
     * Méthode qui permet de supprimer un rôle existant dans la database (ici
     * rolesListDataSource)
     *
     * @param role Le rôle à supprimer
     * @return True si suppression effectuée, false sinon
     */
    @Override
    public boolean deleteRole(Role role) {
    	if (rolesListDataSource.remove(rolesListDataSource.size()) != null)
        	return true;
        return false;
    }

}
