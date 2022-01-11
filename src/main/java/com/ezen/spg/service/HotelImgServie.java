package com.ezen.spg.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.HotelImgDao;
import com.ezen.spg.dto.HotelImgVO;

@Service
public class HotelImgServie {

	
	@Autowired
	HotelImgDao hdao;

	public ArrayList<HotelImgVO> imglist(HotelImgVO hvo) {

		return hdao.imglist(hvo);
	}
}
