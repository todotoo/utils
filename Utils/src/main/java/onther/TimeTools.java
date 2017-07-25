/**
 * Created by eclipse3.2.
 * function:
 * User: FMD(冯敏栋)
 * Date: 2008-12-9
 * Time: 上午10:48:47
 * Email:fmdsaco99@163.com
 * To change this template use File | Settings | File Templates.
 */
package onther;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimeTools {

	protected final static Log log = LogFactory.getLog(TimeTools.class);
	public static final Long MILI_SECOND = 1000L;  
    public static final Long MILI_MINUTE = MILI_SECOND * 60L;  
    public static final Long MILI_HOUR = MILI_MINUTE * 60L;  
    public static final Long MILI_DATE = MILI_HOUR * 24L;  
    public static final Long MILI_MONTH = MILI_DATE * 30L;  
    public static final Long MILI_YEAR = MILI_MONTH * 365L;  
    
	public static void main(String[] arg) {
		String beginDateTime = "20081210235959";

//		System.out.println(TimeTools.dateToStr(new Date(), "yyyyMMddHH"));
		// System.out.println(getStrDate("2009010101","yyyyMMddHH"));
		Date b = strToDate("2012-12-30 ", "yyyy-MM-dd");
		
		Date eb = strToDate("2012-1-01 ", "yyyy-MM-dd");
		  String inDate= TimeTools.firstDayOfMonth(b, "yyyy-MM-dd"); //开始时间
		  String outDate= TimeTools.firstDayOfMonth(eb, "yyyy-MM-dd"); //开始时间
		  Date inD=TimeTools.strToDate(inDate,"yyyy-MM-dd");
		  long outD=TimeTools.getdate(eb,b);
		// System.out.println(getHours(new Date(),-24,"yyyy-MM-dd HH:00:00"));
		// Date dates, int hours, String sFormat
		  
		   
		System.out.println(TimeTools.compareDate("2012-07-20","2013-06-30",1));
	}

	/**
	 * 比较两个时间差
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return 返回相差的秒数
	 */
	public static long getdate(Date beginDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		Calendar cal1 = Calendar.getInstance();
		cal.setTime(beginDate);
		cal1.setTime(endDate);

		return ((long) cal1.getTime().getTime() - (long) cal.getTime()
				.getTime()) / (60*60*24*30);
	}

	/**
	 * 计算两个时间的相差月数
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
    public static long subTimeMonth(Date beginTime, Date endTime) {  
//        isDateCheck(basetime, targettime);  
        return subTimeMiliSecond(beginTime, endTime)/MILI_MONTH;  
    }  
    public static  long subTimeMiliSecond(Date beginTime, Date endTime) {  
 
        return endTime.getTime() - beginTime.getTime();  
    }
    
    public static int compareDate(String begin_time,String end_time,int stype){  
        int n = 0;  
          
        String[] u = {"天","月","年"};  
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";  
          
//        date2 = date2==null?DateTest.getCurrentDate():date2;  
          
        DateFormat df = new SimpleDateFormat(formatStyle);  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        try {  
            c1.setTime(df.parse(begin_time));  
            c2.setTime(df.parse(end_time));  
        } catch (Exception e3) {  
            System.out.println("wrong occured");  
        }  
        //List list = new ArrayList();  
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果  
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来  
            n++;  
            if(stype==1){  
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1  
            }  
            else{  
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1  
            }  
        }  
          
        n = n-1;  
          
        if(stype==2){  
            n = (int)n/365;  
        }     
          
//        System.out.println(begin_time+" -- "+end_time+" 相差多少"+u[stype]+":"+n);        
        return n;  
    }   
    
	/**
	 * @todo 计算两个时间段之间相隔的天数
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int dateToDate(Date begin, Date end) {
		return (int) ((end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24*30) + 0.5);
	}
	
 
	

	/**
	 * @todo 得到当前日期的第几天的日期
	 * @param days
	 *            天数，表示要获得的是当前日期前或后那天的日期,-1表示获得当前日期前一天的日期
	 * @return 返回一个时间格式的字符串
	 */
	public static String getTimes(Date dates, int days, String sFormat) {
		Calendar todayCalendar = Calendar.getInstance();
		if (null != dates) {

			todayCalendar.setTime(dates);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
		todayCalendar.add(Calendar.DAY_OF_MONTH, days);

		return dateFormat.format(todayCalendar.getTime());
	}

	/**
	 * @todo 得到当前小时的前或后的小时时间数
	 * @param days
	 *            天数，表示要获得的是当前日期前或后那天的日期,-1表示获得当前小时前一小时的日期
	 * @return 返回一个时间格式的字符串
	 */
	public static String getHours(Date dates, int hours, String sFormat) {
		Calendar todayCalendar = Calendar.getInstance();
		if (null != dates) {
			todayCalendar.setTime(dates);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
		todayCalendar.add(Calendar.HOUR_OF_DAY, hours);

		return dateFormat.format(todayCalendar.getTime());
	}

	/**
	 * @todo 将字符串格式化成日期
	 * @param time3
	 * @param sFormat格式如
	 *            ：yyyy-MM-dd
	 * @return
	 */
	public static Date strToDate(String time3, String sFormat) {
		// 将一个String 的转换成Date型的
		DateFormat input = new SimpleDateFormat(sFormat);
		Date date = null;
		try {
			date = input.parse(time3);
		} catch (ParseException ex) {
			log.error("dataread：date format error!", ex);
		}
		return date;
	}

	/**
	 * @todo 将一个日期转换成指定格式的字符串
	 * @param
	 * @return 返回一个时间格式的字符串
	 */
	public static String dateToStr(Date time, String sFormat) {
		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.setTime(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);

		return dateFormat.format(todayCalendar.getTime());
	}

	
	/**
	 * 取当月最前一天
	 * @param date
	 * @return
	 */
	public static String beforeMonth(Date date,String sFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		 
//		cal.roll(Calendar.MONTH, -1);
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
//	   Calendar   calendar=Calendar.getInstance();
//	    cal.setTime(date);
//	   
//	        //取得上一个月时间
//	      // calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
//	    String lastYear= sdf.format(calendar.getTime()).substring(0, 4);
//	     String lastMonth= sdf.format(calendar.getTime()).substring(4, 6);
//	         
//	     System.out.println(lastYear+" "+lastMonth);
		return dateFormat.format(cal.getTime());
	}
	
	/**
	 * 取当月最后一天
	 * @param date
	 * @return
	 */
	public static String lastDayOfMonth(Date date,String sFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		 
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
		return dateFormat.format(cal.getTime());
	}
	
	/**
	 * 取当月第一天
	 * @param date
	 * @return
	 */
	public static String firstDayOfMonth(Date date,String sFormat) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, 0);
		 
		SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
		return dateFormat.format(cal.getTime());
	}
	/**
	 * 将一个日期转换成自定义的字符串格式
	 * 
	 * @param time3
	 * @param sFormat
	 * @return
	 */
	public static String getStrDate(String time3, String sFormat) {
		Date da = strToDate(time3, sFormat);
		// System.out.println("" + da);
		return dateToStr(da, sFormat);
	}

}
