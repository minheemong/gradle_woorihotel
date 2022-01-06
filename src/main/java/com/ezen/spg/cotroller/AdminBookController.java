package com.ezen.spg.cotroller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg.dto.BookVO;
import com.ezen.spg.dto.Paging;
import com.ezen.spg.service.AdminBookService;

@Controller
public class AdminBookController {
	
	@Autowired
	AdminBookService abs;
	
	
	
	
	@RequestMapping("adminbookchecklist")
	public ModelAndView adminbookList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		
		HttpSession session = request.getSession();
		//if(session.getAttribute("adminUser")==null) mav.setViewName("adminLogin");
		//else {
			
		//}
		
		int page = 1;
		String booknums="";
		String indate="";
		String outdate="";
		String id="";
		
		if(request.getParameter("booknums")!=null) {
			booknums=request.getParameter("booknums");
			session.setAttribute("booknums", booknums);
		} else if(session.getAttribute("booknums")!=null) {
			booknums=(String)session.getAttribute("booknums");
		} else {
			session.removeAttribute("booknums");
			booknums="";
		}
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page")!=null) {
			page = (int)session.getAttribute("page");
		} else {
			page= 1;
			session.removeAttribute("page");
		}
		
		if(request.getParameter("checkins")!=null) {
			indate=request.getParameter("checkins");
			session.setAttribute("checkins", indate);
		} else if(session.getAttribute("checkins")!=null) {
			indate=(String)session.getAttribute("checkins");
		} else {
			session.removeAttribute("checkins");
			indate="";
		}
		
		if(request.getParameter("checkouts")!=null) {
			outdate=request.getParameter("checkouts");
			session.setAttribute("checkouts", outdate);
		} else if(session.getAttribute("checkouts")!=null) {
			outdate=(String)session.getAttribute("checkouts");
		} else {
			session.removeAttribute("checkouts");
			outdate="";
		}
		
		if(request.getParameter("bookid")!=null) {
			id=request.getParameter("bookid");
			session.setAttribute("bookid", id);
		} else if(session.getAttribute("bookid")!=null) {
			id=(String)session.getAttribute("bookid");
		} else {
			session.removeAttribute("bookid");
			id="";
		}
		
		if(request.getParameter("a")!=null) {
			System.out.println("파라미터 a 값 : "+request.getParameter("a"));
			session.removeAttribute("bookid");
			id="";
			session.removeAttribute("checkins");
			indate="";
			session.removeAttribute("checkouts");
			outdate="";
			session.removeAttribute("booknums");
			booknums="";
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		
		int count=abs.getAllCount(id, booknums, indate, outdate);
		paging.setTotalCount(count);
		paging.paging();
		
		ArrayList<BookVO> booklist = abs.getAllBookList(paging,id,booknums,indate,outdate);
		
		mav.addObject("booklist",booklist);
		mav.addObject("paging", paging);
		mav.addObject("total",count);
		mav.addObject("bookid",id);
		mav.addObject("booknums",booknums);
		mav.addObject("checkins",indate);
		mav.addObject("checkouts",outdate);
		
		mav.setViewName("admin/book/adminbookchecklist");
		
		return mav;
	}
	
	
	
	
	@RequestMapping("/adminbooklistdetail")
	public ModelAndView adminbooklistdetail(@RequestParam("bdseq") int bdseq,
			@RequestParam("gotonum") int gotonum) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("gotonum",gotonum);
		mav.addObject("booklist",abs.getBookDetail(bdseq));
		mav.setViewName("admin/book/listbookcheck");
		
		return mav;
	}
	
	
	
	
	@RequestMapping("/adminbookcancel")
	public String adminbookcancel(@RequestParam("bdseq") int bdseq,
			@RequestParam("gotonum") int gotonum) {
		
		String url = "redirect:/adminbookchecklist";
		abs.adminBookCancel(bdseq);
		
		if(gotonum==2) url="redirect:/adminbookcancelpage";
		
		return url;
	}
	
	
	
	
	@RequestMapping("/adminbookcancelpage")
	public ModelAndView adminbookcancelpage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		int page = 1;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page")!=null) {
			page = (int)session.getAttribute("page");
		} else {
			page= 1;
			session.removeAttribute("page");
		}
		
		int count =abs.getCancelAllCount();
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		paging.paging();
		
		mav.addObject("booklist",abs.getAdminCancelList(paging));
		mav.addObject("total",count);
		mav.addObject("paging",paging);
		
		mav.setViewName("admin/book/adminbookcancelpage");
		
		return mav;
	}

}
