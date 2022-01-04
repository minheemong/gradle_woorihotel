package com.ezen.spg.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QnaVO {

	
	private String title; 
	private String content;	
	private Timestamp indate;
	private String id; 
	private String reply;
	private String rep;
	private Integer qnaseq;
}
