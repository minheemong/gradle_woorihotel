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
import com.ezen.spg.service.MemberService;


@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	

	@RequestMapping("/")
	public ModelAndView index(Model model) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("loginForm")
	public String login_form() {
		return"member/login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			Model model, HttpServletRequest request) {
		if(result.getFieldError("id")!=null) {
			model.addAttribute("message",result.getFieldError("id").getDefaultMessage());
			return "member/login";
		} else if(result.getFieldError("pwd")!=null) {
			model.addAttribute("message",result.getFieldError("pwd").getDefaultMessage());
			return "member/login";
		}
		
		MemberVO mvo = ms.getMember(membervo.getId());
		if(mvo.getId()==null) {
			model.addAttribute("message","아이디가 없습니다");
			return "member/login";
		} else if(mvo.getPwd()==null) {
			model.addAttribute("message","비밀번호가 없습니다");
			return "member/login";
		} else if(!mvo.getPwd().equals(membervo.getPwd())) {
			model.addAttribute("message","비밀번호가 맞지 않습니다");
			return "member/login";
		} else if(mvo.getPwd().equals(membervo.getPwd())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/";
		} else {
			model.addAttribute("message","알 수 없는 이유로 로그인이 불가합니다");
			return "member/login";
		}
	}
	
	@RequestMapping("findIdPw")
	public String find_id_pwd() {
		return"member/findIdPw";
	}
	
	@RequestMapping("findIdForm")
	public String find_id_form() {
		return"member/findIdForm";
	}
	
	@RequestMapping(value="/findIdStep1", method=RequestMethod.POST)
	public ModelAndView find_id_form1(@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = ms.confirmPhone1(name,phone);
		if(mvo == null) {
			mav.addObject("msg", "이름과 전화번호가 일치하는 회원이 없습니다.");
			mav.addObject("member", mvo);
			mav.setViewName("member/findIdForm");
		}else {
			// 인증번호 전송
			mav.setViewName("member/findIdConfirmNumber"); // 인증번호 입력 화면으로 이동
			mav.addObject("member", mvo);
		}
		return mav;	
		}
	
	@RequestMapping(value="/findIdStep2", method=RequestMethod.POST)
	public ModelAndView find_id_form2(@RequestParam("name") String name,
			@RequestParam("id") String id,
			@RequestParam("phone") String phone,
			@RequestParam("confirmNum") String confirmNum,
			Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO mvo = new MemberVO();
		mvo.setId(id);
		mvo.setName(name);
		mvo.setPhone(phone);
			
		if(!confirmNum.equals("0000")) {
			request.setAttribute("msg", "인증번호가 맞지 않습니다");
			mav.setViewName("member/findIdConfirmNumber");
		} else {
			mav.setViewName("member/viewId");
			request.setAttribute("msg", "조회한 회원의 아이디는 "+ mvo.getId()+ " 입니다");
		}
		mav.addObject("member", mvo);
		return mav;
	}
	
	@RequestMapping("findPwForm")
	public String find_pw_form() {
		return"member/findPwForm";
	}
	
	
	@RequestMapping(value="/findPwStep1", method=RequestMethod.POST)
	public ModelAndView find_pw_form1(@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			@RequestParam("id") String id,
			Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = ms.confirmPhone2(id,name,phone);
		if(mvo == null) {
			mav.addObject("msg", "아이디와 이름, 전화번호가 일치하는 회원이 없습니다.");
			mav.addObject("member", mvo);
			mav.setViewName("member/findPwForm");
		}else {
			// 인증번호 전송
			mav.setViewName("member/findPwConfirmNumber"); // 인증번호 입력 화면으로 이동
			mav.addObject("member", mvo);
		}
		return mav;	
		}
	
	@RequestMapping(value="/findPwStep2", method=RequestMethod.POST)
	public ModelAndView find_pw_form2(@RequestParam("name") String name,
			@RequestParam("id") String id,
			@RequestParam("phone") String phone,
			@RequestParam("confirmNum") String confirmNum,
			Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO mvo = new MemberVO();
		mvo.setId(id);
		mvo.setName(name);
		mvo.setPhone(phone);
			
		if(!confirmNum.equals("0000")) {
			request.setAttribute("msg", "인증번호가 맞지 않습니다");
			mav.setViewName("member/findIdConfirmNumber");
		} else {
			mav.setViewName("member/resetPw");
		}
		mav.addObject("member", mvo);
		return mav;
	}
	
	@RequestMapping(value="/resetPw", method=RequestMethod.POST)
	public ModelAndView reset_pw(@RequestParam("pwd") String pwd,
			@RequestParam("id") String id,
			Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO mvo = new MemberVO();
		mvo.setId(id);
		mvo.setPwd(pwd);
		
		// 패스워드 수정
		ms.resetPw(mvo);
		
		mav.addObject("member", mvo);
		mav.setViewName("member/resetPwComplete");
		return mav;
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "redirect:/"; 
	}
	
	@RequestMapping("contract")
	public String contract() {
		return "member/contract";
	}
	
	@RequestMapping(value="/joinForm", method=RequestMethod.POST)
	public String joinForm() {
		return "member/join";
	}
	
	@RequestMapping("idCheckForm")
	public ModelAndView id_check_form(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO mvo = ms.getMember(id);
		int result=0;
		if(mvo==null) result=-1;
		else result=1;
		mav.addObject("result",result);
		mav.addObject("id",id);
		mav.setViewName("member/idcheck");
		return mav;
	}
	
	@RequestMapping("findZipNum")
	public ModelAndView find_zip(@RequestParam(value="dong", required=false) String dong) {
		ModelAndView mav = new ModelAndView();
		if(dong!=null&&dong.trim().equals("")==false) {
			mav.addObject("addressList", ms.selectAddressByDong(dong));
		}
		mav.setViewName("member/findZipNum");
		return mav;
	}
	
	@RequestMapping("joinComplete")
	public String joinComplete(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			Model model, HttpServletRequest request, @RequestParam(value="reid", required=false) String reid,
			@RequestParam(value="pwd", required=false) String pwd,
			@RequestParam(value="id", required=false) String id,
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="addr1", required=false) String addr1,
			@RequestParam(value="addr2", required=false) String addr2,
			@RequestParam(value="zip_num", required=false) String zip_num,
			@RequestParam(value="phone", required=false) String phone,
			@RequestParam(value="pwdCheck", required=false) String pwdCheck) {
		model.addAttribute("reid",reid);
		if(result.getFieldError("id")!=null) {
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("phone",phone);
			return "member/join";
		} else if(result.getFieldError("pwd")!=null) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("id",id);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("phone",phone);
			return "member/join";
		}  else if(result.getFieldError("name")!=null) {
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("id",id);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("phone",phone);
			return "member/join";
		} else if(result.getFieldError("email")!=null) {
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("id",id);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("phone",phone);
			return "member/join";
		} else if(result.getFieldError("phone")!=null) {
			model.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("id",id);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("email",email);
			return "member/join";
		} else if(result.getFieldError("zip_num")!=null) {
			model.addAttribute("message", result.getFieldError("zip_num").getDefaultMessage());
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("id",id);
			model.addAttribute("phone",phone);
			model.addAttribute("email",email);
			return "member/join";
		} else if(result.getFieldError("addr1")!=null) {
			model.addAttribute("message", result.getFieldError("addr1").getDefaultMessage());
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("phone",phone);
			model.addAttribute("id",id);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("email",email);
			return "member/join";
		} else if(pwdCheck==null|| (pwdCheck!=null&& !pwdCheck.equals(membervo.getPwd() ))) {
			model.addAttribute("message","비밀번호 확인이 일치하지 않았습니다");
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("id",id);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("phone",phone);
			return "member/join";
		} else if(reid==null|| (reid!=null&& !reid.equals(membervo.getId() ))) {
			model.addAttribute("message","아이디 중복확인을 하지 않았습니다");
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			model.addAttribute("addr1",addr1);
			model.addAttribute("addr2",addr2);
			model.addAttribute("zip_num",zip_num);
			model.addAttribute("phone",phone);
			return "member/join";
		} 
			membervo.setAddress(request.getParameter("addr1")+" "+request.getParameter("addr2"));
			
			ms.insertMember(membervo);
			model.addAttribute("message", "회원가입이 완료되었어요. 로그인하세요");
			
			 
		      MemberVO mto = new MemberVO();
		      mto.setId(id);
		      mto.setPwd(pwd);
		      mto.setName(name);
		      mto.setEmail(email);
		      mto.setZip_num(zip_num);
		      mto.setAddress( addr1 + " " + addr2 );
		      mto.setPhone(phone);
		    HttpSession session=request.getSession();
		    session.setAttribute("joinName", mto);
			return "member/joinComplete";
	}
	@RequestMapping("joinCom")
	public String joinCom(Model model, HttpServletRequest request) {
		
		 HttpSession session = request.getSession();
	      //session.invalidate();
	      session.removeAttribute("joinName");
	      return "redirect:/loginForm";
	}
	
	@RequestMapping(value="memeberEditForm")
	public ModelAndView memeber_edit_form(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		String addr = mvo.getAddress(); //주소 추출
		int k1 = addr.indexOf(" "); // 첫 번째 공백의 위치 찾음
		int k2 = addr.indexOf(" ",k1+1); // 첫 번째 공백 위치의 다음위치부터 두 번째 공백 위치 찾음
		int k3 = addr.indexOf(" ",k2+1); // 두 번째 공백 위치의 다음위치부터 세 번째 공백 위치 찾음
		// 서울시 마포구 대현동 115-15 세 번째 공백 위치 k3값 ->11 (0부터 시작)
		String addr1 =addr.substring(0,k3); // 맨 앞부터 세 번째 공백 위치 바로 전까지 - 주소 앞부분
		String addr2 =addr.substring(k3+1); // 세 번째 공백 뒷글자부터 맨 끝까지 - 주소 뒷부분
		
		request.setAttribute("dto", mvo);
		mav.addObject("addr1",addr1);
		mav.addObject("addr2",addr2);
		mav.setViewName("member/meberUpdateForm");
		return mav;
	}
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.POST)
	public String memberUpdate(@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, Model model, HttpServletRequest request) {
		request.setAttribute("addr1", request.getParameter("addr1"));
		request.setAttribute("addr2", request.getParameter("addr2"));
		String pwdCheck = request.getParameter("pwdCheck");
		
		if(result.getFieldError("pwd")!=null) {
			request.setAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			return "member/memberUpdateForm";
		} else if(result.getFieldError("name")!=null) {
			request.setAttribute("message", result.getFieldError("name").getDefaultMessage());
			return "member/memberUpdateForm";
		} else if(result.getFieldError("email")!=null) {
			request.setAttribute("message", result.getFieldError("email").getDefaultMessage());
			return "member/memberUpdateForm";
		} else if(pwdCheck!=null && (!pwdCheck.equals(membervo.getPwd()))) {
			request.setAttribute("message", "비밀번호 확인이 일치하지 않습니다");
			return "member/memberUpdateForm";
		} else {
			membervo.setAddress(request.getParameter("addr1") + " " + request.getParameter("addr2"));
			ms.updateMember(membervo);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", membervo);
			return "redirect:/";
		}
	}
}
