<%@page import="com.douzone.mysite.vo.guestbookVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<guestbookVo> list = (List<guestbookVo>)request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath()%>/guestbook?a=add" method="post">
					<input type="hidden" name="a" value="insert">
					<table>
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<%
					int i = list.size() + 1;
					for(guestbookVo vo : list){
						i -= 1;
				%>
					<ul>
						<li>
							<table>
								<tr>
									<td><%=i %></td>
									<td><%=vo.getName() %></td>
									<td><%=vo.getRegDate() %></td>
									<td><a href="<%=request.getContextPath()%>/guestbook?a=del&no=<%=vo.getNo() %>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
									<%=vo.getMessage() %>	
									</td>
								</tr>
							</table>
							<br>
						</li>
					</ul>
				<% } %>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>