package com.cours.ebenus.entities;

public class Voiture {

    private Integer idCars;
    private String brand;
    private String immat;
    private Utilisateur user;

    public Voiture() {}

    public Voiture(Integer idCars, String brand, String immat, Utilisateur user) {
        this.idCars = idCars;
        this.brand = brand;
        this.immat = immat;
        this.user = user;
    }

    public Integer getIdVoiture() {
        return idCars;
    }

    public void setIdVoiture(Integer idCars) {
        this.idCars = idCars;
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

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Voiture cars = (Voiture) o;

        return idCars.equals(cars.idCars);
    }

    @Override
    public int hashCode() {
        return idCars.hashCode();
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "idVoiture=" + idCars +
                ", brand='" + brand + '\'' +
                ", immat='" + immat + '\'' +
                ", user=" + user +
                '}';
    }
}
