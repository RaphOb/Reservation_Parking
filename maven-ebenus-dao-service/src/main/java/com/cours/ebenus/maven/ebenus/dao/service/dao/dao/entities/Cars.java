package com.cours.ebenus.maven.ebenus.dao.service.dao.dao.entities;

public class Cars {

    private Integer idCars;
    private String brand;
    private String immat;
    private User user;

    public Cars() {}

    public Cars(Integer idCars, String brand, String immat, User user) {
        this.idCars = idCars;
        this.brand = brand;
        this.immat = immat;
        this.user = user;
    }

    public Integer getIdCars() {
        return idCars;
    }

    public void setIdCars(Integer idCars) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cars cars = (Cars) o;

        return idCars.equals(cars.idCars);
    }

    @Override
    public int hashCode() {
        return idCars.hashCode();
    }

    @Override
    public String toString() {
        return "Cars{" +
                "idCars=" + idCars +
                ", brand='" + brand + '\'' +
                ", immat='" + immat + '\'' +
                ", user=" + user +
                '}';
    }
}
