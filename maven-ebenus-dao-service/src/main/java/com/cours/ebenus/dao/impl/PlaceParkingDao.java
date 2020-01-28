package com.cours.ebenus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.DataSourceSingleton;
import com.cours.ebenus.dao.IPlaceParkingDao;
import com.cours.ebenus.dao.entities.PlaceParking;

public class PlaceParkingDao extends AbstractDao<PlaceParking> implements IPlaceParkingDao {
	
	private static final Log log = LogFactory.getLog(PlaceParkingDao.class);
	
	public PlaceParkingDao() {
		super(PlaceParking.class);
	}

	@Override
	public List<PlaceParking> findAllPlacesParking() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	@Override
	public PlaceParking findPlaceParkingById(int idPlaceParking) {
		// TODO Auto-generated method stub
		return findById(idPlaceParking);
	}

	@Override
	public List<PlaceParking> findPlaceParkingByNumero(String numeroParking) {
		// TODO Auto-generated method stub
		return super.findByCriteria(numeroParking, "num");
	}

	@Override
	public List<PlaceParking> findPlaceParkingByIdVoiture(int idVoiture) {
		// TODO Auto-generated method stub
		return super.findByCriteria(idVoiture, "idVoiture");
	}
	
	public Integer findNextAvailableNumber() {
		String query = "SELECT num FROM PlaceParking ORDER BY num DESC LIMIT 0, 1";
		Connection connection = null;
        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            PreparedStatement prep = null;
            ResultSet rs = null;
            prep = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            rs = prep.executeQuery();
            if (rs.next())
            	return rs.getInt("num") + 1;
        }
        catch (IllegalArgumentException | SecurityException
                | SQLException e) {
        	 e.printStackTrace();
        }
		return null;
	}

	@Override
	public PlaceParking createPlaceParking(PlaceParking PlaceParking) {
		// TODO Auto-generated method stub
		return super.create(PlaceParking);
	}

	@Override
	public PlaceParking updatePlaceParking(PlaceParking PlaceParking) {
		// TODO Auto-generated method stub
		return super.update(PlaceParking);
	}

	@Override
	public boolean deletePlaceParking(PlaceParking PlaceParking) {
		// TODO Auto-generated method stub
		return super.delete(PlaceParking);
	}

}
