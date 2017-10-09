package com.mysbsprepaidcard.zfsbs.model;

import android.graphics.Bitmap;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

public class SbsPrinterData extends DataSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String merchantName; // 商户名称
    private String merchantNo; // 商户编号
    private String terminalId; // 终端编号
    private String operatorNo; // 操作员号
    private String acquirer; // 收单行
    private String issuer; // 发卡行
    private String cardNo; // 卡号
    private String transType; // 交易类型
    private String expDate; // 卡有效期
    private String batchNO; // 交易批次号
    private String voucherNo; // 交易凭证号
    private String dateTime; // 交易日期时间
    private String authNo; // 授权号
    private String referNo; // 参考号
    private String amount; // 交易金额
    private int payType; // 支付方式
    private String clientOrderNo; // 商户订单号（对应收钱吧:client_sn）
    private String transNo; // 第三方流水（对应收钱吧：trade_no）
    private String authCode; // 交易机构订单号（收钱吧: sn）
    private String outOrderNo; //外部订单号
    private String receiveAmount; // 实收金额
    private String oddChangeAmout; // 找零金额

    private String point_url; // 积分领取二维码路径
    private int point; // 本次产生的积分额度
    private int pointCurrent; // 积分余额
    private String coupon; // 优惠领取二维码的路径
    private String title_url; // 优惠券名称
    private int money; // 优惠券金额（单位：分）
    private Bitmap point_bitmap; // 积分二维码的bitmap
    private Bitmap coupon_bitmap; // 优惠券领取的bitmap

    private int pointCoverMoney; // 积分抵用金额
    private int couponCoverMoney; // 优惠券抵消金额
    private int orderAmount; //订单金额


    private boolean UploadFlag; //流水上送标识
    private boolean isRefund; //是否退款
    private boolean isRefundUpload; //是否退款流水上送
    private String refund_order_no; //退款订单号
    private String scanPayType; //扫码支付通道

    private TransUploadRequest request; // 目的是跟对应的打印数据对应起来
    private TransCancel cancelRequest; //退款的接口保存
    private String transUploadData; //上传流水数据
    private String oldOrderId; //消费撤销时保存原交易订单号。
    private int backAmt; //返利金额
    private int pacektRemian;
    private String promotion_num;
    private String realize_card_num;
    private String member_name;
    private String recharge_order_num;
    private String sh_name;
    private String largessAmount;
    private boolean isStatus; //交易状态 成功、失败
    private String onFailuerData; //扫码失败数据保存，

    private String phoneNo; //手机号
    private boolean isYxf; //是否是第三方
    private int app_type; //标记为第三方
    private String flowNo; //流水号,
    private boolean isMember; //是否是会员
    private Bitmap sign_bitmap; //签名
    private String rechargeUpload; //充值上送

