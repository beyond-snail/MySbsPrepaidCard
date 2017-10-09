package com.mysbsprepaidcard.zfsbs.model;

import java.io.Serializable;

public class SbsGetPrinterDataResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3565364806487954068L;
	private String point_url; // 积分领取二维码路径
	private String point; // 本次产生的积分额度
	private String coupon; // 优惠券领取二位码的路径
	private String title_url; // 优惠券名称
	private int money; // 优惠券金额 （单位：分）

	public String getPoint_url() {
		return point_url;
	}

	public void setPoint_url(String point_url) {
		this.point_url = point_url;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
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

}
