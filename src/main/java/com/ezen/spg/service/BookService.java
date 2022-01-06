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


	public ArrayList<BookVO> getbooklist(String id, Paging paging, String booknums, String indate, String outdate) {
		if(booknums.equals("") && indate.equals("") && outdate.equals("")){
			System.out.println("getbooklist1 \n startNum : " + paging.getStartNum() +
					"\n endNum : "+ paging.getEndNum());
			return bdao.getbooklist1(id,paging);
		} else if(booknums.equals("") && !indate.equals("") || !outdate.equals("")) {
			System.out.println("getbooklist2");
			return bdao.getbooklist2(id,paging,indate,outdate);
		} else if(indate.equals("") && !outdate.equals("")){
			System.out.println("getbooklist3");
			return bdao.getbooklist3(id,paging,outdate);
		} else if(!indate.equals("") && outdate.equals("")) {
			System.out.println("getbooklist4");
			return bdao.getbooklist4(id,paging,indate);
		} else{			
			System.out.println("getbooklist5");
			return bdao.getbooklist5(id,paging,Integer.parseInt(booknums));
		}
		
	}

	public int getAllCount(String id, String booknums, String indate, String outdate) {
		if(booknums.equals("") && indate.equals("") && outdate.equals("")) {
			System.out.println("getAllCount1 \n indate : " + indate +
					"\n outdate : "+ outdate + "\n id : "+ id);
			return bdao.getAllCount1(id);
		} else if(!booknums.equals("") && indate.equals("")&&outdate.equals("")) {
			System.out.println("getAllCount2 \n indate : " + indate +
					"\n outdate : "+ outdate);
			return bdao.getAllCount2(id, booknums);
		} else if(booknums.equals("") && !indate.equals("") || !outdate.equals("")) {
			System.out.println("getAllCount3 \n indate : " + indate +
					"\n outdate : "+ outdate);
			return bdao.getAllCount3(id, indate, outdate);
		} else if(indate.equals("") && outdate!="") {
			System.out.println("getAllCount4 \n indate : " + indate +
					"\n outdate : "+ outdate);
			return bdao.getAllCount4(id, outdate);
		} else { //indate!="" && outdate.equals("")
			System.out.println("getAllCount5 \n indate : " + indate +
					"\n outdate : "+ outdate);
			return bdao.getAllCount5(id, indate);
		}
	}
	
	
}
