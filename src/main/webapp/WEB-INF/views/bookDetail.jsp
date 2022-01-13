 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" /> -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<link href="static/css/datepicker.css" rel="stylesheet">
<script>
  $.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '<',
    nextText: '>',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
  });

  $(function() {
	    $("#datepicker1, #datepicker2").datepicker({
	        showOn:"button"
	            , buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif"
	            ,buttonImageOnly: true,
	  
		      	 minDate:"0", maxDate: "+1Y"

	    	 
	    });
	 

	  });
  
  
  // 객실 더보기 추가
  
  /* 팝업1 */
  $(document).ready(function( $ ){     
	    $(".openPopup1").on("click", function(event) {  //팝업오픈 버튼 누르면
	    $("#popup01").show();   //팝업 오픈
	    $("body").append('<div class="backon"></div>'); //뒷배경 생성
	    });
	   
	    $("body").on("click", function(event) { 
	    	if(event.target.className == 'close' || event.target.className == 'backon'){
	            $("#popup01").hide(); //close버튼 이거나 뒷배경 클릭시 팝업 삭제
	            $(".backon").hide();
	        }
	    });
  });
  
$(document).ready(function( $ ){     
	    $(".openPopup2").on("click", function(event) {  //팝업오픈 버튼 누르면
	    $("#popup02").show();   //팝업 오픈
	    $("body").append('<div class="backon"></div>'); //뒷배경 생성
	    });
	   
	    $("body").on("click", function(event) { 
	    	if(event.target.className == 'close' || event.target.className == 'backon'){
	            $("#popup02").hide(); //close버튼 이거나 뒷배경 클릭시 팝업 삭제
	            $(".backon").hide();
	        }
	    });
});
  
  $(document).ready(function( $ ){     
	    $(".openPopup3").on("click", function(event) {  //팝업오픈 버튼 누르면
	    $("#popup03").show();   //팝업 오픈
	    $("body").append('<div class="backon"></div>'); //뒷배경 생성
	    });
	   
	    $("body").on("click", function(event) { 
	    	if(event.target.className == 'close' || event.target.className == 'backon'){
	            $("#popup03").hide(); //close버튼 이거나 뒷배경 클릭시 팝업 삭제
	            $(".backon").hide();
	        }
	    });
});
  
  $(document).ready(function( $ ){     
	    $(".openPopup4").on("click", function(event) {  //팝업오픈 버튼 누르면
	    $("#popup04").show();   //팝업 오픈
	    $("body").append('<div class="backon"></div>'); //뒷배경 생성
	    });
	   
	    $("body").on("click", function(event) { 
	    	if(event.target.className == 'close' || event.target.className == 'backon'){
	            $("#popup04").hide(); //close버튼 이거나 뒷배경 클릭시 팝업 삭제
	            $(".backon").hide();
	        }
	    });
});
  
  $(function(){
      var num=0;
      $('#lbtn1').click(function(){
          if(num==0) return;
          num--;
          
          $('#seemore_imgview1').animate({left:-900*num},200);
      });
      $('#rbtn1').click(function(){
          if(num==3) return;
          num++;
          
          $('#seemore_imgview1').animate({left:-900*num},200);
      });
  });
  
  $(function(){
      var num=0;
      $('#lbtn2').click(function(){
          if(num==0) return;
          num--;
          
          $('#seemore_imgview2').animate({left:-900*num},200);
      });
      $('#rbtn2').click(function(){
          if(num==3) return;
          num++;
          
          $('#seemore_imgview2').animate({left:-900*num},200);
      });
  });
  
  $(function(){
      var num=0;
      $('#lbtn3').click(function(){
          if(num==0) return;
          num--;
          
          $('#seemore_imgview3').animate({left:-900*num},200);
      });
      $('#rbtn3').click(function(){
          if(num==3) return;
          num++;
          
          $('#seemore_imgview3').animate({left:-900*num},200);
      });
  });
  
  $(function(){
      var num=0;
      $('#lbtn4').click(function(){
          if(num==0) return;
          num--;
          
          $('#seemore_imgview4').animate({left:-900*num},200);
      });
      $('#rbtn4').click(function(){
          if(num==3) return;
          num++;
          
          $('#seemore_imgview4').animate({left:-900*num},200);
      });
  });
  
