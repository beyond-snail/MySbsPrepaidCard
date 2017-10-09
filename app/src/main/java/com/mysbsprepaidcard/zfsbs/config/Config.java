package com.mysbsprepaidcard.zfsbs.config;

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
 * Created by wucongpeng on 2016/11/13.        *
 **********************************************************/


public class Config {

    /**
     * 商博士配置
     */
    public static final String md5_key = "dtradezaq12wsx";
    public final static String SBS_URL = "http://api.eboss007.com/api/index.php";//"http://api.eboss007.com/api/index.php";//"http://sbs.xun-ao.com/dtrade/api/";//"http://wqapi.xun-ao.com/";// "http://wqapi.xun-ao.com/";//"http://sbs.xun-ao.com/dtrade/api/";//"http://121.40.224.25:83/dtrade/api/";//"http://sbs.xun-ao.com/dtrade/api/";//"http://www.eboss007.com/dtrade/api/";//"http://wqapi.xun-ao.com/index.php/api/index";
    public final static String SBS_URL_QB = SBS_URL;


    //配置当前为哪个收单，确定一个其他都为false
    public static final boolean isHs = true;
    public static final boolean isFy = false;
    public static final boolean isLianDong = false;

    //兼容之前的登录界面 同时如果是操作员界面需要手动去manifest文件修正 "OperatorLoginActivity" =》 "OperatorLoginActivity1"
    public static final boolean OPERATOR_UI_BEFORE = false;

    //是否签名
    public static final boolean isSign = false;


    //当前的UI
    public static final int APP_SBS_UI_SBS = 0;
    public static final int APP_SBS_UI_YXF = 1;
    public static final int APP_SBS_UI_RICHER_E = 2;
    public static final int APP_SBS_UI_HD = 3;
    public static final int APP_LIANDONG = 4;

    public static final int APP_UI = APP_SBS_UI_SBS;


    /**
     * 设置当前应用操作方式，后续可继续添加
     */
    public static final int APP_SBS = 0;
    public static final int APP_YXF = 1; //赢消费
    public static final int APP_Richer_e = 2; //富人e
    public static final int APP_HD = 3; //海鼎

    public static final String APP_TYPE = "app_type";  //app_type
    public static int DEFAULT_APP_TYPE = APP_SBS;


    //    //赢消费商户ID
    public static final String YXF_MERCHANT_ID = "yxfMerchantId";
    public static final String YXF_DEFAULT_MERCHANTID = "";

    public static final String YXF_URL = "http://www.zyxdzswzh.com/zhangfu.php";
    public static final String YXF_KEY = "asdfasdfasdfas6546554646asdfwe";


    //是否是海鼎会员
    public static final String isHdMember = "isHdMember";


    //富友收单
    public static final String FY_DOWN_MASTER = "isDownMasterkey";
    public static final String FY_M_DOWN_MASTER = "isDownMasterkey_m";


    public static final String AID_KEY = "aid_key";
    public static final String CPK_KEY = "cpk_key";
    public static final String BLACKLIST_KEY = "blacklist_key";

    //时间设定
    public static final String FY_LOGIN_TIME = "fy_login_time";
    public static final String DEFAULT_FY_LOGIN_TIME = "19700101";


}



























