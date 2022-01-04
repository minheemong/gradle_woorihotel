package com.ezen.spg.cotroller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg.dto.BookVO;

@Controller
public class BookController {
	
	@RequestMapping(value="bookForm", method=RequestMethod.POST)
	public ModelAndView bookForm(@ModelAttribute("dto") @Valid BookVO bookvo,
			BindingResult result, @RequestParam(value="roomnum",required=false) String roomnum,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		
		
		return mav;
	}
}
