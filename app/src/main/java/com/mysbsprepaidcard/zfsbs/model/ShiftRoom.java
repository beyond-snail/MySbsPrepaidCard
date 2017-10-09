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

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**********************************************************
 * *
 * Created by wucongpeng on 2017/2/23.        *
 **********************************************************/


public class ShiftRoom extends DataSupport implements Serializable {

    private ShiftCount pay_cash;
    private ShiftCount pay_swipe;
    private ShiftCount pay_wx;
    private ShiftCount pay_aly;
    private ShiftCount pay_qb;
    private ShiftCount pay_unswipe;
    private ShiftCount pay_unwx;
    private ShiftCount pay_unaly;
    private ShiftCount pay_unqb;
    private ShiftCount total;


    public ShiftCount getPay_cash() {
        return pay_cash;
    }

    public void setPay_cash(ShiftCount pay_cash) {
        this.pay_cash = pay_cash;
    }

    public ShiftCount getPay_swipe() {
        return pay_swipe;
    }

    public void setPay_swipe(ShiftCount pay_swipe) {
        this.pay_swipe = pay_swipe;
    }

    public ShiftCount getPay_wx() {
        return pay_wx;
    }

    public void setPay_wx(ShiftCount pay_wx) {
        this.pay_wx = pay_wx;
    }

    public ShiftCount getPay_aly() {
        return pay_aly;
    }

    public void setPay_aly(ShiftCount pay_aly) {
        this.pay_aly = pay_aly;
    }

    public ShiftCount getPay_qb() {
        return pay_qb;
    }

    public void setPay_qb(ShiftCount pay_qb) {
        this.pay_qb = pay_qb;
    }

    public ShiftCount getPay_unswipe() {
        return pay_unswipe;
    }

    public void setPay_unswipe(ShiftCount pay_unswipe) {
        this.pay_unswipe = pay_unswipe;
    }

    public ShiftCount getPay_unwx() {
        return pay_unwx;
    }

    public void setPay_unwx(ShiftCount pay_unwx) {
        this.pay_unwx = pay_unwx;
    }

    public ShiftCount getPay_unaly() {
        return pay_unaly;
    }

    public void setPay_unaly(ShiftCount pay_unaly) {
        this.pay_unaly = pay_unaly;
    }

    public ShiftCount getPay_unqb() {
        return pay_unqb;
    }

    public void setPay_unqb(ShiftCount pay_unqb) {
        this.pay_unqb = pay_unqb;
    }

    public ShiftCount getTotal() {
        return total;
    }

    public void setTotal(ShiftCount total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "ShiftRoom{" +
                "pay_cash=" + pay_cash +
                ", pay_swipe=" + pay_swipe +
                ", pay_wx=" + pay_wx +
                ", pay_aly=" + pay_aly +
                ", pay_qb=" + pay_qb +
                ", pay_unswipe=" + pay_unswipe +
                ", pay_unwx=" + pay_unwx +
                ", pay_unaly=" + pay_unaly +
                ", pay_unqb=" + pay_unqb +
                ", total=" + total +
                '}';
    }
}
