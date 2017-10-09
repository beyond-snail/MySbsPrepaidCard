package com.mysbsprepaidcard.zfsbs.core.action;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//              佛祖保佑       永无BUG     永不修改                  //
//                                                                //
//          佛曰:                                                  //
//                  写字楼里写字间，写字间里程序员；                   //
//                  程序人员写程序，又拿程序换酒钱。                   //
//                  酒醒只在网上坐，酒醉还来网下眠；                   //
//                  酒醉酒醒日复日，网上网下年复年。                   //
//                  但愿老死电脑间，不愿鞠躬老板前；                   //
//                  奔驰宝马贵者趣，公交自行程序员。                   //
//                  别人笑我忒疯癫，我笑自己命太贱；                   //
//                  不见满街漂亮妹，哪个归得程序员？                   //
////////////////////////////////////////////////////////////////////

import android.content.Context;
import android.os.Handler;

import com.myokhttp.MyOkHttp;
import com.myokhttp.response.GsonResponseHandler;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Config;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.ApiResponse;
import com.mysbsprepaidcard.zfsbs.model.ZfQbResponse;
import com.tool.utils.dialog.CustomTimingProgressDialog;
import com.tool.utils.dialog.LoadingDialog;
import com.tool.utils.utils.EncryptMD5Util;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**********************************************************
 * *
 * Created by wucongpeng on 2016/11/15.        *
 **********************************************************/


public class ZfQbAction {

    private QbPayResultEvent QbPayResultEvent;

    private CustomTimingProgressDialog Timedialog;
    ActionCallbackListener<ZfQbResponse> mListener;

    private Context mContext;
    private boolean isPaying = false; // 查询标识次数
    private String order_no; //订单号
    private String old_time; //原交易时间
    private int old_sid;

    public interface QbPayResultEvent{
        void onSuccess(ZfQbResponse data);
        void onFailure(int statusCode, String error_msg);
    }

    public ZfQbAction(Context context) {
        this.mContext = context;
    }


