package com.mysbsprepaidcard.zfsbs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.adapter.AdapterOilCardMeal;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.CardId;
import com.mysbsprepaidcard.zfsbs.model.RechargeAmount;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.msrcard.MsrCard;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;
import com.tool.utils.view.MyGridView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;


public class RechargeActivity extends BaseActivity implements OnClickListener {

    public static final int REQUEST_CAPTURE = 0;

    private RechargeAmount vo;
    private List<RechargeAmount> list = new ArrayList<RechargeAmount>();

    private AdapterOilCardMeal adapter;
    private EditText etCardNo;
    private EditText etOperator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_recharge_amount2);
        AppManager.getAppManager().addActivity(this);
        initTitle("充值");
        initView();


    }


    private MsrCard.TrackData listener = new MsrCard.TrackData() {
        @Override
        public void onSuccess(String track2Data) {
//            String cardNumber = track2Data.substring(0, track2Data.indexOf("="));
            etCardNo.setText(track2Data);
            etCardNo.setSelection(etCardNo.length());
        }

        @Override
        public void onFail() {
            MsrCard.getMsrCard(mContext).closeMsrCard();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                        MsrCard.getMsrCard(mContext).openMsrCard(listener);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MsrCard.getMsrCard(mContext).closeMsrCard();
    }


    private void initView() {

        etCardNo = (EditText) findViewById(R.id.id_phoneNo);
        etOperator = (EditText) findViewById(R.id.id_tgy);

        button(R.id.id_btn_recharge).setOnClickListener(this);
        imageView(R.id.id_scan).setOnClickListener(this);

        MyGridView gridview = (MyGridView) findViewById(R.id.gridview);
        adapter = new AdapterOilCardMeal(list, mContext);
        gridview.setAdapter(adapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vo = list.get(position);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setIsdefault(0);
                }
                vo.setIsdefault(1);
                adapter.notifyDataSetChanged();


            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_recharge:
                if (StringUtils.isEmpty(etCardNo.getText().toString())) {
                    ToastUtils.CustomShow(mContext, "卡号或手机号不为空");
                    return;
                }

                loadRechargeSureData();
                break;
            case R.id.id_scan:
                CommonFunc.startResultAction(RechargeActivity.this, CaptureActivity.class, null, REQUEST_CAPTURE);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CAPTURE:
                // 处理扫描结果（在界面上显示）
                String phoneNo = data.getStringExtra(CaptureActivity.SCAN_RESULT);
                etCardNo.setText(phoneNo);
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    private void loadData() {
        int sid = MyApplication.getInstance().getLoginData().getSid();
        sbsAction.recharge(mContext, sid, new ActionCallbackListener<List<RechargeAmount>>() {
            @Override
            public void onSuccess(List<RechargeAmount> data) {
                if (data.size() <= 0) {
                    ToastUtils.CustomShow(mContext, "获取充值金额失败");
                    return;
                }
                list.clear();
                list.addAll(data);
                list.get(0).setIsdefault(1);
                adapter.notifyDataSetChanged();
                vo = list.get(0);
                MsrCard.getMsrCard(mContext).openMsrCard(listener);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(mContext, message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {

            }
        });


    }







    private void loadRechargeSureData(){
        int sid = MyApplication.getInstance().getLoginData().getSid();
        String cardNo = etCardNo.getText().toString().trim();
        sbsAction.rechargeSure(mContext, sid, cardNo, new ActionCallbackListener<CardId>() {
            @Override
            public void onSuccess(CardId data) {
                MsrCard.getMsrCard(mContext).closeMsrCard();
                Bundle bundle = new Bundle();
                bundle.putSerializable("RechargeAmount", vo);
                bundle.putString("cardNo", etCardNo.getText().toString().trim());
                bundle.putString("tgy", etOperator.getText().toString().trim());
                bundle.putString("card_id", data.getCard_id());

                startActivity(new Intent(mContext, ZfPayRechargeActivity.class).putExtra("data", bundle));

                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(mContext, message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {

            }
        });
    }


}
