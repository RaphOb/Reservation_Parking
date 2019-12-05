/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "CrudUserServlet", urlPatterns = {"/CrudUserServlet"})
public class CrudUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private static IServiceFacade service = null;
	
	//Pas trouvé de moyen de donner un path relatif avec ~
	private static String downloadPath = System.getProperty("user.home");
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
	    		List<Utilisateur> users = service.getUtilisateurDao().findAllUtilisateurs();
	        	request.setAttribute("users", users);
	    		this.getServletContext().getRequestDispatcher("/pages/crudUser/allUsers.jsp").forward(request, response);
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
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getParameter("action").equals("exportJSON"))
    	{
    		log.debug("exporting users to JSON");
    		List<Utilisateur> users = service.getUtilisateurDao().findAllUtilisateurs();
    		JSONObject globalJSON = new JSONObject();
    		JSONArray usersArray = new JSONArray();
    		/* Build each user object as Json */
    		for(Utilisateur user : users)
    		{
    			JSONObject userJSON = new JSONObject();
    			userJSON.put("idUtilisateur", user.getIdUtilisateur());
    			userJSON.put("Civilité", user.getCivilite());
    			userJSON.put("Prénom", user.getPrenom() );
    			userJSON.put("Nom", user.getNom());
    			userJSON.put("Identifiant", user.getIdentifiant());
    			userJSON.put("Date de naissance", user.getDateNaissance());
    			userJSON.put("Date de création", user.getDateCreation());
    			userJSON.put("Date de modification", user.getDateModification());
    		/* Put each object in array JSON */
    			usersArray.add(userJSON);
    		}
    		/* Put Array to global JSON */
    		globalJSON.put("Utilisateurs", usersArray);
    		
    		/* Create file to home directory */
    		File file = new File(downloadPath, "export_user.json");
    		file.createNewFile();
    		try (FileWriter writer = new FileWriter(file)) {
    			writer.write(globalJSON.toJSONString());
    			System.out.println("\nJSON Object: " + globalJSON);
    		}
    	}
    	if(request.getParameter("action").equals("exportXML"))
    	{
    		log.debug("exporting users to XML");
    	}
    	if(request.getParameter("action").equals("exportCSV"))
    	{
    		log.debug("exporting users to CSV");
    	}
    	
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
