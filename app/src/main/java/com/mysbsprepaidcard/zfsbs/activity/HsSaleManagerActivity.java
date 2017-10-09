package com.mysbsprepaidcard.zfsbs.activity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
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
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;


public class HsSaleManagerActivity extends BaseActivity implements OnClickListener {

    private RelativeLayout unmemberlogin;
    private RelativeLayout btnUnmemberMasterkey;
    private RelativeLayout btnDownAid;
    private RelativeLayout btnDownCapk;
    private RelativeLayout btnSaleUndo;
    private RelativeLayout btnBlacklist;
    private RelativeLayout btnDownParams;
    private RelativeLayout btnSettle;
    private RelativeLayout btnQueryLast;

    private List<SbsPrinterData> allData;
    private String oldTraceNo;
    private String orderNo;

    private int app_type = 0;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_manager);
//        AppManager.getAppManager().addActivity(this);
        initView();

    }


    private void initView() {
        unmemberlogin = (RelativeLayout) findViewById(R.id.id_ll_unmenber);
        unmemberlogin.setOnClickListener(this);

        btnUnmemberMasterkey = (RelativeLayout) findViewById(R.id.id_ll_unmember_masterkey);
        btnUnmemberMasterkey.setOnClickListener(this);
//		btnMemberMasterkey = (Button) findViewById(R.id.id_member_masterkey);
//		btnMemberMasterkey.setOnClickListener(this);
        btnDownAid = (RelativeLayout) findViewById(R.id.id_ll_downAid);
        btnDownAid.setOnClickListener(this);

        btnDownCapk = (RelativeLayout) findViewById(R.id.id_ll_downCapk);
        btnDownCapk.setOnClickListener(this);
//        btnUnmemberSettle = (Button) findViewById(R.id.id_unmember_settle);
//        btnUnmemberSettle.setOnClickListener(this);
//		btnMemberSettle = (Button) findViewById(R.id.id_member_settle);
//		btnMemberSettle.setOnClickListener(this);
        btnSaleUndo = (RelativeLayout) findViewById(R.id.id_ll_sale_undo);
        btnSaleUndo.setOnClickListener(this);
//        btnRefund = (Button) findViewById(R.id.id_refund);
//        btnRefund.setOnClickListener(this);

        btnBlacklist = (RelativeLayout) findViewById(R.id.id_ll_blacklist);
        btnBlacklist.setOnClickListener(this);

        btnDownParams = (RelativeLayout) findViewById(R.id.id_ll_downParams);
        btnDownParams.setOnClickListener(this);

        btnSettle = (RelativeLayout) findViewById(R.id.id_ll_settle);
        btnSettle.setOnClickListener(this);

        btnQueryLast = (RelativeLayout) findViewById(R.id.id_ll_queryLastTrans);
        btnQueryLast.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_unmenber:
//                payLogin();
                break;
            case R.id.id_ll_unmember_masterkey:
//                DownMasterKey();
                break;
            case R.id.id_ll_downAid:
//                DownloadAid();
                break;
            case R.id.id_ll_downCapk:
//                DownloadCapk();
                break;
            case R.id.id_ll_sale_undo:
                SaleUndo();
                break;
            case R.id.id_ll_blacklist:
//                DownloadBlackList();
                break;
            case R.id.id_ll_downParams:
//                DownParams();
                break;
//            case R.id.id_refund:
//                SaleRefund();
//                break;
            case R.id.id_ll_settle:
                settle();
                break;
            case R.id.id_ll_queryLastTrans:
                queryLastTrans();
                break;
        }
    }

    private void queryLastTrans() {
        String mid = MyApplication.getInstance().getLoginData().getMerchantNo();
        String tid = MyApplication.getInstance().getLoginData().getTerminalNo();
        int trace_no = Integer.parseInt("000019");
//        PayCommon.QueryLastTrans(this, trace_no, mid, tid, new PayCommon.ComTransResult<ComTransInfo>() {
//            @Override
//            public void success(ComTransInfo transInfo) {
//                ToastUtils.CustomShow(HsSaleManagerActivity.this, "交易成功");
//            }
//
//            @Override
//            public void failed(String error) {
//
//            }
//        });
    }


    private void settle() {
        String mid = MyApplication.getInstance().getLoginData().getMerchantNo();
        String tid = MyApplication.getInstance().getLoginData().getTerminalNo();
//        PayCommon.settle(this, mid, tid, new PayCommon.ComTransResult<ComSettleInfo>() {
//            @Override
//            public void success(ComSettleInfo comSettleInfo) {
//                Printer.getInstance(HsSaleManagerActivity.this).printSettle(comSettleInfo);
//                payLogin();
//            }
//
//            @Override
//            public void failed(String error) {
//                ToastUtils.CustomShow(HsSaleManagerActivity.this, error);
//            }
//        });
    }


    private void SaleUndo() {
        if (Config.isLianDong == true) {
            SaleUndoAction();
        } else {
//            startAction(this, SaleUndoActivity.class, false);
        }

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
                if (StringUtils.isEquals(allData.get(i).getVoucherNo(), oldTraceNo)) {
                    showTransInfo(allData.get(i));

                    return;
                }
            }
        }
        ToastUtils.CustomShow(this, "没有该笔交易");

    }

    private void showTransInfo(SbsPrinterData sbsPrinterData) {


        //获取交易的订单号
        Gson gson = new Gson();
        TransUploadRequest data = gson.fromJson(sbsPrinterData.getTransUploadData(), TransUploadRequest.class);

        orderNo = data.getClientOrderNo();
        phone = sbsPrinterData.getPhoneNo();
        app_type = sbsPrinterData.getApp_type();


    }

    private void SaleUndoAction() {


        String mid = MyApplication.getInstance().getLoginData().getMerchantNo();
        String tid = MyApplication.getInstance().getLoginData().getTerminalNo();
//        PayCommon.voidSale(this, 0, mid, tid, new PayCommon.ComTransResult<ComTransInfo>() {
//            @Override
//            public void success(ComTransInfo transInfo) {
//                LogUtils.e("SaleUndoAction", transInfo.toString());
//                setUndoPrintData(transInfo);
//
//                oldTraceNo = String.format("%06d", transInfo.getOldTrace());
//                saleUndoInfo();
//                if (app_type == Config.APP_SBS) {
//                    setTransCancel();
//                } else if (app_type == Config.APP_Richer_e) {
//                    Richer_setTransCancel();
//                }
//
//
//            }
//
//            @Override
//            public void failed(String error) {
//
//            }
//        });
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
                ToastUtils.CustomShow(HsSaleManagerActivity.this, "撤销成功");
                printerData.setRefundUpload(true);
                //这个地方用来 在交易记录里去打印用的
                request.setSid(MyApplication.getInstance().getLoginData().getSid());
                // 备份下交易流水数据
//                setTransUpLoadData(request);
                printerData.setApp_type(0);


//                PrinterDataSave();
                // 打印
                Printer.print(printerData, HsSaleManagerActivity.this);
                finish();

            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(HsSaleManagerActivity.this, "撤销成功");
                // 备份下交易流水数据
                request.setSid(MyApplication.getInstance().getLoginData().getSid());
//                setTransUpLoadData(request);
                // 保存打印的数据，不保存图片数据
                printerData.setApp_type(0);


//                PrinterDataSave();
                // 打印
                Printer.print(printerData, HsSaleManagerActivity.this);
                finish();

            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(HsSaleManagerActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(HsSaleManagerActivity.this, OperatorLoginActivity1.class, false);
                }

            }
        });
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
//        printerData.setOldOrderId(transInfo.getOldTrace() + "");
//
////        amount = transInfo.getTransAmount();
//    }
//
//    private void PrinterDataSave() {
//
//        CommonFunc.PrinterDataDelete();
//        printerData.setStatus(true);
//        if (printerData.save()) {
//            LogUtils.e("打印数据存储成功");
//        } else {
//            LogUtils.e("打印数据存储失败");
//        }
//    }
//
//
//    private void setTransUpLoadData(TransUploadRequest request) {
//        Gson gson = new Gson();
//        String data = gson.toJson(request);
//        LogUtils.e("SaleUndoActivity", data);
//        printerData.setTransUploadData(data);
//    }


