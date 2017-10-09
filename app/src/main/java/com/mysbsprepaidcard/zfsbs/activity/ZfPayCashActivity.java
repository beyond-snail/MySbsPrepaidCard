package com.mysbsprepaidcard.zfsbs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.mysbsprepaidcard.R;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import java.math.BigDecimal;


public class ZfPayCashActivity extends BaseActivity implements OnClickListener {

	private TextView tKey1;
	private TextView tKey2;
	private TextView tKey3;
	private TextView tKey4;
	private TextView tKey5;
	private TextView tKey6;
	private TextView tKey7;
	private TextView tKey8;
	private TextView tKey9;
	private TextView tKey0;
	private TextView tkey00;

	private TextView tKeyblack;
	private TextView tKeyCaculate;

	private TextView tTotalAmount;
	private TextView tReceivedAmount;
	private TextView tOddChange;

	private int amount; //原始金额
	private int receiveAmount; // 实收金额

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_pay_cash);
		AppManager.getAppManager().addActivity(this);
		initTitle("现金收款");
		initView();
		addLinstener();
	}

	private void initView() {
		tkey00 = (TextView) findViewById(R.id.id_key_00);
		tKey0 = (TextView) findViewById(R.id.id_key_0);
		tKey1 = (TextView) findViewById(R.id.id_key_1);
		tKey2 = (TextView) findViewById(R.id.id_key_2);
		tKey3 = (TextView) findViewById(R.id.id_key_3);
		tKey4 = (TextView) findViewById(R.id.id_key_4);
		tKey5 = (TextView) findViewById(R.id.id_key_5);
		tKey6 = (TextView) findViewById(R.id.id_key_6);
		tKey7 = (TextView) findViewById(R.id.id_key_7);
		tKey8 = (TextView) findViewById(R.id.id_key_8);
		tKey9 = (TextView) findViewById(R.id.id_key_9);
		tKeyblack = (TextView) findViewById(R.id.id_key_back);
		tKeyCaculate = (TextView) findViewById(R.id.id_key_caculate);

		tTotalAmount = (TextView) findViewById(R.id.id_totalAmount);
		tReceivedAmount = (TextView) findViewById(R.id.id_receivedAmount);
		tOddChange = (TextView) findViewById(R.id.id_oddChange);

		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		if (!StringUtils.isEmpty(bundle.getString("amount"))) {
			BigDecimal big = new BigDecimal(bundle.getString("amount"));
			amount = big.multiply(new BigDecimal(100)).intValue();
		}
//		amount = (int) (Double.parseDouble(bundle.getString("amount")) * 100);

		tTotalAmount.setText(StringUtils.formatIntMoney(amount));
		setTextAmount(0);
	}

	private void addLinstener() {
		tkey00.setOnClickListener(this);
		tKey0.setOnClickListener(this);
		tKey1.setOnClickListener(this);
		tKey2.setOnClickListener(this);
		tKey3.setOnClickListener(this);
		tKey4.setOnClickListener(this);
		tKey5.setOnClickListener(this);
		tKey6.setOnClickListener(this);
		tKey7.setOnClickListener(this);
		tKey8.setOnClickListener(this);
		tKey9.setOnClickListener(this);
		tKeyblack.setOnClickListener(this);
		tKeyCaculate.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_key_0:
			setTextAmount(0);
			break;
		case R.id.id_key_00:
			setTextDouble();
			break;
		case R.id.id_key_1:
			setTextAmount(1);
			break;
		case R.id.id_key_2:
			setTextAmount(2);
			break;
		case R.id.id_key_3:
			setTextAmount(3);
			break;
		case R.id.id_key_4:
			setTextAmount(4);
			break;
		case R.id.id_key_5:
			setTextAmount(5);
			break;
		case R.id.id_key_6:
			setTextAmount(6);
			break;
		case R.id.id_key_7:
			setTextAmount(7);
			break;
		case R.id.id_key_8:
			setTextAmount(8);
			break;
		case R.id.id_key_9:
			setTextAmount(9);
			break;
		case R.id.id_key_back:
			setAmountToKeyBack();
			break;
		case R.id.id_key_caculate:
			Caculate();
			break;

		default:
			break;
		}
	}

	private void Caculate() {
		if (receiveAmount-amount >= 0){
			Intent intent = getIntent();
			Bundle b = new Bundle();
			b.putInt("amount", amount);
			b.putInt("receiveAmount", receiveAmount);
			b.putInt("oddChangeAmout", receiveAmount-amount);
			intent.putExtra("bundle", b);
			setResult(Activity.RESULT_OK, intent);
			finish();
		}else{
			ToastUtils.CustomShow(this, "请输入金额");
		}
	}

	private void setTextAmount(int digital) {
		if (receiveAmount < 100000000) {
			receiveAmount = receiveAmount * 10 + digital;
			tReceivedAmount.setText(StringUtils.formatAmount(receiveAmount));

			if (receiveAmount > 0) {
				if (receiveAmount - amount > 0) {
					tOddChange.setText(StringUtils.formatIntMoney(receiveAmount - amount) + "");
				} else {
					tOddChange.setText(StringUtils.formatIntMoney(0) + "");
				}
			} else {
				tOddChange.setText(StringUtils.formatIntMoney(0) + "");
			}

		}
	}
	
	private void setTextDouble(){
		if (receiveAmount < 100000000){
			receiveAmount = receiveAmount * 100;
			if (receiveAmount > 100000000){
				receiveAmount = receiveAmount/100;
			}
			tReceivedAmount.setText(StringUtils.formatAmount(receiveAmount));
			
			if (receiveAmount > 0) {
				if (receiveAmount - amount > 0) {
					tOddChange.setText(StringUtils.formatIntMoney(receiveAmount - amount) + "");
				} else {
					tOddChange.setText(StringUtils.formatIntMoney(0) + "");
				}
			} else {
				tOddChange.setText(StringUtils.formatIntMoney(0) + "");
			}
		}
	}

	private void setAmountToKeyBack() {
		receiveAmount = receiveAmount / 10;
		tReceivedAmount.setText(StringUtils.formatAmount(receiveAmount));

		if (receiveAmount > 0) {
			if (receiveAmount - amount > 0) {
				tOddChange.setText(StringUtils.formatIntMoney(receiveAmount - amount) + "");
			} else {
				tOddChange.setText(StringUtils.formatIntMoney(0) + "");
			}
		} else {
			tOddChange.setText(StringUtils.formatIntMoney(0) + "");
		}
	}

}
