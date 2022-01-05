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
import com.ezen.spg.service.AdminService;


@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	
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
			mav.addObject("message" , "패쓰워드를 입력하세요");
			mav.setViewName("admin/adminloginForm");
			return mav;
		}

		
		adminvo=as.workerCheck(workId);
		
		if( adminvo == null) {
				mav.addObject("message","없는아이디입니다");
				mav.setViewName("/admin/adminloginForm");
		} else  if (adminvo.getPwd() == null) {
			
				mav.addObject("message","DB 오류. 관리자에게 문의하세요");
				mav.setViewName("/adminloginForm");
		} else if (!adminvo.getPwd().equals(workPwd)) {
			
			mav.addObject("message","암호가 다릅니다");
			mav.setViewName("admin/adminLoginForm");
    	
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

}