//    /**
//     * 撤销流水上送
//     */
//    private void Richer_setTransCancel() {
//        final TransUploadRequest request = new TransUploadRequest();
//        String orderId = CommonFunc.getNewClientSn(this, Constants.PAY_WAY_UNDO);
//        printerData.setClientOrderNo(orderId);
//        printerData.setOldOrderId(orderNo);
//        request.setAction("2");
//        request.setOld_trade_order_num(orderNo);
//        request.setNew_trade_order_num(orderId);
//        request.setPayType(Constants.PAY_WAY_UNDO);
//        request.setAuthCode(printerData.getVoucherNo());
//        request.setClientOrderNo(orderNo);
//        request.setT(StringUtils.getdate2TimeStamp(printerData.getDateTime()));
//        request.setMerchantNo(printerData.getMerchantNo());
//        request.setCardNo(phone);
//        request.setPayType(printerData.getPayType());
//        String amountStr = printerData.getAmount();
//        BigDecimal big = new BigDecimal(amountStr);
//        int amount = big.multiply(new BigDecimal(100)).intValue();
//        request.setBankAmount(amount);
//        request.setCash(amount);
//
//
//        RicherQb.UploadTransInfo(this, request, new ActionCallbackListener<RicherGetMember>() {
//            @Override
//            public void onSuccess(RicherGetMember data) {
//                ToastUtils.CustomShow(HsSaleManagerActivity.this, "撤销成功");
//                printerData.setRefundUpload(true);
//                //这个地方用来 在交易记录里去打印用的
//                request.setSid(MyApplication.getInstance().getLoginData().getSid());
//                // 备份下交易流水数据
//                setTransUpLoadData(request);
//                printerData.setApp_type(app_type);
//
//
//                PrinterDataSave();
//                // 打印
//                Printer.print(printerData, HsSaleManagerActivity.this);
//                finish();
//
//            }
//
//
//            @Override
//            public void onFailure(String errorEvent, String message) {
//                ToastUtils.CustomShow(HsSaleManagerActivity.this, "撤销成功");
//                // 备份下交易流水数据
//                request.setSid(MyApplication.getInstance().getLoginData().getSid());
//                setTransUpLoadData(request);
//                // 保存打印的数据，不保存图片数据
//                printerData.setApp_type(app_type);
//
//
//                PrinterDataSave();
//                // 打印
//                Printer.print(printerData, HsSaleManagerActivity.this);
//                finish();
//
//
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
//                    CommonFunc.startAction(HsSaleManagerActivity.this, OperatorLoginActivity.class, false);
//                } else {
//                    CommonFunc.startAction(HsSaleManagerActivity.this, OperatorLoginActivity1.class, false);
//                }
//            }
//        });
//    }
}