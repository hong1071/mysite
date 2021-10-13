package com.douzone.web.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

import com.douzone.mysite.dao.guestbookDao;
import com.douzone.mysite.vo.guestbookVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		guestbookDao dao = new guestbookDao();
		List<guestbookVo> list = dao.findAll();
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
		MvcUtil.forward("guestbook/list", request, response);
	}

}
