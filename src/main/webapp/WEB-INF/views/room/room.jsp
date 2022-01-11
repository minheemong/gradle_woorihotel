<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %><%@ include file="sub_menu.jsp" %>
<style>
#room_view{
width: 1100px;
margin: 0 auto;
height: 700px
}
#room_top{

width: 1100px;
height:  70px;
font-weight:  bold;

}
.room_content{
    float: left;
    width: 1000px;  
    height: 700px;
    margin-top: 30px;
}

.room_1 {
    width: 500px;
    float: left;
   
}
.roomcontent_view{
height: 100px;
    width: 400px;
}
.roomcotent123{
idth: 200px;
    height: 50px;
}
.roomcotent123 h4,p{
margin:0;
}
.room_content_view{
}
.room_contetn_btn{
    position: relative;
    top: -50px;
    width: 90px;
    left: 300px;
}

.room_btn{
background-color: #F1EBD6;
}

}
.room_btn2{
background-color:#9F876B; 
width: 82.66px;
}
</style>
<script type="text/javascript">

function gotobook(kind){
	 var url="gotobook?kind="+kind
	 var opt = "toolbar=no,menubar=no,scrollbars=no,resizable=no,width=810,height=470, top=300, left=300";
	window.open(url, "객실 예약하기", opt);
	
}

function gotoimgshow(num){

 	 var url="gotoimgshow?num="+num;
	 var opt = "toolbar=no,menubar=no,scrollbars=no,resizable=no,width=810,height=470, top=300, left=300";
	window.open(url, "객실 사진", opt); 
	
}


</script>
<article>
<div id="room_view">  

			<div id="room_top">
					<h4> 내집과같은편안함으로 공간이사으이 가치가 숨쉬고 있는 제주신라호텔객식</h4>
					<h6>
					비즈니스를 위한 최고의 서비스와 프리미엄 베드가 말할수없이 편안함 감촉 한국적인센스가 
					담긴 최고의 뷰와 휴양지의 안락함까지 세계명문호텘에서만 느낄수있는 수준높은 브랜드를 만나보시기 바랍니다</h6>
			</div>
			
			<div class="room_content">
									<div class="room_1">
														<div>
															<a href="gotoimgshow?num=1"><img  src="static/room_images/Deluxe1.jpg" height="200px" width="400px"></a>
															
														</div>
													
														<div class="roomcontent_view">
																		<div class="roomcotent123">
																					<h4>Deluxe</h4>
																					<p>고급스러운 매력을지닌 디럭스 객실</p>	
																		</div>
																		 <div class="room_contetn_btn"> 
																				 <input type="button" onClick ="gotoimgshow(1)" value="이미지 보기" class="room_btn">
																				 <input type="button" value="예약하기"   onClick="gotobook('Deluxe');" style="background-color:#9F876B; width: 85.66px;">
																		 </div>
														</div>											
								</div>
								
								<div class="room_1">
														<div>
													<a href="gotoimgshow?num=2"><img  src="static/room_images/BusinessDeluxe1.jpg" height="200px" width="400px"></a>
														</div>
													
														<div class="roomcontent_view">
																		<div class="roomcotent123">
																					<h4>BusinessDeluxe</h4>
																					<p>고급스러운 매력을지닌 디럭스 객실</p>		
																		</div>
																		 <div class="room_contetn_btn">  
																				 <input type="button" onClick ="gotoimgshow(2)" value="이미지 보기" class="room_btn">
																				 <input type="button"  value="예약하기"  onClick="gotobook('Business Deluxe');" style="background-color:#9F876B; width: 85.66px;">
																		 </div>
														</div>											
								</div>
									<div class="room_1">
														<div>
													<a href="gotoimgshow?num=3"><img  src="static/room_images/GrandCornerDeluxe1.jpg" height="200px" width="400px"></a>
														</div>
													
														<div class="roomcontent_view">
																		<div class="roomcotent123">
																					<h4>GrandCornerDeluxe</h4>
																					<p>고급스러운 매력을지닌 디럭스 객실</p>		
																		</div>
																		 <div class="room_contetn_btn"> 
																				 <input type="button" onClick ="gotoimgshow(3)" value="이미지 보기" class="room_btn">
																				 <input type="button" value="예약하기" onClick="gotobook('Grand Corner Deluxe');" style="background-color:#9F876B; width: 85.66px;">
																		 </div>
														</div>											
								</div>
								<div class="room_1">
														<div>
														<a href="gotoimgshow?num=4">	<img  src="static/room_images/ExecuticeBusinessDeluxe1.jpg" height="200px" width="400px"></a>
														</div>
													
														<div class="roomcontent_view" >
																		<div class="roomcotent123">
																					<h4>ExecuticeBusinessDeluxe</h4>
																					<p>고급스러운 매력을지닌 디럭스 객실</p>			
																		</div>
																		<div class="room_contetn_btn">  
																				 <input type="button" onClick ="gotoimgshow(4)" value="이미지 보기" class="room_btn">
																				 <input type="button"  value="예약하기" onClick="gotobook('Executive Business Deluxe');" style="background-color:#9F876B; width: 85.66px;">
																		 </div>
														</div>											
								</div>
		</div>
			
</div>
			
			
			









</article>





<%@ include file="../footer.jsp" %>