package com.lh.cloud.tool;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: LH
 * @Description: 日期转换工具类
 * @Date: 11:02 2017/7/11
 */
public class DateUtils implements Serializable {
    /**
     * yyyy-MM-dd HH:mm:ss 格式
     */
    public static final String DEFAULT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm 格式
     */
    public static final String DEFAULT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd 格式
     */
    public static final String DEFAULT_YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * yyyy-MM 格式
     */
    public static final String DEFAULT_YYYY_MM = "yyyy-MM";

    /**
     * 锁对象
     */
    private static final Object lockObj = new Object();

    /**
     * 存放不同的日期模板格式的sdf的Map
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null) {
            synchronized (lockObj) {
                tl = sdfMap.get(pattern);
                if (tl == null) {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map

                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>() {

                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    public static SimpleDateFormat getDefaultSdf() {
        return getSdf(DEFAULT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static String format(String date, String pattern, String beforPattern) throws ParseException {
        Date parse = getSdf(pattern).parse(date);
        return getSdf(beforPattern).format(parse);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String now() {
        return getDefaultSdf().format(new Date());
    }

    /**
     * 获取一年
     *
     * @return
     */
    public static int year() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, -1);
        int year = instance.get(Calendar.YEAR);
        return year;
    }

    /**
     * @param date        日期字符串
     * @param datePattern 日期格式
     * @return
     */
    public static java.util.Date strFormatDate(String date, String datePattern) {
        SimpleDateFormat sd = new SimpleDateFormat(datePattern);
        return sd.parse(date, new java.text.ParsePosition(0));
    }


    /**
     * 将指定日期对象转换成格式化字符串
     *
     * @param date        XML日期对象
     * @param datePattern 日期格式
     * @return
     */
    public static String dateFormattedString(Date date, String datePattern) {
        SimpleDateFormat sd = new SimpleDateFormat(datePattern);
        return sd.format(date);
    }

    /**
     * @param time1 当前时间
     * @param time2 比较时间
     * @return 如果time1比time2大gap分钟，则返回true;
     */
    public static boolean compareDateTime(Date time1, Date time2, int gap) {
        return time1.getTime() - time2.getTime() > gap * 60 * 1000;
    }

    /**
     * 比较日期大小
     *
     * @param dt1 date 1
     * @param dt2 date2
     * @return 返回true 第一个时间比较靠后
     */
    public static boolean compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime()) {
            return true;
        } else if (dt1.getTime() < dt2.getTime()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s 需要转换的时间
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(String s) throws ParseException {
        long ts = getDefaultSdf().parse(s).getTime();
        return String.valueOf(ts);
    }

    /**
     * 将时间转换为时间戳
     *
     * @param s 需要转换的时间
     * @return
     * @throws ParseException
     */
    public static long dateToStamps(String s) throws Exception {
        try {
            return getDefaultSdf().parse(s).getTime();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    /**
     * 当前减加上月
     *
     * @return
     * @throws ParseException
     */
    public static Date nowMinusMonth(int month) {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) - month);
        return curr.getTime();
    }

    /**
     * 将时间戳转换为时间
     *
     * @param s 时间戳
     * @return
     */
    public static String stampToDate(String s) {
        return getDefaultSdf().format(new Date(new Long(s)));
    }

    /**
     * 时间减去小时
     *
     * @param hour 分钟
     * @return
     */
    public static String timeMistaHour(String dateTime, int hour) {
        long time = 0; // 得到指定日期的毫秒数
        try {
            time = getDefaultSdf().parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hour = hour * 60 * 60 * 1000; // 转换成毫秒数
        time -= hour; // 相加得到新的毫秒数
        return dateFormattedString(new Date(time), DEFAULT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间减分钟数
     *
     * @param minute 分钟
     * @return
     */
    public static String timeMistaMinute(String dateTime, int minute) {
        long time = 0; // 得到指定日期的毫秒数
        try {
            time = getDefaultSdf().parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        minute = minute * 60 * 1000; // 转换成毫秒数
        time -= minute; // 相加得到新的毫秒数
        return dateFormattedString(new Date(time), DEFAULT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间加上分钟数
     *
     * @param minute 分钟
     * @return
     */
    public static String timeSumMinute(String dateTime, int minute) {
        long time = 0; // 得到指定日期的毫秒数
        try {
            time = getDefaultSdf().parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        minute = minute * 60 * 1000; // 转换成毫秒数
        time += minute; // 相加得到新的毫秒数
        return dateFormattedString(new Date(time), DEFAULT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间加上秒数
     *
     * @param dateTime 时间
     * @param second   秒数
     * @return
     */
    public static String timeSumSecond(String dateTime, int second) {
        long time = 0; // 得到指定日期的毫秒数
        try {
            time = getDefaultSdf().parse(dateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        second = second * 1000;
        time += second;
        return dateFormattedString(new Date(time), DEFAULT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 指定时间减天数
     *
     * @param dateTime 指定时间
     * @param day      天数
     * @return
     * @throws ParseException
     */
    public static String timeMistakeDay(String dateTime, int day) {
        Date date = null; // 指定日期
        try {
            date = getDefaultSdf().parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date newDate = addDate(date, day); // 指定日期加上天
        return getDefaultSdf().format(newDate);
    }

    private static Date addDate(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time -= day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    public static int getYearMonth() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR) * 100;
        int month = instance.get(Calendar.MONTH) + 1;
        return year + month;
    }

    public static int getYear() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        return year;
    }

    //获取当前月的天数
    public static int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    //获取当前月份
    public static int getMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int month = aCalendar.get(Calendar.MONTH) + 1;
        return month;
    }

    //获取当前号数
    public static int getNowDay() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.get(Calendar.DATE);
        return day;
    }

    /**
     * 两个日期相减 获取分钟数
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static int getDateMinute(String startTime, String endTime) {
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            try {
                long l = getDefaultSdf().parse(startTime).getTime() - getDefaultSdf().parse(endTime).getTime();
                return Math.abs((int) l / (1000 * 60));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }


    /**
     * 格式转换
     *
     * @param dateTime
     * @param datePattern
     * @return
     */
    public static synchronized String srtDateFormatStr(String dateTime, String datePattern) {
        try {
            return dateFormattedString(new Date(getDefaultSdf().parse(dateTime).getTime()), datePattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期 DEFAULTYYYYMM_DD
     *
     * @return
     */
    public static int getDay() {
        return Integer.parseInt(DateUtils.dateFormattedString(new Date(), DateUtils.DEFAULT_YYYY_MM_DD).replace("-", ""));
    }

    /**
     * 获取当前日期 DEFAULT_YYYY_MM_DD
     *
     * @return
     */
    public static String getDayStr() {
        return DateUtils.dateFormattedString(new Date(), DateUtils.DEFAULT_YYYY_MM_DD).replace("-", "");
    }

    public static void main(String[] args) throws ParseException {
       /* System.out.println(compareDate(new Date(),strFormatDate("2017-07-11 13:46:49",DEFAULT_YYYY_MM_DD_HH_MM_SS)));
        System.out.println(timeSumMinute(now(), 10));
        System.out.println(timeSumSecond(now(), 10));*/
       /* String s = DateUtils.timeSumMinute(DateUtils.now(), 10);
        long l = getDefaultSdf().parse(s).getTime() - getDefaultSdf().parse(DateUtils.now()).getTime();
        System.out.println(l / (1000 * 60));*/
    }
}
