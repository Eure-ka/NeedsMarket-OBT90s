
<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<head>
<style>
#layer {
   z-index: 2;
   position: absolute;
   top: 0px;
   left: 0px;
   width: 100%;
}

#popup {
   z-index: 3;
   position: fixed;
   text-align: center;
   left: 50%;
   top: 45%;
   width: 300px;
   height: 200px;
   background-color: #ccffff;
   border: 3px solid #87cb42;
}

#close {
   z-index: 4;
   float: right;
}

.addRepl {
   border-style: none;
   width: 50px;
   height: 97px;
   border-radius: 5px;
   border-collapse: collapse;
   margin-bottom: 5px;
}

#replyTable {
   margin-left: 2.5em;
   margin-right: 2em;
}

#replyArea {
   border-collapse: collapse;
   padding: -1px;
}

.delRepl {
   font-size: 15px;
}

#date {
   font-size: 15px;
   color: lightgrey;
}

#reListTable {
   font-size: 18px;
   color: darkslategrey;
   margin-left: 2em;
   margin-right: 2em;
}

#letter_reply {
   font-size: 20px;
}

textarea {
   width: 900px;
   padding: 10px;
   box-sizing: border-box;
   border: solid 2px #d2d2d2;
   border-radius: 5px;
   font-size: 16px;
   resize: both;;
   border-collapse: collapse;
}
</style>
<script type="text/javascript">
     
function fn_recommend_article(url,tb_number,isLogin ){
   if(isLogin== true){
   alert("해당 글을 추천했습니다");
   var form = document.createElement("form");
   form.setAttribute("method", "post");
   console.log("url>>>>>>>>>>"+url)
    form.setAttribute("action", url);
    var articleNOInput = document.createElement("input");
    articleNOInput.setAttribute("type","hidden");
    articleNOInput.setAttribute("name","tb_number");
    articleNOInput.setAttribute("value", tb_number);
    form.appendChild(articleNOInput);
    document.body.appendChild(form);
    form.submit();
   }else{
       alert("로그인 후 추천하기가 가능합니다.")
       location.href='${contextPath}/member/loginForm.do';
    }
}
    
     function backToList(obj){
       obj.action="${contextPath}/board/tb_board/tb_listArticles.do";
       obj.submit();
     }
 
    function fn_enable(obj){
       document.getElementById("i_title").disabled=false;
       document.getElementById("i_content").disabled=false;
       document.getElementById("i_imageFileName").disabled=false;
       document.getElementById("tr_btn_modify").style.display="block";
       document.getElementById("tr_file_upload").style.display="block";
       document.getElementById("tr_btn").style.display="none";
    }
    
    function fn_modify_article(url,tb_number){
       var form = document.createElement("form");
       form.setAttribute("method", "post");
       form.setAttribute("action", url);
        var articleNOInput = document.createElement("input");
        articleNOInput.setAttribute("type","hidden");
        articleNOInput.setAttribute("name","tb_number");
        articleNOInput.setAttribute("value", tb_number);
       
        form.appendChild(articleNOInput);
        document.body.appendChild(form);
        form.submit();
    }
    
    function fn_remove_article(url,tb_number){
      
          var form = document.createElement("form");
          form.setAttribute("method", "post");
          form.setAttribute("action", url);
           var articleNOInput = document.createElement("input");
           articleNOInput.setAttribute("type","hidden");
           articleNOInput.setAttribute("name","tb_number");
           articleNOInput.setAttribute("value", tb_number);
          
           form.appendChild(articleNOInput);
           document.body.appendChild(form);
           form.submit();
    }
    
    function fn_reply_form(url, parent_no,isLogin){
      if(isLogin== true){
       var form = document.createElement("form");
       form.setAttribute("method", "post");
       form.setAttribute("action", url);
        var parentNOInput = document.createElement("input");
        parentNOInput.setAttribute("type","hidden");
        parentNOInput.setAttribute("name","parent_no");
        parentNOInput.setAttribute("value", parent_no); 
        form.appendChild(parentNOInput);
        document.body.appendChild(form);
       form.submit();
      } else{
          alert("로그인 후 글쓰기가 가능합니다.")
          location.href='${contextPath}/member/loginForm.do';
       }
    }
    
   function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
   
   function fn_recommend_article(url,tb_number,isLogin ){
		if(isLogin== true){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		console.log("url>>>>>>>>>>"+url)
		 form.setAttribute("action", url);
	    var articleNOInput = document.createElement("input");
	    articleNOInput.setAttribute("type","hidden");
	    articleNOInput.setAttribute("name","tb_number");
	    articleNOInput.setAttribute("value", tb_number);
	    form.appendChild(articleNOInput);
	    document.body.appendChild(form);
	    form.submit();
		}else{
			 alert("로그인 후 글쓰기가 가능합니다.")
			 location.href='${contextPath}/member/loginForm.do';
		 }
	}
 </script>
