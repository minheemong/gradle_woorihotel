<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
  <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
<script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
   
<style>
article{
width: 800px;height: 510px; margin: 0 auto;
background: #9F876B;;
}
 
      .swiper {
        width: 100%;
        height: 100%;
      }

      .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;

        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
      }

      .swiper-slide img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
      }

 

      .swiper {
        width: 100%;
        height: 300px;
        margin-left: auto;
        margin-right: auto;
      }

      .swiper-slide {
        background-size: cover;
        background-position: center;
      }

      .mySwiper2 {
        height: 80%;
        width: 100%;
      }

      .mySwiper {
        height: 20%;
        box-sizing: border-box;
        padding: 10px 0;
      }

      .mySwiper .swiper-slide {
        width: 25%;
        height: 100%;
        opacity: 0.4;
      }

      .mySwiper .swiper-slide-thumb-active {
        opacity: 1;
      }

      .swiper-slide img {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
      }
      
      #sum_aa1{position:relative;  height:47px; border-bottom:6px solid #3a3125; 
	font-size:170%; line-height:64px;/* background:aqua; */
	
	}
   </style>
</head>

<body>

<article>
<div><c:forEach items="${imglist}" var="imglist">
<div id="sum_aa1" style="width: 800px; margin: 0 auto;">
${imglist.kind} 객실 사진</div></c:forEach>
<div style="  margin: 0 auto; width: 97%;"> 

  <div
      style="--swiper-navigation-color: #fff; --swiper-pagination-color: #fff"
      class="swiper mySwiper2"
    >
      <div class="swiper-wrapper">
      <c:forEach items="${imglist}" var="imglist">
        <div class="swiper-slide">
        
          				<img src="static/room_images/${imglist.img1}.jpg" />
        </div>
       
               <div class="swiper-slide">
       					   <img src="static/room_images/${imglist.img2}.jpg" />
        </div>
               <div class="swiper-slide">
        					  <img src="static/room_images/${imglist.img3}.jpg" />
        </div>
               <div class="swiper-slide">
       							   <img src="static/room_images/${imglist.img4}.jpg" />
      </div>
        </c:forEach>
      
      </div>
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
    </div>
    <div thumbsSlider="" class="swiper mySwiper">
      <div class="swiper-wrapper">
        <c:forEach items="${imglist}" var="imglist">
        <div class="swiper-slide">
         	<img src="static/room_images/${imglist.img1}.jpg" />
        </div>
        <div class="swiper-slide">
         	<img src="static/room_images/${imglist.img2}.jpg" />
        </div>
        <div class="swiper-slide">
       	<img src="static/room_images/${imglist.img3}.jpg" />
        </div>
        <div class="swiper-slide">
         	<img src="static/room_images/${imglist.img4}.jpg" />
        </div>
     </c:forEach>
      </div>
    </div>

    <!-- Swiper JS -->
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

    <!-- Initialize Swiper -->
    <script>
      var swiper = new Swiper(".mySwiper", {
        spaceBetween: 10,
        slidesPerView: 4,
        freeMode: true,
        watchSlidesProgress: true,
      });
      var swiper2 = new Swiper(".mySwiper2", {
        spaceBetween: 10,
        navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
        },
        thumbs: {
          swiper: swiper,
        },
      });
    </script>

</div>


</div>


</article>
</body>
</html>


