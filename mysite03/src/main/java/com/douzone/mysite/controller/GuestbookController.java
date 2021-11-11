package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String list(Model model) {
		
		List<GuestbookVo> list = guestbookService.list();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping("/spa")
	public String spa(Model model) {
		
		List<GuestbookVo> list = guestbookService.list();
		model.addAttribute("list", list);
		return "guestbook/list-spa";
	}
	
	@RequestMapping("add")
	public String add(GuestbookVo vo) {
		guestbookService.add(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="deleteform/{no}", method=RequestMethod.GET)
	public String deleteform(@PathVariable("no") int no, Model model) {
		
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		
		guestbookService.delete(no, password);
		return "redirect:/guestbook";
	}
}
