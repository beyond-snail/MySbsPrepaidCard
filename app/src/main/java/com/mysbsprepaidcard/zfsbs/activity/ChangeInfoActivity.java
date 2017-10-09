package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.ToastUtils;


public class ChangeInfoActivity extends BaseActivity implements OnClickListener {

	private Button mChangeInfo;

	private EditText edUnMemberAuth;
	private EditText edMemberAuth;
	private EditText edUnMemberMid;
	private EditText edUnMemberTid;
	private EditText edMemberMid;
	private EditText edMemberTid;
	private EditText edIp;
	private EditText edPort;
	private EditText edTpdu;

	private EditText edCode;
	private EditText edVendorId;
	private EditText edVendorKey;
	private EditText edSubject;
	private EditText edDescription;

	private EditText editMerchantName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_changeinfo);
//		AppManager.getAppManager().addActivity(this);
		initView();
		addEventListener();
	}

	void initView() {

		mChangeInfo = (Button) findViewById(R.id.change);
		editMerchantName = (EditText) findViewById(R.id.id_menchant_name);

		edUnMemberAuth = (EditText) findViewById(R.id.id_unmember_auth);
		edMemberAuth = (EditText) findViewById(R.id.id_member_auth);
		edUnMemberMid = (EditText) findViewById(R.id.id_unmember_mid);
		edMemberMid = (EditText) findViewById(R.id.id_member_mid);
		edUnMemberTid = (EditText) findViewById(R.id.id_unmember_tid);
		edMemberTid = (EditText) findViewById(R.id.id_member_tid);
		edIp = (EditText) findViewById(R.id.id_ip);
		edPort = (EditText) findViewById(R.id.id_port);
		edTpdu = (EditText) findViewById(R.id.id_tpdu);

		edCode = (EditText) findViewById(R.id.id_activate_code);
		edVendorId = (EditText) findViewById(R.id.id_vendorid);
		edVendorKey = (EditText) findViewById(R.id.id_vendorkey);

		edSubject = (EditText) findViewById(R.id.id_subject);
		edDescription = (EditText) findViewById(R.id.id_description);

		edUnMemberAuth.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_AUTH1, Constants.DEFAULT_HS_AUTH1));
		edMemberAuth.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_AUTH2, Constants.DEFAULT_HS_AUTH2));
		edUnMemberMid.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_MID1, Constants.DEFAULT_HS_MID1));
		edMemberMid.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_MID2, Constants.DEFAULT_HS_MID2));
		edUnMemberTid.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_TID1, Constants.DEFAULT_HS_TID1));
		edMemberTid.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_TID2, Constants.DEFAULT_HS_TID2));
		edIp.setText((String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_IP, Constants.DEFAULT_HS_IP));
		edPort.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_PORT, Constants.DEFAULT_HS_PORT));
		edTpdu.setText(
				(String) SPUtils.get(ChangeInfoActivity.this, Constants.HS_TPDU, Constants.DEFAULT_HS_TPDU));

		edCode.setText((String) SPUtils.get(ChangeInfoActivity.this, Constants.SQB_ACTIVATE_CODE,
				Constants.DEFALULT_SQB_ACTIVATE_CODE));
		edVendorId.setText((String) SPUtils.get(ChangeInfoActivity.this, Constants.SQB_VENDOR_ID,
				Constants.DEFALULT_SQB_VENDOR_ID));
	
		edVendorKey.setText((String) SPUtils.get(ChangeInfoActivity.this, Constants.SQB_VENDOR_KEY,
				Constants.DEFALULT_SQB_VENDOR_KEY));

		edSubject.setText((String) SPUtils.get(ChangeInfoActivity.this, Constants.SQB_SUBJECT,
				Constants.DEFALULT_SQB_SUBJECT));
		edDescription.setText((String) SPUtils.get(ChangeInfoActivity.this, Constants.SQB_DESCRIPTION,
				Constants.DEFALULT_SQB_DESCRIPTION));

		editMerchantName.setText((String) SPUtils.get(ChangeInfoActivity.this, Constants.MERCHANT_NAME, Constants.DEFALULT_MERCHANT_NAME));

	}

	void addEventListener() {
		mChangeInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change:

			SPUtils.put(ChangeInfoActivity.this, Constants.MERCHANT_NAME, editMerchantName.getText().toString());

			SPUtils.put(ChangeInfoActivity.this, Constants.HS_AUTH1, edUnMemberAuth.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_AUTH2, edMemberAuth.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_MID1, edUnMemberMid.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_MID2, edMemberMid.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_TID1, edUnMemberTid.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_TID2, edMemberTid.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_IP, edIp.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_PORT, edPort.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.HS_TPDU, edTpdu.getText().toString());

			// 判断下 激活码 是否 更改
			if (!SPUtils.get(ChangeInfoActivity.this, Constants.SQB_ACTIVATE_CODE, Constants.DEFALULT_SQB_ACTIVATE_CODE)
					.equals(edCode.getText().toString())) {
				SPUtils.put(ChangeInfoActivity.this, Constants.ACTIVATE_FLAG, false);
			}

			SPUtils.put(ChangeInfoActivity.this, Constants.SQB_ACTIVATE_CODE, edCode.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.SQB_VENDOR_ID, edVendorId.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.SQB_VENDOR_KEY, edVendorKey.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.SQB_SUBJECT, edSubject.getText().toString());
			SPUtils.put(ChangeInfoActivity.this, Constants.SQB_DESCRIPTION, edDescription.getText().toString());


//			setPayParam();



			ToastUtils.CustomShow(this, "信息保存成功");

			finish();

			break;

		default:
			break;
		}
	}

//	/**
//	 * 设置参数
//	 */
//	private void setPayParam(){
//		// 设置参数
//		int keyIndex = MyApplication.getInstance().getLoginData().getKeyIndex();
//		String mid = MyApplication.getInstance().getLoginData().getMerchantNo();
//		String tid = MyApplication.getInstance().getLoginData().getTerminalNo();
//		PayCommon.setParams(this, keyIndex, mid, tid, new PayCommon.ComTransResult<ComTransInfo>() {
//			@Override
//			public void success(ComTransInfo transInfo) {
//
//			}
//
//			@Override
//			public void failed(String error) {
//
//			}
//		});
//	}

}
