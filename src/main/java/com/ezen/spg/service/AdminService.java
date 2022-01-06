package com.ezen.spg.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IAdminDao;
import com.ezen.spg.dto.AdminVO;
import com.ezen.spg.dto.Paging;
import com.ezen.spg.dto.QnaVO;
  
@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public AdminVO workerCheck(String workId) {
	
		return adao.workerCheck(workId);
	}

	public int getAllCount(String key) {
		
		return adao.getAllCount(key);
	}

	public ArrayList<QnaVO> listQna(Paging paging, String key) {
	
		return adao.listQna(paging,key);
	}


}
