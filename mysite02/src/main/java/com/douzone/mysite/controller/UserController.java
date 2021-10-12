package com.douzone.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.util.MvcUtil;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String actionName = request.getParameter("a");
//		if("joinform".equals(action)) {
//			MvcUtil.forward("/WEB-INF/views/user/joinform.jsp", request, response);
//		}
//		else if("join".equals(action)) {
//			
//		} 
//		else {
//			MvcUtil.redirect("/mysite02", request, response);
//		}
		
		ActionFactory af = new UserActionFactory();
		Action action = af.getAction(actionName);
		action.execute();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}