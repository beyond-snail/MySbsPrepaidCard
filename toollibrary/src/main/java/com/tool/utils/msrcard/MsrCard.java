package com.tool.utils.msrcard;

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
import android.util.Log;

import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;
import com.unionpay.cloudpos.wizarposexternal.DeviceException;
import com.unionpay.cloudpos.wizarposexternal.OperationListener;
import com.unionpay.cloudpos.wizarposexternal.OperationResult;
import com.unionpay.cloudpos.wizarposexternal.POSTerminal;
import com.unionpay.cloudpos.wizarposexternal.msr.MSRDevice;
import com.unionpay.cloudpos.wizarposexternal.msr.MSROperationResult;
import com.unionpay.cloudpos.wizarposexternal.msr.MSRTrackData;

/**********************************************************
 *                                                        *
 *                  Created by wucongpeng on 2017/7/25.        *
 **********************************************************/


public class MsrCard {

    private static Context mContext;
    private MSRDevice msrDevice;
    private static MsrCard msrCard;
    private TrackData mlistener;

    private String str = "";

    private String track2Data;


    private Handler handler = new Handler();


    private Runnable myRunnable = new Runnable() {
        public void run() {
            if (!StringUtils.isEmpty(str)) {
                ToastUtils.CustomShow(mContext, str);
            }
//            closeMsrCard();
//            openMsrCard(mlistener);
            mlistener.onFail();
        }
    };

    private Runnable myRunnable1 = new Runnable() {
        public void run() {

            if (track2Data.length() < 5) {
                return;
            }

            String cardNumber;

            if (track2Data.indexOf("=") != -1) {
                cardNumber = track2Data.substring(0, track2Data.indexOf("="));
            } else {
                cardNumber = track2Data;
            }


            mlistener.onSuccess(cardNumber);
        }
    };


    private MsrCard() {
        msrDevice = (MSRDevice) POSTerminal.getInstance(mContext.getApplicationContext()).getDevice("cloudpos.device.msr");
    }


    public interface TrackData {
        void onSuccess(String track2Data);

        void onFail();
    }


    /**
     * 单一实例
     */
    public static MsrCard getMsrCard(Context context) {
        mContext = context;
        if (msrCard == null) {
            msrCard = new MsrCard();
        }
        return msrCard;
    }

    public void openMsrCard(final TrackData listener) {
        try {

//            str = "正在打开磁条卡阅读器，请稍后...\n";
//            handler.post(myRunnable);
            msrDevice.open();
//            str += "磁条卡阅读器已成功打开，请刷卡...\n";
//            handler.post(myRunnable);

            try {
                msrDevice.listenForSwipe(new OperationListener() {

                    @Override
                    public void handleResult(OperationResult result) {
                        // TODO Auto-generated method stub
                        if (result.getResultCode() == result.SUCCESS) {
                            MSROperationResult msrOperationResult = (MSROperationResult) result;
                            MSRTrackData tarckData = msrOperationResult.getMSRTrackData();
//                            if (tarckData.getTrackError(0) == MSRTrackData.NO_ERROR) {
//                                if (tarckData.getTrackData(0) != null) {
//                                    mlistener = listener;
//                                    String track1Data = new String(tarckData.getTrackData(0));
//                                    str = "刷卡失败";//"第一磁道信息:" + track1Data + "\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第一磁道信息:" + track1Data);
//                                }
//                            } else {
//                                if (tarckData.getTrackData(0) == null) {
//                                    mlistener = listener;
//                                    str = "刷卡失败";//、、"第一磁道信息:\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第一磁道信息:");
//                                }
//                            }

                            str = "";
                            if (tarckData.getTrackError(1) == MSRTrackData.NO_ERROR) {
                                if (tarckData.getTrackData(1) != null) {
                                    track2Data = new String(tarckData.getTrackData(1));
//                                    str += "第二磁道信息:" + track2Data + "\n";
//                                    handler.post(myRunnable);
                                    Log.i("", "第二磁道信息:" + track2Data);
                                    mlistener = listener;
                                    handler.post(myRunnable1);

                                }
                            } else {
                                if (tarckData.getTrackData(1) == null) {
                                    str = "刷卡失败,请重新刷卡";//"第二磁道信息:\n";
                                    mlistener = listener;
                                    handler.post(myRunnable);
                                    Log.i("", "第二磁道信息:");
                                }
                            }
//                            if (tarckData.getTrackError(2) == MSRTrackData.NO_ERROR) {
//                                if (tarckData.getTrackData(2) != null) {
//                                    String track3Data = new String(tarckData.getTrackData(2));
//                                    str = "刷卡失败";//"第三磁道信息:" + track3Data + "\n";
//                                    mlistener = listener;
////                                    handler.post(myRunnable);
//                                    Log.i("", "第三磁道信息:" + track3Data);
//                                }
//                            } else {
//                                if (tarckData.getTrackData(2) == null) {
//                                    str = "刷卡失败";//"第三磁道信息:\n";
//                                    mlistener = listener;
//                                    handler.post(myRunnable);
//                                    Log.i("", "第三磁道信息:");
//                                }
//                            }
                        } else if (result.getResultCode() == result.ERR_TIMEOUT) {
//                            str = "读取磁道信息超时..\n";
                            str = "";
                            Log.i("", "读取磁道信息超时...\\n:");
                            mlistener = listener;
                            handler.post(myRunnable);
                        } else {
                            str = "";
//                            str = "读取磁道信息失败1...\n";
//                            Log.i("", "读取磁道信息失败...\\n:");
//                            mlistener = listener;
//                            handler.post(myRunnable);
                        }
                    }
                }, 20000);

            } catch (DeviceException de) {
//                str = "读取磁道信息失败...\n";
                Log.i("", "读取磁道信息失败2...\\n:");
                mlistener = listener;
                handler.post(myRunnable);
            }
        } catch (DeviceException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
//            str = "磁条卡阅读器打开失败...\n";
            Log.i("", "磁条卡阅读器打开失败...\\n:");
            mlistener = listener;
            handler.post(myRunnable);
        }
    }


    public void closeMsrCard() {
        try {
            if (msrDevice != null) {
                msrDevice.close();
                Log.i("", "磁条卡阅读器已成功关闭...\\n:");
            }


//            str += "磁条卡阅读器已成功关闭...\n";
//            handler.post(myRunnable);
        } catch (DeviceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//            str += "磁条卡阅读器关闭失败...\n";
            Log.i("", "磁条卡阅读器关闭失败...\\n:");
//            handler.post(myRunnable);
        }
    }


}
