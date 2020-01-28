package com.cours.ebenus.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class LogoutServlet
 */

@WebServlet("/LogoutServlet")  //Cette annotation autorise de ne pas remplir le web.xml
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		
		if(session != null)
		{
			log.debug("Logout...");
			session.invalidate();
			session = null;
			response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
		}
		else
		{
			log.debug("Shouldn't be able to use disconnect without session... Bug");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