</script>


<div id="hotelview">
	<div id="serch_bar_view">
    <form method="post" name="formm" action="bookForm">
    <input type="hidden" id = "kind" name="kind" value="">
           <div id="serch_bar">
                  <ul>
                    <li >체크인&nbsp;&nbsp;<input type="text"  name="checkin" id="datepicker1" size="20" value="${checkin }"> </li>                  
                    <li >체크아웃&nbsp;&nbsp;<input type="text" name="checkout" id="datepicker2" size="20" value="${checkout }">  </li>
                            
                    <li>객실&nbsp;&nbsp;<input type="text" name="roomnum" size="5" id="datepicker3" value="${roomnum }"></li>
                    <li>성인&nbsp;&nbsp;<input type="text" name="usernum" size="5" id="datepicker4" value="${usernum }"></li>
                    <li><input type="submit" value="검색하기"id="serch_bar_button" onclick="return checkRoom();"></li>
                  </ul>
           </div></form>          
	</div>
	
	<hr> <!-- 방선택 뷰  Deluxe--> 
 
 <c:if test="${DBool == false && BDBool == false && GCDBool == false && EBDBool == false}">
   <div style="margin-top:250px; margin-left:370px;"><h2>남아있는 방이 없습니다.</h2></div>
   </c:if>
  <!--  ----------------- -->
	<c:if  test="${DBool == true }">
	<div id="roomview">
		<div id=room_img><img src="/static/room_images/Deluxe1.jpg"></div>
		<div id=room_content>
			<ul>
				<li>Deluxe</li>
				<li>객실크기 : 40㎡ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 위치 : 5 ~ 6층 </li>
				<li>침대 : 더블(킹) 1개 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp; 최대인원 : 2명</li>
				<li><div class="openPopup1">객실 자세히 보기</div></li>
			</ul>
		</div>
		<div id =price_view>
			<div id=price>320,000 원 ~</div>
			<div id=price2>1박</div>
		</div>
		<div id =button_view>
			<div id=button_view_div><input type="button" value="예약하기" id="book_button" onclick="book_go1('Deluxe')"></div>
		</div>
	</div>
	<hr>
	</c:if>
	
	<!-- Business Deluxe -->
	<c:if  test="${BDBool == true }">
	<div id="roomview">
		<div id=room_img><img src="/static/room_images/BusinessDeluxe1.jpg"></div>
		<div id=room_content>
			<ul>
				<li>Business Deluxe</li>
				<li>객실크기 : 56㎡ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 위치 : 5~6층</li>
				<li>침대 : 더블(킹) 2개 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp; 최대인원 : 4명</li>
				<li><div class="openPopup2">객실 자세히 보기</div></li>
			</ul>
		</div>
		<div id =price_view>
			<div id=price>390,000 원 ~</div>
			<div id=price2>1박</div>
		</div>
		<div id =button_view>
			<div id=button_view_div><input type="button" value="예약하기" id="book_button" onclick="book_go1('Business Deluxe')"></div>
		</div>
	</div>
	<hr>
	</c:if>
	
	<c:if  test="${GCDBool == true }">
	<div id="roomview">
		<div id=room_img><img src="/static/room_images/GrandCornerDeluxe1.jpg"></div>
		<div id=room_content>
			<ul>
				<li>Grand Corner Deluxe</li>
				<li>객실크기 : 72㎡ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 위치 : 7~8층</li>
				<li>침대 : 더블(킹) 3개 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp; 최대인원 : 6명</li>
				<li><div class="openPopup3">객실 자세히 보기</div></li>
			</ul>
		</div>
		<div id =price_view>
			<div id=price>460,000 원 ~</div>
			<div id=price2>1박</div>
		</div>
		<div id =button_view>
			<div id=button_view_div><input type="button" value="예약하기" id="book_button" onclick="book_go1('Grand Corner Deluxe')"></div>
		</div>
	</div>
	<hr>
	</c:if>
	
	<c:if  test="${EBDBool == true }">
	<div id="roomview">
		<div id=room_img><img src="/static/room_images/ExecuticeBusinessDeluxe1.jpg"></div>
		<div id=room_content>
			<ul>
				<li>Executive Business Deluxe</li>
				<li>객실크기 : 64㎡ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 위치 : 최상층</li>
				<li>침대 : 더블(킹) 2개 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp; 최대인원 : 4명</li>
				<li><div class="openPopup4">객실 자세히 보기</div></li>
			</ul>
		</div>
		<div id =price_view>
			<div id=price>550,000 원 ~</div>
			<div id=price2>1박</div>
		</div>
		<div id =button_view>
			<div id=button_view_div><input type="button" value="예약하기" id="book_button" onclick="book_go1('Executive Business Deluxe')"></div>
		</div>
	</div>
	<hr>
	</c:if>
	


