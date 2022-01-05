package com.ezen.spg.cotroller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg.dto.MemberVO;
import com.ezen.spg.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bs;
	
	@RequestMapping(value="bookForm", method=RequestMethod.POST)
	public ModelAndView bookForm(@RequestParam("roomnum") int roomnum,
			@RequestParam("usernum") int usernum, @RequestParam("checkin") String checkin,
			@RequestParam("checkout") String checkout, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		boolean DBool = bs.confirmRoom(checkin,checkout,"Deluxe", usernum, roomnum);
		boolean BDBool = bs.confirmRoom(checkin,checkout,"Business Deluxe",usernum, roomnum);
		boolean GCDBool = bs.confirmRoom(checkin,checkout,"Grand Corner Deluxe",usernum, roomnum);
		boolean EBDBool = bs.confirmRoom(checkin,checkout,"Executive Business Deluxe",usernum, roomnum);
		
		mav.addObject("checkin",checkin);
		mav.addObject("checkout",checkout);
		mav.addObject("roomnum",roomnum);
		mav.addObject("usernum",usernum);
		mav.addObject("DBool",DBool);
		mav.addObject("BDBool",BDBool);
		mav.addObject("GCDBool",GCDBool);
		mav.addObject("EBDBool",EBDBool);
		mav.setViewName("bookDetail");
		return mav;
	}
	
	@RequestMapping("book")
	public String book(@RequestParam("roomnum") int roomnum,
			@RequestParam("usernum") int usernum, @RequestParam("checkin") String checkin,
			@RequestParam("checkout") String checkout, @RequestParam("kind") String kind,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo == null)
			return "member/login";
		else {
			ArrayList<Integer> remainList = bs.remainList(checkin, checkout, kind);
			ArrayList<Integer> userNumList = new ArrayList<>();
			int a = usernum / roomnum ;  
			int b = usernum % roomnum; 
			
			for(int i = 0 ; i<roomnum ; i++) { 
				userNumList.add(a);
			}
			for(int i = 0 ; i<b ; i ++) {
				userNumList.set(i, userNumList.get(i)+1);
			}
			
			// 주석
			for(Integer i : remainList) {
				System.out.println("2번째 : " + i);
			}
			
			// bs.insertRoom(remainList,roomnum,usernum,mvo.getId(),userNumList,checkin,checkout);
			return "";
		}
		
		
	}
}
