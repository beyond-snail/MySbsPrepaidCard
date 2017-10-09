package com.mysbsprepaidcard.zfsbs.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class TransUploadRequest extends DataSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1173505418601061326L;
	private int id;
	private int sid; // 商户ID
	private String cardNo; // 会员卡号
	private String password; // 根据是否免密判断是否有密码输入(此处可以连带)
	private int cash; // 现金消费金额
	private int bankAmount; // 网银支付额度
	private int couponCoverAmount; // 优惠券抵用金额
	private int pointCoverAmount; // 积分抵用金额
	private int pointAmount; // 消耗积分数
	private String couponSns; // 消费优惠券序列号组 （格式:逗号分隔）
	private String clientOrderNo; // 订单号（客户端生成）
	private String activateCode; // 激活码
	private String merchantNo; // 商户号
	private long t; // 时间戳
	private String transNo; // 交易流水号（第三方） (华势：批次号+流水号， 收钱吧：返回的 第三方流水号)
	private String authCode; // 交易机构订单号（收钱吧）（华势：参考号）
	private String serialNum; // 交易终端设备序列号
	private int payType; // 收款方式  //11：微信撤销 12：支付宝撤销 14：钱包撤销
	private int sbsprinterdata_id;

	private String old_trade_order_num; //原交易订单号
	private String new_trade_order_num; //新订单号
	private String action;              //1为查询订单 2为交易撤销
//	private String payType;             //11：微信撤销 12：支付宝撤销 14：钱包撤销
	private String phone; //

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getBankAmount() {
		return bankAmount;
	}

	public void setBankAmount(int bankAmount) {
		this.bankAmount = bankAmount;
	}

	public int getCouponCoverAmount() {
		return couponCoverAmount;
	}

	public void setCouponCoverAmount(int couponCoverAmount) {
		this.couponCoverAmount = couponCoverAmount;
	}

	public int getPointCoverAmount() {
		return pointCoverAmount;
	}

	public void setPointCoverAmount(int pointCoverAmount) {
		this.pointCoverAmount = pointCoverAmount;
	}

	public int getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}

	public String getCouponSns() {
		return couponSns;
	}

	public void setCouponSns(String couponSns) {
		this.couponSns = couponSns;
	}

	public String getClientOrderNo() {
		return clientOrderNo;
	}

	public void setClientOrderNo(String clientOrderNo) {
		this.clientOrderNo = clientOrderNo;
	}

	public String getActivateCode() {
		return activateCode;
	}

	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public long getT() {
		return t;
	}

	public void setT(long t) {
		this.t = t;
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

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOld_trade_order_num() {
		return old_trade_order_num;
	}

	public void setOld_trade_order_num(String old_trade_order_num) {
		this.old_trade_order_num = old_trade_order_num;
	}

	public String getNew_trade_order_num() {
		return new_trade_order_num;
	}

	public void setNew_trade_order_num(String new_trade_order_num) {
		this.new_trade_order_num = new_trade_order_num;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getSbsprinterdata_id() {
		return sbsprinterdata_id;
	}

	public void setSbsprinterdata_id(int sbsprinterdata_id) {
		this.sbsprinterdata_id = sbsprinterdata_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "TransUploadRequest{" +
				"id=" + id +
				", sid=" + sid +
				", cardNo='" + cardNo + '\'' +
				", password='" + password + '\'' +
				", cash=" + cash +
				", bankAmount=" + bankAmount +
				", couponCoverAmount=" + couponCoverAmount +
				", pointCoverAmount=" + pointCoverAmount +
				", pointAmount=" + pointAmount +
				", couponSns='" + couponSns + '\'' +
				", clientOrderNo='" + clientOrderNo + '\'' +
				", activateCode='" + activateCode + '\'' +
				", merchantNo='" + merchantNo + '\'' +
				", t=" + t +
				", transNo='" + transNo + '\'' +
				", authCode='" + authCode + '\'' +
				", serialNum='" + serialNum + '\'' +
				", payType=" + payType +
				", sbsprinterdata_id=" + sbsprinterdata_id +
				", old_trade_order_num='" + old_trade_order_num + '\'' +
				", new_trade_order_num='" + new_trade_order_num + '\'' +
				", action='" + action + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
