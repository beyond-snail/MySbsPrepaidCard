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
 * Created by wucongpeng on 2016/11/15.        *
 **********************************************************/


public class ZfQbRequest {
    private String TranCode; //交易码
    private String qrCode; //用户二维码
    private String MerchantId; //商户编号
    private String TerminalNo; //终端号
    private String OrgOrderNum; //订单号
    private String OrgTranDateTime; //交易时间
    private String SysTraceNum; //交易流水号
    private String SignMsg; //签名
    private String TranAmt; //收款金额
    private String OrderCurrency; //币种


    public String getTranCode() {
        return TranCode;
    }

    public void setTranCode(String tranCode) {
        TranCode = tranCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
    }

    public String getTerminalNo() {
        return TerminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        TerminalNo = terminalNo;
    }

    public String getOrgOrderNum() {
        return OrgOrderNum;
    }

    public void setOrgOrderNum(String orgOrderNum) {
        OrgOrderNum = orgOrderNum;
    }

    public String getOrgTranDateTime() {
        return OrgTranDateTime;
    }

    public void setOrgTranDateTime(String orgTranDateTime) {
        OrgTranDateTime = orgTranDateTime;
    }

    public String getSysTraceNum() {
        return SysTraceNum;
    }

    public void setSysTraceNum(String sysTraceNum) {
        SysTraceNum = sysTraceNum;
    }

    public String getSignMsg() {
        return SignMsg;
    }

    public void setSignMsg(String signMsg) {
        SignMsg = signMsg;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getOrderCurrency() {
        return OrderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        OrderCurrency = orderCurrency;
    }

    @Override
    public String toString() {
        return "ZfQbRequest{" +
                "TranCode='" + TranCode + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", MerchantId='" + MerchantId + '\'' +
                ", TerminalNo='" + TerminalNo + '\'' +
                ", OrgOrderNum='" + OrgOrderNum + '\'' +
                ", OrgTranDateTime='" + OrgTranDateTime + '\'' +
                ", SysTraceNum='" + SysTraceNum + '\'' +
                ", SignMsg='" + SignMsg + '\'' +
                ", TranAmt='" + TranAmt + '\'' +
                ", OrderCurrency='" + OrderCurrency + '\'' +
                '}';
    }
}
