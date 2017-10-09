package com.mysbsprepaidcard.zfsbs.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.msrcard.MsrCard;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

public class CardChangeActivity extends BaseActivity implements View.OnClickListener {

    private EditText etCardNo;
    private EditText etOldPwd;
    private EditText etNewPwd;
    private EditText etCofimPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_card_change);
        AppManager.getAppManager().addActivity(this);
        initTitle("卡密码修改");

        initView();

    }

    private void initView() {
        etCardNo = editText(R.id.id_memberCardNo);
        etOldPwd = editText(R.id.id_old_pwd);
        etNewPwd = editText(R.id.id_new_psw);
        etCofimPwd = editText(R.id.id_comfirm_psw);

        button(R.id.id_ok).setOnClickListener(this);


        MsrCard.getMsrCard(mContext).openMsrCard(listener);
    }


    private MsrCard.TrackData listener = new MsrCard.TrackData() {
        @Override
        public void onSuccess(String track2Data) {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_ok:

                if (StringUtils.isBlank(etCardNo.getText().toString().trim())){
                    ToastUtils.CustomShow(mContext, "会员卡号为空");
                    return;
                }
                if (StringUtils.isBlank(etOldPwd.getText().toString().trim())){
                    ToastUtils.CustomShow(mContext, "原支付密码为空");
                    return;
                }
                if (StringUtils.isBlank(etNewPwd.getText().toString().trim())){
                    ToastUtils.CustomShow(mContext, "新支付密码为空");
                    return;
                }
                if (StringUtils.isBlank(etCofimPwd.getText().toString().trim())){
                    ToastUtils.CustomShow(mContext, "确认支付密码为空");
                    return;
                }

                if (!StringUtils.isEquals(etNewPwd.getText().toString().trim(), etCofimPwd.getText().toString().trim())){
                    ToastUtils.CustomShow(mContext, "两次密码不一致");
                    return;
                }

                String cardNo = etCardNo.getText().toString().trim();
                String oldPwd = etOldPwd.getText().toString().trim();
                String newPwd = etNewPwd.getText().toString().trim();
                changePass(mContext, cardNo, newPwd, oldPwd);

                break;
            default:
                break;
        }
    }

    private void changePass(final Context mContext, String cardNo, String newPwd, String oldPwd) {

        sbsAction.changePass(mContext, cardNo, newPwd, oldPwd, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                ToastUtils.CustomShow(mContext, data);
                onBackPressed();
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
