package com.mysbsprepaidcard.zfsbs.activity;

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

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.adapter.CommonAdapter;
import com.mysbsprepaidcard.zfsbs.adapter.ViewHolder;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.model.SbsPrinterData;

import java.util.List;


/**********************************************************
 * *
 * Created by wucongpeng on 2016/11/9.        *
 **********************************************************/


public class MyRecordsAdapter extends CommonAdapter<SbsPrinterData> {

    private static final String TAG = "MyCouponsAdapter";
    private List<SbsPrinterData> datas;

    public MyRecordsAdapter(Context context, List<SbsPrinterData> datas, int layoutId){
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    public void convert(Context context, ViewHolder holder, SbsPrinterData sbsPrinterData) {
        holder.setText(R.id.amount_id, sbsPrinterData.getAmount())
                .setText(R.id.dateTime_id, sbsPrinterData.getDateTime())
                .setText(R.id.id_trace_flag, getUploadStr(sbsPrinterData))
                .setText(R.id.id_refund_flag, sbsPrinterData.isRefund() ? "已退款":"")
                .setText(R.id.pay_method_id, Constants.getPayWayDesc(sbsPrinterData.getPayType()));
    }

    private String getUploadStr(SbsPrinterData sbsPrinterData){
//        if (sbsPrinterData.isStatus()){
//            return "交易未知";
//        }
        if (sbsPrinterData.getPayType() == Constants.PAY_WAY_UNDO){
            if (!sbsPrinterData.isRefundUpload()){
                return "流水上送失败";
            }
        }else {
            if (sbsPrinterData.isUploadFlag()){
                return "流水上送失败";
            }
        }

        return "";
    }
}
