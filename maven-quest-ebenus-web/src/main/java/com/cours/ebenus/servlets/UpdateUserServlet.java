package com.cours.ebenus.servlets;

import com.cours.ebenus.dao.entities.Role;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(LoginServlet.class);
    private static IServiceFacade service = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        service = new ServiceFacade();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {

            log.debug("No session found... Redirecting to login page");
            response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");

        } else {
            if (request.getSession(false).getAttribute("user") != null) {
                {
                    Utilisateur user = (Utilisateur) request.getSession(false).getAttribute("user");

                    Integer u = Integer.parseInt(request.getParameter("user"));
                    Utilisateur uUpdate = service.getUtilisateurDao().findUtilisateurById(u);
                    if (u == user.getIdUtilisateur() || user.getRole().getIdentifiant().equals("Administrateur")) {
                        List<Role> roles = service.getRoleDao().findAllRoles();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        request.setAttribute("date", df.format(uUpdate.getDateNaissance()));
                        request.setAttribute("roles", roles);
                        request.setAttribute("userU",uUpdate);
                        this.getServletContext().getRequestDispatcher("/pages/crudUser/updateUser.jsp").forward(request, response);
                    } else {
                        log.debug("You cant update another user sorry bro");
                        response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
                    }
                }
            } else {
                log.debug("Ba noon brooo");
                response.sendRedirect(this.getServletContext().getContextPath() + "/LoginServlet");
            }
        }


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String idRole = request.getParameter("select_role");
        String civilite = request.getParameter("sex");
        String prenom = request.getParameter("firstname");
        String nom = request.getParameter("lastname");
        String email = request.getParameter("email");
        String dateNaissance = request.getParameter("dteNaiss");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateNaissance);
        } catch (ParseException e) {
            e.getErrorOffset();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Date d = cal.getTime();

        Integer u = Integer.parseInt(request.getParameter("userU"));
        Utilisateur user = service.getUtilisateurDao().findUtilisateurById(u);
        user.setIdentifiant(email);
        user.setMotPasse(civilite);
        user.setPrenom(prenom);
        user.setNom(nom);
        user.setDateNaissance(d);
        user.setDateModification(new Date(System.currentTimeMillis()));

        Role role = service.getRoleDao().findRoleById(Integer.parseInt(idRole));
        user.setRole(role);

        service.getUtilisateurDao().updateUtilisateur(user);
        log.debug("User updated");
        response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
}

