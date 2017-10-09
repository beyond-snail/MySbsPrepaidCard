package com.mysbsprepaidcard.zfsbs.core.action;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.mysbsprepaidcard.zfsbs.config.Config;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.model.SbsPrinterData;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoom;
import com.tool.utils.dialog.SignDialog;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;
import com.unionpay.cloudpos.wizarposexternal.DeviceException;
import com.unionpay.cloudpos.wizarposexternal.POSTerminal;
import com.unionpay.cloudpos.wizarposexternal.printer.Format;
import com.unionpay.cloudpos.wizarposexternal.printer.PrinterDevice;


public class Printer {

    private static final String TAG = "Printer";

    private static Printer printer;
    private static Context mContext;
    private static PrinterDevice printerDevice;
    private static Format format;
    private static boolean isPrinter = false; // 标识正在打印

    private Printer(Context context) {
        mContext = context;
        printerDevice = (PrinterDevice) POSTerminal.getInstance(context.getApplicationContext())
                .getDevice("cloudpos.device.printer");
    }

    public synchronized static Printer getInstance(Context context) {
        if (printer == null) {
            printer = new Printer(context);
        }
        return printer;
    }

    private static Handler handler = new Handler();

    private static Runnable myRunnable = new Runnable() {
        public void run() {
            ToastUtils.CustomShowLong(mContext, "打印机缺纸...");
            isPrinter = false;
        }
    };

    public static void print(final SbsPrinterData printerData, final Context context) {

        if (Config.isSign) {

            if (printerData.getSign_bitmap() == null) {
                SignDialog dialog = new SignDialog(context, new SignDialog.OnClickInterface() {
                    @Override
                    public void onClickSure(Bitmap bitmap) {
                        printerData.setSign_bitmap(bitmap);

                        print1(printerData);
                    }

                });
                dialog.setCancelable(false);
                dialog.show();
            } else {
                print1(printerData);
            }
        }else {
            print1(printerData);
        }



    }


    public static void print1(final SbsPrinterData printerData){
        if (isPrinter){return;}
        isPrinter = true;
        try {
            printerDevice.open();
            LogUtils.e("printerStatus: " + printerDevice.queryStatus());
            try {
                if (printerDevice.queryStatus() == -101 || printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
                    handler.post(myRunnable);
                } else if (printerDevice.queryStatus() == -102) {
                    ToastUtils.CustomShow(mContext, "打印机状态异常...");
                    close();
                } else if (printerDevice.queryStatus() == printerDevice.STATUS_PAPER_EXIST) {

                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {

                            printer(printerData);
                            LogUtils.e(TAG, "打印完成。。。");

                            try {
                                if (printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
                                    LogUtils.e("打印机没纸");
                                    handler.post(myRunnable);
                                }
                            } catch (DeviceException e) {
                                e.printStackTrace();
                                isPrinter = false;

                            }
                            isPrinter = false;

                            close();

                        }
                    });
                    thread.start();
                }
            } catch (DeviceException de) {
                isPrinter = false;

                ToastUtils.CustomShow(mContext, "查看打印机状态失败...");
                de.printStackTrace();
                close();
            }
        } catch (DeviceException de) {
            isPrinter = false;

            de.printStackTrace();
            ToastUtils.CustomShow(mContext, "打印机设备打开失败...请重试。");
            close();
        }
    }



