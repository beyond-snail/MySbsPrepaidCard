package com.mysbsprepaidcard.zfsbs.core.action;


import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myokhttp.MyOkHttp;
import com.myokhttp.response.RawResponseHandler;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.model.FyMicropayRequest;
import com.mysbsprepaidcard.zfsbs.model.FyMicropayResponse;
import com.mysbsprepaidcard.zfsbs.model.FyQueryRequest;
import com.mysbsprepaidcard.zfsbs.model.FyQueryResponse;
import com.mysbsprepaidcard.zfsbs.model.FyRefundRequest;
import com.mysbsprepaidcard.zfsbs.model.FyRefundResponse;
import com.mysbsprepaidcard.zfsbs.model.LastQueryRequest;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.dialog.CustomTimingProgressDialog;
import com.tool.utils.dialog.LoadingDialog;
import com.tool.utils.utils.EncryptMD5Util;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.MapUtil;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import java.lang.reflect.Type;
import java.util.Map;


public class FyBat {
    private static final String TAG = "FyBat";
//    public static String FUSM_URL = "http://121.40.224.25:9091/ZFTiming/api/fyTrade/pay";
//    public static String FUQUERY_URL = "http://121.40.224.25:9091/ZFTiming/api/fyTrade/query";
//    public static String FUREFUND_URL = "http://121.40.224.25:9091/ZFTiming/api/fyTrade/refund";
//
//    public static String FUSM_URL = "http://192.168.1.253:8080/ZFTiming/api/fyTrade/pay";
//    public static String FUQUERY_URL = "http://192.168.1.253:8080/ZFTiming/api/fyTrade/query";
//    public static String FUREFUND_URL = "http://192.168.1.253:8080/ZFTiming/api/fyTrade/refund";
    //富友 公私钥
//    public static String FY_RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALi+gZ6+Y3UAao1IGkVPKVGeqdA+j0i4ebwJ4Fp+7Sn7Ys0TzO6X3maH01MzsgVVk/26QUf3QTvifXlZoDGp447oCB1SelZDRrVD69Bl/5unqH9BCbOweJqrWvb+OzzWRRpBHWhIqPPdbIFgm675BMR+7rxB+pvMxy6/GzopoYUvAgMBAAECgYB5MyowbbEsCijdJUdu3v85d8DqSJCR4cyja0tPs2N+Hlj0N6BDi0ixtTwTop+Q1lLvq2i2gOTAF9e/a+gnjNAnS2YuoUpGKgKgKxcWs/RZLrV98AShaMLuiHuUpVPovCMpY02OzY/bvlnDZng8/ON4xrygHtSq1Hc3FaLCqRcuMQJBAO2PD7xvecVNRLkMyRxfVlTwGDB7jrI2WpLqQ1Xh4orWpkzr+tu8Hod65eoiMdcslHw4QJJ3wadLX+Jg9jLQj9kCQQDHFd/Flk41lChu3f5hQH86KUi4GLg1Pl3SfWa66rSvTrhRJ/EZ+r2QvQDqKRY2o8NSXY7tQdu6vJ86yuxrsqBHAkBX/CGW6C7QraKjayHdeU4PXXGIG2sphEodmdhgqa3vQDsNyGT8F3uzMAiRpCKTkHZaX7dCyEoYPSmBPepRvb9ZAkA5HORaOblsE3nZ/GOKoMce91L+RVErLR4bUZBUFRsKo8mqtourSUxyplYJ1wmhWS+ihGaJV/hiRNRlOGvEpbTnAkAlwLoIdf3R4tH3uV2tE9l5WpJ75fxNd2Atl/ED1o78O3W6QOXJo/ulfByQmV3laUM5U+8OFynVwck2xEmt0Tzp";
//    public static String FY_RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4voGevmN1AGqNSBpFTylRnqnQPo9IuHm8CeBafu0p+2LNE8zul95mh9NTM7IFVZP9ukFH90E74n15WaAxqeOO6AgdUnpWQ0a1Q+vQZf+bp6h/QQmzsHiaq1r2/js81kUaQR1oSKjz3WyBYJuu+QTEfu68QfqbzMcuvxs6KaGFLwIDAQAB";

    private Context mContext;
    private boolean isPaying = false; // 查询标识次数
    private String pay_type;
    private String order_no; //订单号

    private FYPayResultEvent fyPayResultEvent;

