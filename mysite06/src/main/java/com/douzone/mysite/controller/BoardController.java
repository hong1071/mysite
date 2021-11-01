package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/{pageNo}")
	public String list(@PathVariable("pageNo") int pageNo, Model model) {
		
		List<BoardVo> AllList = boardService.listAll();
		List<BoardVo> list = boardService.listPageNum(pageNo);
		
		int pageLength = (int) Math.ceil((AllList.size() / 10) + 1);
		
		//pageNum 관리(back): 1 미만의 값은 1로, 최대값을 초과하는 값은 최댓값으로 값을 지정한다
		if(pageNo < 1) {
			pageNo = 1;
		}
		else if(pageNo > pageLength) {
			pageNo = pageLength;
		}
		
		model.addAttribute("pageLength", pageLength);
		model.addAttribute("list", list);
		model.addAttribute("pageNo", pageNo);
		
		return "board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") int no, Model model) {
		BoardVo vo = boardService.view(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request) {
		
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		int userNo = Integer.parseInt(no);

		BoardVo vo = new BoardVo();
		vo.setUserNo(userNo);
		vo.setTitle(title);
		vo.setContents(contents);

		boardService.insert(vo);
		
		return "redirect:/board/1";
	}
	
	
	@RequestMapping("/modifyForm/{no}")
	public String modifyForm(@PathVariable("no") int boardNo, Model model) {
		
		BoardVo vo = boardService.findByNo(boardNo);
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request) {
		
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		Long Bno = Long.parseLong(no);
		
		BoardVo vo = new BoardVo();
		vo.setNo(Bno);
		vo.setTitle(title);
		vo.setContents(contents);
		boardService.update(vo);
		
		return "redirect:/board/1";
	}
	
	
	@RequestMapping("replyForm/{no}")
	public String replyForm(@PathVariable("no") int BoardNo, Model model) {
		BoardVo vo = new BoardVo(); 
		vo = boardService.findByNo(BoardNo);
		model.addAttribute("vo", vo);
		return "board/reply";
	}
	
	@RequestMapping("reply")
	public String reply(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String groupNo = request.getParameter("groupNo");
		String orderNo = request.getParameter("orderNo");
		String depth = request.getParameter("depth");
		String userNo = request.getParameter("no");
		
		int bGroupNo = Integer.parseInt(groupNo);
		int bOrderNo = Integer.parseInt(orderNo);
		int bDepth = Integer.parseInt(depth);
		int bUserNo = Integer.parseInt(userNo);
		
		//같은 그룹 내의 orderNo 조정
		if(bOrderNo == 1) {
			BoardVo vo1 = new BoardVo();
			vo1.setGroupNo(bGroupNo);
			vo1.setDepth(bDepth);
			vo1.setOrderNo(bOrderNo);
			boardService.UpdateOrderNo2(vo1);
		}
		else {
			BoardVo vo1 = new BoardVo();
			vo1.setGroupNo(bGroupNo);
			vo1.setDepth(bDepth);
			vo1.setOrderNo(bOrderNo + 1);
			boardService.UpdateOrderNo1(vo1);
		}
		
		//답글 insert
		BoardVo vo2 = new BoardVo();
		vo2.setTitle(title);
		vo2.setContents(contents);
		vo2.setGroupNo(bGroupNo);
		vo2.setOrderNo(bOrderNo + 1);
		vo2.setDepth(bDepth + 1);
		vo2.setUserNo(bUserNo);
		
		boardService.replyInsert(vo2);
		
		return "redirect:/board/1";
	}
	
	
	@RequestMapping("delete/{no}")
	public String delete(@PathVariable("no") int boardNo) {
		boardService.delete(boardNo);
		return "redirect:/board/1";
	}
	

}
