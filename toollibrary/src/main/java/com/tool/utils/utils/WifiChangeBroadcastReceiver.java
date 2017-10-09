package com.tool.utils.utils;

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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import static android.content.Context.WIFI_SERVICE;

/**********************************************************
 * *
 * Created by wucongpeng on 2017/2/21.        *
 **********************************************************/


public class WifiChangeBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "WifiChangeBroadcastRece";

    private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext=context;
//        LogUtils.e(TAG, "Wifi发生变化");
        getWifiInfo();

//        int wifi_state = intent.getIntExtra("wifi_state", 0);
//        int level = Math.abs(((WifiManager)context.getSystemService(WIFI_SERVICE)).getConnectionInfo().getRssi());
//        LogUtils.e(TAG, "1111:" + level);
//        switch (wifi_state) {
//            case WifiManager.WIFI_STATE_DISABLING:
//                LogUtils.e(TAG, "1111:" + WifiManager.WIFI_STATE_DISABLING);
//                break;
//            case WifiManager.WIFI_STATE_DISABLED:
//                LogUtils.e(TAG, "2222:" + WifiManager.WIFI_STATE_DISABLED);
//                break;
//            case WifiManager.WIFI_STATE_ENABLING:
//                LogUtils.e(TAG, "33333:" + WifiManager.WIFI_STATE_ENABLING);
//                break;
//            case WifiManager.WIFI_STATE_ENABLED:
//                LogUtils.e(TAG, "4444:" + WifiManager.WIFI_STATE_ENABLED);
//                break;
//            case WifiManager.WIFI_STATE_UNKNOWN:
//                LogUtils.e(TAG, "5555:" + WifiManager.WIFI_STATE_UNKNOWN);
//                break;
//        }
    }

    private void getWifiInfo() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getBSSID() != null) {
            //wifi名称
            String ssid = wifiInfo.getSSID();
            //wifi信号强度
            int signalLevel = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 5);
            //wifi速度
            int speed = wifiInfo.getLinkSpeed();
            //wifi速度单位
            String units = WifiInfo.LINK_SPEED_UNITS;
            LogUtils.e(TAG, "ssid="+ssid+",signalLevel="+signalLevel+",speed="+speed+",units="+units);
            if(speed <= 12) {
//                ToastUtils.CustomShow(mContext, "当前网络不稳定，请确保网络稳定！！！");
            }
        }
    }
}
