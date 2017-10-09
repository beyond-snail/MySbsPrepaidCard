package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;


public class MasterChangePass extends BaseActivity implements OnClickListener{
	
	private EditText etOldPass;
	private EditText etNewPass;
	private Button btnSure;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_master_pass);
		AppManager.getAppManager().addActivity(this);
		initTitle("修改主管理员密码");
		initView();
	}

	private void initView() {
		etOldPass = (EditText) findViewById(R.id.id_master_password_old);
		etNewPass = (EditText) findViewById(R.id.id_master_password_new);
		btnSure = (Button) findViewById(R.id.id_master_password_sure);
		btnSure.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_master_password_sure:
			isCheckMasterPass();
			break;

		default:
			break;
		}
	}

	private void isCheckMasterPass() {
		if (StringUtils.isEmpty(etOldPass.getText().toString())){
			ToastUtils.CustomShow(this, "请输入原密码");
			return;
		}
		if (StringUtils.isEmpty(etNewPass.getText().toString())){
			ToastUtils.CustomShow(this, "请输入新密码");
			return;
		}
		if (!StringUtils.isEquals(etOldPass.getText().toString(), etNewPass.getText().toString())){
			String old = (String) SPUtils.get(this, Constants.MASTER_PASS, Constants.DEFAULT_MASTER_PASS);
			if (StringUtils.isEquals(etOldPass.getText().toString(), old)){
				SPUtils.put(this, Constants.MASTER_PASS, etNewPass.getText().toString());
				ToastUtils.CustomShow(this, "密码设置成功");
				finish();
			}else{
				ToastUtils.CustomShow(this, "原密码错误");
			}
		}else{
			ToastUtils.CustomShow(this, "原密码与新密码一致");
		}
	}
}
