package com.mysbsprepaidcard.zfsbs.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.myokhttp.MyOkHttp;
import com.myokhttp.response.JsonResponseHandler;
import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Config;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.action.FyBat;
import com.mysbsprepaidcard.zfsbs.core.action.Printer;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.FailureData;
import com.mysbsprepaidcard.zfsbs.model.FyMicropayRequest;
import com.mysbsprepaidcard.zfsbs.model.FyMicropayResponse;
import com.mysbsprepaidcard.zfsbs.model.FyQueryRequest;
import com.mysbsprepaidcard.zfsbs.model.FyQueryResponse;
import com.mysbsprepaidcard.zfsbs.model.FyRefundResponse;
import com.mysbsprepaidcard.zfsbs.model.SbsPrinterData;
import com.mysbsprepaidcard.zfsbs.model.TransUploadRequest;
import com.mysbsprepaidcard.zfsbs.model.TransUploadResponse;
import com.mysbsprepaidcard.zfsbs.model.ZfQbResponse;
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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class SaleMainActivity extends BaseActivity implements OnClickListener {

//    private RelativeLayout btnSale;
//    private RelativeLayout btnyxfSale;
//    private RelativeLayout btnyxfSaleManager;
//
//    private RelativeLayout btnRecord;
//    private RelativeLayout btnSaleManager;
//    private RelativeLayout btnSaleInfo;
//    private RelativeLayout btnGetInfo;
//    private RelativeLayout btnChangePass;
//    private RelativeLayout btnEndQuery;
//    private RelativeLayout btnShitRoom;


//    private List<View> views = null;
//
//    public ViewPager mViewPager;
//    public LinearLayout viewPoints;
    private SbsPrinterData printerData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_sale_main2);
        AppManager.getAppManager().addActivity(this);
        initTitle("首页");
        initView();
//        addLinstener();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initView() {

//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPoints = (LinearLayout) findViewById(R.id.dots_parent);
//
//        views = new ArrayList<View>();
//        View view1 = null, view2 = null;
//
//
//        view1 = LayoutInflater.from(this).inflate(R.layout.activity_sale_main2, null);
////        view2 = LayoutInflater.from(this).inflate(R.layout.activity_sale_main1, null);
//
//        views.add(view1);
//        views.add(view2);

//        btnSale = (RelativeLayout) view1.findViewById(R.id.id_ll_sale);
//        btnRecord = (RelativeLayout) view1.findViewById(R.id.id_ll_record);
//        btnSaleManager = (RelativeLayout) view1.findViewById(R.id.id_ll_sale_manager);
//        btnSaleInfo = (RelativeLayout) view1.findViewById(R.id.id_ll_sale_info);
//        btnGetInfo = (RelativeLayout) view1.findViewById(R.id.id_ll_getInfo);
//        btnEndQuery = (RelativeLayout) view1.findViewById(R.id.id_ll_end_query);
//
//        btnChangePass = (RelativeLayout) view2.findViewById(R.id.id_ll_change_pass);
//        btnShitRoom = (RelativeLayout) view2.findViewById(R.id.id_ll_shiftroom);
//        btnyxfSale = (RelativeLayout) view2.findViewById(R.id.id_ll_yxf_sale);
//        btnyxfSaleManager = (RelativeLayout) view2.findViewById(R.id.id_ll_yxf_sale_manager);
//
//
//        btnyxfSale.setVisibility(View.INVISIBLE);
//        btnyxfSaleManager.setVisibility(View.INVISIBLE);

        linearLayout(R.id.id_ll_sale).setOnClickListener(this);
        linearLayout(R.id.id_ll_record).setOnClickListener(this);
        linearLayout(R.id.id_ll_member_recharge).setOnClickListener(this);
        linearLayout(R.id.id_ll_open_card).setOnClickListener(this);
        linearLayout(R.id.id_ll_system_setting).setOnClickListener(this);
        linearLayout(R.id.id_ll_change_pass).setOnClickListener(this);



        //注册滑动页面
//        new ViewPagerHelper(false, mViewPager, views, viewPoints, R.mipmap.page_indicator_focused,
//                R.mipmap.page_indicator_unfocused);
    }

