package com.mysbsprepaidcard.zfsbs.model;

public class FyRefundResponse {
	private String result_code;// 错误代码, 000000成功,其他详细参见错误列表
	private String result_msg; // 错误代码描述
	private String ins_cd; // 机构号,接入机构在富友的唯一代码
	private String mchnt_cd; // 商户号, 富友分配的商户号
	private String term_id; // 终端号, 富友分配的终端设备号
	private String random_str; // 随机字符串
	private String sign; // 签名, 详见签名生成算法
	private String order_type; // 订单类型:ALIPAY , WECHAT
	private String mchnt_order_no; // 商户订单号
	private String refund_order_no; // 商户退款单号
	private String transaction_id; // 渠道交易流水号
	private String refund_id; // 渠道退款流水号
	private String reserved_fy_settle_dt; // 富友清算日
	
	public FyRefundResponse(String result_code, String result_msg) {
		this.result_code = result_code;
		this.result_msg = result_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_msg() {
		return result_msg;
	}

	public void setResult_msg(String result_msg) {
		this.result_msg = result_msg;
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

	public String getMchnt_order_no() {
		return mchnt_order_no;
	}

	public void setMchnt_order_no(String mchnt_order_no) {
		this.mchnt_order_no = mchnt_order_no;
	}

	public String getRefund_order_no() {
		return refund_order_no;
	}

	public void setRefund_order_no(String refund_order_no) {
		this.refund_order_no = refund_order_no;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getRefund_id() {
		return refund_id;
	}

	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}

	public String getReserved_fy_settle_dt() {
		return reserved_fy_settle_dt;
	}

	public void setReserved_fy_settle_dt(String reserved_fy_settle_dt) {
		this.reserved_fy_settle_dt = reserved_fy_settle_dt;
	}

	@Override
	public String toString() {
		return "FyRefundResponse [result_code=" + result_code + ", result_msg=" + result_msg + ", ins_cd=" + ins_cd
				+ ", mchnt_cd=" + mchnt_cd + ", term_id=" + term_id + ", random_str=" + random_str + ", sign=" + sign
				+ ", order_type=" + order_type + ", mchnt_order_no=" + mchnt_order_no + ", refund_order_no="
				+ refund_order_no + ", transaction_id=" + transaction_id + ", refund_id=" + refund_id
				+ ", reserved_fy_settle_dt=" + reserved_fy_settle_dt + "]";
	}

}
