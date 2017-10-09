package com.mysbsprepaidcard.zfsbs.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;


public class ChangePassActivity extends BaseActivity implements OnClickListener{
	private RelativeLayout btnOperator;
	private RelativeLayout btnMaster;
	private Button btnTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pass);
//		AppManager.getAppManager().addActivity(this);
		initView();
		addListener();
	}

	private void initView() {
		btnOperator = (RelativeLayout) findViewById(R.id.id_ll_operator_pass);
		btnMaster = (RelativeLayout) findViewById(R.id.id_ll_master_pass);
		btnTest = (Button) findViewById(R.id.id_test);
	}

	private void addListener() {
		btnOperator.setOnClickListener(this);
		btnMaster.setOnClickListener(this);
		btnTest.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_ll_operator_pass:
			CommonFunc.startAction(this, OperatorChangePass.class, false);
			break;
		case R.id.id_ll_master_pass:
			CommonFunc.startAction(this, MasterChangePass.class, false);
			break;
		case R.id.id_test:
//			startAction(this, Test2Activity.class, false);
//			startAction(this, MyService.class, false);
			break;
		default:
			break;
		}
	}
}
