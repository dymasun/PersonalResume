package xyz.dymasun.ws.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 将时间转化为mills
	 * @param Date
	 * @return
	 * @throws ParseException
	 */
	public static long getMills(String Date) throws ParseException{
		return formatToDate(Date).getTime();
	}
	public static String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}
	public static long disparity_s(Date date1,Date date2){
		long between=(date2.getTime()-date1.getTime())/1000;//除以1000是为了转换成秒
		return between;
	}
	public static long disparity_min(Date date1,Date date2){
		return DateUtils.disparity_s(date1,date2)/60;
	}
	public static long disparity_h(Date date1,Date date2){
		return DateUtils.disparity_min(date1,date2)/60;
	}
	public static long disparity_d(Date date1,Date date2){
		return DateUtils.disparity_h(date1,date2)/24;
	}
	/**
	 * 将日期字符串转化为时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date formatToDate(String date) throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date);
	}

	public static byte AbeforeB(Date A,Date B){
		if(A.before(B)){
			return 1;
		}else if(A.after(B)){
			return -1;
		}
		return 0;
	}

	public static String secondToHHMMSS(long second){
		String h = String.valueOf(second/3600);
		if(1==h.length()){
			h = "0" + h;
		}
		String min = String.valueOf((second/60)%60);
		if(1==min.length()){
			min = "0" + min;
		}
		String s = String.valueOf(second%60);
		if(1==s.length()){
			s = "0" + s;
		}
		return String.format("%s:%s:%s",h,min,s);
	}
}
