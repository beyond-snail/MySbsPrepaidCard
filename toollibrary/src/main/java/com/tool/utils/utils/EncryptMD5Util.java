package com.tool.utils.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptMD5Util {
	 // MD5加密
    public static String makeMD5(String password) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
    
 // MD5加密，32位
 	public static String MD5(String str) {
 		MessageDigest md5 = null;
 		StringBuffer hexValue = new StringBuffer();
 		try {
 			md5 = MessageDigest.getInstance("MD5");
 		} catch (Exception e) {
 			e.printStackTrace();
 			return "";
 		}
 		byte[] byteArray;
 		try {
 			byteArray = str.getBytes("UTF-8");
 			byte[] md5Bytes = md5.digest(byteArray);
 			
 			for (int i = 0; i < md5Bytes.length; i++) {
 				int val = ((int) md5Bytes[i]) & 0xff;
 				if (val < 16) {
 					hexValue.append("0");
 				}
 				hexValue.append(Integer.toHexString(val));
 			}
 		} catch (UnsupportedEncodingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
 		return hexValue.toString();
 	}
}
