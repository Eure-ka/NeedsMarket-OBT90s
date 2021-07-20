<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>회원가입</title>
<!-- <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function fn_overlapped() {
		var member_id = $("#member_id").val();
		if (member_id == '') {
			alert("ID를 입력하세요");
			return;
		}
		$.ajax({
			type : "post",
			async : false,
			url : "${contextPath}/member/overlapped.do",
			dataType : "text",
			data : {
				id : member_id
			},
			success : function(data, textStatus) {
				if (data == 'false') {
					alert("사용할 수 있는 ID입니다.");
					$('#btnOverlapped');
					$('#member_id');
					$('#_member_id').val(member_id);
				} else {
					alert("사용할 수 없는 ID입니다.");
				}
			},
			error : function(data, textStatus) {
				alert("에러가 발생했습니다.");
			},
			complete : function(data, textStatus) {
				/* alert("작업을완료 했습니다"); */
			}
		}); //end ajax	 
	}
</script>
<style>
@import url("http://fonts.googleapis.com/earlyaccess/nanumgothic.css");
	
	html {
		height: 100%;
	}
	
	body {
	    width:100%;
	    height:100%;
	    margin: 0;
  		padding-top: 80px;
  		padding-bottom: 40px;
  		font-family: "Nanum Gothic", arial, helvetica, sans-serif;
  		background-repeat: no-repeat;
  		background:linear-gradient(to bottom right, #0098FF, #6BA8D1);
	}

    .card{
    	align-content: center;
		width:60%; 
		border-radius:20px;
        margin: 0 auto;
        margin-bottom: 10px; 
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
	} 
</style>
</head>
<body>
	<div class="card">
	<div class="card-body">
		<h2 style="text-align: center">회원가입창</h2>
		<div class="row">
				<form action="${contextPath}/member/addMember.do" method="post">
					<div class="row">
						<div class="col-xs-5 col-md-5">
							<input type="text" class="form-control" name="member_id" id="member_id" placeholder="아이디"/> 
							<input type="hidden" name="_member_id" id="_member_id" /> 
							<input type="button" id="btnOverlapped" value="중복체크" onClick="fn_overlapped()" />
						</div>
					</div>
					<br> <br>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<input type="text" class="form-control input-lg" name="member_pw"
								placeholder="비밀번호" ng-required="true" ng-maxlength="4" />
						</div>
					</div>
					<br> <br>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<input type="text" class="form-control input-lg" name="name"
								placeholder="이름" ng-required="true" ng-maxlength="4" />
						</div>
					</div>
					<br> <br>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<input type="text" class="form-control input-lg"
								placeholder="법정생년월일을 선택해주세요" ng-required="true" ng-maxlength="4"
								readonly /> <select name="birth_year">

								<c:forEach var="year" begin="1" end="100">
									<c:choose>
										<c:when test="${year==80}">
											<option value="${ 1920+year}" selected>${ 1920+year}
											</option>
										</c:when>
										<c:otherwise>
											<option value="${ 1920+year}">${ 1920+year}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>년 <select name="birth_month">
								<c:forEach var="month" begin="1" end="12">
									<c:choose>
										<c:when test="${month==1 }">
											<option value="${month }" selected>${month }</option>
										</c:when>
										<c:otherwise>
											<option value="${month }">${month}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>월 <select name="birth_day">
								<c:forEach var="day" begin="1" end="31">
									<c:choose>
										<c:when test="${day==1 }">
											<option value="${day}" selected>${day}</option>
										</c:when>
										<c:otherwise>
											<option value="${day}">${day}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>일 <span style="padding-left: 50px"></span>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<input type="text" name="email_head"
								class="form-control input-lg" placeholder="email_head"
								ng-required="true" ng-maxlength="3" />
						</div>
					</div>
					@
					<div class="row">
						<div class="col-xs-6 col-md-6">
							<input type="text" name="email_tail"
								class="form-control input-lg" placeholder="email_tail"
								ng-required="true" /> <select name="email_tail" onChange=""
								title="직접입력">
								<option value="non">직접입력</option>
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
							</select>
						</div>
					</div>
					<br> <br>
					<div class="row">
						<div class="col-xs-4 col-md-4">
							<input type="text" name="roadAddress"
								class="form-control input-lg" placeholder="지번 주소 "
								ng-required="true" ng-maxlength="4" />
						</div>
						<div class="col-xs-4 col-md-4">
							<input type="text" name="jibunAddress"
								class="form-control input-lg" placeholder="도로명 주소 "
								ng-required="true" ng-maxlength="6" />
						</div>
						<div class="col-xs-4 col-md-4">
							<input type="text" name="namujiAddress"
								class="form-control input-lg" placeholder="나머지주소 "
								ng-required="true" ng-maxlength="4" />
						</div>
					</div>
					<br> <label>성별 : </label> <input type="radio" name="gender"
						value="M">남자 <input type="radio" name="gender" value="F">여자
					<br>
					<button class="btn btn-lg btn-primary btn-block signup-btn"
						type="submit" style="position: relative; left:50px;">회원가입</button>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>