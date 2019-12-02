/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cours.ebenus.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.dao.entities.Utilisateur;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.logging.*;


/**
 * @author elhad
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

	private static final Log log = LogFactory.getLog(LoginServlet.class);
    private static IServiceFacade service = null;

    @Override
    public void init() throws ServletException {
        service = new ServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	if(request.getSession() == null)
    	{
    		this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    	}
    	else
    	{
    		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    	}
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        
        /* Verifier si l'utilisateur est déja connecté à partir de son mail*/
        if(session.getAttribute(email) != null)
        {
        	log.debug("User already authenticate");
        	response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
        }
        else
        {
        	/* Sinon, lancer la méthode d'authentification */
            Utilisateur user = service.getUtilisateurDao().authenticate(email, password);
            if (user != null) {
            	log.debug("Authentification succeed");
            	session.setAttribute(email, user);
                response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
            } else {
            	/* Failed to authenticate */
                response.sendRedirect("LoginServlet");
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