    private void CloseTimeDialog(){
        if (Timedialog != null){
            Timedialog.dialogDismiss();
        }
    }


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // do something
            if (isPaying) {
                query(old_sid, order_no, old_time, mListener);
            } else {
                ToastUtils.CustomShow(mContext, "交易失败");
                CloseTimeDialog();
//                handler.removeCallbacks(this);
//                mListener.onFailure("", "交易失败");
            }
        }
    };

    /**
     * 商博士-钱包支付
     * @param sid
     * @param orderNo
     * @param amount
     * @param qrCode
     * @param listener
     */
    public void qbAction(final int sid, final String orderNo, String amount, final String time, String traceNum, String qrCode, final ActionCallbackListener<ZfQbResponse> listener){
//        final LoadingDialog dialog = new LoadingDialog(context);
//        dialog.show("正在钱包支付...");

        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put("TranCode", "9448");
        paramsMap.put("QrCode", qrCode);
        paramsMap.put("MerchantId", sid);
        paramsMap.put("TerminalNo", StringUtils.getSerial());
        paramsMap.put("OrgOrderNum", orderNo);
        paramsMap.put("OrgTranDateTime", time);
        paramsMap.put("SysTraceNum", traceNum);
        paramsMap.put("TranAmt", amount);
        paramsMap.put("OrderCurrency", "156");

        isPaying = true;
        //特殊处理了下
//        String data = getJsonStr("qbPay", paramsMap, "verify", SbsAction.md5_key, "TerminalNo");
        String data = CommonFunc.getJsonStr("qbPay", paramsMap, "verify", Config.md5_key);
        LogUtils.e("request", data);
        LogUtils.e("URL", Config.SBS_URL_QB);

        Timedialog = new CustomTimingProgressDialog(mContext, new CustomTimingProgressDialog.DialogDismissEvent() {
            @Override
            public void dialogDismiss() {
                isPaying = false;
            }
        });
        Timedialog.show();
        Timedialog.setCancelable(false);


        MyOkHttp.get().postJson(mContext, Config.SBS_URL_QB, data, new GsonResponseHandler<ApiResponse<ZfQbResponse>>() {
            @Override
            public void onSuccess(int statusCode, ApiResponse<ZfQbResponse> response) {
                LogUtils.e("response", response.toString());
//                dialog.dismiss();
                if (response != null) {
                    if (response.getCode().equals("A00006")) {
                        if (StringUtils.isEquals(response.getResult().getTransState(), "0000")){
                            CloseTimeDialog();
                            listener.onSuccess(response.getResult());
                        }else{
                            order_no = orderNo;
                            old_time = time;
                            old_sid = sid;
                            mListener = listener;
                            handler.postDelayed(runnable, 5000);
//                            ToastUtils.CustomShow(mContext, response.getResult().getMsgTxt());
//                            CloseTimeDialog();
                        }

                    } else {
                        CloseTimeDialog();
                        listener.onFailure(response.getCode(), response.getMsg());
                    }
                } else {
                    CloseTimeDialog();
                    listener.onFailure("", "链接服务器异常");
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
//                dialog.dismiss();
                CloseTimeDialog();
                listener.onFailure("" + statusCode, error_msg);
            }

        });
    }


    /**
     * 商博士-钱包支付
     * @param sid
     * @param orderNo
     * @param amount
     * @param qrCode
     * @param listener
     */
    public void qbAction1(final int sid, final String orderNo, String amount, final String time, String traceNum, String qrCode, final ActionCallbackListener<ZfQbResponse> listener){
//        final LoadingDialog dialog = new LoadingDialog(context);
//        dialog.show("正在钱包支付...");

        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put("TranCode", "9448");
        paramsMap.put("QrCode", qrCode);
        paramsMap.put("MerchantId", sid);
        paramsMap.put("TerminalNo", StringUtils.getSerial());
        paramsMap.put("OrgOrderNum", orderNo);
        paramsMap.put("OrgTranDateTime", time);
        paramsMap.put("SysTraceNum", traceNum);
        paramsMap.put("TranAmt", amount);
        paramsMap.put("OrderCurrency", "156");
        paramsMap.put("operator_num", SPUtils.get(mContext, Constants.USER_NAME, ""));
        isPaying = true;
        //特殊处理了下
//        String data = getJsonStr("qbPay", paramsMap, "verify", SbsAction.md5_key, "TerminalNo");
        String data = CommonFunc.getJsonStr("qbPay", paramsMap, "verify", Config.md5_key);
//        LogUtils.e("request", data);
        LogUtils.e("URL", Config.SBS_URL_QB);
//        ALog.json(data);

        Timedialog = new CustomTimingProgressDialog(mContext, new CustomTimingProgressDialog.DialogDismissEvent() {
            @Override
            public void dialogDismiss() {
                isPaying = false;
            }
        });
        Timedialog.show();
        Timedialog.setCancelable(false);


        MyOkHttp.get().postJson(mContext, Config.SBS_URL_QB, data, new GsonResponseHandler<ApiResponse<ZfQbResponse>>() {
            @Override
            public void onSuccess(int statusCode, ApiResponse<ZfQbResponse> response) {
//                LogUtils.e("response", response.toString());
//                ALog.json(response.toString());
//                dialog.dismiss();
                if (response != null) {
                    if (response.getCode().equals("A00006")) {
                        if (StringUtils.isEquals(response.getResult().getTransState(), "0000")){
                            CloseTimeDialog();
                            listener.onSuccess(response.getResult());
                        }else{
                            order_no = orderNo;
                            old_time = time;
                            old_sid = sid;
                            mListener = listener;
                            handler.postDelayed(runnable, 5000);
//                            ToastUtils.CustomShow(mContext, response.getResult().getMsgTxt());
//                            CloseTimeDialog();
                        }

                    } else {
                        CloseTimeDialog();
                        listener.onFailure(response.getCode(), response.getMsg());
                    }
                } else {
                    CloseTimeDialog();
                    listener.onFailure("", "链接服务器异常");
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
//                dialog.dismiss();
                CloseTimeDialog();
                listener.onFailurTimeOut("" + statusCode, error_msg);
            }

        });
    }


    public void query(int sid, String orderNo, String time, final ActionCallbackListener<ZfQbResponse> listener){

        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put("MerchantId", sid);
        paramsMap.put("TerminalNo", StringUtils.getSerial());
        paramsMap.put("OrgOrderNum", orderNo);
        paramsMap.put("OrgTranDateTime", time);
        paramsMap.put("operator_num", SPUtils.get(mContext, Constants.USER_NAME, ""));

        String data = CommonFunc.getJsonStr("qbQuery", paramsMap, "verify", Config.md5_key);
//        LogUtils.e("request", data);
        LogUtils.e("URL", Config.SBS_URL_QB);
//        ALog.json(data);



        MyOkHttp.get().postJson(mContext, Config.SBS_URL_QB, data, new GsonResponseHandler<ApiResponse<ZfQbResponse>>() {
            @Override
            public void onSuccess(int statusCode, ApiResponse<ZfQbResponse> response) {
//                LogUtils.e("response", response.toString());
//                ALog.json(response.toString());
//                dialog.dismiss();
                if (response != null) {
                    if (response.getCode().equals("A00006")) {
                        if (StringUtils.isEquals(response.getResult().getTransState(), "0000")){
                            CloseTimeDialog();
                            listener.onSuccess(response.getResult());
                        }else{

                            handler.postDelayed(runnable, 5000);
                        }

                    } else {
                        CloseTimeDialog();
                        listener.onFailure(response.getCode(), response.getMsg());
                    }
                } else {
                    CloseTimeDialog();
                    listener.onFailure("", "链接服务器异常");
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
//                dialog.dismiss();
                CloseTimeDialog();
                listener.onFailure("" + statusCode, error_msg);
            }

        });
    }


    public void query1(Context context, int sid, String orderNo, String time, final ActionCallbackListener<ZfQbResponse> listener){

        final LoadingDialog dialog = new LoadingDialog(context);
        dialog.show("正在查询...");
        dialog.setCancelable(false);

        Map<String, Object> paramsMap = new HashMap<String, Object>();

        paramsMap.put("MerchantId", sid);
        paramsMap.put("TerminalNo", StringUtils.getSerial());
        paramsMap.put("OrgOrderNum", orderNo);
        paramsMap.put("OrgTranDateTime", time);
        paramsMap.put("operator_num", SPUtils.get(context, Constants.USER_NAME, ""));

        String data = CommonFunc.getJsonStr("qbQuery", paramsMap, "verify", Config.md5_key);
//        LogUtils.e("request", data);
        LogUtils.e("URL", Config.SBS_URL_QB);
//        ALog.json(data);



        MyOkHttp.get().postJson(mContext, Config.SBS_URL_QB, data, new GsonResponseHandler<ApiResponse<ZfQbResponse>>() {
            @Override
            public void onSuccess(int statusCode, ApiResponse<ZfQbResponse> response) {
//                LogUtils.e("response", response.toString());
//                ALog.json(response.toString());
                dialog.dismiss();
                if (response != null) {
                    if (response.getCode().equals("A00006")) {
                        if (StringUtils.isEquals(response.getResult().getTransState(), "0000")) {
                            listener.onSuccess(response.getResult());
                        }else {
                            listener.onFailure(response.getCode(), response.getMsg());
                        }

                    } else {
                        listener.onFailure(response.getCode(), response.getMsg());
                    }
                } else {
                    listener.onFailure("", "链接服务器异常");
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                dialog.dismiss();
                listener.onFailure("" + statusCode, error_msg);
            }

        });
    }


    private String getJsonStr(String cmd, Map<String, Object> paramsMap, String sign, String key, String terminalId) {
        JSONObject jsonParams = null;

        ArrayList<String> keys = new ArrayList<String>();
        for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext(); ) {
            keys.add(it.next());
        }
        String tmp = "";
        keys = CommonFunc.getSortAsc(keys);
        for (int i = 0; i < keys.size(); i++) {
            Object obj = paramsMap.get(keys.get(i));
            if (obj != null) {
                tmp = tmp + obj.toString();
            } else {
                LogUtils.e("getJsonStr", "getSortAsc obj is null");
            }
        }

        String verify = EncryptMD5Util.MD5(tmp + key);
        paramsMap.put(sign, verify);
        if (!StringUtils.isEmpty(terminalId)){
            paramsMap.put(terminalId, StringUtils.getSerial());
        }


        Map<String, Object> final_params = new HashMap<String, Object>();
        final_params.put("cmd", cmd);
        final_params.put("params", paramsMap);

        jsonParams = new JSONObject(final_params);

        return jsonParams.toString();
    }

}
