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
<title>거래 게시판 글쓰기창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
/* #price:invalid {
  border: 3px solid red;
} */
</style>
<script type="text/javascript">
   function readURL(input) {
      if (input.files && input.files[0]) {
         var reader = new FileReader();
         reader.onload = function(e) {
            $(input).parent().next().find('.preview').attr('src',
                  e.target.result);
         }
         reader.readAsDataURL(input.files[0]);
      }
   }

   function backToList(obj) {
      obj.action = "${contextPath}/board//tb_board/tb_listArticles.do";
      obj.submit();
   }

   var cnt = 0;
   function addFile() {
      cnt++;
      $("#file-list")
            .append(
                  "<tr><td><input type='file' name='file"
                        + cnt
                        + "' onchange='readURL(this);'/><a href='#this' name='file-delete'>삭제</a></td><td><img class='preview' id=file"+cnt+"' scr='#' width=200 height=200/></td></tr>");
      $("a[name='file-delete']").on("click", function(e) {
         e.preventDefault();
         console.log("$this", $(this));
         deleteFile($(this));
      });
   }

   function deleteFile(obj) {
      obj.closest('tr').remove();
   }

   
   $(function() {
      var $input = $("#price");
      $input.on('keyup', function() {
         // 입력 값 알아내기
         var _this = this;
         numberFormat(_this)
      })

   });

   // 콤마 찍기
   function comma(str) {
      str = String(str);
      return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
   }

   // 콤마 풀기
   function uncomma(str) {
      str = String(str);
      return str.replace(/[^\d]+/g, '');
   }

   function numberFormat(obj) {
      obj.value = comma(uncomma(obj.value));
   }
   
   function fn_add_new_goods(obj){
	      var sel = document.getElementById("sort");
	      var val = sel.options[sel.selectedIndex].value;
	       fileName = $('.preview').val();
	       if(fileName == null && val=="판매"){
	          alert("판매 글은 이미지는 반드시 첨부해야 합니다.");
	          return;
	          
	       }else{
	          obj.submit();
	       }
	   }
</script>
</head>

<BODY>
   <form enctype="multipart/form-data"
      action="${contextPath}/board/tb_board/addNewtrans.do" method="post"
      name="transForm">
      <div class="row">
         <div class="col-md-3">
            <div class="form-group">
               <label for="writer">작성자</label> <input type="text"
                  class="form-control" name="member_id" id="writer"
                  value="${memberInfo.member_id}" readonly>
            </div>
         </div>
         <input type="hidden" id="id" name="member_id"
            value="${memberInfo.member_id}">
      </div>

      <div class="form-group" style="margin-right: 70px">
         <select name="trans_sort" id="sort">
            <option value="판매" selected>판매
            <option value="구매">구매
         </select> <label for="title">글제목</label> <input type="text"
            class="form-control" name="tb_title" id="title" value=""
            required="required">
      </div>
      <table class="form-group">
         <tr>
            <td>제품이름</td>
            <td><input name="product_name" type="text" size="40"
               required="required" /></td>
         </tr>
         <tr>
            <td>희망판매/구매가격</td>
            <td><input name="sales_price" id="price" type="text"
               required="required" />원</td>
         </tr>
         <tr>
            <td width=200>거래방식</td>
            <td width=500><select name="contract_sort" id="sort"
               required="required">
                  <option value="직거래" selected>직거래
                  <option value="택배">택배
            </select></td>
         </tr>
      </table>
      <div class="form-group">
         <div>글내용</div>
         <textarea style="margin-right: 70px" class="form-control" rows="10" name="tb_content"
            id="content" style="resize: none;" required="required"></textarea>
      </div>
      <div>
      <table>
      <tr>
      <td>
      첫번째로 업로드되는 이미지는 대표 이미지로 사용됩니다.
      </td>
      </tr>
      <tr>
      <td class="form-group" id="file-list">
         <a href="#this" onclick="addFile()">파일추가</a>
      </td>
      </tr>
      </table>      
      </div>
      <div class="center-block" style='width: 400px' align="center">
         <input  type="button" value="등록하기" class="btn btn-info" onClick="fn_add_new_goods(this.form)">&nbsp;&nbsp; <input
            type="reset" class="btn btn-info" value="다시쓰기"
            style="background-color: #2dcb73">&nbsp;&nbsp; <input
            type="button" class="btn btn-info" value="취소"
            onClick="backToList(this.form)" id="cancelBtn"
            style="background-color: #2dcb73">
      </div>
   </form>
</body>
</html>