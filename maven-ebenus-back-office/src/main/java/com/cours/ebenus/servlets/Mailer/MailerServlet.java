package com.cours.ebenus.servlets.Mailer;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cours.ebenus.service.IServiceFacade;
import com.cours.ebenus.service.ServiceFacade;
import com.cours.ebenus.servlets.LoginServlet;

@WebServlet("/MailerServlet")
public class MailerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginServlet.class);
	private static IServiceFacade service = null;

	@Override
	public void init() throws ServletException {
		service = new ServiceFacade();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		
		/* Verifiy session is not null to avoid NullPointer at getAttribute */
		if(request.getSession(false)==null)
		{
	
			this.getServletContext().getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
		}
		else
		{
			/* Verify connexion on refresh */
			if (request.getSession(false).getAttribute("user") != null) {
	
				// Recipient's email ID needs to be mentioned.
				String to = request.getParameter("mail");
	
				// Sender's email ID needs to be mentioned
				String from = "test@ebenus.com";
	
				// Assuming you are sending email from localhost
				String host = "localhost";
	
				// Get system properties
				Properties properties = System.getProperties();
	
				// Setup mail server
				properties.setProperty("mail.smtp.host", host);
	
				// Get the default Session object.
				javax.mail.Session session = Session.getInstance(properties);
	
				try {
					// Create a default MimeMessage object.
					MimeMessage message = new MimeMessage(session);
	
					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));
	
					// Set To: header field of the header.
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
					// Set Subject: header field
					message.setSubject("Your account has been verified");
	
					// Now set the actual message
					message.setText("Hi, your account on Parclick has been validated ! Use your identifiers (mail, password), to log in and start the real experience.");
	
					// Send message
					Transport.send(message);
					System.out.println("Email envoyé");
				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
				response.sendRedirect(this.getServletContext().getContextPath() + "/CrudUserServlet");
			}
		}
	}
}