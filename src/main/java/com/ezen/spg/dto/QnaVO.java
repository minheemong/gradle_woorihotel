package com.ezen.spg.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
  
@Data
public class QnaVO {

	@NotEmpty
	private String title; 
	@NotEmpty
	private String content;	
	private Timestamp indate;
	private String id; 
	private String reply;
	private String rep;
	private Integer qnaseq;
}
