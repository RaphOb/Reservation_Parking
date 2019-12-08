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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            Utilisateur user = (Utilisateur) request.getSession(false).getAttribute("user");

           Integer u = Integer.parseInt(request.getParameter("user"));
            if (u == user.getIdUtilisateur()) {
                List<Role> roles = service.getRoleDao().findAllRoles();
                request.setAttribute("roles",roles);
                this.getServletContext().getRequestDispatcher("/pages/crudUser/updateUser.jsp").forward(request, response);

            } else {
                log.debug("You cant update another user sorry bro");
                response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
            }
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        /* Get data from form */
        String idRole = request.getParameter("select_role");
        String email = request.getParameter("email");
        String motPasse = request.getParameter("password");
        String confirmMotPasse = request.getParameter("password_confirm");
        Utilisateur user = (Utilisateur) request.getSession(false).getAttribute("user");
        user.setIdentifiant(email);
        user.setMotPasse(motPasse);
        Role role = service.getRoleDao().findRoleById(Integer.parseInt(idRole));
        user.setRole(role);

        service.getUtilisateurDao().updateUtilisateur(user);
        log.debug("User updated");
        response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
    }
}