<!-- 팝업 -->
<div id="popup01" class="popup00">
	    <div class="close">close</div>
	    <div>
	    	<div id=seemore_view>
		    	<div id=seemore_div1>
		    		<div id=seemore_imgview1 class="seemore_imgview">
						<img src="/static/room_images/Deluxe1.jpg"><img src="/static/room_images/Deluxe2.jpg">
						<img src="/static/room_images/Deluxe3.jpg"><img src="/static/room_images/Deluxe4.jpg">
					</div>
					<div id="lbtn1" class="lbtn"></div>
        			<div id="rbtn1" class="rbtn"></div>
		    	</div>
		    	<hr>
				<div id=seemore_div2>
					<div id=seemore_div2_1>
						<div id=seemore_div2_1left>
							객실이용
						</div>
						<div id=seemore_div2_1right>
							<ul>
								<li>위치 : 5~6층 &nbsp;&nbsp;전망 : 산 또는 바다</li>
                        		<li>침대 : 1&nbsp;&nbsp; 인원 : 4 &nbsp;크기 : 40㎡</li>
								<li>55인치 스마트 TV(위성 TV 48개 채널)</li>
								<li>50~100Mbps 초고속 유·무선(wifi) 인터넷 무</li>
								<li>220V, 110V 전압 사용 가능</li>
								<li>커피·차 티백 무료 제공</li>
							</ul>
						</div>
					</div>
					<hr>
					<div id=seemore_div2_2>
						<div id=seemore_div2_2left>
							부대시설
						</div>
						<div id=seemore_div2_2right>
							<ul>
								<li>피트니스 클럽 이용 안내</li>
								<li>- 체육관(Gym) 무료 이용(만 16세 이상 입장 가능)</li>
								<li>- 실내 수영장 무료 이용(만 13세 이상 입장 가능)</li>
								<li>&nbsp;&nbsp;&nbsp;(단, 주말 및 공휴일에는 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;만 13세 미만인 고객이 이용 가능하며,<br>
									&nbsp;&nbsp;&nbsp;신장 140cm 미만인 고객은 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;구명조끼 착용 시 이용 가능)
								</li>
								<li>- 사우나 이용 시 유료(만 13세 이상 입장 가능)</li>
								<li>- 피트니스 클럽은 매월 세 번째 수요일 정기휴일<br></li>
								<li>투숙 기간 내 무료 주차 가능</li>
							</ul>
						</div>
					</div>
				</div>
				<div id=seemore_div3>
				<ul>
					<li>조식 이용 안내</li>
						<li>&nbsp;&nbsp;- 더 파크뷰 06:00~10:00(주중/주말/공휴일)</li>
						<li>&nbsp;&nbsp;- 더 라이브러리 08:00~11:30</li>
						<li>&nbsp;&nbsp;&nbsp;※ 현재 더 라이브러리는 코로나19로 인한 운영시간<br>
							&nbsp;&nbsp;&nbsp;&nbsp;축소로 조식 운영을 하지 않고 있사오니 이용에<br>
							&nbsp;&nbsp;&nbsp;&nbsp;참고 부탁드립니다.</li>
					<li><br>체크인/체크아웃 시간</li>
						<li>&nbsp;&nbsp;- 체크인 : 오후 2시 이후</li>
						<li>&nbsp;&nbsp;- 체크아웃 : 오전 11시까지</li>
				
					<li><br>취소/변경 및 노쇼(No-show) 안내</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시까지는 위약금 없음</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시 이후 취소/변경/노쇼 발생 시</li>
					
						<li><br>&nbsp;&nbsp;성수기(5월~10월, 12월 24일~31일)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 80%가 위약금으로 부과</li>
						<li><br>&nbsp;&nbsp;비수기(성수기 외 기간)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 10%가 위약금으로 부과</li>
				</ul>
				</div>
			</div>
	    </div>
