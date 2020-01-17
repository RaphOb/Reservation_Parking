package com.cours.ebenus.dao.entities;

import com.cours.ebenus.dao.annotations.DBTable;

public class Report extends Entities {

    @DBTable(columnName = "idReport")
    private Integer idReport;
    @DBTable(columnName = "idUtilisateur")
    private Integer idUtilisateur;
    @DBTable(columnName = "sqlQuery")
    private String sqlQuery;
    @DBTable(columnName = "description")
    private String description;
    
    public Report(){}
    
    public Report(Integer idUtilisateur, String sqlQuery, String description)
    {
    	this.idUtilisateur = idUtilisateur;
    	this.sqlQuery = sqlQuery;
    	this.description = description;
    }

	public Integer getIdReport() {
		return idReport;
	}

	public void setIdReport(Integer idReport) {
		this.idReport = idReport;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idReport != null ? idReport.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
    	return String.format("\n[idReport=%s, idUtilisateur=%s, sqlQuery=%s, description=%s", idUtilisateur, idReport, sqlQuery, description);
    }
}
