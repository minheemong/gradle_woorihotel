package com.ezen.spg.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	
	
	
	
	
	
	public int days() {
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println(sdf.format(checkin));
		System.out.println(sdf.format(checkout));
		String indayS = sdf.format(checkin);
		String outdayS = sdf.format(checkout);
		int inday = Integer.parseInt(indayS);
		int outday = Integer.parseInt(outdayS);
		String inyearS = indayS.substring(0, 4); int inyear = Integer.parseInt(inyearS);
		String outyearS = outdayS.substring(0, 4); int outyear = Integer.parseInt(outyearS);
		String inmonthS = indayS.substring(4, 6); int inmonth = Integer.parseInt(inmonthS);
		String outmonthS = outdayS.substring(4, 6); int outmonth = Integer.parseInt(outmonthS);
		String indateS = indayS.substring(6, 8); int indate = Integer.parseInt(indateS);
		String outdateS = outdayS.substring(6, 8); int outdate = Integer.parseInt(outdateS);
		System.out.println("년 : "+inyear+", 월 : "+inmonth+"일 : "+indate);
		int days = 0;
		if(inyear==outyear && inmonth==outmonth) {
			days=outday-inday;
		} else if(inyear==outyear && inmonth!=outmonth) {
			if(inmonth==1 || inmonth==3 || inmonth==5 || inmonth==7 || inmonth==8 || inmonth==10) {
				days=outday-inday-69;
			} else if(inmonth==2) {
				if(inyear%4==0 && inyear%100 !=0 || inyear%400==0) {
					days=outday-inday-72;
				} else {
					days=outday-inday-71;
				}
			} else days=outday-inday-70;
		} else if(inyear!=outyear) days=outday-inday-69-8800;
		
		int price = 0;
		price = this.price*days;
		
		
		
		System.out.println("하루 가격 : "+this.price+", 일수 : "+days+", 총 가격 : "+price);
		this.price=price;
		
		return price;
	}
}
