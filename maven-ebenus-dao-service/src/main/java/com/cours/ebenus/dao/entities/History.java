package com.cours.ebenus.dao.entities;

import com.cours.ebenus.dao.annotations.DBTable;

import java.util.Date;

public class History extends Entities {
    @DBTable(columnName = "idHistory")
    private Integer idHistory;
    @DBTable(columnName = "bookTime")
    private Date bookTime;
    @DBTable(columnName = "idUtilisateur")
    private Utilisateur utilisateur;
    @DBTable(columnName = "idVoiture")
    private Voiture voiture;
    @DBTable(columnName = "idPlaceParking")
    private PlaceParking placeParking;

    public History(){}
    public History(Integer idHistory, Date bookTime, Utilisateur utilisateur, Voiture voiture, PlaceParking placeParking) {
        this.idHistory = idHistory;
        this.bookTime = bookTime;
        this.utilisateur = utilisateur;
        this.voiture = voiture;
        this.placeParking = placeParking;
    }

    public History(Date bookTime, Utilisateur utilisateur, Voiture voiture, PlaceParking placeParking) {
        this(null,bookTime,
                utilisateur,
                voiture,
                placeParking);
    }

    public History(Integer idHistory) {
        this(idHistory, null, null, null, null);
    }

    public Integer getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public PlaceParking getPlaceParking() {
        return placeParking;
    }

    public void setPlaceParking(PlaceParking placeParking) {
        this.placeParking = placeParking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        return idHistory.equals(history.idHistory);
    }

    @Override
    public int hashCode() {
        return idHistory.hashCode();
    }

    @Override
    public String toString() {
        return "History{" +
                "idHistory=" + idHistory +
                ", bookTime=" + bookTime +
                ", utilisateur=" + utilisateur +
                ", voiture=" + voiture +
                ", placeParking=" + placeParking +
                '}';
    }
}
