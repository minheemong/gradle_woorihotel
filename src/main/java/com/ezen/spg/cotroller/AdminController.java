package com.ezen.spg.cotroller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg.dto.AdminVO;

import com.ezen.spg.dto.Paging;
import com.ezen.spg.dto.QnaVO;
import com.ezen.spg.service.AdminService;
import com.ezen.spg.service.QnaService;



@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	@Autowired
	QnaService qs;
	
	@RequestMapping("/a")
	public String adminmain() {
		return "admin/adminmain";
	}
	@RequestMapping("adminloginForm")
	public String admin_login_form() {
		return"admin/adminloginForm";
	}
	
	@RequestMapping("adminlogin")
	public ModelAndView admin_login(@ModelAttribute("dto") @Valid AdminVO adminvo , 
	BindingResult result,Model model, HttpServletRequest request,
			@RequestParam("workId") String workId,
			@RequestParam("workPwd") String workPwd) {
		
		ModelAndView mav=new ModelAndView();
		if( workId == null || workId.equals("") ) {
			mav.addObject("message" , "아이디를 입력하세요");
			mav.setViewName("admin/adminloginForm");
			return mav;
		}else if( workPwd == null || workPwd.equals("") ) {
			mav.addObject("message" , "비밀번호를 입력하세요");
			mav.setViewName("admin/adminloginForm");
			return mav;
		}
		
		

		
		adminvo=as.workerCheck(workId);
		
		if( adminvo == null) {
				mav.addObject("message","관리자 정보가 없습니다");
				mav.setViewName("admin/adminloginForm");
		} else  if (adminvo.getId() == null) {
			mav.addObject("message","아이디가 없습니다");
			mav.setViewName("admin/adminloginForm");
		} else  if (adminvo.getPwd() == null) {
				mav.addObject("message","비밀번호가 없습니다");
				mav.setViewName("admin/adminloginForm");
		} else if (!adminvo.getPwd().equals(workPwd)) {
			mav.addObject("message","비밀번호가 다릅니다");
			mav.setViewName("admin/adminloginForm");
		}else {
			HttpSession session = request.getSession();
	    	session.setAttribute("loginAdmin", adminvo);
	    	mav.setViewName("admin/adminmain");
		}
		
		
		
		return mav;
	}
	
	@RequestMapping("adminlogout")
	public String adminlogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginAdmin");
		return "redirect:/a"; 
	}
	
	
	
	
	@RequestMapping("adminQnaList")
	public ModelAndView adminQnaList(HttpServletRequest request){
		ModelAndView mav= new ModelAndView();
		HttpSession session=request.getSession();
		
		AdminVO avo=(AdminVO)session.getAttribute("loginAdmin");
	
		
		if(avo == null)
			mav.setViewName("admin/adminloginForm");
		else {

			if( request.getParameter("first") != null ) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			int page = 1;
			if( request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if( session.getAttribute("page")!= null ) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}
		
		
			String key = "";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = as.getAllCount(key);
			paging.setTotalCount(count);
			paging.paging();
		


		
			
			mav.addObject("qnaList" , as.listQna(paging, key));
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.setViewName("admin/qna/adminqnaList");		
	}
		return mav;
}
	
	@RequestMapping("adminQnaDetail")
	public ModelAndView admin_qna_detail(HttpServletRequest request,
			@RequestParam("qnaseq") String qnaseq) {
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
	    AdminVO mvo = (AdminVO) session.getAttribute("loginAdmin");
	    System.out.println(1);
	    if(mvo==null) mav.setViewName("admin/adminloginForm");
	    else {
	        System.out.println(2);
	    	mav.addObject("qnaDto", qs.getQna(Integer.parseInt(qnaseq)));
	        System.out.println(3);
			mav.setViewName("admin/qna/adminqnaDetail");
		    System.out.println(4);
		    
	    }
		return mav;
	}
	@RequestMapping("adminQnaRepsave")
	public ModelAndView admin_qna_repsave(HttpServletRequest request
	,@RequestParam("qnaseq") String qnaseq) {
		ModelAndView mav= new ModelAndView();

		HttpSession session = request.getSession();
	    AdminVO mvo = (AdminVO) session.getAttribute("loginAdmin");

	    
	    if(mvo==null) mav.setViewName("admin/adminloginForm");
	    else {
	    			QnaVO qvo= new QnaVO();
	    	
	    			qvo.setQnaseq(Integer.parseInt(qnaseq));
	    			 qvo.setReply(request.getParameter("reply"));
	    			qs.updateQnaReply(qvo);
	    			
	    			mav.setViewName("redirect:/adminQnaList");
	    }
		return mav;
	   
}
}
