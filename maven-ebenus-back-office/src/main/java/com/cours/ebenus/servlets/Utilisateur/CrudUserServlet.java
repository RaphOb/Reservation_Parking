/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets.Utilisateur;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.PlaceParking;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "CrudUserServlet", urlPatterns = {"/CrudUserServlet"})
public class CrudUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private static IServiceFacade service = null;
	
    /**
     * Méthode d'initialisation de la Servlet
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
    	service = new ServiceFacade();
    }

    /**
     * Méthode appelée lors d'une requête HTTP GET
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @throws ServletException
     * @throws IOException
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	
    	if(session == null)
    	{
    		log.debug("No session found... Redirecting to login page");
    		response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
    	}
    	else
    	{
    		if (request.getSession(false).getAttribute("user") != null)
    		{
    			/* Give to JSP all needed data */
	    		List<Utilisateur> users = service.getUtilisateurDao().findAllUtilisateurs();
	    		List<PlaceParking> parkings = service.getPlaceParkingDao().findAllPlacesParking();
	    		List<Voiture> voitures = service.getVoitureDao().findAllVoitures();
	    		
	        	request.setAttribute("users", users);
	        	request.setAttribute("parkings", parkings);
	        	request.setAttribute("voitures", voitures);
	        	
	        	request.setAttribute("current_user", request.getSession(false).getAttribute("user"));
	    		this.getServletContext().getRequestDispatcher("/pages/globalCrud.jsp").forward(request, response);
    		}
    		else
    		{
    			log.debug("No session found... Redirecting to login page");
    			response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
    		}
    	}
    	
    }

    /**
     * Méthode appelée lors d'une requête HTTP POST
     *
     * @param request L'objet requête contenant les informations de la requête
     * http
     * @param response L'objet réponse contenant les informations de la réponse
     * http
     * @return 
     * @throws ServletException
     * @throws IOException
     */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
