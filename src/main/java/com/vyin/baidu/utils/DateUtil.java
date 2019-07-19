
package com.vyin.baidu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class DateUtil {

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/** 年月日时分秒(无下划线) yyyyMMddHHmmssSSS */
	public static final String dtLonger = "yyyyMMddHHmmssSSS";

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmssSSS为格式的当前系统时间
	 */
	public static String getOrderNumSSS() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLonger);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	/**
	 * 产生随机的六位数
	 * 
	 * @return
	 */
	public static String getSix() {
		Random rad = new Random();
		Double radd = rad.nextDouble();
		return radd.toString().substring(2, 8);
	}

	/**
	 * 根据日期获取时间字符串(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter(Date date) {
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 根据日期获取时间字符串(精确到毫秒)，格式：p
	 * 
	 * @return
	 */
	public static String getDateFormatter(Date date, String p) {
		DateFormat df = new SimpleDateFormat(p);
		return df.format(date);
	}

	/**
	 * 根据时间字符串获取date，格式：p
	 * 
	 * @param dateStr
	 * @param p
	 * @return
	 */
	public static Date getDateFromStr(String dateStr, String p) {
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * date 移动n小时
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getDatePlusHOUR(Date date, int n) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.HOUR_OF_DAY, n);// 整数往后推,负数往前移动
		return calendar.getTime();
	}

	/**
	 * 比较两个时间的差值(以秒为单位)
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return long
	 */
	public static long dateDiff(java.util.Date date1, java.util.Date date2) {
		// return date1.getTime() / (24*60*60*1000) - date2.getTime() /
		// (24*60*60*1000);
		return date2.getTime() / 1000 - date1.getTime() / 1000; // 用立即数，减少乘法计算的开销
	}

	// 获取当月第一天
	public static String getCurrentMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(calendar.getTime());
		return firstDay;
	}

	// 获取一个月以前的时间
	public static String getAMonthAgo() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 0); // 得到当前一天
		calendar.add(Calendar.MONTH, -1);// 得到前一个月
		calendar.add(Calendar.YEAR, 0);// 得到当前年
		String aMonthAgoDay = format.format(calendar.getTime());
		return aMonthAgoDay;
	}

	/**
	 * 指定格式获取当前格式时间数据
	 * @param p
	 * @return
	 */
	public static String getCurrentDate(String p) {
		SimpleDateFormat format = new SimpleDateFormat(p);
		String currentDate = format.format(new Date());
		return currentDate;
	}
	
	public static void main(String[] args) {
		String time = "2018-06-20 12:25:36";
//		String time = "2018-06-20 23:25:36";
		Date dete = getDateFromStr(time, "yyyy-MM-dd HH:mm:ss");
		long l = dete.getTime();
		System.out.println(l);
	}
	
	
}
