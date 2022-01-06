package com.ezen.spg.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spg.dto.BookVO;
import com.ezen.spg.dto.Paging;

@Mapper
public interface IAdminBookDao {
	
	// count

	int getAllCount();

	int getAllCountWithId(String id);

	int getAllCountWithBooknum(String booknums);
	
	int getAllCountWithIdBooknums(String id, String booknums);

	int getAllCountWithIndateOutdate(String indate, String outdate);

	int getAllCountWithIndate(String indate);

	int getAllCountWithOutdate(String outdate);

	int getAllCountWithIdIndateOutdate(String id, String indate, String outdate);

	int getAllCountWithIdIndate(String id, String indate);

	int getAllCountWithIdOutdate(String id, String outdate);
	
	
	
	// book list

	ArrayList<BookVO> getAllBookList(Paging paging);

	ArrayList<BookVO> getAllBookListWithBooknum(Paging paging, String booknums);

	ArrayList<BookVO> getAllBookListWithId(Paging paging, String id);
	
	ArrayList<BookVO> getAllBookListWithIdBooknum(Paging paging, String id, String booknums);

	ArrayList<BookVO> getAllBookListWithIndateOutdate(Paging paging, String indate, String outdate);

	ArrayList<BookVO> getAllBookListWithIndate(Paging paging, String indate);

	ArrayList<BookVO> getAllBookListWithOutdate(Paging paging, String outdate);

	ArrayList<BookVO> getAllBookListWithIdIndateOutdate(Paging paging, String id, String indate, String outdate);

	ArrayList<BookVO> getAllBookListWithIdIndate(Paging paging, String id, String indate);

	ArrayList<BookVO> getAllBookListWithIdOutdate(Paging paging, String id, String outdate);

	
	
	
	// 예약 상세 보기
	BookVO getBookDetail(int bdseq);

	// 예약 취소하기
	void adminBookCancel(int bdseq);

	// 예약 취소 대기 명단 가져오기
	ArrayList<BookVO> getAdminCancelList(Paging paging);

	// 예약 취소 대기 명단 카운트
	int getCancelAllCount();

	void updateBookResult(int parseInt);

	

	



}
