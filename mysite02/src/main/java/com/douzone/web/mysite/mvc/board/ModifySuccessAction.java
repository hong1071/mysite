package com.douzone.web.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifySuccessAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bno = request.getParameter("bNo");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String pno = request.getParameter("pNo");
		
		Long bNo = Long.parseLong(bno);
		Long pNo = Long.parseLong(pno);
		
		BoardVo vo = new BoardVo();
		vo.setNo(bNo);
		vo.setTitle(title);
		vo.setContents(contents);
		new BoardDao().Update(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=view&bNo=" + bNo +"&pNo=" + pNo, request, response);

	}

}
