package com.mysbsprepaidcard.zfsbs.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.model.LoginApiResponse;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.StringUtils;


public class LoginInfoActivity extends BaseActivity{
	
	private TextView tTerminalName;
	private TextView tMerchantNo;
	private TextView tTerminalNo;
	private TextView tSerial;
	private TextView tLiceseNo;
	private TextView tAccountName;
	private TextView tAccountBank;
	private TextView tAccountNo;
	private TextView tActivateCode;
	private TextView tAuthCode;
	private EditText tDomain;
	private TextView tSmMerchantName;
	private TextView tSmMerchantNo;
	private TextView tsmTerminal;
	private LinearLayout lvSmMerchantName;
	private LinearLayout lvSmMerChantNo;
	private LinearLayout lvActivateCode;
	private LinearLayout lvSmTerminal;
	private LinearLayout lvAuthoter;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_login_info);
		AppManager.getAppManager().addActivity(this);
		initTitle("收款信息");
		initView();
		initLoadData();
	}



	private void initView() {
		tTerminalName = (TextView) findViewById(R.id.id_mechant_name);
		tMerchantNo = (TextView) findViewById(R.id.id_merchant_no);
		tTerminalNo = (TextView) findViewById(R.id.id_terminalNo);
		tSerial = (TextView) findViewById(R.id.id_serial);
		tLiceseNo = (TextView) findViewById(R.id.id_licenseNo);
		tAccountName = (TextView) findViewById(R.id.id_accountName);
		tAccountBank = (TextView) findViewById(R.id.id_accountBank);
		tAccountNo = (TextView) findViewById(R.id.id_accoutNo);
		tActivateCode = (TextView) findViewById(R.id.id_activateCode);
		tAuthCode = (TextView) findViewById(R.id.id_auth_code);
		tDomain = (EditText) findViewById(R.id.id_domain);

		tSmMerchantName = (TextView) findViewById(R.id.id_smMerchantName);
		tSmMerchantNo = (TextView) findViewById(R.id.id_smMerchantNo);
		tsmTerminal = (TextView) findViewById(R.id.id_smTerminal);

		lvSmMerchantName = (LinearLayout) findViewById(R.id.lv_smMerchantName);
		lvSmMerChantNo = (LinearLayout) findViewById(R.id.id_lv_smMerchantNo);
		lvActivateCode = (LinearLayout) findViewById(R.id.lv_activateCode);
		lvSmTerminal = (LinearLayout) findViewById(R.id.lv_smTerminal);
		lvAuthoter = (LinearLayout) findViewById(R.id.lv_auth_code);
	}
	
	
	@SuppressLint("NewApi")
	private void initLoadData() {

		LoginApiResponse loginData = MyApplication.getInstance().getLoginData();
		String sm_type = loginData.getScanPayType();
		tTerminalName.setText(loginData.getTerminalName());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_TERMINAL_NAME_KEY, ""));
		tMerchantNo.setText(loginData.getMerchantNo());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_MERCHANT_NO_KEY, ""));
		tTerminalNo.setText(loginData.getTerminalNo());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_TERMINAL_NO_KEY, ""));
		tSerial.setText(StringUtils.getSerial());
		tLiceseNo.setText(loginData.getLicenseNo());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_LICENSE_NO_KEY, ""));
		tAccountName.setText(loginData.getAccountName());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_ACCOUNT_NAME_KEY, ""));
		tAccountBank.setText(loginData.getAccountBank());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_ACCOUNT_BANK_KEY, ""));
		tAccountNo.setText(loginData.getAccountNo());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_ACCOUNT_NO_KEY, ""));
		if (StringUtils.isEmpty(loginData.getOther())) {
			tAuthCode.setText(loginData.getOther());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_AUTH_CODE_KEY, ""));
			lvAuthoter.setVisibility(View.GONE);
		}else {
			tAuthCode.setText(loginData.getOther());
		}
		tDomain.setText("");
		if (!StringUtils.isEmpty(sm_type)&&StringUtils.isEquals(sm_type, Constants.SM_TYPE_SQB)) {
			lvSmMerchantName.setVisibility(View.GONE);
			lvSmMerChantNo.setVisibility(View.GONE);
			lvSmTerminal.setVisibility(View.GONE);
			lvActivateCode.setVisibility(View.VISIBLE);

			tActivateCode.setText(loginData.getActiveCode());//((String) SPUtils.get(this, filename, SbsConfig.LOGIN_ACTIVATE_CODE_KEY, ""));
		}else if (!StringUtils.isEmpty(sm_type) && StringUtils.isEquals(sm_type, Constants.SM_TYPE_FY)){

			lvSmMerchantName.setVisibility(View.VISIBLE);
			lvSmMerChantNo.setVisibility(View.VISIBLE);
			lvSmTerminal.setVisibility(View.VISIBLE);
			lvActivateCode.setVisibility(View.GONE);

			tSmMerchantName.setText(loginData.getFyMerchantName());
			tSmMerchantNo.setText(loginData.getFyMerchantNo());
			tsmTerminal.setText(loginData.getActiveCode());
		}
	}
}