//    public static void printSettle(final ComSettleInfo comSettleInfo){
//        if (isPrinter){return;}
//        isPrinter = true;
//        try {
//            printerDevice.open();
//            LogUtils.e("printerStatus: " + printerDevice.queryStatus());
//            try {
//                if (printerDevice.queryStatus() == -101 || printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
//                    handler.post(myRunnable);
//                } else if (printerDevice.queryStatus() == -102) {
//                    ToastUtils.CustomShow(mContext, "打印机状态异常...");
//                    close();
//                } else if (printerDevice.queryStatus() == printerDevice.STATUS_PAPER_EXIST) {
//
//                    Thread thread = new Thread(new Runnable() {
//
//                        @Override
//                        public void run() {
//
////                            printerShiftRoom(printerData, start_time, end_time, type);
//                            printerSettle(comSettleInfo);
//
//                            try {
//                                if (printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
//                                    LogUtils.e("打印机没纸");
//                                    handler.post(myRunnable);
//                                }
//                            } catch (DeviceException e) {
//                                e.printStackTrace();
//                                isPrinter = false;
//
//                            }
//                            isPrinter = false;
//
//                            close();
//
//                        }
//                    });
//                    thread.start();
//                }
//            } catch (DeviceException de) {
//                isPrinter = false;
//
//                ToastUtils.CustomShow(mContext, "查看打印机状态失败...");
//                de.printStackTrace();
//                close();
//            }
//        } catch (DeviceException de) {
//            isPrinter = false;
//
//            de.printStackTrace();
//            ToastUtils.CustomShow(mContext, "打印机设备打开失败...请重试。");
//            close();
//        }
//    }



    public static void printShiftRoom(final ShiftRoom printerData, final long start_time, final long end_time, final int type) {
        if (isPrinter){return;}
        isPrinter = true;
        try {
            printerDevice.open();
            LogUtils.e("printerStatus: " + printerDevice.queryStatus());
            try {
                if (printerDevice.queryStatus() == -101 || printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
                    handler.post(myRunnable);
                } else if (printerDevice.queryStatus() == -102) {
                    ToastUtils.CustomShow(mContext, "打印机状态异常...");
                    close();
                } else if (printerDevice.queryStatus() == printerDevice.STATUS_PAPER_EXIST) {

                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {

                            printerShiftRoom(printerData, start_time, end_time, type);

                            try {
                                if (printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
                                    LogUtils.e("打印机没纸");
                                    handler.post(myRunnable);
                                }
                            } catch (DeviceException e) {
                                e.printStackTrace();
                                isPrinter = false;

                            }
                            isPrinter = false;

                            close();

                        }
                    });
                    thread.start();
                }
            } catch (DeviceException de) {
                isPrinter = false;

                ToastUtils.CustomShow(mContext, "查看打印机状态失败...");
                de.printStackTrace();
                close();
            }
        } catch (DeviceException de) {
            isPrinter = false;

            de.printStackTrace();
            ToastUtils.CustomShow(mContext, "打印机设备打开失败...请重试。");
            close();
        }

    }




    public static void close() {
        try {
            printerDevice.close();

        } catch (DeviceException e) {
            ToastUtils.CustomShow(mContext, "打印设备关闭失败...");
        }
    }

    private static String getTransTypeStr(int type) {

        String str = "";

        switch (type) {
            case 1:
                str += "消费（SALE）";
                break;
            case 13:
                str += "消费撤销";
            default:
                break;
        }

        return str;
    }


    private static void printerShiftRoom(ShiftRoom data, long start_time, long end_time, int type){
        if (Constants.PRINTER_SHIFT_ROOM == type){
            printer_shift_room(data, start_time, end_time);
        }else if (Constants.PRINTER_SHIFT_ROOM_DAY == type){
            printer_shift_room_day(data, start_time, end_time);
        }
    }




    private static void printer(SbsPrinterData printerData) {
        switch (printerData.getPayType()) {
            case Constants.PAY_WAY_FLOT:
            case Constants.PAY_WAY_UNDO:
                printer_flot(printerData);
                break;
            case Constants.PAY_WAY_ALY:
            case Constants.PAY_WAY_WX:
            case Constants.PAY_WAY_BFB:
            case Constants.PAY_WAY_JD:
            case Constants.PAY_WAY_QB:
            case Constants.PAY_WAY_STK:
            case Constants.PAY_WAY_RECHARGE_ALY:
            case Constants.PAY_WAY_RECHARGE_WX:

                printer_bat(printerData);
                break;
            case Constants.PAY_WAY_CASH:
            case Constants.PAY_WAY_RECHARGE_CASH:
            case Constants.PAY_WAY_PAY_FLOT:

                printer_cash(printerData);
                break;
            default:
                break;
        }
    }

    private static void printer_flot(SbsPrinterData printerData) {
        try {
            format = new Format();
            format.setParameter("align", "center");
            format.setParameter("bold", "true");
            format.setParameter("size", "large");
            printerDevice.printlnText(format, "POS签购单");
            printerDevice.printlnText("");
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "商户名称：");
            if (!StringUtils.isEmpty(printerData.getMerchantName())) {
                printerDevice.printlnText(format, printerData.getMerchantName());
            }
//            if (!StringUtils.isEmpty(printerData.getMerchantNo())) {
//                printerDevice.printlnText(format, "商户编号：" + printerData.getMerchantNo());
//            }
            if (!StringUtils.isEmpty(printerData.getTerminalId())) {
                printerDevice.printlnText(format, "终端编号：" + printerData.getTerminalId());
            }
            if (!StringUtils.isEmpty(printerData.getOperatorNo())) {
                printerDevice.printlnText(format, "操作员号：" + printerData.getOperatorNo());
            }
            if (!StringUtils.isEmpty(printerData.getIssuer())) {
                printerDevice.printlnText(format, "发卡行号：" + printerData.getIssuer());
            }
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            format.setParameter("bold", "true");
            format.setParameter("density", "dark");
            // printerDevice.printlnText(format,
            // "发卡行：中国工商银行");
            if (!StringUtils.isEmpty(printerData.getCardNo())) {
                printerDevice.printlnText(format, "卡号：" + printerData.getCardNo());
            }
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            if (!StringUtils.isEmpty(printerData.getAcquirer())) {
                printerDevice.printlnText(format, "收单行号：" + printerData.getAcquirer());
            }
            if (!StringUtils.isEmpty(printerData.getTransType())) {
                printerDevice.printlnText(format,
                        "交易类型：" + getTransTypeStr(Integer.parseInt(printerData.getTransType())));
            }
            if (!StringUtils.isEmpty(printerData.getExpDate())) {
                printerDevice.printlnText(format, "有效期：" + printerData.getExpDate());
            }
            if (!StringUtils.isEmpty(printerData.getBatchNO())) {
                printerDevice.printlnText(format, "批次号：" + printerData.getBatchNO());
            }
            if (!StringUtils.isEmpty(printerData.getVoucherNo())) {
                printerDevice.printlnText(format, "凭证号：" + printerData.getVoucherNo());
            }
            if (!StringUtils.isEmpty(printerData.getReferNo())) {
                printerDevice.printlnText(format, "参考号：" + printerData.getReferNo());
            }
            if (!StringUtils.isEmpty(printerData.getClientOrderNo())) {
                printerDevice.printlnText(format, "订单号：" + printerData.getClientOrderNo());
            }
            if (!StringUtils.isEmpty(printerData.getDateTime())) {
                printerDevice.printlnText(format, "日期时间：" + printerData.getDateTime());
            }
//			format.clear();
//			format.setParameter("align", "left");
//			format.setParameter("size", "medium");
//			format.setParameter("density", "dark");
//			format.setParameter("bold", "true");
//			printerDevice.printlnText(format, "金额（AMOUNT）：" + printerData.getAmount());
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            printerDevice.printlnText(format, "订单金额：" + StringUtils.formatIntMoney(printerData.getOrderAmount()) + "元");
            printerDevice.printlnText(format, "支付金额：" + printerData.getAmount()+"元");
            if (printerData.getApp_type() == Config.APP_HD) {
                printerDevice.printlnText(format, "获得积分：" + printerData.getPoint());
                printerDevice.printlnText(format, "积分余额：" + printerData.getPointCurrent());
            }else {
                printerDevice.printlnText(format, "积分抵扣金额：" + StringUtils.formatIntMoney(printerData.getPointCoverMoney()) + "元");
                printerDevice.printlnText(format, "优惠券抵扣金额：" + StringUtils.formatIntMoney(printerData.getCouponCoverMoney()) + "元");
                printerDevice.printlnText(format, "返利金额：" + StringUtils.formatIntMoney(printerData.getBackAmt()) + "元");
            }
            printerDevice.printlnText(format, "支付方式：" + Constants.getPayWayDesc(printerData.getPayType()));
            // printerDevice.printlnText(format, "备注：");
            printerDevice.printText(format, "\n");
            // printerDevice.printText(format,
            // "AID：A00000033310101\n");
            // printerDevice.printText(format, "ARQC：TC\n");
            printerDevice.printText(format, "持卡人签名：\n");
            printerDevice.printText(format, "CARD HOLDER SIGNATURE\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printlnText(format, "--------------------------------");

            printerDevice.printlnText(format, "备注：");
            format.clear();
            format.setParameter("align", "center");
            format.setParameter("size", "medium");
            if (printerData.getSign_bitmap() != null) {
                LogUtils.e("width: " + printerData.getSign_bitmap().getWidth() + " height: "
                        + printerData.getSign_bitmap().getHeight());
                printerDevice.printBitmap(format, printerData.getSign_bitmap());
            }
            printerDevice.printText(format, "\n");
            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printText(format, "本人确认以上交易，同意将其记入本卡账户\n");
            printerDevice.printText(format, "I ACKNOWLEDGE SATISFACTORY RECEIPT OF RELATIVE GOODS/SERVICES\n");
            printerDevice.printText(format, "    \n");
            printerDevice.printText(format, "    \n");
            if (printerData.getApp_type() != Config.APP_HD) {
                format.clear();
                format.setParameter("align", "center");
                format.setParameter("size", "medium");
                if (printerData.getPoint_bitmap() != null) {
                    LogUtils.e("width: " + printerData.getPoint_bitmap().getWidth() + " height: "
                            + printerData.getPoint_bitmap().getHeight());
                    printerDevice.printlnText(format, "使用微信扫一扫 获取更多优惠信息");
                    printerDevice.printBitmap(format, printerData.getPoint_bitmap());
                }
                format.clear();
                format.setParameter("align", "left");
                format.setParameter("size", "medium");
                if (printerData.getPoint() > 0) {
                    printerDevice.printlnText(format, "--------------------------------");
                    printerDevice.printlnText(format, "本次获得积分：" + printerData.getPoint());
                }
                if (printerData.getPointCurrent() > 0) {
                    printerDevice.printlnText(format, "积分余额：" + printerData.getPointCurrent());
                }

                format.clear();
                format.setParameter("align", "center");
                format.setParameter("size", "medium");
                if (printerData.getCoupon_bitmap() != null) {
                    LogUtils.e("width: " + printerData.getCoupon_bitmap().getWidth() + " height: "
                            + printerData.getCoupon_bitmap().getHeight());
                    printerDevice.printBitmap(format, printerData.getCoupon_bitmap());
                }
                format.clear();
                format.setParameter("align", "left");
                format.setParameter("size", "medium");
                if (!StringUtils.isEmpty(printerData.getTitle_url())) {
                    printerDevice.printlnText(format, "--------------------------------");
                    printerDevice.printlnText(format, "优惠券名称：" + printerData.getTitle_url());
                }
                if (printerData.getMoney() > 0) {
                    printerDevice.printlnText(format, "优惠券金额：" + StringUtils.formatIntMoney(printerData.getMoney()));
                }
            }
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
        } catch (DeviceException e) {
            e.printStackTrace();
            try {
                if (printerDevice.queryStatus() == -101) {
                    ToastUtils.CustomShow(mContext, "打印机缺纸...");
                } else if (printerDevice.queryStatus() == -102) {

                    ToastUtils.CustomShow(mContext, "打印机状态异常，请退出...");
                } else {

                    ToastUtils.CustomShow(mContext, "打印失败，未知异常...");
                }
            } catch (DeviceException e1) {
                e1.printStackTrace();
                ToastUtils.CustomShow(mContext, "打印失败，请重新打印...");
            }
        }
    }

    private static void printer_bat(SbsPrinterData printerData) {
        try {
            format = new Format();
            format.setParameter("align", "center");
            format.setParameter("size", "large");
            printerDevice.printlnText(format, "POS签购单");
            printerDevice.printlnText(" ");
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "商户名称：");
            if (!StringUtils.isEmpty(printerData.getMerchantName())) {
                printerDevice.printlnText(format, printerData.getMerchantName());
            }
//            if (!StringUtils.isEmpty(printerData.getMerchantNo())) {
//                printerDevice.printlnText(format, "商户编号：" + printerData.getMerchantNo());
//            }
            if (!StringUtils.isEmpty(printerData.getTerminalId())) {
                printerDevice.printlnText(format, "终端编号：" + printerData.getTerminalId());
            }
            if (!StringUtils.isEmpty(printerData.getOperatorNo())) {
                printerDevice.printlnText(format, "操作员号：" + printerData.getOperatorNo());
            }
            if (!StringUtils.isEmpty(printerData.getClientOrderNo())) {
                printerDevice.printlnText(format, "商户订单号：" + printerData.getClientOrderNo());
            }
            if (!StringUtils.isEmpty(printerData.getAuthCode())) {
                printerDevice.printlnText(format, "第三方订单号：" + printerData.getAuthCode());
            }
            if (!StringUtils.isEmpty(printerData.getDateTime())) {
                printerDevice.printlnText(format, "日期时间：" + printerData.getDateTime());
            }
//			format.clear();
//			format.setParameter("align", "left");
//			format.setParameter("size", "medium");
//			format.setParameter("density", "dark");
//			format.setParameter("bold", "true");
//			printerDevice.printlnText(format, "金额（AMOUNT）：" + printerData.getAmount());
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            if (printerData.getPayType() == Constants.PAY_WAY_RECHARGE_ALY || printerData.getPayType() == Constants.PAY_WAY_RECHARGE_WX ||
                    printerData.getPayType() == Constants.PAY_WAY_STK){
                if (!StringUtils.isBlank(printerData.getRealize_card_num())){
                    printerDevice.printlnText(format, "会员卡号："+printerData.getRealize_card_num());
                }
                if (!StringUtils.isBlank(printerData.getMember_name())) {
                    printerDevice.printlnText(format, "会员姓名：" + printerData.getMember_name());
                }
                if (!StringUtils.isBlank(printerData.getMember_name())) {
                    printerDevice.printlnText(format, "充值金额：" + printerData.getAmount() + "元");
                }
                int largessAmount = printerData.getOrderAmount() - StringUtils.stringToInt(printerData.getAmount());
                if (largessAmount != 0) {
                    printerDevice.printlnText(format, "充值赠送：" + StringUtils.formatIntMoney(largessAmount) + "元");
                }
                if (printerData.getOrderAmount() != 0) {
                    printerDevice.printlnText(format, "到账金额：" + StringUtils.formatIntMoney(printerData.getOrderAmount()) + "元");
                }
//                printerDevice.printlnText(format, "充值金额：" + printerData.getAmount() + "元");
//                printerDevice.printlnText(format, "到账金额：" + StringUtils.formatIntMoney(printerData.getOrderAmount()) + "元");
                if (printerData.getPacektRemian() != 0) {
                    printerDevice.printlnText(format, "账号余额：" + StringUtils.formatIntMoney(printerData.getPacektRemian()) + "元");
                }

            }else {
                printerDevice.printlnText(format, "订单金额：" + StringUtils.formatIntMoney(printerData.getOrderAmount()) + "元");
                printerDevice.printlnText(format, "支付金额：" + printerData.getAmount() + "元");
                if (printerData.getPayType() == Constants.PAY_WAY_QB ){
                    if (printerData.getPacektRemian()!=0) {
                        printerDevice.printlnText(format, "会员账号余额：" + StringUtils.formatIntMoney(printerData.getPacektRemian()) + "元");
                    }
                }
            }
            if (printerData.getApp_type() == Config.APP_HD) {
                printerDevice.printlnText(format, "获得积分：" + printerData.getPoint());
                printerDevice.printlnText(format, "积分余额：" + printerData.getPointCurrent());
            }else {
                if (printerData.getPayType() != Constants.PAY_WAY_RECHARGE_ALY || printerData.getPayType() != Constants.PAY_WAY_RECHARGE_WX ) {
                    printerDevice.printlnText(format, "积分抵扣金额：" + StringUtils.formatIntMoney(printerData.getPointCoverMoney()) + "元");
                    printerDevice.printlnText(format, "优惠券抵扣金额：" + StringUtils.formatIntMoney(printerData.getCouponCoverMoney()) + "元");
                    printerDevice.printlnText(format, "返利金额：" + StringUtils.formatIntMoney(printerData.getBackAmt()) + "元");
                }
            }
            printerDevice.printlnText(format, "支付方式：" + Constants.getPayWayDesc(printerData.getPayType()));
            // printerDevice.printlnText(format, "备注：");
            printerDevice.printText("\n");
            // printerDevice.printText(format,
            // "AID：A00000033310101\n");
            // printerDevice.printText(format, "ARQC：TC\n");
            printerDevice.printText(format, "持卡人签名：\n");
            printerDevice.printText(format, "CARD HOLDER SIGNATURE\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printlnText(format, "--------------------------------");

            printerDevice.printlnText(format, "备注：");
            if(!StringUtils.isBlank(printerData.getPromotion_num())){
                printerDevice.printlnText(format, printerData.getPromotion_num());
            }
            format.clear();
            format.setParameter("align", "center");
            format.setParameter("size", "medium");
            if (printerData.getSign_bitmap() != null) {
                LogUtils.e("width: " + printerData.getSign_bitmap().getWidth() + " height: "
                        + printerData.getSign_bitmap().getHeight());
                printerDevice.printBitmap(format, printerData.getSign_bitmap());
            }
            printerDevice.printText(format, "\n");
            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printText(format, "本人确认以上交易，同意将其记入本卡账户\n");
            printerDevice.printText(format, "I ACKNOWLEDGE SATISFACTORY RECEIPT OF RELATIVE GOODS/SERVICES\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            if (printerData.getApp_type() != Config.APP_HD) {
                format.clear();
                format.setParameter("align", "center");
                format.setParameter("size", "medium");
                if (printerData.getPoint_bitmap() != null) {
                    LogUtils.e("width: " + printerData.getPoint_bitmap().getWidth() + " height: "
                            + printerData.getPoint_bitmap().getHeight());
                    printerDevice.printlnText(format, "使用微信扫一扫 获取更多优惠信息");
                    printerDevice.printBitmap(format, printerData.getPoint_bitmap());
                }
                format.clear();
                format.setParameter("align", "left");
                format.setParameter("size", "medium");
                if (printerData.getPoint() > 0) {
                    printerDevice.printlnText(format, "--------------------------------");
                    printerDevice.printlnText(format, "本次获得积分：" + printerData.getPoint());
                }
                if (printerData.getPointCurrent() > 0) {
                    printerDevice.printlnText(format, "积分余额：" + printerData.getPointCurrent());
                }

                format.clear();
                format.setParameter("align", "center");
                format.setParameter("size", "medium");
                if (printerData.getCoupon_bitmap() != null) {
                    LogUtils.e("width: " + printerData.getCoupon_bitmap().getWidth() + " height: "
                            + printerData.getCoupon_bitmap().getHeight());
                    printerDevice.printBitmap(format, printerData.getCoupon_bitmap());
                }
                format.clear();
                format.setParameter("align", "left");
                format.setParameter("size", "medium");
                if (!StringUtils.isEmpty(printerData.getTitle_url())) {
                    printerDevice.printlnText(format, "--------------------------------");
                    printerDevice.printlnText(format, "优惠券名称：" + printerData.getTitle_url());
                }
                if (printerData.getMoney() > 0) {
                    printerDevice.printlnText(format, "优惠券金额：" + StringUtils.formatIntMoney(printerData.getMoney()));
                }
            }
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
        } catch (DeviceException e) {
            e.printStackTrace();
            try {
                if (printerDevice.queryStatus() == -101) {
                    ToastUtils.CustomShow(mContext, "打印机缺纸...");
                } else if (printerDevice.queryStatus() == -102) {

                    ToastUtils.CustomShow(mContext, "打印机状态异常，请退出...");
                } else {

                    ToastUtils.CustomShow(mContext, "打印失败，未知异常...");
                }
            } catch (DeviceException e1) {
                e1.printStackTrace();
                ToastUtils.CustomShow(mContext, "打印失败，请重新打印...");
            }
        }
    }

    private static void printer_cash(SbsPrinterData printerData) {
        try {
            format = new Format();
            format.setParameter("align", "center");
            format.setParameter("size", "large");
            printerDevice.printlnText(format, "POS签购单");
            printerDevice.printlnText(" ");
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "商户名称：");
            if (!StringUtils.isEmpty(printerData.getMerchantName())) {
                printerDevice.printlnText(format, printerData.getMerchantName());
            }
//            if (!StringUtils.isEmpty(printerData.getMerchantNo())) {
//                printerDevice.printlnText(format, "商户编号：" + printerData.getMerchantNo());
//            }
            printerDevice.printlnText(format, "终端编号：" + printerData.getTerminalId());
            printerDevice.printlnText(format, "操作员号：" + printerData.getOperatorNo());
            printerDevice.printlnText(format, "日期时间：" + printerData.getDateTime());
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            format.setParameter("density", "dark");
            format.setParameter("bold", "true");
            if (!StringUtils.isEmpty(printerData.getClientOrderNo())) {
                printerDevice.printlnText(format, "订 单 号：" + printerData.getClientOrderNo());
            }

            if (printerData.getPayType() == Constants.PAY_WAY_RECHARGE_CASH || printerData.getPayType() == Constants.PAY_WAY_PAY_FLOT){
                printerDevice.printlnText(format, "会员卡号："+printerData.getRealize_card_num());
                printerDevice.printlnText(format, "会员姓名："+printerData.getMember_name());
                printerDevice.printlnText(format, "充值金额：" + printerData.getAmount() + "元");
                int largessAmount = StringUtils.stringToInt(printerData.getReceiveAmount()) - StringUtils.stringToInt(printerData.getAmount());
                printerDevice.printlnText(format, "充值赠送：" + StringUtils.formatIntMoney(largessAmount) + "元");
                printerDevice.printlnText(format, "到账金额：" +  printerData.getReceiveAmount() + "元");
                if (printerData.getPacektRemian() != 0) {
                    printerDevice.printlnText(format, "账号余额：" + StringUtils.formatIntMoney(printerData.getPacektRemian()) + "元");
                }
            }else{
                printerDevice.printlnText(format, "订单金额：" + printerData.getAmount() + "元");
                printerDevice.printlnText(format, "实收金额：" + printerData.getReceiveAmount() + "元");
            }
//			printerDevice.printlnText(format, "找零金额：" + printerData.getOddChangeAmout());
            format.clear();
            format.setParameter("align", "left");
            format.setParameter("size", "medium");
            if (printerData.getApp_type() == Config.APP_HD) {
                printerDevice.printlnText(format, "获得积分：" + printerData.getPoint());
                printerDevice.printlnText(format, "积分余额：" + printerData.getPointCurrent());
            }else {
                if (printerData.getPayType() != Constants.PAY_WAY_RECHARGE_CASH || printerData.getPayType() != Constants.PAY_WAY_PAY_FLOT) {
                    printerDevice.printlnText(format, "积分抵扣金额：" + StringUtils.formatIntMoney(printerData.getPointCoverMoney()) + "元");
                    printerDevice.printlnText(format, "优惠券抵扣金额：" + StringUtils.formatIntMoney(printerData.getCouponCoverMoney()) + "元");
                    printerDevice.printlnText(format, "返利金额：" + StringUtils.formatIntMoney(printerData.getBackAmt()) + "元");
                }
                printerDevice.printlnText(format, "支付方式：" + Constants.getPayWayDesc(printerData.getPayType()));
            }
            // printerDevice.printlnText(format, "备注：");
            printerDevice.printText(format, "\n");
            // printerDevice.printText(format,
            // "AID：A00000033310101\n");
            // printerDevice.printText(format, "ARQC：TC\n");
            printerDevice.printText(format, "持卡人签名：\n");
            printerDevice.printText(format, "CARD HOLDER SIGNATURE\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printlnText(format, "--------------------------------");

            printerDevice.printlnText(format, "备注：");
            if(!StringUtils.isBlank(printerData.getPromotion_num())){
                printerDevice.printlnText(format, printerData.getPromotion_num());
            }
            format.clear();
            format.setParameter("align", "center");
            format.setParameter("size", "medium");
            if (printerData.getSign_bitmap() != null) {
                LogUtils.e("width: " + printerData.getSign_bitmap().getWidth() + " height: "
                        + printerData.getSign_bitmap().getHeight());
                printerDevice.printBitmap(format, printerData.getSign_bitmap());
            }
//            printerDevice.printText(format, "\n");
            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printText(format, "本人确认以上交易，同意将其记入本卡账户\n");
            printerDevice.printText(format, "I ACKNOWLEDGE SATISFACTORY RECEIPT OF RELATIVE GOODS/SERVICES\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");

            if (printerData.getApp_type() != Config.APP_HD) {
                format.clear();
                format.setParameter("align", "center");
                format.setParameter("size", "medium");
                if (printerData.getPoint_bitmap() != null) {
                    LogUtils.e("width: " + printerData.getPoint_bitmap().getWidth() + " height: "
                            + printerData.getPoint_bitmap().getHeight());
                    printerDevice.printlnText(format, "使用微信扫一扫 获取更多优惠信息");
                    printerDevice.printBitmap(format, printerData.getPoint_bitmap());
                }
                format.clear();
                format.setParameter("align", "left");
                format.setParameter("size", "medium");
                if (printerData.getPoint() > 0) {
                    printerDevice.printlnText(format, "--------------------------------");
                    printerDevice.printlnText(format, "本次获得积分：" + printerData.getPoint());
                }
                if (printerData.getPointCurrent() > 0) {
                    printerDevice.printlnText(format, "积分余额：" + printerData.getPointCurrent());
                }

                format.clear();
                format.setParameter("align", "center");
                format.setParameter("size", "medium");
                if (printerData.getCoupon_bitmap() != null) {
                    LogUtils.e("width: " + printerData.getCoupon_bitmap().getWidth() + " height: "
                            + printerData.getCoupon_bitmap().getHeight());
                    printerDevice.printBitmap(format, printerData.getCoupon_bitmap());
                }
                format.clear();
                format.setParameter("align", "left");
                format.setParameter("size", "medium");
                if (!StringUtils.isEmpty(printerData.getTitle_url())) {
                    printerDevice.printlnText(format, "--------------------------------");
                    printerDevice.printlnText(format, "优惠券名称：" + printerData.getTitle_url());
                }
                if (printerData.getMoney() > 0) {
                    printerDevice.printlnText(format, "优惠券金额：" + StringUtils.formatIntMoney(printerData.getMoney()));
                }
            }
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
        } catch (DeviceException e) {
            e.printStackTrace();
            try {
                if (printerDevice.queryStatus() == -101) {
                    ToastUtils.CustomShow(mContext, "打印机缺纸...");
                } else if (printerDevice.queryStatus() == -102) {
                    ToastUtils.CustomShow(mContext, "打印机状态异常，请退出...");
                } else {
                    ToastUtils.CustomShow(mContext, "打印失败，未知异常...");
                }
            } catch (DeviceException e1) {
                e1.printStackTrace();
                ToastUtils.CustomShow(mContext, "打印失败，请重新打印...");
            }
        }
    }



    private static void printer_shift_room(ShiftRoom data, long start_time, long end_time) {
        try {
            format = new Format();
            format.setParameter("align", "center");
            format.setParameter(Format.FORMAT_FONT_SIZE, Format.FORMAT_FONT_SIZE_MEDIUM);
            printerDevice.printlnText(format, "班接统计");
            printerDevice.printlnText(" ");
            format.clear();
            format.setParameter("align", "left");
            format.setParameter(Format.FORMAT_FONT_SIZE, Format.FORMAT_FONT_SIZE_SMALL);
            printerDevice.printlnText(format, "起始时间："+ StringUtils.timeStamp2Date1(start_time + "")+":00");
            printerDevice.printlnText(format, "结束时间："+ StringUtils.timeStamp2Date1(end_time + "")+":00");

            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "刷卡：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getReal_pay_money() : "0")+"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getIntergral_deduct() : "0")+"元");

            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "现金：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_cash() != null) ? data.getPay_cash().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_cash() != null) ? data.getPay_cash().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_cash() != null) ? data.getPay_cash().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_cash() != null) ? data.getPay_cash().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "微信：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_wx() != null) ? data.getPay_wx().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_wx() != null) ? data.getPay_wx().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_wx() != null) ? data.getPay_wx().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_wx() != null) ? data.getPay_wx().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "支付宝：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_aly() != null) ? data.getPay_aly().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_aly() != null) ? data.getPay_aly().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_aly() != null) ? data.getPay_aly().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_aly() != null) ? data.getPay_aly().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "钱包：");

            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_qb() != null) ? data.getPay_qb().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_qb() != null) ? data.getPay_qb().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_qb() != null) ? data.getPay_qb().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_qb() != null) ? data.getPay_qb().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "刷卡撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unswipe() != null) ? data.getPay_unswipe().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unswipe() != null) ? data.getPay_unswipe().getReal_pay_money() : "0") +"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "微信撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unwx() != null) ? data.getPay_unwx().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unwx() != null) ? data.getPay_unwx().getReal_pay_money() : "0") +"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "支付宝撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unaly() != null) ? data.getPay_unaly().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unaly() != null) ? data.getPay_unaly().getReal_pay_money() : "0") +"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "钱包撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unqb() != null) ? data.getPay_unqb().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unqb() != null) ? data.getPay_unqb().getReal_pay_money() : "0") +"元");

            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "交易统计：");

            printerDevice.printlnText(format, "交易总笔数："+ ((data.getTotal() != null) ? data.getTotal().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收总金额："+ ((data.getTotal() != null) ? data.getTotal().getReal_pay_money() : "0")+"元");
            printerDevice.printlnText(format, "交易撤销总金额："+ ((data.getTotal() != null) ? data.getTotal().getReal_undo_money() : "0")+"元");
            printerDevice.printlnText(format, "优惠券抵扣总金额："+ ((data.getTotal() != null) ? data.getTotal().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣总金额："+ ((data.getTotal() != null) ? data.getTotal().getIntergral_deduct() : "0") +"元");


            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
        } catch (DeviceException e) {
            e.printStackTrace();
            try {
                if (printerDevice.queryStatus() == -101) {
                    ToastUtils.CustomShow(mContext, "打印机缺纸...");
                } else if (printerDevice.queryStatus() == -102) {
                    ToastUtils.CustomShow(mContext, "打印机状态异常，请退出...");
                } else {
                    ToastUtils.CustomShow(mContext, "打印失败，未知异常...");
                }
            } catch (DeviceException e1) {
                e1.printStackTrace();
                ToastUtils.CustomShow(mContext, "打印失败，请重新打印...");
            }
        }
    }

    private static void printer_shift_room_day(ShiftRoom data, long start_time, long end_time) {

        try {
            format = new Format();
            format.setParameter("align", "center");
            format.setParameter(Format.FORMAT_FONT_SIZE, Format.FORMAT_FONT_SIZE_MEDIUM);
            printerDevice.printlnText(format, "当日统计");
            printerDevice.printlnText(" ");
            format.clear();
            format.setParameter("align", "left");
            format.setParameter(Format.FORMAT_FONT_SIZE, Format.FORMAT_FONT_SIZE_SMALL);

            printerDevice.printlnText(format, "起始时间："+ StringUtils.timeStamp2Date1(start_time + "")+":00");
            printerDevice.printlnText(format, "结束时间："+ StringUtils.timeStamp2Date1(end_time + "")+":00");

            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "刷卡：");
            printerDevice.printlnText(format, "交数："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getReal_pay_money() : "0")+"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_swipe() != null) ? data.getPay_swipe().getIntergral_deduct() : "0")+"元");

            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "现金：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_cash() != null) ? data.getPay_cash().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_cash() != null) ? data.getPay_cash().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_cash() != null) ? data.getPay_cash().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_cash() != null) ? data.getPay_cash().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "微信：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_wx() != null) ? data.getPay_wx().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_wx() != null) ? data.getPay_wx().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_wx() != null) ? data.getPay_wx().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_wx() != null) ? data.getPay_wx().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "支付宝：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_aly() != null) ? data.getPay_aly().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_aly() != null) ? data.getPay_aly().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_aly() != null) ? data.getPay_aly().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_aly() != null) ? data.getPay_aly().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "钱包：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_qb() != null) ? data.getPay_qb().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收金额："+ ((data.getPay_qb() != null) ? data.getPay_qb().getReal_pay_money() : "0") +"元");
            printerDevice.printlnText(format, "优惠券抵扣金额："+ ((data.getPay_qb() != null) ? data.getPay_qb().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣金额："+ ((data.getPay_qb() != null) ? data.getPay_qb().getIntergral_deduct()  : "0")+"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "刷卡撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unswipe() != null) ? data.getPay_unswipe().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unswipe() != null) ? data.getPay_unswipe().getReal_pay_money() : "0") +"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "微信撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unwx() != null) ? data.getPay_unwx().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unwx() != null) ? data.getPay_unwx().getReal_pay_money() : "0") +"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "支付宝撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unaly() != null) ? data.getPay_unaly().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unaly() != null) ? data.getPay_unaly().getReal_pay_money() : "0") +"元");


            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "钱包撤销：");
            printerDevice.printlnText(format, "交易笔数："+ ((data.getPay_unqb() != null) ? data.getPay_unqb().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "撤销金额："+ ((data.getPay_unqb() != null) ? data.getPay_unqb().getReal_pay_money() : "0") +"元");

            printerDevice.printlnText(format, "--------------------------------");
            printerDevice.printlnText(format, "交易统计：");
            printerDevice.printlnText(format, "交易总笔数："+ ((data.getTotal() != null) ? data.getTotal().getTrade_num() : "0")+"笔");
            printerDevice.printlnText(format, "实收总金额："+ ((data.getTotal() != null) ? data.getTotal().getReal_pay_money() : "0")+"元");
            printerDevice.printlnText(format, "交易撤销总金额："+ ((data.getTotal() != null) ? data.getTotal().getReal_undo_money() : "0")+"元");
            printerDevice.printlnText(format, "优惠券抵扣总金额："+ ((data.getTotal() != null) ? data.getTotal().getCoupon_deduct() : "0")+"元");
            printerDevice.printlnText(format, "积分抵扣总金额："+ ((data.getTotal() != null) ? data.getTotal().getIntergral_deduct() : "0") +"元");



            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
            printerDevice.printText(format, "\n");
        } catch (DeviceException e) {
            e.printStackTrace();
            try {
                if (printerDevice.queryStatus() == -101) {
                    ToastUtils.CustomShow(mContext, "打印机缺纸...");
                } else if (printerDevice.queryStatus() == -102) {
                    ToastUtils.CustomShow(mContext, "打印机状态异常，请退出...");
                } else {
                    ToastUtils.CustomShow(mContext, "打印失败，未知异常...");
                }
            } catch (DeviceException e1) {
                e1.printStackTrace();
                ToastUtils.CustomShow(mContext, "打印失败，请重新打印...");
            }
        }
    }



