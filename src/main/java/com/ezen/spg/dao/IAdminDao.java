package com.ezen.spg.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.AdminVO;

@Mapper
public interface IAdminDao {

	AdminVO workerCheck(String workId);

	

}
