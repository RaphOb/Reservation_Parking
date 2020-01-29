package com.cours.ebenus.servlets.parkingServlet;

import com.cours.ebenus.dao.entities.Utilisateur;
import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
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

@WebServlet(name = "HistoryServlet", urlPatterns = "/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(HttpServlet.class);
    private static IServiceFacade service = null;

    @Override
    public void init() {
        service = new ServiceFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.debug("not connected => redirect to login");
            response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
        } else {
            if (request.getSession(false).getAttribute("user") != null) {
                Utilisateur u = (Utilisateur) request.getSession().getAttribute("user");
                request.setAttribute("getHistory", service.getHistoryDao().findHistoryByIdUtilisateur(u.getIdUtilisateur()));
                this.getServletContext().getRequestDispatcher("/pages/crudBookPark/historyLog.jsp").forward(request, response);
            } else {
                log.debug("pas de user => go login");
                response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
            }
        }
    }
}

