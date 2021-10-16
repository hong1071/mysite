package com.douzone.web.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter("bno");
		
		int bno = Integer.parseInt(no);
		
		BoardVo vo = new BoardVo();
		BoardDao dao = new BoardDao();
		vo = dao.findByNo(bno);
		request.setAttribute("vo", vo);		
		MvcUtil.forward("board/reply", request, response);

	}

}
