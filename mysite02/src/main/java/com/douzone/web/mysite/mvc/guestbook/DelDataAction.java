package com.douzone.web.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.guestbookDao;
import com.douzone.web.mvc.Action;

public class DelDataAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		new guestbookDao().delete(no, password);
		
		response.sendRedirect("/mysite02/guestbook?a=list");

	}

}
