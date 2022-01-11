package com.ezen.spg.service;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IAdminBookDao;
import com.ezen.spg.dao.IBookDao;

@Service
public class BookService {

	@Autowired
	IBookDao bdao;
	@Autowired
	IAdminBookDao abao;

	public boolean confirmRoom(String checkin, String checkout, String kind, int usernum, int roomnum) {
		boolean result = false;
		int bookedCount = bdao.bookedRoom(checkin, checkout, kind).size();
		
		if ( bdao.maxPeopleNumber(kind) >= usernum/roomnum && 
				(bdao.maxRoomNumber(kind)-bookedCount) >= roomnum ) result = true;
				
		return result;
	}

	public ArrayList<Integer> remainList(String checkin, String checkout, String kind) {
		ArrayList<Integer> remainRoomList = bdao.selectRoomNum(kind);
		ArrayList<Integer> bookedRoomList = bdao.bookedRoom(checkin, checkout, kind);
		remainRoomList.removeAll(bookedRoomList);
		
		
		
		return remainRoomList;
	}
	
	public void insertRoom(ArrayList<Integer> remainList, String id,
			ArrayList<Integer> userNumList, String checkin, String checkout) {
		bdao.insertBook(id);
		int booknum = bdao.LookupMaxBooknum();
		
		for(int i = 0 ; i<userNumList.size(); i++) {
			bdao.insertRoom(booknum,remainList.get(i), userNumList.get(i), checkin, checkout);
		}
		
	}
	/*
	 * public ArrayList<BookVO> getbooklist(String id, Paging paging, String
	 * booknums, String indate, String outdate) { ArrayList<BookVO> list = null;
	 * 
	 * if(booknums.equals("") && indate.equals("") && outdate.equals("")){
	 * System.out.println("getbooklist1 \n startNum : " + paging.getStartNum() +
	 * "\n endNum : "+ paging.getEndNum()); list= bdao.getbooklist1(id,paging); }
	 * else if(booknums.equals("") && !indate.equals("") || !outdate.equals("")) {
	 * if(indate!="" && outdate=="") list =
	 * bdao.getbooklistWithIndateOutdate(paging,id,indate,outdate); else
	 * if(indate!="" && outdate=="") list =
	 * bdao.getbooklistWithIndate(paging,id,indate); else if(indate=="" &&
	 * outdate!="") list = bdao.getbooklistWithOutdate(paging,id,outdate); } else{
	 * System.out.println("getbooklist5"); list=
	 * bdao.getbooklist5(id,paging,Integer.parseInt(booknums)); }
	 * 
	 * return list;
	 * 
	 * }
	 * 
	 * public int getAllCount(String id, String booknums, String indate, String
	 * outdate) { int count = 0;
	 * 
	 * if(booknums.equals("") && indate.equals("") && outdate.equals("")) {
	 * System.out.println("getAllCount1 \n indate : " + indate + "\n outdate : "+
	 * outdate + "\n id : "+ id); count= bdao.getAllCount1(id); } else
	 * if(!booknums.equals("") && indate.equals("")&&outdate.equals("")) {
	 * System.out.println("getAllCount2 \n indate : " + indate + "\n outdate : "+
	 * outdate); count= bdao.getAllCount2(id, booknums); } else
	 * if(booknums.equals("") && !indate.equals("") || !outdate.equals("")) {
	 * if(indate!="" && outdate!="") count = bdao.getAllCount3(id, indate, outdate);
	 * 
	 * 
	 * System.out.println("getAllCount3 \n indate : " + indate + "\n outdate : "+
	 * outdate); }
	 * 
	 * return count;
	 * 
	 * }
	 */

