<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>   

<script src="../script/script.js"></script>
<style type="text/css">
input{border:#BEBBB9 solid 1px;}
</style>
<article>
<div class="contTitle">회원가입</div>
<div class="contThreeStep">
	<div class="contNonselect">약관동의</div>
	<div class="contSelect">회원정보 입력</div>
	<div class="contNonselect">가입완료</div>
</div>

<!-- join테마 css -->

<div style="width:865px; margin: 0 auto;">
<form id="join" method="post" name="formm" action="joinComplete">
		
	<br><br>
	<div class="joinTitle">기본입력</div>
	<table class="joinBox">
		<tr><th>이름</th><td><input type="text" size="20" name="name" value="${name}"> </td></tr>
		<tr><th>이메일</th><td><input type="text" size="30" name="email" value="${email}"></td></tr>
		<tr><th>전화번호</th><td><input type="text" size="20" name="phone" value="${phone}"></td></tr>
		<tr><th rowspan="3">주소</th><td><input type="text" name="zip_num" size="10" value="${zip_num}">      
	   		<input type="button" value="주소 찾기" class="dup_brown" onclick="post_zip();"></td></tr>
		<tr><td><input type="text" name="addr1"   size="50" value="${addr1}"></td></tr>
		<tr><td><input type="text" name="addr2"   size="25" value="${addr2}"></td></tr>
	</table>
	<br><br>
	<div class="joinTitle">웹사이트 비밀번호 입력</div>
	<table class="joinBox">	
		<tr><th>아이디</th><td><input type="text" name="id" size="20" value="${id}"> 
	    		<input type="button" value="중복 체크" class="dup_brown" onclick="idcheck();">
				<input type="hidden" name="reid"></td></tr>
				
				<c:if test="${result==-1}">
				${id}는 사용가능한 아이디입니다.
				<input type="button" value="사용" class="cancel" onclick="idok('${id}');">
				</c:if>
				</td></tr>
		<tr><th>비밀번호</th><td><input type="password" name="pwd" size="20">	</td></tr>
		<tr><th>비밀번호 확인</th><td><input type="password" name="pwdCheck" size="20">  </td></tr>
	</table>
	<center><div>${message}</div></center>

<!-- cont테마 css -->
<center><div class="contButtons">
	<input type="submit" value="가입신청" class="joinButton">
</div></center>


</form>
</div>
</article>






<%@ include file="../footer.jsp" %> <!-- 완료 -->