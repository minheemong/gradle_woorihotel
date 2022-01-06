package com.ezen.spg.dao;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.BookVO;
import com.ezen.spg.dto.Paging;


@Mapper
public interface IBookDao {

	int maxPeopleNumber(String kind);

	int maxRoomNumber(String kind);

	ArrayList<Integer> bookedRoom(String checkin, String checkout, String kind);

	ArrayList<Integer> selectRoomNum(String kind);

	void insertBook(String id);

	int LookupMaxBooknum();

	void insertRoom(int booknum, int hotelnum, int usernum, String checkin, String checkout);
	
	
	ArrayList<BookVO> getbooklist1(String id, Paging paging);

	ArrayList<BookVO> getbooklist2(String id, Paging paging, String indate, String outdate);

	ArrayList<BookVO> getbooklist3(String id, Paging paging, String outdate);

	ArrayList<BookVO> getbooklist4(String id, Paging paging, String indate);

	ArrayList<BookVO> getbooklist5(String id, Paging paging, int booknums);

	int getAllCount1(String id);

	int getAllCount2(String id, String booknums);

	int getAllCount3(String id, String indate, String outdate);

	int getAllCount4(String id, String outdate);

	int getAllCount5(String id, String indate);


}