</div>

<div id="popup02" class="popup00">
	    <div class="close">close</div>
	    <div>
	    	<div id=seemore_view>
		    	<div id=seemore_div1>
		    		<div id=seemore_imgview2 class="seemore_imgview">
						<img src="/static/room_images/BusinessDeluxe1.jpg"><img src="/static/room_images/BusinessDeluxe2.jpg">
						<img src="/static/room_images/BusinessDeluxe3.jpg"><img src="/static/room_images/BusinessDeluxe4.jpg">
					</div>
					<div id="lbtn2" class="lbtn"></div>
        			<div id="rbtn2" class="rbtn"></div>
		    	</div>
		    	<hr>
				<div id=seemore_div2>
					<div id=seemore_div2_1>
						<div id=seemore_div2_1left>
							객실이용
						</div>
						<div id=seemore_div2_1right>
							<ul>
								<li>위치 : 5~6층 &nbsp;&nbsp;전망 : 산 또는 바다</li>
                    		    <li>침대 : 2&nbsp;&nbsp; 인원 : 4 크기 : 56㎡</li>
								<li>55인치 스마트 TV(위성 TV 48개 채널)</li>
								<li>50~100Mbps 초고속 유·무선(wifi) 인터넷 무</li>
								<li>220V, 110V 전압 사용 가능</li>
								<li>커피·차 티백 무료 제공</li>
							</ul>
						</div>
					</div>
					<hr>
					<div id=seemore_div2_2>
						<div id=seemore_div2_2left>
							부대시설
						</div>
						<div id=seemore_div2_2right>
							<ul>
								<li>피트니스 클럽 이용 안내</li>
								<li>- 체육관(Gym) 무료 이용(만 16세 이상 입장 가능)</li>
								<li>- 실내 수영장 무료 이용(만 13세 이상 입장 가능)</li>
								<li>&nbsp;&nbsp;&nbsp;(단, 주말 및 공휴일에는 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;만 13세 미만인 고객이 이용 가능하며,<br>
									&nbsp;&nbsp;&nbsp;신장 140cm 미만인 고객은 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;구명조끼 착용 시 이용 가능)
								</li>
								<li>- 사우나 이용 시 유료(만 13세 이상 입장 가능)</li>
								<li>- 피트니스 클럽은 매월 세 번째 수요일 정기휴일<br></li>
								<li>투숙 기간 내 무료 주차 가능</li>
							</ul>
						</div>
					</div>
				</div>
				<div id=seemore_div3>
				<ul>
					<li>조식 이용 안내</li>
						<li>&nbsp;&nbsp;- 더 파크뷰 06:00~10:00(주중/주말/공휴일)</li>
						<li>&nbsp;&nbsp;- 더 라이브러리 08:00~11:30</li>
						<li>&nbsp;&nbsp;&nbsp;※ 현재 더 라이브러리는 코로나19로 인한 운영시간<br>
							&nbsp;&nbsp;&nbsp;&nbsp;축소로 조식 운영을 하지 않고 있사오니 이용에<br>
							&nbsp;&nbsp;&nbsp;&nbsp;참고 부탁드립니다.</li>
					<li><br>체크인/체크아웃 시간</li>
						<li>&nbsp;&nbsp;- 체크인 : 오후 2시 이후</li>
						<li>&nbsp;&nbsp;- 체크아웃 : 오전 11시까지</li>
				
					<li><br>취소/변경 및 노쇼(No-show) 안내</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시까지는 위약금 없음</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시 이후 취소/변경/노쇼 발생 시</li>
					
						<li><br>&nbsp;&nbsp;성수기(5월~10월, 12월 24일~31일)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 80%가 위약금으로 부과</li>
						<li><br>&nbsp;&nbsp;비수기(성수기 외 기간)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 10%가 위약금으로 부과</li>
				</ul>
				</div>
			</div>
	    </div>
