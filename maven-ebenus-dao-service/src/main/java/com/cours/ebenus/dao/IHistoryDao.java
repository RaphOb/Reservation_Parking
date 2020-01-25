package com.cours.ebenus.dao;

import com.cours.ebenus.dao.entities.History;

import java.util.List;

public interface IHistoryDao {
    public List<History> findAllHistory();

    public History findHistoryById(int idHistory);

    public List<History> findHistoryByIdUtilisateur(Integer idUtilisateur);

    public List<History> findHistoryByIdVoiture(int idVoiture);

    public List<History> findHistoryByIdPlaceParking(int idPlaceParking);


    public History createHistory(History history);

    public History updateHistory(History history);

    public boolean deleteHistory(History history);
}