//    private void addLinstener() {
//        if (Config.APP_UI != Config.APP_SBS_UI_YXF) {
//            btnSale.setOnClickListener(this);
//        }
//
//        btnyxfSale.setOnClickListener(this);
//        btnyxfSaleManager.setOnClickListener(this);
//        btnRecord.setOnClickListener(this);
//        btnSaleManager.setOnClickListener(this);
//        btnSaleInfo.setOnClickListener(this);
//        btnGetInfo.setOnClickListener(this);
//        btnChangePass.setOnClickListener(this);
//        btnEndQuery.setOnClickListener(this);
//        btnShitRoom.setOnClickListener(this);
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_ll_sale:
                CommonFunc.startAction(this, InputAmountActivity.class, false);
                break;
            case R.id.id_ll_record:
                CommonFunc.startAction(this, RecordInfoActivity.class, false);
                break;
            case R.id.id_ll_member_recharge:
                CommonFunc.startAction(this, CheckOperatorLoginActivity.class, false);
                break;
            case R.id.id_ll_open_card:
                CommonFunc.startAction(this, OpenCardActivity.class, false);
                break;
            case R.id.id_ll_system_setting:
                CommonFunc.startAction(this, SysMainActivity.class, false);
                break;
            case R.id.id_ll_change_pass:
                CommonFunc.startAction(this, CardChangeActivity.class, false);
                break;

