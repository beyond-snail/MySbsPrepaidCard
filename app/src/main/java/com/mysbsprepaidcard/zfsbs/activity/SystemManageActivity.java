package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;


public class SystemManageActivity extends BaseActivity implements OnClickListener {

	// 更改信息按钮
	private Button mChangeBtn;

	private TextView txtMenchantName;
	private TextView txtKsnNo;

	private TextView txtUnMemberAuthCode;
	private TextView txtMemberAuthCode;
	private TextView txtUnMemberMenchantNo;
	private TextView txtUnMemberTid;
	private TextView txtMemberMenchantNO;
	private TextView txtMemberTid;
	private TextView txtIp;
	private TextView txtPort;
	private TextView txtTpdu;

	private TextView txtCode; // 激活码
	private TextView txtVendorId; // 商户id
	private TextView txtVendorKey; // 商户key
	private TextView txtSubject; // 交易简介
	private TextView txtDescription; // 交易详情

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sysmanage_basic);
//		AppManager.getAppManager().addActivity(this);
		initViewMerchInfo();

		addEventListener();
	}

	void addEventListener() {
		mChangeBtn = (Button) findViewById(R.id.changeInfoBtn);
		mChangeBtn.setOnClickListener(this);
	}

	void initViewMerchInfo() {

		txtMenchantName = (TextView) findViewById(R.id.id_mechant_name);
		txtKsnNo = (TextView) findViewById(R.id.id_ksn);
		txtUnMemberAuthCode = (TextView) findViewById(R.id.id_unmember_authcode);
		txtMemberAuthCode = (TextView) findViewById(R.id.id_member_authcode);
		txtUnMemberMenchantNo = (TextView) findViewById(R.id.id_unmember_menchant_no);
		txtUnMemberTid = (TextView) findViewById(R.id.id_unmember_tid_no);
		txtMemberMenchantNO = (TextView) findViewById(R.id.id_member_menchant_no);
		txtMemberTid = (TextView) findViewById(R.id.id_member_tid_no);
		txtIp = (TextView) findViewById(R.id.id_ip);
		txtPort = (TextView) findViewById(R.id.id_port);
		txtTpdu = (TextView) findViewById(R.id.id_tpdu);

		txtCode = (TextView) findViewById(R.id.id_activate_code);
		txtVendorId = (TextView) findViewById(R.id.id_vendor_id);
		txtVendorKey = (TextView) findViewById(R.id.id_vendor_key);

		txtSubject = (TextView) findViewById(R.id.id_subject);
		txtDescription = (TextView) findViewById(R.id.id_description);

		txtMenchantName
				.setText((String) SPUtils.get(SystemManageActivity.this, Constants.MERCHANT_NAME, Constants.DEFALULT_MERCHANT_NAME));
		txtKsnNo.setText(StringUtils.getSerial());

		txtUnMemberAuthCode.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_AUTH1, Constants.DEFAULT_HS_AUTH1));
		txtMemberAuthCode.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_AUTH2, Constants.DEFAULT_HS_AUTH2));
		txtUnMemberMenchantNo.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_MID1, Constants.DEFAULT_HS_MID1));
		txtUnMemberTid.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_TID1, Constants.DEFAULT_HS_TID1));
		txtMemberMenchantNO.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_MID2, Constants.DEFAULT_HS_MID2));
		txtMemberTid.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_TID2, Constants.DEFAULT_HS_TID2));
		txtIp.setText((String) SPUtils.get(SystemManageActivity.this, Constants.HS_IP, Constants.DEFAULT_HS_IP));
		txtPort.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_PORT, Constants.DEFAULT_HS_PORT));
		txtTpdu.setText(
				(String) SPUtils.get(SystemManageActivity.this, Constants.HS_TPDU, Constants.DEFAULT_HS_TPDU));

		txtCode.setText((String) SPUtils.get(SystemManageActivity.this, Constants.SQB_ACTIVATE_CODE,
				Constants.DEFALULT_SQB_ACTIVATE_CODE));
		txtVendorId.setText((String) SPUtils.get(SystemManageActivity.this, Constants.SQB_VENDOR_ID,
				Constants.DEFALULT_SQB_VENDOR_ID));
		txtVendorKey.setText((String) SPUtils.get(SystemManageActivity.this, Constants.SQB_VENDOR_KEY,
				Constants.DEFALULT_SQB_VENDOR_KEY));

		txtSubject.setText((String) SPUtils.get(SystemManageActivity.this, Constants.SQB_SUBJECT,
				Constants.DEFALULT_SQB_SUBJECT));
		txtDescription.setText((String) SPUtils.get(SystemManageActivity.this, Constants.SQB_DESCRIPTION,
				Constants.DEFALULT_SQB_DESCRIPTION));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.changeInfoBtn:
			CommonFunc.startAction(SystemManageActivity.this, ChangeInfoActivity.class, true);
		
			break;
		default:
			break;
		}
	}

	
}
