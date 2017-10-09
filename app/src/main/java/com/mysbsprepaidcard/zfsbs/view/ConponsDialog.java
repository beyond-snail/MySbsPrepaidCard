package com.mysbsprepaidcard.zfsbs.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.adapter.MyCouponsAdapter;
import com.mysbsprepaidcard.zfsbs.model.Coupons;

import java.util.List;


public class ConponsDialog extends Dialog implements View.OnClickListener {

	private int layoutRes;// 布局文件
	private Context context;
	private List<Coupons> coupons;
	private ListView lvShowCoupons;
	private ImageButton imgReturn;

	public ConponsDialog(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * 自定义布局的构造方法
	 * 
	 * @param context
	 * @param resLayout
	 */
	public ConponsDialog(Context context, int resLayout, List<Coupons> data) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
		this.coupons = data;
		
	}

	/**
	 * 自定义主题及布局的构造方法
	 * 
	 * @param context
	 * @param theme
	 * @param resLayout
	 */
	public ConponsDialog(Context context, int theme, int resLayout) {
		super(context, R.style.ConponsDialog);
		this.context = context;
		this.layoutRes = resLayout;
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
        
        if (coupons != null){
			lvShowCoupons = (ListView) findViewById(R.id.id_show_coupons);
			MyCouponsAdapter adapter = new MyCouponsAdapter(context, coupons, R.layout.activity_coupons_item);
			lvShowCoupons.setAdapter(adapter);
        }

		initView();
	}

	private void initView() {
		imgReturn = (ImageButton) findViewById(R.id.id_return);
		imgReturn.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.id_return:
				this.dismiss();
				break;
		}
	}
}