</div>


<div id="popup03" class="popup00">
	    <div class="close">close</div>
	    <div>
	    	<div id=seemore_view>
		    	<div id=seemore_div1>
		    		<div id=seemore_imgview3 class="seemore_imgview">
						<img src="/static/room_images/GrandCornerDeluxe1.jpg"><img src="/static/room_images/GrandCornerDeluxe2.jpg">
						<img src="/static/room_images/GrandCornerDeluxe3.jpg"><img src="/static/room_images/GrandCornerDeluxe4.jpg">
					</div>
					<div id="lbtn3" class="lbtn"></div>
        			<div id="rbtn3" class="rbtn"></div>
		    	</div>
		    	<hr>
				<div id=seemore_div2>
					<div id=seemore_div2_1>
						<div id=seemore_div2_1left>
							객실이용
						</div>
						<div id=seemore_div2_1right>
							<ul>
								<li>위치 : 7~8층 &nbsp;&nbsp;전망 : 바다&nbsp;&nbsp; 크기 : 72㎡</li>
                   			    <li>침대 : 3,&nbsp;&nbsp; 인원 : 6 </li>
								<li>55인치 스마트 TV(위성 TV 48개 채널)</li>
								<li>50~100Mbps 초고속 유·무선(wifi) 인터넷 무</li>
								<li>220V, 110V 전압 사용 가능</li>
								<li>커피·차 티백 무료 제공</li>
							</ul>
						</div>
					</div>
					<hr>
					<div id=seemore_div2_2>
						<div id=seemore_div2_2left>
							부대시설
						</div>
						<div id=seemore_div2_2right>
							<ul>
								<li>피트니스 클럽 이용 안내</li>
								<li>- 체육관(Gym) 무료 이용(만 16세 이상 입장 가능)</li>
								<li>- 실내 수영장 무료 이용(만 13세 이상 입장 가능)</li>
								<li>&nbsp;&nbsp;&nbsp;(단, 주말 및 공휴일에는 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;만 13세 미만인 고객이 이용 가능하며,<br>
									&nbsp;&nbsp;&nbsp;신장 140cm 미만인 고객은 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;구명조끼 착용 시 이용 가능)
								</li>
								<li>- 사우나 이용 시 유료(만 13세 이상 입장 가능)</li>
								<li>- 피트니스 클럽은 매월 세 번째 수요일 정기휴일<br></li>
								<li>투숙 기간 내 무료 주차 가능</li>
							</ul>
						</div>
					</div>
				</div>
				<div id=seemore_div3>
				<ul>
					<li>조식 이용 안내</li>
						<li>&nbsp;&nbsp;- 더 파크뷰 06:00~10:00(주중/주말/공휴일)</li>
						<li>&nbsp;&nbsp;- 더 라이브러리 08:00~11:30</li>
						<li>&nbsp;&nbsp;&nbsp;※ 현재 더 라이브러리는 코로나19로 인한 운영시간<br>
							&nbsp;&nbsp;&nbsp;&nbsp;축소로 조식 운영을 하지 않고 있사오니 이용에<br>
							&nbsp;&nbsp;&nbsp;&nbsp;참고 부탁드립니다.</li>
					<li><br>체크인/체크아웃 시간</li>
						<li>&nbsp;&nbsp;- 체크인 : 오후 2시 이후</li>
						<li>&nbsp;&nbsp;- 체크아웃 : 오전 11시까지</li>
				
					<li><br>취소/변경 및 노쇼(No-show) 안내</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시까지는 위약금 없음</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시 이후 취소/변경/노쇼 발생 시</li>
					
						<li><br>&nbsp;&nbsp;성수기(5월~10월, 12월 24일~31일)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 80%가 위약금으로 부과</li>
						<li><br>&nbsp;&nbsp;비수기(성수기 외 기간)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 10%가 위약금으로 부과</li>
				</ul>
				</div>
			</div>
	    </div>
</div>

