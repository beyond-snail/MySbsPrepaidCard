package com.mysbsprepaidcard.zfsbs.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myokhttp.MyOkHttp;
import com.myokhttp.response.JsonResponseHandler;
import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Config;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.action.Printer;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.SbsPrinterData;
import com.mysbsprepaidcard.zfsbs.model.TransUploadRequest;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.dialog.LoadingDialog;
import com.tool.utils.utils.EncryptMD5Util;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class SaleUndoActivity extends BaseActivity implements OnClickListener {

    private LinearLayout llPassword;
    private LinearLayout llTraceNo;
    private LinearLayout llText;
    private EditText etPassword;
    private EditText etTraceNo;
    private Button btnPassword;
    private Button btnTraceNo;
    private Button btnInfo;
    private Button btnClose;
    private TextView tv;

    private List<SbsPrinterData> allData;
    private int index;
    private String orderNo;

    private int app_type = 0;
    private String phone;
    private int points = 0;
    private boolean isMember;
    private String oldOrderId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_sale_undo);
//        AppManager.getAppManager().addActivity(this);
        initTitle("消费撤销");
        initView();
        addListener();
    }

    private void initView() {
        etPassword = (EditText) findViewById(R.id.id_input_password);
        etTraceNo = (EditText) findViewById(R.id.id_input_trace_no);
        btnPassword = (Button) findViewById(R.id.id_password_sure);
        btnTraceNo = (Button) findViewById(R.id.id_traceNO_sure);
        btnInfo = (Button) findViewById(R.id.id_info_sure);
        btnClose = (Button) findViewById(R.id.id_close);

        llPassword = (LinearLayout) findViewById(R.id.id_ll_MasterId);
        llTraceNo = (LinearLayout) findViewById(R.id.id_ll_trace_no);
        llText = (LinearLayout) findViewById(R.id.id_ll_text);

        tv = (TextView) findViewById(R.id.id_tv);


//        app_type = (int) SPUtils.get(this, Config.APP_TYPE, Config.DEFAULT_APP_TYPE);
    }

    private void addListener() {
        btnPassword.setOnClickListener(this);
        btnTraceNo.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_password_sure:
                isCheckPassword();
                break;
            case R.id.id_traceNO_sure:
                isCheckTraceNo();
                break;
            case R.id.id_info_sure:
                SaleUndoAction();
                break;
            case R.id.id_close:
                finish();
                break;
            default:
                break;
        }
    }

    private void SaleUndoAction() {
        int trace_no = Integer.parseInt(etTraceNo.getText().toString());
        LogUtils.e(trace_no + "");
//        emvImpl.voidSale(this, traceno, new TransResult<TransInfo>() {
//
//            @Override
//            public void success(TransInfo transInfo) {
////				ToastUtils.CustomShow(SaleUndoActivity.this, "撤销成功");
////				finish();
//                LogUtils.e("SaleUndoAction",transInfo.toString());
//                setUndoPrintData(transInfo);
////				TransUploadRequest request = setTransUploadData();
////				transUploadAction(request);
//
//                setTransCancel();
//            }
//
//            @Override
//            public void failed(String error) {
//                ToastUtils.CustomShow(SaleUndoActivity.this, error);
//            }
//        });

        String mid = MyApplication.getInstance().getLoginData().getMerchantNo();
        String tid = MyApplication.getInstance().getLoginData().getTerminalNo();
//        PayCommon.voidSale(this, trace_no, mid, tid, new PayCommon.ComTransResult<ComTransInfo>() {
//            @Override
//            public void success(ComTransInfo transInfo) {
//                LogUtils.e("SaleUndoAction", transInfo.toString());
//                setUndoPrintData(transInfo);
//
//                if (app_type == Config.APP_Richer_e) {
//                    Richer_setTransCancel();
//                } else if (app_type == Config.APP_SBS) {
//                    setTransCancel();
//                } else if (app_type == Config.APP_YXF) {
////                    if (StringUtils.isEmpty(phone)){
//                        String orderId = CommonFunc.getNewClientSn(SaleUndoActivity.this, Constants.PAY_WAY_UNDO);
//                        printerData.setClientOrderNo(orderId);
//                        printerData.setOldOrderId(StringUtils.isEmpty(oldOrderId)? "" : oldOrderId);
//                        printerData.setRefundUpload(true);
//                        printerData.setApp_type(app_type);
//                        PrinterDataSave();
//                        // 打印
//                        Printer.print(printerData, SaleUndoActivity.this);
//                        finish();
////                    }else {
////                        sendYxf(printerData);
////                    }
//                } else if (app_type == Config.APP_HD){
//                    if (isMember){
//                        setTransCancel();
//                    }else {
//                        String orderId = CommonFunc.getNewClientSn(SaleUndoActivity.this, Constants.PAY_WAY_UNDO);
//                        printerData.setClientOrderNo(orderId);
//                        printerData.setOldOrderId(StringUtils.isEmpty(oldOrderId)? "" : oldOrderId);
//                        printerData.setRefundUpload(true);
//                        printerData.setApp_type(app_type);
//                        PrinterDataSave();
//                        // 打印
//                        Printer.print(printerData, SaleUndoActivity.this);
//                        finish();
//                    }
//                }
//
//            }
//
//            @Override
//            public void failed(String error) {
//                final CommonDialog confirmDialog = new CommonDialog(SaleUndoActivity.this, error);
//                confirmDialog.show();
//                confirmDialog.setClicklistener(new CommonDialog.ClickListenerInterface() {
//                    @Override
//                    public void doConfirm() {
//
//                    }
//                });
//            }
//        });
    }


