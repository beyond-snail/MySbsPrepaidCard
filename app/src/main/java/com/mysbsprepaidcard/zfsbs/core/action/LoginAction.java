package com.mysbsprepaidcard.zfsbs.core.action;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;

import com.mysbsprepaidcard.zfsbs.activity.SaleMainActivity;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.core.myinterface.UiAction;
import com.mysbsprepaidcard.zfsbs.model.ActivateApiResponse;
import com.mysbsprepaidcard.zfsbs.model.LoginApiResponse;
import com.mysbsprepaidcard.zfsbs.model.OperatorBean;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;


public class LoginAction {

    private static final String TAG = "LoginAction";

    private Activity mContext;
    private UiAction ui;

    public LoginAction(Activity context, UiAction ui) {
        this.mContext = context;
        this.ui = ui;
    }

    @SuppressLint("NewApi")
    public void loginAction() {

        MyApplication.getInstance().getSbsAction().Login(mContext, StringUtils.getSerial(), "", "", new ActionCallbackListener<LoginApiResponse>() {
            @Override
            public void onSuccess(LoginApiResponse data) {
                loginSave(data);
                //记录当前日期
                SPUtils.put(mContext, Constants.SBS_LOGIN_TIME, StringUtils.getCurDate());
                SPUtils.put(mContext, Constants.SHIFT_ROOM_TIME, StringUtils.getCurTime());
                //每天开启一次服务
//                if (Constants.isCheckLoginStatus(context, Constants.SBS_LOGIN_TIME, Constants.DEFAULT_SBS_LOGIN_TIME)) {
//                    LogUtils.e("Start Service", "开启定时服务。。。。");
//                    Intent i = new Intent(context, AlarmService.class);
//                    context.startService(i);
//                }
                ToastUtils.CustomShow(mContext, "获取信息成功");
                // 激活BAT
                ActivateCode();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(mContext, errorEvent + "#" + message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {

            }
        });
    }

    @SuppressLint("NewApi")
    public void loginAction(final String username, final String password) {

        MyApplication.getInstance().getSbsAction().Login(mContext, StringUtils.getSerial(), username, password, new ActionCallbackListener<LoginApiResponse>() {
            @Override
            public void onSuccess(LoginApiResponse data) {
                //判断当前的操作员是否存在返回列表中
                List<OperatorBean> lists = data.getOperator_list();

                if (!(checkOpertorUserName(lists, username) && checkOptertorPsw(lists, password))){
                    ToastUtils.CustomShow(mContext, "操作员账号或者密码错误");
                    return;
                }




                loginSave(data);
                //保存当前的操作员号
                SPUtils.put(mContext, Constants.USER_NAME, username);
                SPUtils.put(mContext, Constants.USER_PSW, password);


                //记录当前日期
                SPUtils.put(mContext, Constants.SBS_LOGIN_TIME, StringUtils.getCurDate());
                SPUtils.put(mContext, Constants.SHIFT_ROOM_TIME, StringUtils.getCurTime());
                //每天开启一次服务
//                if (Constants.isCheckLoginStatus(context, Constants.SBS_LOGIN_TIME, Constants.DEFAULT_SBS_LOGIN_TIME)) {
//                    LogUtils.e("Start Service", "开启定时服务。。。。");
//                    Intent i = new Intent(context, AlarmService.class);
//                    context.startService(i);
//                }
                ToastUtils.CustomShow(mContext, "获取信息成功");
                // 激活BAT
                ActivateCode();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(mContext, errorEvent + "#" + message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {

            }
        });
    }

    private boolean checkOpertorUserName(List<OperatorBean> lists, String username) {
        for (OperatorBean operatorBean : lists) {
            if (StringUtils.isEquals(username, operatorBean.getOperator_num())){
                return true;
            }
        }
        return false;
    }

    private boolean checkOptertorPsw(List<OperatorBean> lists, String password){
        for (OperatorBean operatorBean : lists){
            if (StringUtils.isEquals(password, operatorBean.getOperator_password())){
                return true;
            }
        }
        return false;
    }



    /**
     * 保存签到返回信息。
     *
     * @param data
     */
    private void loginSave(LoginApiResponse data) {
        // 从数据表中查询所有的记录
        List<LoginApiResponse> list = DataSupport.findAll(LoginApiResponse.class);
        LoginApiResponse temp = null;

        // 第一次
        if (list.size() == 0) {
            // 索引累加
            data.setKeyIndex(Constants.FY_INDEX_0);//(StringUtils.getIndexNo(mContext));
            data.setDownMasterKey(Constants.isDownMaster); // 这里暂时改为 false
            data.setCurrent(true); // 第一次 表示为当前可用
//            String operatorData = GsonUtils.parseObjToJson(data.getOperator_list());
//            LogUtils.e(TAG, operatorData);
//            data.setOperatorListData(operatorData);
            // 保存到数据表中
            data.save();
            // 保存为当前使用的数据
            Save(data);
        } else {
            // 遍历查询 根据授权码判断
            for (int i = 0; i < list.size(); i++) {
                // 没有相等的 继续遍历(终端号是否相等)
                if (!StringUtils.isEquals(list.get(i).getTerminalNo(), data.getTerminalNo())) {
                    // 不相等说明不是当前可用的，将当前标识重置
                    ContentValues values = new ContentValues();
                    values.put("isCurrent", false);
                    DataSupport.update(LoginApiResponse.class, values, list.get(i).getId());
                    continue;
                } else {
                    // 如果终端号 不变，授权码变了，华势后台授权码可以重置，那么需要更新本地的授权码，同时状态也要更新
                    if (!StringUtils.isEmpty(data.getOther())) {
                        if (!StringUtils.isEquals(list.get(i).getOther(), StringUtils.replaceBlank(data.getOther()))) {
                            // 设置当前的下载密钥状态
                            list.get(i).setDownMasterKey(Constants.isDownMaster);
                            list.get(i).setOther(StringUtils.replaceBlank(data.getOther()));
                            // 更新本地授权码
                            ContentValues values = new ContentValues();
                            values.put("other", StringUtils.replaceBlank(data.getOther()));
                            values.put("isDownMasterKey", Constants.isDownMaster);
                            DataSupport.update(LoginApiResponse.class, values, list.get(i).getId());
//                            DataSupport.updateAll(LoginApiResponse.class, values);

                        }
                    }
                    temp = list.get(i);
                    break;
                }

            }

            // 本地不存在
            if (temp == null) {
                // 索引累加
                data.setKeyIndex(Constants.FY_INDEX_1);//(StringUtils.getIndexNo(mContext));
                data.setDownMasterKey(Constants.isDownMaster);
                data.setCurrent(true);
//                String operatorData = GsonUtils.parseObjToJson(data.getOperator_list());
//                LogUtils.e(TAG, operatorData);
//                data.setOperatorListData(operatorData);
                // 保存当前信息到数据表
                data.save();
                // 保存为当前使用的数据
                Save(data);
            } else {

                // 判断激活码
                String activateCode_old = MyApplication.getInstance().getLoginData().getActiveCode();
                LogUtils.e("old_activate: " + activateCode_old);
                // 当原始激活码和服务器激活码不一样 并且 是已激活,那么更新为最新的激活码，并且状态为未激活
                // 判断本地激活码是否是空的，如果是空的后台重新配置后要更新
                if (StringUtils.isEmpty(activateCode_old)
                        || !StringUtils.isEquals(activateCode_old, data.getActiveCode())) {
                    // 设置当前激活状态 和 激活码 同时更新数据库
                    temp.setActive(false);
                    temp.setActiveCode(data.getActiveCode());

                    // 更新到数据库
                    ContentValues values = new ContentValues();
                    values.put("isActive", false);
                    values.put("activeCode", data.getActiveCode());
                    DataSupport.update(LoginApiResponse.class, values, temp.getId());
//                    DataSupport.updateAll(LoginApiResponse.class, values);
                }

                // 更新其他到数据库
                ContentValues values = new ContentValues();
                values.put("sid", data.getSid());
                values.put("licenseNo", data.getLicenseNo());
                values.put("accountName", data.getAccountName());
                values.put("accountBank", data.getAccountBank());
                values.put("accountNo", data.getAccountNo());
                values.put("terminalName", data.getTerminalName());
                values.put("operatList", data.getOperatList());
                values.put("isCurrent", true);
                values.put("fyMerchantNo", data.getFyMerchantNo());
                values.put("fyMerchantName", data.getFyMerchantName());
                values.put("scanPayType", data.getScanPayType());
//                String operatorData = GsonUtils.parseObjToJson(data.getOperator_list());
//                LogUtils.e(TAG, operatorData);
//                values.put("operatorListData", operatorData);

                DataSupport.update(LoginApiResponse.class, values, temp.getId());
//                DataSupport.updateAll(LoginApiResponse.class, values);

                // 更新当前使用数据
                temp.setSid(data.getSid());
                temp.setLicenseNo(data.getLicenseNo());
                temp.setAccountName(data.getAccountName());
                temp.setAccountBank(data.getAccountBank());
                temp.setAccountNo(data.getAccountNo());
                temp.setTerminalName(data.getTerminalName());
                temp.setOperatList(data.getOperatList());
                temp.setCurrent(true);
                temp.setFyMerchantName(data.getFyMerchantName());
                temp.setFyMerchantNo(data.getFyMerchantNo());
                temp.setScanPayType(data.getScanPayType());
//                temp.setOperatorListData(operatorData);
                // 如果存在 那么就用存在的密钥索引
                Save(temp);
            }

        }

    }

//    /**
//     * 保存签到返回信息。
//     *
//     * @param data
//     */
//    private void loginSave0(LoginApiResponse data) {
//
//        // 索引累加
//        data.setKeyIndex(Constants.FY_INDEX_0);//(StringUtils.getIndexNo(mContext));
//        data.setDownMasterKey((Boolean) SPUtils.get(mContext, Config.FY_DOWN_MASTER, false) ? true : false);
//        data.setCurrent(true);
//        // 保存当前信息到数据表
//        data.save();
//        // 保存为当前使用的数据
//        Save0(data);
//    }
//
//    /**
//     * 保存签到返回信息。
//     *
//     * @param data
//     */
//    private void loginSave1(LoginApiResponse data) {
//
//        // 索引累加
//        data.setKeyIndex(Constants.FY_INDEX_1);//(StringUtils.getIndexNo(mContext));
//        data.setDownMasterKey((Boolean) SPUtils.get(mContext, Config.FY_DOWN_MASTER, false) ? true : false);
//        data.setCurrent(true);
//        // 保存当前信息到数据表
//        data.save();
//        // 保存为当前使用的数据
//        Save1(data);
//    }

    private void Save(LoginApiResponse data) {
        MyApplication.getInstance().setLoginData(data);
    }
//
//    private void Save0(LoginApiResponse data) {
//        MyApplication.getInstance().setLoginData(data);
//    }
//
//    private void Save1(LoginApiResponse data) {
//        MyApplication.getInstance().setLoginData(data);
//    }

    // 激活BAT
    protected void ActivateCode() {
        String sm_type = MyApplication.getInstance().getLoginData().getScanPayType();

        if (!StringUtils.isEmpty(sm_type) && StringUtils.isEquals(sm_type, Constants.SM_TYPE_FY)) {
            ui.UiAction(mContext, SaleMainActivity.class, true);
            return;
        } else if (!StringUtils.isEmpty(sm_type) && StringUtils.isEquals(sm_type, Constants.SM_TYPE_SQB)) {

        } else {
            ui.UiAction(mContext, SaleMainActivity.class, true);
            return;
        }

    }

    /**
     * 激活反馈
     *
     * @param isSuccess 1：成功，0：失败
     */
    private void activateAction(int isSuccess) {
        final LoginApiResponse loginData = MyApplication.getInstance().getLoginData();
        int sid = loginData.getSid();
        String activateCode = loginData.getActiveCode();
        MyApplication.getInstance().getSbsAction().active(mContext, sid, isSuccess, activateCode, new ActionCallbackListener<ActivateApiResponse>() {
            @Override
            public void onSuccess(ActivateApiResponse data) {
                // 设置到当前使用 同时 更新到数据库
                loginData.setActivateCodemerchantName(data.getMerchantName());
                loginData.setActivateCodeMerchantNo(data.getMerchantNo());
                // 更新到数据库
                ContentValues values = new ContentValues();
                values.put("activateCodeMerchantNo", data.getMerchantNo());
                values.put("activateCodemerchantName", data.getMerchantName());
                values.put("activateMerchantNanme", data.getTerminalName());
                DataSupport.update(LoginApiResponse.class, values, loginData.getId());

                ui.UiAction(mContext, SaleMainActivity.class, true);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(mContext, message);
                // 这里反馈没有成功，那边就让这个激活码的状态为false ，重新签到的时候再次去激活，反馈
                // 保存激活状态
                MyApplication.getInstance().getLoginData().setActive(false);
                MyApplication.getInstance().getLoginData().setActiveCode("");
                // 更新到数据库
                ContentValues values = new ContentValues();
                values.put("isActive", false);
                values.put("activeCode", "");
                DataSupport.update(LoginApiResponse.class, values,
                        MyApplication.getInstance().getLoginData().getId());
                ui.UiAction(mContext, SaleMainActivity.class, true);
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
