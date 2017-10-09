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
 *                                                        *
 *                  Created by wucongpeng on 2017/7/29.        *
 **********************************************************/


public class ChargeBlance {
    private String sh_name;
    private String recharge_order_num;
    private String member_name;
    private String realize_card_num;
    private int packet_remain;

    public String getSh_name() {
        return sh_name;
    }

    public void setSh_name(String sh_name) {
        this.sh_name = sh_name;
    }

    public String getRecharge_order_num() {
        return recharge_order_num;
    }

    public void setRecharge_order_num(String recharge_order_num) {
        this.recharge_order_num = recharge_order_num;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getRealize_card_num() {
        return realize_card_num;
    }

    public void setRealize_card_num(String realize_card_num) {
        this.realize_card_num = realize_card_num;
    }

    public int getPacket_remain() {
        return packet_remain;
    }

    public void setPacket_remain(int packet_remain) {
        this.packet_remain = packet_remain;
    }
}
