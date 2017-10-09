package com.mysbsprepaidcard.zfsbs.common;

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

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.mysbsprepaidcard.zfsbs.core.action.SbsAction;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.SbsPrinterData;
import com.mysbsprepaidcard.zfsbs.model.TransUploadRequest;
import com.mysbsprepaidcard.zfsbs.model.TransUploadResponse;
import com.tool.utils.utils.LogUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**********************************************************
 * *
 * Created by wucongpeng on 2017/3/2.        *
 **********************************************************/


public class UploadFunc {

    private static final String TAG = "UploadFunc";
    private  List<SbsPrinterData> allData;

    private  boolean inUpload;

    private static  UploadFunc uploadFunc;

    private Context mContext;

    public static UploadFunc   getInstance(){

        if(uploadFunc==null){
            uploadFunc=new UploadFunc();
        }
        return uploadFunc;
    }



    public  synchronized void isCheckUploadStatus(Context context) {

        mContext = context;
        // 从交易记录中读取数据
        allData = DataSupport.findAll(SbsPrinterData.class);
        inUpload = false;
        if (allData.size() <= 0) {
            return;
        }

        List<SbsPrinterData> temp = new ArrayList<>();

        for (int i = 0; i < allData.size(); i++){
            if (allData.get(i).isUploadFlag()){
                temp.add(allData.get(i));
            }
        }

        // 遍历
        for (int index=0;index< temp.size();) {

            //检测到流水上送失败的标识，然后上送
//            if (temp.get(index).isUploadFlag()) {
                //开始上送流水
                if (!inUpload) {
                    inUpload = true;
//                    transUploadAction(context, temp.get(index));
                    Message message = new Message();
                    message.obj = temp.get(index);

                    handler.handleMessage(message);
                    index++;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }
        }
        inUpload = false;
    }

    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            // process incoming messages here
            transUploadAction((SbsPrinterData) msg.obj);
        }
    };


    //流水上送
    private  void transUploadAction(final SbsPrinterData recordData) {

        TransUploadRequest transUploadRequest = new Gson().fromJson(recordData.getTransUploadData(), TransUploadRequest.class);

        if (transUploadRequest == null) {
            return;
        }
        LogUtils.e(transUploadRequest.toString());


        SbsAction sbsAction = new SbsAction();
        sbsAction.transUpload(mContext, transUploadRequest, new ActionCallbackListener<TransUploadResponse>() {
            @Override
            public void onSuccess(TransUploadResponse data) {
                LogUtils.e(TAG, "交易流水上送成功" + data.toString());

                //更新
                ContentValues values = new ContentValues();
                values.put("point_url", data.getPoint_url());
                values.put("point", data.getPoint());
                values.put("pointCurrent", data.getPointCurrent());
                values.put("coupon", data.getCoupon());
                values.put("title_url", data.getTitle_url());
                values.put("money", data.getMoney());
                values.put("backAmt", data.getBackAmt());
                values.put("UploadFlag", false);
                DataSupport.update(SbsPrinterData.class, values, recordData.getId());
                inUpload = false;


            }

            @Override
            public void onFailure(String errorEvent, String message) {
                LogUtils.e(TAG, "交易流水上送失败");
                inUpload = false;
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {
                inUpload = false;
            }

            @Override
            public void onLogin() {
            }
        });
    }


}

























