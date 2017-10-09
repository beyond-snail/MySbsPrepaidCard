package com.tool.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * Created by wucongpeng on 16/6/20.
 */
public class SPUtils {

	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "share_data";
	private static final String HexChars = "1234567890abcdefABCDEF";

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 *
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void put(Context context, String key, Object object) {

		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}

		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 自定义filename 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 
	 * @param context
	 * @param filename
	 * @param key
	 * @param object
	 */
	public static void put(Context context, String filename, String key, Object object) {

		SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}

		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 *
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object get(Context context, String key, Object defaultObject) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}

		return null;
	}

	/**
	 * 自定义filename 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 
	 * @param context
	 * @param filename
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object get(Context context, String filename, String key, Object defaultObject) {
		SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);

		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}

		return null;
	}

	/**
	 * 移除某个key值已经对应的值
	 * 
	 * @param context
	 * @param key
	 */
	public static void remove(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 自定义filename 移除某个key值已经对应的值
	 * 
	 * @param filename
	 * @param context
	 * @param key
	 */
	public static void remove(String filename, Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 清除所有数据
	 * 
	 * @param context
	 */
	public static void clear(Context context) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 自定义filename 清除所有数据
	 * 
	 * @param filename
	 * @param context
	 */
	public static void clear(String filename, Context context) {
		SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 查询某个key是否已经存在
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean contains(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * 自定义filename 查询某个key是否已经存在
	 * 
	 * @param filename
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean contains(String filename, Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * 返回所有的键值对
	 *
	 * @param context
	 * @return
	 */
	public static Map<String, ?> getAll(Context context) {
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		return sp.getAll();
	}

	/**
	 * 自定义filename 返回所有的键值对
	 * 
	 * @param filename
	 * @param context
	 * @return
	 */
	public static Map<String, ?> getAll(String filename, Context context) {
		SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
		return sp.getAll();
	}

	/**
	 * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
	 *
	 * @author zhy
	 *
	 */
	private static class SharedPreferencesCompat {
		private static final Method sApplyMethod = findApplyMethod();

		/**
		 * 反射查找apply的方法
		 *
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static Method findApplyMethod() {
			try {
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e) {
			}

			return null;
		}

		/**
		 * 如果找到则使用apply执行，否则使用commit
		 *
		 * @param editor
		 */
		public static void apply(SharedPreferences.Editor editor) {
			try {
				if (sApplyMethod != null) {
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
			editor.commit();
		}
	}

	/**
	 * desc:保存对象
	 * 
	 * @param context
	 * @param key
	 * @param obj
	 *            要保存的对象，只能保存实现了serializable的对象 modified:
	 */
	public static void saveObject(Context context, String filename, String key, Object obj) {
		try {
			// 保存对象
			SharedPreferences.Editor sharedata = context.getSharedPreferences(filename, 0).edit();
			// 先将序列化结果写到byte缓存中，其实就分配一个内存空间
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bos);
			// 将对象序列化写入byte缓存
			os.writeObject(obj);
			// 将序列化的数据转为16进制保存
			String bytesToHexString = bytesToHexString(bos.toByteArray());
			Log.e("SPUtils", bytesToHexString);
			// 保存该16进制数组
			sharedata.putString(key, bytesToHexString);
			sharedata.commit();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("SPUtils", "保存obj失败");
		}
	}

	/**
	 * desc:将数组转为16进制
	 * 
	 * @param bArray
	 * @return modified:
	 */
	public static String bytesToHexString(byte[] bArray) {
		if (bArray == null) {
			return null;
		}
		if (bArray.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * desc:获取保存的Object对象
	 * 
	 * @param context
	 * @param key
	 * @return modified:
	 */
	public static Object readObject(Context context, String filename, String key) {
		try {
			SharedPreferences sharedata = context.getSharedPreferences(filename, 0);
			if (sharedata.contains(key)) {
				String string = sharedata.getString(key, "");
				if (TextUtils.isEmpty(string)) {
					return null;
				} else {
					// 将16进制的数据转为数组，准备反序列化
					byte[] stringToBytes = hexString2bytes(string);
					ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
					ObjectInputStream is = new ObjectInputStream(bis);
					// 返回反序列化得到的对象
					Object readObject = is.readObject();
					return readObject;
				}
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 所有异常返回null
		return null;

	}

	/**
	 * @param s
	 *            source string (with Hex representation)
	 * @return byte array
	 */
	public static byte[] hexString2bytes(String s) {
		if (null == s)
			return null;

		s = trimSpace(s);

		if (false == isHexChar(s, false))
			return null;

		return hex2byte(s, 0, s.length() >> 1);
	}
	
	/**
	 * Method trim space
	 * 
	 * @param The
	 *            string to be format.
	 * 
	 */
	public static String trimSpace(String oldString) {
		if (null == oldString)
			return null;
		if (0 == oldString.length())
			return "";

		StringBuffer sbuf = new StringBuffer();
		int oldLen = oldString.length();
		for (int i = 0; i < oldLen; i++) {
			if (' ' != oldString.charAt(i))
				sbuf.append(oldString.charAt(i));
		}
		String returnString = sbuf.toString();
		sbuf = null;
		return returnString;
	}
	
	/**
	 * Method Check String
	 * 
	 * @param The
	 *            string to be format.
	 * 
	 */
	public static boolean isHexChar(String hexString, boolean trimSpaceFlag) {
		if (null == hexString || 0 == hexString.length())
			return false;

		if (trimSpaceFlag)
			hexString = trimSpace(hexString);

		if (hexString.length() % 2 != 0)
			return false;
		int hexLen = hexString.length();
		for (int i = 0; i < hexLen; i++) {
			if (HexChars.indexOf(hexString.charAt(i)) < 0)
				return false;
		}

		return true;
	}

	/**
	 * @param s
	 *            source string
	 * @param offset
	 *            starting offset
	 * @param len
	 *            number of bytes in destination (processes len*2)
	 * @return byte[len]
	 */
	public static byte[] hex2byte(String s, int offset, int len) {
		byte[] d = new byte[len];
		int byteLen = len * 2;
		for (int i = 0; i < byteLen; i++) {
			int shift = (i % 2 == 1) ? 0 : 4;
			d[i >> 1] |= Character.digit(s.charAt(offset + i), 16) << shift;
		}
		return d;
	}
	
	public static void save(Context context, String filename, String key, Object value){
		if (value == null){
			put(context, filename, key, "");
		}else{
			put(context, filename, key, value);
		}
	}

}