//    private static void printerSettle(ComSettleInfo comSettleInfo){
//        try {
//            format = new Format();
//            format.setParameter("align", "center");
//            format.setParameter(Format.FORMAT_FONT_SIZE, Format.FORMAT_FONT_SIZE_MEDIUM);
//            printerDevice.printlnText(format, "结算总计单");
//            printerDevice.printlnText(" ");
//            format.clear();
//            format.setParameter("align", "left");
//            format.setParameter(Format.FORMAT_FONT_SIZE, Format.FORMAT_FONT_SIZE_SMALL);
//
//            printerDevice.printlnText(format, "商户号："+ comSettleInfo.getMid());
//            printerDevice.printlnText(format, "终端号："+ comSettleInfo.getTid());
//
//            printerDevice.printlnText(format, "批次号："+ StringUtils.fillZero(comSettleInfo.getBatchNumber() + "", 6));
//            printerDevice.printlnText(format, "日期/时间："+StringUtils.formatTime(comSettleInfo.getBatchDate()+comSettleInfo.getBatchTime()));
//
//            printerDevice.printlnText(format, "类型              笔数                金额");
//            printerDevice.printlnText(format, "内卡消费            "+comSettleInfo.getCupSaleCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getCupSaleAmount()));
//            printerDevice.printlnText(format, "内卡退货            "+comSettleInfo.getCupRefundCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getCupRefundAmount()));
//            printerDevice.printlnText(format, "内卡借记累计        "+comSettleInfo.getCupDebitCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getCupDebitAmount()));
//            printerDevice.printlnText(format, "内卡贷记累计        "+comSettleInfo.getCupCreditCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getCupCreditAmount()));
//            printerDevice.printlnText(format, "外卡消费            "+comSettleInfo.getAbrSaleCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getAbrSaleAmount()));
//            printerDevice.printlnText(format, "外卡退货            "+comSettleInfo.getAbrRefundCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getAbrRefundAmount()));
//            printerDevice.printlnText(format, "外卡借记累计        "+comSettleInfo.getAbrDebitCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getAbrDebitAmount()));
//            printerDevice.printlnText(format, "外卡贷记累计        "+comSettleInfo.getAbrCreditCount()+"          "+StringUtils.formatIntMoney(comSettleInfo.getAbrCreditAmount()));
//
//
//
//
//
//            printerDevice.printText(format, "\n");
//            printerDevice.printText(format, "\n");
//            printerDevice.printText(format, "\n");
//            printerDevice.printText(format, "\n");
//        } catch (DeviceException e) {
//            e.printStackTrace();
//            try {
//                if (printerDevice.queryStatus() == -101) {
//                    ToastUtils.CustomShow(mContext, "打印机缺纸...");
//                } else if (printerDevice.queryStatus() == -102) {
//                    ToastUtils.CustomShow(mContext, "打印机状态异常，请退出...");
//                } else {
//                    ToastUtils.CustomShow(mContext, "打印失败，未知异常...");
//                }
//            } catch (DeviceException e1) {
//                e1.printStackTrace();
//                ToastUtils.CustomShow(mContext, "打印失败，请重新打印...");
//            }
//        }
//    }


}
