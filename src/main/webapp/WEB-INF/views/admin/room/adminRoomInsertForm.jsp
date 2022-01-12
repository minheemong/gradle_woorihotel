<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="../adminheader.jsp" %>
 <script type="text/javascript">

 function self_close(){
		self.close();
}
 </script>
<style>
#adminmemebrlist{
    border: 2px solid #9F876B;
    margin: 0 auto;
    width: 1000px;
    height:200px;
    
}
#adminmemebrlist tr th{
border-bottom:1px solid black;
width:400px;
}
#adminmemebrlist td{
border-bottom:1px solid black;
width:600px;
}
#adminmemebrlist tr td input{
text-align: left;
}

#qna_button{
border:1px solid black;
 height: 36px;
    width: 80px;
    font-weight: bold;
    background: #9F876B;
    font-size: 70%;
}
</style>
<article>


<form method="post" name="formm" action="adminRoomInsert">

<table  id="adminmemebrlist" style="center; width: 500px;">
	<tr><th>객실번호(숫자)</th><td><input type="text" size="30" name="hotelnum"  value="${dto.hotelnum}"></td></tr>
	<tr><th>종류</th><td><input type="text" size="30" name="kind"  value="${dto.kind}"></td></tr>
	<tr><th>인원(숫자)</th><td><input type="text" size="30" name="persons"  value="${dto.persons}"></td></tr>
	<tr><th>가격(숫자)</th><td><input type="text" size="30" name="price"  value="${dto.price}"></td></tr>
	<tr><th>방크기(숫자)</th> <td><input type="text" size="30" name="roomsize"  value="${dto.roomsize}"></td></tr>
	<tr><th>이미지</th> <td><input type="file" name="img"  value="${dto.img}" /></td></tr>
</table>

	<center><input class="btn" type="submit" value="입력완료" id="qna_button">           
	<input class="btn" type="button" value="취소"  onClick="self_close()"id="qna_button"></center>
<center>${message}</center>
</article>

<%@ include file="../adminfooter.jsp" %>