package com.ezen.spg.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spg.dao.IAdminBookDao;
import com.ezen.spg.dto.BookVO;
import com.ezen.spg.dto.Paging;

@Service
public class AdminBookService {

	@Autowired
	IAdminBookDao abao;

	public int getAllCount(String id, String booknums,
			String indate, String outdate) {
		int count=0;
		
		if(id=="" && booknums=="" && indate=="" && outdate=="")
			count=abao.getAllCount();
		else if(id!="" && booknums=="" && indate=="" && outdate=="")
			count=abao.getAllCountWithId(id);
		else if(id!="" && booknums=="" && indate=="" && outdate=="")
			count=abao.getAllCountWithIdBooknums(id,booknums);
		else if(id=="" && booknums!="" && indate=="" && outdate=="")
			count=abao.getAllCountWithBooknum(booknums);
		else if((id=="" && booknums=="" && indate!="") ||
				(id=="" && booknums=="" && outdate!="")) {
			if(indate!="" && outdate!="")
				count=abao.getAllCountWithIndateOutdate(indate, outdate);
			else if(indate!="" && outdate=="")
				count=abao.getAllCountWithIndate(indate);
			else if(indate=="" && outdate!="")
				count=abao.getAllCountWithOutdate(outdate);
		} else if((booknums=="" && id!="" && indate!="") ||
				(booknums=="" && id!="" && outdate!="")) {
			if(indate!="" && outdate!="")
				count = abao.getAllCountWithIdIndateOutdate(id,indate,outdate);
			else if(indate!="" && outdate=="")
				count = abao.getAllCountWithIdIndate(id,indate);
			else if(indate=="" && outdate!="")
				count = abao.getAllCountWithIdOutdate(id,outdate);
		}
			
		
		return count;
	}

	public ArrayList<BookVO> getAllBookList(Paging paging, String id, String booknums, String indate, String outdate) {
		ArrayList<BookVO> booklist = new ArrayList<BookVO>();
		
		if(id=="" && booknums=="" && indate=="" && outdate=="")
			booklist = abao.getAllBookList(paging);
		else if(id=="" && booknums!="" && indate=="" && outdate=="")
			booklist = abao.getAllBookListWithBooknum(paging,booknums);
		else if(id!="" && booknums=="" && indate=="" && outdate=="")
			booklist = abao.getAllBookListWithId(paging,id);
		else if(id!="" && booknums!="" && indate=="" && outdate=="")
			booklist = abao.getAllBookListWithIdBooknum(paging,id,booknums);
		else if((id=="" && booknums=="" && indate!="") || 
				(id=="" && booknums=="" && outdate!="")) {
			if(indate!="" && outdate!="")
				booklist = abao.getAllBookListWithIndateOutdate(paging,indate,outdate);
			else if(indate!="" && outdate=="")
				booklist = abao.getAllBookListWithIndate(paging,indate);
			else if(indate=="" && outdate!="")
				booklist = abao.getAllBookListWithOutdate(paging,outdate);
		} else if((id!="" && booknums=="" && indate!="") || 
				(id!="" && booknums=="" && outdate!="")) {
			if(indate!="" && outdate!="")
				booklist = abao.getAllBookListWithIdIndateOutdate(paging,id,indate,outdate);
			else if(indate!="" && outdate=="")
				booklist = abao.getAllBookListWithIdIndate(paging,id,indate);
			else if(indate=="" && outdate!="")
				booklist = abao.getAllBookListWithIdOutdate(paging,id,outdate);
		}
		
		for(BookVO bvo : booklist) {
			bvo.days();
		}
		
		
		return booklist;
	}

	public BookVO getBookDetail(int bdseq) {
		BookVO bvo=abao.getBookDetail(bdseq);
		bvo.days();
		return bvo;
	}

	public void adminBookCancel(int bdseq) {
		abao.adminBookCancel(bdseq);
		
	}

	public ArrayList<BookVO> getAdminCancelList(Paging paging) {
		ArrayList<BookVO> list = abao.getAdminCancelList(paging);
		
		for(BookVO bvo : list)
			bvo.days();
		
		return list;
	}

	public int getCancelAllCount() {
		return abao.getCancelAllCount();
	}

