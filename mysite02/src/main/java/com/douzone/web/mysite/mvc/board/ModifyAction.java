package com.douzone.web.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bno = request.getParameter("bNo");
		String pno = request.getParameter("pNo");
		
		int bNo = Integer.parseInt(bno);
		int pNo = Integer.parseInt(pno); 
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo(); 
		vo = dao.findByNo(bNo);
		request.setAttribute("vo", vo);
		request.setAttribute("pNo", pNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp");
		MvcUtil.forward("board/modify", request, response);

	}

}
