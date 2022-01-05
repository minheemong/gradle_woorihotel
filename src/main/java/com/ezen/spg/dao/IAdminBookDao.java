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

	ArrayList<BookVO> getAllBookListWithIndateOutdate(Paging paging, String indate, String outdate);

	ArrayList<BookVO> getAllBookListWithIndate(Paging paging, String indate);

	ArrayList<BookVO> getAllBookListWithOutdate(Paging paging, String outdate);

	ArrayList<BookVO> getAllBookListWithIdIndateOutdate(Paging paging, String id, String indate, String outdate);

	ArrayList<BookVO> getAllBookListWithIdIndate(Paging paging, String id, String indate);

	ArrayList<BookVO> getAllBookListWithIdOutdate(Paging paging, String id, String outdate);

	
	
	
	
	BookVO getBookDetail(int bdseq);

	void adminBookCancel(int bdseq);

}
