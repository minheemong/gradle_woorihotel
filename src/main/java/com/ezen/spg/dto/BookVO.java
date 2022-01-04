package com.ezen.spg.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BookVO {

	
	private String id;
	private int booknum;
	private String kind;
	private int usernum;
	private Timestamp checkin;
	private Timestamp checkout;
	private String result;
	private int price;

	private String name;
	private int bdseq;
	private int hotelnum;
}
