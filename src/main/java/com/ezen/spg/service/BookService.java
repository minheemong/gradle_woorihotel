package com.ezen.spg.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IBookDao;

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

	
	
}
