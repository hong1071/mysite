package com.douzone.web.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplySuccessAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String groupNo = request.getParameter("groupNo");
		String orderNo = request.getParameter("orderNo");
		String depth = request.getParameter("depth");
		String userNo = request.getParameter("no");
		
		String pno = request.getParameter("pNo");
		
		int bGroupNo = Integer.parseInt(groupNo);
		int bOrderNo = Integer.parseInt(orderNo);
		int bDepth = Integer.parseInt(depth);
		int bUserNo = Integer.parseInt(userNo);
		
		int pNo = Integer.parseInt(pno);
		
		//같은 그룹 내의 orderNo 조정
		if(bOrderNo == 1) {
			BoardVo vo1 = new BoardVo();
			vo1.setGroupNo(bGroupNo);
			vo1.setDepth(bDepth);
			vo1.setOrderNo(bOrderNo);
			new BoardDao().UpdateOrderNo2(vo1);
		}
		else {
			BoardVo vo1 = new BoardVo();
			vo1.setGroupNo(bGroupNo);
			vo1.setDepth(bDepth);
			vo1.setOrderNo(bOrderNo);
			new BoardDao().UpdateOrderNo1(vo1);
		}
		
		//답글 insert
		BoardVo vo2 = new BoardVo();
		vo2.setTitle(title);
		vo2.setContents(contents);
		vo2.setGroupNo(bGroupNo);
		vo2.setOrderNo(bOrderNo + 1);
		vo2.setDepth(bDepth + 1);
		vo2.setUserNo(bUserNo);
		
		new BoardDao().replyInsert(vo2);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list&pNo="+ pNo, request, response);
	

	}

}
