package com.douzone.web.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.guestbookDao;
import com.douzone.mysite.vo.guestbookVo;
import com.douzone.web.mvc.Action;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String message = request.getParameter("content");
		
		guestbookVo vo = new guestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		new guestbookDao().insert(vo);
		
		response.sendRedirect("/mysite02/guestbook?a=list");
	}

}
