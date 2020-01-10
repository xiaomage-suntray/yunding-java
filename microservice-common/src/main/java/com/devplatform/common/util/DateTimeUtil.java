
package com.devplatform.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 
 *
 */
public class DateTimeUtil {
    
    public static final String _DDMMYY = "ddMMyy";
    
    public static final String _MMDDYY = "MMddyy";
    
    public static final String _YYMMDD = "yyMMdd";
    
    public static final String _YY_MM_DD = "yy-MM-dd";
    
    public static final String _YYYYMMDD = "yyyyMMdd";
    
    public static final String _YYYY_MM_DD = "yyyy-MM-dd";
    
    public static final String _YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    public static final String _YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    
    public static final String _YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    
    public static final String _YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    
    public static final String _HHMM = "HHmm";
    
    public static final String _HHMMSS = "HHmmss";
    
    public static final String _HH_MM_SS = "HH:mm:ss";
    
    public static final String _HH_MM_SS_SSS = "HH:mm:ss.SSS";
    
    public static Date clearTime(Date date) {
        return minTime(date);
    }
    
    public static Date minTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }
    
    public static Date maxTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 999);
        return cal.getTime();
    }
    
    public static int getInteger(Date date, int calendarDateType) {
        if (date == null)
            return 0;
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendarDateType);
    }
    
    public static String parse2String(Date date) {
        return parse2String(date, "yyyy-MM-dd");
    }
    
    public static String parse2String(Date date, String format) {
        if (StringUtils.isBlank(format))
            format = "yyyy-MM-dd";
        
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return ((date != null) ? formater.format(date) : "");
    }
    
    public static String parse2String(String dateStr, String dateFormat, String timeStr, String timeFormat, String format) {
        if (StringUtils.isBlank(dateFormat))
            dateFormat = "yyyy-MM-dd";
        
        if (StringUtils.isBlank(timeFormat))
            format = "HH:mm:ss";
        
        if (StringUtils.isBlank(format))
            format = "yyyy-MM-dd HH:mm:ss";
        
        if ((StringUtils.isBlank(dateStr)) || (StringUtils.isBlank(timeStr)))
            return "";
        
        Date date = parse2Date(dateStr, dateFormat);
        Date time = parse2Date(timeStr, timeFormat);
        
        Calendar timeal = Calendar.getInstance();
        timeal.setTime(time);
        
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.set(11, timeal.get(11));
        dateCal.set(12, timeal.get(12));
        dateCal.set(13, timeal.get(13));
        
        return parse2String(dateCal.getTime(), format);
    }
    
    public static Date parse2Date(Date date) {
        return parse2Date(date, "yyyy-MM-dd");
    }
    
    public static Date parse2Date(String format) {
        return parse2Date(new Date(), format);
    }
    
    public static Date parse2Date(Date date, String format) {
        if (StringUtils.isBlank(format))
            format = "yyyy-MM-dd";
        
        if (date == null)
            return null;
        
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            date = formater.parse(date.toString());
        }
        catch (ParseException e) {
        }
        return date;
    }
    
    public static Date parse2Date(String dateStr, String format) {
        if (StringUtils.isBlank(format))
            format = "yyyy-MM-dd";
        
        if (StringUtils.isBlank(dateStr))
            dateStr = getDateTimeStr(format);
        
        SimpleDateFormat formater = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formater.parse(dateStr);
        }
        catch (ParseException e) {
        }
        return date;
    }
    
    public static Date parse2Date(String dateStr, String timeStr, String format) {
        if (StringUtils.isBlank(format))
            format = "yyyy-MM-dd HH:mm:ss";
        
        if ((StringUtils.isBlank(dateStr)) || (StringUtils.isBlank(timeStr)))
            return null;
        
        Date date = parse2Date(dateStr, "ddMMyy");
        Date time = parse2Date(timeStr, "HHmmss");
        
        Calendar timeal = Calendar.getInstance();
        timeal.setTime(time);
        
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.set(11, timeal.get(11));
        dateCal.set(12, timeal.get(12));
        dateCal.set(13, timeal.get(13));
        
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            date = formater.parse(parse2String(dateCal.getTime(), format));
        }
        catch (ParseException e) {
        }
        return date;
    }
    
    public static Date getDate_YYYYMMDD() {
        return parse2Date("yyyyMMdd");
    }
    
    public static String getDateStr_YYYYMMDD() {
        return getDateTimeStr("yyyyMMdd");
    }
    
    public static Date getDate_YYYY_MM_DD() {
        return parse2Date("yyyy-MM-dd");
    }
    
    public static String getDateStr_YYYY_MM_DD() {
        return getDateTimeStr("yyyy-MM-dd");
    }
    
    public static String getTimeStr_HHMM() {
        return getDateTimeStr("HHmm");
    }
    
    public static String getTimeStr_HHMMSS() {
        return getDateTimeStr("HHmmss");
    }
    
    public static String getTimeStr_HH_MM_SS() {
        return getDateTimeStr("HH:mm:ss");
    }
    
    public static String getTimeStr_HH_MM_SS_SSS() {
        return getDateTimeStr("HH:mm:ss.SSS");
    }
    
    public static Date getDateTime_YYYYMMDDHHMMSS() {
        return parse2Date("yyyyMMddHHmmss");
    }
    
    public static String getDateTimeStr_YYYYMMDDHHMMSS() {
        return getDateTimeStr("yyyyMMddHHmmss");
    }
    
    public static Date getDateTime_YYYYMMDDHHMMSSSSS() {
        return parse2Date("yyyyMMddHHmmssSSS");
    }
    
    public static String getDateTimeStr_YYYYMMDDHHMMSSSSS() {
        return getDateTimeStr("yyyyMMddHHmmssSSS");
    }
    
    public static Date getDateTime_YYYY_MM_DD_HH_MM_SS() {
        return parse2Date("yyyy-MM-dd HH:mm:ss");
    }
    
    public static String getDateTimeStr_YYYY_MM_DD_HH_MM_SS() {
        return getDateTimeStr("yyyy-MM-dd HH:mm:ss");
    }
    
    public static Date getDateTime_YYYY_MM_DD_HH_MM_SS_SSS() {
        return parse2Date("yyyy-MM-dd HH:mm:ss.SSS");
    }
    
    public static String getDateTimeStr_YYYY_MM_DD_HH_MM_SS_SSS() {
        return getDateTimeStr("yyyy-MM-dd HH:mm:ss.SSS");
    }
    
    public static Date getDateTime(String format) {
        return parse2Date(format);
    }
    
    public static String getDateTimeStr(String format) {
        return parse2String(new Date(), format);
    }
    
    public static String getFirstDayByCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        return parse2String(calendar.getTime(), "yyyy-MM-dd");
    }
    
    public static String getLastDayByCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, 1);
        calendar.roll(5, -1);
        return parse2String(calendar.getTime(), "yyyy-MM-dd");
    }
    
    public static Date getDateOfNextMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, i);
        return cal.getTime();
    }
    
    public static int getYear() {
        return getYear(new Date());
    }
    
    public static int getYear(String date) {
        return getYear(parse2Date(date));
    }
    
    public static int getYear(Date date) {
        return getInteger(date, 1);
    }
    
    public static int getMonth() {
        return getMonth(new Date());
    }
    
    public static int getMonth(String date) {
        return getMonth(parse2Date(date));
    }
    
    public static int getMonth(Date date) {
        return (getInteger(date, 2) + 1);
    }
    
    public static int getDay() {
        return getDay(new Date());
    }
    
    public static int getDay(String date) {
        return getDay(parse2Date(date));
    }
    
    public static int getDay(Date date) {
        return getInteger(date, 5);
    }
    
    public static int getHour() {
        return getHour(new Date());
    }
    
    public static int getHour(String date) {
        return getHour(parse2Date(date, "yyyy-MM-dd HH:mm:ss"));
    }
    
    public static int getHour(String date, String format) {
        return getHour(parse2Date(date, format));
    }
    
    public static int getHour(Date date) {
        return getInteger(date, 11);
    }
    
    public static int getMinute() {
        return getMinute(new Date());
    }
    
    public static int getMinute(String date) {
        return getMinute(parse2Date(date, "yyyy-MM-dd HH:mm:ss"));
    }
    
    public static int getMinute(Date date) {
        return getInteger(date, 12);
    }
    
    public static int getSecond() {
        return getSecond(new Date());
    }
    
    public static int getSecond(String date) {
        return getSecond(parse2Date(date, "yyyy-MM-dd HH:mm:ss"));
    }
    
    public static int getSecond(Date date) {
        return getInteger(date, 13);
    }
    
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(parse2Date(date, "yyyy-MM-dd"), parse2Date(otherDate, "yyyy-MM-dd"), true);
    }
    
    public static int getIntervalDays(String date, String otherDate, boolean isAbsolute) {
        return getIntervalDays(parse2Date(date, "yyyy-MM-dd"), parse2Date(otherDate, "yyyy-MM-dd"), isAbsolute);
    }
    
    public static int getIntervalDays(Date date, Date otherDate, boolean isAbsolute) {
        date = parse2Date(date, "yyyy-MM-dd");
        otherDate = parse2Date(otherDate, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long time1 = cal.getTimeInMillis();
        cal.setTime(otherDate);
        long time2 = cal.getTimeInMillis();
        long between_days = Long.parseLong("0");
        if (time2 > time1)
            between_days = (time2 - time1) / 86400000L * -1L;
        else
            between_days = (time1 - time2) / 86400000L;
        
        between_days = (isAbsolute) ? Math.abs(between_days) : between_days;
        return Integer.parseInt(String.valueOf(between_days));
    }
    
    public static int getMonthDiff(Date startDate, Date endDate) throws ParseException {
        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate);
        
        int sYear = starCal.get(1);
        int sMonth = starCal.get(2);
        int sDay = starCal.get(5);
        
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int eYear = endCal.get(1);
        int eMonth = endCal.get(2);
        int eDay = endCal.get(5);
        
        int monthday = (eYear - sYear) * 12 + eMonth - sMonth;
        
        if (sDay > eDay)
            monthday -= 1;
        
        return monthday;
    }
    
    public static Date addDateTime(int value, int calendarDateType) {
        return addDateTime(new Date(), value, calendarDateType);
    }
    
    public static Date addDateTime(Date date, int value, int calendarDateType) {
        if (date == null)
            return new Date();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarDateType, value);
        return calendar.getTime();
    }
    
    public static boolean isBetween(Date start, Date end, Date date) {
        if ((start == null) || (end == null) || (date == null))
            return false;
        
        start = minTime(start);
        end = minTime(end);
        date = minTime(date);
        
        Calendar sCal = Calendar.getInstance();
        sCal.setTime(start);
        Calendar eCal = Calendar.getInstance();
        eCal.setTime(end);
        Calendar dCal = Calendar.getInstance();
        dCal.setTime(date);
        
        boolean flag1 = (sCal.compareTo(dCal) >= 0) && (eCal.compareTo(dCal) <= 0);
        boolean flag2 = (sCal.compareTo(dCal) <= 0) && (eCal.compareTo(dCal) >= 0);
        
        return ((flag1) || (flag2));
    }
    
    public static void test() {
        Date date = new Date();
        System.out.println(clearTime(date));
        System.out.println(getDateStr_YYYYMMDD());
        System.out.println(getDateStr_YYYY_MM_DD());
        System.out.println(getDateTimeStr_YYYYMMDDHHMMSS());
        System.out.println(getDateTimeStr_YYYYMMDDHHMMSSSSS());
        System.out.println(getDateTimeStr_YYYY_MM_DD_HH_MM_SS());
        System.out.println(getDateTimeStr_YYYY_MM_DD_HH_MM_SS_SSS());
        System.out.println(getFirstDayByCurrentMonth());
        System.out.println(getLastDayByCurrentMonth());
        System.out.println(getTimeStr_HH_MM_SS());
        System.out.println(getTimeStr_HH_MM_SS_SSS());
        System.out.println("getDateOfNextMonth");
        System.out.println(parse2String(getDateOfNextMonth(date, 1)));
        System.out.println(parse2String(getDateOfNextMonth(date, 2)));
        System.out.println(parse2String(getDateOfNextMonth(date, 5)));
        System.out.println(parse2String(getDateOfNextMonth(date, -3)));
        System.out.println("Year");
        System.out.println(getYear());
        System.out.println(getYear(date));
        System.out.println(getYear("2014-4-17"));
        System.out.println("Month");
        System.out.println(getMonth());
        System.out.println(getMonth(date));
        System.out.println(getMonth("2014-4-17"));
        System.out.println("Day");
        System.out.println(getDay());
        System.out.println(getDay(date));
        System.out.println(getDay("2014-4-17"));
        System.out.println("Hour");
        System.out.println(getHour());
        System.out.println(getHour(date));
        System.out.println(getHour("2014-4-17"));
        System.out.println(getHour("2014-4-17 10:47:56"));
        System.out.println(getHour("10:47:56", "HH:mm:ss"));
        System.out.println(getHour("10:00:00", "HH:mm:ss"));
        System.out.println(getHour("10:00:01", "HH:mm:ss"));
        System.out.println(getHour("10:59:59", "HH:mm:ss"));
        System.out.println(getHour("00:00:00", "HH:mm:ss"));
        System.out.println(getHour("00:59:59", "HH:mm:ss"));
        System.out.println(getHour("01:00:00", "HH:mm:ss"));
        System.out.println("Minute");
        System.out.println(getMinute());
        System.out.println(getMinute(date));
        System.out.println(getMinute("2014-4-17"));
        System.out.println(getMinute("2014-4-17 10:47:56"));
        System.out.println("IntervalDays");
        System.out.println(getIntervalDays("2014-04-19 10:33:22", "2014-04-17 22:33:22"));
        System.out.println(getIntervalDays("2014-04-17 10:33:22", "2014-04-19 22:33:22"));
        System.out.println(getIntervalDays("2014-04-17", "2014-04-17"));
        System.out.println(getIntervalDays("2014-04-17", "2014-04-18"));
        System.out.println(getIntervalDays("2014-04-17", "2014-04-17 22:33:22"));
        System.out.println(getIntervalDays("2014-04-17 10:33:22", "2014-04-17"));
        System.out.println(getIntervalDays("2014-04-17", "2014-04-18 22:33:22"));
        System.out.println(getIntervalDays("2014-04-17 10:33:22", "2014-04-18"));
        System.out.println("Second");
        System.out.println(getSecond());
        System.out.println(getSecond(date));
        System.out.println(getSecond("2014-4-17"));
        System.out.println(getSecond("2014-4-17 10:47:56"));
        System.out.println("parse2Date");
        System.out.println(parse2Date("01:00:00", "HH:mm:ss"));
        System.out.println(parse2Date("110414", "111310", "yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse2String("110414", "ddMMyy", "111310", "HHmmss", "yyyy-MM-dd HH:mm:ss"));
        System.out.println();
        int i = getIntervalDays(parse2Date("110415", "111310", "yyyy-MM-dd HH:mm:ss"), date, true);
        
        System.out.println(i);
        System.out.println("addDateTime");
        System.out.println(parse2String(date, "yyyy-MM-dd HH:mm:ss") + "\t" + parse2String(addDateTime(date, 3, 1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse2String(date, "yyyy-MM-dd HH:mm:ss") + "\t" + parse2String(addDateTime(date, 3, 2), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse2String(date, "yyyy-MM-dd HH:mm:ss") + "\t" + parse2String(addDateTime(date, 3, 5), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse2String(date, "yyyy-MM-dd HH:mm:ss") + "\t" + parse2String(addDateTime(date, 3, 11), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse2String(date, "yyyy-MM-dd HH:mm:ss") + "\t" + parse2String(addDateTime(date, 3, 12), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse2String(date, "yyyy-MM-dd HH:mm:ss") + "\t" + parse2String(addDateTime(date, 3, 13), "yyyy-MM-dd HH:mm:ss"));
        
        System.out.println("isBetween");
        System.out.println(
                isBetween(parse2Date("2015-01-01", "yyyy-MM-dd"), parse2Date("2015-01-03", "yyyy-MM-dd"), parse2Date("2015-01-02", "yyyy-MM-dd")));
        
        System.out.println(
                isBetween(parse2Date("2015-01-03", "yyyy-MM-dd"), parse2Date("2015-01-01", "yyyy-MM-dd"), parse2Date("2015-01-02", "yyyy-MM-dd")));
        
        System.out.println(
                isBetween(parse2Date("2015-01-01", "yyyy-MM-dd"), parse2Date("2015-01-01", "yyyy-MM-dd"), parse2Date("2015-01-01", "yyyy-MM-dd")));
        
        System.out.println(
                isBetween(parse2Date("2015-01-01", "yyyy-MM-dd"), parse2Date("2015-01-02", "yyyy-MM-dd"), parse2Date("2015-01-01", "yyyy-MM-dd")));
        
        System.out.println(
                isBetween(parse2Date("2015-01-01", "yyyy-MM-dd"), parse2Date("2015-01-02", "yyyy-MM-dd"), parse2Date("2015-01-02", "yyyy-MM-dd")));
        
        System.out.println(
                isBetween(parse2Date("2015-01-01", "yyyy-MM-dd"), parse2Date("2015-01-02", "yyyy-MM-dd"), parse2Date("2015-01-03", "yyyy-MM-dd")));
    }
    
}
