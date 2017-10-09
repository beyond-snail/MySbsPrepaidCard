package com.tool.utils.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.toollibrary.R;


/**
 * Toast统一管理
 * Created by wucongpeng on 16/6/20.
 */
public class ToastUtils {

    private ToastUtils()
    {
        /** cannot be instantiated**/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;
    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }
    
    /**
     * 自定义显示Toast
     * @param context
     * @param message
     * 
     */
    @SuppressLint("InflateParams")
	public static void CustomShow(Context context, CharSequence message){
    	if (isShow){
    		LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		View view = li.inflate(R.layout.activity_toast, null);
    		TextView tv = (TextView) view.findViewById(R.id.id_toast_tv);
    		tv.setText(message);
    		tv.setTextSize(20);
    		Toast toast = new Toast(context);
    		toast.setView(view);
    		toast.setDuration(Toast.LENGTH_SHORT);
    		toast.show();
    	}
    }

    /**
     * 自定义显示Toast
     * @param context
     * @param message
     *
     */
    @SuppressLint("InflateParams")
    public static void CustomShowLong(Context context, CharSequence message){
        if (isShow){
            LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = li.inflate(R.layout.activity_toast, null);
            TextView tv = (TextView) view.findViewById(R.id.id_toast_tv);
            tv.setText(message);
            tv.setTextSize(20);
            Toast toast = new Toast(context);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
