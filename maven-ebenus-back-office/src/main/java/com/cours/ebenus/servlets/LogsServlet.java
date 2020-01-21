/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.Report;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;


/**
 * @author elhad
 */
@WebServlet("/LogsServlet")
public class LogsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
    private static IServiceFacade service = null;

    @Override
    public void init() throws ServletException {
        service = new ServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	/* Verifiy session is not null to avoid NullPointer at getAttribute */
    	if(request.getSession(false) == null)
    	{
    		
    			this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    	}
    	else
    	{
    		/* Verify connexion on refresh */
    		if (request.getSession(false).getAttribute("user") != null)
    		{
    			List<Report> reports = service.getReportDao().findAllReport();
    			
    			request.setAttribute("reports", reports);
    			request.setAttribute("current_user", request.getSession(false).getAttribute("user"));
    			this.getServletContext().getRequestDispatcher("/pages/logs.jsp").forward(request, response);
    		}
    		else
    		{
    			this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    		}
    	}
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
