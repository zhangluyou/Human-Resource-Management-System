package com.jointem.hrm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);
	/**
	 * 全日期格式
	 */
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 短日期格式
	 */
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";


	/**
	 * 设置当前时间为当天的最初时间（即00时00分00秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}

	/**
	 * 把源日历的年月日设置到目标日历对象中
	 * 
	 * @param destCal
	 * @param sourceCal
	 */
	public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal) {
		destCal.set(Calendar.YEAR, sourceCal.get(Calendar.YEAR));
		destCal.set(Calendar.MONTH, sourceCal.get(Calendar.MONTH));
		destCal.set(Calendar.DAY_OF_MONTH, sourceCal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 格式化日期为
	 * 
	 * @return
	 */
	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	/**
	 * 将字符串转换为日期
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString, new String[] {
					DATE_FORMAT_FULL, DATE_FORMAT_YMD });
		} catch (Exception ex) {
			logger.error("Pase the Date(" + dateString + ") occur errors:"
					+ ex.getMessage());
		}
		return date;
	}

	/**
	 * 将日期转换为字符串
	 * 
	 * @param date
	 *            日期
	 * @param df
	 *            日期格式
	 * @return
	 */
	public static String dataToString(Date date, DateFormat df) {
		return df.format(date);
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @param df日期格式
	 * @return
	 */
	public static String getCurrentSysTime(DateFormat df) {
		TimeZone tz = TimeZone.getTimeZone("GMT+08:00");
		TimeZone.setDefault(tz);
		Date currentTime0 = new Date();// 得到当前系统时间
		return df.format(currentTime0);
	}
	
	/**
	 * 获取当前系统时间
	 * 
	 * @param 
	 * @return
	 */
	public static Date getNowDate() {
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = DateUtil.getCurrentSysTime(df2);
		return DateUtil.parseDate(dateString);
	}
	
	/**
	 * 获取当前系统时间
	 * 
	 * @param 
	 * @return
	 */
	public static String getNowDateString() {
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return DateUtil.getCurrentSysTime(df2);
	}
	
	/**
	 * 获取当前系统日期
	 * 
	 * @param 
	 * @return
	 */
	public static String getNowDateDayString() {
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		return DateUtil.getCurrentSysTime(df2);
	}

	/**
	 * 两个日期之间的天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date startDate, Date endDate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		startDate = sdf.parse(sdf.format(startDate));
		endDate = sdf.parse(sdf.format(endDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 两个日期之间的天数
	 * 
	 * @param startDate开始日期
	 * @param endDate
	 *            结束日期
	 * @param df
	 *            时间格式
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(String startDate, String endDate,
			DateFormat df) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(df.parse(startDate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(df.parse(endDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static int createRandom() {
		Random ran = new Random();
		int r = 0;
		m1: while (true) {
			int n = ran.nextInt(10000);
			r = n;
			int[] bs = new int[4];
			for (int i = 0; i < bs.length; i++) {
				bs[i] = n % 10;
				n /= 10;
			}
			Arrays.sort(bs);
			for (int i = 1; i < bs.length; i++) {
				if (bs[i - 1] == bs[i]) {
					continue m1;
				}
			}
			break;
		}
		return r;
	}
	
	/**
	  * 获取现在时间
	  * 
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	 public static Date getNowDate_() {
	  Date currentTime = new Date();
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String dateString = formatter.format(currentTime);
	  ParsePosition pos = new ParsePosition(8);
	  Date currentTime_2 = formatter.parse(dateString, pos);
	  System.out.println(currentTime_2);
	  return currentTime_2;
	 }
	 
	 /**
	  * 获取当前时间前多少小时的时间
	  * h为正数则是过去的时间，h为负数为将来的时间
	  * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	  */
	 public static String getNowBeforeHours(int h) {
		    Calendar c = Calendar.getInstance();  
	        Date date = new Date();  
	        c.setTime(date);  
	        int hour = c.get(Calendar.HOUR);  
	        c.set(Calendar.HOUR, hour - h);  
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());  
//	        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());  
//	        System.out.println(dayBefore);
//	        return DateUtil.parseDate(dayBefore);
	 }
	 /**
		 * 计算俩个时间之间相差几分钟
		 * @param startDate
		 * @param endDate
		 * @return
		 * @throws ParseException
		 */
		public static Integer hoursBetween(Date startDate, Date endDate)
				throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			startDate = sdf.parse(sdf.format(startDate));
			endDate = sdf.parse(sdf.format(endDate));
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			long time1 = cal.getTimeInMillis();
			cal.setTime(endDate);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 60);
			return Integer.valueOf(String.valueOf(between_days));
		}
}
