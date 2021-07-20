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
<title>신고 게시판 글쓰기창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			//console.log(input);
			//console.log($(this)); //Window
			console.log($(input)); //Window
			console.log($(input).find('.preview'));
			var reader = new FileReader();
			reader.onload = function(e) {
				$(input).parent().next().find('.preview').attr('src',
						e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj) {
		obj.action = "${contextPath}/board/tb_board/tb_listArticles.do";
		obj.submit();
	}

/* 	function fn_addFile() {
		var cnt = 1;
		/*  $("#d_file").append("<input type='file' name="file"+cnt+' onchange='readURL(this);' /></td><td><img class='preview' scr='#' width=200 height=200/>");)
		$("file_add")
				.on(
						"click",
						function() {
							$("#d_file")
									.append(
											"<br>"
													+ "<input type='file' name='file"+cnt+"' />")
							cnt++;
						}); */
			$(document).ready(function() {
					           $("a[name='file-delete']").on("click", function(e) {
					               e.preventDefault();
					               deleteFile($(this));
					           });
					       })
					    
					       function addFile() {
					           var str = "<div class='file-group'><input type='file' name='file'><a href='#this' name='file-delete'>삭제</a></div>";
					           $("#file-list").append(str);
					           $("a[name='file-delete']").on("click", function(e) {
					               e.preventDefault();
					               deleteFile($(this));
					           });
					       }
					    
					       function deleteFile(obj) {
					           obj.parent().remove();
					       }
	}
</script>
<title>글쓰기창</title>
</head>
<body>
	<h1 style="text-align: center">수정하기</h1>
	<form name="articleForm" method="post"
		action="${contextPath}/board/tb_board/modarticle.do">
		<table border="1" align="center">
			<tr>
				<td align="right">작성자</td>
				<td colspan=2 align="left"><input type="text" size="20"
					maxlength="100" name="member_id" value="${memberInfo.name}"
					readonly></td>
			</tr>
			<tr>
				<td align="right">수정할글제목:</td>
				<td colspan="2"><input type="text" size="67" maxlength="500"
					name="tb_title" /></td>
			</tr>
			<tr>
				<td align="right" valign="top"><br>수정할 글내용:</td>
				<td colspan=2><textarea name="tb_content" rows="10" cols="65"
						maxlength="4000"></textarea></td>
			</tr>
			<input type="text" name="tb_number" value="${tb_number}" hidden />
			<!-- <tr>
				<td align="right">이미지파일 첨부: <a href="#this" onclick="addFile()">파일추가</a>
					<div class="file-group">
						<input type="file" name="imageFileName" onchange="readURL(this);" /><a
							href='#this' name='file-delete'>삭제</a>
					</div>
					<div>
						<img class="preview" src="#" width=200 height=200 />
					</div>
					<div id="d_file"></td>
				<td></div></td>
				<<td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
				<td><img class="preview" src="#"   width=200 height=200/></td>

			</tr> -->
			<tr>
				<td align="right"></td>
				<td colspan="2"><input type="submit" value="수정반영하기" /> <input
					type=button value="이전단계로 돌아가기" onClick="backToList(this.form)" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
