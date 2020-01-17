package com.cours.ebenus.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.IReportDao;
import com.cours.ebenus.dao.entities.Report;
import com.cours.ebenus.dao.entities.Role;

public class ReportDao extends AbstractDao<Report> implements IReportDao{
	
	
	 private static final Log log = LogFactory.getLog(RoleDao.class);

	 public ReportDao() {
		 super(Report.class);
	 }

 	public List<Report> findAllReport()
 	{
		return super.findAll();
 		
 	}

    public Report findReportById(int idReport)
    {
		return super.findById(idReport);
    	
    }

    public Report createReport(Report report)
    {
		return super.create(report);
    	
    }

    public boolean deleteReport(Report report)
    {
		return super.delete(report);
    	
    }
}
