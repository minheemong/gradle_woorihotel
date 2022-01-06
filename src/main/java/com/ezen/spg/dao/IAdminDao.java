package com.ezen.spg.dao;
  
import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.AdminVO;
import com.ezen.spg.dto.Paging;
import com.ezen.spg.dto.QnaVO;

@Mapper
public interface IAdminDao {

	AdminVO workerCheck(String workId);

	int getAllCount(String key);

	ArrayList<QnaVO> listQna(Paging paging, String key);

	  
	/* //dd */
}
