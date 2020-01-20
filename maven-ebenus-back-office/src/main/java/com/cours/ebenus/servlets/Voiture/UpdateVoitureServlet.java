package com.cours.ebenus.servlets.Voiture;

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
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import com.cours.ebenus.dao.impl.AbstractDao;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;

@WebServlet(name = "/UpdateVoitureServlet", urlPatterns = {"/UpdateVoitureServlet"})
public class UpdateVoitureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private static IServiceFacade service = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVoitureServlet() {
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
    			List<String> list = generateBrandArray();
    			List<Utilisateur> users = service.getUtilisateurDao().findAllUtilisateurs();
    			Integer u = Integer.parseInt(request.getParameter("voiture"));
                Voiture selected = service.getVoitureDao().findVoitureById(u);
    			
    			request.setAttribute("all_brand", list);
    			request.setAttribute("users", users);
    			request.setAttribute("current_voiture", selected);
    			request.setAttribute("current_user", user);
	    		this.getServletContext().getRequestDispatcher("/pages/crudVoiture/updateVoiture.jsp").forward(request, response);
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
		String brand = request.getParameter("brand");
		String immat = request.getParameter("immatriculation");
		Integer owner = Integer.parseInt(request.getParameter("owner"));
		
		System.out.println(immat);
		
		List<Voiture> l = service.getVoitureDao().findVoitureByImmatriculation(immat);
		
		for(Voiture v : l)
		{
			System.out.println(v);
		}
		
		Voiture v = l.get(0);
		
		v.setImmattriculation(immat);
		v.setMarque(brand);
		v.setUtilisateur(owner);
		
		service.getVoitureDao().updateVoiture(v);
		log.debug("Voiture updated");

		/* Build report */
		Utilisateur current_user = (Utilisateur) request.getSession(false).getAttribute("user");
		String query = AbstractDao.lastQuery;
		Report report = new Report(current_user.getIdUtilisateur(), query, "Mise à jour d'une voiture");
		service.getReportDao().createReport(report);
		
		response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
    
    protected static List<String> generateBrandArray()
	{
		List<String> list = new ArrayList<String>();
		list.add("Audi");
		list.add("BMW");
		list.add("Citroên");
		list.add("Dacia");
		list.add("Fiat");
		list.add("Ford");
		list.add("Honda");
		list.add("Hyundai");
		list.add("Mercedes");
		list.add("Mini");
		list.add("Nissan");
		list.add("Opel");
		list.add("Peugeot");
		list.add("Skoda");
		list.add("Smart");
		list.add("Suzuki");
		list.add("Toyota");
		list.add("Volkswagen");
		list.add("Volvo");
		return list;
	}
}

