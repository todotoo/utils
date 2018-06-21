package org.wg.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类 <br />
 * 常用指数：★★★★★
 *
 * @author wg
 * @date 2015-08-08
 */
public class DateUtils {

    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_NO_SEPARATOR_TIME = "yyyyMMddHHmmss";
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIME = "HH:mm:ss";

    /**
     * 时间格式化方法：<br>
     * 提供将时间转换成指定格式的字符串
     * 常用指数：★★★★★
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        String result = "";
        if (null == date) {
            return result;
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 将时间格式化成: "yyyy-MM-dd"
     *
     * @param date
     * @return "2014-06-27"
     */
    public static String formatDate(Date date) {
        return format(date, FORMAT_DATE);
    }


    /**
     * 将时间格式化成："yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return "2014-06-27 16:46:32"
     */
    public static String formatDateTime(Date date) {
        return format(date, FORMAT_DATETIME);
    }


    /**
     * 将时间格式化成："HH:mm:ss"
     *
     * @param date
     * @return "16:46:32"
     */
    public static String formatTime(Date date) {
        return format(date, FORMAT_TIME);
    }


    /**
     * 时间字符串解析方法：<br>
     * 提供将指定格式的时间字符串解析成时间对象
     * 常用指数：★★★★★
     *
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static Date parse(String date, String format) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }


    /**
     * 将<code>format["yyyy-MM-dd"]</code>字符串解析成对象
     *
     * @param date "2014-06-27"
     * @return
     * @throws Exception
     */
    public static Date parseDate(String date) throws Exception {
        return parse(date, FORMAT_DATE);
    }

    public static java.sql.Date parseSqlDate(Date date) {
        if (null == date) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * 将<code>format["yyyy-MM-dd HH:mm:ss"]</code>字符串解析成对象
     *
     * @param date "2014-06-27 16:46:32"
     * @return
     * @throws Exception
     */
    public static Date parseDateTime(String date) throws Exception {
        return parse(date, FORMAT_DATETIME);
    }

    // -------------------------------------分隔线-------------------------------------

    /**
     * 取日期的后N天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getNextDay(Date d, int day) {
        if (d == null) {
            d = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * @param d
     * @return
     * @Description 取日期的前N天
     */
    public static Date getPrevDay(Date d, int day) {
        if (d == null) {
            d = new Date();//参数日期为空 默认当前日期
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, -day);//上一天
        return cal.getTime();
    }


    /**
     * 获取当前时间, 并格式化成："yyyy-MM-dd HH:mm:ss" 输出
     *
     * @return "2014-06-27 16:46:32"
     */
    public static String getCurrentDateTime2Str() {
        return format(new Date(), FORMAT_DATETIME);
    }

    /**
     * 获取当前时间, 并格式成："yyyyMMddHHmmss" 输出
     *
     * @return "20140627164632"
     */
    public static String getCurrentDateTime2Str2() {
        return format(new Date(), "yyyyMMddHHmmss");
    }

    /**
     * 将<code>format["yyyy-MM-dd"]</code>字符串 + " 23:59:59" <br>
     * 然后再解析成对象
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getMaxTimeByStringDate(String date) throws Exception {
        String maxTime = date + " 23:59:59";
        return parse(maxTime, FORMAT_DATETIME);
    }

    /**
     * 将<code>format["yyyy-MM-dd"]</code>字符串 + " 00:00:00" <br>
     * 然后再解析成对象
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getMinTimeByStringDate(String date) throws Exception {
        String minTime = date + " 00:00:00";
        return parse(minTime, FORMAT_DATETIME);
    }

    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * 计算两个日期之间相差的小时数(去除天数)
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int hoursBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_hours = (time2 - time1) % (1000 * 3600 * 24) / (1000 * 3600);
        return Integer.parseInt(String.valueOf(between_hours));
    }

    /**
     * 日期相加
     *
     * @param date Date
     * @param day  int
     * @return Date
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期相减
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 日期相减(返回秒值)
     *
     * @param date  Date
     * @param date1 Date
     * @return int
     * @author
     */
    public static Long diffDateTime(Date date, Date date1) {
        return (Long) ((getMillis(date) - getMillis(date1)) / 1000);
    }


    public static Integer getLeftSeconds(String date) throws Exception {
        Date condition = parse(date, FORMAT_DATETIME);
        long n = condition.getTime();
        long s = System.currentTimeMillis();
        return (int) ((s - n) / 1000);
    }

    /**
     * 判断日期格式是否正确
     */
    public static boolean validateDate(String dateString) {
        //使用正则表达式 测试 字符 符合 dddd-dd-dd 的格式(d表示数字)
        Pattern p = Pattern.compile("\\d{4}+[-]\\d{2}+[-]\\d{2}+");
        Matcher m = p.matcher(dateString);
        if (!m.matches()) {
            return false;
        }
        //得到年月日
        String[] array = dateString.split("-");
        int year = Integer.valueOf(array[0]);
        int month = Integer.valueOf(array[1]);
        int day = Integer.valueOf(array[2]);

        if (month < 1 || month > 12) {
            return false;
        }
        int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year)) {
            monthLengths[2] = 29;
        } else {
            monthLengths[2] = 28;
        }
        int monthLength = monthLengths[month];
        if (day < 1 || day > monthLength) {
            return false;
        }
        return true;
    }

    /**
     * 是否是闰年
     */
    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }


    /**
     * 砍掉 <b>时分秒</b>  得到 ： yyyy-MM-dd
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getDate(Date date) throws Exception {
        return parseDate(formatDate(date));
    }

    /**
     * 判断是否为当前日期
     *
     * @param date
     * @return
     */
    public static boolean isCurrentDate(Date date) {
        return formatDate(date).equals(formatDate(new Date()));
    }

    /**
     * 获取上一个月 yyyy-MM
     */
    public static String getLastMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        return format(c.getTime(), "yyyy-MM");
    }

    /**
     * @param d     时间
     * @param index 跳动几个月
     * @return
     * @Description 得到时间特定的前后几个月
     */
    public static Date getLastMonth(Date d, int index) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, index);
        return cal.getTime();
    }

    /**
     * Unix时间戳转日期对象
     */
    public static Date unixTimeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        return new java.util.Date(timestamp);
    }

    /**
     * 日期转Unix时间戳
     */
    public static String date2UnixTimeStamp(Date date) {
        long unixTimestamp = date.getTime() / 1000;
        return Long.toString(unixTimestamp);
    }

    /**
     * 获取 指定日期  后 指定分钟的 Date
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getDateAddMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        if (null != date) {//没有 就取当前时间
            cal.setTime(date);
        }
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }


    /**
     * 获取指定日期后指定毫秒后的Date
     *
     * @param date
     * @param millSecond
     * @return
     */
    public static Date getDateAddMillSecond(Date date, int millSecond) {
        Calendar cal = Calendar.getInstance();
        // 没有 就取当前时间
        if (null != date) {
            cal.setTime(date);
        }
        cal.add(Calendar.MILLISECOND, millSecond);
        return cal.getTime();
    }

    public static void main(String[] args) {
        String dateStr = DateUtils.format(new Date(), DateUtils.FORMAT_NO_SEPARATOR_TIME);
        System.out.println(dateStr);
    }
}