//    protected void setUndoPrintData(ComTransInfo transInfo) {
//        printerData.setMerchantName(MyApplication.getInstance().getLoginData().getTerminalName());
//        printerData.setMerchantNo(transInfo.getMid());
//        printerData.setTerminalId(transInfo.getTid());
//        printerData.setOperatorNo((String) SPUtils.get(this, Constants.USER_NAME, ""));
//        printerData.setAcquirer(transInfo.getAcquirerCode());
//        printerData.setIssuer(transInfo.getIssuerCode());
//        printerData.setCardNo(StringUtils.formatCardNo(transInfo.getPan()));
//        printerData.setTransType(transInfo.getTransType() + "");
//        printerData.setExpDate(transInfo.getExpiryDate());
//        printerData.setBatchNO(StringUtils.fillZero(transInfo.getBatchNumber() + "", 6));
//        printerData.setVoucherNo(StringUtils.fillZero(transInfo.getTrace() + "", 6));
//        printerData.setDateTime(
//                StringUtils.formatTime(StringUtils.getCurYear() + transInfo.getTransDate() + transInfo.getTransTime()));
//        printerData.setAuthNo(transInfo.getAuthCode());
//        LogUtils.e(transInfo.getRrn());
//        printerData.setReferNo(transInfo.getRrn());
//        printerData.setPointCoverMoney(0);
//        printerData.setCouponCoverMoney(0);
//        // transInfo.setTransAmount(56000);
//        printerData.setOrderAmount(transInfo.getTransAmount());
//        printerData.setAmount(StringUtils.formatIntMoney(transInfo.getTransAmount()));
//        printerData.setPayType(Constants.PAY_WAY_UNDO);
//
////        amount = transInfo.getTransAmount();
//    }


    private void setTransUpLoadData(TransUploadRequest request) {
        Gson gson = new Gson();
        String data = gson.toJson(request);
        LogUtils.e("SaleUndoActivity", data);
        printerData.setTransUploadData(data);
    }


    /**
     * 撤销流水上送
     */
    private void setTransCancel() {
        final TransUploadRequest request = new TransUploadRequest();
        String orderId = CommonFunc.getNewClientSn(this, Constants.PAY_WAY_UNDO);
        printerData.setClientOrderNo(orderId);
        printerData.setOldOrderId(orderNo);
        request.setAction("2");
        request.setOld_trade_order_num(orderNo);
        request.setNew_trade_order_num(orderId);
        request.setPayType(Constants.PAY_WAY_UNDO);
        request.setAuthCode(printerData.getVoucherNo());
        request.setClientOrderNo(orderNo);
        request.setT(StringUtils.getdate2TimeStamp(printerData.getDateTime()));

        this.sbsAction.transCancelRefund(this, request, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                ToastUtils.CustomShow(SaleUndoActivity.this, "撤销成功");
                printerData.setRefundUpload(true);
                //这个地方用来 在交易记录里去打印用的
                request.setSid(MyApplication.getInstance().getLoginData().getSid());
                // 备份下交易流水数据
                setTransUpLoadData(request);
                printerData.setApp_type(app_type);

                if(app_type == Config.APP_HD) {

//                    goHdAdjustScore();

                }else {

                    PrinterDataSave();
                    // 打印
                    Printer.print(printerData, SaleUndoActivity.this);
                    finish();
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(SaleUndoActivity.this, "撤销成功");
                // 备份下交易流水数据
                request.setSid(MyApplication.getInstance().getLoginData().getSid());
                setTransUpLoadData(request);
                // 保存打印的数据，不保存图片数据
                printerData.setApp_type(app_type);
                PrinterDataSave();
                // 打印
                Printer.print(printerData, SaleUndoActivity.this);
                finish();
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(SaleUndoActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(SaleUndoActivity.this, OperatorLoginActivity1.class, false);
                }
            }
        });
    }