    private CustomTimingProgressDialog Timedialog;

    public FyBat(Context context, FYPayResultEvent fyPayResultEvent) {
        this.mContext = context;
        this.fyPayResultEvent = fyPayResultEvent;
    }

    public interface FYPayResultEvent{
        void onSuccess(FyMicropayResponse data);
        void onSuccess(FyQueryResponse data);
        void onSuccess(FyRefundResponse data);
        void onFailure(int statusCode, String error_msg, String pay_type, String amount);
//        void onFailure(String pay_type, String amount);
        void onFailure(FyMicropayRequest data);
        void onFailure(FyQueryRequest data);
        void onLogin();
    }


    private void CloseTimeDialog(){
        if (Timedialog != null){
            Timedialog.dialogDismiss();
            Timedialog = null;
        }
    }


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // do something
            if (isPaying) {
                query(pay_type, order_no);
            } else {
                CloseTimeDialog();

                fyPayResultEvent.onFailure(recoveryTermialData());
                ToastUtils.CustomShow(mContext, "交易未知");
                handler.removeCallbacks(this);
            }
        }
    };






    /**
     * 富友扫码支付
     *
     * @param result   （条码信息）
     * @param type     （支付方式：ALIPAY , WECHAT）
     * @param amount   （金额：分单位）
     */
    public void pay1(final String result, String type, String outOrderNo, final int amount){//, final ActionCallbackListener<FyMicropayResponse> listener) {
        final FyMicropayRequest request = new FyMicropayRequest();

        request.setVersion("");//("1.0");
//        request.setIns_cd((String) SPUtils.get(mContext, Constants.FY_INS_CD, Constants.DEFAULT_INS_CD));
//        request.setMchnt_cd((String) SPUtils.get(mContext, Constants.FY_MERCHANT_NO, Constants.DEFAULT_FY_MERCHANT_NO));
        request.setMchnt_cd(MyApplication.getInstance().getLoginData().getFyMerchantNo());
        request.setTerm_id(StringUtils.getTerminalNo(StringUtils.getSerial()));
        request.setOutOrderNum(outOrderNo);
        request.setRandom_str("");//(StringUtils.createRandomStr(32));
        request.setType(type);
        request.setGoods_des("消费");
        request.setGoods_detail("消费物品");
        request.setAddn_inf("");
        request.setMchnt_order_no("");//(outTradeNo);
        request.setCurr_type("");
        request.setAmount(amount);
        request.setTerm_ip("");//(NetUtils.getLocalIpAddress(mContext));
        request.setTxn_begin_ts("");//(StringUtils.getFormatCurTime());
        request.setGoods_tag("");
        request.setAuth_code(result);
        request.setSence("");//("1");
        request.setReserved_sub_appid("");
        request.setReserved_limit_pay("");

        pay_type = type;
        isPaying = true;

        // 除去sign组成map 按字典排序
        Map<String, String> tempMap = MapUtil
                .order(MapUtil.objectToMap(request, "sign", "reserved_sub_appid", "reserved_limit_pay"));
        MapUtil.removeNullValue(tempMap);
        String src = MapUtil.mapJoin(tempMap, false, false);
        String signRes = EncryptMD5Util.MD5(src);
        tempMap.put("sign", signRes);
        LogUtils.e("tempMap", tempMap.toString());
        String url = "http://"+ SPUtils.get(mContext, Constants.FY_IP, Constants.DEFAULT_FY_IP)+"/ZFTiming/api/fyTrade/pay";
        LogUtils.e("url", url);

        Timedialog = new CustomTimingProgressDialog(mContext, new CustomTimingProgressDialog.DialogDismissEvent() {
            @Override
            public void dialogDismiss() {
                isPaying = false;

            }
        });

        Timedialog.setCancelable(false);
        Timedialog.show();

        MyOkHttp.get().post2(mContext, url, tempMap, new RawResponseHandler() {

            @Override
            public void onFailure(int statusCode, String error_msg) {
                LogUtils.e(TAG, statusCode + "#" + error_msg);
                CloseTimeDialog();
                ToastUtils.CustomShow(mContext, statusCode + "" + error_msg);
//                saveTermialData(amount+"", pay_type,true);
                fyPayResultEvent.onFailure(request);
            }

            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.e(TAG, statusCode + "#" + response);
                if (statusCode == 200 && !StringUtils.isEmpty(response)) {
                    Type type = new TypeToken<FyMicropayResponse>() {
                    }.getType();
                    Gson gson = new Gson();
                    FyMicropayResponse result = gson.fromJson(response, type);
                    if (result.getResult_code().equals("000000")) {
//                        saveTermialData(amount+"", pay_type);
                        CloseTimeDialog();
//                        ToastUtils.CustomShow(mContext, "交易成功");
                        fyPayResultEvent.onSuccess(result);
                    }
                    else if (result.getResult_code().equals("030010")){ //用户使用支付密码
//                        saveTermialData(amount+"", pay_type, true);
                        order_no = result.getMchnt_order_no();
                        handler.postDelayed(runnable, 5000);
                    }
                    else {
                        CloseTimeDialog();
                        ToastUtils.CustomShow(mContext, result.getResult_code()+"#"+result.getResult_msg());
//                        saveTermialData(amount+"", pay_type);
//                        fyPayResultEvent.onFailure(request);
                    }
                } else {
                    CloseTimeDialog();
                    ToastUtils.CustomShow(mContext, statusCode + "#" + response);
//                    saveTermialData(amount+"", pay_type, true);
                    fyPayResultEvent.onFailure(request);
                }
            }
        });


    }


    /**
     * 保存末笔的数据
     */
    private void setBackTermialData(FyQueryRequest request) {
        Gson gson = new Gson();
        String data = gson.toJson(request);
        LogUtils.e(TAG, data);
        SPUtils.put(mContext, Constants.TERMINAL_QUERY, data);
    }

    private FyQueryRequest recoveryTermialData(){
        String backpu_currentMember = (String) SPUtils.get(mContext, Constants.TERMINAL_QUERY, "");
        return StringUtils.isEmpty(backpu_currentMember) ? null : new Gson().fromJson(backpu_currentMember, FyQueryRequest.class);
    }





    /**
     * 订单查询（富友）
     *
     * @param type       （支付方式：ALIPAY , WECHAT）
     * @param outTradeNo （商户订单号）
     */
    public void query(final String type, final String outTradeNo){//, final ActionCallbackListener<FyQueryResponse> listener) {
        final FyQueryRequest request = new FyQueryRequest();
        request.setVersion("");//("1.0");
//        request.setIns_cd((String) SPUtils.get(mContext, Constants.FY_INS_CD, Constants.DEFAULT_INS_CD));
//        request.setMchnt_cd((String) SPUtils.get(mContext, Constants.FY_MERCHANT_NO, Constants.DEFAULT_FY_MERCHANT_NO));
        request.setMchnt_cd(MyApplication.getInstance().getLoginData().getFyMerchantNo());
        request.setTerm_id(StringUtils.getTerminalNo(StringUtils.getSerial()));
        request.setOrder_type(type);
        request.setMchnt_order_no(outTradeNo);
        request.setRandom_str("");//(StringUtils.createRandomStr(32));



        // 除去sign组成map 按字典排序
        Map<String, String> tempMap = MapUtil
                .order(MapUtil.objectToMap(request, "sign", "reserved_sub_appid", "reserved_limit_pay"));
        MapUtil.removeNullValue(tempMap);
        String src = MapUtil.mapJoin(tempMap, false, false);
        String signRes = EncryptMD5Util.MD5(src);
        tempMap.put("sign", signRes);
        LogUtils.e("tempMap", tempMap.toString());

        String url = "http://"+ SPUtils.get(mContext, Constants.FY_IP, Constants.DEFAULT_FY_IP)+"/ZFTiming/api/fyTrade/query";
        LogUtils.e("url", url);
        MyOkHttp.get().post(mContext, url, tempMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.e(TAG, statusCode + "#" + response);
                if (statusCode == 200 && !StringUtils.isEmpty(response)) {
                    Type type = new TypeToken<FyQueryResponse>() {
                    }.getType();
                    Gson gson = new Gson();
                    FyQueryResponse result = gson.fromJson(response, type);
                    if (result.getResult_code().equals("000000")) {
                        if (result.getTrans_stat().equals("SUCCESS")) {
                            CloseTimeDialog();
//                            ToastUtils.CustomShow(mContext, "交易成功");
                            fyPayResultEvent.onSuccess(result);
                        } else { //用户支付中。。
                            handler.postDelayed(runnable, 5000);
                            setBackTermialData(request);
                        }
                    }else {
                        CloseTimeDialog();
                        if (result.getResult_code().equals("999999") && result.getTrans_stat().equals("PAYERROR")){
                            ToastUtils.CustomShow(mContext, "用户未支付");
                        }else {
                            ToastUtils.CustomShow(mContext, result.getResult_code()+"#"+result.getResult_msg());
                        }

                        fyPayResultEvent.onFailure(request);
                    }
                } else {
                    CloseTimeDialog();
                    ToastUtils.CustomShow(mContext, statusCode+"#"+response);
                    fyPayResultEvent.onFailure(request);
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                CloseTimeDialog();
                ToastUtils.CustomShow(mContext, statusCode+"#"+error_msg);
                fyPayResultEvent.onFailure(request);

            }
        });


    }


    /**
     * 订单查询（富友）
     *
     * @param type       （支付方式：ALIPAY , WECHAT）
     * @param outTradeNo （商户订单号）
     */
    public void query1(Context context, final String type, final String outTradeNo, final String outOrderNo){//, final ActionCallbackListener<FyQueryResponse> listener) {

        final LoadingDialog dialog = new LoadingDialog(context);
        dialog.show("正在查询...");
        dialog.setCancelable(false);


        final FyQueryRequest request = new FyQueryRequest();
        request.setVersion("");//("1.0");
//        request.setIns_cd((String) SPUtils.get(mContext, Constants.FY_INS_CD, Constants.DEFAULT_INS_CD));
//        request.setMchnt_cd((String) SPUtils.get(mContext, Constants.FY_MERCHANT_NO, Constants.DEFAULT_FY_MERCHANT_NO));
        request.setMchnt_cd(MyApplication.getInstance().getLoginData().getFyMerchantNo());
        request.setTerm_id(StringUtils.getTerminalNo(StringUtils.getSerial()));
        request.setOrder_type(type);
        request.setMchnt_order_no(outTradeNo);
        request.setRandom_str("");//(StringUtils.createRandomStr(32));
        request.setOutOrderNum(outOrderNo);


        // 除去sign组成map 按字典排序
        Map<String, String> tempMap = MapUtil
                .order(MapUtil.objectToMap(request, "sign", "reserved_sub_appid", "reserved_limit_pay"));
        MapUtil.removeNullValue(tempMap);
        String src = MapUtil.mapJoin(tempMap, false, false);
        String signRes = EncryptMD5Util.MD5(src);
        tempMap.put("sign", signRes);
        LogUtils.e("tempMap", tempMap.toString());

        String url = "http://"+ SPUtils.get(mContext, Constants.FY_IP, Constants.DEFAULT_FY_IP)+"/ZFTiming/api/fyTrade/query";
        LogUtils.e("url", url);
        MyOkHttp.get().post(mContext, url, tempMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                dialog.dismiss();
                LogUtils.e(TAG, statusCode + "#" + response);
                if (statusCode == 200 && !StringUtils.isEmpty(response)) {
                    Type type = new TypeToken<FyQueryResponse>() {
                    }.getType();
                    Gson gson = new Gson();
                    FyQueryResponse result = gson.fromJson(response, type);
                    if (result.getResult_code().equals("000000")) {
                        if (result.getTrans_stat().equals("SUCCESS")) {
//                            CloseTimeDialog();
//                            ToastUtils.CustomShow(mContext, "交易成功");
                            fyPayResultEvent.onSuccess(result);
                        } else { //用户支付中。。
                            ToastUtils.CustomShow(mContext, result.getResult_code()+"#"+result.getResult_msg());
                        }
                    }else {
                        if (result.getResult_code().equals("999999") && result.getTrans_stat().equals("PAYERROR")){
                            ToastUtils.CustomShow(mContext, "用户未支付");
                        }else {
                            ToastUtils.CustomShow(mContext, result.getResult_code()+"#"+result.getResult_msg());
                        }
//                        fyPayResultEvent.onFailure(request);
                    }
                } else {
                    ToastUtils.CustomShow(mContext, statusCode+"#"+response);
//                    fyPayResultEvent.onFailure(request);
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                dialog.dismiss();
                ToastUtils.CustomShow(mContext, statusCode+"#"+error_msg);
//                fyPayResultEvent.onFailure(request);

            }
        });


    }


    public void refund(String type, String mid, String outTradeNo, int amount){//, final ActionCallbackListener<FyRefundResponse> listener) {
//        outTradeNo = order_no;
        FyRefundRequest request = new FyRefundRequest();
        request.setVersion("");//("1.0");
//        request.setIns_cd((String) SPUtils.get(mContext, Constants.FY_INS_CD, Constants.DEFAULT_INS_CD));
//        request.setMchnt_cd((String) SPUtils.get(mContext, Constants.FY_MERCHANT_NO, Constants.DEFAULT_FY_MERCHANT_NO));
        request.setMchnt_cd(mid);
        request.setTerm_id(StringUtils.getTerminalNo(StringUtils.getSerial()));
        request.setMchnt_order_no(outTradeNo);
        request.setRandom_str("");//(StringUtils.createRandomStr(32));
        request.setOrder_type(type);
        request.setRefund_order_no("");//(StringUtils.createOrderNo(null));
        request.setAmount(amount);
        request.setRefund_amt(amount);
        request.setOperator_id("");


//        //测试用
//        request.setVersion("");//("1.0");
//        request.setIns_cd("08M0064989");
//        request.setMchnt_cd("0002900F0336730");
//        request.setTerm_id(StringUtils.getTerminalNo(android.os.Build.SERIAL));
//        request.setMchnt_order_no("201702161026317313489");
//        request.setRandom_str("");//(StringUtils.createRandomStr(32));
//        request.setOrder_type(type);
//        request.setRefund_order_no("");//(StringUtils.createOrderNo(null));
//        request.setAmount(501);
//        request.setRefund_amt(501);
//        request.setOperator_id("");

        final LoadingDialog dialog = new LoadingDialog(mContext);
        dialog.show("正在退款...");
        dialog.setCancelable(false);


        // 除去sign组成map 按字典排序
        Map<String, String> tempMap = MapUtil
                .order(MapUtil.objectToMap(request, "sign", "reserved_sub_appid", "reserved_limit_pay"));
        MapUtil.removeNullValue(tempMap);
        String src = MapUtil.mapJoin(tempMap, false, false);
        String signRes = EncryptMD5Util.MD5(src);
        tempMap.put("sign", signRes);
        LogUtils.e("tempMap", tempMap.toString());

        String url = "http://"+ SPUtils.get(mContext, Constants.FY_IP, Constants.DEFAULT_FY_IP)+"/ZFTiming/api/fyTrade/refund";
        LogUtils.e("url", url);

        MyOkHttp.get().post(mContext, url, tempMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                dialog.dismiss();
                LogUtils.e(TAG, statusCode + "#" + response);
                if (statusCode == 200 && !StringUtils.isEmpty(response)) {
                    Type type = new TypeToken<FyRefundResponse>() {
                    }.getType();
                    Gson gson = new Gson();
                    FyRefundResponse result = gson.fromJson(response, type);
                    if (result.getResult_code().equals("000000")) {
                        ToastUtils.CustomShow(mContext, "退款成功");
                        fyPayResultEvent.onSuccess(result);
                    } else {
                        ToastUtils.CustomShow(mContext, result.getResult_code()+"#"+result.getResult_msg());
                    }
                } else {
                    ToastUtils.CustomShow(mContext, statusCode+"#"+response);
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                dialog.dismiss();
                ToastUtils.CustomShow(mContext, statusCode+"#"+error_msg);
            }
        });


    }


    /**
     * 订单查询（富友）
     *
     * @param type       （支付方式：ALIPAY , WECHAT）
     */
    public void terminalQuery(final String type, final String amount, final boolean isCallback){//, final ActionCallbackListener<FyQueryResponse> listener) {

        final LoadingDialog dialog = new LoadingDialog(mContext);
        dialog.show("正在末笔查询...");
        dialog.setCancelable(false);

        LastQueryRequest lastQuery = new LastQueryRequest();
        lastQuery.setAmount(amount);
        lastQuery.setMchnt_cd(MyApplication.getInstance().getLoginData().getFyMerchantNo());
        lastQuery.setOrder_type(type);
        lastQuery.setTerm_id(StringUtils.getTerminalNo(StringUtils.getSerial()));

        // 除去sign组成map 按字典排序
        Map<String, String> tempMap = MapUtil
                .order(MapUtil.objectToMap(lastQuery, "sign", "reserved_sub_appid", "reserved_limit_pay"));
        MapUtil.removeNullValue(tempMap);
        String src = MapUtil.mapJoin(tempMap, false, false);
        String signRes = EncryptMD5Util.MD5(src);
        tempMap.put("sign", signRes);
        LogUtils.e("tempMap", tempMap.toString());


        String url = "http://"+ SPUtils.get(mContext, Constants.FY_IP, Constants.DEFAULT_FY_IP)+"/ZFTiming/api/fyTrade/lastQuery";
        LogUtils.e("url", url);
        MyOkHttp.get().post(mContext, url, tempMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.e(TAG, statusCode + "#" + response);
                dialog.dismiss();
                if (statusCode == 200 && !StringUtils.isEmpty(response)) {
                    Type type = new TypeToken<FyQueryResponse>() {
                    }.getType();
                    Gson gson = new Gson();
                    FyQueryResponse result = gson.fromJson(response, type);
                    if (result.getResult_code().equals("000000")) {
                        if (result.getTrans_stat().equals("SUCCESS")) {

//                            ToastUtils.CustomShow(mContext, "交易成功");
//                            saveTermialData(amount+"", pay_type, false);
                            if (isCallback) {
                                fyPayResultEvent.onSuccess(result);
                            }
                        } else {
                            ToastUtils.CustomShow(mContext, "交易失败");
                        }
                    }else {
                        ToastUtils.CustomShow(mContext, result.getResult_code()+"#"+result.getResult_msg());
                    }
                } else {
                    ToastUtils.CustomShow(mContext, statusCode+"#"+response);
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.CustomShow(mContext, statusCode+"#"+error_msg);
                dialog.dismiss();
            }
        });


    }


    /**
     * 订单查询（富友）
     *
     * @param type       （支付方式：ALIPAY , WECHAT）
     */
    public void terminalQuery1(final String type, final String amount, final boolean isCallback, final String outOrderNo){//, final ActionCallbackListener<FyQueryResponse> listener) {

        final LoadingDialog dialog = new LoadingDialog(mContext);
        dialog.show("正在末笔查询...");
        dialog.setCancelable(false);

        LastQueryRequest lastQuery = new LastQueryRequest();
        lastQuery.setAmount(amount);
        lastQuery.setMchnt_cd(MyApplication.getInstance().getLoginData().getFyMerchantNo());
        lastQuery.setOrder_type(type);
        lastQuery.setTerm_id(StringUtils.getTerminalNo(StringUtils.getSerial()));
        lastQuery.setOutOrderNum(outOrderNo);
        // 除去sign组成map 按字典排序
        Map<String, String> tempMap = MapUtil
                .order(MapUtil.objectToMap(lastQuery, "sign", "status", "reserved_sub_appid", "reserved_limit_pay"));
        MapUtil.removeNullValue(tempMap);
        String src = MapUtil.mapJoin(tempMap, false, false);
        LogUtils.e("before", src);
        String signRes = EncryptMD5Util.MD5(src);
        LogUtils.e("after", signRes);
        tempMap.put("sign", signRes);
        LogUtils.e("tempMap", tempMap.toString());


        String url = "http://"+ SPUtils.get(mContext, Constants.FY_IP, Constants.DEFAULT_FY_IP)+"/ZFTiming/api/fyTrade/lastQuery";
        LogUtils.e("url", url);
        MyOkHttp.get().post(mContext, url, tempMap, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                LogUtils.e(TAG, statusCode + "#" + response);
                dialog.dismiss();
                if (statusCode == 200 && !StringUtils.isEmpty(response)) {
                    Type type = new TypeToken<FyQueryResponse>() {
                    }.getType();
                    Gson gson = new Gson();
                    FyQueryResponse result = gson.fromJson(response, type);
                    if (result.getResult_code().equals("000000")) {
                        if (result.getTrans_stat().equals("SUCCESS")) {

//                            ToastUtils.CustomShow(mContext, "交易成功");
                            if (isCallback) {
                                fyPayResultEvent.onSuccess(result);
                            }
                        } else {
                            ToastUtils.CustomShow(mContext, "交易失败");
                        }
                    }else {
                        ToastUtils.CustomShow(mContext, result.getResult_code()+"#"+result.getResult_msg());
                    }
                } else {
                    ToastUtils.CustomShow(mContext, statusCode+"#"+response);
                }

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.CustomShow(mContext, statusCode+"#"+error_msg);
                dialog.dismiss();
            }
        });


    }

}
