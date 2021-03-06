/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.util.List;

import com.cours.ebenus.dao.entities.Utilisateur;

/**
 *
 * @author ElHadji
 */
public interface IUtilisateurDao {

    public List<Utilisateur> findAllUtilisateurs();

    public Utilisateur findUtilisateurById(int idUtilisateur);

    public List<Utilisateur> findUtilisateursByPrenom(String prenom);

    public List<Utilisateur> findUtilisateursByNom(String nom);

    public List<Utilisateur> findUtilisateurByEmail(String email);

    public List<Utilisateur> findUtilisateursByIdRole(int idRole);

    public List<Utilisateur> findUtilisateursByIdentifiantRole(String identifiantRole);

    public Utilisateur createUtilisateur(Utilisateur user);

    public Utilisateur updateUtilisateur(Utilisateur user);

    public boolean deleteUtilisateur(Utilisateur user);

    public Utilisateur authenticate(String email, String password);

}
