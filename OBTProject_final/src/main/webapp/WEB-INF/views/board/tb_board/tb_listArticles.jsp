<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>

<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}
</style>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<script>
	function fn_articleForm(isLogin){
	  if(isLogin== true){
	    location.href='${contextPath}/board/tb_board/tb_articleForm.do';
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href='${contextPath}/member/loginForm.do';
	  }
	}
</script>
<body>
	<h2
		class="page-section-heading text-center text-uppercase text-secondary mb-0">거래게시판</h2>
	<!-- Icon Divider-->
	<div class="divider-custom">
		<div class="divider-custom-line"></div>
		<div class="divider-custom-icon">
			<i class="fas fa-star"></i>
		</div>
		<div class="divider-custom-line"></div>
	</div>	
	
		<table class="table table-hover">
			<c:choose>
				<c:when test="${articlesList ==null }">
					<tr height="10">
						<td colspan="4">
							<p align="center">
								<b><span style="font-size: 9pt;">등록된 글이 없습니다.</span></b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${articlesList !=null }">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>추천</th>
						</tr>
					</thead>

					<c:forEach items="${articlesList}" var="article"
						varStatus="articleNum">
						<tr>
							<td width="10%">${articleNum.count}<br></td>
							<td width="40%" align="center"><a
								href="${contextPath}/board/tb_board/tb_View.do?tb_number=${article.tb_number}"><c:out
										value="${article.tb_title}" /></a></td>
							<td width="10%"><c:out value="${article.member_id }" /></td>
							<td width="20%"><fmt:formatDate value="${article.write_date}"
									pattern="yyyy-MM-dd" /></td>
							<td width="10%"><c:out value="${article.tb_countnum}" /></td>
							<td width="10%"><c:out value="${article.tb_likenum}" /></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</table>
		<button type="button" class="btn btn-info btn-xs" onclick="fn_articleForm(${isLogin})">글쓰기</button>
</body>
</html>