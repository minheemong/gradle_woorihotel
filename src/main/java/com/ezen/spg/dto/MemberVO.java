package com.ezen.spg.dto;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
    private String pwd;     
    private String name;
    private String email;
    private String phone;
    private String zip_num;
    private String address;
}
