/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import com.cours.ebenus.dao.entities.PlaceParking;
import java.util.List;

/**
 *
 * @author ElHadji
 */
public interface IPlaceParkingDao {

    public List<PlaceParking> findAllPlaceParkings();

    public PlaceParking findPlaceParkingById(int idPlaceParking);

    public List<PlaceParking> findPlaceParkingByNumero(String numeroParking);
    
    public List<PlaceParking> findPlaceParkingByIdVoiture(int IdVoiture);

    public PlaceParking createPlaceParking(PlaceParking PlaceParking);

    public PlaceParking updatePlaceParking(PlaceParking PlaceParking);

    public boolean deletePlaceParking(PlaceParking PlaceParking);

}
