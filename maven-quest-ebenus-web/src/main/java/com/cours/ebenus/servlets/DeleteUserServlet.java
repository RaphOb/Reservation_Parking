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

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private static IServiceFacade service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
        service = new ServiceFacade();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				/* Verify if current user is an administrator */
				Utilisateur user = (Utilisateur) request.getSession(false).getAttribute("user");
				if(user.getRole().getIdentifiant().equals("Administrateur"))
				{
					/* If yes, go in post to delete */
					doPost(request, response);
				}
				else
				{
					log.debug("You have no right to delete a user. Connect as admin");
					response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* Delete selected user */
		Integer userID = Integer.parseInt(request.getParameter("user"));
		Utilisateur user = service.getUtilisateurDao().findUtilisateurById(userID);
		service.getUtilisateurDao().deleteUtilisateur(user);
		log.debug("User deleted");
		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
	}

}
