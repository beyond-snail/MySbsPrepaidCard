package com.mysbsprepaidcard.zfsbs.model;

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

import java.io.Serializable;

/**********************************************************
 *                                                        *
 *                  Created by wucongpeng on 2017/7/27.        *
 **********************************************************/


public class RechargeUpLoad implements Serializable{
    private int sid;
    private int recharge_get_money;
    private int recharge_pay_money;
    private String recharge_order_num;
    private long recharge_time;
    private String terminal_sn;
    private int payType;
    private String promotion_num;
    private String opreator_num;
    private String realize_card_id;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getRecharge_get_money() {
        return recharge_get_money;
    }

    public void setRecharge_get_money(int recharge_get_money) {
        this.recharge_get_money = recharge_get_money;
    }

    public int getRecharge_pay_money() {
        return recharge_pay_money;
    }

    public void setRecharge_pay_money(int recharge_pay_money) {
        this.recharge_pay_money = recharge_pay_money;
    }

    public String getRecharge_order_num() {
        return recharge_order_num;
    }

    public void setRecharge_order_num(String recharge_order_num) {
        this.recharge_order_num = recharge_order_num;
    }

    public long getRecharge_time() {
        return recharge_time;
    }

    public void setRecharge_time(long recharge_time) {
        this.recharge_time = recharge_time;
    }

    public String getTerminal_sn() {
        return terminal_sn;
    }

    public void setTerminal_sn(String terminal_sn) {
        this.terminal_sn = terminal_sn;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPromotion_num() {
        return promotion_num;
    }

    public void setPromotion_num(String promotion_num) {
        this.promotion_num = promotion_num;
    }

    public String getOpreator_num() {
        return opreator_num;
    }

    public void setOpreator_num(String opreator_num) {
        this.opreator_num = opreator_num;
    }

    public String getRealize_card_id() {
        return realize_card_id;
    }

    public void setRealize_card_id(String realize_card_id) {
        this.realize_card_id = realize_card_id;
    }
}
