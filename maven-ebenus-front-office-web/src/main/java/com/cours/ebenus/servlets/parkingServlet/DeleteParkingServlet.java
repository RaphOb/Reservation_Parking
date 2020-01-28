package com.cours.ebenus.servlets.parkingServlet;

import com.cours.ebenus.dao.entities.History;
import com.cours.ebenus.service.GoogleCalendar;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;

@WebServlet(name="DeleteParkingServlet",urlPatterns = "/DeleteParkingServlet")
public class DeleteParkingServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(LoginServlet.class);


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                int placePark = Integer.parseInt(request.getParameter("num"));
                String date = request.getParameter("date");
                String emailUser = request.getParameter("userEmail");
                GoogleCalendar.deleteEvent(placePark, emailUser, date);
                try {
                    GoogleCalendar.checkCal(100);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
                log.debug("Event deleted");
                response.sendRedirect(this.getServletContext().getContextPath() + "/CrudParkingServlet");

            }
            else
            {
                log.debug("No session found... Redirecting to login page");
                response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
            }
        }
    }
}