	public void updateBookResult(String bdseq) {
		abao.updateBookResult(Integer.parseInt(bdseq));
		
	}
	/*
		public int getAllCount_book(Paging paging, String id, String booknums, String indate, String outdate) {
			if(booknums=="" && indate=="" && outdate=="" && id=="") {
				return abao.getAllCount_bookWithPaging(paging);
				sql="select count(*) as cnt from book";
			} else if(booknums=="" && indate=="" && outdate=="" && id!="") {
				return abao.getAllCount_bookWithId(paging, id);
				sql="select count(*) as cnt from ( select rownum as rn, b.* from"
						+ " ( ( select * from book_view where id=?"
						+ " order by booknum desc) b )"
						+ ")";
				pstmt.setString(1, id);
			} else if(id=="" && booknums!="" && indate=="" && outdate=="") {
				return abao.getAllCount_bookWithBooknum(paging, booknums);
				sql="select count(*) as cnt from ( select rownum as rn, b.* from"
						+ " ( ( select * from book_view where booknum=?"
						+ " order by booknum desc) b )"
						+ ")";
				pstmt.setString(1, booknums);
			} else if((id=="" && booknums=="" && indate!="") || (id=="" && booknums==""&&outdate!="")){
				return abao.getAllCount_bookWithIndateOutdate(paging, indate, outdate);
				if(indate!="" && outdate!="") {
					sql="select count(*) as cnt from ( select rownum as rn, b.* from "
							+ "( (select * from book_view where "
							+ " to_char(checkin, 'YYYYMMDD')=? "
							+ " and to_char(checkout, 'YYYYMMDD')=?"
							+ " order by booknum desc) b )"
							+ " )";
					pstmt.setString(1, indate);
					pstmt.setString(2, outdate);
				} else if(indate=="" && outdate!=""){
					return abao.getAllCount_bookWithOutdate(paging, outdate);
					sql="select count(*) as cnt from ( select rownum as rn, b.* from "
							+ "( (select * from book_view where "
							+ " to_char(checkout, 'YYYYMMDD')=? "
							+ " order by booknum desc) b )"
							+ " )";
					pstmt.setString(1, outdate);
				} else if(indate!="" && outdate=="") {
					return abao.getAllCount_bookWithIndate(paging, indate);
					sql="select count(*) as cnt from ( select rownum as rn, b.* from "
							+ "( (select * from book_view where "
							+ " to_char(checkin, 'YYYYMMDD')=? "
							+ " order by booknum desc) b )"
							+ " )";
					pstmt.setString(1, indate);
				}
			} else if((booknums=="" && id!="" && indate!="")||(booknums=="" && id!="" && outdate!="")) {
				return abao.getAllCount_bookWithIdIndateOutdate(paging, id indate, outdate);
				if(indate!="" && outdate!="") {
					sql="select count(*) as cnt from ( select rownum as rn, b.* from "
							+ "( (select * from book_view where id=? "
							+ "and to_char(checkin, 'YYYYMMDD')=? "
							+ "and to_char(checkout, 'YYYYMMDD')=? order by booknum desc) b )"
							+ " )";
					pstmt.setString(1, id);
					pstmt.setString(2, indate);
					pstmt.setString(3, outdate);
				} else if(indate=="" && outdate!=""){
					return abao.getAllCount_bookWithIdOutdate(paging, id, outdate);
					sql="select count(*) as cnt from ( select rownum as rn, b.* from "
							+ "( (select * from book_view where id=? "
							+ "and to_char(checkout, 'YYYYMMDD')=? "
							+ " order by booknum desc) b )"
							+ " )";
					pstmt.setString(1, id);
					pstmt.setString(2, outdate);
				} else {
					return abao.getAllCount_book(paging, id, indate);
					// if(indate!="" && outdate=="") 
					sql="select count(*) as cnt from ( select rownum as rn, b.* from "
							+ "( (select * from book_view where id=? "
							+ "and to_char(checkin, 'YYYYMMDD')=? "
							+ " order by booknum desc) b )"
							+ " )";
					pstmt.setString(1, id);
					pstmt.setString(2, indate);
				}
		}*/

	// member 리스트 이름 클릭 시 예약목록 출력
	public ArrayList<BookVO> getMemberBook(String id, Paging paging, String booknums, String indate, String outdate) {
		if(booknums=="" && indate=="" && outdate=="" && id=="") {
			return abao.getMemberBook(paging);
			//System.out.println("전부 공백");
		} else if(booknums=="" && indate=="" && outdate=="" && id!="") {
			return abao.getMemberBookWithId(paging,id);
			//System.out.println("아이디만 입력");
		} else if(id=="" && booknums!="" && indate=="" && outdate=="") {
			return abao.getMemberBookWithBooknum(paging, booknums);
			//System.out.println("번호만 입력");
		} else if((id=="" && booknums=="" && indate!="") || 
				(id=="" && booknums=="" &&outdate!="")) {
			if(indate!="" && outdate!="") {
				return abao.getMemberBookWithIndateOutdate(paging, indate, outdate);
				//System.out.println("체크인 체크아웃 날짜만 입력");
				//System.out.println("아이디 : "+id);
				//System.out.println("번호 : "+booknums);
				//System.out.println("체크아웃 : "+outdate);
				//System.out.println("체크인 : "+indate);
			} else if(indate=="" && outdate!=""){
				return abao.getMemberBookWithOutdate(paging,outdate);
				//System.out.println("체크아웃 날짜만 입력");
				//System.out.println("아이디 : "+id);
			} else {//indate!="" && outdate==""
				return abao.getMemberBookWithIndate(paging, indate);
				//System.out.println("체크인 날짜만 입력");
			}
		} else {//booknums=="" && id!="" && indate!="" || outdate!=""
			if(indate!="" && outdate!="") {
				return abao.getMemberBookWithIdIndateOudate(paging,id,indate,outdate);
				//System.out.println("아이디, 체크인, 체크아웃 입력");
			} else if(indate=="" && outdate!=""){
				return abao.getMemberBookWithIdOutdate(paging, id, outdate);
				//System.out.println("아이디, 체크아웃 입력");
			} else {//indate!="" && outdate==""
				return abao.getMemberBookWithIdIndate(paging,id, indate);
				//System.out.println("아이디, 체크인 입력");
			}
		}
	}
}
