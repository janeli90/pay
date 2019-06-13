package com.janeli.pay.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    public static String formatDateStr(Date date) {
        return formatDateByFormat(date, "yyyyMMdd");
    }

    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static Date parseDate(java.sql.Date date) {
        return date;
    }

    public static Date parseDate(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(date);
    }

    public static Date parseDate(String date, String format)
            throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

    public static java.sql.Date parseSqlDate(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                DateFormat df = new SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public static List<String> getDaysByWeek(Date date)
            throws Exception {
        date = DateUtils.getdate(DateUtils.format1(date));
        List<String> days = new ArrayList<String>();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        String firstWeek = DateUtils.getFirstWeekDay(date);
        for (int i = 0; i < 7; i++) {
            Date firstWeekDate = DateUtils.getdate1(firstWeek);
            firstWeekDate = DateUtils.addDate(firstWeekDate, i);
            int j = DateUtils.diffDate(date, firstWeekDate);
            if (j >= 0) {
                days.add(DateUtils.format1(firstWeekDate));
            } else {
                break;
            }
        }
        return days;
    }

    public static Date getNextWeek(Date date, int count) {
        Calendar strDate = Calendar.getInstance();
        strDate.setTime(date);
        strDate.add(strDate.DATE, count * 7);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.set(strDate.get(Calendar.YEAR),
                strDate.get(Calendar.MONTH), strDate.get(Calendar.DATE));
        Date day = currentDate.getTime();
        return day;
    }

    /**
     * 功能描述：根据日期获取天
     *
     * @param date
     * @return <p>
     * 修改历史 ：(修改人，修改时间，修改原因/内容)
     * </p>
     * @author 刘文军(bluesky.liu)
     * <p>
     * 创建日期 ：2013-5-27 上午10:48:19
     * </p>
     */
    public static List<String> getDaysByDate(Date date) {
        List<String> days = new ArrayList<String>();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        int dayss = DateUtils.getDay(date);
        String monthStr = DateUtils.format(date, "yyyy-MM");
        for (int i = 1; i <= dayss; i++) {
            String day = new String();
            if (i < 10) {
                day = monthStr + "-0" + i;
            } else {
                day = monthStr + "-" + i;
            }
            days.add(day);
        }
        return days;
    }

    /**
     * 当前时间的周一
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static String getFirstWeekDay(Date theDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(theDate);
        // 取得本周一
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateUtils.format(calendar.getTime(), "yyyy-MM-dd") + " 00:00:00";
    }

    /**
     * 当前时间的周日
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static String getLastWeekDay(Date theDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(theDate);
        // 取得本周日
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return DateUtils.format(new Date(calendar.getTime().getTime()
                + (6 * 24 * 60 * 60 * 1000)), "yyyy-MM-dd")
                + " 23:59:59";
    }

    /**
     * 当月第一天
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static String getFirstDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(
                " 00:00:00");
        return str.toString();
    }

    /**
     * 功能描述：获取一月 month为1就是上一个月 为-1就是下一个月
     *
     * @param theDate
     * @return <p>
     * 修改历史 ：(修改人，修改时间，修改原因/内容)
     * </p>
     * @throws ParseException
     * @author 刘文军(bluesky.liu)
     * <p>
     * 创建日期 ：2013-5-27 下午12:39:22
     * </p>
     */
    @SuppressWarnings("unused")
    public static Date getUpMonth(Date theDate, int month)
            throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(theDate);
        c.add(Calendar.MONTH, month);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(c.getTime());
        return format.parse(time);
    }

    /**
     * 当月最后一天
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static String getLastDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH,
                gcLast.getActualMaximum(Calendar.DAY_OF_MONTH));
        String s = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();
    }

    public static String getMinDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 00:00:00");
        return str.toString();
    }

    public static String getMaxDay(Date theDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();
    }

    public static String format(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    public static String format1(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String format2(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss ");
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
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public static String getDate(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    public static String getDate(Date date, String formatStr) {
        return format(date, formatStr);
    }

    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    public static String getDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
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
     * @param date Date
     * @param day  int
     * @return Date
     */
    public static Date subDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
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

    public static Date getdate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public static Date getDate(String date, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    public static Date getJsonDate(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.parse(date);
    }

    public static Date getdate1(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    public static Date getMaxTimeByStringDate(String date)
            throws Exception {
        String maxTime = date + " 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(maxTime);
    }

    /**
     * 获得当前时间
     *
     * @return
     */
    public static Date getCurrentDateTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = DateUtils.getDateTime(date);
        try {
            return sdf.parse(result);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    public static String getCurrentDateTimeToStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(getCurrentDateTime());
    }

    public static String getCurrentDateTimeToStr1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(getCurrentDateTime());
    }

    public static String getCurrentDateTimeToStr2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(getCurrentDateTime());
    }

    public static java.sql.Timestamp getCurrentDateTimeToSqlDateStamp() {
        return new java.sql.Timestamp(getCurrentDateTime().getTime());
    }

    public static Long getWmsupdateDateTime() {
        Calendar cl = Calendar.getInstance();

        return cl.getTimeInMillis();
    }

    public static Integer getLeftSeconds(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date condition = sdf.parse(date);
        long n = condition.getTime();
        long s = sdf.parse(getCurrentDateTimeToStr2()).getTime();
        // System.out.println("开始时间:"+date+"-->"+(int)((s-n)/1000));
        return (int) ((s - n) / 1000);
    }


    public static Date getNowDate() {
        try {
            return getdate(format1(new Date()));
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 功能描述：得到两个日期之间的日期list
     *
     * @param beginDate
     * @param endDate
     * @return <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @author <p>创建日期 ：
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }

    public static String getDateString() {
        String time = String.valueOf(System.currentTimeMillis());
        time = time.substring(time.length() - 4);
        String str = sdf.format(new Date()) + time;
        return str;
    }

    public static String getCurrentDateTimeToStr3() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        return sdf1.format(new Date());
    }

    public static Date getNextMonth(Date date, int i) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, i);//把日期往后增加一个月.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一个月的结果
        System.out.println(date);
        return date;
    }

    public static int compareDate(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //int i= compareDate("1999-12-20", "1999-12-11");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int dateSpaceDay(String dbtime1, String dbtime2) {
        try {
            //算两个日期间隔多少天
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(dbtime1);
            Date date2 = format.parse(dbtime2);
            int a = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
            return a;
        } catch (Exception exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static boolean isAfterTime(String dbtime, Integer hour){
        try {
            //算两个日期间隔多少天
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(dbtime);

            //判断date1是否为今天
            Calendar dbCalendar = Calendar.getInstance();
            dbCalendar.setTime(date1);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            if(dbCalendar.get(Calendar.DATE) == calendar.get(Calendar.DATE)){
                //如果是今天
                Date deadTime = calendar.getTime();
                Date now = new Date();
                if(now.after(deadTime)){
                    return true;
                }
            }
            return false;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 获取最近七天的日期
     * @return
     */
    public static List<Date> getBeforeWeekDays(){
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
            Date today = calendar.getTime();
            dates.add(today);
        }
        return dates;
    }
    /**
     * 获取最近七天的日期
     * @return
     */
    public static List<String> getBeforeHalfYearMonths(){
        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (int i = 5; i >= 0; i--) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -i);
            Date m = c.getTime();
            dates.add(sdf.format(m));
        }
        return dates;
    }

    public static String dateToYYYYMMDD(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String dateToYYYYMM(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    /**
     * str转换为Date
     * @param str
     * @return
     */
    public static Date strToYYYYMMDate(String str){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
         date = format.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }


    public static Date getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        String dateStr = DateUtils.dateToYYYYMMDD(cal.getTime()) + " 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        String dateStr = DateUtils.dateToYYYYMMDD(cal.getTime()) + " 23:59:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}


