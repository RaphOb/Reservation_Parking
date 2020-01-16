package com.cours.ebenus.servlets.PlaceParking;

import java.io.IOException;
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
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;

@WebServlet("/UpdatePlaceParkingServlet")
public class UpdatePlaceParkingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private static IServiceFacade service = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePlaceParkingServlet() {
        super();
        service = new ServiceFacade();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
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
    			Integer numPlace = Integer.parseInt(request.getParameter("place"));
    			request.setAttribute("current_place", numPlace);
    			if (request.getParameter("voiture") != null && (!request.getParameter("voiture").equals("")))
    			{
    				System.out.println(request.getParameter("voiture"));
    				Integer numVoiture = Integer.parseInt(request.getParameter("voiture"));
    				Voiture selected = service.getVoitureDao().findVoitureById(numVoiture);
    				request.setAttribute("current_voiture", selected);
    			}
    			List<Voiture> l = service.getVoitureDao().findAllVoitures();
    			request.setAttribute("voitures", l);
	    		this.getServletContext().getRequestDispatcher("/pages/crudPlaceParking/updatePlaceParking.jsp").forward(request, response);
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
    	Integer numPlace = Integer.parseInt(request.getParameter("num"));
    	PlaceParking p = service.getPlaceParkingDao().findPlaceParkingById(numPlace);
    	if (request.getParameter("voiture") != null) 
    	{
    		if (!request.getParameter("voiture").equals("EMPTY")) 
    		{
    			Integer carId = Integer.parseInt(request.getParameter("voiture"));
        		p.setIdVoiture(carId);
        		p.setAvailable(false);
    		}
    		else
    		{
    			p.setIdVoiture(null);
        		p.setAvailable(true);
    		}
    	}
    	service.getPlaceParkingDao().updatePlaceParking(p);
		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
}

