package com.sap.sample.opensap.week2.unit3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sap.security.um.service.UserManagementAccessor;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;

/**
 * Servlet implementation class HelloEmployee
 */
public class HelloEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloEmployee() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.getWriter().append("<html><body><h3>");
		
		// Check for a logged in user
		if (request.getUserPrincipal() != null) {
			response.getWriter().append("User ID: ").append(request.getUserPrincipal().getName() + "<br>");
			
            try {
			    // UserProvider provides access to the user storage
			    UserProvider users = UserManagementAccessor.getUserProvider();
	
			    // Read the currently logged in user from the user storage
			    User user = users.getUser(request.getUserPrincipal().getName());
	
			    // Print the user name and email
			    response.getWriter().append("User name: " + user.getAttribute("firstname") + " " + user.getAttribute("lastname") + "<br>");
			    response.getWriter().append("Email: " + user.getAttribute("email") + "<br>");
			} catch (Exception e) {
				response.getWriter().append("Error: " + e.getMessage());
			}			
		}				
		response.getWriter().append("</h3></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
