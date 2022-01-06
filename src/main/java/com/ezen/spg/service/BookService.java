package com.ezen.spg.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IBookDao;
import com.ezen.spg.dto.BookVO;
import com.ezen.spg.dto.Paging;

@Service
public class BookService {

	@Autowired
	IBookDao bdao;

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

	public void requestBookCancel(int bdseq) {
		bdao.requestBookCancel(bdseq);
		
	}
	
	
}
