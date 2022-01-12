package com.ezen.spg.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IAdminRoomDao;
import com.ezen.spg.dto.HotelVO;
import com.ezen.spg.dto.Paging;


@Service
public class AdminRoomService {
	@Autowired
	IAdminRoomDao arao;

	public int getAllCount(String key) {
		return arao.getAllCount(key);
	}

	public ArrayList<HotelVO> getAllRoomList(Paging paging, String key) {
		return arao.getAllRoomList(paging, key);
	}

	public HotelVO getRoom(String hotelnum) {
		return arao.getRoom(hotelnum);
	}

	public void updateRoom(HotelVO hotelvo) {
		arao.updateRoom(hotelvo);
	}

	public void deleteRoom(String hotelnum) {
		arao.deleteRoom(hotelnum);
	}

	public void insertRoom(HotelVO hvo) {
		arao.insertRoom(hvo);
	}
}
