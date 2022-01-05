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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg.dto.MemberVO;
import com.ezen.spg.dto.QnaVO;
import com.ezen.spg.service.QnaService;



@Controller
public class QnaController {

	@Autowired
	QnaService qs;
	
	
	@RequestMapping("qnaList")
	public ModelAndView qna_list(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    ModelAndView mav = new ModelAndView();
	    
	    if (mvo == null) mav.setViewName("member/login");
	    else {
	    	mav.addObject("qnaList", qs.listQna(mvo.getId()) );
	    	mav.setViewName("qna/qnaList");
	    }
		return mav;
	}
	
	
	@RequestMapping("qnaWriteForm")
	public String qna_writre_form(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    MemberVO mvo= (MemberVO) session.getAttribute("loginUser");    	    
	    if (mvo == null) return "member/login";
	    return "qna/qnaWrite";
	}
	
	
	
	
	@RequestMapping("qnaWrite")
	public ModelAndView qna_write( @ModelAttribute("dto") @Valid QnaVO qnavo,
			BindingResult result,  HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(result.getFieldError("title") != null ) {
			mav.addObject("message", "제목을 입력하세요");
			mav.setViewName("qna/qnaWrite");
			return mav;
		}else if(result.getFieldError("content") != null ) {
			mav.addObject("message", "내용을 입력하세요");
			mav.setViewName("qna/qnaWrite");
			return mav;
		}
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    if (mvo == null) mav.setViewName("member/login");
	    else {
	    	qnavo.setId(mvo.getId());
	    	qs.insertQna(qnavo);
	    }
		mav.setViewName("redirect:/qnaList");
		return mav;
	}
	
	
	
	
	
	@RequestMapping("qnaView")
	public ModelAndView qna_view(Model model, HttpServletRequest request,
			@RequestParam("qnaseq") int qnaseq) {
		HttpSession session = request.getSession();
	    MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
	    ModelAndView mav = new ModelAndView();
		if (mvo == null) mav.setViewName("member/login");
		else {
			mav.addObject("qnaVO", qs.getQna(qnaseq));
			mav.setViewName("qna/qnaView");
		}
		return mav;
	}
	
	
	@RequestMapping("contact")
	public String qna_contact(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    MemberVO mvo= (MemberVO) session.getAttribute("loginUser");    	    
	    if (mvo == null) return "member/login";
	    
	 
	    return "qna/contact";
	
	
}
	
	@RequestMapping("qnaUpdateForm")
	public ModelAndView qna_update_form( HttpServletRequest request,
			@RequestParam("qnaseq") int qnaseq) {
		ModelAndView mav= new ModelAndView();
		HttpSession session = request.getSession();
	    MemberVO mvo= (MemberVO) session.getAttribute("loginUser");    	    
	    if (mvo == null) mav.setViewName( "member/login");
	    QnaVO qvo=qs.getQna(qnaseq);
	    System.out.println("qnaseq"+ qnaseq);
	    mav.addObject("qnaVO",qvo);
	    mav.setViewName("qna/qnaUpdateForm");
	    return mav;
	
	
}
	@RequestMapping("qnaUpdate")
	public ModelAndView qna_update(@ModelAttribute("dto") @Valid QnaVO qnavo,
			BindingResult result, Model model, HttpServletRequest request,
			@RequestParam("qnaseq") String qnaseq){
			ModelAndView mav= new ModelAndView();
		
			System.out.println(1);
			  if( result.getFieldError("title")!=null) {
				  mav.addObject("message", "제목을 입력하셔야합니다"); 
				  mav.setViewName("qna/qnaUpdateForm"); return mav; }
			  else  if(result.getFieldError("content") != null ) {
				  mav.addObject("message", "내용을 입력하세요"); 
			  mav.setViewName("qna/qnaUpdateForm"); return mav; }
			 
			  	System.out.println(2);
			HttpSession session= request.getSession();
			MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		    if (mvo == null) mav.setViewName("member/login");
		    else {
		    	System.out.println(3);
	
		    	qnavo.setQnaseq(Integer.parseInt(qnaseq));
		    	
		    	System.out.println(4);
		    	System.out.println("qnaseq :"+request.getParameter("qnaseq"));
		    	System.out.println(request.getParameter("content"));
		    	System.out.println(request.getParameter("title"));
		    	qs.updateQna(qnavo);
		    	System.out.println(5);
		    	
		    }
			mav.setViewName("redirect:/qnaList");
			return mav;	
	
	}
	

	@RequestMapping("qnaDelete")
	public String qna_delete(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
	    MemberVO mvo= (MemberVO) session.getAttribute("loginUser");    	    
	    if (mvo == null) return "loginForm";
	    int qnaseq = Integer.parseInt(request.getParameter("qnaseq"));
	    qs.deleteQna(qnaseq);
	    
	    return "redirect:/qnaList";
	
	
}
}
