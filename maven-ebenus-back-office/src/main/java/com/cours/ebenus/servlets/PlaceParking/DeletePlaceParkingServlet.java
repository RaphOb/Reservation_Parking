package com.cours.ebenus.servlets.PlaceParking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.dao.entities.PlaceParking;
import com.cours.ebenus.dao.entities.Report;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.impl.AbstractDao;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeletePlaceParkingServlet")
public class DeletePlaceParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private static IServiceFacade service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePlaceParkingServlet() {
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
				doPost(request, response);
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
		/* Delete selected place */
		Integer placeParkingID = Integer.parseInt(request.getParameter("place"));
		PlaceParking place = service.getPlaceParkingDao().findPlaceParkingById(placeParkingID);
		service.getPlaceParkingDao().deletePlaceParking(place);
		log.debug("Place deleted");

		/* Build report */
		Utilisateur current_user = (Utilisateur) request.getSession(false).getAttribute("user");
		String query = AbstractDao.lastQuery;
		Report report = new Report(current_user.getIdUtilisateur(), query, "Suppression d'une place de parking");
		service.getReportDao().createReport(report);
		
		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
	}

}
