package com.douzone.web.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Num = request.getParameter("pNum");
		int pageNum = Integer.parseInt(Num);
		
		BoardDao dao = new BoardDao();
		
		// 전체 글 갯수의 길이를 구해 paging을 위한 수를 구한다.
		List<BoardVo> AllList = dao.findAll();
		
		// (전체 글 갯수 / 10) + 1 = 페이징 최댓값
		int pageLength = (int) Math.ceil((AllList.size() / 10) + 1);

		//페이지에 따른 글 갯수를 나타낸다.
		List<BoardVo> list = dao.findByPage(pageNum - 1);
		
		request.setAttribute("list", list);
		request.setAttribute("pageLength", pageLength);
		request.setAttribute("pageNum", pageNum);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		MvcUtil.forward("board/list", request, response);

	}

}
