package com.tool.utils.utils;

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
 *                  Created by wucongpeng on 2017/3/28.        *
 **********************************************************/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 发送广播简易工具类
 * Created by wucongpeng on 2017/3/28.
 */

public class BroadcastUtil {

    /**
     * 构造函数设为private，防止外部实例化
     */
    private void BroadcastManager() {
    }

    /**
     * 注册广播接收器
     *
     * @param ctx       Context(不可空)
     * @param iReceiver IReceiver(不可空)
     * @param action    Intent.Action(不可空)
     */
    public static void registerReceiver(Context ctx, final IReceiver iReceiver, final String action) {
        if (ctx == null || iReceiver == null || action == null) throw new IllegalArgumentException("使用registerReceiver()注册广播时，参数Context、IReceiver和Action不能为空！");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                iReceiver.onReceive(context, intent);
            }
        };
        IntentFilter filter = new IntentFilter(action);
        ctx.registerReceiver(receiver, filter);
    }

    /**
     * 发送广播
     *
     * @param ctx    Context(不可空)
     * @param i      Intent（可为null），不需要设置Action，请把Action作为参数传入
     * @param action Intent.Action（不可空）
     */
    public static void send(Context ctx, Intent i, final String action) {
        if (ctx == null || action == null) throw new IllegalArgumentException("使用send()发送广播时，参数Context和Action不能为空！");

        if (i == null) i = new Intent();
        i.setAction(action);
        ctx.sendBroadcast(i);
    }


    /**
     * 接收广播的页面需要实现，把接收后的操作覆写在OnReceive()方法里
     */
    public interface IReceiver {
        void onReceive(Context ctx, Intent intent);
    }
}