</head>
<body>
   <c:set var="article" value="${resultMap.transBoard }" />
   <c:set var="replList" value="${resultMap.replList }" />
   <c:set var="imageFileList" value="${resultMap.imageFileList }" />
   
   <form name="frmArticle" method="post">
   <c:if test="${empty imageFileList || imageFileList =='null' }">
      <hgroup>
         <h1>${article.trans_sort}글</h1>
      </hgroup>
      <div class="row">
         <div class="col-md-3">
            <div class="form-group">
               <label for="writer">작성자</label> <input type="text"
                  style="margin-right: 70px" class="form-control" name="member_id"
                  id="writer" value="${article.member_id}" readonly>
            </div>
         </div>
         <input type="hidden" id="id" name="member_id"
            value="${article.member_id}">
      </div>
      <div class="form-group" style="margin-right: 70px">
         <label for="title">글제목 <input type=text name="tb_title"
            value="${article.tb_title}" disabled />
      </div>

      <div class="form-group" style="margin-left: 70px">
         <label for="content">글내용</label> <input type=text
            class="form-control" style="width: 950px; height: 500px"
            value="${article.tb_content}" name="tb_content"
            style="resize: none;" id="content" required="required" readonly>

      </div>

   </c:if>

   <c:if
      test="${not empty imageFileList && imageFileList !='null' }">
      <hgroup>
         <h1>${article.trans_sort}글</h1>
      </hgroup>
      <div class="form-group" style="margin-right: 70px">
         <label for="title">글제목 <input type=text name="tb_title"
            value="${article.tb_title}" disabled />
      </div>
      <figure>
         <c:forEach var="item" begin="0" end="0" items="${imageFileList}">
            <table>
               <tr>
                  <td rowspan="4" align="center">
                  <input type="hidden" name="originalFileName" value="${item.imageFileName }" />
                  <img src="${contextPath}/download.do?tb_number=${article.tb_number}&imageFileName=${item.imageFileName}" id="preview" style="width: 50%" /></td>
                  <td class="fixed">작성자</td>
                  <td class="fixed"><input type="text"
                  style="margin-right: 70px" class="form-control" name="member_id"
                  id="writer" value="${article.member_id}" readonly></td>
               </tr>
               <tr>
               <td class="fixed">판매 물건</td>
                  <td class="fixed"><input type="text" readonly
                     value="${article.product_name }" />
               </tr>
               <tr>
               <td class="fixed">판매가</td>
                  <td class="fixed"> <input type="text" readonly
                        value="${article.sales_price}원" />
               </tr>
               <tr>
                  <td class="fixed">거래</td>
                  <td class="fixed"><input type="text" readonly
                     value="${article.contract_sort }"></td>
               </tr>
            </table>
         </c:forEach>
      </figure>
      <div class="clear"></div>
      <!-- 내용 들어 가는 곳 -->
      <div class="form-group" style="margin-left: 70px">
         <label for="content">글내용</label> <input type=text
            class="form-control" style="width: 950px; height: 500px"
            value="${article.tb_content}" name="tb_content"
            style="resize: none;" id="content" required="required" readonly>
         </textarea>
      </div>
      <div class="clear"></div>

   </c:if>
   <table border=0 align="center">
      <c:if
         test="${not empty imageFileList && imageFileList !='null' }">
         <c:forEach var="item" items="${imageFileList}"
            varStatus="status">
            <tr>
               <td width="100" align="center" bgcolor="#2dcb73" rowspan="2">
                  이미지${status.count }</td>
               <td><input type="hidden" name="originalFileName"
                  value="${item.imageFileName }" /> <img
                  src="${contextPath}/download.do?tb_number=${article.tb_number}&imageFileName=${item.imageFileName}"
                  id="preview" style="width: 75%" /><br></td>
            </tr>
            <tr>
               <td>
                  <!-- <input  type="file"  name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"   /> -->
               </td>
            </tr>
         </c:forEach>
      </c:if>

      <tr id="tr_btn">
				<td colspan="2" align="center"><c:if
						test="${memberInfo.member_id == article.member_id}">
						<input type=button value="수정하기" class="btn btn-info btn-sm"
							onClick="fn_modify_article('${contextPath}/board/tb_board/tb_articlemodForm.do',${article.tb_number})">
						<input type=button value="삭제하기" class="btn btn-danger btn-sm"
							onClick="fn_remove_article('${contextPath}/board/tb_board/removetrans.do' , ${article.tb_number})">
					</c:if> 
					<input type=button value="추천하기" class="btn btn-warning btn-sm"
							onClick="fn_recommend_article('${contextPath}/board/tb_board/recommend.do' , ${article.tb_number},${isLogin})">
					<input type=button value="리스트로 돌아가기" class="btn btn-primary btn-sm" onClick="backToList(this.form)"> 
				</td>
			</tr>
   </table>
   <br>
   <hr>
	</form>
   <!-- 댓글 -->

   <c:set var="reply" value="${resultMap.reply}" />
   <script
      src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.0/css/solid.min.css"></script>
   <form id="replyTable" action="${contextPath}/reply/addNewReply.do"
      method="post" onsubmit="return loginCheck()">

      <h3 id="letter_reply" align="left">
         <i class="far fa-comment-dots"></i>&nbsp&nbsp댓글
      </h3>
      <table id="replyArea" align="center" border="0" width="950px">
         <tr>
            <td><input type="hidden" size="20" name="member_id"
               id="member_id" value="${memberInfo.member_id}" /> <input
               type="hidden" name="article_number" value="${article.tb_number}" />
               <input type="hidden" name="boardtype" value="trans_board" /></td>
            <td><textarea name="reply_content" rows="3" cols="100"
                  maxlength="500" placeholder="댓글을 남겨보세요"></textarea></td>
            <td>
               <button class="addRepl" type="submit">댓글 달기</button>
            </td>
         </tr>

      </table>
   </form>

   <script>
   /* 로그인 체크 */
   
      function loginCheck() {
         console.log("wow");
         console.log($('#member_id').val());
         if($('#member_id').val() == "" ){
            alert("로그인을 해주세요!");
            location.href = "${contextPath}/member/loginForm.do";
            return false;
         }else{
            console.log("wow else");
            return true;
         }
          
       }
   
      /* $(document).ready( function () {
          $('#replyTable').DataTable();
      } );  */
   </script>


