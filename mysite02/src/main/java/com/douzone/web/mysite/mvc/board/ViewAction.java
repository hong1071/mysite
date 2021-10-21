package com.douzone.web.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("bNo");
		String pageNo = request.getParameter("pNo");
		int boardNo = Integer.parseInt(no);
		
		// 쿠키 처리
		///////////////////////////////////////////////////////
		Cookie[] cookies = request.getCookies();
		int visitor = 0;
		
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName());
			if(cookie.getName().equals("visit")) {
				visitor = 1;
				System.out.println("visit 통과");
				if(cookie.getValue().contains(no)) {
					System.out.println("visitif 통과");
				}
				else {
					cookie.setValue(cookie.getValue() + "-" + no);
					response.addCookie(cookie);
					new BoardDao().hitNumUpdate(boardNo);
				}
			}
		}
		
		if(visitor == 0) {
			Cookie cookie1 = new Cookie("visit", no);
			response.addCookie(cookie1);
			new BoardDao().hitNumUpdate(boardNo);
		}
		/////////////////////////////////////////////////////////
		
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo = dao.findByNo(boardNo);
		request.setAttribute("vo", vo);
		request.setAttribute("pNo", pageNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		MvcUtil.forward("board/view", request, response);

	}
}
