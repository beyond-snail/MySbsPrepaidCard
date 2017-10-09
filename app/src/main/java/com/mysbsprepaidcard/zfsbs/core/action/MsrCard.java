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

/**********************************************************
 *                                                        *
 *                  Created by wucongpeng on 2017/7/25.        *
 **********************************************************/


public class MsrCard {

//    private static Context mContext;
//    private MSRDevice msrDevice;
//    private static MsrCard msrCard;
//    private TrackData listener;
//
//    private String str = "";
//
//
//    private Handler handler = new Handler();
//
//
//    private Runnable myRunnable = new Runnable() {
//        public void run() {
//            ToastUtils.CustomShow(mContext, str);
//        }
//    };
//
//    private MsrCard(){
//        msrDevice = (MSRDevice) POSTerminal.getInstance(mContext.getApplicationContext()).getDevice("cloudpos.device.msr");
//    }
//
//
//
//    interface TrackData{
//        void onSuccess(String track2Data);
//    }
//
//
//
//    /**
//     * 单一实例
//     */
//    public static MsrCard getMsrCard(Context context) {
//        mContext = context;
//        if (msrCard == null) {
//            msrCard = new MsrCard();
//        }
//        return msrCard;
//    }
//
//    public void openMsrCard(final TrackData listener){
//        try {
////            str = "正在打开磁条卡阅读器，请稍后...\n";
////            handler.post(myRunnable);
//            msrDevice.open();
////            str += "磁条卡阅读器已成功打开，请刷卡...\n";
////            handler.post(myRunnable);
//            try {
//                msrDevice.listenForSwipe(new OperationListener() {
//
//                    @Override
//                    public void handleResult(OperationResult result) {
//                        // TODO Auto-generated method stub
//                        if (result.getResultCode() == result.SUCCESS) {
//                            MSROperationResult msrOperationResult = (MSROperationResult) result;
//                            MSRTrackData tarckData = msrOperationResult.getMSRTrackData();
//                            if (tarckData.getTrackError(0) == MSRTrackData.NO_ERROR) {
//                                if (tarckData.getTrackData(0) != null) {
//                                    String track1Data = new String(tarckData.getTrackData(0));
////                                    str += "第一磁道信息:" + track1Data + "\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第一磁道信息:" + track1Data);
//                                }
//                            } else {
//                                if (tarckData.getTrackData(0) == null) {
////                                    str += "第一磁道信息:\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第一磁道信息:");
//                                }
//                            }
//                            if (tarckData.getTrackError(1) == MSRTrackData.NO_ERROR) {
//                                if (tarckData.getTrackData(1) != null) {
//                                    String track2Data = new String(tarckData.getTrackData(1));
////                                    str += "第二磁道信息:" + track2Data + "\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第二磁道信息:" + track2Data);
//                                    listener.onSuccess(track2Data);
//                                }
//                            } else {
//                                if (tarckData.getTrackData(1) == null) {
////                                    str += "第二磁道信息:\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第二磁道信息:");
//                                }
//                            }
//                            if (tarckData.getTrackError(2) == MSRTrackData.NO_ERROR) {
//                                if (tarckData.getTrackData(2) != null) {
//                                    String track3Data = new String(tarckData.getTrackData(2));
////                                    str += "第三磁道信息:" + track3Data + "\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第三磁道信息:" + track3Data);
//                                }
//                            } else {
//                                if (tarckData.getTrackData(2) == null) {
////                                    str += "第三磁道信息:\n";
////                                    handler.post(myRunnable);
//                                    Log.i("", "第三磁道信息:");
//                                }
//                            }
//                        } else if (result.getResultCode() == result.ERR_TIMEOUT) {
//                            str += "读取磁道信息超时..\n";
//                            handler.post(myRunnable);
//                        } else {
//                            str += "读取磁道信息失败...\n";
//                            handler.post(myRunnable);
//                        }
//                    }
//                }, 20000);
//
//            } catch (DeviceException de) {
//                str += "读取磁道信息失败...\n";
//                handler.post(myRunnable);
//            }
//        } catch (DeviceException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//            str += "磁条卡阅读器打开失败...\n";
//            handler.post(myRunnable);
//        }
//    }
//
//
//    public void closeMsrCard(){
//        try {
//            msrDevice.close();
////            str += "磁条卡阅读器已成功关闭...\n";
////            handler.post(myRunnable);
//        } catch (DeviceException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            str += "磁条卡阅读器关闭失败...\n";
//            handler.post(myRunnable);
//        }
//    }









}
