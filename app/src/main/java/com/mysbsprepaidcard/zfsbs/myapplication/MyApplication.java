package com.mysbsprepaidcard.zfsbs.myapplication;

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


import com.mysbsprepaidcard.zfsbs.core.action.SbsAction;
import com.mysbsprepaidcard.zfsbs.model.LoginApiResponse;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tool.utils.utils.ALog;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.List;

/**********************************************************
 * *
 * Created by wucongpeng on 2016/11/1.        *
 **********************************************************/


public class MyApplication extends LitePalApplication {

    //    private EmvImpl emvImpl;
    private SbsAction sbsAction;
    private static LoginApiResponse loginData;
    private static MyApplication instance = null;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

//        emvImpl = new EmvImpl(this);
        sbsAction = new SbsAction();

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).threadPoolSize(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);

        new ALog.Builder(this)
                .setLogSwitch(true)// 设置log总开关，默认开
                .setGlobalTag("")// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头部是否显示，默认显示
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setLogFilter(ALog.V);// log过滤器，和logcat过滤器同理，默认Verbose
    }

//
//    public EmvImpl getEmvImpl() {
//        return emvImpl;
////    }


    public SbsAction getSbsAction() {
        return sbsAction;
    }

    public LoginApiResponse getLoginData() {
        if (loginData == null) {
            // 从数据库中读取
            List<LoginApiResponse> list = DataSupport.findAll(LoginApiResponse.class);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isCurrent()) {
                    loginData = list.get(i);
                    break;
                }
            }
        }
        return loginData;
    }

//    public LoginApiResponse getLoginData() {
////        if (loginData == null) {
////            // 从数据库中读取
////            List<LoginApiResponse> list = DataSupport.findAll(LoginApiResponse.class);
////            for (int i = 0; i < list.size(); i++) {
////                if (list.get(i).isCurrent()) {
////                    loginData = list.get(i);
////                    break;
////                }
////           }
////        }
//
//        boolean isMember = (boolean) SPUtils.get(this, Constants.isMember, false);
//
//        if (isMember){
//            List<LoginApiResponse> list = DataSupport.findAll(LoginApiResponse.class);
//            loginData = list.get(Constants.FY_INDEX_1);
//        }else {
//            List<LoginApiResponse> list = DataSupport.findAll(LoginApiResponse.class);
//            loginData = list.get(Constants.FY_INDEX_0);
//        }
//
//        return loginData;
//    }



    public void setLoginData(LoginApiResponse Data) {
        loginData = Data;
    }
}
