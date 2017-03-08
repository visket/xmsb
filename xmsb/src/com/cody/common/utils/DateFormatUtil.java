package com.cody.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Date日期格式化类，提供方法
 * 1、获取月头和月末之间
 * @author 周围
 * @date 2016-5-29
 */
public class DateFormatUtil {

	
	/**
	 * 获取月头与月末之间的日期  例如  2000-02: between 2000.02.01-2000.02.29/28
	 * @param yearMonth
	 * @return
	 */
	public static Date[] getMonthMinToMaxDay(String yearMonth) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date[] ds = new Date[2];
		String[] temp = yearMonth.split("-");
		
		Calendar c = Calendar.getInstance();
		//设置当前月初日期
		c.set(Integer.valueOf(temp[0]), Integer.valueOf(temp[1])-1, 1, 0, 0, 0);
		ds[0] = c.getTime();
		
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, 2);
		
		
		ds[1] = c.getTime();
		
		return ds;
	}
	
	/**
	 * 格式化字符串 --> Date  yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		if(str == null || "".equals(str)) return null;
		String[] temp = str.split("-");
		Calendar c = Calendar.getInstance();
		c.set(Integer.valueOf(temp[0]), Integer.valueOf(temp[1])-1, Integer.valueOf(temp[2]));
		return c.getTime();
	}
	
	public static Date stringToDate(String str, String regex) {
		if(str == null || "".equals(str)) return null;
		String[] temp = str.split(regex);

		Calendar c = Calendar.getInstance();
		c.set(Integer.valueOf(temp[0]), Integer.valueOf(temp[1])-1, Integer.valueOf(temp[2]),
				Integer.valueOf(temp[3]),Integer.valueOf(temp[4]), (int)(Float.valueOf(temp[5])/1));
		
		return c.getTime();
	}
	
	
	public static String dateToString(Date date, String format) {
		if(format == null) format = "yyyy-MM-dd";
		if(date == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	
	public static Date toBeginDate(String str) {
		if(str == null || "".equals(str)) return null;
		String[] temp = str.split("-");
		Calendar c = Calendar.getInstance();
		c.set(Integer.valueOf(temp[0]), Integer.valueOf(temp[1])-1, Integer.valueOf(temp[2]),0,0,0);
		return c.getTime();
	}
	
	public static Date toEndDate(String str) {
		if(str == null || "".equals(str)) return null;
		String[] temp = str.split("-");
		Calendar c = Calendar.getInstance();
		c.set(Integer.valueOf(temp[0]), Integer.valueOf(temp[1])-1, Integer.valueOf(temp[2]),23,59,59);
		return c.getTime();
	}
	
	
	/**
	 * 字符串 --> Date，    用于条件查询，begin--end
	 * @param str
	 * @param date  传入开始日期时请-1，结束日期+1
	 * @return
	 */
	public static Date stringToDate(String str, int date) {
		if(str == null || "".equals(str)) return null;
		String[] temp = str.split("-");
		Calendar c = Calendar.getInstance();
		c.set(Integer.valueOf(temp[0]), Integer.valueOf(temp[1])-1, Integer.valueOf(temp[2]));
		c.add(Calendar.DATE, date);
		return c.getTime();
	}
	
	
	/**
	 * 获取当前年月字符串
	 * @return
	 */
	public static String getYearMonth() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(d);
	}
	
	
	public static void main(String[] args) {
		getMonthMinToMaxDay("2001-02");
	}
	
	public static Date getDate(String strDate){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=null;
		try {
			dt = sim.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return dt;
	}
	
}
