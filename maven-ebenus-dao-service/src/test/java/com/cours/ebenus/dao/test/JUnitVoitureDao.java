package com.cours.ebenus.dao.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cours.ebenus.dao.entities.Voiture;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

public class JUnitVoitureDao {

	private static final Log log = LogFactory.getLog(JUnitRoleDao.class);

    private static final int NB_VOITURES_LIST = 5;
    private static IServiceFacade serviceFacade = null;
   

    private static final int NB_VOITURES_FIND_BY_IMMATRICULATION = 1;
    private static final String VOITURES_FIND_BY_IMMATRICULATION = "PB-199-PP";

    private static final int NB_VOITURES_FIND_BY_MARQUE = 1;
    private static final String VOITURES_FIND_BY_MARQUE = "Citroen";

    private static final int NB_VOITURES_FIND_BY_UTILISATEUR = 1;
    private static final int VOITURES_FIND_BY_UTILISATEUR = 4;
	
    private static List<Voiture> voitures = null;
    
    @BeforeClass
    public static void init() throws Exception {
        // Configuration de l'application
    	serviceFacade = new ServiceFacade();
    	voitures = serviceFacade.getVoitureDao().findAllVoitures();
    }
    
    //Had to add static
    public static void verifyVoitureData(Voiture voiture) {
        log.debug("Entree de la methode");
        if (voiture != null) {
            log.debug("idVoiture : " + voiture.getIdVoiture());
            Assert.assertNotNull(voiture.getMarque());
            Assert.assertNotNull(voiture.getImmattriculation());
            Assert.assertNotNull(voiture.getUtilisateur());
        } else if (voiture == null) {
            Assert.fail("Voiture null");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindAllVoitures() {
        log.debug("Entree de la methode");
        if (voitures != null) {
            log.debug("NB_Voitures_LIST: " + NB_VOITURES_LIST + " , voitures.size(): " + voitures.size());
            Assert.assertEquals(NB_VOITURES_LIST, voitures.size());
        } else {
            Assert.fail("Aucun Voiture n'a ete trouves dans votre base de données");
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testCreateUpdateDeleteVoiture() {
        log.debug("Entree de la methode");
        Voiture voitureCRUD = new Voiture("Citroen", "CX-423-HU", 1);
        voitureCRUD = serviceFacade.getVoitureDao().createVoiture(voitureCRUD);
        verifyVoitureData(voitureCRUD);
        log.debug("Created voitureCRUD : " + voitureCRUD);
        log.debug("Created voitureCRUD.getIdVoiture : " + voitureCRUD.getIdVoiture());
        voitureCRUD = serviceFacade.getVoitureDao().findVoitureById(voitureCRUD.getIdVoiture());
        Assert.assertNotNull(voitureCRUD);
        voitureCRUD.setImmattriculation("HY-404-JU");
        voitureCRUD.setMarque("Fiat");
        voitureCRUD = serviceFacade.getVoitureDao().updateVoiture(voitureCRUD);
        Assert.assertNotNull(voitureCRUD);
        voitureCRUD = serviceFacade.getVoitureDao().findVoitureById(voitureCRUD.getIdVoiture());
        log.debug("Updated voitureCRUD : " + voitureCRUD);
        Assert.assertEquals("HY-404-JU", voitureCRUD.getImmattriculation());
        Assert.assertEquals("Fiat", voitureCRUD.getMarque());
        Assert.assertTrue(serviceFacade.getVoitureDao().deleteVoiture(voitureCRUD));
        voitureCRUD = serviceFacade.getVoitureDao().findVoitureById(voitureCRUD.getIdVoiture());
        Assert.assertNull(voitureCRUD);
        List<Voiture> voituresFinal = serviceFacade.getVoitureDao().findAllVoitures();
        if (voituresFinal != null) {
            Assert.assertEquals(NB_VOITURES_LIST, voituresFinal.size());
            log.debug("voituresFinal.size() : " + voituresFinal.size() + " , NB_VOITURES_LIST: " + NB_VOITURES_LIST);
        }
        log.debug("Sortie de la methode");
    }
    
    @Test
    public void testFindByCriteria() {
    	List<Voiture> voituresByImmatriculation = serviceFacade.getVoitureDao().findVoitureByImmatriculation(VOITURES_FIND_BY_IMMATRICULATION);
        List<Voiture> voituresByMarque = serviceFacade.getVoitureDao().findVoituresByMarque(VOITURES_FIND_BY_MARQUE);
        List<Voiture> voituresByUtilisateur = serviceFacade.getVoitureDao().findVoitureByIdUtilisateur(VOITURES_FIND_BY_UTILISATEUR);
    	if (voituresByImmatriculation != null) {
            log.debug("NB_VOITURES_FIND_BY_IMMATRICULATION: " + NB_VOITURES_FIND_BY_IMMATRICULATION + " , voituresByImmatriculation.size(): " + voituresByImmatriculation.size());
            Assert.assertEquals(NB_VOITURES_FIND_BY_IMMATRICULATION, voituresByImmatriculation.size());
        } else {
            Assert.fail("Aucune voiture avec l'immatruculation " + VOITURES_FIND_BY_IMMATRICULATION + "' n'a ete trouve dans votre base de données");
        }
        if (voituresByMarque != null) {
            log.debug("NB_VOITURES_FIND_BY_MARQUE: " + NB_VOITURES_FIND_BY_MARQUE + " , voituresByMarque.size(): " + voituresByMarque.size());
            Assert.assertEquals(NB_VOITURES_FIND_BY_MARQUE, voituresByMarque.size());
        } else {
            Assert.fail("Aucune voiture avec la marque " + VOITURES_FIND_BY_MARQUE + "' n'a ete trouve dans votre base de données");
        }
        if (voituresByUtilisateur != null) {
            log.debug("NB_VOITURES_FIND_BY_UTILISATEUR: " + NB_VOITURES_FIND_BY_UTILISATEUR + " , voituresByUtilisateur.size(): " + voituresByUtilisateur.size());
            Assert.assertEquals(NB_VOITURES_FIND_BY_UTILISATEUR, voituresByUtilisateur.size());
        } else {
            Assert.fail("Aucune voiture avec l'utilisateur " + VOITURES_FIND_BY_UTILISATEUR + "' n'a ete trouve dans votre base de données");
        }
    }
    
    @AfterClass
    public static void terminate() throws Exception {
        log.debug("Entree de la methode");
        serviceFacade = null;
        voitures = null;
        log.debug("Sortie de la methode");
    }
}
