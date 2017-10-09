package com.tool.utils.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeUtils {

	public static String toURLEncoded(String paramString, String chartName) {
		if (paramString == null || paramString.equals("")) {
			LogUtils.e("toURLEncoded error:" + paramString);
			return "";
		}

		try {
			String str = new String(paramString.getBytes(), "UTF-8");
			str = URLEncoder.encode(str, chartName);
			return str;
		} catch (Exception localException) {
			LogUtils.e("toURLEncoded error:" + paramString, localException.toString());
		}

		return "";
	}

	public static String toURLDecoded(String paramString, String chartName) {
		if (paramString == null || paramString.equals("")) {
			LogUtils.e("toURLDecoded error:" + paramString);
			return "";
		}

		try {
			String str = new String(paramString.getBytes(), "UTF-8");
			str = URLDecoder.decode(str, chartName);
			return str;
		} catch (Exception localException) {
			LogUtils.e("toURLDecoded error:" + paramString, localException.toString());
		}

		return "";
	}
}
