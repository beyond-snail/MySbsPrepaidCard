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

/**********************************************************
 * *
 * Created by wucongpeng on 2017/2/23.        *
 **********************************************************/


public class ShiftRoomShow {

    private String name;
//    private ShiftCount shiftCount;
    private String trade_num_key; //交易笔数
    private int trade_num; //交易笔数
    private String real_pay_money_key; //实付金额
    private String real_pay_money; //实付金额
    private String real_undo_money_key; //交易撤销总金额
    private String real_undo_money; //交易撤销总金额
    private String coupon_deduct_key; //优惠券抵扣金额
    private String coupon_deduct; //优惠券抵扣金额
    private String intergral_deduct_key; //积分抵扣金额
    private String intergral_deduct; //积分抵扣金额

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrade_num_key() {
        return trade_num_key;
    }

    public void setTrade_num_key(String trade_num_key) {
        this.trade_num_key = trade_num_key;
    }

    public int getTrade_num() {
        return trade_num;
    }

    public void setTrade_num(int trade_num) {
        this.trade_num = trade_num;
    }

    public String getReal_pay_money_key() {
        return real_pay_money_key;
    }

    public void setReal_pay_money_key(String real_pay_money_key) {
        this.real_pay_money_key = real_pay_money_key;
    }

    public String getReal_pay_money() {
        return real_pay_money;
    }

    public void setReal_pay_money(String real_pay_money) {
        this.real_pay_money = real_pay_money;
    }

    public String getReal_undo_money_key() {
        return real_undo_money_key;
    }

    public void setReal_undo_money_key(String real_undo_money_key) {
        this.real_undo_money_key = real_undo_money_key;
    }

    public String getReal_undo_money() {
        return real_undo_money;
    }

    public void setReal_undo_money(String real_undo_money) {
        this.real_undo_money = real_undo_money;
    }

    public String getCoupon_deduct_key() {
        return coupon_deduct_key;
    }

    public void setCoupon_deduct_key(String coupon_deduct_key) {
        this.coupon_deduct_key = coupon_deduct_key;
    }

    public String getCoupon_deduct() {
        return coupon_deduct;
    }

    public void setCoupon_deduct(String coupon_deduct) {
        this.coupon_deduct = coupon_deduct;
    }

    public String getIntergral_deduct_key() {
        return intergral_deduct_key;
    }

    public void setIntergral_deduct_key(String intergral_deduct_key) {
        this.intergral_deduct_key = intergral_deduct_key;
    }

    public String getIntergral_deduct() {
        return intergral_deduct;
    }

    public void setIntergral_deduct(String intergral_deduct) {
        this.intergral_deduct = intergral_deduct;
    }


    @Override
    public String toString() {
        return "ShiftRoomShow{" +
                "name='" + name + '\'' +
                ", trade_num_key='" + trade_num_key + '\'' +
                ", trade_num=" + trade_num +
                ", real_pay_money_key='" + real_pay_money_key + '\'' +
                ", real_pay_money='" + real_pay_money + '\'' +
                ", real_undo_money_key='" + real_undo_money_key + '\'' +
                ", real_undo_money='" + real_undo_money + '\'' +
                ", coupon_deduct_key='" + coupon_deduct_key + '\'' +
                ", coupon_deduct='" + coupon_deduct + '\'' +
                ", intergral_deduct_key='" + intergral_deduct_key + '\'' +
                ", intergral_deduct='" + intergral_deduct + '\'' +
                '}';
    }
}