//            case R.id.id_ll_sale:
//                SPUtils.put(this, Config.APP_TYPE, Config.APP_SBS);
////                if (getLoginDataNoMember()) {
//                if (true) {
//
//                    CommonFunc.startAction(this, InputAmountActivity.class, false);
//                } else {
////                        SPUtils.put(this, Config.APP_TYPE, Config.APP_SBS);
//                    CommonFunc.startAction(this, HsSaleManagerActivity.class, false);
//                }
////                }else {
////                    getInfo(false);
////                }
//                break;
//            case R.id.id_ll_yxf_sale:
//                SPUtils.put(this, Config.APP_TYPE, Config.APP_YXF);
//                if (isCheckStatus()) {
//                    if (!StringUtils.isEmpty((String) SPUtils.get(this, Config.YXF_MERCHANT_ID, Config.YXF_DEFAULT_MERCHANTID))) {
//
//                        CommonFunc.startAction(this, InputAmountActivity.class, false);
//                    } else {
//                        CommonFunc.startAction(this, YxfManagerActivity.class, false);
//                    }
//                } else {
////                    SPUtils.put(this, Config.APP_TYPE, Config.APP_YXF);
//                    CommonFunc.startAction(this, HsSaleManagerActivity.class, false);
//                }
//                break;
//            case R.id.id_ll_hd:
//                SPUtils.put(this, Config.APP_TYPE, Config.APP_HD);
//                if (isCheckStatus()) {
//
//                    CommonFunc.startAction(this, InputAmountActivity.class, false);
//                } else {
////                    SPUtils.put(this, Config.APP_TYPE, Config.APP_HD);
//                    CommonFunc.startAction(this, HsSaleManagerActivity.class, false);
//                }
//                break;
//            case R.id.id_ll_yxf_sale_manager:
//                CommonFunc.startAction(this, YxfManagerActivity.class, false);
//                break;
//            case R.id.id_ll_record:
//                CommonFunc.startAction(this, RecordInfoActivity.class, false);
//                break;
//            case R.id.id_ll_sale_manager:
//                CommonFunc.startAction(this, HsSaleManagerActivity.class, false);
//                break;
//            case R.id.id_ll_sale_info:
//                CommonFunc.startAction(this, LoginInfoActivity.class, false);
//                break;
//            case R.id.id_ll_getInfo:
//                if (Config.OPERATOR_UI_BEFORE) {
//                    CommonFunc.startAction(this, GetLoginInfoActivity.class, false);
//                } else {
//                    CommonFunc.startAction(this, GetLoginInfoActivity1.class, false);
//                }
//                break;
//            case R.id.id_ll_change_pass:
//                CommonFunc.startAction(this, ChangePassActivity.class, false);
//                break;
//            case R.id.id_ll_end_query:
//                endQuery1();
//                break;
//            case R.id.id_ll_shiftroom:
//                CommonFunc.startAction(this, ShiftRoomActivity.class, false);
//                break;
//            case R.id.id_ll_richer_e_qb:
//                SPUtils.put(this, Config.APP_TYPE, Config.APP_Richer_e);
////                if (getLoginDataIsMember()) {
//                if (isCheckStatus()) {
//
//                    CommonFunc.startAction(this, InputAmountActivity.class, false);
//                } else {
//                    CommonFunc.startAction(this, HsSaleManagerActivity.class, false);
//                }
////                }else {
////                    getInfo(true);
////                }
//                break;
            default:
                break;
        }
    }


    /**
     * 末笔查询
     */
    private void endQuery1() {

        if (CommonFunc.recoveryFailureInfo(this) == null) {
            ToastUtils.CustomShow(this, "末笔交易成功，无需查询");
            return;
        }
        switch (CommonFunc.recoveryFailureInfo(this).getPay_type()) {
            case Constants.PAY_WAY_QB:
                ZfQbQuery();
                break;
            case Constants.PAY_WAY_ALY:
            case Constants.PAY_WAY_WX:
                if (CommonFunc.recoveryFailureInfo(this).getFaiureType() == Constants.FY_FAILURE_PAY) {
                    ZfFyPayQuery();
                } else if (CommonFunc.recoveryFailureInfo(this).getFaiureType() == Constants.FY_FAILURE_QUERY) {
                    ZfFyQuery();
                }
                break;
        }
    }

    /**
     * 钱包末笔查询
     */
    private void ZfQbQuery() {

        printerData = new SbsPrinterData();

        CommonFunc.ZfQbFailQuery(this, new ActionCallbackListener<ZfQbResponse>() {
            @Override
            public void onSuccess(ZfQbResponse data) {

                FailureData failureData = CommonFunc.recoveryFailureInfo(SaleMainActivity.this);
                //流水上送
                setQbPay1(data, failureData.getOrderNo(),
                        failureData.getTime(), failureData.getTraceNum());
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(SaleMainActivity.this, errorEvent + "#" + message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {
                ToastUtils.CustomShow(SaleMainActivity.this, s + "#" + error_msg);
            }

            @Override
            public void onLogin() {
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity1.class, false);
                }
            }
        });
    }


    private FyBat.FYPayResultEvent listener1 = new FyBat.FYPayResultEvent() {
        @Override
        public void onSuccess(FyMicropayResponse data) {


            setFySmPay1(data);
        }

        @Override
        public void onSuccess(FyQueryResponse data) {
            //先判断本地数据是否存在，防止从华尔街平台拿到的是上一笔成功的交易
            SbsPrinterData datas = DataSupport.findLast(SbsPrinterData.class);
            if (!StringUtils.isEmpty(datas.getAuthCode()) && datas.getAuthCode().equals(data.getMchnt_order_no())) {
                ToastUtils.CustomShow(SaleMainActivity.this, "请确认消费者交易成功。");
                return;
            }
            setFySmPayQurey1(data);
        }

        @Override
        public void onSuccess(FyRefundResponse data) {

        }

        @Override
        public void onFailure(int statusCode, String error_msg, String pay_type, String amount) {

        }

        @Override
        public void onFailure(FyMicropayRequest data) {

        }

        @Override
        public void onFailure(FyQueryRequest data) {

        }

        @Override
        public void onLogin() {

        }
    };

    /**
     * 富友扫码支付异常处理
     */
    private void ZfFyPayQuery() {
        printerData = new SbsPrinterData();
        FyBat fybat = new FyBat(this, listener1);
        fybat.terminalQuery1(CommonFunc.recoveryFailureInfo(this).getOrder_type(), CommonFunc.recoveryFailureInfo(this).getAmount(), true,
                CommonFunc.recoveryFailureInfo(this).getOutOrderNo());
    }

    /**
     * 富友扫码查询异常处理
     */
    private void ZfFyQuery() {
        printerData = new SbsPrinterData();
        FyBat fybat = new FyBat(this, listener1);
        fybat.query1(this, CommonFunc.recoveryFailureInfo(this).getOrder_type(), CommonFunc.recoveryFailureInfo(this).getOrderNo(),
                CommonFunc.recoveryFailureInfo(this).getOutOrderNo());
    }


    /**
     * 富友扫码支付参数设置
     *
     * @param data
     */
    private void setFySmPay1(FyMicropayResponse data) {
        printerData.setMerchantName(MyApplication.getInstance().getLoginData().getFyMerchantName());
        printerData.setMerchantNo(MyApplication.getInstance().getLoginData().getFyMerchantNo());
        printerData.setTerminalId(StringUtils.getTerminalNo(StringUtils.getSerial()));
        printerData.setOperatorNo((String) SPUtils.get(this, Constants.USER_NAME, ""));
        printerData.setTransNo(data.getTransaction_id());
        printerData.setAuthCode(data.getMchnt_order_no());
        printerData.setDateTime(StringUtils.formatTime(data.getTxn_begin_ts()));
        printerData.setOrderAmount(CommonFunc.recoveryMemberInfo(this).getTradeMoney());
        printerData.setAmount(StringUtils.formatStrMoney(data.getTotal_amount()));
        printerData.setPointCoverMoney(CommonFunc.recoveryMemberInfo(this).getPointCoverMoney());
        printerData.setCouponCoverMoney(CommonFunc.recoveryMemberInfo(this).getCouponCoverMoney());
        printerData.setScanPayType(MyApplication.getInstance().getLoginData().getScanPayType());
        if (data.getOrder_type().equals(Constants.PAY_FY_ALY)) {
            printerData.setPayType(Constants.PAY_WAY_ALY);
        } else if (data.getOrder_type().equals(Constants.PAY_FY_WX)) {
            printerData.setPayType(Constants.PAY_WAY_WX);
        }

        if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_SBS) {
            TransUploadRequest request = CommonFunc.setTransUploadData(printerData, CommonFunc.recoveryMemberInfo(this),
                    data.getOutOrderNum(), printerData.getTransNo(), printerData.getAuthCode()
            );
            printerData.setClientOrderNo(data.getOutOrderNum());
            transUploadAction1(request);
        } else if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_YXF) {
            if (!StringUtils.isEmpty(CommonFunc.recoveryMemberInfo(SaleMainActivity.this).getMemberCardNo())) {
                printerData.setPhoneNo(CommonFunc.recoveryMemberInfo(SaleMainActivity.this).getMemberCardNo());
                sendYxf(printerData);
            } else {
                printerData.setApp_type(Config.APP_YXF);
                PrinterDataSave();
                Printer.getInstance(SaleMainActivity.this).print(printerData, SaleMainActivity.this);
            }
        } else if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_Richer_e) {

        } else if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_HD) {
            if (CommonFunc.recoveryFailureInfo(this).isMember()) {
                TransUploadRequest request = CommonFunc.setTransUploadData(printerData, CommonFunc.recoveryMemberInfo(this),
                        data.getOutOrderNum(), printerData.getTransNo(), printerData.getAuthCode()
                );
                printerData.setClientOrderNo(data.getOutOrderNum());
                transUploadAction2(request);
            } else {
                printerData.setApp_type(Config.APP_HD);
                printerData.setClientOrderNo(data.getOutOrderNum());
                PrinterDataSave();
                Printer.getInstance(SaleMainActivity.this).print(printerData, SaleMainActivity.this);
            }
        }

    }


    /**
     * 扫码支付查询异常
     *
     * @param data
     */
    private void setFySmPayQurey1(FyQueryResponse data) {
        printerData.setMerchantName(MyApplication.getInstance().getLoginData().getFyMerchantName());
        printerData.setMerchantNo(MyApplication.getInstance().getLoginData().getFyMerchantNo());
        printerData.setTerminalId(StringUtils.getTerminalNo(StringUtils.getSerial()));
        printerData.setOperatorNo((String) SPUtils.get(this, Constants.USER_NAME, ""));
        printerData.setTransNo(data.getTransaction_id());
        printerData.setAuthCode(data.getMchnt_order_no());
        printerData.setDateTime(StringUtils.getCurTime());
        printerData.setOrderAmount(CommonFunc.recoveryMemberInfo(this).getTradeMoney());
        printerData.setAmount(StringUtils.formatStrMoney(data.getOrder_amt()));
        printerData.setPointCoverMoney(CommonFunc.recoveryMemberInfo(this).getPointCoverMoney());
        printerData.setCouponCoverMoney(CommonFunc.recoveryMemberInfo(this).getCouponCoverMoney());
        printerData.setScanPayType(MyApplication.getInstance().getLoginData().getScanPayType());
        if (data.getOrder_type().equals(Constants.PAY_FY_ALY)) {
            printerData.setPayType(Constants.PAY_WAY_ALY);
        } else if (data.getOrder_type().equals(Constants.PAY_FY_WX)) {
            printerData.setPayType(Constants.PAY_WAY_WX);
        }

        if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_SBS) {
            TransUploadRequest request = CommonFunc.setTransUploadData(printerData, CommonFunc.recoveryMemberInfo(this),
                    data.getOutOrderNum(), printerData.getTransNo(), printerData.getAuthCode()
            );
            printerData.setClientOrderNo(data.getOutOrderNum());
            transUploadAction1(request);
        } else if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_YXF) {
            if (!StringUtils.isEmpty(CommonFunc.recoveryMemberInfo(SaleMainActivity.this).getMemberCardNo())) {
                printerData.setPhoneNo(CommonFunc.recoveryMemberInfo(SaleMainActivity.this).getMemberCardNo());
                sendYxf(printerData);
            } else {
                printerData.setApp_type(Config.APP_YXF);
                PrinterDataSave();
                Printer.getInstance(SaleMainActivity.this).print(printerData, SaleMainActivity.this);
            }
        } else if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_Richer_e) {
            TransUploadRequest request = CommonFunc.setTransUploadData(printerData, CommonFunc.recoveryMemberInfo(this),
                    data.getOutOrderNum(), printerData.getTransNo(), printerData.getAuthCode()
            );
            printerData.setClientOrderNo(data.getOutOrderNum());
//            Richer_transUploadAction(request);
        } else if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_HD) {
            if (CommonFunc.recoveryFailureInfo(this).isMember()) {
                TransUploadRequest request = CommonFunc.setTransUploadData(printerData, CommonFunc.recoveryMemberInfo(this),
                        data.getOutOrderNum(), printerData.getTransNo(), printerData.getAuthCode()
                );
                printerData.setClientOrderNo(data.getOutOrderNum());
                transUploadAction2(request);
            } else {
                printerData.setApp_type(Config.APP_HD);
                printerData.setClientOrderNo(data.getOutOrderNum());
                PrinterDataSave();
                Printer.getInstance(SaleMainActivity.this).print(printerData, SaleMainActivity.this);
            }
        }

    }

    /**
     * 设置钱包参数
     *
     * @param data
     * @param orderNo
     * @param time
     * @param traceNum
     */
    private void setQbPay1(ZfQbResponse data, String orderNo, String time, String traceNum) {
        printerData.setMerchantName(MyApplication.getInstance().getLoginData().getTerminalName());
        printerData.setMerchantNo(data.getGroupId());
        printerData.setTerminalId(StringUtils.getSerial());
        printerData.setOperatorNo((String) SPUtils.get(this, Constants.USER_NAME, ""));
        printerData.setDateTime(time);
        printerData.setClientOrderNo(orderNo);
        printerData.setTransNo(traceNum);
        printerData.setAuthCode(data.getSystemOrderNo());
        printerData.setDateTime(StringUtils.formatTime(time));
        printerData.setOrderAmount(CommonFunc.recoveryMemberInfo(this).getTradeMoney());
        printerData.setAmount(StringUtils.formatIntMoney(CommonFunc.recoveryMemberInfo(this).getRealMoney()));
        printerData.setPointCoverMoney(CommonFunc.recoveryMemberInfo(this).getPointCoverMoney());
        printerData.setCouponCoverMoney(CommonFunc.recoveryMemberInfo(this).getCouponCoverMoney());
        printerData.setPayType(Constants.PAY_WAY_QB);

        if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_SBS) {
            TransUploadRequest request = CommonFunc.setTransUploadData(printerData, CommonFunc.recoveryMemberInfo(this),
                    CommonFunc.getNewClientSn(this, printerData.getPayType()), printerData.getTransNo(), printerData.getAuthCode()
            );
            //这个地方保持和支付的时候一直
            request.setClientOrderNo(orderNo);
            transUploadAction1(request);
        } else if (CommonFunc.recoveryFailureInfo(this).getApp_type() == Config.APP_HD) {
            TransUploadRequest request = CommonFunc.setTransUploadData(printerData, CommonFunc.recoveryMemberInfo(this),
                    CommonFunc.getNewClientSn(this, printerData.getPayType()), printerData.getTransNo(), printerData.getAuthCode()
            );
            //这个地方保持和支付的时候一直
            request.setClientOrderNo(orderNo);
            transUploadAction2(request);
        }


    }

    /**
     * 流水上送
     *
     * @param request
     */
    private void transUploadAction1(final TransUploadRequest request) {
        final LoadingDialog dialog = new LoadingDialog(this);
        dialog.show("正在上传交易流水...");
        dialog.setCancelable(false);
        this.sbsAction.transUpload(this, request, new ActionCallbackListener<TransUploadResponse>() {
            @Override
            public void onSuccess(TransUploadResponse data) {

                setTransUpLoadData(request);
                // 设置流水返回的数据
                setTransUpdateResponse(data, dialog, true);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                dialog.dismiss();
                ToastUtils.CustomShow(SaleMainActivity.this, errorEvent + "#" + message);


                setTransUpLoadData(request);
                // 设置当前交易流水需要上送
                printerData.setUploadFlag(true);
                printerData.setApp_type(CommonFunc.recoveryFailureInfo(SaleMainActivity.this).getApp_type());
                // 保存打印的数据，不保存图片数据
                PrinterDataSave();
                // 打印
                Printer.print(printerData, SaleMainActivity.this);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                dialog.dismiss();
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity1.class, false);
                }
            }
        });
    }

    /**
     * 流水上送
     *
     * @param request
     */
    private void transUploadAction2(final TransUploadRequest request) {
        final LoadingDialog dialog = new LoadingDialog(this);
        dialog.show("正在计算积分...");
        dialog.setCancelable(false);
        this.sbsAction.transUpload(this, request, new ActionCallbackListener<TransUploadResponse>() {
            @Override
            public void onSuccess(TransUploadResponse data) {
                dialog.dismiss();
                setTransUpLoadData(request);
                printerData.setApp_type(CommonFunc.recoveryFailureInfo(SaleMainActivity.this).getApp_type());
                printerData.setPoint(data.getPoint());
                printerData.setPhoneNo(request.getPhone());
                // 保存打印的数据，不保存图片数据
                PrinterDataSave();
                // 打印
                Printer.print(printerData, SaleMainActivity.this);
                // 上送积分
//                HdAction.HdAdjustScore(SaleMainActivity.this, request.getPhone(), data.getPoint(), new HdAction.HdCallResult() {
//                    @Override
//                    public void onSuccess(String data) {
//
//                        HdAdjustScoreResponse response = new Gson().fromJson(data, HdAdjustScoreResponse.class);
//
//                        //保存流水号和总积分
//                        printerData.setPointCurrent(Integer.parseInt(response.getResult().getScoreTotal()));
//                        printerData.setFlowNo(response.getResult().getFlowNo());
//
//                        // 保存打印的数据，不保存图片数据
//                        PrinterDataSave();
//                        // 打印
//                        Printer.print(printerData, SaleMainActivity.this);
//                    }
//
//                    @Override
//                    public void onFailed(String errorCode, String message) {
//                        ToastUtils.CustomShow(SaleMainActivity.this, errorCode + "#" + message);
//                        // 保存打印的数据，不保存图片数据
//                        PrinterDataSave();
//                        // 打印
//                        Printer.print(printerData, SaleMainActivity.this);
//                    }
//                });
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                dialog.dismiss();
                ToastUtils.CustomShow(SaleMainActivity.this, errorEvent + "#" + message);


                setTransUpLoadData(request);
                // 设置当前交易流水需要上送
                printerData.setUploadFlag(true);
                printerData.setApp_type(CommonFunc.recoveryFailureInfo(SaleMainActivity.this).getApp_type());
                // 保存打印的数据，不保存图片数据
                PrinterDataSave();
                // 打印
                Printer.print(printerData, SaleMainActivity.this);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                dialog.dismiss();
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity1.class, false);
                }
            }
        });
    }


