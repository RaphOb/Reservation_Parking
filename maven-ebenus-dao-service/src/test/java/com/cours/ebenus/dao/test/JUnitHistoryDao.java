package com.cours.ebenus.dao.test;

import com.cours.ebenus.dao.entities.*;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class JUnitHistoryDao {
    private static final Log log = LogFactory.getLog(JUnitRoleDao.class);
    private static IServiceFacade serviceFacade = null;
    private static List<History> historyList = null;
    private static final int NB_HISTORY = 2;

    @BeforeClass
    public static void init() throws Exception {
        serviceFacade = new ServiceFacade();
        historyList = serviceFacade.getHistoryDao().findAllHistory();
    }

    public void veryfyHistoryData(History h) {
        log.debug("Entrée de la methode");
        if (h != null) {
            Assert.assertNotNull(h.getBookTime());
            Assert.assertNotNull(h.getIdHistory());
            Assert.assertNotNull(h.getPlaceParking());
            Assert.assertNotNull(h.getUtilisateur());
            Assert.assertNotNull(h.getVoiture());
        } else {
            Assert.fail("History null");
        }
        log.debug("fin methode");
    }

    @Test
    public void testFindAll() {
        log.debug("Entree de la methode");
        if (historyList != null) {
            log.debug("NB_HISTORY: " + NB_HISTORY + " , placesparking.size(): " + historyList.size());
            Assert.assertEquals(NB_HISTORY, historyList.size());
        } else {
            Assert.fail("Aucun History n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }

    @Test
    public void testCreateUpdateDeleteHistory() {
        log.debug("Entrée de la methode");
        PlaceParking place_parkingCRUD = new PlaceParking(200, true);
        place_parkingCRUD = serviceFacade.getPlaceParkingDao().createPlaceParking(place_parkingCRUD);
        Role roleCRUD = new Role("Superviseur", "Le rôle superviseur");
        roleCRUD = serviceFacade.getRoleDao().createRole(roleCRUD);
        Utilisateur userCRUD = new Utilisateur("Mr", "Admin", "Admin", "new_user@gmail.com", "passw0rd", roleCRUD);
        userCRUD = serviceFacade.getUtilisateurDao().createUtilisateur(userCRUD);
        Voiture voitureCRUD = new Voiture("Citroen", "CX-423-HU", 1);
        voitureCRUD = serviceFacade.getVoitureDao().createVoiture(voitureCRUD);
        History h = null;
        try {
            h = new History(new SimpleDateFormat("yyy-MM-dd").parse("2020-12-01"), userCRUD, voitureCRUD, place_parkingCRUD);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        h = serviceFacade.getHistoryDao().createHistory(h);
        log.debug("check created history");
        veryfyHistoryData(h);

        log.debug("check findby");
        assert h != null;
        h = serviceFacade.getHistoryDao().findHistoryById(h.getIdHistory());
        Assert.assertNotNull(h);

    }

    @Test
    public void testFindByCriteria() {
        log.debug("Entré de la methode find by Criteria");
        History h = serviceFacade.getHistoryDao().findHistoryByIdUtilisateur(4).get(0);
        log.debug("Check si  le find by Id utilisateur a fonctionné");
        veryfyHistoryData(h);
        log.debug("check si le find by Id Voiture fonctionne");
        h = serviceFacade.getHistoryDao().findHistoryByIdVoiture(4).get(0);
        veryfyHistoryData(h);
        log.debug("check si le find by");
        h = serviceFacade.getHistoryDao().findHistoryByIdPlaceParking(2).get(0);
        veryfyHistoryData(h);


    }
}
