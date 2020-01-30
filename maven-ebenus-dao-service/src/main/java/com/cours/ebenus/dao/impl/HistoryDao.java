package com.cours.ebenus.dao.impl;

import com.cours.ebenus.dao.IHistoryDao;
import com.cours.ebenus.dao.entities.History;

import java.util.List;

public class HistoryDao extends AbstractDao<History> implements IHistoryDao {
    public HistoryDao() {
        super(History.class);
    }

    @Override
    public List<History> findAllHistory() {
        return super.findAll();
    }

    @Override
    public History findHistoryById(int idHistory) {
        return super.findById(idHistory);
    }

    @Override
    public List<History> findHistoryByIdUtilisateur(Integer idUtilisateur) {
        return super.findByCriteria(idUtilisateur, "Utilisateur.idUtilisateur");
    }

    @Override
    public List<History> findHistoryByIdVoiture(int idVoiture) {
        return super.findByCriteria(idVoiture, "Voiture.idVoiture");
    }

    @Override
    public List<History> findHistoryByIdPlaceParking(int idPlaceParking) {
        return super.findByCriteria(idPlaceParking, "PlaceParking.idPlaceParking");
    }

    @Override
    public History createHistory(History history) {
        return super.create(history);
    }

    @Override
    public History updateHistory(History history) {
        return super.update(history);
    }

    @Override
    public boolean deleteHistory(History history) {
        return super.delete(history);
    }
}