//    private void Richer_transUploadAction(final TransUploadRequest request) {
//        final LoadingDialog dialog = new LoadingDialog(this);
//        dialog.show("正在上传交易流水...");
//        dialog.setCancelable(false);
//
//        RicherQb.UploadTransInfo(SaleMainActivity.this, request, new ActionCallbackListener<RicherGetMember>() {
//            @Override
//            public void onSuccess(RicherGetMember data) {
//                dialog.dismiss();
//                setTransUpLoadData(request);
//                // 设置流水返回的数据
////                setTransUpdateResponse(data, dialog, true);
//                // 设置当前交易流水需要上送
//                printerData.setUploadFlag(false);
//
//                if (Config.isSign){
//                    final SignDialog dialog = new SignDialog(SaleMainActivity.this, new SignDialog.OnClickInterface() {
//                        @Override
//                        public void onClickSure(Bitmap bitmap) {
//                            printerData.setSign_bitmap(bitmap);
//                            PrinterDataSave();
//                            // 打印
//                            Printer.print(printerData, SaleMainActivity.this);
//                        }
//
//                    });
//                    dialog.setCancelable(false);
//                    dialog.show();
//                }else {
//
//                    PrinterDataSave();
//                    // 打印
//                    Printer.print(printerData, SaleMainActivity.this);
//                }
//            }
//
//
//            @Override
//            public void onFailure(String errorEvent, String message) {
//                dialog.dismiss();
//                ToastUtils.CustomShow(SaleMainActivity.this, errorEvent + "#" + message);
//
//
//                setTransUpLoadData(request);
//                // 设置当前交易流水需要上送
//                printerData.setUploadFlag(true);
//                // 保存打印的数据，不保存图片数据
//                PrinterDataSave();
//                // 打印
//                Printer.print(printerData, SaleMainActivity.this);
//            }
//
//            @Override
//            public void onLogin() {
//                AppManager.getAppManager().finishAllActivity();
//                if (Config.OPERATOR_UI_BEFORE) {
//                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity.class, false);
//                } else {
//                    CommonFunc.startAction(SaleMainActivity.this, OperatorLoginActivity1.class, false);
//                }
//            }
//
//            @Override
//            public void onFailurTimeOut(String s, String error_msg) {
//
//            }
//        });
//    }


    private void setTransUpLoadData(TransUploadRequest request) {
        Gson gson = new Gson();
        String data = gson.toJson(request);
        LogUtils.e(data);
        printerData.setTransUploadData(data);
    }


    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            Bitmap point_bitmap = bundle.getParcelable("point_bitmap");
            Bitmap title_bitmap = bundle.getParcelable("title_bitmap");
            printerData.setPoint_bitmap(point_bitmap);
            printerData.setCoupon_bitmap(title_bitmap);


            // 打印
            Printer.print(printerData, SaleMainActivity.this);

        }

        ;
    };


    protected void setTransUpdateResponse(final TransUploadResponse data, final LoadingDialog dialog, boolean flag) {
        printerData.setPoint_url(data.getPoint_url());
        printerData.setPoint(data.getPoint());
        printerData.setPointCurrent(data.getPointCurrent());
        printerData.setCoupon(data.getCoupon());
        printerData.setTitle_url(data.getTitle_url());
        printerData.setMoney(data.getMoney());
        printerData.setBackAmt(data.getBackAmt());
        printerData.setApp_type(CommonFunc.recoveryFailureInfo(this).getApp_type());
        if (flag) {
            // 保存打印的数据，不保存图片数据
            PrinterDataSave();
        }
        new Thread(new Runnable() {

            @Override
            public void run() {

                Bitmap point_bitmap = Constants.ImageLoad(data.getPoint_url());
                Bitmap title_bitmap = Constants.ImageLoad(data.getCoupon());
//				LogUtils.e(point_bitmap.getByteCount()+"");
//				LogUtils.e(title_bitmap.getByteCount()+"");
                dialog.dismiss();

                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putParcelable("point_bitmap", point_bitmap);
                bundle.putParcelable("title_bitmap", title_bitmap);
                msg.setData(bundle);
                mhandler.sendMessage(msg);

            }
        }).start();

    }

    private String getMenchantNo(int PayType) {
        String sm_type = MyApplication.getInstance().getLoginData().getScanPayType();
        String menchantNo = "";
        if (PayType == Constants.PAY_WAY_ALY || PayType == Constants.PAY_WAY_BFB || PayType == Constants.PAY_WAY_JD
                || PayType == Constants.PAY_WAY_WX) {
            if (!StringUtils.isEmpty(sm_type) && StringUtils.isEquals(sm_type, Constants.SM_TYPE_SQB)) {
                menchantNo = MyApplication.getInstance().getLoginData().getActivateCodeMerchantNo();
            } else {
                menchantNo = MyApplication.getInstance().getLoginData().getFyMerchantNo();
            }
        } else if (PayType == Constants.PAY_WAY_FLOT || PayType == Constants.PAY_WAY_CASH) {
            menchantNo = MyApplication.getInstance().getLoginData().getMerchantNo();
        } else if (PayType == Constants.PAY_WAY_QB) {
            menchantNo = printerData.getMerchantNo();
        }

        return menchantNo;
    }

    private String getAuthCode(int PayType) {
        String authCode = "";

        if (PayType == Constants.PAY_WAY_ALY || PayType == Constants.PAY_WAY_BFB || PayType == Constants.PAY_WAY_JD
                || PayType == Constants.PAY_WAY_WX || PayType == Constants.PAY_WAY_QB) {
            authCode = printerData.getAuthCode();
        } else if (PayType == Constants.PAY_WAY_FLOT) {
            authCode = printerData.getReferNo();
        }
        return authCode;
    }

    private String getClientOrderNo(int PayType) {
        String sm_type = MyApplication.getInstance().getLoginData().getScanPayType();
        String orderId = "";
        if (PayType == Constants.PAY_WAY_ALY || PayType == Constants.PAY_WAY_BFB || PayType == Constants.PAY_WAY_JD
                || PayType == Constants.PAY_WAY_WX) {
            if (!StringUtils.isEmpty(sm_type) && StringUtils.isEquals(sm_type, Constants.SM_TYPE_SQB)) {
//                orderId = bat.getMyOrderId();
            } else if (!StringUtils.isEmpty(sm_type) && StringUtils.isEquals(sm_type, Constants.SM_TYPE_FY)) {
                orderId = CommonFunc.getNewClientSn(this, PayType);
            }
        } else {
            orderId = CommonFunc.getNewClientSn(this, PayType);
        }

        return orderId;
    }

    private String getTransNo(int PayType) {
        String transNo = "";
        if (PayType == Constants.PAY_WAY_FLOT) {
            transNo = printerData.getVoucherNo();//AuthNo();// getReferNo();
        } else if (PayType == Constants.PAY_WAY_ALY || PayType == Constants.PAY_WAY_BFB
                || PayType == Constants.PAY_WAY_JD || PayType == Constants.PAY_WAY_WX ||
                PayType == Constants.PAY_WAY_QB) {
            transNo = printerData.getTransNo();
        }
        return transNo;
    }


    private void sendYxf(final SbsPrinterData recordData) {

        String money = recordData.getAmount();//"0.01";
        String mobile = recordData.getPhoneNo();//"13979328519";
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
//                dialog.dismiss();
                recordData.setApp_type(CommonFunc.recoveryFailureInfo(SaleMainActivity.this).getApp_type());
                try {
                    String state = response.getString("state");
                    String info = response.getString("info");
                    String qr_code = response.getString("qr_code");
                    if (StringUtils.isEquals(state, "0")) {
                        ToastUtils.CustomShow(SaleMainActivity.this, "上送成功");

                        //判断二维码链接是否为空，为空直接打印，不为空去下载
                        if (StringUtils.isEmpty(qr_code)) {
                            dialog.dismiss();

                            PrinterDataSave();
                            Printer.getInstance(SaleMainActivity.this).print(recordData, SaleMainActivity.this);
                        } else {
                            yxf_setTransUpdateResponse(recordData, qr_code, dialog, true);
                        }


                    } else {
                        dialog.dismiss();
                        ToastUtils.CustomShow(SaleMainActivity.this, !StringUtils.isEmpty(info) ? info : "上送失败");
                        recordData.setUploadFlag(false);
                        PrinterDataSave();
                        Printer.getInstance(SaleMainActivity.this).print(recordData, SaleMainActivity.this);
                    }


                } catch (JSONException e) {
//                    e.printStackTrace();
                    dialog.dismiss();
                    ToastUtils.CustomShow(SaleMainActivity.this, "返回数据解析失败");
                    recordData.setUploadFlag(false);
                    PrinterDataSave();
                    Printer.getInstance(SaleMainActivity.this).print(recordData, SaleMainActivity.this);
                }


            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                dialog.dismiss();
                LogUtils.e("sendYxf", error_msg);
                recordData.setApp_type(CommonFunc.recoveryFailureInfo(SaleMainActivity.this).getApp_type());
                recordData.setUploadFlag(false);
                PrinterDataSave();
                Printer.getInstance(SaleMainActivity.this).print(recordData, SaleMainActivity.this);

            }
        });
    }

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


    private void PrinterDataSave() {
        CommonFunc.ClearFailureInfo(this);
        CommonFunc.PrinterDataDelete();
        printerData.setStatus(true);
        if (printerData.save()) {
            LogUtils.e("打印数据存储成功");
        } else {
            LogUtils.e("打印数据存储失败");
        }
    }


    private boolean isCheckStatus() {

        if (!MyApplication.getInstance().getLoginData().isDownMasterKey()) {
//            DownMasterKey();
            ToastUtils.CustomShow(this, "请下载主密钥。。。");
            return false;
        }

        if (CommonFunc.isLogin(this, Constants.HS_LOGIN_TIME, Constants.DEFAULT_HS_LOGIN_TIME)) {
//            Hslogin();
            ToastUtils.CustomShow(this, "请签到。。。");
            return false;
        }

        return true;
    }
}
