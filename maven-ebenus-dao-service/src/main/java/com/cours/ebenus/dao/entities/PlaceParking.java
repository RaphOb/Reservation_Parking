package com.cours.ebenus.dao.entities;

import com.cours.ebenus.dao.annotations.DBTable;

public class PlaceParking extends Entities {

	@DBTable(columnName = "idPlaceParking")
    private Integer idPlaceParking;
	@DBTable(columnName = "idVoiture")
    private Integer idVoiture;
	@DBTable(columnName = "num")
    private Integer num;
	@DBTable(columnName = "available")
    private Boolean available;

    public PlaceParking(){}

    public PlaceParking(Integer id, Integer num, Boolean available) {
        this.idPlaceParking = id;
        this.num = num;
        this.available = available;
    }
    
    public PlaceParking(Integer num, Boolean available) {
    	this.idPlaceParking = null;
        this.num = num;
        this.available = available;
    }

    public Integer getIdPlaceParking() {
        return idPlaceParking;
    }

    public void setIdPlace(Integer id) {
        this.idPlaceParking = id;
    }
    
    public Integer getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Integer idVoiture) {
		this.idVoiture = idVoiture;
	}

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

        PlaceParking that = (PlaceParking) o;

        return idPlaceParking.equals(that.idPlaceParking);
    }

    @Override
    public int hashCode() {
        return idPlaceParking.hashCode();
    }

    @Override
    public String toString() {
        return "PlaceParking{" +
                "idPlace=" + idPlaceParking +
                ", idVoiture=" + idVoiture +
                ", Numéro=" + num +
                ", available=" + available +
                '}';
    }

}
