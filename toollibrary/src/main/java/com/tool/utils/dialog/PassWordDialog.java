package com.tool.utils.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.tool.utils.view.PasswordInputView;
import com.toollibrary.R;


public class PassWordDialog extends Dialog {

	private int layoutRes;// 布局文件
	private Context context;
	private OnResultInterface resultListener;
	PasswordInputView passwordInputView;


	public interface OnResultInterface {
		abstract void onResult(String data);
	}

	public PassWordDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	
	
	

	/**
	 * 自定义布局的构造方法
	 * 
	 * @param context
	 * @param resLayout
	 */
	public PassWordDialog(Context context, int resLayout, OnResultInterface listener) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
		this.resultListener = listener;
		
	}

	/**
	 * 自定义主题及布局的构造方法
	 * 
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public PassWordDialog(Context context, int theme, int resLayout, OnResultInterface listener) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
		this.resultListener = listener;
		
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
	}

	private void initView() {
		passwordInputView = (PasswordInputView) findViewById(R.id.passwordInputView);
	}


	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		
		if (event.getKeyCode()== KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
			Editable data = passwordInputView.getText();
			if (data.length() == 6){
				this.dismiss();
				resultListener.onResult(data.toString());
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

}
