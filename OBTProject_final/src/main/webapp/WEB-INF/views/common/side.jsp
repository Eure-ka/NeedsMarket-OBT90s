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
<title>사이드 메뉴</title>
<script src="slideshow.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css" href="slideshow.css" />
<script>
var slideIndex = 0; //slide index

//HTML 로드가 끝난 후 동작
window.onload=function(){
showSlides(slideIndex);

// Auto Move Slide
var sec = 3000;
setInterval(function(){
 slideIndex++;
 showSlides(slideIndex);

}, sec);
}


//Next/previous controls
function moveSlides(n) {
slideIndex = slideIndex + n
showSlides(slideIndex);
}

//Thumbnail image controls
function currentSlide(n) {
slideIndex = n;
showSlides(slideIndex);
}

function showSlides(n) {

var slides = document.getElementsByClassName("mySlides");
var dots = document.getElementsByClassName("dot");
var size = slides.length;

if ((n+1) > size) {
 slideIndex = 0; n = 0;
}else if (n < 0) {
 slideIndex = (size-1);
 n = (size-1);
}

for (i = 0; i < slides.length; i++) {
   slides[i].style.display = "none";
}
for (i = 0; i < dots.length; i++) {
   dots[i].className = dots[i].className.replace(" active", "");
}

slides[n].style.display = "block";
dots[n].className += " active";
}
</script>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing:border-box;
}

/* Slideshow container */
.slideshow-container {
  max-width: 1200px;
  max-height: 750px;
  position: relative;
  margin: auto;
}
.slideshow-container .mySlides img {
  height: 750px;
}

/* Hide the images by default */
.mySlides {
  display: none;
}

/* Next & previous buttons */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  margin-top: -22px;
  padding: 16px;
  color: white;
  font-weight: bold;
  font-size: 18px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
}

/* Position the "next button" to the right */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition-duration: 10s;
}

.active, .dot:hover {
  background-color: #717171;
}

@-webkit-keyframes fade {
  from {opacity: .4}
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4}
  to {opacity: 1}
}
</style>
</head>
<body>
<div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">게시판목록 </div>
      <div class="list-group list-group-flush">
        <a href="${contextPath}/board/fb_board/fb_listArticles.do" class="list-group-item list-group-item-action bg-light">자유게시판</a>
        <a href="${contextPath}/board/tb_board/tb_listArticles.do" class="list-group-item list-group-item-action bg-light">거래게시판</a>
        <a href='${contextPath}/board/rb_board/rb_listarticles.do' class="list-group-item list-group-item-action bg-light">신고게시판</a>
        <div class="slideshow-container">
      <!-- Full-width images with number and caption text -->
      <div class="mySlides">
        <img src="https://i.pinimg.com/564x/ea/bb/65/eabb65ca2724f8c1878b696864d3658f.jpg" style="width:100%">
        <div class="text">ACNE STUDIO</div>
      </div>

      <div class="mySlides">
        <img src="https://i.pinimg.com/564x/80/4d/1c/804d1c9f24b302fb415b9c6e51bd7065.jpg" style="width:100%">
        <div class="text">ACNE STUDIO</div>
      </div>

      <div class="mySlides">
        <img src="https://i.pinimg.com/564x/14/cd/55/14cd55f30b845df1c63744d405ae0b42.jpg" style="width:100%">
        <div class="text">ACNE STUDIO</div>
      </div>

      <div class="mySlides">
        <img src="https://i.pinimg.com/564x/2d/a8/f1/2da8f15fefc26132ec3fd3cd2068d4a3.jpg" style="width:100%">
        <div class="text">ACNE STUDIO</div>
      </div>

      <div class="mySlides">
        <img src="https://i.pinimg.com/564x/9d/59/0a/9d590a9ce528372c8ee2115d10b7717e.jpg" style="width:100%">
        <div class="text">ACNE STUDIO</div>
      </div>

      <div class="mySlides">
        <img src="https://i.pinimg.com/564x/8f/d0/1d/8fd01d792ba13f4d71d52d6bc4d4890c.jpg" style="width:100%">
        <div class="text">ACNE STUDIO</div>
      </div>

      <!-- Next and previous buttons -->
      <!-- <a class="prev" onclick="moveSlides(-1)">&#10094;</a>
      <a class="next" onclick="moveSlides(1)">&#10095;</a> -->
    </div>
    <br/>

    <!-- The dots/circles -->
    <div style="text-align:center">
      <span class="dot" onclick="currentSlide(0)"></span>
      <span class="dot" onclick="currentSlide(1)"></span>
      <span class="dot" onclick="currentSlide(2)"></span>
      <span class="dot" onclick="currentSlide(3)"></span>
      <span class="dot" onclick="currentSlide(4)"></span>
      <span class="dot" onclick="currentSlide(5)"></span>
    </div>
      </div>
    </div>

</body>
</html>