/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
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
	
	private static String uploadDirectory = "UploadedFiles";
	
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
    @SuppressWarnings("unchecked")
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
	    			//JSON CASE
	    		log.debug("Creating JSON users file ");
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
	    			System.out.println("\nJSON Object: " + globalJSON);
	    		}
	    		
	    		log.debug("Creating CSV users file ");
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
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Utilisateur> users = service.getUtilisateurDao().findAllUtilisateurs();
    	
    	log.debug(this.getServletContext().getContextPath());
    	log.debug(this.getServletContext().getRealPath("/"));
    	
    	if (request.getParameter("action").equals("importCSV"))
		{
    		// gets absolute path of the web application
    		String appPath = request.getServletContext().getRealPath("");
            // constructs path of the directory to save uploaded file
            String savePath = appPath + uploadDirectory;
             
            log.debug(savePath);
            
            // creates the save directory if it does not exists
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
            	log.debug(fileSaveDir.mkdir());
            }
             
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                // refines the fileName in case it is an absolute path
                fileName = new File(fileName).getName();
                if (fileName.contentEquals(""))
                {
                	response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
                	return;
                }
                log.debug(fileName);
                part.write(savePath + File.separator + fileName);
            }
		}
    	response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
    
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
