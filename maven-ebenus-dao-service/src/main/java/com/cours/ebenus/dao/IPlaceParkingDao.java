/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.util.List;

import com.cours.ebenus.dao.entities.PlaceParking;

/**
 *
 * @author ElHadji
 */
public interface IPlaceParkingDao {

    public List<PlaceParking> findAllPlacesParking();

    public PlaceParking findPlaceParkingById(int idPlaceParking);
    
    public List<PlaceParking> findPlaceParkingByNumero(String numeroParking);
    
    public List<PlaceParking> findPlaceParkingByIdVoiture(int IdVoiture);
    
    public Integer findNextAvailableNumber();

    public PlaceParking createPlaceParking(PlaceParking PlaceParking);

    public PlaceParking updatePlaceParking(PlaceParking PlaceParking);

    public boolean deletePlaceParking(PlaceParking PlaceParking);

}
