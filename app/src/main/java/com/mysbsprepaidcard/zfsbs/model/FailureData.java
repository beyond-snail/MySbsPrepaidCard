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
 * Created by wucongpeng on 2017/2/25.        *
 **********************************************************/


public class FailureData {
    private String orderNo; //订单号
    private String outOrderNo; //外部订单号
    private String time; //时间
    private String traceNum;
    private int pay_type; //交易类型
    private String order_type; //富友sm 类型
    private String amount; //金额
    private boolean status;//交易状态
    private int faiureType; //富友sm 失败的类型 支付失败 还是 查询失败
    private int app_type; //第三方类型
    private boolean isMember; //是否是会员
    private String tgy;
    private String cardId;
    private int real_pay_money; //实付金额
    private int real_get_money; //实际充值金额

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTraceNum() {
        return traceNum;
    }

    public void setTraceNum(String traceNum) {
        this.traceNum = traceNum;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getFaiureType() {
        return faiureType;
    }

    public void setFaiureType(int faiureType) {
        this.faiureType = faiureType;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public int getApp_type() {
        return app_type;
    }

    public void setApp_type(int app_type) {
        this.app_type = app_type;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public String getTgy() {
        return tgy;
    }

    public void setTgy(String tgy) {
        this.tgy = tgy;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getReal_pay_money() {
        return real_pay_money;
    }

    public void setReal_pay_money(int real_pay_money) {
        this.real_pay_money = real_pay_money;
    }

    public int getReal_get_money() {
        return real_get_money;
    }

    public void setReal_get_money(int real_get_money) {
        this.real_get_money = real_get_money;
    }

    @Override
    public String toString() {
        return "FailureData{" +
                "orderNo='" + orderNo + '\'' +
                ", outOrderNo='" + outOrderNo + '\'' +
                ", time='" + time + '\'' +
                ", traceNum='" + traceNum + '\'' +
                ", pay_type=" + pay_type +
                ", order_type='" + order_type + '\'' +
                ", amount='" + amount + '\'' +
                ", status=" + status +
                ", faiureType=" + faiureType +
                ", app_type=" + app_type +
                ", isMember=" + isMember +
                ", tgy='" + tgy + '\'' +
                ", cardId='" + cardId + '\'' +
                ", real_pay_money=" + real_pay_money +
                ", real_get_money=" + real_get_money +
                '}';
    }
}
