package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	@RequestMapping("/write")
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
	
	/*
	@RequestMapping("reply")
	public String reply() {
		boardService.reply();
		return "";
	}
	
	@RequestMapping("replySuccess")
	public String replySuccess() {
		boardService.replySuccess();
		return "";
	}
	*/
	
	@RequestMapping("delete/{no}")
	public String delete(@PathVariable("no") int boardNo) {
		boardService.delete(boardNo);
		return "redirect:/board/1";
	}
	

}
