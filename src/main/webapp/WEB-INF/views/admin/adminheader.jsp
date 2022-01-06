<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>    
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200&display=swap" rel="stylesheet">

<!--dd  -->

<title>Insert title here</title>

<link href="static/css/admin.css" rel="stylesheet" type="text/css">
<link href="static/css/admincss.css" rel="stylesheet" type="text/css">
<link href="static/css/Hotel.css" rel="stylesheet" type="text/css">
<script src="static/script/adminHotel.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&f
amily=Gothic+A1:wght@900&family=Hahmlet:wght@251&family=Nanum+Myeongjo:wght@800&display=swap"
 rel="stylesheet">
 <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200&display=swap" rel="stylesheet">
<style>


.admin_header_logo h1{

font-family: 'Nanum Myeongjo', serif;
}
.admin_header_logo p{

font-family: 'Nanum Myeongjo', serif;
}
.header_gnb {
}
.header_gnb ul li{
font-family: 'Black Han Sans', sans-serif;
font-family: 'Gothic A1', sans-serif;
font-family: 'Hahmlet', serif;
font-family: 'Nanum Myeongjo', serif;
/* dd */
}
</style>





</head>
<body>
<div id="wrap">
<header class="admin_header">

  <div class="admin_header_logo">
        <a href="a">
            <h1>THE WOORI</h1>
            <p > HOTELS & RESORTS</p></a>
            <p> 관리자 페이지 </p>
        
    
 </div>
 
 <div class="admin_header_gnb" >
           <ul >         
                       <li><a class="admin_header_a" href="adminbookchecklist?a=1"> 고객 예약 리스트</a></li>                              
                       <li><a class="admin_header_a" href="adminQnaList?page=1&key=">고객문의 리스트</a></li>
                       <li><a class="admin_header_a" href="adminMemberList" >회원  리스트</a></li>
                       
                       
                       
               <c:choose>  
                     <c:when  test="${empty loginAdmin}">
                           <li><a class="admin_header_a" href="adminloginForm">관리자 로그인</a></li>
                       </c:when>
                    <c:otherwise>
                       <li> 안녕하세요 관리자(${loginAdmin.id})님</li>    
                       <li><a class="admin_header_a" href="adminlogout">로그아웃</a></li>
                  </c:otherwise>
               </c:choose>
          </ul>
    </div>
        
           		
           		
           		
        
</header>