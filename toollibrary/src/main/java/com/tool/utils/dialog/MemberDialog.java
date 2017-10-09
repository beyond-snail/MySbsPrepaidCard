package com.tool.utils.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.toollibrary.R;


public class MemberDialog extends Dialog implements View.OnClickListener {

	private int layoutRes;// 布局文件
	private Context context;
	private onClickLeftListener leftLister;
	private onClickRightListener rightLister;
	
	private EditText etInputName;
	private Button btnLeft;
	private Button btnRight;

	public MemberDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	/**
	 * 提问框 Listener
	 */
	public interface onClickLeftListener {
		abstract void onClickLeft(MemberDialog dialog, String result);
	}

	/**
	 * 提问框 Listener
	 */
	public interface onClickRightListener {
		abstract void onClickRight(MemberDialog dialog);
	}

	/**
	 * 自定义布局的构造方法
	 * 
	 * @param context
	 * @param resLayout
	 */
	public MemberDialog(Context context, int resLayout, onClickLeftListener leftLister, onClickRightListener rightListener) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
		this.leftLister = leftLister;
		this.rightLister = rightListener;
	}

	/**
	 * 自定义主题及布局的构造方法
	 * 
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public MemberDialog(Context context, int theme, int resLayout, onClickLeftListener leftLister, onClickRightListener rightLister) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
		this.leftLister = leftLister;
		this.rightLister = rightLister;
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
		etInputName = (EditText) findViewById(R.id.id_inputName);
		btnLeft = (Button) findViewById(R.id.id_left);
		btnRight = (Button) findViewById(R.id.id_right);
	}

	private void addListener() {
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		if (i == R.id.id_left) {
			if (etInputName.getText().toString().length() > 0) {
				leftLister.onClickLeft(this, etInputName.getText().toString());
			}

		} else if (i == R.id.id_right) {
			rightLister.onClickRight(this);

		} else {
		}
	}

}
