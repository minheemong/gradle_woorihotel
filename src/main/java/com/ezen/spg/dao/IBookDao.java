package com.ezen.spg.dao;


import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IBookDao {

	int bookedCount(String checkin, String checkout, String kind);

	int maxPeopleNumber(String kind);

	int maxRoomNumber(String kind);

	

}
