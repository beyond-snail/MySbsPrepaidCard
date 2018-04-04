package com.mysbsprepaidcard.zfsbs.config;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.tool.utils.utils.StringUtils;


public class Constants {

//	public static String FUSM_URL = "http://116.239.4.195:28164/micropay";
//	public static String FUQUERY_URL = "http://116.239.4.195:28164/commonQuery";
//	public static String FUREFUND_URL = "http://116.239.4.195:28164/commonRefund";
//	public static String FUSM_URL = "http://192.168.1.253:8080/ZFTiming/api/fyTrade/pay";
//	public static String FUQUERY_URL = "http://192.168.1.253:8080/ZFTiming/api/fyTrade/query";
//	public static String FUREFUND_URL = "http://192.168.1.253:8080/ZFTiming/api/fyTrade/refund";
//	//富友 公私钥
//	public static String FY_RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALi+gZ6+Y3UAao1IGkVPKVGeqdA+j0i4ebwJ4Fp+7Sn7Ys0TzO6X3maH01MzsgVVk/26QUf3QTvifXlZoDGp447oCB1SelZDRrVD69Bl/5unqH9BCbOweJqrWvb+OzzWRRpBHWhIqPPdbIFgm675BMR+7rxB+pvMxy6/GzopoYUvAgMBAAECgYB5MyowbbEsCijdJUdu3v85d8DqSJCR4cyja0tPs2N+Hlj0N6BDi0ixtTwTop+Q1lLvq2i2gOTAF9e/a+gnjNAnS2YuoUpGKgKgKxcWs/RZLrV98AShaMLuiHuUpVPovCMpY02OzY/bvlnDZng8/ON4xrygHtSq1Hc3FaLCqRcuMQJBAO2PD7xvecVNRLkMyRxfVlTwGDB7jrI2WpLqQ1Xh4orWpkzr+tu8Hod65eoiMdcslHw4QJJ3wadLX+Jg9jLQj9kCQQDHFd/Flk41lChu3f5hQH86KUi4GLg1Pl3SfWa66rSvTrhRJ/EZ+r2QvQDqKRY2o8NSXY7tQdu6vJ86yuxrsqBHAkBX/CGW6C7QraKjayHdeU4PXXGIG2sphEodmdhgqa3vQDsNyGT8F3uzMAiRpCKTkHZaX7dCyEoYPSmBPepRvb9ZAkA5HORaOblsE3nZ/GOKoMce91L+RVErLR4bUZBUFRsKo8mqtourSUxyplYJ1wmhWS+ihGaJV/hiRNRlOGvEpbTnAkAlwLoIdf3R4tH3uV2tE9l5WpJ75fxNd2Atl/ED1o78O3W6QOXJo/ulfByQmV3laUM5U+8OFynVwck2xEmt0Tzp";
//	public static String FY_RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4voGevmN1AGqNSBpFTylRnqnQPo9IuHm8CeBafu0p+2LNE8zul95mh9NTM7IFVZP9ukFH90E74n15WaAxqeOO6AgdUnpWQ0a1Q+vQZf+bp6h/QQmzsHiaq1r2/js81kUaQR1oSKjz3WyBYJuu+QTEfu68QfqbzMcuvxs6KaGFLwIDAQAB";
//

	public static final boolean isUsedQb = true; //是否使用钱包


	public static final String MERCHANT_NAME = "merchant_no";
	public static final String HS_MID1 = "hs_mid1";
	public static final String HS_TID1 = "hs_tid1";
	public static final String HS_MID2 = "hs_mid2";
	public static final String HS_TID2 = "hs_tid2";
	public static final String HS_IP = "hs_ip";
	public static final String HS_PORT = "hs_port";
	public static final String HS_TPDU = "hs_tpdu";
	public static final String HS_AUTH1 = "hs_auth1";
	public static final String HS_AUTH2 = "hs_auth2";

