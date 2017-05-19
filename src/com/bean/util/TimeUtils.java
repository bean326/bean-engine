package com.bean.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author: bean
 * Description:
 */
public class TimeUtils {

	public static final String YYYY = "yyyy";
	public static final String MM = "dd";
	public static final String DD = "dd";
	public static final String HH = "HH";
	public static final String mm = "mm";
	public static final String ss = "ss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYY_MM_DD  = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    

    /**
     * 获取星期几
     * @return
     */
    public static int getWeekDay()
    {
    	Calendar now = Calendar.getInstance();
    	//一周第一天是否为星期天
    	boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY);
    	//获取周几
    	int weekDay = now.get(Calendar.DAY_OF_WEEK);
    	//若一周第一天为星期天，则-1
    	if(isFirstSunday){
    		weekDay = weekDay - 1;
    		if(weekDay == 0){
    			weekDay = 7;
    		}
    	}
    	return weekDay;
    }
   
    
    public static Date getCurrentDate()
    {
    	return new Date();
    }
    
    public static String getFormatTime(long time, Object... format)
    {
    	if(format == null)
    		return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(time);
    	
		return new SimpleDateFormat(format[0].toString()).format(time);
	}
	
	public static String getCurrentTime(Object... format)
	{
		if(format == null)
			return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(new Date());
		
		return new SimpleDateFormat(format[0].toString()).format(new Date());
	}
	
	
	/**
	 * 根据指定时间 获取时间的毫秒数
	 * @param indexDay 0表示为当天
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static long getTime(int indexDay, int hour, int minute, int second){
		Calendar calendar = Calendar.getInstance();

		int day = calendar.get(Calendar.DAY_OF_YEAR) + indexDay;
		
		calendar.set(Calendar.DAY_OF_YEAR, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTimeInMillis();
	}
	public static Long getMaxDatetime(String maxDate){
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		Long maxTime = null;
		if(!Utils.isNull(maxDate)){
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(maxDate));
				c.set(Calendar.HOUR_OF_DAY, 23);
				c.set(Calendar.MINUTE, 59);
				c.set(Calendar.SECOND, 59);
				maxTime = c.getTimeInMillis() / 1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return maxTime;
	}
	public static Long getMinDatetime(String minDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		Long minTime = null;
		try {
			if(!Utils.isNull(minDate)){
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(minDate));
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				return c.getTimeInMillis() / 1000;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return minTime;
	}
	
	public static Long getTime(String ymdhms){
		SimpleDateFormat f = new SimpleDateFormat(YYYY_MM_DD);
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(f.parse(ymdhms));
			return c.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /**
	 * 根据字符串获取时间戳
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static long getDateByString(String dateString) throws ParseException{
		SimpleDateFormat f = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return f.parse(dateString).getTime();
	}

    /**
     * @Title: getDayOfMonth
     * @Description: 当前日期加一天减一天
     * @param @param day
     * @param @return
     * @return String
     * @throws
     */
    public static String getDayOfMonth(int day){
        SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD); //制定日期格式
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH,day); //将当前日期加一天
        return df.format(c.getTime());
    }

 

    /**
     * @Title: getYear
     * @Description: 获取当前年份
     * @param @return
     * @return int
     * @throws
     */
    public final static int getYear(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * @Title: getYear
     * @Description: 当前年份加减
     * @param @param year
     * @param @return
     * @return String
     * @throws
     */
    public static int getYear(String time){
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        Calendar cal;
        try {
            Date date = sdf.parse(time);
            cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * @Title: getYear
     * @Description: 当前年份加减
     * @param @param year
     * @param @return
     * @return String
     * @throws
     */
    public static Date getBrithday(String time ,int year){
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        Calendar cal;
        try {
            Date date = sdf.parse(time);
            cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR,-year); //将当前年份加减
            return cal.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Title: islegitimate
     * @Description: 验证是否大于18岁
     * @param @param year
     * @param @return
     * @return boolean
     * @throws
     */
    public static int islegitimate(String brithday){

        int currentYear = getYear();
        int britjdayYear = getYear(brithday);
        int rsYear = currentYear - britjdayYear;
        if(rsYear > 18){
            return 1;
        }else{
            return rsYear - 18;
        }
    }



    /**
     * @Title: isBetWeen
     * @Description: 根据当前日期和时间范围计算，当前日期是否在时间范围之内
     * @param @param year
     * @param @return
     * @return boolean
     * @throws
     */
    public static boolean isBetWeen(String currentTime ,String beginDateStr, String endDateStr)  {

        try {
            boolean bl = false;
            SimpleDateFormat format = new SimpleDateFormat(
                    YYYY_MM_DD_HH_MM_SS);
            Date beginDate;
            Date endDate;
            Date currentDate;

            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            currentDate = format.parse(currentTime);
            if(currentDate.getTime() >= beginDate.getTime() && currentDate.getTime() <=  endDate.getTime()){
                bl = true;
            }
            return bl;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
