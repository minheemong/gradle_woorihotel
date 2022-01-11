<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<style>

#room_menu{

    width: 250px;
    float: left;
    min-height: 300px;
    margin: 20px 60px;
    /* background: yellowgreen; */
    background: #faf1d0;
    }

    #room_aa{
        font-size: 150%;
    width: 230px;
    /* width: 250px; */
    color: #3a3125;
    border-bottom: 3px solid #3a3125;
    list-style: none;
    margin-left: -30px;
    
}
    

#room_li{
margin: 10px 15px; */
    text-align: center;
    width: 220px;
    height: 50px;
    line-height: 50px;
       font-weight: bold;
       
    list-style: none;
    margin-left: -30px;
    /* width: 250px; */
}
    
</style>
<nav id="room_menu">
    <ul>
    
        <li id="room_aa"> 객실 </li>
        <li id="room_li"><a href="gotoroom?num=1">Deluxe </a></li>
		<li id="room_li"><a href="gotoroom?num=2"> BusinessDeluxe </a></li>
		<li id="room_li"><a href="gotoroom?num=3">GrandCornerDeluxe</a></li> 
		<li id="room_li"><a href="gotoroom?num=4">ExecuticeBusinessDeluxe</a></li>   
    </ul>
</nav>