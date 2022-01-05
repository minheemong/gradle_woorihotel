package com.ezen.spg.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IAdminDao;
import com.ezen.spg.dto.AdminVO;

@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public AdminVO workerCheck(String workId) {
	
		return adao.workerCheck(workId);
	}


}