	public static final String DEFALULT_MERCHANT_NAME = "测试商户名";
	public static final String DEFAULT_HS_AUTH1 = "0000000000000";
	public static final String DEFAULT_HS_AUTH2 = "0000000000000";
	public static final String DEFAULT_HS_MID1 = "000000000000000";
	public static final String DEFAULT_HS_TID1 = "00000000";
	public static final String DEFAULT_HS_MID2 = "000000000000000";
	public static final String DEFAULT_HS_TID2 = "00000000";
	public static final String DEFAULT_HS_IP = "58.246.226.173";// 测试环境："180.168.215.67";
	public static final String DEFAULT_HS_PORT = "8888";// "40004";//"10004";
	public static final String DEFAULT_HS_TPDU = "6000030000";

	public static final String SQB_ACTIVATE_CODE = "sqb_activate_code"; // 激活码
	public static final String SQB_VENDOR_ID = "sqb_vendor_id";// 商户ID
	public static final String SQB_VENDOR_KEY = "sqb_vendor_key"; // 商户key
	public static final String SQB_SUBJECT = "sqb_subject";
	public static final String SQB_DESCRIPTION = "sqb_description";

	public static final String DEFALULT_SQB_ACTIVATE_CODE = "25923953";
	public static final String DEFALULT_SQB_VENDOR_ID = "91800001";
	public static final String DEFALULT_SQB_VENDOR_KEY = "35a33367f12c5b368d136b849c05e21b";
	public static final String DEFALULT_SQB_SUBJECT = "消费";
	public static final String DEFALULT_SQB_DESCRIPTION = "测试本次消费情况";

	public static final String OPERATOR_PASS = "operator_pass";
	public static final String DEFAULT_OPERATOR_PASS = "666666";

	public static final String MASTER_PASS = "master_pass";
	public static final String DEFAULT_MASTER_PASS = "123456";

	public static final boolean isDownMaster = false; // 标识是否下载主密钥




	public static final int PAY_WAY_ALY = 1; // 支付宝
	public static final int PAY_WAY_WX = 3; // 微信
	public static final int PAY_WAY_BFB = 4; // 百度
	public static final int PAY_WAY_JD = 5; // 京东
	public static final int PAY_WAY_FLOT = 6; // 刷卡
	public static final int PAY_WAY_CASH = 7; // 现金
	public static final int PAY_WAY_QB = 13; //钱包

	public static final int PAY_WAY_UNDO = 10; //撤销
	public static final int PAY_WAY_REFUND_WX = 11; //微信退款
	public static final int PAY_WAY_REFUND_ALY = 12; //支付宝退款
	public static final int PAY_WAY_REFUND_QB = 14; //钱包退款

	public static final int PAY_WAY_PAY_FLOT = 8; //刷卡记账
	public static final int PAY_WAY_RECHARGE_WX = 26; //微信充值
	public static final int PAY_WAY_RECHARGE_ALY = 27; //支付宝充值
	public static final int PAY_WAY_RECHARGE_CASH = 28; //现金充值
	public static final int PAY_WAY_STK = 29; //会员卡

	//富友双商户 密钥索引对应
	public static final int FY_INDEX_0 = 0;
	public static final int FY_INDEX_1 = 1;



	//扫码类型
	public static final String SM_TYPE_SQB = "1";
	public static final String SM_TYPE_FY = "2";

	public static final int REQUEST_CAPTURE_WX = 0;
	public static final int REQUEST_CAPTURE_ALY = 1;
	public static final int REQUEST_CAPTURE_QB = 2;
	public static final int REQUEST_CASH = 4; //现金
	public static final int REQUEST_flot_CASH = 5; //现金

	//富友扫码支付方式
	public static final String PAY_FY_WX = "WECHAT";
	public static final String PAY_FY_ALY = "ALIPAY";

	public static final String FY_INS_CD = "fy_ins_cd"; //机构号
	public static final String FY_MERCHANT_NO = "fy_merchant_no"; //商户号
	public static final String FY_IP = "fy_ip"; //IP 或者 域名

