package com.dxsfw.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DateUtils {
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	/**
	 * yyyy
	 */
	public static final String YEAR_PATTERN = "yyyy";
	/**
	 * yyyy-MM
	 */
	public static final String YEAR_MONTH_PATTERN = "yyyy-MM";
	/**
	 * yyyy年M月d日
	 */
	public static final String CN_DATE_PATTERN = "yyyy年M月d日";
	/**
	 * yyyy年MM月dd日
	 */
	public static final String CN2_DATE_PATTERN = "yyyy年MM月dd日";
	/**
	 * 日期数据初始化
	 */
	public static final String DATE_INIT = "1970-01-01 00:00:00";
	/**
	 * 日期数据后缀起始
	 */
	public static final String DATE_SUFFIX_S = " 00:00:00";
	/**
	 * 日期数据后缀结束
	 */
	public static final String DATE_SUFFIX_E = " 23:59:59";

	public static Date createDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date);
		return calendar.getTime();
	}

	public static Date toDate(String dateStr, String pattern) throws ParseException {
		if (StringUtils.isNotBlank(dateStr)) {
			DateFormat format = new SimpleDateFormat(pattern);
			return format.parse(dateStr);
		}

		return null;
	}

	/**
	 * 获取时间格式字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date) {
		return getDateStr(date, DATETIME_PATTERN);
	}

	public static String getDateStr(Date date, String datePattern) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat(datePattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 获取年纪
	 * 
	 * @Author Leo.Zhou
	 * @Version 1.0
	 * @CreateDate 2013-7-30
	 *
	 * @param birthday 出生日期
	 * @return
	 */
	public static int getAge(Date birthday) {
		Long days = (Calendar.getInstance().getTimeInMillis() - birthday.getTime()) / (1000 * 60 * 60 * 24);
		return days.intValue() / 365;
	}

	public static Integer getAgeBy(Date birthDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		Calendar end = (Calendar) start.clone();

		start.setTime(birthDate);
		end.setTime(endDate);

		start.clear(Calendar.HOUR);
		start.clear(Calendar.HOUR_OF_DAY);
		start.clear(Calendar.MINUTE);
		start.clear(Calendar.SECOND);
		start.clear(Calendar.MILLISECOND);

		end.clear(Calendar.HOUR);
		end.clear(Calendar.HOUR_OF_DAY);
		end.clear(Calendar.MINUTE);
		end.clear(Calendar.SECOND);
		end.clear(Calendar.MILLISECOND);

		int year = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
		start.add(Calendar.YEAR, year);

		year = end.compareTo(start) < 0 ? year - 1 : year;

		return new Integer(year);
	}

	/**
	 * 获取日期中的年
	 * 
	 * @param date
	 *            日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取日期中的月
	 * 
	 * @param date
	 *            日期
	 * @return 月份 (1~12)
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取日期(月)
	 * 
	 * @param date
	 *            日期
	 * @return 天
	 */
	public static int getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 获取星期天 (1(星期日)~7(星期六)
	 * 
	 * @param date
	 *            日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	public static Date clearTime(Date date) {
		Date d = DateUtils.createDate(DateUtils.getYear(date), DateUtils.getMonth(date), DateUtils.getDate(date));
		return d;
	}

	public static void clearTime(Calendar calendar) {
		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.HOUR_OF_DAY);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
	}

	/**
	 * 获取指定日期的起始时间
	 */
	public static Calendar getStartOf(final Calendar calendar) {
		return new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 获取指定日期的截止时间
	 */
	public static Calendar getEndOf(final Calendar calendar) {
		return new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
	}

	// 1.计算某一月份的最大天数
	public static int ActualMaximum(Date date) {
		Calendar time = Calendar.getInstance();
		time.clear();
		time.set(Calendar.YEAR, getYear(date));
		time.set(Calendar.MONTH, getMonth(date) - 1);//注意,Calendar对象默认一月为0
		int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数 
		return day;
	}

	/**
	 * 计算两个时间之间相隔天数
	 * 
	 * @param startday
	 *            开始时间
	 * @param endday
	 *            结束时间
	 * @return
	 */
	public int getIntervalDays(Calendar startday, Calendar endday) {
		//确保startday在endday之前
		if (startday.after(endday)) {
			Calendar cal = startday;
			startday = endday;
			endday = cal;
		}
		//分别得到两个时间的毫秒数
		long sl = startday.getTimeInMillis();
		long el = endday.getTimeInMillis();

		long ei = el - sl;
		//根据毫秒数计算间隔天数
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	//(2)传进Date对象
	/**
	 * 计算两个时间之间相隔天数
	 * 
	 * @param startday
	 *            开始时间
	 * @param endday
	 *            结束时间
	 * @return
	 */
	public int getIntervalDays(Date startday, Date endday) {
		//确保startday在endday之前
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		//分别得到两个时间的毫秒数
		long sl = startday.getTime();
		long el = endday.getTime();

		long ei = el - sl;
		//根据毫秒数计算间隔天数
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 同理，可以用相同的方法计算出任意两个时间相隔的小时数，分钟数，秒钟数等 注：以上方法是完全按时间计算，有时并不能令人满意，如： startday="2006-10-11 20:00:00" endday="2006-10-12 8:00:00"
	 * 计算结果为0，但是我们也许相让计算结果变为1，此时可以用如下方法实现： 在传参之前，先设定endday的时间，如： endday.set(Calendar.HOUR_OF_DAY, 23); endday.set(Calendar.MINUTE, 59);
	 * endday.set(Calendar.SECOND, 59); endday.set(Calendar.MILLISECOND, 59); 这样再传进去startday,endday，则结果就如我们所愿了。不过，如果嫌以上方法麻烦，可以参考以下方法：
	 */
	//(3)改进精确计算相隔天数的方法
	public int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
			// swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static int getDaysBetween(Date startday, Date endday) {
		Calendar d1 = Calendar.getInstance();
		d1.setTime(startday);

		Calendar d2 = Calendar.getInstance();
		d2.setTime(endday);

		if (d1.after(d2)) {
			// swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);//得到当年的实际天数
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	//获取系统当前时间：
	public static String getSystemTime() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static String getSystemTime(String format) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	//获取系统当前时间： 
	public static String getSortSystemTime() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	public static Date getSortSystemDate() throws ParseException {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		String s = df.format(date);

		Date d2 = df.parse(s);

		return d2;
	}

	public static String getDateTime(Long time) {
		if (time == null || time == 0l)
			return "";
		else {
			Date utilDate = new java.util.Date(time);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(utilDate);
		}
	}

	//
	public static String getSortDateTime(long time) {
		Date utilDate = new java.util.Date(time);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(utilDate);
	}

	public static Date getSortDate(long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate = new java.util.Date(time);

		String s = df.format(utilDate);
		Date d2 = null;
		try {
			d2 = df.parse(s);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return d2;
	}

	//Calendar转化为Date
	public static Date getDateByCalendar() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		//SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		//java.util.Date date= myFormatter.parse("2003-05-1");
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return date;
	}

	//Calendar转化为Date
	public static Calendar getCalendarByDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
	 * 
	 * @param dateString
	 *            需要转换为timestamp的字符串
	 * @return dataTime timestamp
	 */
	public final static java.sql.Timestamp string2Time(String dateString) throws java.text.ParseException {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//设定格式
		//dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);//util类型
		//Timestamp类型,timeDate.getTime()返回一个long型
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}

	/**
	 * method 将字符串类型的日期转换为一个Date（java.sql.Date）
	 * 
	 * @param dateString
	 *            需要转换为Date的字符串
	 * @return dataTime Date
	 */
	public final static java.sql.Date string2Date(String dateString) {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		java.util.Date timeDate = null;
		try {
			timeDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//util类型
		java.sql.Date dateTime = null;
		if (timeDate != null) {
			dateTime = new java.sql.Date(timeDate.getTime());//sql类型
		}
		return dateTime;
	}

	/**
	 * method 将字符串类型的日期转换为一个Date（java.sql.Date）
	 * 
	 * @param dateString
	 *            需要转换为Date的字符串
	 * @return dataTime Date
	 */
	public final static java.sql.Date sortString2Date(String dateString) {
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		java.util.Date timeDate = null;
		java.sql.Date dateTime = null;
		try {
			timeDate = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}//util类型
		if (timeDate != null) {
			dateTime = new java.sql.Date(timeDate.getTime());//sql类型
		}
		return dateTime;
	}

	public Date parseDate(String s) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date3 = bartDateFormat.parse(s);
			date3 = bartDateFormat.parse(s);
			return date3;
		} catch (Exception ex) {
			return null;
		}
	}

}
