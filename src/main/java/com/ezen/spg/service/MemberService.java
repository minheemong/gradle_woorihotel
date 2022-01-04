package com.ezen.spg.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IMemberDao;
import com.ezen.spm1.dto.AddressVO;
import com.ezen.spm1.dto.MemberVO;

@Service
public class MemberService {

	@Autowired
	IMemberDao mdao;
	

	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}

	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		return mdao.selectAddressByDong(dong);
	}

	public void insertMember(MemberVO membervo) {
			mdao.insertMember(membervo);
	}

	public void updateMember(MemberVO membervo) {
			mdao.updateMember(membervo);
	}
}
