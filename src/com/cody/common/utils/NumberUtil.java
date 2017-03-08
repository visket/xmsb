package com.cody.common.utils;

import java.text.DecimalFormat;



/**
 * 处理数字类型和转换的问题
 * 1、转化字节问题，将B字节转化为MB
 * 2、根据指定格式化要求，格式化出需要的数值转字符串
 * @author 周围
 * @date 2016-7-15
 */
public class NumberUtil {

	
	/**
	 * 根据指定格式化要求，格式化出需要的数值转字符串
	 * @param pattern	格式
	 * @param count	数值
	 * @return String
	 */
	public static String formatToString(String pattern, int count) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(count);
	}
	
	
	public static String getFileSizeToMB(long size) {
		DecimalFormat df = new DecimalFormat("#.000");
		
		double kb = size / 1024;
		double mb = kb / 1024;
		
		String temp = df.format(mb);
		
		if(temp.indexOf("0.000") != -1) temp = "0.001";
		
		return temp;
	}
	
	
	
	
	public static Float formatFileSize(long size) {
		DecimalFormat df = new DecimalFormat("#0.000");
		
		double kb = size / 1024;
		double mb = kb / 1024;
		
		String temp = df.format(mb);
		
		if(temp.indexOf("0.000") != -1) temp = "0.001";
		
		return Float.valueOf(temp);
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(NumberUtil.getFileSizeToMB(2546545123L));
		System.out.println(formatToString("0000", 15));
	}
	
	
}
