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

import com.ezen.spg.dto.BookVO;
import com.ezen.spg.dto.MemberVO;
import com.ezen.spg.dto.Paging;
import com.ezen.spg.service.AdminBookService;
import com.ezen.spg.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bs;
	
	@Autowired
	AdminBookService abs;
	
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
	@RequestMapping("gotobook")
	public ModelAndView gotobook(HttpServletRequest request){
		ModelAndView mav= new ModelAndView();
		
		String kind=request.getParameter("kind");
		
		mav.addObject("kind",kind);
		mav.setViewName("room/gotobook");
		return mav;	
	}
	@RequestMapping(value="gotobookdetail", method=RequestMethod.POST)
	public ModelAndView gotobookdetail(@RequestParam("roomnum") int roomnum,
			@RequestParam("usernum") int usernum, @RequestParam("checkin") String checkin,
			@RequestParam("checkout") String checkout, HttpServletRequest request) {
	
		String kind=request.getParameter("kind");
		
		ModelAndView mav = new ModelAndView();
		if(kind.equals("Deluxe")) {
		boolean DBool = bs.confirmRoom(checkin,checkout,"Deluxe", usernum, roomnum);
		mav.addObject("DBool",DBool);
		
		}
		else if(kind.equals("Business")) {
		boolean BDBool = bs.confirmRoom(checkin,checkout,"Business Deluxe",usernum, roomnum);
		mav.addObject("BDBool",BDBool);
		}else if(kind.equals("Grand Corner Deluxe")) {
		boolean GCDBool = bs.confirmRoom(checkin,checkout,"Grand Corner Deluxe",usernum, roomnum);
		mav.addObject("GCDBool",GCDBool);
		}else {	
		boolean EBDBool = bs.confirmRoom(checkin,checkout,"Executive Business Deluxe",usernum, roomnum);
		mav.addObject("EBDBool",EBDBool);
		}
		
		mav.addObject("checkin",checkin);
		mav.addObject("checkout",checkout);
		mav.addObject("roomnum",roomnum);
		mav.addObject("usernum",usernum);
	
		
		
	
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
			
			for(int i = 0 ; i<roomnum ; i++) userNumList.add(a);
			for(int i = 0 ; i<b ; i ++) userNumList.set(i, userNumList.get(i)+1);

			bs.insertRoom(remainList,mvo.getId(),userNumList,checkin,checkout);
			return "redirect:/bookChecklist";
		}
		
		
	}
	@RequestMapping(value="bookChecklist")
	public ModelAndView bookChecklist(@RequestParam(value="booknums", required=false) String booknums,
			@RequestParam(value="checkins", required=false) String checkins,
			@RequestParam(value="checkouts", required=false) String checkouts, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String url="mypage/bookchecklist";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		String indate="";
		String outdate="";
		
		// System.out.println("예약 번호 : "+booknums+", 체크인 : "+checkins+", 체크아웃 : "+checkouts+", ");
		
		if(mvo==null) url="member/login";
		else {
			if(booknums!=null) {
				session.setAttribute("booknums", booknums);
			} else if(session.getAttribute("booknums")!=null) {
				booknums=(String)session.getAttribute("booknums");
			} else {
				session.removeAttribute("booknums");
				booknums="";
			}
			
			int page = 1;
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page")!=null) {
				page = (int)session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}
			
			
			if(checkins!=null) {
				indate=checkins;
				session.setAttribute("checkins", indate);
			} else if(session.getAttribute("checkins")!=null) {
				indate=(String)session.getAttribute("checkins");
			} else {
				session.removeAttribute("checkins");
				indate="";
			}
			
			
			if(checkouts!=null) {
				outdate=checkouts;
				session.setAttribute("checkouts", outdate);
			} else if(session.getAttribute("checkouts")!=null) {
				outdate=(String)session.getAttribute("checkouts");
			} else {
				session.removeAttribute("checkouts");
				outdate="";
			}
			
			if(request.getParameter("a")!=null) {
				//System.out.println("파라미터 a 값 : "+request.getParameter("a"));
				session.removeAttribute("checkins");
				indate="";
				session.removeAttribute("checkouts");
				outdate="";
				session.removeAttribute("booknums");
				booknums="";
			}
			
			
			Paging paging = new Paging();
			paging.setPage(page);

			System.out.println("아이디 : "+mvo.getId()+", 예약 번호 : "+booknums);
			int count=abs.getAllCount(mvo.getId(), booknums, indate, outdate);
			paging.setTotalCount(count);
			paging.paging();
			System.out.println("count : "+count);

			ArrayList<BookVO> booklist = abs.getAllBookList(paging,mvo.getId(),booknums,indate,outdate);
			System.out.println("booklist : "+booklist.size());
			mav.addObject("booklist",booklist);
			mav.addObject("total",count);
			mav.addObject("paging",paging);
			mav.addObject("booknums",booknums);
			mav.addObject("checkins",checkins);
			mav.addObject("checkouts",checkouts);
		}
		
		mav.setViewName(url);
		return mav;
	}
	
	/*
	 * @RequestMapping(value="bookChecklistSerch", method=RequestMethod.POST) public
	 * ModelAndView bookChecklistSerch(@RequestParam(value="booknums",
	 * required=false) String booknums,
	 * 
	 * @RequestParam(value="checkins", required=false) String checkins,
	 * 
	 * @RequestParam(value="checkouts", required=false) String checkouts,
	 * HttpServletRequest request) { ModelAndView mav = new ModelAndView();
	 * 
	 * String url="mypage/bookchecklist"; HttpSession session =
	 * request.getSession(); MemberVO mvo =
	 * (MemberVO)session.getAttribute("loginUser");
	 * 
	 * String indate=""; String outdate=""; if(mvo==null) url="loginForm"; else {
	 * if(booknums!=null) { session.setAttribute("booknums", booknums); } else
	 * if(session.getAttribute("booknums")!=null) {
	 * booknums=(String)session.getAttribute("booknums"); } else {
	 * session.removeAttribute("booknums"); booknums=""; }
	 * 
	 * int page = 1; if(request.getParameter("page")!=null) { page =
	 * Integer.parseInt(request.getParameter("page")); session.setAttribute("page",
	 * page); } else if(session.getAttribute("page")!=null) { page =
	 * (int)session.getAttribute("page"); } else { page = 1;
	 * session.removeAttribute("page"); }
	 * 
	 * 
	 * if(checkins!=null) { indate=checkins; session.setAttribute("checkins",
	 * indate); } else if(session.getAttribute("checkins")!=null) {
	 * indate=(String)session.getAttribute("checkins"); } else {
	 * session.removeAttribute("checkins"); indate=""; }
	 * 
	 * 
	 * if(checkouts!=null) { outdate=checkouts; session.setAttribute("checkouts",
	 * outdate); } else if(session.getAttribute("checkouts")!=null) {
	 * outdate=(String)session.getAttribute("checkouts"); } else {
	 * session.removeAttribute("checkouts"); outdate=""; }
	 * 
	 * if(request.getParameter("a")!=null) {
	 * System.out.println("파라미터 a 값 : "+request.getParameter("a"));
	 * session.removeAttribute("checkins"); indate="";
	 * session.removeAttribute("checkouts"); outdate="";
	 * session.removeAttribute("booknums"); booknums=""; }
	 * 
	 * 
	 * Paging paging = new Paging(); paging.setPage(page);
	 * 
	 * int count = bs.getAllCount(mvo.getId(), booknums, indate, outdate);
	 * paging.setTotalCount(count); paging.paging();
	 * 
	 * ArrayList<BookVO> list = bs.getbooklist(mvo.getId(),paging, booknums, indate,
	 * outdate); System.out.println("getbooklist size : "+list.size()); int
	 * total=count; mav.addObject("booklist",list); mav.addObject("total",total);
	 * mav.addObject("paging",paging); mav.addObject("booknums",booknums);
	 * mav.addObject("checkins",checkins); mav.addObject("checkouts",checkouts); }
	 * 
	 * mav.setViewName(url); return mav; }
	 */
	
	
	
	
	@RequestMapping("/goInfo")
	public String goInfo() {
		return "Info/sum";
	}
	
	
	
	
	@RequestMapping("/seoulHotel")
	public String seoulHotel() {
		return "Info/Seoul";
	}
	
	
	
	
	@RequestMapping("/listbookcheck")
	public ModelAndView listbookcheck(@RequestParam("bdseq") int bdseq,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo.getId()==null) mav.setViewName("member/login");
		else {
			mav.addObject("bookcheck",abs.getBookDetail(bdseq));
			mav.setViewName("mypage/listbookcheck");
		}
		
		return mav;
	}
	
	
	
	
	@RequestMapping("/bookcancel")
	public String bookcancel(@RequestParam("bdseq") int bdseq,
			@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout,
			@RequestParam("price") int price, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo.getId()==null) return ("member/login");
		else {
			
			bs.requestBookCancel(bdseq, checkin , checkout,price);
			
			return "redirect:/bookChecklist";
		}
	}
	
	
	@RequestMapping("changeRoom")
	public ModelAndView changeRoom(HttpServletRequest request,
			@RequestParam("bdseq") int bdseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo.getId()==null) mav.setViewName("member/login");
		else {
			BookVO bvo = abs.getBookDetail(bdseq);
			int maxbed = bs.getMaxBed(bvo.getHotelnum());
			
			mav.addObject("bookcheck",abs.getBookDetail(bdseq));
			mav.addObject("max", maxbed);
			
			mav.setViewName("mypage/changeRoom");
		}
		
		return mav;
	}
	
	
	
	
	@RequestMapping("/gotochangeroom")
	public String gotochangeroom(@RequestParam("bdseq") int bdseq,
			@RequestParam("number") int number, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo.getId()==null) return "member/login";
		else {
			bs.ChangePeople(bdseq,number);
			
			return "redirect:/bookChecklist";
		}
		
	}
	
	
}
