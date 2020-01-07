/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cours.ebenus.dao.entities.PlaceParking;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.opencsv.CSVWriter;

/**
 *
 * @author elhad
 */
// @WebServlet(name = "CrudUserServlet", urlPatterns = {"/CrudUserServlet"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
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
	    		List<PlaceParking> parkings = service.getPlaceParkingDao().findAllPlaceParkings();
	    		List<Voiture> voitures = service.getVoitureDao().findAllVoitures();
	    		
	        	request.setAttribute("users", users);
	        	request.setAttribute("parkings", parkings);
	        	request.setAttribute("voitures", voitures);
	        	
	        	request.setAttribute("current_user", request.getSession(false).getAttribute("user"));
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
     * @return 
     * @throws ServletException
     * @throws IOException
     */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	log.debug(this.getServletContext().getRealPath("/"));
    	
    	//Import case
    	if (request.getParameter("action").equals("importCSV"))
		{
    		List<Utilisateur> users = service.getUtilisateurDao().findAllUtilisateurs();

    		
    		String[] current_user_tab = usersAsEmailLines(users);
            for (Part part : request.getParts()) {
                InputStream is = part.getInputStream();
                if (is != null) {
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);
                    String line;
                    
                    reader.readLine();  //To ignore first line, columns names
                    
                    /* Get each line of the file */
                    while ((line = reader.readLine()) != null) {
                    	String currentEmailFromLine = line.split(";")[5];
                    	/* If current user already exist */
                    	if(Arrays.stream(current_user_tab).parallel().anyMatch(currentEmailFromLine::contains))
                    	{
                    		log.debug(currentEmailFromLine + " already exists");
                    	}
                    	else
                    	{
                    		/* New user to add detected, building new Utilisateur */
                    		log.debug("New user to add : " + currentEmailFromLine);
                    		String civilite = line.split(";")[0];
                    		String prenom = line.split(";")[1];
                    		String nom = line.split(";")[2];

                    		String role_identifiant = line.split(";")[4];
                    		List<Role> role = service.getRoleDao().findRoleByIdentifiant(role_identifiant);
                    		String email = line.split(";")[5];
                    		String motPasse = line.split(";")[6];
                    		Utilisateur new_user = new Utilisateur(civilite, prenom, nom, email, motPasse, role.get(0));
                    		service.getUtilisateurDao().createUtilisateur(new_user);	
                    	}
                    }
                }
            }
            
            
		}
    	response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
    
    private String[] usersAsEmailLines(List<Utilisateur> users)
    {
    	String[] current_user_tab = new String[users.size()];
		int cpt = 0;
		/* Build identifers tab to compare*/
		log.debug("Currents users");
		for(Utilisateur user : users)
		{
			current_user_tab[cpt] = user.getemail();
			log.debug(current_user_tab[cpt]);
			cpt++;
		}
		return current_user_tab;
    }
    
    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
