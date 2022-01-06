package com.ezen.spg.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.AddressVO;
import com.ezen.spg.dto.MemberVO;

@Mapper
public interface IMemberDao {
	
	MemberVO getMember(String id);

	ArrayList<AddressVO> selectAddressByDong(String dong);

	void insertMember(MemberVO membervo);

	void updateMember(MemberVO membervo);

	MemberVO confirmPhone1(String name, String phone);

	MemberVO confirmPhone2(String id, String name, String phone);

	void resetPw(MemberVO mvo);

	void deleteMember(String id);

}
//완료
