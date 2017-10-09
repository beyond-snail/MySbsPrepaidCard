
package com.tool.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * @author 杨工
	 * @param strInput
	 *            传入String
	 * @return boolean 传入的String是否为空
	 */
	static public boolean isEmpty(String strInput) {
		/*
		 * if(strInput == null) return true; return strInput.length() == 0 ?
		 * true : false;
		 */
		return TextUtils.isEmpty(strInput);

	}

	public static boolean isBlank(String value) {
		return value == null || "null".equals(value) || value.trim().length() <= 0;
	}

	public static boolean isEquals(String str1, String str2) {
		// if (!isEmpty(str1) && !isEmpty(str2)) {
		if (str1.equals(str2)) {
			return true;
		}
		// }
		return false;
	}

	/**
	 * java去除字符串中的空格、回车、换行符、制表符
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}


	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	static protected boolean CheckByte(byte byteIn) {
		// '0' - '9'
		if (byteIn <= 0x39 && byteIn >= 0x30)
			return true;
		// 'A' - 'F'
		if (byteIn <= 0x46 && byteIn >= 0x41)
			return true;
		// 'a' - 'f'
		if (byteIn <= 0x66 && byteIn >= 0x61)
			return true;
		return false;
	}

	static protected boolean CheckString(String strInput) {
		strInput = strInput.trim();
		if (strInput.length() != 2)
			return false;
		byte[] byteArry = strInput.getBytes();
		for (int i = 0; i < 2; i++) {
			if (!CheckByte(byteArry[i]))
				return false;
		}
		return true;
	}

	static protected byte StringToByte(String strInput) {
		byte[] byteArry = strInput.getBytes();
		for (int i = 0; i < 2; i++) {

			if (byteArry[i] <= 0x39 && byteArry[i] >= 0x30) {
				byteArry[i] -= 0x30;
			} else if (byteArry[i] <= 0x46 && byteArry[i] >= 0x41) {
				byteArry[i] -= 0x37;
			} else if (byteArry[i] <= 0x66 && byteArry[i] >= 0x61) {
				byteArry[i] -= 0x57;
			}
		}
		// Log.i("APP", String.format("byteArry[0] = 0x%X\n", byteArry[0]));
		// Log.i("APP", String.format("byteArry[1] = 0x%X\n", byteArry[1]));
		return (byte) ((byteArry[0] << 4) | (byteArry[1] & 0x0F));
	}

	/**
	 * 获取设备序列号
	 * @return
	 */
	public static String getSerial(){
		return Build.SERIAL;
	}

	public static String getSerial1(){
		return Build.SERIAL+"1";
	}

	/**
	 * 求最小值
	 *
	 */
	public static double min(double a, double b, double c) {
		if (a > b) {
			if (b > c) {
				return c;
			} else {
				return b;
			}
		} else {
			if (a > c) {
				return c;
			} else {
				return a;
			}
		}
	}

	/**
	 * @author 杨工
	 * @param String
	 *            strInput
	 * @param byte[]
	 *            arryByte
	 * @return int
	 */
	static public int StringToByteArray(String strInput, byte[] arryByte) {
		strInput = strInput.trim();// 清除空白
		String[] arryString = strInput.split(" ");
		if (arryByte.length < arryString.length)
			return -1;
		for (int i = 0; i < arryString.length; i++) {
			if (!CheckString(arryString[i]))
				return -1;
			arryByte[i] = StringToByte(arryString[i]);
			// Log.i("APP", String.format("%02X", arryByte[i]));
		}

		return arryString.length;
	}

	static public String ByteArrayToString(byte[] arryByte, int nDataLength) {
		String strOut = new String();
		for (int i = 0; i < nDataLength; i++)
			strOut += String.format("%02X ", arryByte[i]);
		return strOut;
	}

	static public String ByteArrayToString(byte[] arryByte) {
		if (arryByte == null || arryByte.length == 0) {
			return "";
		}
		int nDataLength = arryByte.length;
		String strOut = new String();
		for (int i = 0; i < nDataLength; i++)
			strOut += String.format("%02X ", arryByte[i]);
		return strOut;
	}

	static public String ByteArrayToString(byte[] arryByte, int offset, int nDataLength) {
		String strOut = "";
		try {
			for (int i = offset; i < nDataLength; i++) {
				strOut += String.format("%02X ", arryByte[i]);
			}
		} catch (Exception e) {
			strOut = "";
		}
		return strOut;
	}

	/**
	 * @author john.li
	 * @param String
	 *            str 传入字符串
	 * @param String
	 *            reg 按照哪种方式或哪个字段拆分
	 * @return Stringp[] 返回拆分后的数组。
	 */
	static public String[] spiltStrings(String str, String reg) {
		String[] arrayStr = str.split(reg);
		return arrayStr;
	}
	
	
	/**
	 * 随机一个订单号 以时间+7位随机数 
	 * @param order
	 * @return
	 */
	public static String createOrderNo(String order) {
		if (order == null) {
			return getFormatCurTime() + createRandomStr(7);
		} else {
			return order += createRandomStr(1);
		}
	}

	/***
	 * 得到当前日期
	 * 
	 * @return 2012年4月10日 = 20120410
	 */
	public static String getCurDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}

	/**
	 * 获取yyyy-MM-dd'T'HH:mm:ss.SSSZ格式的数据
	 * @return
	 */
	public static String getDateFormateT(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		return df.format(new Date());
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static long getCurTimeMills() {
		return System.currentTimeMillis();
	}


	/**
	 * 日期格式字符串转换成时间戳
	 * @param date_str 字符串日期
	 * @return
     */
	public static long getdate2TimeStamp(String date_str){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(date_str).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 日期格式字符串转换成时间戳
	 * @param date_str 字符串日期
	 * @return
	 */
	public static long getdate2TimeStamp1(String date_str){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			return sdf.parse(date_str).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * @param seconds 精确到秒的字符串
	 * @return
	 */
	public static String timeStamp2Date(String seconds) {
		if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(Long.valueOf(seconds+"000")));
	}


	public static String timeStamp2Date1(String seconds) {
		if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return sdf.format(new Date(Long.valueOf(seconds+"000")));
	}


	/***
	 * 得到当前日期
	 * 
	 * @return 2012年4月10日 = 20120410
	 */
	public static String getDateFormate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}

	/**
	 * 得到当前的年
	 * 
	 * @return
	 */
	public static String getCurYear() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(new Date());
	}

	/***
	 * 得到标准格式当前时间
	 * 
	 * @return 2012-04-17 11:11:19
	 */
	public static String getCurTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}


	public static String getCurTime1() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
		return dateFormat.format(new Date());
	}



	public static Date getDateFromString(String dateString, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date result = null;
		try {
			result = format.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getDateStrFromString(String dateString, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		Date result = null;
		try {
			result = format.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format.format(result);
	}

	public static String getStringFromDate(Date date, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		return format.format(date);
	}

	public static Date getDateFromDate(Date in, int inc) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		calendar.add(Calendar.DATE, inc);
		return calendar.getTime();
	}
	
	public static long getDateFromMillis(Date in, int inc) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		calendar.add(Calendar.DATE, inc);
		return calendar.getTimeInMillis();
	}
	
	/* 
     * 将时间戳转换为时间
     */
    public static Date ltampToDate(long lt){
//        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(lt);
//        res = simpleDateFormat.format(date);
        return date;
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String s){
//        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
//        res = simpleDateFormat.format(date);
        return date;
    }
	
	

	/***
	 * 得到格式化后当前时间
	 * 
	 * @return 20120417111119
	 */
	public static String getFormatCurTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(new Date());
	}

	/***
	 * 得到格式化后当前时间
	 * 
	 * @return 20120417111119
	 */
	public static String getFormatLongTime(long longTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(new Date(longTime));
	}

	/***
	 * 格式化时间
	 * 
	 * @param time
	 *            比如： 20120331151638
	 * @return 2012-03-31 15:16:38
	 */
	public static String formatTime(String time) {
		String timeText = "";
		if (checkNotNull(time) && time.length() == 14) {
			StringBuilder sb = new StringBuilder();
			sb.append(time.substring(0, 4)).append("-").append(time.substring(4, 6)).append("-")
					.append(time.substring(6, 8)).append(" ").append(time.substring(8, 10)).append(":")
					.append(time.substring(10, 12)).append(":").append(time.substring(12, 14));
			timeText = sb.toString();
			sb = null;

		}
		return timeText;
	}

	/**
	 * 检验是否为空
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean checkNotNull(String str) {
		if (null == str || str.trim().equals("") || str.length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 返回一个length长度的随机字串
	 * 
	 * @param length
	 * @return
	 */
	public static String createRandomStr(int length) {
		String randomChars = "1234567890ABCDEF";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int random = new Random().nextInt(9);
			buffer.append(randomChars.charAt(random));
		}
		return buffer.toString();
	}

	/**
	 * 返回一个length长度的纯数字随机字串
	 * 
	 * @param length
	 * @return
	 */
	public static String createRandomNumStr(int length) {
		String randomChars = "123456789";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int random = new Random().nextInt(9);
			buffer.append(randomChars.charAt(random));
		}
		return buffer.toString();
	}

	/***
	 * 把以分的金额单位转化为以元为单位
	 * 
	 * @param money
	 * @return 100分=￥1.00元
	 */
	public static String formatMoney(String money) {
		if (money == null || money.length() == 0) {
			return "￥0.00元";
		} else {
			money = replaceStartZero(money);
			if (money == null || money.length() == 0) {
				return "￥0.00元";
			}
			String newMoney = "";
			int length = money.length();
			if (length == 1) {
				newMoney += "0.0" + money;
			} else if (length == 2) {
				newMoney += "0." + money;
			} else {
				newMoney = money.substring(0, length - 2) + "." + money.substring(length - 2, length);
			}
			return "￥" + newMoney + "元";
		}
	}

	/***
	 * 递归把第一个字符为0替换空
	 * 
	 * @param str
	 * @return
	 */
	private static String replaceStartZero(String str) {
		if (str.startsWith("0")) {
			str = replaceStartZero(str.substring(1, str.length()));
		}
		return str;
	}

	/***
	 * 把字串以分的金额单位转化为以元为单位
	 * 
	 * @param money
	 * @return 100分=1.00
	 */
	public static String formatStrMoney(String money) {
		if (money == null || money.length() == 0) {
			return "0.00";
		} else {
			money = money.replace(".", money);
			money = replaceStartZero(money);
			if (money == null || money.length() == 0) {
				return "0.00";
			}
			String newMoney = "";
			int length = money.length();
			if (length == 1) {
				newMoney += "0.0" + money;
			} else if (length == 2) {
				newMoney += "0." + money;
			} else {
				newMoney = money.substring(0, length - 2) + "." + money.substring(length - 2, length);
			}
			return newMoney;
		}
	}

	/***
	 * 把整型以分的金额单位转化为以元为单位
	 * 
	 * @param money
	 * @return
	 */
	public static String formatIntMoney(int money) {
		return formatStrMoney(money + "");
	}

	/***
	 * 把整型以分的金额单位转化为以元为单位
	 *
	 * @param money
	 * @return
	 */
	public static String formatIntMoney(long money) {
		return formatStrMoney(money + "");
	}

	/**
	 * 屏蔽卡号
	 * 
	 * @param cardNo
	 *            银行卡号
	 * @return 屏蔽后的卡号
	 */
	public static String formatCardNo(String cardNo) {
		if (!checkNotNull(cardNo)) {
			LogUtils.e("屏蔽卡号", " cardNo null");
			return "";
		}
		int length = cardNo.length();

		String shieldedText = "";
		for (int i = 0; i < length - 10; i++) {
			shieldedText += "*";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(cardNo.substring(0, 6)).append(shieldedText).append(cardNo.substring(length - 4, length));
		return sb.toString();
	}

	/**
	 * Method fill string
	 * 
	 * @param The
	 *            string to be format.
	 * 
	 */
	public static String fillString(String formatString, int length, char fillChar, boolean leftFillFlag) {
		if (null == formatString) {
			formatString = "";
		}
		int strLen = formatString.length();
		if (strLen >= length) {
			if (true == leftFillFlag) // left fill
				return formatString.substring(strLen - length, strLen);
			else
				return formatString.substring(0, length);
		} else {
			StringBuffer sbuf = new StringBuffer();
			int fillLen = length - formatString.length();
			for (int i = 0; i < fillLen; i++) {
				sbuf.append(fillChar);
			}

			if (true == leftFillFlag) // left fill
			{
				sbuf.append(formatString);
			} else {
				sbuf.insert(0, formatString);
			}
			String returnString = sbuf.toString();
			sbuf = null;
			return returnString;
		}
	}

	/**
	 * Method Format string
	 * 
	 * @param The
	 *            string to be format.
	 * 
	 */
	public static String fillZero(String formatString, int length) {
		return fillString(formatString, length, '0', true);
	}

	public static String formatAmount(String amount, boolean separator) {
		if (amount == null) {
			amount = "";
		}
		if (amount.length() < 3) {
			amount = fillZero(amount, 3);
		}
		StringBuffer s = new StringBuffer();
		int strLen = amount.length();
		for (int i = 1; i <= strLen; i++) {
			s.insert(0, amount.charAt(strLen - i));
			if (i == 2)
				s.insert(0, '.');
			if (i > 3 && ((i % 3) == 0)) {
				if (separator) {
					s.insert(1, ',');
				}
			}
		}
		return s.toString();
	}

	/**
	 * prepare long value used as amount for display (implicit 2 decimals)
	 * 
	 * @param amount
	 *            value
	 * @return formated field
	 * @exception RuntimeException
	 */
	public static String formatAmount(long amount) {
		return formatAmount("" + amount, false);
	}

	/**
	 * 随机一个订单号 以时间+7位随机数
	 * 
	 * @param order
	 * @return
	 */
	public static String createOrderNo() {
		return getFormatCurTime() + createRandomStr(7);
	}

	/***
	 * 从SharedPreferences中取出数据
	 * 
	 * @param context
	 * @param key
	 */
	public static int getSharedInt(Context context, String key) {
		SharedPreferences preferences = context.getSharedPreferences("mobile_pos3", Context.MODE_PRIVATE);
		return preferences.getInt(key, 0);
	}

	/***
	 * 保存数据到SharedPreferences中
	 * 
	 * @param context
	 * @param key
	 * @param param
	 */
	public static void setSharedInt(Context context, String key, int param) {
		SharedPreferences preferences = context.getSharedPreferences("mobile_pos3", Context.MODE_PRIVATE);
		preferences.edit().putInt(key, param).commit();
	}

	public static int getIndexNo(Context context) {
		int reqNo = getSharedInt(context, "key_index");
		setSharedInt(context, "key_index", reqNo + 1);
		java.text.DecimalFormat format = new java.text.DecimalFormat("0");
		int nextReqNo = reqNo + 1;// format.format(reqNo + 1);
		return nextReqNo;
	}
	
	
//	public static void main(String[] args) {
//		Date t = getDateFromDate(new Date(), -7);
//		System.out.println(getDateFormate(t));
//	}

	public static String getTerminalNo(String data){
		String str = "";//android.os.Build.SERIAL;
		int len = data.length();
		if (data.length() > 8){
			str = data.substring(len-8, len);
		}else{
			str = fillZero(data, 8);
		}
		return str;
	}


	/**
	 * 带小数点的字串 转整形（小数点2位）
	 *
	 * @param string
	 * @return
	 */
	public static int stringToInt(String string) {
		if (isBlank(string)){
			return 0;
		}
		String str = string.substring(0, string.indexOf("."));
		String str2 = string.substring(string.indexOf(".") + 1);
		if (str2.length() == 1) {
			str2 += "0";
		}
		int intgeo = Integer.parseInt(str + str2);
		return intgeo;
	}




}
