package com.cours.ebenus.entities;

public class PlaceParking {

    private Integer idLot;
    private Integer idVoiture;
    private Integer number_lot;
    private Boolean available;

    public PlaceParking(){}

    public PlaceParking(Integer idLot, Integer number_lot, Boolean available) {
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
    
    public Integer getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Integer idVoiture) {
		this.idVoiture = idVoiture;
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

        PlaceParking that = (PlaceParking) o;

        return idLot.equals(that.idLot);
    }

    @Override
    public int hashCode() {
        return idLot.hashCode();
    }

    @Override
    public String toString() {
        return "PlaceParking{" +
                "idLot=" + idLot +
                ", idVoiture=" + idVoiture +
                ", number_lot=" + number_lot +
                ", available=" + available +
                '}';
    }

}
