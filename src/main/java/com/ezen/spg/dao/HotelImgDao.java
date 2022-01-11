package com.ezen.spg.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.HotelImgVO;

@Mapper
public interface HotelImgDao {

	ArrayList<HotelImgVO> imglist(HotelImgVO hvo);

}
