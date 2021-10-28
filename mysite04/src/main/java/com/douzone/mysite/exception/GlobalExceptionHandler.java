package com.douzone.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.dto.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public void HandlerException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception, IOException {
		
		// 1. 로깅
		StringWriter errors = new StringWriter(); 
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		
		// 2. 요청 구분
		// 만약 JSON 요청인 경우, request header의 accept에 application/json
		// 만약 html 요청인 경우, request header의 accept에 text/html
		
		String accept = request.getHeader("accept");
		if(accept.matches(".*application/json.*")) {
	
			// 3-1. JSON 응답 
			JsonResult result = JsonResult.fail(errors.toString());
			String jsonString = new ObjectMapper().writeValueAsString(result);
			
			response.setStatus(HttpServletResponse.SC_OK);
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8"));
			os.close();
		}
		else {
			// 3-2. 사과 페이지(정상종료)
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
	}
}
