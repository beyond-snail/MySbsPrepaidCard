package com.tool.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tool.utils.utils.ToastUtils;
import com.toollibrary.R;


public class MemberNoDialog1 extends Dialog implements View.OnClickListener {

	private int layoutRes;// 布局文件
	private Context context;
	private OnClickInterface listener;

	private static EditText etInputNo;
	private static ImageView ivScan;
	private static Button btnLeft;
	private static Button btnRight;
	private static Button btnMid;


	public interface OnClickInterface {
		abstract void onClickLeft(String result);
		abstract void onClickRight();
		abstract void onResultScanContent();
		abstract void onClickMid();
	}

	public MemberNoDialog1(Context context) {
		super(context);
		this.context = context;
	}



	/**
	 * 自定义布局的构造方法
	 *
	 * @param context
	 * @param resLayout
	 */
	public MemberNoDialog1(Context context, int resLayout, OnClickInterface listener) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
		this.listener = listener;
	}

	/**
	 * 自定义主题及布局的构造方法
	 *
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public MemberNoDialog1(Context context, int theme, int resLayout, OnClickInterface listener) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
		this.listener = listener;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(layoutRes);
		
		getWindow().setGravity(Gravity.CENTER); //显示在底部

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth()-20; //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);
        
        initView();
        addListener();
	}

	private void initView() {
		etInputNo = (EditText) findViewById(R.id.id_phoneNo);
		ivScan = (ImageView) findViewById(R.id.id_scan);
		btnLeft = (Button) findViewById(R.id.id_left);
		btnRight = (Button) findViewById(R.id.id_right);
		btnMid = (Button) findViewById(R.id.id_mid);
	}

	private void addListener() {
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		ivScan.setOnClickListener(this);
		btnMid.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.id_left) {
			enterNext();

		} else if (i == R.id.id_right) {
			this.dismiss();
			listener.onClickRight();

		} else if (i == R.id.id_scan) {
			listener.onResultScanContent();

		} else if (i == R.id.id_mid){
			listener.onClickMid();
		}
	}
	
	
	private void enterNext() {
		
		if (etInputNo.getText().toString().length() > 0){
			this.dismiss();
			listener.onClickLeft(etInputNo.getText().toString());
		}else{
			ToastUtils.CustomShow(context, "请输入手机号");
			getFocus();
		}
	}



	public static void setMemberNo(String memberNo){
		etInputNo.setText(memberNo);
		etInputNo.setSelection(memberNo.length());
	}

	public static void setBtnRightText(String text){
		btnRight.setText(text);
	}

	public static void setEtInputNoText(String text){
		etInputNo.setHint(text);
		etInputNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
	}



	/**
	 * 是否隐藏扫码框
	 * @param isHide
     */
	public static void isHideScanIcon(boolean isHide){
		if (isHide) {
			ivScan.setVisibility(View.GONE);
		}else {
			ivScan.setVisibility(View.VISIBLE);
		}
	}
	
	
	
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		
		if (event.getKeyCode()== KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
			enterNext();
			return true;
		}
		return super.dispatchKeyEvent(event);
	}
	
	
	private void getFocus(){
		etInputNo.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				etInputNo.setFocusable(true);
				etInputNo.setFocusableInTouchMode(true);
				etInputNo.requestFocus();
				etInputNo.findFocus();
			}
		}, 500);
	}
	

}
