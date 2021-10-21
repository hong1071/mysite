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
		String bno = request.getParameter("bNo");
		String pno = request.getParameter("pNo");
		
		int boardNum = Integer.parseInt(bno);
		int pageNum = Integer.parseInt(pno); 
		
		new BoardDao().delete(boardNum);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&pNo=" + pageNum, request, response);

	}

}
