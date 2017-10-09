package com.mysbsprepaidcard.zfsbs.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.ApiResponse;
import com.mysbsprepaidcard.zfsbs.model.VipCardNo;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.msrcard.MsrCard;
import com.tool.utils.utils.AlertUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

public class OpenCardActivity extends BaseActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etPhone;
    private EditText etCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_open_card);
        AppManager.getAppManager().addActivity(this);

        initTitle("开卡/绑卡");

        initView();

        MsrCard.getMsrCard(mContext).openMsrCard(listener);

    }


    private MsrCard.TrackData listener = new MsrCard.TrackData() {
        @Override
        public void onSuccess(String track2Data) {
//            if (track2Data.length() < 5){
//                return;
//            }
//            String cardNumber = track2Data.substring(0, track2Data.indexOf("="));
            etCard.setText(track2Data);
            etCard.setSelection(etCard.length());

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

    private void initView() {

        etName = editText(R.id.id_member_name);
        etName.setSelection(etName.getText().length());
        etPhone = editText(R.id.id_memberphoneNo);
        etPhone.setSelection(etPhone.getText().length());
        etCard = editText(R.id.id_memberCardNo);
        etCard.setSelection(etCard.getText().length());


        button(R.id.id_bind_card).setOnClickListener(this);
        button(R.id.id_change_card).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_bind_card:
                openRealizeCard(1);
                break;
            case R.id.id_change_card:
                openRealizeCard(2);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MsrCard.getMsrCard(mContext).closeMsrCard();
    }

    private void openRealizeCard(int action){

        int sid = MyApplication.getInstance().getLoginData().getSid();
        String memberName = etName.getText().toString().trim();
        String memberCard = etCard.getText().toString().trim();
        String memberPhone = etPhone.getText().toString().trim();

        if (StringUtils.isBlank(memberCard)){
            ToastUtils.CustomShow(mContext, "会员卡号不为空");
            return;
        }

//        if (StringUtils.isBlank(memberName)){
//            ToastUtils.CustomShow(mContext, "会员名不为空");
//            return;
//        }
//
//        if (StringUtils.isBlank(memberPhone)){
//            ToastUtils.CustomShow(mContext, "会员手机号不为空");
//            return;
//        }

        sbsAction.openCard(mContext, sid, action + "", memberName, memberPhone, memberCard, new ActionCallbackListener<ApiResponse<VipCardNo>>() {
            @Override
            public void onSuccess(ApiResponse<VipCardNo> response) {

                AlertUtils.alert2("提示", response.getMsg(), mContext, "确定", "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        onBackPressed();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        onBackPressed();
                    }
                }, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        onBackPressed();
                    }
                }, null, false, false, 1);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(mContext, message);
//                MsrCard.getMsrCard(mContext).openMsrCard(listener);
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

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {

            }
        });
    }

}
