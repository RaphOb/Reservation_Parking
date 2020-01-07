package com.cours.ebenus.dao.entities;

import com.cours.ebenus.dao.annotations.DBTable;

public class Voiture {

	@DBTable(columnName = "idVoiture")
    private Integer id;
	@DBTable(columnName = "marque")
    private String brand;
	@DBTable(columnName = "immatriculation")
    private String immat;
	@DBTable(columnName = "idUtilisateur")
    private Integer idUser;

    public Voiture() {}

    public Voiture(Integer id, String brand, String immat, Integer user) {
        this.id = id;
        this.brand = brand;
        this.immat = immat;
        this.idUser = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImmat() {
        return immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public Integer getUser() {
        return idUser;
    }

    public void setUser(Integer user) {
        this.idUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voiture cars = (Voiture) o;

        return id.equals(cars.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", immat='" + immat + '\'' +
                ", user=" + idUser +
                '}';
    }
}
