package com.cours.ebenus.maven.ebenus.dao.service.dao.entities;

public class LotParcking {

    private Integer idLot;
    private Integer number_lot;
    private Boolean available;

    public LotParcking(){}

    public LotParcking(Integer idLot, Integer number_lot, Boolean available) {
        this.idLot = idLot;
        this.number_lot = number_lot;
        this.available = available;
    }

    public Integer getIdLot() {
        return idLot;
    }

    public void setIdLot(Integer idLot) {
        this.idLot = idLot;
    }

    public Integer getNumber_lot() {
        return number_lot;
    }

    public void setNumber_lot(Integer number_lot) {
        this.number_lot = number_lot;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LotParcking that = (LotParcking) o;

        return idLot.equals(that.idLot);
    }

    @Override
    public int hashCode() {
        return idLot.hashCode();
    }

    @Override
    public String toString() {
        return "LotParcking{" +
                "idLot=" + idLot +
                ", number_lot=" + number_lot +
                ", available=" + available +
                '}';
    }
}
