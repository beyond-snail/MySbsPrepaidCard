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

import android.os.Bundle;
import android.widget.TextView;

import com.mysbsprepaidcard.R;
import com.tool.utils.utils.StringUtils;


/**********************************************************
 * *
 * Created by wucongpeng on 2016/12/14.        *
 **********************************************************/


public class ShowInfoCurrentDayData extends BaseActivity{

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_current_day_data);

        tv = (TextView) findViewById(R.id.id_show_data);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        tv.setText(
                "刷卡消费金额: "+ ((bundle.getInt("flotAmount") == 0) ? "0.00元" : StringUtils.formatIntMoney(bundle.getInt("flotAmount"))+"元") +"\r\n\r\n"+
                "撤销消费金额: "+ ((bundle.getInt("undoAmount") == 0) ? "0.00元" : "-"+StringUtils.formatIntMoney(bundle.getInt("undoAmount"))+"元") +"\r\n\r\n"+
                "支付宝消费金额: "+ ((bundle.getInt("smAlyAmount") == 0) ? "0.00元" : StringUtils.formatIntMoney(bundle.getInt("smAlyAmount"))+"元") +"\r\n\r\n"+
                "微信消费金额: "+ ((bundle.getInt("smWxAmount") == 0) ? "0.00元" : StringUtils.formatIntMoney(bundle.getInt("smWxAmount"))+"元") +"\r\n\r\n"+
                "现金消费金额: "+ ((bundle.getInt("cashAmount") == 0) ? "0.00元" : StringUtils.formatIntMoney(bundle.getInt("cashAmount"))+"元") +"\r\n\r\n"+
                "总金额: "+ ((bundle.getInt("totalAmount") == 0) ? "0.00元" : StringUtils.formatIntMoney(bundle.getInt("totalAmount"))+"元") +"\r\n"

        );

    }
}



























