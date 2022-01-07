<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../adminheader.jsp" %>

<script type="text/javascript">
function go_memberlist_member(){
	document.adminmemberDetail.action = "adminMemberList?page=1";
	document.adminmemberDetail.submit();
}

function okButton_member(id){
	 var url="adminMemberUpdate?id=" + id;
	 document.adminmemberDetail.action=url;
	 document.adminmemberDetail.submit();
}
</script>
<style>
#adminmemebrlist{
    border: 2px solid #9F876B;
    margin: 0 auto;
    width: 400px;
    height:200px;
    border-collapse: collapse;
    
}
#adminmemebrlist tr th{
border-bottom:1px solid black;
}
#adminmemebrlist td{
border-bottom:1px solid black;
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


<form method="post" name="adminmemberDetail" action="adminMemberUpdate">

<table  id="adminmemebrlist" style="center;">

<caption>
   <h1>우리 호텔 ${dto.name}(${dto.id})님 회원 정보</h1>
</caption>
           <tr><th>아이디&nbsp;&nbsp;:</th><td style="text-align: left;">${dto.id}</td></tr>
			<tr><th>이름&nbsp;&nbsp;:</th><td>${dto.name}</td></tr>
			<tr><th>이메일&nbsp;&nbsp;:</th><td><input type="text" size="30" name="email" value="${dto.email}"></td></tr>
			<tr><th>전화번호&nbsp;&nbsp;:</th><td><input type="text" size="20" name="phone" value="${dto.phone}"></td></tr>	
			<tr><th rowspan="3">주소</th><td><input type="text" name="zip_num" size="10" value="${dto.zip_num}">      
	   		<input type="button" value="주소 찾기" class="dup_brown" onclick="post_zip();"></td></tr>
			<tr><td><input type="text" name="addr1"   size="50" value="${addr1}"></td></tr>
			<tr><td><input type="text" name="addr2"   size="25" value="${addr2}"></td></tr>
			
			<input type="hidden" name="name" value="${dto.name}">
			<input type="hidden" name="pwd" value="${dto.pwd}">
</table>

		<center><div id="profText">&nbsp;&nbsp;&nbsp;${message}</div></center>
		<div id="profButton"  style="margin:0 auto;
			width:1000px; border:1px soloid black; text-align: center;">
			<input class="btn" type="button" name="btb_total" value="목록으로" 
  				onclick="go_memberlist_member();"id="qna_button">
			<input type="button" value="수정하기" 
				onclick="okButton_member('${dto.id}')" id="qna_button">
		</div>
</form>

</article>


<%@ include file="../adminfooter.jsp" %>