//	public PrinterData() {
//		request = new TransUploadRequest();
//	}	

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issu) {
        this.issuer = issu;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getBatchNO() {
        return batchNO;
    }

    public void setBatchNO(String batchNO) {
        this.batchNO = batchNO;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getReferNo() {
        return referNo;
    }

    public void setReferNo(String referNo) {
        this.referNo = referNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getReceiveAmount() {
        return receiveAmount;
    }

    public String getClientOrderNo() {
        return clientOrderNo;
    }

    public void setClientOrderNo(String clientOrderNo) {
        this.clientOrderNo = clientOrderNo;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setReceiveAmount(String receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public String getOddChangeAmout() {
        return oddChangeAmout;
    }

    public void setOddChangeAmout(String oddChangeAmout) {
        this.oddChangeAmout = oddChangeAmout;
    }

    public String getPoint_url() {
        return point_url;
    }

    public void setPoint_url(String point_url) {
        this.point_url = point_url;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPointCurrent() {
        return pointCurrent;
    }

    public void setPointCurrent(int pointCurrent) {
        this.pointCurrent = pointCurrent;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getTitle_url() {
        return title_url;
    }

    public void setTitle_url(String title_url) {
        this.title_url = title_url;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Bitmap getPoint_bitmap() {
        return point_bitmap;
    }

    public void setPoint_bitmap(Bitmap point_bitmap) {
        this.point_bitmap = point_bitmap;
    }

    public Bitmap getCoupon_bitmap() {
        return coupon_bitmap;
    }

    public void setCoupon_bitmap(Bitmap coupon_bitmap) {
        this.coupon_bitmap = coupon_bitmap;
    }

    public boolean isUploadFlag() {
        return UploadFlag;
    }

    public void setUploadFlag(boolean uploadFlag) {
        UploadFlag = uploadFlag;
    }

    public boolean isRefund() {
        return isRefund;
    }

    public String getRefund_order_no() {
        return refund_order_no;
    }

    public void setRefund_order_no(String refund_order_no) {
        this.refund_order_no = refund_order_no;
    }

    public void setRefund(boolean refund) {
        isRefund = refund;
    }

    public String getScanPayType() {
        return scanPayType;
    }

    public void setScanPayType(String scanPayType) {
        this.scanPayType = scanPayType;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getPointCoverMoney() {
        return pointCoverMoney;
    }

    public void setPointCoverMoney(int pointCoverMoney) {
        this.pointCoverMoney = pointCoverMoney;
    }

    public int getCouponCoverMoney() {
        return couponCoverMoney;
    }

    public boolean isRefundUpload() {
        return isRefundUpload;
    }

    public void setRefundUpload(boolean refundUpload) {
        isRefundUpload = refundUpload;
    }

    public void setCouponCoverMoney(int couponCoverMoney) {
        this.couponCoverMoney = couponCoverMoney;
    }

    public TransUploadRequest getRequest() {
        List<TransUploadRequest> list = DataSupport.where("sbsprinterdata_id = ?", "" + getId()).find(TransUploadRequest.class);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return request;
    }

    public void setRequest(TransUploadRequest request) {
        this.request = request;
    }

    public TransCancel getCancelRequest() {
        return cancelRequest;
    }

    public void setCancelRequest(TransCancel cancelRequest) {
        this.cancelRequest = cancelRequest;
    }

    public String getTransUploadData() {
        return transUploadData;
    }

    public void setTransUploadData(String transUploadData) {
        this.transUploadData = transUploadData;
    }

    public String getOldOrderId() {
        return oldOrderId;
    }

    public void setOldOrderId(String oldOrderId) {
        this.oldOrderId = oldOrderId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public boolean isYxf() {
        return isYxf;
    }

    public void setYxf(boolean yxf) {
        isYxf = yxf;
    }

    public int getBackAmt() {
        return backAmt;
    }

    public void setBackAmt(int backAmt) {
        this.backAmt = backAmt;
    }

    public int getPacektRemian() {
        return pacektRemian;
    }

    public void setPacektRemian(int pacektRemian) {
        this.pacektRemian = pacektRemian;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public String getOnFailuerData() {
        return onFailuerData;
    }

    public void setOnFailuerData(String onFailuerData) {
        this.onFailuerData = onFailuerData;
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

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public Bitmap getSign_bitmap() {
        return sign_bitmap;
    }

    public void setSign_bitmap(Bitmap sign_bitmap) {
        this.sign_bitmap = sign_bitmap;
    }

    public String getRechargeUpload() {
        return rechargeUpload;
    }

    public void setRechargeUpload(String rechargeUpload) {
        this.rechargeUpload = rechargeUpload;
    }

    public String getRealize_card_num() {
        return realize_card_num;
    }

    public void setRealize_card_num(String realize_card_num) {
        this.realize_card_num = realize_card_num;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getRecharge_order_num() {
        return recharge_order_num;
    }

    public void setRecharge_order_num(String recharge_order_num) {
        this.recharge_order_num = recharge_order_num;
    }

    public String getSh_name() {
        return sh_name;
    }

    public void setSh_name(String sh_name) {
        this.sh_name = sh_name;
    }

    public String getLargessAmount() {
        return largessAmount;
    }

    public void setLargessAmount(String largessAmount) {
        this.largessAmount = largessAmount;
    }

    public String getPromotion_num() {
        return promotion_num;
    }

    public void setPromotion_num(String promotion_num) {
        this.promotion_num = promotion_num;
    }

    @Override
    public String toString() {
        return "SbsPrinterData{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", terminalId='" + terminalId + '\'' +
                ", operatorNo='" + operatorNo + '\'' +
                ", acquirer='" + acquirer + '\'' +
                ", issuer='" + issuer + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", transType='" + transType + '\'' +
                ", expDate='" + expDate + '\'' +
                ", batchNO='" + batchNO + '\'' +
                ", voucherNo='" + voucherNo + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", authNo='" + authNo + '\'' +
                ", referNo='" + referNo + '\'' +
                ", amount='" + amount + '\'' +
                ", payType=" + payType +
                ", clientOrderNo='" + clientOrderNo + '\'' +
                ", transNo='" + transNo + '\'' +
                ", authCode='" + authCode + '\'' +
                ", outOrderNo='" + outOrderNo + '\'' +
                ", receiveAmount='" + receiveAmount + '\'' +
                ", oddChangeAmout='" + oddChangeAmout + '\'' +
                ", point_url='" + point_url + '\'' +
                ", point=" + point +
                ", pointCurrent=" + pointCurrent +
                ", coupon='" + coupon + '\'' +
                ", title_url='" + title_url + '\'' +
                ", money=" + money +
                ", point_bitmap=" + point_bitmap +
                ", coupon_bitmap=" + coupon_bitmap +
                ", pointCoverMoney=" + pointCoverMoney +
                ", couponCoverMoney=" + couponCoverMoney +
                ", orderAmount=" + orderAmount +
                ", UploadFlag=" + UploadFlag +
                ", isRefund=" + isRefund +
                ", isRefundUpload=" + isRefundUpload +
                ", refund_order_no='" + refund_order_no + '\'' +
                ", scanPayType='" + scanPayType + '\'' +
                ", request=" + request +
                ", cancelRequest=" + cancelRequest +
                ", transUploadData='" + transUploadData + '\'' +
                ", oldOrderId='" + oldOrderId + '\'' +
                ", backAmt=" + backAmt +
                ", pacektRemian=" + pacektRemian +
                ", isStatus=" + isStatus +
                ", onFailuerData='" + onFailuerData + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", isYxf=" + isYxf +
                ", app_type=" + app_type +
                ", flowNo='" + flowNo + '\'' +
                ", isMember=" + isMember +
                ", sign_bitmap=" + sign_bitmap +
                ", rechargeUpload='" + rechargeUpload + '\'' +
                '}';
    }
}
