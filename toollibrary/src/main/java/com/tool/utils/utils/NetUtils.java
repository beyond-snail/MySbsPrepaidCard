package com.tool.utils.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

/**
 * 跟网络相关的工具类 Created by wucongpeng on 16/6/20.
 */
public class NetUtils {

	private NetUtils() {
		/** cannot be instantiated **/
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断网络是否连接
	 *
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (null != connectivity) {

			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm == null)
			return false;
		return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Activity activity) {
		Intent intent = new Intent("/");
		ComponentName cm = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}

	/**
	 * 获取IP地址
	 * 
	 * @return
	 */
	public static String getLocalIpAddress(Context context) {
		try {
			// for (Enumeration<NetworkInterface> en =
			// NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
			// {
			// NetworkInterface intf = en.nextElement();
			// for (Enumeration<InetAddress> enumIpAddr =
			// intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
			// {
			// InetAddress inetAddress = enumIpAddr.nextElement();
			// if (!inetAddress.isLoopbackAddress())
			// {
			// return inetAddress.getHostAddress().toString();
			// }
			// }
			// }
			// WifiManager wifiManager = (WifiManager)
			// context.getSystemService(Context.WIFI_SERVICE);
			// WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			// int i = wifiInfo.getIpAddress();
			// return int2ip(i);

			for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = (NetworkInterface) en.nextElement();
				for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
					// 这里需要注意：这里增加了一个限定条件( inetAddress instanceof Inet4Address
					// ),主要是在Android4.0高版本中可能优先得到的是IPv6的地址。参考：http://blog.csdn.net/stormwy/article/details/8832164
					if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (Exception ex) {
//			Log.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	}

	/**
	 * 将ip的整数形式转换成ip形式
	 * 
	 * @param ipInt
	 * @return
	 */
	public static String int2ip(int ipInt) {
		StringBuilder sb = new StringBuilder();
		sb.append(ipInt & 0xFF).append(".");
		sb.append((ipInt >> 8) & 0xFF).append(".");
		sb.append((ipInt >> 16) & 0xFF).append(".");
		sb.append((ipInt >> 24) & 0xFF);
		return sb.toString();
	}

	/**
	 * 判断某个服务是否正在运行的方法
	 *
	 * @param mContext
	 * @param serviceName
	 *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
	 * @return true代表正在运行，false代表服务没有正在运行
	 */
	public static boolean isServiceWork(Context mContext, String serviceName) {
		boolean isWork = false;
		ActivityManager myAM = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
		if (myList.size() <= 0) {
			return false;
		}
		for (int i = 0; i < myList.size(); i++) {
			String mName = myList.get(i).service.getClassName().toString();
			if (mName.equals(serviceName)) {
				isWork = true;
				break;
			}
		}
		return isWork;
	}
}
