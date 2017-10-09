package com.mysbsprepaidcard.zfsbs.model;

public class FyMicropayResponse {
	private String result_code;// 错误代码, 000000成功,其他详细参见错误列表
	private String result_msg; // 错误代码描述
	private String ins_cd; // 机构号,接入机构在富友的唯一代码
	private String mchnt_cd; // 商户号, 富友分配的商户号
	private String term_id; // 终端号, 富友分配的终端设备号
	private String random_str; // 随机字符串
	private String sign; // 签名, 详见签名生成算法
	private String order_type; // 订单类型:ALIPAY , WECHAT
	private String total_amount; // 订单金额，分为单位的整数
	private String buyer_id; // 买家在渠道账号
	private String transaction_id; // 渠道交易流水号
	private String addn_inf; // 附加数据
	private String reserved_fy_order_no; // 富友生成的订单号,需要商户与商户订单号进行关联
	private String reserved_mchnt_order_no; // 商户订单号, 商户系统内部的订单号
	private String reserved_fy_settle_dt; // 富友清算日
	private String mchnt_order_no; //返回的订单号
	private String txn_begin_ts;// 交易起始时间, 订单生成时间，格式为yyyyMMddHHmmss
	private String outOrderNum; //外部订单号

	public String getTxn_begin_ts() {
		return txn_begin_ts;
	}

	public void setTxn_begin_ts(String txn_begin_ts) {
		this.txn_begin_ts = txn_begin_ts;
	}

	public String getMchnt_order_no() {
		return mchnt_order_no;
	}

	public void setMchnt_order_no(String mchnt_order_no) {
		this.mchnt_order_no = mchnt_order_no;
	}

	public FyMicropayResponse(String result_code, String result_msg) {
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

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getAddn_inf() {
		return addn_inf;
	}

	public void setAddn_inf(String addn_inf) {
		this.addn_inf = addn_inf;
	}

	public String getReserved_fy_order_no() {
		return reserved_fy_order_no;
	}

	public void setReserved_fy_order_no(String reserved_fy_order_no) {
		this.reserved_fy_order_no = reserved_fy_order_no;
	}

	public String getReserved_mchnt_order_no() {
		return reserved_mchnt_order_no;
	}

	public void setReserved_mchnt_order_no(String reserved_mchnt_order_no) {
		this.reserved_mchnt_order_no = reserved_mchnt_order_no;
	}

	public String getReserved_fy_settle_dt() {
		return reserved_fy_settle_dt;
	}

	public void setReserved_fy_settle_dt(String reserved_fy_settle_dt) {
		this.reserved_fy_settle_dt = reserved_fy_settle_dt;
	}

	public String getOutOrderNum() {
		return outOrderNum;
	}

	public void setOutOrderNum(String outOrderNum) {
		this.outOrderNum = outOrderNum;
	}

	@Override
	public String toString() {
		return "FyMicropayResponse{" +
				"result_code='" + result_code + '\'' +
				", result_msg='" + result_msg + '\'' +
				", ins_cd='" + ins_cd + '\'' +
				", mchnt_cd='" + mchnt_cd + '\'' +
				", term_id='" + term_id + '\'' +
				", random_str='" + random_str + '\'' +
				", sign='" + sign + '\'' +
				", order_type='" + order_type + '\'' +
				", total_amount='" + total_amount + '\'' +
				", buyer_id='" + buyer_id + '\'' +
				", transaction_id='" + transaction_id + '\'' +
				", addn_inf='" + addn_inf + '\'' +
				", reserved_fy_order_no='" + reserved_fy_order_no + '\'' +
				", reserved_mchnt_order_no='" + reserved_mchnt_order_no + '\'' +
				", reserved_fy_settle_dt='" + reserved_fy_settle_dt + '\'' +
				", mchnt_order_no='" + mchnt_order_no + '\'' +
				", txn_begin_ts='" + txn_begin_ts + '\'' +
				", outOrderNum='" + outOrderNum + '\'' +
				'}';
	}
}
