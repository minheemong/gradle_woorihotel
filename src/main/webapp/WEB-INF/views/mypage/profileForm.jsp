<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_menu.jsp" %>
<style type="text/css">
input{border:#BEBBB9 solid 1px;}
</style>
<script src="mypage/mypage.js"></script>

<article>

<form method="post" name="formm" action="profileUpdate">
	
<div id="abox_aa">
	<div id="sum_aa">프로필 수정</div>
	<br>
	<span id="chklisttxt_aa">${loginUser.name}님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인합니다.</span>
	<br><br>
	<div id="chklistbox_aa">
	<br><br>
		<table class="pwBox">
			<tr><th>아이디</th><td>${loginUser.id}</td></tr>
			<tr><th>이름</th><td>${loginUser.name}</td></tr>
			<tr><th>이메일</th><td><input type="text" size="30" name="email" value="${loginUser.email}"></td></tr>
			<tr><th>전화번호</th><td><input type="text" size="20" name="phone" value="${loginUser.phone}"></td></tr>
			<tr><th rowspan="3">주소</th><td><input type="text" name="zip_num" size="10" value="${loginUser.zip_num}">      
	   		<input type="button" value="주소 찾기" class="dup_brown" onclick="post_zip();"></td></tr>
			<tr><td><input type="text" name="addr1"   size="50" value="${addr1}"></td></tr>
			<tr><td><input type="text" name="addr2"   size="25" value="${addr2}"></td></tr>
			
			<input type="hidden" name="id" value="${loginUser.id}">
			<input type="hidden" name="name" value="${loginUser.name}">
			<input type="hidden" name="pwd" value="${loginUser.pwd}">
		</table>
		<br>
		<div id="profText">&nbsp;&nbsp;&nbsp;${message}</div>
		<div id="profButton">
			<input type="submit" value="확인" class="okButton">
			<input type="button" value="취소" class="cancleButton" onclick="go_profilePw()">
		</div>
	</div>
</div>
</article>




<%@ include file="../footer.jsp" %>  