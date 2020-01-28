package com.cours.ebenus.servlets.parkingServlet;

import com.cours.ebenus.dao.entities.History;
import com.cours.ebenus.dao.entities.PlaceParking;
import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.dao.entities.Voiture;
import com.cours.ebenus.service.GoogleCalendar;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class CrudParkingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(CrudParkingServlet.class);
    private static IServiceFacade service = null;
    private static GoogleCalendar g = null;

    /**
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        service = new ServiceFacade();
        GoogleCalendar.getInstance();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            log.debug("not connected => redirect to login");
            response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
        } else {
            if (request.getSession(false).getAttribute("user") != null) {
                try {
                    GoogleCalendar.checkCal(1000);
                } catch (IOException | GeneralSecurityException e) {
                    e.printStackTrace();
                }
                Utilisateur u = (Utilisateur) request.getSession().getAttribute("user");
                request.setAttribute("current_user", request.getSession(false).getAttribute("user"));
                request.setAttribute("getDates", GoogleCalendar.getDays());
                request.setAttribute("getListEvent", GoogleCalendar.listEvent);
                request.setAttribute("getParkings", service.getPlaceParkingDao().findAllPlacesParking());
                this.getServletContext().getRequestDispatcher("/pages/crudBookPark/bookRoom.jsp").forward(request, response);
            } else {
                log.debug("pas de user => go login");
                response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
            }
        }
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int placePark = Integer.parseInt(req.getParameter("num"));
        String date = req.getParameter("date");
        String emailUser = req.getParameter("userEmail");
        GoogleCalendar.addEvent(placePark, emailUser, date);
        List<PlaceParking> placeParking = service.getPlaceParkingDao().findPlaceParkingByNumero(String.valueOf(placePark));
        List<Utilisateur> u = service.getUtilisateurDao().findUtilisateurByEmail(emailUser);
        List<Voiture> v = service.getVoitureDao().findVoitureByIdUtilisateur(u.get(0).getIdUtilisateur());
        History h = null;
        try {
            h = new History(new SimpleDateFormat("yyyy-MM-dd").parse(date),u.get(0),v.get(0),placeParking.get(0));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        service.getHistoryDao().createHistory(h);
        System.out.println("history : " + service.getHistoryDao().findAllHistory());
        resp.sendRedirect(this.getServletContext().getContextPath() + "/CrudParkingServlet");
    }
}
