package com.mysbsprepaidcard.zfsbs.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tool.utils.activityManager.AppManager;


public class LoginReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        CommonFunc.startAction((Activity)context, OperatorLoginActivity1.class, false);
//        Intent intent1=new Intent(context,OperatorLoginActivity1.class);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent1);

        Log.e("liujun", "时间发生变化。。。");

        AppManager.getAppManager().finishAllActivity();
//        if (Config.OPERATOR_UI_BEFORE) {
//            CommonFunc.startAction((Activity)context, OperatorLoginActivity.class, false);
//        } else {
//            CommonFunc.startAction((Activity)context, OperatorLoginActivity1.class, false);
//        }
    }
}
