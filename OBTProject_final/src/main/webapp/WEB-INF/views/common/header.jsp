<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>헤더</title>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="${contextPath}" style="color: black;">니즈마켓</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><c:choose>
							<c:when test="${memberInfo != null }">
								<p class="nav-link" style="color: black; font-weight: bold;">${memberInfo.name}님</p>
							</c:when>
						</c:choose></li>
					<li class="nav-item"><c:choose>
							<c:when test="${memberInfo != null }">
								<a class="nav-link" href="${contextPath}/member/logout.do"
									style="color: black;">로그아웃</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" href="${contextPath}/member/loginForm.do"
									style="color: black;">로그인</a>
							</c:otherwise>
						</c:choose></li>

					<li class="nav-item"><c:choose>
							<c:when test="${memberInfo != null }">
								<a class="nav-link" href="${contextPath}/member/memberInfo.do"
									style="color: black;">회원정보</a>
							</c:when>
							<c:otherwise>
								<a class="nav-link" href="${contextPath}/member/memberForm.do"
									style="color: black;">회원가입</a>
							</c:otherwise>
						</c:choose></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="overlay"
		style="background-image: url('https://cdn.pixabay.com/photo/2015/11/19/08/45/banner-1050602_1280.jpg'); height: 220px"></div>

</body>
</html>