<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../adminheader.jsp" %>
<script type="text/javascript">
function go_total_room(){
	document.frm.key.value="";
	document.frm.action="adminRoomList?a=1";
	document.frm.submit();
}

function go_search_room(){
	if(document.frm.key.value=="") return;
	var url="adminRoomList?page=1";
	// 보던페이지가어떤에피이지 이더라도,검색갤겨의 1페이로 가기위해 파라미터page를 1로 전송.,
	document.frm.action=url;
	document.frm.submit();
}

function go_roomview_detail(hotelnum) {
		 var url="adminRoomDetail?hotelnum="+hotelnum;
		 var opt = "toolbar=no,menubar=no,scrollbars=no,resizable=no,width=700,";
			opt = opt + "height=600, top=300, left=300";
			window.open(url, "객실 정보", opt); 
	}

function delete_room(hotelnum){
	var url="adminRoomDelete?hotelnum="+hotelnum;
	document.frm.action=url;
	document.frm.submit();
}

function insert_room(){
		var url="adminRoomInsertForm";
		document.frm.action=url;
		document.frm.submit();
}
</script>

</script>
<style>
#adminmemebrlist{ margin:0 auto;     border-collapse: collapse;
    border-collapse: collapse;
width:1000px;  border-bottom:1px solid #999;   color:#666; font-size:12px; table-layout:fixed;
margin:0 auto;
border:1

}

#adminmemebrlist tr th{
padding:5px 0 6px;  border-top: solid 1px #999;   border-bottom: solid 1px #b2b2b2; background-color:#9F876B; color: #333; font-weight: bold;  
line-height: 10px width:300px;
}
#adminmemebrlist tr td{
padding: 8px 0 6px; border-top:solid 1px #999; background-color:white; color:#333; font-weight: bold; vertical-align: top;
line-height: 30px;  
}
#qna_button{
border:1px solid black;
 height: 36px;
    width: 80px;
    font-weight: bold;
    background: #9F876B;
    font-size: 65%;
}
</style>

<div  class="clear"></div>
<article >
<h1>객실 리스트</h1>
<center>${message}</center>
	<form name="frm" method="post">
		<table style="margin:0 auto; 
	width:1000px; border:1px soloid black; text-align: center; margin-right: 140px;font-weight:bold;">
			<tr>
                <td width="642">객실번호<input type="text" name="key" value="${key}">
				 <input class="btn" type="button" name="btn_search" value="검색" onclick="go_search_room();" id="qna_button">
				 <input class="btn" type="button" name="btn_total" value="전체보기 " onclick="go_total_room();" id="qna_button">	
				 <input class="btn" type="button" name="btn_total" value="객실추가 " onclick="insert_room();" id="qna_button">
            	</td>	
			</tr>
		</table>
	
	<center><table id="adminmemebrlist" style="center;">
		<tr><th>이미지</th><th>객실번호</th><th>종류</th><th>인원</th><th>가격</th><th>방크기</th>
			<th>객실삭제</th></tr>
		<c:choose>
	         <c:when test='${empty roomViewList}'>
	            <tr>
	               <td width="100%" colspan="7" align="center" height="23">등록된
	                  상품이 없습니다.</td> </tr>
			  </c:when>
	        <c:otherwise>  
				<c:forEach items="${roomViewList}" var="roomViewDto">
					<tr>
		                <td>
		                	<a href="#" onClick= "go_roomview_detail('${roomViewDto.hotelnum}')">
		                	<img src="../static/images/${roomViewDto.img}" width="200pt"></a></td>
		                <td height="23" align="center" >
		                	<a href="#" onClick= "go_roomview_detail('${roomViewDto.hotelnum}')">
		                	${roomViewDto.hotelnum}</a></td>
		                <td height="23" align="center" >
		                	<a href="#" onClick= "go_roomview_detail('${roomViewDto.hotelnum}')">
		                	${roomViewDto.kind}</a></td>
		                <td height="23" align="center" >
		                	<a href="#" onClick= "go_roomview_detail('${roomViewDto.hotelnum}')">
		                	${roomViewDto.persons}</a></td>
		                <td height="23" align="center" >
		                	<a href="#" onClick= "go_roomview_detail('${roomViewDto.hotelnum}')">
		                	${roomViewDto.price}</a></td>
		                <td height="23" align="center" >
		                	<a href="#" onClick= "go_roomview_detail('${roomViewDto.hotelnum}')">
		                	${roomViewDto.roomsize}</a></td>
		                <td> <center><input class="btn" type="button" name="btn_search" value="삭제" onclick="delete_room('${roomViewDto.hotelnum}');" id="qna_button"></center></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table></center>
</form>
	<br>
<jsp:include page="../paging/paging.jsp">
	<jsp:param name="page" value="${paging.page}" />
	<jsp:param name="beginPage" value="${paging.beginPage}" />
	<jsp:param name="endPage" value="${paging.endPage}" />
	<jsp:param name="prev" value="${paging.prev}" />
	<jsp:param name="next" value="${paging.next}" />
	<jsp:param name="command" value="adminRoomList" />
</jsp:include>
	
		
</article>








<%@ include file="../adminfooter.jsp" %>