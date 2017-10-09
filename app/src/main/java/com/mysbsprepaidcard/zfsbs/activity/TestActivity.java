package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.TransUploadRequest;
import com.mysbsprepaidcard.zfsbs.model.TransUploadResponse;
import com.tool.utils.dialog.LoadingDialog;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;


public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        transUploadAction1();

    }




    /**
     * 流水上送
     *
     */
    private void transUploadAction1() {
        final LoadingDialog dialog = new LoadingDialog(this);
        dialog.show("正在上传交易流水...");
        dialog.setCancelable(false);

        final TransUploadRequest request = new TransUploadRequest();
        request.setSid(195);
        request.setCardNo("");
        request.setPassword("");

        if (printerData.getPayType() == Constants.PAY_WAY_CASH) {
            request.setCash(0);
            request.setBankAmount(0);
        } else {
            request.setCash(0);
            request.setBankAmount(900);
        }
        request.setCouponCoverAmount(0);
        request.setPointCoverAmount(0);
        request.setCouponSns("");
        request.setClientOrderNo("2120170221075912353WP16151Q000040440");
        request.setActivateCode("WP16151Q000040440");
        request.setMerchantNo("0002900F0368364");
        request.setT(StringUtils.getdate2TimeStamp("2017-02-21 07:59:12"));//(StringUtils.getCurTimeMills());
//        LogUtils.e("time = " + printerData.getDateTime());
//        long t = StringUtils.getdate2TimeStamp(printerData.getDateTime());
//        LogUtils.e("t = " + t);
//        String t1 = StringUtils.timeStamp2Date(t + "");
//        LogUtils.e("t1 = " + t1);

        request.setTransNo("2017022121001004600222866891");
        request.setAuthCode("201702210759088232193");
        request.setSerialNum(StringUtils.getSerial());
        request.setPayType(1);
        request.setPointAmount(0);


        this.sbsAction.transUpload(this, request, new ActionCallbackListener<TransUploadResponse>() {
            @Override
            public void onSuccess(TransUploadResponse data) {

                dialog.dismiss();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                dialog.dismiss();
                ToastUtils.CustomShow(TestActivity.this, errorEvent + "#" + message);

            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                dialog.dismiss();

            }
        });
    }
}
