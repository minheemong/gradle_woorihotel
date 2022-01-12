package com.ezen.spg.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.HotelVO;
import com.ezen.spg.dto.Paging;

@Mapper
public interface IAdminRoomDao {

	int getAllCount(String key);

	ArrayList<HotelVO> getAllRoomList(Paging paging, String key);

	HotelVO getRoom(String hotelnum);

	void updateRoom(HotelVO hotelvo);

	void deleteRoom(String hotelnum);

	void insertRoom(HotelVO hvo);



}
