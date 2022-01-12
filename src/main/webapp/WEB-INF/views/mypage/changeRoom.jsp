<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="static/css/Hotel.css" rel="stylesheet">

<style type="text/css">
	body {padding:0; margin:0;}
	ul {position:relative; list-style:none; padding:0;}
	li {position:relative;}
	#chboxb_aa{position:relative; width:500px; min-height:250px;/*  background:fuchsia; */
	padding-bottom:15px; float:left;}
	#chcheck_aa{position:relative; width:495px; min-height:65px;  margin:0 auto;
	border-bottom:5px solid #9f876b; font-size:150%; font-weight:bold; line-height:65px;
	letter-spacing:1px; padding:3px 0; /* width:600px; height:70px; */
	/* background:olive; */ background:#faf1d0;}
</style>

<script type="text/javascript">
	function roomChangeCheck(){
		if(document.formm.number.value>document.formm.max.value){
			alert('최대 인원 '+document.formm.max.value+'명을 초과하였습니다.');
			return;
		}
		
		if(window.confirm(document.formm.number.value+"명으로 예약을 변경하시겠습니까?")){
			opener.location.href="gotochangeroom?bdseq="+document.formm.bdseq.value+
					"&number="+document.formm.number.value;
			self.close();
		}
	}
</script>

</head>
<body>

<form method="post" name="formm" action="gotochangeroom">

<div id="chboxb_aa">
	<div id="chcheck_aa">인원 변경</div>
	<ul>
		<li>${bookcheck.kind} 최대 인원(${max })명</li>
		<li><input type="number" name="number" max="${max }"></li>
		
	</ul>
	<input type="hidden" name="bdseq" value="${bookcheck.bdseq }">
	<input type="hidden" name="max" value="${max }">
	
	<input type="button" value="수정" onclick="roomChangeCheck();" id="btn2_aa">
</div>

</form>

</body>
</html>