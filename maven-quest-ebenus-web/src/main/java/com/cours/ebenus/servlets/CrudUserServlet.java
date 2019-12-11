/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
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

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
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
    			/* Give to JSP list of users */
	    		List<Utilisateur> users = service.getUtilisateurDao().findAllUtilisateurs();
	        	request.setAttribute("users", users);
	    		this.getServletContext().getRequestDispatcher("/pages/crudUser/allUsers.jsp").forward(request, response);
	    		
	    		/* Rebuild Export files */
	    		reBuildExportJSON(users);
	    		reBuildExportCSV(users);
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
                    		String strDate = line.split(";")[3];
                    		Date dateNaissance = null;
							try {
								dateNaissance = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
                    		String role_identifiant = line.split(";")[4];
                    		List<Role> role = service.getRoleDao().findRoleByIdentifiant(role_identifiant);
                    		String identifiant = line.split(";")[5];
                    		String motPasse = line.split(";")[6];
                    		Utilisateur new_user = new Utilisateur(civilite, prenom, nom, identifiant, motPasse, dateNaissance, role.get(0));
                    		service.getUtilisateurDao().createUtilisateur(new_user);	
                    	}
                    }
                }
            }
            
            
		}
    	response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
    
    @SuppressWarnings("unchecked")
    private void reBuildExportJSON(List<Utilisateur> users) throws IOException
    {
    	//JSON CASE
    	File file = new File(this.getServletContext().getRealPath("/"), "/export_user.json");
		JSONObject globalJSON = new JSONObject();
		JSONArray usersArray = new JSONArray();
		/* Build each user object as Json */
		for(Utilisateur user : users)
		{
			JSONObject userJSON = new JSONObject();
			userJSON.put("Id Utilisateur", user.getIdUtilisateur());
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
		
		file.createNewFile();
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(globalJSON.toJSONString());
			//System.out.println("\nJSON Object: " + globalJSON);
		}
    }
    
    private void reBuildExportCSV(List<Utilisateur> users)
    {
    	//CSV CASE
	    try { 
	    	File file2 = new File(this.getServletContext().getRealPath("/"), "/export_user.csv");
	    	file2.createNewFile();
	        FileWriter outputfile = new FileWriter(file2); 
	  
	        // create CSVWriter object filewriter object as parameter 
	        CSVWriter writer = new CSVWriter(outputfile); 
	  
	        // Adding header to csv 
	        String[] header = { "Id Utilisateur",
		    	        		"Civilité",
		    	        		"Prénom",
		    	        		"Nom",
		    	        		"Identifiant",
		    	        		"Date de naissance",
		    	        		"Date de création",
		    	        		"Date de modification"
		    	        	}; 
	        writer.writeNext(header); 
	  
	        for(Utilisateur user : users)
	        {
	        	// Add data to csv 
    	        String[] user_info = { user.getIdUtilisateur().toString(),
    	        						user.getCivilite(),
    	        						user.getPrenom(),
    	        						user.getNom(),
    	        						user.getIdentifiant(),
    	        						user.getDateNaissance().toString(),
    	        						user.getDateCreation().toString(),
    	        						user.getDateModification().toString()
    	        					}; 
    	        writer.writeNext(user_info); 
	        }
	        // closing writer connection 
	        writer.close(); 
	    } 
	    catch (IOException e) { 
	        // TODO Auto-generated catch block 
	        e.printStackTrace(); 
	    } 
    }
    
    private String[] usersAsEmailLines(List<Utilisateur> users)
    {
    	String[] current_user_tab = new String[users.size()];
		int cpt = 0;
		/* Build identifers tab to compare*/
		log.debug("Currents users");
		for(Utilisateur user : users)
		{
			current_user_tab[cpt] = user.getIdentifiant();
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
