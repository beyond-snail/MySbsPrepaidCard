package com.mysbsprepaidcard.zfsbs.adapter;

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
import android.view.View;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoomShow;

import java.util.List;


/**********************************************************
 * *
 * Created by wucongpeng on 2017/2/23.        *
 **********************************************************/


public class MyShiftRoom extends CommonAdapter<ShiftRoomShow> {

    private List<ShiftRoomShow> datas;

    public MyShiftRoom(Context context, List<ShiftRoomShow> datas, int layoutId) {
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    public void convert(Context context, ViewHolder holder, ShiftRoomShow show) {
        if (!show.getName().equals("交易总计")){
            holder.getView(R.id.id_ll_undo_money).setVisibility(View.GONE);
            if (show.getName().equals("刷卡撤销") || show.getName().equals("微信撤销") || show.getName().equals("支付宝撤销") || show.getName().equals("钱包撤销")){
                holder.getView(R.id.id_ll_show_coupon).setVisibility(View.GONE);
                holder.getView(R.id.id_ll_show_intergral_deduct).setVisibility(View.GONE);
            }else {
                holder.getView(R.id.id_ll_show_coupon).setVisibility(View.VISIBLE);
                holder.getView(R.id.id_ll_show_intergral_deduct).setVisibility(View.VISIBLE);
            }
        }else {
            holder.getView(R.id.id_ll_undo_money).setVisibility(View.VISIBLE);

        }

        holder.setText(R.id.id_type_name, show.getName())

                .setText(R.id.id_trade_num_name, show.getTrade_num_key())
                .setText(R.id.id_trade_num, show.getTrade_num() + "笔")

                .setText(R.id.id_undo_money_name, show.getReal_undo_money_key())
                .setText(R.id.id_undo_money, show.getReal_undo_money()+ "元")

                .setText(R.id.id_real_pay_money_name, show.getReal_pay_money_key())
                .setText(R.id.id_show_real_pay_money, show.getReal_pay_money()+ "元")

                .setText(R.id.id_show_coupon_deduct_name, show.getCoupon_deduct_key())
                .setText(R.id.id_show_coupon_deduct, show.getCoupon_deduct()+ "元")

                .setText(R.id.id_show_intergral_deduct_name, show.getIntergral_deduct_key())
                .setText(R.id.id_show_intergral_deduct, show.getIntergral_deduct()+ "元");
    }
}
