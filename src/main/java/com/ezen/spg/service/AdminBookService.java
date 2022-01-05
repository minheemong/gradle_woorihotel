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
}
