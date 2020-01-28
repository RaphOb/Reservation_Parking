package com.cours.ebenus.dao.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.dao.entities.PlaceParking;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

public class JUnitPlaceParkingDao {
	
	private static final Log log = LogFactory.getLog(JUnitRoleDao.class);

    private static final int NB_PLACES_PARKING_LIST = 10;
    private static IServiceFacade serviceFacade = null;
   

    private static final int NB_PLACES_PARKING_FIND_BY_VOITURE = 1;
    private static final int PLACES_PARKING_FIND_BY_VOITURE = 1;

    private static final int NB_PLACES_PARKING_FIND_BY_NUM = 1;
    private static final String PLACES_PARKING_FIND_BY_NUM = "1";
	
    private static List<PlaceParking> placesparking = null;
    
    @BeforeClass
    public static void init() throws Exception {
        // Configuration de l'application
    	serviceFacade = new ServiceFacade();
    	placesparking = serviceFacade.getPlaceParkingDao().findAllPlacesParking();
    }
    
    //Had to add static
    public static void verifyPlaceParkingData(PlaceParking place_parking) {
        log.debug("Entree de la methode");
        if (place_parking != null) {
            log.debug("idPlaceParking : " + place_parking.getIdPlaceParking());
            Assert.assertNotNull(place_parking.getIdPlaceParking());
            Assert.assertNotNull(place_parking.getNum());
            Assert.assertNotNull(place_parking.getAvailable());
        } else if (place_parking == null) {
            Assert.fail("PlaceParking null");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindAllPlaceParkings() {
        log.debug("Entree de la methode");
        if (placesparking != null) {
            log.debug("NB_PLACES_PARKING_LIST: " + NB_PLACES_PARKING_LIST + " , placesparking.size(): " + placesparking.size());
            Assert.assertEquals(NB_PLACES_PARKING_LIST, placesparking.size());
        } else {
            Assert.fail("Aucun PlaceParking n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testCreateUpdateDeletePlaceParking() {
        log.debug("Entree de la methode");
        PlaceParking place_parkingCRUD = new PlaceParking(200, true);
        place_parkingCRUD = serviceFacade.getPlaceParkingDao().createPlaceParking(place_parkingCRUD);
        verifyPlaceParkingData(place_parkingCRUD);
        log.debug("Created place_parkingCRUD : " + place_parkingCRUD);
        log.debug("Created place_parkingCRUD.getIdPlaceParking : " + place_parkingCRUD.getIdPlaceParking());
        place_parkingCRUD = serviceFacade.getPlaceParkingDao().findPlaceParkingById(place_parkingCRUD.getIdPlaceParking());
        Assert.assertNotNull(place_parkingCRUD);
        place_parkingCRUD.setNum(100);
        place_parkingCRUD.setAvailable(false);
        place_parkingCRUD = serviceFacade.getPlaceParkingDao().updatePlaceParking(place_parkingCRUD);
        Assert.assertNotNull(place_parkingCRUD);
        place_parkingCRUD = serviceFacade.getPlaceParkingDao().findPlaceParkingById(place_parkingCRUD.getIdPlaceParking());
        log.debug("Updated place_parkingCRUD : " + place_parkingCRUD);
        Assert.assertEquals(100, (int) place_parkingCRUD.getNum());
        Assert.assertEquals(false, place_parkingCRUD.getAvailable());
        Assert.assertTrue(serviceFacade.getPlaceParkingDao().deletePlaceParking(place_parkingCRUD));
        place_parkingCRUD = serviceFacade.getPlaceParkingDao().findPlaceParkingById(place_parkingCRUD.getIdPlaceParking());
        Assert.assertNull(place_parkingCRUD);
        List<PlaceParking> placesparkingFinal = serviceFacade.getPlaceParkingDao().findAllPlacesParking();
        if (placesparkingFinal != null) {
            Assert.assertEquals(NB_PLACES_PARKING_LIST, placesparkingFinal.size());
            log.debug("placesparkingFinal.size() : " + placesparkingFinal.size() + " , NB_PLACES_PARKING_LIST: " + NB_PLACES_PARKING_LIST);
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindByCriteria() {
    	List<PlaceParking> placesparkingByVoiture = serviceFacade.getPlaceParkingDao().findPlaceParkingByIdVoiture(PLACES_PARKING_FIND_BY_VOITURE);
        List<PlaceParking> placesparkingByNum = serviceFacade.getPlaceParkingDao().findPlaceParkingByNumero(PLACES_PARKING_FIND_BY_NUM);
    	if (placesparkingByVoiture != null) {
            log.debug("NB_PLACES_PARKING_FIND_BY_VOITURE: " + NB_PLACES_PARKING_FIND_BY_VOITURE + " , placesparkingByVoiture.size(): " + placesparkingByVoiture.size());
            Assert.assertEquals(NB_PLACES_PARKING_FIND_BY_VOITURE, placesparkingByVoiture.size());
        } else {
            Assert.fail("Aucune place_parking avec le numéro de voiture " + PLACES_PARKING_FIND_BY_VOITURE + "' n'a ete trouve dans votre base de données");
        }
        if (placesparkingByNum != null) {
            log.debug("NB_PLACES_PARKING_FIND_BY_NUM: " + NB_PLACES_PARKING_FIND_BY_NUM + " , placesparkingByNum.size(): " + placesparkingByNum.size());
            Assert.assertEquals(NB_PLACES_PARKING_FIND_BY_NUM, placesparkingByNum.size());
        } else {
            Assert.fail("Aucune place_parking avec la marque " + PLACES_PARKING_FIND_BY_NUM + "' n'a ete trouve dans votre base de données");
        }
    }
    
    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        serviceFacade = null;
        placesparking = null;
        log.debug("Sortie de la methode");
    }
}
