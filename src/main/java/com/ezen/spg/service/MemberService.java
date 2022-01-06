package com.ezen.spg.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IMemberDao;
import com.ezen.spg.dto.AddressVO;
import com.ezen.spg.dto.MemberVO;


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

	public MemberVO confirmPhone1(String name, String phone) {
		return mdao.confirmPhone1(name,phone);
	}

	public MemberVO confirmPhone2(String id, String name, String phone) {
		return mdao.confirmPhone2(id,name,phone);

	}

	public void resetPw(MemberVO mvo) {
		mdao.resetPw(mvo);	
	}

	public void deleteMember(String id) {
		mdao.deleteMember(id);
	}
}

//완료