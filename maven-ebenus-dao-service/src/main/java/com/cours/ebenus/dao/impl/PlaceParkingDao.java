package com.cours.ebenus.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.IPlaceParkingDao;
import com.cours.ebenus.dao.entities.PlaceParking;

public class PlaceParkingDao extends AbstractDao<PlaceParking> implements IPlaceParkingDao {
	
	private static final Log log = LogFactory.getLog(PlaceParkingDao.class);
	
	public PlaceParkingDao() {
		super(PlaceParking.class);
	}

	@Override
	public List<PlaceParking> findAllPlaceParkings() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public PlaceParking findPlaceParkingById(int idPlaceParking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceParking> findPlaceParkingByNumero(String numeroParking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceParking> findPlaceParkingByIdVoiture(int IdVoiture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceParking createPlaceParking(PlaceParking PlaceParking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaceParking updatePlaceParking(PlaceParking PlaceParking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePlaceParking(PlaceParking PlaceParking) {
		// TODO Auto-generated method stub
		return false;
	}

}