	public static final String DEFAULT_INS_CD = "08M0064989";
	public static final String DEFAULT_FY_MERCHANT_NO = "0001100F0370523";
//	public static final String DEFAULT_FY_IP = "121.40.224.25:9091"; //测试
	public static final String DEFAULT_FY_IP = "121.40.64.120:9090";



	//末笔数据查询
	public static final String TERMINAL_QUERY = "terminal_query";
	public static final String MEMBER_DATA = "member_data";

	public static final String CURRENT_MEMBER_BACK = "current_member_back";
	public static final String CURRENT_Failure_BACK = "current_failure_back";

	//富友扫码失败类型
	public static final int FY_FAILURE_PAY = 1; //支付失败
	public static final int FY_FAILURE_QUERY = 2; //查询失败


	// 收钱吧
	// public static final int PAY_ALY = 1;
	// public static final int PAY_QQ = 3;
	// public static final int PAY_BFB = 4;
	// public static final int PAY_JD = 5;
	public static final boolean isActivate = false;
	public static final String ACTIVATE_FLAG = "activate";

	public static final String HS_LOGIN_TIME = "hs_login_time";
	public static final String DEFAULT_HS_LOGIN_TIME = "19700101";
	public static final String SBS_LOGIN_TIME = "sbs_login_time";
	public static final String DEFAULT_SBS_LOGIN_TIME = "19700101000000";



	public static final String SBS_LOGIN_TIME1 = "sbs_login_time";
	public static final String DEFAULT_SBS_LOGIN_TIME1 = "19700101000000";

	public static final String USER_NAME = "user_name";
	public static final String USER_PSW = "user_psw";


	public static final String SHIFT_ROOM_TIME = "shift_time"; //班接时间
	public static final String DEFAULT_SHIFT_ROOM_TIME = "19700101000000";
	public static final int er_width = 200;
	public static final int er_height = 200;


	public static  final int PRINTER_SHIFT_ROOM = 1;
	public static  final int PRINTER_SHIFT_ROOM_DAY = 2;



	// @SuppressLint("NewApi")
	// public static String creatKsnNo() {
	//
	//// return "5012" + android.os.Build.SERIAL.substring(2, 6) +
	// android.os.Build.SERIAL.substring(8, 16);
	// return android.os.Build.SERIAL;
	// }



	public static Bitmap ImageLoad(String url) {
		if (!StringUtils.isEmpty(url)) {
			ImageSize mImageSize = new ImageSize(er_width, er_height);
			Bitmap bmp = ImageLoader.getInstance().loadImageSync(url, mImageSize);
			return bmp;
		}
		return null;
	}

	public static String getPayWayDesc(int type) {

		String str = "";

		switch (type) {
			case PAY_WAY_FLOT:
				str += "刷卡/插卡";
				break;
			case PAY_WAY_UNDO:
				str += "刷卡/插卡(已撤销)";
				break;
			case PAY_WAY_ALY:
				str += "支付宝";
				break;
			case PAY_WAY_WX:
				str += "微信";
				break;
			case PAY_WAY_BFB:
				str += "百度钱包";
				break;
			case PAY_WAY_JD:
				str += "京东";
				break;
			case PAY_WAY_CASH:
				str += "现金";
				break;
			case PAY_WAY_PAY_FLOT:
				str += "刷卡记账";
				break;
			case PAY_WAY_QB:
				str += "钱包";
				break;
			case PAY_WAY_STK:
				str += "会员卡";
				break;
			case PAY_WAY_RECHARGE_WX:
				str += "微信充值";
				break;
			case PAY_WAY_RECHARGE_ALY:
				str += "支付宝充值";
				break;
			case PAY_WAY_RECHARGE_CASH:
				str += "现金充值";
				break;
			default:
				break;
		}

		return str;
	}

}
