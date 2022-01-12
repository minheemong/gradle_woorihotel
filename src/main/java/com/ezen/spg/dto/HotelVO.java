package com.ezen.spg.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class HotelVO {
	@NotNull(message="객실번호를 입력하세요") 
	@NotEmpty(message="객실번호를 입력하세요")
	private String hotelnum;
	@NotNull(message="인원을 입력하세요") 
	@NotEmpty(message="인원을 입력하세요")
	private String persons;
	@NotNull(message="가격을 입력하세요") 
	@NotEmpty(message="가격을 입력하세요")
	private String price;
	@NotNull(message="이미지를 입력하세요") 
	@NotEmpty(message="이미지를 입력하세요")
	private String img;
	@NotNull(message="방크기를 입력하세요") 
	@NotEmpty(message="방크기를 입력하세요")
	private String roomsize;
	@NotNull(message="방종류를 입력하세요") 
	@NotEmpty(message="방종류를 입력하세요")
	private String kind;
}
