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
       var reader = new FileReader();
       reader.onload = function(e) {
          $(input).parent().next().find('.preview').attr('src', e.target.result);
       }
       reader.readAsDataURL(input.files[0]);
    }
 }

 function backToList(obj) {
    obj.action = "${contextPath}/board/fb_board/fb_listArticles.do";
    obj.submit();
 }

 var cnt=0;
 function addFile() {
    cnt++;
    $("#file-list").append("<tr><td><input type='file' name='file"+cnt+"' onchange='readURL(this);'/><a href='#this' name='file-delete'>삭제</a></td><td><img class='preview' id=file"+cnt+"' scr='#' width=200 height=200/></td></tr>");
    $("a[name='file-delete']").on("click", function(e) {
       e.preventDefault();
       console.log("$this", $(this));
       deleteFile($(this));
    });
 }

 function deleteFile(obj) {
    obj.closest('tr').remove();
 }
</script>
<title>글쓰기창</title>
</head>
<body>
   <h1 style="text-align: center">글쓰기</h1>
   <form enctype="multipart/form-data"
      action="${contextPath}/board/fb_board/addNewfree.do"
      method="post">
      <div class="row">
         <div class="col-md-6">
            <div class="form-group">
               <label for="writer">작성자</label> <input type="text"
                  class="form-control" name="member_id" id="writer"
                  value="${memberInfo.name}" readonly>
            </div>
         </div>
         <input type="hidden" id="id" name="member_id" value="${memberInfo.name}">
      </div>

      <div class="form-group" style="margin-right: 70px">
         <label for="title">글제목</label> <input type="text"
            class="form-control" name="fb_title" id="title" value=""
            required="required">
      </div>

      <div class="form-group" style="margin-left: 70px">
         <label for="content">글내용</label>
         <textarea class="form-control" rows="10" name="fb_content" id="content" style="resize: none;"
            required="required"></textarea>
      </div>

      <div class="form-group" id="file-list">
         <a href="#this" onclick="addFile()">파일추가</a>
      </div>
      <div class="center-block" style='width: 400px' align="center">
         <input type="submit" class="btn btn-info" value="등록하기"
            style="background-color: #2dcb73">&nbsp;&nbsp; <input
            type="reset" class="btn btn-info" value="다시쓰기"
            style="background-color: #2dcb73">&nbsp;&nbsp; <input
            type="button" class="btn btn-info" value="취소" onClick="backToList(this.form)" id="cancelBtn"
            style="background-color: #2dcb73">
      </div>
   </form>

</body>
</html>