package com.glue.framework.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DateTools {
	 public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
	    public static final String DATE_FORMAT_DAY_2 = "yyyy/MM/dd";
	    public static final String TIME_FORMAT_SEC = "HH:mm:ss";
	    public static final String DATE_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";
	    public static final String DATE_FORMAT_MSEC = "yyyy-MM-dd HH:mm:ss.SSS";
	    public static final String DATE_FORMAT_MSEC_T = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	    public static final String DATE_FORMAT_MSEC_T_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	    public static final String DATE_FORMAT_DAY_SIMPLE = "y/M/d";

	    /**
	     * 匹配yyyy-MM-dd
	     */
	    private static final String DATE_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
	    /**
	     * 匹配yyyy/MM/dd
	     */
	    private static final String DATE_REG_2 = "^[1-9]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])$";
	    /**
	     * 匹配y/M/d
	     */
	    private static final String DATE_REG_SIMPLE_2 = "^[1-9]\\d{3}/([1-9]|1[0-2])/([1-9]|[1-2][0-9]|3[0-1])$";
	    /**
	     * 匹配HH:mm:ss
	     */
	    private static final String TIME_SEC_REG = "^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
	    /**
	     * 匹配yyyy-MM-dd HH:mm:ss
	     */
	    private static final String DATE_TIME_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s" +
	            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
	    /**
	     * 匹配yyyy-MM-dd HH:mm:ss.SSS
	     */
	    private static final String DATE_TIME_MSEC_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s" +
	            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
	    /**
	     * 匹配yyyy-MM-dd'T'HH:mm:ss.SSS
	     */
	    private static final String DATE_TIME_MSEC_T_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T" +
	            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}$";
	    /**
	     * 匹配yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
	     */
	    private static final String DATE_TIME_MSEC_T_Z_REG = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T" +
	            "(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d\\.\\d{3}Z$";


	    /**
	     * str转换成date
	     * @param str
	     * @return
	     * @throws ParseException
	     */
	    public static Date str2Date(String strDate) throws Exception {

	        strDate = strDate.trim();
	        SimpleDateFormat sdf = null;
	        if (isMatched(strDate, DATE_REG)) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_DAY);
	        }
	        if (isMatched(strDate, DATE_REG_2)) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_DAY_2);
	        }
	        if (isMatched(strDate, DATE_REG_SIMPLE_2)) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_DAY_SIMPLE);
	        }
	        if (isMatched(strDate, TIME_SEC_REG)) {
	            sdf = new SimpleDateFormat(TIME_FORMAT_SEC);
	        }
	        if (isMatched(strDate, DATE_TIME_REG)) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_SEC);
	        }
	        if (isMatched(strDate, DATE_TIME_MSEC_REG)) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC);
	        }
	        if (isMatched(strDate, DATE_TIME_MSEC_T_REG)) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC_T);
	        }
	        if (isMatched(strDate, DATE_TIME_MSEC_T_Z_REG)) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC_T_Z);
	        }
	        if (null != sdf) {
	            return sdf.parse(strDate);
	        }
	        throw new Exception(
	                String.format("[%s] can not matching right time format", strDate));
	    }
	    /**
	     * data转换成str  
	     * @param str
	     * @return
	     * @throws ParseException
	     */
	    public static String date2Str(Date date,String pattern) throws Exception {
	        SimpleDateFormat sdf = null;
	        if ((DATE_FORMAT_DAY.equals(pattern))) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_DAY);
	        }
	        if ((DATE_FORMAT_DAY_2.equals(pattern))) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_DAY_2);
	        }
	        if ((DATE_FORMAT_DAY_SIMPLE.equals(pattern))) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_DAY_SIMPLE);
	        }
	        if ((TIME_FORMAT_SEC.equals(pattern))) {
	            sdf = new SimpleDateFormat(TIME_FORMAT_SEC);
	        }
	        if ((DATE_FORMAT_SEC.equals(pattern))) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_SEC);
	        }
	        if ((DATE_FORMAT_MSEC.equals(pattern))) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC);
	        }
	        if ((DATE_FORMAT_MSEC_T.equals(pattern))) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC_T);
	        }
	        if ((DATE_FORMAT_MSEC_T_Z.equals(pattern))) {
	            sdf = new SimpleDateFormat(DATE_FORMAT_MSEC_T_Z);
	        }
	        if (null != sdf) {
	            return sdf.format(date);
	        }
	        throw new Exception(
	                String.format("[%s] can not matching right time format", date));

	    }
	    /**
	     * long转换成date
	     * @param str
	     * @return
	     * @throws ParseException
	     */
		 public static Date long2Date(String str) throws ParseException {
		        Long str1 = Long.parseLong(str);
		        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String str2 = simpleDateFormat.format(str1);
		        //System.out.println("-->"+str2);
		        return  simpleDateFormat.parse(str2);//Wed Jun 06 10:28:15 CST 2018
		    }
	    	 
    	/**
    	   * 比较日期对象 相等返回0，date1早于date2返回-1，date1晚于date2返回1 
    	   * 比较两个Date是否相等
    	 * @param date1
    	 * @param date2
    	 * @return
    	 */
	    
	    public static int compareDate(Date date1,Date date2){
	        return date1.compareTo(date2);
	    }
        /**
                                * 获取Date毫秒数
         * @param date
         * @return
         */
	    public static String getDateTimes(Date date){
	        Long date1 = date.getTime();
	        return date1.toString();
	    }

	    /**
	     * <p>判断内容是否匹配</p>
	     * @param pattern 匹配目标内容
	     * @param reg     正则表达式
	     * @return 返回boolean
	     */
	    public static boolean isMatched(String pattern, String reg) {
	        Pattern compile = Pattern.compile(reg);
	        return compile.matcher(pattern).matches();
	    }

	  

	 
	 public static void main(String[] args) throws Exception {
	        String str1="1528252095811";
	        String str2="2018/6/29";
	        String str3="2018-06-29 14:12:28";

	       // System.out.println(long2Date(str1));//Wed Jun 06 10:28:15 CST 2018
	        System.out.println(str2Date(str2));//Fri Jun 29 00:00:00 CST 2018
	      //  System.out.println(str2Date(str3));//Fri Jun 29 14:12:28 CST 2018
	      //  System.out.println(date2Str(new Date(),DATE_FORMAT_MSEC));//2018-10-24 10:21:14.189
	     //   System.out.println(compareDate(long2Date(str1),str2Date(str2)));//-1
	      //  System.out.println(getDateTimes(new Date()));//1540347818396

	    }
	// String2Date。
	//SimpleDateFormat
	// Date2String。
	
}
