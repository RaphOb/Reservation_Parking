/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao.entities;

import java.util.Date;

import com.cours.ebenus.dao.annotations.DBTable;

/**
 * @author elhad
 */
public class Utilisateur extends Entities {

    @DBTable(columnName = "idUtilisateur")
    private Integer idUtilisateur;
    @DBTable(columnName = "idRole")
    private Role role;
    @DBTable(columnName = "civilite")
    private String civilite;
    @DBTable(columnName = "prenom")
    private String prenom;
    @DBTable(columnName = "nom")
    private String nom;
    @DBTable(columnName = "email")
    private String email;
    @DBTable(columnName = "motPasse")
    private String motPasse;
    @DBTable(columnName = "dateCreation")
    private Date dateCreation;
    @DBTable(columnName = "dateModification")
    private Date dateModification;


    public Utilisateur() {
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String email, String motPasse, Date dateCreation, Date dateModification, Role role) {
        this.idUtilisateur = idUtilisateur;
        this.civilite = civilite;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.motPasse = motPasse;
        this.dateCreation = dateCreation;
        this.dateModification = dateModification;
        this.role = role;
    }

    public Utilisateur(Integer idUtilisateur, String civilite, String prenom, String nom, String email, String motPasse, Role role) {
        this(idUtilisateur, civilite, prenom, nom, email, motPasse, null, null, role);
    }


    public Utilisateur(String civilite, String prenom, String nom, String email, String motPasse, Role role) {
        this(null, civilite, prenom, nom, email, motPasse, null, null, role);
    }

    public Utilisateur(Integer idUtilisateur) {
        this(idUtilisateur, null, null, null, null, null, null, null, null);
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }


    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String toString = String.format("\n[idUtilisateur=%s, civilite=%s, prenom=%s, nom=%s, email=%s, motPasse=%s, dateCreation=%s, dateModification=%s", idUtilisateur, civilite, prenom, nom, email, motPasse, dateCreation, dateModification);
        if (role != null) {
            toString += String.format(" ,role=%s ", role);
        }
        return toString;
    }
}
