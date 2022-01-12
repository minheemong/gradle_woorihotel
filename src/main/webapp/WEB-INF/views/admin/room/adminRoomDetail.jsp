<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
 <script type="text/javascript">
 function selectFile(){
		var opt = "toolbar=no,menubar=no,resizable=no,width=450,height=200";
		window.open( 'selectimg' , 'selectimg',  opt);
}
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


<form method="post" name="formm">

<table  id="adminmemebrlist" style="center; width: 500px;">
	<tr><th>객실번호</th><td>${dto.hotelnum}</td></tr>
	<tr><th>종류</th><td><input type="text" size="30" name="kind" value="${dto.kind}"></td></tr>
	<tr><th>인원</th><td><input type="text" size="30" name="persons" value="${dto.persons}"></td></tr>
	<tr><th>가격</th><td><input type="text" size="30" name="price" value="${dto.price}"></td></tr>
	<tr><th>방크기</th> <td><input type="text" size="30" name="roomsize" value="${dto.roomsize}"></td></tr>
	<tr><th>이미지</th> <td><img src="../static/images/${dto.img}" width="200pt">
	      	<div id="imageName" style="float:left; font-size:120%; margin-right:20px; font-whight:bold"></div><Br>
			<input type="button" value="이미지 선택" onclick="selectFile();"><br>
	      	<input type="hidden" name="imagefilename"> *주의 : 이미지를 수정할 때에만 선택해주세요
	   </td></tr>
</table>

	<center><input class="btn" type="button" value="수정" onClick=
	"location.href='adminRoomUpdate?hotelnum=${dto.hotelnum}'"id="qna_button">           
	<input class="btn" type="button" value="취소"  onClick="self_close()"id="qna_button"></center>
<center>${message}</center>
</article>