package com.mysbsprepaidcard.zfsbs.model;

public class MemberTransAmountRequest {
	private int sid; // 商户ID
	private String memberCardNo = ""; // 会员卡号
	private String password = ""; // 根据是否免密判断是否有密码输入
	private int tradeMoney = 0; // 交易金额
	private int point = 0; // 抵消积分数
	private String couponSn = ""; // 优惠券号
	private String memberName = ""; // 用户名（非会员注册）

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getMemberCardNo() {
		return memberCardNo;
	}

	public void setMemberCardNo(String memberCardNo) {
		this.memberCardNo = memberCardNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(int tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getCouponSn() {
		return couponSn;
	}

	public void setCouponSn(String couponSn) {
		this.couponSn = couponSn;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

}
