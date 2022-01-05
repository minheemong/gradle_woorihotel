package com.ezen.spg.dao;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IBookDao {

	int maxPeopleNumber(String kind);

	int maxRoomNumber(String kind);

	ArrayList<Integer> bookedRoom(String checkin, String checkout, String kind);

	ArrayList<Integer> selectRoomNum(String kind);

	

}
