package com.cours.ebenus.dao.entities;

import com.cours.ebenus.dao.annotations.DBTable;

public class PlaceParking {

	@DBTable(columnName = "idPlace")
    private Integer id;
	@DBTable(columnName = "idVoiture")
    private Integer idVoiture;
	@DBTable(columnName = "num")
    private Integer num;
	@DBTable(columnName = "available")
    private Boolean available;

    public PlaceParking(){}

    public PlaceParking(Integer id, Integer num, Boolean available) {
        this.id = id;
        this.num = num;
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "PlaceParking{" +
                "id=" + id +
                ", idVoiture=" + idVoiture +
                ", Numéro=" + num +
                ", available=" + available +
                '}';
    }

}
