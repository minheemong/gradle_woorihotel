<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


<article  class="rightarticle_aa"><%@ include file="sub_menu.jsp" %>
<form action="bookcancel" name="frm">
<input type="hidden" name="bdseq" value="${bookcheck.bdseq }">
<input type="hidden" id="result" value="${bookcheck.result }">
<input type="hidden" name="checkin" value="${bookcheck.checkin }">
<input type="hidden" name="checkout" value="${bookcheck.checkout }">
<input type="hidden" name="price" value="${bookcheck.price }">
<div class="bbox_aa">
<div id="bboxb_aa">
	<div id="bcheck_aa">예약 확인</div>
	<div id="bigctextbox_aa">
		<div class="smallctextbox_aa">
			<div class="ctextsub_aa"> &nbsp;이름</div>
			<div class="ctexttext_aa">${bookcheck.name}</div>
		</div>
		<div class="smallctextbox_aa">
			<div class="ctextsub_aa">&nbsp;예약 번호</div>
			<div class="ctexttext_aa">${bookcheck.booknum}</div>
		</div>
		<div class="smallctextbox_aa">
			<div class="ctextsub_aa">&nbsp;객실</div>
			<div class="ctexttext_aa">${bookcheck.kind}</div>
		</div>
		<div class="smallctextbox_aa">
			<div class="ctextsub_aa">&nbsp;인원</div>
			<div class="ctexttext_aa">${bookcheck.usernum}</div>
		</div>
		<div class="smallctextbox_aa">
			<div class="ctextsub_aa">&nbsp;체크인</div>
			<div class="ctexttext_aa"><fmt:formatDate value="${bookcheck.checkin}"/></div>
		</div>
		<div class="smallctextbox_aa">
			<div class="ctextsub_aa">&nbsp;체크아웃</div>
			<div class="ctexttext_aa"><fmt:formatDate value="${bookcheck.checkout}"/></div>
		</div>
		<div class="smallctextbox_aa">
			<div class="ctextsub_aa">&nbsp;결제 금액</div>
			<div class="ctexttext_aa">${bookcheck.price}</div>
		</div>
		
	</div>
	
	<div id="buttons_aa">
	<input type="button" value="목록으로" class="btn_aa" 
		onclick="history.go(-1)">
	<c:choose>
		<c:when test='${bookcheck.result=="0"}'>
			<input type="submit" value="예약 취소" class="btn_aa" name="bookCancelBtn"
				onclick="return bookCancelCheck();">
		</c:when>
		<c:when test='${bookcheck.result=="1"}'>
			<input type="submit" value="예약 취소" class="btn_aa" name="bookCancelBtn"
				onclick="return bookCancelCheck();">
		</c:when>
	</c:choose>
</div> 
	
</div>


</div>
</form>
</article>
<%@ include file="../footer.jsp" %>