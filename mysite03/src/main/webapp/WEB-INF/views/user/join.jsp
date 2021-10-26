<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>	

	<script>
	
		/*
		setTimeout(function(){
			
			//ajax
			$.ajax({
				url: "/mysite03/msg02",
				type: "get",
				dataType: "json", 
				success: function(response){
					p =  $("#test");
					p.html("<strong>" + response.message + "</strong>");
				}
			});
		}, 3000);
		
		for(i = 0; i < 5; i++){
			console.log("Hello World");
		}
		*/
		
		//자바스크립트 이벤트 처리
		/*	
		window.onload = function(){
			console.log("loaded!");
			btnCheckEmail = $("#btnChkEmail");
			btnCheckEmail.click(function(){
				console.log("click");	
			});
		};
		*/
		
		//jquery 이벤트 처리
		$(function(){
			$("#btnChkEmail").click(function(){
				var email = $("#email").val();
				if(email == ''){
					return;
				}
				console.log(email);
				$.ajax({
					url:"${pageContext.request.contextPath }/user/api/checkemail?email=" + email,
					type: "get",
					dataType: "json",
					error: function(xhr, statues, e){				//xhr: ajax에서 사용하는 XmlHttpRequest객체
						console.log(status, e);
					},					
					success: function(response){
						console.log(response);
						
						if(response.result != "success"){
							console.error(response.message);
							return;
						}
						
						if(response.data){
							alert("존재하는 이메일입니다. 다른 이메일을 사용하세요");
							$("#email").val("").focus();
							return;
						}
						
						$("#btnChkEmail").hide();
						$("#imgCheckEmail").show();
					}
				});
			});
		});
		
		
	</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="${userVo.name }">
					<p style="text-align:left; padding-left:0; color:red;">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('name') }">		<!-- name이라는 필드에 에러가 있는지 여부 판단 -->
								${errors.getFieldError( 'name' ).defaultMessage }
							</c:if>
						</spring:hasBindErrors>
					</p>
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="${userVo.email }">
					<input id="btnChkEmail" type="button" value="id 중복체크">
					<img id="imgCheckEmail" src="${pageContext.request.contextPath }/assets/images/check.png" style="width:20px;display:none">
					<p style="text-align:left; padding-left:0; color:red;">
						<spring:hasBindErrors name="userVo">
							<c:if test="${errors.hasFieldErrors('email') }">		<!-- email이라는 필드에 에러가 있는지 여부 판단 -->
								${errors.getFieldError( 'email' ).defaultMessage }
							</c:if>
						</spring:hasBindErrors>
					</p>
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
			<p id="test">
			
			</p>
		</div>
		
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>