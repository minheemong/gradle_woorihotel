<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../header.jsp" %>

<style>
#map{
margin: 0 auto; top:30px; border:2px solid blck;     left: -150px;
}
</style>

<article class="rightarticle_aa"><%@ include file="sub_menu.jsp" %>


<div id="sum_aa"  style="float: left;">우리호텔 오시는길</div>

<div id="map" style="width:865px;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7fd0b24380bb5d47fd4d9a1e2248ca15"></script>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.247423134591976, 126.40804197851352), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(33.247423134591976, 126.40804197851352); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

var iwContent = '<div style="padding:5px;">길찾기! <br><a href="https://map.kakao.com/link/map/제주신라호텔!,33.247423134591976, 126.40804197851352" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.247423134591976, 126.40804197851352" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(33.450701, 126.570667); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position : iwPosition, 
    content : iwContent 
});
  
// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(map, marker); 


</script>
<div class="clear"></div>
<div class="box_aa" id="box3_aa" style="left:350px; margin-top: 50px; height: 140px; float: left;">
		<ul class="add_aa" id="name_aa"   style="  margin: 29px 5px;">
			<li>주소</li>
			<li>대표전화</li>
	
		</ul>
		<ul class="add_aa" id="content_aa" style="  margin: 30px 5px;">
			<li>서귀포시 중문 관광로 72번길75</li>
			<li>064 2233 3131</li>
		
		</ul>
	</div>

</article>
<div  class="clear"></div>
<%@ include file="../footer.jsp" %>