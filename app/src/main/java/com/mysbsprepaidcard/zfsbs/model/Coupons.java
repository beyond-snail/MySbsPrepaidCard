package com.mysbsprepaidcard.zfsbs.model;

import java.io.Serializable;

public class Coupons implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; // 优惠券ID
	private int money; // 可抵消金额
	private String sn; // 优惠劵劵号
	private String name; // 优惠券名称
	private String remark; // 说明信息
	private boolean canMultiChoose; // 是否可多选
	private boolean isChecked; // 是否使用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isCanMultiChoose() {
		return canMultiChoose;
	}

	public void setCanMultiChoose(boolean canMultiChoose) {
		this.canMultiChoose = canMultiChoose;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "Coupons{" +
				"id=" + id +
				", money=" + money +
				", sn='" + sn + '\'' +
				", name='" + name + '\'' +
				", remark='" + remark + '\'' +
				", canMultiChoose=" + canMultiChoose +
				", isChecked=" + isChecked +
				'}';
	}
}
