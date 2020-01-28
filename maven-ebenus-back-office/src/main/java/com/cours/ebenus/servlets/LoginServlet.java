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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;


/**
 * @author elhad
 */
// @WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
    			response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    		}
    		else
    		{
    			this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    		}
    	}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String signIn = request.getParameter("signin");
        
        HttpSession session = request.getSession(false);
        
    	/* Lancement de la méthode d'authentification */
        Utilisateur user = service.getUtilisateurDao().authenticate(email, password);
        if (user != null) {
        	/* If Admin */
        	if(user.toString().contains("idRole=1"))
        	{
        		session.setAttribute("user", user);
	            response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
        	}
        	else
        	{
        		response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
        	}
        } else {
        	/* Failed to authenticate */
        	this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
        }
    }

    /**
     * Méthode appelée lors de la fin de la Servlet
     */
    @Override
    public void destroy() {
    }

}
