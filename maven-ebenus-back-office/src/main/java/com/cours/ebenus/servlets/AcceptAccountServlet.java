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
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.Report;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;


/**
 * @author elhad
 */
@WebServlet("/AcceptAccountServlet")
public class AcceptAccountServlet extends HttpServlet {

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
    	// TODO Auto-generated method stub
    			HttpSession session = request.getSession(false);
    			
    			if(session == null)
    			{
    				log.debug("No session found... Redirecting to login page");
    				this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    			}
    			else
    			{
    				if (request.getSession(false).getAttribute("user") != null)
    				{
						doPost(request, response);
    				}
    				else
    				{
    					log.debug("No session found... Redirecting to login page");
    					response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
    				}
    			}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	Integer userID = Integer.parseInt(request.getParameter("user"));
    	Utilisateur user = service.getUtilisateurDao().findUtilisateurById(userID);
    	Role r = user.getRole();
    	r.setIdRole(2);
    	user.setRole(r);
    	service.getUtilisateurDao().updateUtilisateur(user);
    	log.debug("Account authentified");
    	
    	/* Send Email to user to inform */
    	response.sendRedirect(this.getServletContext().getContextPath() + "/MailerServlet");
    }
    
    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
