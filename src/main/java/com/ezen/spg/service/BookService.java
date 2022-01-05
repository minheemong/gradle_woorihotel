package com.ezen.spg.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IBookDao;

@Service
public class BookService {

	@Autowired
	IBookDao bdao;

	public boolean confirmRoom(String checkin, String checkout, String kind, int usernum, int roomnum) {
		boolean result = false;
		int bookedCount = bdao.bookedCount(checkin, checkout, kind);
		
		if ( bdao.maxPeopleNumber(kind) >= usernum/roomnum && 
				(bdao.maxRoomNumber(kind)-bookedCount) >= roomnum ) result = true;
				
		return result;
	}

	
	
}
