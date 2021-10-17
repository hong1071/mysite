package com.douzone.web.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		int boardNum = Integer.parseInt(no);
		
		new BoardDao().delete(boardNum);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&pNum=1", request, response);

	}

}
