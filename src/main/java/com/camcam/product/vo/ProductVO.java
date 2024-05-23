package com.camcam.product.vo;

import lombok.Data;

@Data
public class ProductVO {
	private int productNo;
	private String categoryNo;
	private String productName;
	private int price;
	private int offPrice;
	private String explain;
	private String productImg;
	private int productCnt;
	private double rating;
	private String inputDate;
	
}
