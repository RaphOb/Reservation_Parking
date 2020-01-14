package com.cours.ebenus.dao.entities;

import com.cours.ebenus.dao.annotations.DBTable;

public class Voiture extends Entities {

	@DBTable(columnName = "idVoiture")
    private Integer idVoiture;
	@DBTable(columnName = "marque")
    private String marque;
	@DBTable(columnName = "immatriculation")
    private String immatriculation;
	@DBTable(columnName = "idUtilisateur")
    private Integer idUtilisateur;

    public Voiture() {}

    public Voiture(Integer idVoiture, String marque, String immatriculation, Integer utilisateur) {
        this.idVoiture = idVoiture;
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.idUtilisateur = utilisateur;
    }
    
    public Voiture(String marque, String immatriculation, Integer utilisateur) {
    	this.idVoiture = null;
        this.marque = marque;
        this.immatriculation = immatriculation;
        this.idUtilisateur = utilisateur;
    }

    public Integer getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(Integer idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getImmattriculation() {
        return immatriculation;
    }

    public void setImmattriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Integer getUtilisateur() {
        return idUtilisateur;
    }

    public void setUtilisateur(Integer user) {
        this.idUtilisateur = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voiture cars = (Voiture) o;

        return idVoiture.equals(idVoiture);
    }

    @Override
    public int hashCode() {
        return idVoiture.hashCode();
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "idVoiture=" + idVoiture +
                ", marque='" + marque + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", idUtilisateur=" + idUtilisateur +
                '}';
    }
}