<table align="center" border="0" width="950px" id="reListTable">
      <c:forEach var="repl" items="${replList}" varStatus="reply_no">

         <tr class="${repl.reply_no}">
            <td align="left" style="font-size:20px;"><i class="fas fa-user-circle"></i>&nbsp&nbsp${repl.member_id
               }</td>
         </tr>
         <tr class="${repl.reply_no}">

            <td colspan="2" align="left">${repl.reply_content }</td>
         </tr>

         <tr class="${repl.reply_no}" id="plRemove_btn">
            <td align="left" id="date"><fmt:formatDate
                  value="${repl.reply_date }" pattern="yyyy.MM.dd HH:mm" /></td>
            <td align="right"><c:if
                  test="${memberInfo.member_id == repl.member_id }">
                  <a class="delRepl" href="#none" id="${repl.reply_no}">삭제</a>
               </c:if></td>
         </tr>

      </c:forEach>

   </table>
   <hr>
   <script type="text/javascript">
     
/* ---댓글 삭제 ajax */     
     
$(document).ready(function(){
     $(".delRepl").click(function(e){
        e.preventDefault();
        console.log("wow");
        console.log($(this).attr("id")); //32
        var reply_no = $(this).attr("id");
        
        $.ajax({
           method: "POST",
           url: "${contextPath}/reply/removeReply.do",
           contentType: "application/json; charset=UTF-8",
           data: JSON.stringify({"boardKind": "tb", "reply_no": reply_no}),
           //dataType: "json",           
           success: function (res) {
              console.log("done>>> ", res);
              if(res=='success'){
                 $('.'+reply_no).remove();
              }
           },
           error: function (request, status, error) {
              console.log(status);
              console.log(error);
           }
           });
   });
});   

</script>
</body>
</html>