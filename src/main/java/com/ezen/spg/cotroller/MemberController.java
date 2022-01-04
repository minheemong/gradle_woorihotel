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
	
	@RequestMapping(value="joinForm", method=RequestMethod.POST)
	public String joinForm() {
		return "member/joinForm";
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
	
	@RequestMapping("join")
	public String join(@ModelAttribute("dto") @Valid MemberVO membervo, BindingResult result,
			Model model, HttpServletRequest request, @RequestParam(value="reid", required=false) String reid,
			@RequestParam(value="pwdCheck", required=false) String pwdCheck) {
		if(result.getFieldError("id")!=null) {
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			model.addAttribute("reid",reid);
			return "member/joinForm";
		} else if(result.getFieldError("pwd")!=null) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			model.addAttribute("reid",reid);
			return "member/joinForm";
		}  else if(result.getFieldError("name")!=null) {
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
			return "member/joinForm";
		} else if(result.getFieldError("email")!=null) {
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
			return "member/joinForm";
		} else if(pwdCheck==null|| (pwdCheck!=null&& !pwdCheck.equals(membervo.getPwd() ))) {
			model.addAttribute("message","비밀번호 확인이 일치하지 않았습니다");
			return "member/joinForm";
		} else if(reid==null|| (reid!=null&& !reid.equals(membervo.getId() ))) {
			model.addAttribute("message","아이디 중복확인을 하지 않았습니다");
			return "member/joinForm";
		} 
			membervo.setAddress(request.getParameter("addr1")+" "+request.getParameter("addr2"));
			
			ms.insertMember(membervo);
			model.addAttribute("message", "회원가입이 완료되었어요. 로그인하세요");
			//return "redirect:/loginForm";
			return "member/login";
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