<div id="popup04" class="popup00">
	    <div class="close">close</div>
	    <div>
	    	<div id=seemore_view>
		    	<div id=seemore_div1>
		    		<div id=seemore_imgview4 class="seemore_imgview">
						<img src="/static/room_images/ExecuticeBusinessDeluxe1.jpg"><img src="/static/room_images/ExecuticeBusinessDeluxe2.jpg">
						<img src="/static/room_images/ExecuticeBusinessDeluxe3.jpg"><img src="/static/room_images/ExecuticeBusinessDeluxe4.jpg">
					</div>
					<div id="lbtn4" class="lbtn"></div>
        			<div id="rbtn4" class="rbtn"></div>
		    	</div>
		    	<hr>
				<div id=seemore_div2>
					<div id=seemore_div2_1>
						<div id=seemore_div2_1left>
							객실이용
						</div>
						<div id=seemore_div2_1right>
							<ul>
								<li>위치 : 최상층 &nbsp;&nbsp;전망 : 바다 크기 : 68㎡ </li>
                       			<li>침대 : 2 &nbsp;&nbsp;인원 : 4 </li>
								<li>55인치 스마트 TV(위성 TV 48개 채널)</li>
								<li>50~100Mbps 초고속 유·무선(wifi) 인터넷 무</li>
								<li>220V, 110V 전압 사용 가능</li>
								<li>커피·차 티백 무료 제공</li>
							</ul>
						</div>
					</div>
					<hr>
					<div id=seemore_div2_2>
						<div id=seemore_div2_2left>
							부대시설
						</div>
						<div id=seemore_div2_2right>
							<ul>
								<li>피트니스 클럽 이용 안내</li>
								<li>- 체육관(Gym) 무료 이용(만 16세 이상 입장 가능)</li>
								<li>- 실내 수영장 무료 이용(만 13세 이상 입장 가능)</li>
								<li>&nbsp;&nbsp;&nbsp;(단, 주말 및 공휴일에는 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;만 13세 미만인 고객이 이용 가능하며,<br>
									&nbsp;&nbsp;&nbsp;신장 140cm 미만인 고객은 성인 보호자의 보호 하에<br>
									&nbsp;&nbsp;&nbsp;구명조끼 착용 시 이용 가능)
								</li>
								<li>- 사우나 이용 시 유료(만 13세 이상 입장 가능)</li>
								<li>- 피트니스 클럽은 매월 세 번째 수요일 정기휴일<br></li>
								<li>투숙 기간 내 무료 주차 가능</li>
							</ul>
						</div>
					</div>
				</div>
				<div id=seemore_div3>
				<ul>
					<li>조식 이용 안내</li>
						<li>&nbsp;&nbsp;- 더 파크뷰 06:00~10:00(주중/주말/공휴일)</li>
						<li>&nbsp;&nbsp;- 더 라이브러리 08:00~11:30</li>
						<li>&nbsp;&nbsp;&nbsp;※ 현재 더 라이브러리는 코로나19로 인한 운영시간<br>
							&nbsp;&nbsp;&nbsp;&nbsp;축소로 조식 운영을 하지 않고 있사오니 이용에<br>
							&nbsp;&nbsp;&nbsp;&nbsp;참고 부탁드립니다.</li>
					<li><br>체크인/체크아웃 시간</li>
						<li>&nbsp;&nbsp;- 체크인 : 오후 2시 이후</li>
						<li>&nbsp;&nbsp;- 체크아웃 : 오전 11시까지</li>
				
					<li><br>취소/변경 및 노쇼(No-show) 안내</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시까지는 위약금 없음</li>
						<li>&nbsp;&nbsp;- 숙박 예정일 1일 전 18시 이후 취소/변경/노쇼 발생 시</li>
					
						<li><br>&nbsp;&nbsp;성수기(5월~10월, 12월 24일~31일)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 80%가 위약금으로 부과</li>
						<li><br>&nbsp;&nbsp;비수기(성수기 외 기간)</li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;-> 최초 1일 숙박 요금의 10%가 위약금으로 부과</li>
				</ul>
				</div>
			</div>
	    </div>
</div>

	
	
</div>



<%@ include file="footer.jsp" %>