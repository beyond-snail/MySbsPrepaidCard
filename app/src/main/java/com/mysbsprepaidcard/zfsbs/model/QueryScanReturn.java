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
 * Created by wucongpeng on 2016/11/17.        *
 **********************************************************/


public class QueryScanReturn {
    private int scanPayType; //扫码支付类型 1:收钱吧 2：富友
    private String fyMerchantNo; //富友商户号
    private String fyMerchantName; //富友商户名称

    public int getScanPayType() {
        return scanPayType;
    }

    public void setScanPayType(int scanPayType) {
        this.scanPayType = scanPayType;
    }

    public String getFyMerchantNo() {
        return fyMerchantNo;
    }

    public void setFyMerchantNo(String fyMerchantNo) {
        this.fyMerchantNo = fyMerchantNo;
    }

    public String getFyMerchantName() {
        return fyMerchantName;
    }

    public void setFyMerchantName(String fyMerchantName) {
        this.fyMerchantName = fyMerchantName;
    }

    @Override
    public String toString() {
        return "QueryScanReturn{" +
                "scanPayType=" + scanPayType +
                ", fyMerchantNo='" + fyMerchantNo + '\'' +
                ", fyMerchantName='" + fyMerchantName + '\'' +
                '}';
    }
}
