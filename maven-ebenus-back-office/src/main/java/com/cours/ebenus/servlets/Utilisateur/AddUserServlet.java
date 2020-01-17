package com.cours.ebenus.servlets.Utilisateur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private static IServiceFacade service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
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
    	
		/* Admin add user case */
		if(session == null)
    	{
    		log.debug("No session found... Redirecting to login page");
    		this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
    	}
    	else
    	{
    		Object user = request.getSession(false).getAttribute("user");
    		if (user != null)
    		{
	    		/* Set roles to gives info to jsp page */
	    		List<Role> list = service.getRoleDao().findAllRoles();
	    		request.setAttribute("roles", list);
	    		request.setAttribute("current_user", request.getSession(false).getAttribute("user"));
	    		/* Gives page to create user which call post method*/
	    		this.getServletContext().getRequestDispatcher("/pages/crudUser/addUser.jsp").forward(request, response);
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
		
		/* Get data from form */
		String idRole = request.getParameter("select_role");
		if(idRole == null)
			idRole = "3";
		
		String civilite = request.getParameter("sex");
		String prenom = request.getParameter("firstname");
		String nom = request.getParameter("lastname");
		String email = request.getParameter("email");
		String motPasse = request.getParameter("password");

		/* Build Date object */
		/* Get Role from ID */
		Role role = service.getRoleDao().findRoleById(Integer.parseInt(idRole));
		Utilisateur user = new Utilisateur(civilite, prenom, nom, email, motPasse, role);
		user = service.getUtilisateurDao().createUtilisateur(user);
		log.debug("User created");

		
		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
	}

}