	public void requestBookCancel(int bdseq, String checkin, String checkout, int price) {
		String result = abao.getBookDetail(bdseq).getResult();
		if(result.equals("0")) {
			bdao.bookCancel(bdseq);
		} else if(result.equals("1")) {
			
			int totalPrice = 0;
			int calPrice;
			Date today = new Date();
			Date checkinTs = Timestamp.valueOf(checkin);
			Date checkoutTs = Timestamp.valueOf(checkout);
			
			int checkinYear = checkinTs.getYear() + 1900; 
			int checkoutYear = checkoutTs.getYear() + 1900;
			long confirmTime = (checkinTs.getTime() - today.getTime())/1000;
			
			String confirm5St = Integer.toString(checkinYear); confirm5St += "-05-01 00:00:00.0";
			Date confirm5Ts = Timestamp.valueOf(confirm5St);
			String confirm11St = Integer.toString(checkinYear); confirm11St += "-11-01 00:00:00.0";
			Date confirm11Ts = Timestamp.valueOf(confirm11St);
			String confirm1224St = Integer.toString(checkinYear); confirm1224St += "-12-24 00:00:00.0";
			Date confirm1224Ts = Timestamp.valueOf(confirm1224St);
			
			String confirm0101St = Integer.toString(checkoutYear); confirm0101St += "-01-01 00:00:00.0";
			Date confirm0101Ts = Timestamp.valueOf(confirm0101St);
			
			long calDateDays = calDays(checkinTs, checkoutTs);
			calPrice = (int) (price / calDateDays);
			
		
			
			
			// 21600 6시간 유예초 180초  21780
			// 체크인 하루전 18시 이후에 취소하는지 안하는지?
			if(21780>=confirmTime) {
				// 체크인과 체크아웃 년도가 같은지?
				if(checkinTs.getYear() == checkoutTs.getYear()) {
					// 체크인이 5월 전 체크아웃 5월 전 
					if(confirm5Ts.compareTo(checkinTs)>0 && confirm5Ts.compareTo(checkoutTs)>=0) {
						totalPrice = calTotalPrice10(checkinTs, checkoutTs,calPrice);
						
					// 체크인 5월전 체크아웃 5월 후	
					}else if (confirm5Ts.compareTo(checkinTs)>0 && confirm5Ts.compareTo(checkoutTs)<0 ){
						totalPrice = calTotalPriceBtw(checkinTs, checkoutTs, confirm5Ts,calPrice);
					// 체크인이 5월뒤에 체크아웃이 10.31 안에있을때
					}else if( confirm5Ts.compareTo(checkinTs)<=0 && confirm11Ts.compareTo(checkoutTs)>0) {
						totalPrice = calTotalPrice80(checkinTs, checkoutTs,calPrice);
					//체크인이 11월 안 체크아웃이 11월 밖	
					}else if(confirm11Ts.compareTo(checkinTs)>0 && confirm11Ts.compareTo(checkoutTs)<0 ) {
						totalPrice = calTotalPriceBtwReverse(checkinTs, checkoutTs, confirm11Ts,calPrice);
					//체크인이 11월 이후 체크아웃으 1224 전
					}else if(confirm11Ts.compareTo(checkinTs)<=0 && confirm1224Ts.compareTo(checkoutTs)>=0) {
						totalPrice = calTotalPrice10(checkinTs, checkoutTs,calPrice);
					// 체크인이 1224전 체크아웃이 1224 후
					}else if(confirm1224Ts.compareTo(checkinTs)>0 && confirm1224Ts.compareTo(checkoutTs)<=0) {
						totalPrice = calTotalPriceBtw(checkinTs, checkoutTs, confirm1224Ts,calPrice);
					}else {
						totalPrice = calTotalPrice80(checkinTs, checkoutTs,calPrice);
					}
					
					
				// 체크인과 체크아웃 년도가 안같은 경우
				}else { 
					// 두가지가 있다 첫째 1224 전에 체크인
					if(confirm1224Ts.compareTo(checkinTs)>0) {
						int totalPrice1 = calTotalPrice10(checkinTs,confirm1224Ts,calPrice); 
						int totalPrice2 = calTotalPrice80(confirm1224Ts, confirm0101Ts,calPrice);
						int totalPrice3 = calTotalPrice10(confirm0101Ts,checkoutTs,calPrice);
						totalPrice = totalPrice1 + totalPrice2 + totalPrice3;
					// 둘때 1224후 체크인
					} else {
						int totalPrice1 = calTotalPrice80(checkinTs, confirm0101Ts,calPrice);
						int totalPrice2 = calTotalPrice10(confirm0101Ts,checkoutTs,calPrice);
						totalPrice = totalPrice1 + totalPrice2;
					}
				}		
			}else {
				totalPrice = price;
			}
		}
	}
	



	private int calTotalPriceBtwReverse(Date checkinTs, Date checkoutTs, Date confirmDate, int calPrice) {
		int totalPrice;
		long calDateDays1 = calDays(checkinTs,confirmDate);
		totalPrice= (int) (calPrice*calDateDays1*20/100);
		long calDateDays2 = calDays(confirmDate, checkoutTs);
		totalPrice += (int)(calPrice*calDateDays2*90/100);
		return totalPrice;
	}

	private int calTotalPrice80(Date checkinTs, Date checkoutTs, int calPrice) {
		long calDateDays = calDays(checkinTs,checkoutTs);
		System.out.println("calDateDays :" + calDateDays);
		return (int)((calPrice*calDateDays)*20/100);
	}

	private int calTotalPriceBtw(Date checkinTs, Date checkoutTs, Date confirmDate, int calPrice) {
		int totalPrice;
		long calDateDays1 = calDays(checkinTs,confirmDate);
		totalPrice= (int) (calPrice*calDateDays1*90/100);
		long calDateDays2 = calDays(confirmDate, checkoutTs);
		totalPrice += (int)(calPrice*calDateDays2*20/100);
		return totalPrice;
	}

	private int calTotalPrice10(Date checkinTs, Date checkoutTs, int calPrice) {
		long calDateDays = calDays(checkinTs,checkoutTs);
		System.out.println("calDateDays :" + calDateDays);
		return (int)((calPrice*calDateDays)*90/100);
	}

	private long calDays(Date date1, Date date2) {
		long calDate = date1.getTime() - date2.getTime();
		
		long calDateDays = calDate / ( 24*60*60*1000); 
		calDateDays = Math.abs(calDateDays);
		return calDateDays;
	}
	
	
}


