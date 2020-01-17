/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.dao;

import java.util.List;

import com.cours.ebenus.dao.entities.Report;

/**
 *
 * @author ElHadji
 */
public interface IReportDao {

    public List<Report> findAllReport();

    public Report findReportById(int idReport);

    public Report createReport(Report role);

    public boolean deleteReport(Report role);

}
