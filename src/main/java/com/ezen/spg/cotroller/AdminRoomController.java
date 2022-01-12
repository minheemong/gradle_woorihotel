package com.ezen.spg.cotroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
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
import com.ezen.spg.dto.HotelVO;
import com.ezen.spg.dto.MemberVO;
import com.ezen.spg.dto.Paging;
import com.ezen.spg.service.AdminRoomService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AdminRoomController {
	
	@Autowired
	AdminRoomService ars;
	
	@Autowired
	ServletContext context;
	
	
	@RequestMapping("adminRoomList")
	public ModelAndView adminRoomList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
	    AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
	    if(avo==null) mav.setViewName("admin/adminloginForm");
	    else {
			int page = 1;
			String key="";
			
			if(request.getParameter("key")!=null) {
				key=request.getParameter("key");
				session.setAttribute("key", key);
			} else if(session.getAttribute("key")!=null) {
				key=(String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key="";
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
			
			if(request.getParameter("a")!=null) {
				System.out.println("파라미터 a 값 : "+request.getParameter("a"));
				session.removeAttribute("key");
				key="";
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count=ars.getAllCount(key);
			paging.setTotalCount(count);
			paging.paging();
			
			ArrayList<HotelVO> list = ars.getAllRoomList(paging,key);
			
			mav.addObject("roomViewList",list);
			mav.addObject("paging", paging);
			mav.addObject("total",count);
			mav.addObject("key",key);
			
			mav.setViewName("admin/room/adminRoomList");
	    }
		return mav;
	}
	

	@RequestMapping("adminRoomDetail")
	public ModelAndView adminRoomDetail(HttpServletRequest request,
			@ModelAttribute("dto") @Valid HotelVO hotelvo, BindingResult result,
			@RequestParam("hotelnum") String hotelnum) {
			
		ModelAndView mav= new ModelAndView();

		HttpSession session = request.getSession();
	    AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
	    if(avo==null) mav.setViewName("admin/adminloginForm");
	
		mav.addObject("dto", ars.getRoom(hotelnum));
		
		mav.setViewName("admin/room/adminRoomDetail");
		
	    return mav;
	}
	
	@RequestMapping("/selectimg")
	public String selectimg() {
		return "admin/room/selectimg";
	}
	
	@RequestMapping(value="/fileupload" , method = RequestMethod.POST)
	public String fileupload(Model model, HttpServletRequest request) {
		String path = context.getRealPath("/images");
		System.out.println("============ uploadFilePath = " + path);
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			model.addAttribute("image", multi.getFilesystemName("image") );	
			model.addAttribute("originalFilename", multi.getOriginalFileName("image"));
		} catch (IOException e) {  e.printStackTrace();
		}
		return "admin/room/completeupload";
	}

	/*
	@RequestMapping(value="/adminRoomUpdate", method=RequestMethod.POST)
	public ModelAndView adminRoomUpdate(HttpServletRequest request,
			@ModelAttribute("dto") @Valid HotelVO hotelvo, BindingResult result,
			@RequestParam("hotelnum") String hotelnum) {
			
		ModelAndView mav= new ModelAndView();
	
		HttpSession session = request.getSession();
	    AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
	    if(avo==null) mav.setViewName("admin/adminloginForm");
		else {
		    if(result.getFieldError("kind")!=null) {
				mav.addObject("message", result.getFieldError("email").getDefaultMessage());
				mav.setViewName("admin/room/adminRoomDetail");
			} else  if(result.getFieldError("persons")!=null) {
				mav.addObject("message", result.getFieldError("persons").getDefaultMessage());
				mav.setViewName("admin/room/adminRoomDetail");
			} else  if(result.getFieldError("price")!=null) {
				mav.addObject("message", result.getFieldError("price").getDefaultMessage());
				mav.setViewName("admin/room/adminRoomDetail");
			} else  if(result.getFieldError("roomsize")!=null) {
				mav.addObject("message", result.getFieldError("roomsize").getDefaultMessage());
				mav.setViewName("admin/room/adminRoomDetail");
			} else  if(result.getFieldError("img")!=null) {
				mav.addObject("message", result.getFieldError("img").getDefaultMessage());
				mav.setViewName("admin/room/adminRoomDetail");
			} else {
				// 이미 만들어져있는 메서드 사용
				request.setAttribute("message", "정상적으로 수정되었습니다");
				ars.updateRoom(hotelvo);
				mav.setViewName("admin/room/adminRoomDetail");
			}
		}
	    return mav;
	}
	*/
	@RequestMapping(value="/adminRoomDelete", method=RequestMethod.POST)
	public ModelAndView adminRoomDelete(HttpServletRequest request,
			@RequestParam("hotelnum") String hotelnum) {
			
		ModelAndView mav= new ModelAndView();

		HttpSession session = request.getSession();
	    AdminVO avo = (AdminVO) session.getAttribute("loginAdmin");
	    if(avo==null) mav.setViewName("admin/adminloginForm");
	    else {
	    	ars.deleteRoom(hotelnum);
	    	mav.setViewName("redirect:/adminRoomList");
	    }
	    return mav;
	}
	
	@RequestMapping(value="/adminRoomInsertForm")
	public String adminRoomInsertForm() {
		return "admin/room/adminRoomInsertForm";
	}
	
	@RequestMapping(value="/adminRoomInsert", method=RequestMethod.POST)
	public String adminRoomInsert(@ModelAttribute("dto") @Valid HotelVO hotelvo, BindingResult result,
			Model model, HttpServletRequest request) {
		if(result.getFieldError("hotelnum")!=null) {
			model.addAttribute("message", result.getFieldError("hotelnum").getDefaultMessage());
			model.addAttribute("kind",hotelvo.getKind());
			model.addAttribute("persons",hotelvo.getPersons());
			model.addAttribute("price",hotelvo.getPrice());
			model.addAttribute("roomsize",hotelvo.getRoomsize());
			model.addAttribute("img",hotelvo.getImg());
			return "admin/room/adminRoomInsertForm";
		} else if(result.getFieldError("kind")!=null) {
			model.addAttribute("message", result.getFieldError("kind").getDefaultMessage());
			model.addAttribute("hotelnum",hotelvo.getHotelnum());
			model.addAttribute("persons",hotelvo.getPersons());
			model.addAttribute("price",hotelvo.getPrice());
			model.addAttribute("roomsize",hotelvo.getRoomsize());
			model.addAttribute("img",hotelvo.getImg());
			return "admin/room/adminRoomInsertForm";
		} else if(result.getFieldError("persons")!=null) {
			model.addAttribute("message", result.getFieldError("persons").getDefaultMessage());
			model.addAttribute("hotelnum",hotelvo.getHotelnum());
			model.addAttribute("kind",hotelvo.getKind());
			model.addAttribute("price",hotelvo.getPrice());
			model.addAttribute("roomsize",hotelvo.getRoomsize());
			model.addAttribute("img",hotelvo.getImg());
			return "admin/room/adminRoomInsertForm";
		}else if(result.getFieldError("price")!=null) {
			model.addAttribute("message", result.getFieldError("price").getDefaultMessage());
			model.addAttribute("hotelnum",hotelvo.getHotelnum());
			model.addAttribute("kind",hotelvo.getKind());
			model.addAttribute("persons",hotelvo.getPersons());
			model.addAttribute("roomsize",hotelvo.getRoomsize());
			model.addAttribute("img",hotelvo.getImg());
			return "admin/room/adminRoomInsertForm";
		} else if(result.getFieldError("roomsize")!=null) {
			model.addAttribute("message", result.getFieldError("roomsize").getDefaultMessage());
			model.addAttribute("hotelnum",hotelvo.getHotelnum());
			model.addAttribute("kind",hotelvo.getKind());
			model.addAttribute("persons",hotelvo.getPersons());
			model.addAttribute("price",hotelvo.getPrice());
			model.addAttribute("img",hotelvo.getImg());
			return "admin/room/adminRoomInsertForm";
		}  else if(result.getFieldError("img")!=null) {
			model.addAttribute("message", result.getFieldError("img").getDefaultMessage());
			model.addAttribute("hotelnum",hotelvo.getHotelnum());
			model.addAttribute("kind",hotelvo.getKind());
			model.addAttribute("persons",hotelvo.getPersons());
			model.addAttribute("price",hotelvo.getPrice());
			model.addAttribute("roomsize",hotelvo.getRoomsize());
			return "admin/room/adminRoomInsertForm";
		}  
			ars.insertRoom(hotelvo);
		    model.addAttribute("message", "객실 추가가 완료되었습니다");
			return "redirect:/adminRoomList";
	}
}
