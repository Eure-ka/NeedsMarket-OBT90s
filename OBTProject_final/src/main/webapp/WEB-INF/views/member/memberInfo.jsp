<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>

<head>
<meta charset="UTF-8">
<title>회원정보</title>
<style>
#tr_file_upload {
	display: none;
}

#tr_btn_modify {
	display: none;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		obj.action = "${contextPath}";
		obj.submit();
	}

	function fn_enable(obj) {
		document.getElementById("i_name").disabled = false;
		document.getElementById("i_email").style.display = "none";
		document.getElementById("i_emailhead").style.display = "block";
		document.getElementById("i_emailtail").style.display = "block";
		document.getElementById("tr_btn_modify").style.display = "block";
		document.getElementById("tr_btn").style.display = "none";
		document.getElementById("gol").style.display = "block";
	}

	function fn_modify_memberInfo(obj) {

		var emailVal = $("#i_emailhead").val();
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*/i;
		if (emailVal.match(regExp) != null) {
			obj.action = "${contextPath}/member/modmemberInfo.do";
			obj.submit();
		} else {
			alert('올바르지 않은 이메일 형식');
		}
	}

	function fn_remove_article(url) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</head>
<body>
	<form name="memberinfo" method="post" style="position: relative; left:140px">
		<div class="container" >
			<div class="row">
				<div class="col-sm-12">
					<div class="col-sm-2"></div>
					<div class="col-sm-9">
						<h2 class="text-center">회원 정보 보기</h2>
						<table class="table table-striped">
							<tr>
								<td>아이디</td>
								<td><input type="text"  value="${memberInfo.member_id}"  name="member_id" style="border: none"readonly /></td>
							</tr>

							<tr>
								<td>이메일</td>
								<td><input type="text" name="email"
									value="${memberInfo.email_head}@${memberInfo.email_tail}"
									id="i_email" style="border: none" disabled /> <input
									type="text" name="email_head" value="${memberInfo.email_head}"
									id="i_emailhead" style="display: none; border: none" />
								<p id="gol" style="display: none">@</p>
									<select name="email_tail" onChange="" title="직접입력"
									id="i_emailtail" style="display: none; border: none">
										<option value="hanmail.net">hanmail.net</option>
										<option value="naver.com">naver.com</option>
										<option value="yahoo.co.kr">yahoo.co.kr</option>
										<option value="hotmail.com">hotmail.com</option>
										<option value="paran.com">paran.com</option>
										<option value="nate.com">nate.com</option>
										<option value="google.com">google.com</option>
										<option value="gmail.com">gmail.com</option>
										<option value="empal.com">empal.com</option>
										<option value="korea.com">korea.com</option>
										<option value="freechal.com">freechal.com</option>
								</select></td>
							</tr>

							<tr>
								<td>이름</td>
								<td><input type=text value="${memberInfo.name}"
									name="name" id="i_name" style="border: none" disabled /></td>
							</tr>

							<tr>
								<td>가입일</td>
								<td><fmt:formatDate value="${memberInfo.joinDate}"
										pattern="yyyy-MM-dd" /></td>
							</tr>

							<tr>
								<td>성별</td>
								<td>${memberInfo.gender}</td>
							</tr>

							<tr>
								<td>생년</td>
								<td>${memberInfo.birth_year}</td>
							</tr>

							<tr>
								<td>생월</td>
								<td>${memberInfo.birth_month}</td>
							</tr>
							<tr>
								<td>생일</td>
								<td>${memberInfo.birth_day}</td>
							</tr>
							<tr id="tr_btn_modify" align="center">
								<td colspan="2"><input type=button value="수정반영하기"
									onClick="fn_modify_memberInfo(memberinfo)"
									class="btn btn-primary"> <input type=button value="취소"
									onClick="backToList(memberinfo)" class="btn btn-danger">
								</td>
							</tr>
							<tr id="tr_btn">
								<td colspan="2" align="center"><input type=button
									value="회원정보수정하기" onClick="fn_enable(this.form)"
									class="btn btn-success"> <input type=button
									value="회원탈퇴하기"
									onClick="fn_remove_article('${contextPath}/member/memberDeleteView.do')"
									class="btn btn-danger"> <input type=button
									value="리스트로 돌아가기" onClick="backToList(this.form)"
									class="btn btn-primary"></td>
							</tr>
						</table>
					</div>
				</div>
				<!-- col-sm-12 -->
			</div>
			<!-- row -->
		</div>
		<!-- container end-->
	</form>
</body>
</html>