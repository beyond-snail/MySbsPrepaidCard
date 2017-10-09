package com.mysbsprepaidcard.zfsbs.model;

import java.io.Serializable;

public class MemberTransAmountResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3880705816678091583L;
	private String memberName; // 会员名称
	private String memberCardNo; // 会员卡号
	private String stkCardNo; //实体卡号
	private int tradeMoney; // 交易金额（单位：分）
	private int realMoney; // 实收金额
	private int pointCoverMoney; // 积分抵用金额
	private String couponSns; // 消费优惠券序列号（格式：逗号分隔）
	private String couponName; // 优惠券名称
	private int couponNum; // 消费优惠券数
	private int couponCoverMoney; // 优惠券抵消金额
	private String phone; // 手机号
	private int point; // 积分
	private String pass; // 密码

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberCardNo() {
		return memberCardNo;
	}

	public void setMemberCardNo(String memberCardNo) {
		this.memberCardNo = memberCardNo;
	}

	public String getStkCardNo() {
		return stkCardNo;
	}

	public void setStkCardNo(String stkCardNo) {
		this.stkCardNo = stkCardNo;
	}

	public int getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(int tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public int getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(int realMoney) {
		this.realMoney = realMoney;
	}

	public int getPointCoverMoney() {
		return pointCoverMoney;
	}

	public void setPointCoverMoney(int pointCoverMoney) {
		this.pointCoverMoney = pointCoverMoney;
	}

	public String getCouponSns() {
		return couponSns;
	}

	public void setCouponSns(String couponSns) {
		this.couponSns = couponSns;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(int couponNum) {
		this.couponNum = couponNum;
	}

	public int getCouponCoverMoney() {
		return couponCoverMoney;
	}

	public void setCouponCoverMoney(int couponCoverMoney) {
		this.couponCoverMoney = couponCoverMoney;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MemberTransAmountResponse{" +
				"memberName='" + memberName + '\'' +
				", memberCardNo='" + memberCardNo + '\'' +
				", tradeMoney=" + tradeMoney +
				", realMoney=" + realMoney +
				", pointCoverMoney=" + pointCoverMoney +
				", couponSns='" + couponSns + '\'' +
				", couponName='" + couponName + '\'' +
				", couponNum=" + couponNum +
				", couponCoverMoney=" + couponCoverMoney +
				", phone='" + phone + '\'' +
				", point=" + point +
				", pass='" + pass + '\'' +
				'}';
	}
}
