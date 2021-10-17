<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align:left;">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>			
					<c:set var='count' value='${fn:length(list) }' />	
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>[${count-status.index }]</td>
							<td style="text-align:left; padding-left:${(vo.depth - 1)*20}px;">
								<c:choose>
									<c:when test='${vo.depth != 1}'>
										<img src="${pageContext.request.contextPath }/assets/images/reply.png" />
									</c:when>									
								</c:choose>
								<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no}">${vo.title }</a>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<c:choose>
								<c:when test="${authUser.no == vo.userNo}">
									<td><a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no}" class="del">삭제</a></td>
								</c:when>
								<c:otherwise>
									<td><a href="" class="del2"></a></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				
				<!--페이징 오류 방지를 위함: 1 미만의 값은 1로, 최대값을 초과하는 값은 최댓값으로 값을 지정한다 -->
				<c:set var="beginNum" value="${pageNum - 2 }"/>
				<c:set var="endNum" value="${pageNum + 2 }"/>
				<c:if test="${beginNum < 1 }">
					<c:set var="beginNum" value="${beginNum = 1 }"/>
				</c:if>
				<c:if test="${endNum > pageLength  }">
					<c:set var="endNum" value="${endNum = pageLength }"/>
				</c:if>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="${pageContext.request.contextPath }/board?a=list&pNum=${pageNum - 1}">◀</a></li>
							<c:forEach var='i' begin='${beginNum }' end='${endNum }'>
								<c:choose>
									<c:when test="${pageNum == i }">
										<li class="selected" ><a href="${pageContext.request.contextPath }/board?a=list&pNum=${i}">${i }</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.request.contextPath }/board?a=list&pNum=${i}">${i }</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<li><a href="${pageContext.request.contextPath }/board?a=list&pNum=${pageNum + 1}">▶</a></li>
					</ul>
				</div>			
					
				<!-- pager 추가 -->
					<div class="bottom">
						<c:choose>
							<c:when test="${authUser != null }">
								<a href="${pageContext.request.contextPath }/board?a=write&userNo=${authUser.no}" id="new-book">글쓰기</a>
							</c:when>	
						</c:choose>
					</div>			
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>