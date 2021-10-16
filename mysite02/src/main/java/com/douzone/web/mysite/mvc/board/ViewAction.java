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

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("no");
		int boardNo = Integer.parseInt(no);
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo = dao.findByNo(boardNo);
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		MvcUtil.forward("board/view", request, response);

	}
}
