package com.mysbsprepaidcard.zfsbs.model;

import java.io.Serializable;

public class TransUploadResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8922398259200576828L;
	private String point_url; // 积分领取二维码路径
	private int point; // 本次产生的积分额度
	private int pointCurrent; // 当前剩余积分
	private String coupon; // 优惠券领取的路径
	private String title_url; // 优惠券名称
	private int money; // 优惠券金额
	private int backAmt; //返利金额
	private int packet_remain;

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

	public int getBackAmt() {
		return backAmt;
	}

	public void setBackAmt(int backAmt) {
		this.backAmt = backAmt;
	}

	public int getPacket_remain() {
		return packet_remain;
	}

	public void setPacket_remain(int packet_remain) {
		this.packet_remain = packet_remain;
	}

	@Override
	public String toString() {
		return "TransUploadResponse{" +
				"point_url='" + point_url + '\'' +
				", point=" + point +
				", pointCurrent=" + pointCurrent +
				", coupon='" + coupon + '\'' +
				", title_url='" + title_url + '\'' +
				", money=" + money +
				", backAmt=" + backAmt +
				", packet_remain=" + packet_remain +
				'}';
	}
}
