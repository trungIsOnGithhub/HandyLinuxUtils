package com.trung.qotd;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trung.qotd.services.AuthenService;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/welcome")
public class WelcomeServlet extends HttpServlet {
//	private AuthenService authentication = new AuthenService();

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
												throws ServletException, IOException {
//		System.out.println(request.getAttribute("name"));
		String requestUsername = request.getParameter("usr_name");
		
		String resourcesView = "/WEB-INF/views/welcome.jsp";

		if ( !AuthenService.validUsername(requestUsername) ) {
			resourcesView = "/WEB-INF/views/error.jsp";
		}
		
		System.out.println(requestUsername);
		System.out.println(resourcesView);
		
		request.setAttribute("username", requestUsername);
		
		request.getRequestDispatcher(resourcesView).forward(request, response);
	}
}