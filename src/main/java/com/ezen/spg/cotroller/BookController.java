package com.ezen.spg.cotroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
		
		mav.addObject("oldCheckin",checkin);
		mav.addObject("oldCheckout",checkout);
		mav.addObject("oldRoomnum",roomnum);
		mav.addObject("oldUsernum",usernum);
		mav.addObject("DBool",DBool);
		mav.addObject("BDBool",BDBool);
		mav.addObject("GCDBool",GCDBool);
		mav.addObject("EBDBool",EBDBool);
		mav.setViewName("bookDetail");
		return mav;
	}
}
