<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>

<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 취소
		$(".cencle").on("click", function() {

			location.href = "${contextPath}/member/memberInfo.do";

		});
	})
</script>
</head>
<body>
	<section id="container">
		<form action="${contextPath}/member/removeMember.do" method="post">
			<h1>진짜로 탈퇴하실꺼냥?</h1>
			<img
				src="https://i.pinimg.com/564x/57/d0/b9/57d0b9e4dd165d1d070d658c35deeb52.jpg"
				alt="탈퇴사진" style="width: 50%; height:60%" />
			<div class="form-group has-feedback">
				<button class="btn btn-success" type="submit" id="submit">회원탈퇴</button>
				<button class="cencle btn btn-danger" type="button">취소</button>
			</div>
		</form>
	</section>
</body>
</html>