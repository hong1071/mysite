package com.douzone.mysite.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.security.Auth;


@Controller
public class MainController {
	
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		return "안녕";
	}
	
	@ResponseBody
	@RequestMapping("/msg02")
	public Map<String, Object> message02(/*HttpServletResponse resp*/) throws Exception {
//		resp.setContentType("application/json; charset=UTF-8");
//		resp.getWriter().print("{\"message\":\"hello world\"}");
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");
		return map;
	}
}
