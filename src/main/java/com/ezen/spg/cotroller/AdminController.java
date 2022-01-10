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

import com.ezen.spg.dto.AdminVO;
import com.ezen.spg.dto.MemberVO;
import com.ezen.spg.dto.Paging;
import com.ezen.spg.dto.QnaVO;
import com.ezen.spg.service.AdminBookService;
import com.ezen.spg.service.AdminService;
import com.ezen.spg.service.MemberService;
import com.ezen.spg.service.QnaService;



@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	@Autowired
	AdminBookService abs;
	@Autowired
	QnaService qs;
	@Autowired
	MemberService ms;
	
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
		
		
			String key ="";
			if( request.getParameter("key") != null ) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if( session.getAttribute("key")!= null ) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}
			
			String order="";
			if(request.getParameter("tag") != null ) {
				order = request.getParameter("tag");
				session.setAttribute("order", order);
			} else if( session.getAttribute("order")!= null ) {
				order = (String)session.getAttribute("order");
			} else {
				session.removeAttribute("order");
				order = "";
			}
			
			
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = as.getAllCount(key,"qna", "content");
			paging.setTotalCount(count);
			paging.paging();
		
			System.out.println("pagig order :"+ order);
		
				
			mav.addObject("qnaList" , as.listQna(paging, key, order));		
			mav.addObject("order",order);
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

	@RequestMapping("adminMemberList")
	public ModelAndView adminMemberList(HttpServletRequest request){
		ModelAndView mav= new ModelAndView();
		HttpSession session=request.getSession();
		
		AdminVO avo=(AdminVO)session.getAttribute("loginAdmin");
	
		
		if(avo == null)
			mav.setViewName("admin/adminloginForm");
		else {
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
			
			int count = as.getAllCount(key,"hotelmember","name");
			paging.setTotalCount(count);
			paging.paging();
		


		
			
			mav.addObject("memberList" , as.listMember(paging, key));
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.setViewName("admin/member/adminMemberList");		
		}
		return mav;
	}
	
	@RequestMapping("adminMemberDetailBook")
	public ModelAndView adminMemberDetailBook(HttpServletRequest request,
			@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			@RequestParam("id") String id) {
		ModelAndView mav= new ModelAndView();

		String url = "admin/member/adminMemberDetailBook";
		HttpSession session = request.getSession();
	    AdminVO mvo = (AdminVO) session.getAttribute("loginAdmin");
		
	    int page=1;
	    if( request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
			session.setAttribute("id",id);
		} else if( session.getAttribute("page")!= null ) {
			page = (int) session.getAttribute("page");
			session.setAttribute("id",id);
		} else {
			page = 1;
			session.removeAttribute("page");
			session.setAttribute("id",id);
		}
	
	    String booknums="";
		String indate="";
		String outdate="";
	    if(mvo==null) mav.setViewName("admin/adminloginForm");
		Paging paging = new Paging();
		paging.setPage(page);
		
		int count=abs.getAllCount(id, booknums, indate, outdate);
		paging.setTotalCount(count);
		paging.paging();
		
		mav.addObject("paging", paging);
		mav.addObject("action", "adminMemberDetailBook?id="+id);
		mav.addObject("dto", ms.getMember(id));
		mav.addObject("list", abs.getMemberBook(id, paging, booknums, indate, outdate));
		mav.setViewName("admin/member/adminMemberDetailBook");
		
	    return mav;
	}
	
	@RequestMapping("adminMemberDetail")
	public ModelAndView adminMemberDetail(HttpServletRequest request,
			@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			@RequestParam("id") String id) {
			
		ModelAndView mav= new ModelAndView();

		HttpSession session = request.getSession();
	    AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
	    if(avo==null) mav.setViewName("admin/adminloginForm");
	
	    MemberVO mvo = ms.getMember(id);
	    String addr = mvo.getAddress(); //주소 추출
		int k1 = addr.indexOf(" "); // 첫 번째 공백의 위치 찾음
		int k2 = addr.indexOf(" ",k1+1); // 첫 번째 공백 위치의 다음위치부터 두 번째 공백 위치 찾음
		int k3 = addr.indexOf(" ",k2+1); // 두 번째 공백 위치의 다음위치부터 세 번째 공백 위치 찾음
		// 서울시 마포구 대현동 115-15 세 번째 공백 위치 k3값 ->11 (0부터 시작)
		String addr1 =addr.substring(0,k3); // 맨 앞부터 세 번째 공백 위치 바로 전까지 - 주소 앞부분
		String addr2 =addr.substring(k3+1); // 세 번째 공백 뒷글자부터 맨 끝까지 - 주소 뒷부분
		
		mav.addObject("addr1",addr1);
		mav.addObject("addr2",addr2);
		mav.addObject("dto", ms.getMember(id));
		
		mav.setViewName("admin/member/adminMemberDetail");
		
	    return mav;
	}
	
	@RequestMapping(value="/adminMemberUpdate", method=RequestMethod.POST)
	public ModelAndView adminMemberUpdate(HttpServletRequest request,
			@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			@RequestParam("id") String id) {
			
		ModelAndView mav= new ModelAndView();

		HttpSession session = request.getSession();
	    AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
	    if(avo==null) mav.setViewName("admin/adminloginForm");
	    
	    if(result.getFieldError("email")!=null) {
			mav.addObject("message", result.getFieldError("email").getDefaultMessage());
			String addr = membervo.getAddress(); //주소 추출
			int k1 = addr.indexOf(" "); // 첫 번째 공백의 위치 찾음
			int k2 = addr.indexOf(" ",k1+1); // 첫 번째 공백 위치의 다음위치부터 두 번째 공백 위치 찾음
			int k3 = addr.indexOf(" ",k2+1); // 두 번째 공백 위치의 다음위치부터 세 번째 공백 위치 찾음
			// 서울시 마포구 대현동 115-15 세 번째 공백 위치 k3값 ->11 (0부터 시작)
			String addr1 =addr.substring(0,k3); // 맨 앞부터 세 번째 공백 위치 바로 전까지 - 주소 앞부분
			String addr2 =addr.substring(k3+1); // 세 번째 공백 뒷글자부터 맨 끝까지 - 주소 뒷부분
			
			mav.addObject("addr1",addr1);
			mav.addObject("addr2",addr2);
			mav.setViewName("admin/member/adminMemberDetail");
		} else if(result.getFieldError("phone")!=null) {
			mav.addObject("message", result.getFieldError("phone").getDefaultMessage());
			String addr = membervo.getAddress(); //주소 추출
			int k1 = addr.indexOf(" "); // 첫 번째 공백의 위치 찾음
			int k2 = addr.indexOf(" ",k1+1); // 첫 번째 공백 위치의 다음위치부터 두 번째 공백 위치 찾음
			int k3 = addr.indexOf(" ",k2+1); // 두 번째 공백 위치의 다음위치부터 세 번째 공백 위치 찾음
			// 서울시 마포구 대현동 115-15 세 번째 공백 위치 k3값 ->11 (0부터 시작)
			String addr1 =addr.substring(0,k3); // 맨 앞부터 세 번째 공백 위치 바로 전까지 - 주소 앞부분
			String addr2 =addr.substring(k3+1); // 세 번째 공백 뒷글자부터 맨 끝까지 - 주소 뒷부분
			
			mav.addObject("addr1",addr1);
			mav.addObject("addr2",addr2);
			mav.setViewName("admin/member/adminMemberDetail");
		}  else if(request.getParameter("addr1").equals("") || result.getFieldError("zip_num")!=null) {
			mav.addObject("message", "주소를 입력해주세요");
			mav.setViewName("admin/member/adminMemberDetail");
		} else {
			// 이미 만들어져있는 메서드 사용
			request.setAttribute("message", "정상적으로 수정되었습니다");
			membervo.setAddress(request.getParameter("addr1") + " " + request.getParameter("addr2"));
			
			ms.updateMember(membervo);
			String addr = membervo.getAddress(); //주소 추출
			int k1 = addr.indexOf(" "); // 첫 번째 공백의 위치 찾음
			int k2 = addr.indexOf(" ",k1+1); // 첫 번째 공백 위치의 다음위치부터 두 번째 공백 위치 찾음
			int k3 = addr.indexOf(" ",k2+1); // 두 번째 공백 위치의 다음위치부터 세 번째 공백 위치 찾음
			// 서울시 마포구 대현동 115-15 세 번째 공백 위치 k3값 ->11 (0부터 시작)
			String addr1 =addr.substring(0,k3); // 맨 앞부터 세 번째 공백 위치 바로 전까지 - 주소 앞부분
			String addr2 =addr.substring(k3+1); // 세 번째 공백 뒷글자부터 맨 끝까지 - 주소 뒷부분
			
			mav.addObject("addr1",addr1);
			mav.addObject("addr2",addr2);
			mav.setViewName("admin/member/adminMemberDetail");
		}
	    return mav;
	}
}
