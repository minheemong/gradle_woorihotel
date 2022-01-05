package com.ezen.spg.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spg.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;
	
	@RequestMapping("/a")
	public String adminmain() {
		return "admin/adminmain";
	}

}
