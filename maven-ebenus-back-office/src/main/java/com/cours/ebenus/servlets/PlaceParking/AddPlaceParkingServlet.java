package com.cours.ebenus.servlets.PlaceParking;

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

import com.cours.ebenus.dao.entities.PlaceParking;
import com.cours.ebenus.dao.entities.Role;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddPlaceParkingServlet")
public class AddPlaceParkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private static IServiceFacade service = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPlaceParkingServlet() {
        super();
        // TODO Auto-generated constructor stub
        service = new ServiceFacade();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
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
    			List<Voiture> v = service.getVoitureDao().findAllVoitures();
    			request.setAttribute("voitures", v);
    			request.setAttribute("next_num", service.getPlaceParkingDao().findNextAvailableNumber());
	    		this.getServletContext().getRequestDispatcher("/pages/crudPlaceParking/addPlaceParking.jsp").forward(request, response);
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer idVoiture = null;
		if (request.getParameter("immat") != null)
			idVoiture = Integer.parseInt(request.getParameter("immat"));
		Integer num = Integer.parseInt(request.getParameter("num"));
		PlaceParking place = null;
		
		if (idVoiture != null)
		{
			place = new PlaceParking(num, false);
			place.setIdVoiture(idVoiture);
		}
		else
		{
			place = new PlaceParking(num, true);
		}
		
		service.getPlaceParkingDao().createPlaceParking(place);
		log.debug("Place created");
		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
	}

}