//    private void goHdAdjustScore() {
//        //上送积分到海鼎
//        HdAction.HdAdjustScore(SaleUndoActivity.this, phone, -points, new HdAction.HdCallResult() {
//            @Override
//            public void onSuccess(String data) {
//
//                HdAdjustScoreResponse response = new Gson().fromJson(data, HdAdjustScoreResponse.class);
//
//                //保存流水号和总积分
//                printerData.setPointCurrent(Integer.parseInt(response.getResult().getScoreTotal()));
//                printerData.setTransNo(response.getResult().getFlowNo());
//
//                // 保存打印的数据，不保存图片数据
//                PrinterDataSave();
//                // 打印
//                Printer.print(printerData, SaleUndoActivity.this);
//                finish();
//            }
//
//            @Override
//            public void onFailed(String errorCode, String message) {
//                ToastUtils.CustomShow(SaleUndoActivity.this, errorCode + "#" + message);
//                // 保存打印的数据，不保存图片数据
//                PrinterDataSave();
//                // 打印
//                Printer.print(printerData, SaleUndoActivity.this);
//                finish();
//            }
//        });
//    }


    /**
     * 赢消费流水上送
     *
     * @param recordData
     */
    private void sendYxf(final SbsPrinterData recordData) {

        String money = recordData.getAmount();//"0.01";
        String mobile = phone;//recordData.getPhoneNo();//"13979328519";
        String time = String.valueOf(StringUtils.getdate2TimeStamp(recordData.getDateTime()));
        String orderId = time + StringUtils.getSerial();

        String admin_id = (String) SPUtils.get(this, Config.YXF_MERCHANT_ID, Config.YXF_DEFAULT_MERCHANTID);

        if (StringUtils.isEmpty(admin_id)) {

            ToastUtils.CustomShow(this, "上送商户ID为空");
//            return;
        }

        if (StringUtils.isEmpty(money)) {
            ToastUtils.CustomShow(this, "上送金额为空");
//            return;
        }

        if (StringUtils.isEmpty(mobile)) {
//            ToastUtils.CustomShow(this, "上送手机号为空");
//            ToastUtils.CustomShow(SaleUndoActivity.this, "撤销成功");
//            recordData.setApp_type(app_type);
//            PrinterDataSave();
//            Printer.getInstance(SaleUndoActivity.this).print(printerData);
//            finish();
            return;
        }


        String before = admin_id + money + mobile + time + orderId + Config.YXF_KEY;
        LogUtils.e(before);
        String skey = EncryptMD5Util.MD5(before);

        Map<String, String> paramsMap = new LinkedHashMap<String, String>();
        paramsMap.put("arr1", admin_id);
        paramsMap.put("arr2", money);
        paramsMap.put("arr3", mobile);
        paramsMap.put("arr4", time);
        paramsMap.put("arr5", orderId);
        paramsMap.put("skey", skey);


        LogUtils.e(paramsMap.toString());

        final LoadingDialog dialog = new LoadingDialog(this);
        dialog.show("正在上送...");
        dialog.setCancelable(false);

        MyOkHttp.get().get(this, Config.YXF_URL, paramsMap, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                LogUtils.e("sendYxf", response.toString());
                recordData.setApp_type(app_type);


                try {
                    String state = response.getString("state");
                    String info = response.getString("info");
                    String qr_code = response.getString("qr_code");

                    if (StringUtils.isEquals(state, "0")) {
                        ToastUtils.CustomShow(SaleUndoActivity.this, "上送成功");
                        printerData.setRefundUpload(true);
                        //判断二维码链接是否为空，为空直接打印，不为空去下载
                        if (StringUtils.isEmpty(qr_code)) {
                            dialog.dismiss();

                            PrinterDataSave();
                            Printer.getInstance(SaleUndoActivity.this).print(printerData, SaleUndoActivity.this);
                            finish();
                        }else {
                            yxf_setTransUpdateResponse(recordData, qr_code, dialog, true);
                        }


                    } else {
                        dialog.dismiss();
                        ToastUtils.CustomShow(SaleUndoActivity.this, !StringUtils.isEmpty(info) ? info : "上送失败");
//                        recordData.setUploadFlag(true);

                        PrinterDataSave();
                        Printer.getInstance(SaleUndoActivity.this).print(printerData, SaleUndoActivity.this);
                        finish();
                    }


                } catch (JSONException e) {
//                    e.printStackTrace();
                    dialog.dismiss();
                    ToastUtils.CustomShow(SaleUndoActivity.this, "返回数据解析失败");
//                    recordData.setUploadFlag(true);
                    PrinterDataSave();
                    Printer.getInstance(SaleUndoActivity.this).print(printerData, SaleUndoActivity.this);
                    finish();
                }


            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                dialog.dismiss();
                LogUtils.e("sendYxf", error_msg);

//                recordData.setUploadFlag(true);
                recordData.setApp_type(app_type);
                PrinterDataSave();
                Printer.getInstance(SaleUndoActivity.this).print(printerData, SaleUndoActivity.this);
                finish();
            }
        });
    }


    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            Bitmap point_bitmap = bundle.getParcelable("point_bitmap");
            Bitmap title_bitmap = bundle.getParcelable("title_bitmap");
            printerData.setPoint_bitmap(point_bitmap);
            printerData.setCoupon_bitmap(title_bitmap);


            // 打印
            Printer.print(printerData, SaleUndoActivity.this);
            finish();
        }

        ;
    };



    protected void yxf_setTransUpdateResponse(final SbsPrinterData data, final String qr_code, final LoadingDialog dialog, boolean flag) {

        data.setCoupon(qr_code);
        if (flag) {
            // 保存打印的数据，不保存图片数据
            PrinterDataSave();
        }

        //开启线程下载二维码图片
        new Thread(new Runnable() {

            @Override
            public void run() {

                Bitmap title_bitmap = null;

                if (!StringUtils.isEmpty(qr_code)) {

                    try {
                        title_bitmap = Glide.with(getApplicationContext())
                                .load(qr_code)
                                .asBitmap()
                                .centerCrop()
                                .into(200, 200).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }

                dialog.dismiss();

                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelable("title_bitmap", title_bitmap);
                msg.setData(bundle);
                mhandler.sendMessage(msg);

            }
        }).start();

    }





    /**
     * 撤销流水上送
     */
    private void Richer_setTransCancel() {
        final TransUploadRequest request = new TransUploadRequest();
        String orderId = CommonFunc.getNewClientSn(this, Constants.PAY_WAY_UNDO);
        printerData.setClientOrderNo(orderId);
        printerData.setOldOrderId(orderNo);
        request.setAction("2");
        request.setOld_trade_order_num(orderNo);
        request.setNew_trade_order_num(orderId);
        request.setPayType(Constants.PAY_WAY_UNDO);
        request.setAuthCode(printerData.getVoucherNo());
        request.setClientOrderNo(orderNo);
        request.setT(StringUtils.getdate2TimeStamp(printerData.getDateTime()));
        request.setMerchantNo(printerData.getMerchantNo());
        request.setCardNo(phone);
        request.setPayType(printerData.getPayType());
        String amountStr=printerData.getAmount();
        BigDecimal big = new BigDecimal(amountStr);
        int amount = big.multiply(new BigDecimal(100)).intValue();
        request.setBankAmount(amount);
        request.setCash(amount);



//        RicherQb.UploadTransInfo(this, request, new ActionCallbackListener<RicherGetMember>() {
//            @Override
//            public void onSuccess(RicherGetMember data) {
//                ToastUtils.CustomShow(SaleUndoActivity.this, "撤销成功");
//                printerData.setRefundUpload(true);
//                //这个地方用来 在交易记录里去打印用的
//                request.setSid(MyApplication.getInstance().getLoginData().getSid());
//                // 备份下交易流水数据
//                setTransUpLoadData(request);
//                printerData.setApp_type(app_type);
//                PrinterDataSave();
//                // 打印
//                Printer.print(printerData, SaleUndoActivity.this);
//                finish();
//            }
//
//
//            @Override
//            public void onFailure(String errorEvent, String message) {
//                ToastUtils.CustomShow(SaleUndoActivity.this, "撤销成功");
//                // 备份下交易流水数据
//                request.setSid(MyApplication.getInstance().getLoginData().getSid());
//                setTransUpLoadData(request);
//                // 保存打印的数据，不保存图片数据
//                printerData.setApp_type(app_type);
//                PrinterDataSave();
//                // 打印
//                Printer.print(printerData, SaleUndoActivity.this);
//                finish();
//            }
//
//            @Override
//            public void onFailurTimeOut(String s, String error_msg) {
//
//            }
//
//            @Override
//            public void onLogin() {
//                AppManager.getAppManager().finishAllActivity();
//                if (Config.OPERATOR_UI_BEFORE) {
//                    CommonFunc.startAction(SaleUndoActivity.this, OperatorLoginActivity.class, false);
//                } else {
//                    CommonFunc.startAction(SaleUndoActivity.this, OperatorLoginActivity1.class, false);
//                }
//            }
//        });
    }

    private void PrinterDataSave() {

        CommonFunc.PrinterDataDelete();
        printerData.setStatus(true);
        if (printerData.save()) {
            LogUtils.e("打印数据存储成功");
        } else {
            LogUtils.e("打印数据存储失败");
        }
    }

    private void isCheckTraceNo() {
        if (StringUtils.isEmpty(etTraceNo.getText().toString())) {
            ToastUtils.CustomShow(this, "请输入原交易凭证号");
            return;
        }
        saleUndoInfo();
    }

    private void saleUndoInfo() {

        // 从交易记录中读取数据
        allData = DataSupport.order("id desc").limit(100).find(SbsPrinterData.class);
        if (allData.size() <= 0) {
            ToastUtils.CustomShow(this, "没有交易记录");
            return;
        }


        // 遍历
        for (int i = 0; i < allData.size(); i++) {
            // 遍历刷卡支付
            if (allData.get(i).getPayType() == Constants.PAY_WAY_FLOT && !StringUtils.isEmpty(allData.get(i).getVoucherNo())) {
                if (StringUtils.isEquals(allData.get(i).getVoucherNo(), etTraceNo.getText().toString())) {
                    showTransInfo(allData.get(i));
                    index = i;
                    return;
                }
            }
        }
        ToastUtils.CustomShow(this, "没有该笔交易");

    }

    private void showTransInfo(SbsPrinterData sbsPrinterData) {
        llTraceNo.setVisibility(View.GONE);
        llText.setVisibility(View.VISIBLE);
        String str = "卡号：\r\n" + sbsPrinterData.getCardNo() + "\r\n" + "金额：" + sbsPrinterData.getAmount() + "\r\n"
                + "交易时间：\r\n" + sbsPrinterData.getDateTime();
        tv.setText(str);

        //获取交易的订单号
        Gson gson = new Gson();
        TransUploadRequest data = gson.fromJson(sbsPrinterData.getTransUploadData(), TransUploadRequest.class);

        if (data != null) {
            orderNo = data.getClientOrderNo();
        }

        oldOrderId = sbsPrinterData.getClientOrderNo();
        app_type=sbsPrinterData.getApp_type();
        phone=sbsPrinterData.getPhoneNo();
        points = sbsPrinterData.getPoint();
        isMember = sbsPrinterData.isMember();
    }

    private void isCheckPassword() {
        String pass = (String) SPUtils.get(this, Constants.MASTER_PASS, Constants.DEFAULT_MASTER_PASS);
        if (StringUtils.isEmpty(etPassword.getText().toString())) {
            ToastUtils.CustomShow(this, "请输入主管理密码");
            return;
        }
        if (!StringUtils.isEquals(pass, etPassword.getText().toString())) {
            ToastUtils.CustomShow(this, "主管理密码错误");
            return;
        }
        llPassword.setVisibility(View.GONE);
        llTraceNo.setVisibility(View.VISIBLE);
        etTraceNo.setFocusable(true);
        etTraceNo.requestFocus();
    }
}
