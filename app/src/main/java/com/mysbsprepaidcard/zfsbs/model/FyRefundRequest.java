package com.mysbsprepaidcard.zfsbs.model;

public class FyRefundRequest {
	private String version;// 版本号
	private String ins_cd; // 机构号，在富友的唯一代码
	private String mchnt_cd; // 商户号， 富友分配给二级商户的商户号
	private String term_id; // 终端号，富友分配的终端设备号
	private String mchnt_order_no; // 商户订单号，商户系统内部的订单号
	private String random_str; // 随机字符串
	private String sign; // 签名
	private String order_type; // 订单类型：ALYPAY, WECHAT
	private String refund_order_no; // 商户退款单号
	private int amount;// 总金额
	private int Refund_amt;// 退款金额
	private String operator_id; // 操作员

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIns_cd() {
		return ins_cd;
	}

	public void setIns_cd(String ins_cd) {
		this.ins_cd = ins_cd;
	}

	public String getMchnt_cd() {
		return mchnt_cd;
	}

	public void setMchnt_cd(String mchnt_cd) {
		this.mchnt_cd = mchnt_cd;
	}

	public String getTerm_id() {
		return term_id;
	}

	public void setTerm_id(String term_id) {
		this.term_id = term_id;
	}

	public String getMchnt_order_no() {
		return mchnt_order_no;
	}

	public void setMchnt_order_no(String mchnt_order_no) {
		this.mchnt_order_no = mchnt_order_no;
	}

	public String getRandom_str() {
		return random_str;
	}

	public void setRandom_str(String random_str) {
		this.random_str = random_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getRefund_order_no() {
		return refund_order_no;
	}

	public void setRefund_order_no(String refund_order_no) {
		this.refund_order_no = refund_order_no;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRefund_amt() {
		return Refund_amt;
	}

	public void setRefund_amt(int refund_amt) {
		Refund_amt = refund_amt;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	@Override
	public String toString() {
		return "FyRefundRequest{" +
				"version='" + version + '\'' +
				", ins_cd='" + ins_cd + '\'' +
				", mchnt_cd='" + mchnt_cd + '\'' +
				", term_id='" + term_id + '\'' +
				", mchnt_order_no='" + mchnt_order_no + '\'' +
				", random_str='" + random_str + '\'' +
				", sign='" + sign + '\'' +
				", order_type='" + order_type + '\'' +
				", refund_order_no='" + refund_order_no + '\'' +
				", amount=" + amount +
				", Refund_amt=" + Refund_amt +
				", operator_id='" + operator_id + '\'' +
				'}';
	}
}
