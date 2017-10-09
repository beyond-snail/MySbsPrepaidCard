package com.mysbsprepaidcard.zfsbs.model;

public class FyQueryResponse {
	private String result_code;// 错误代码, 000000成功,其他详细参见错误列表
	private String result_msg;// 错误代码描述
	private String ins_cd; // 机构号,接入机构在富友的唯一代码
	private String mchnt_cd;// 商户号, 富友分配的商户号
	private String term_id; // 终端号, 富友分配的终端设备号
	private String random_str; // 随机字符串
	private String sign; // 签名, 详见签名生成算法
	private String buyer_id; // 用户在商户的id
	private String order_type; // 订单类型:ALIPAY , WECHAT
	private String trans_stat; // 交易状态 SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付
								// CLOSED—已关闭 REVOKED—已撤销（刷卡支付）
								// USERPAYING--用户支付中
								// PAYERROR--支付失败(其他原因，如银行返回失败)
	private String order_amt; // 订单金额, 单位为分
	private String transaction_id; // 渠道订单号
	private String mchnt_order_no; // 商户订单号, 商户系统的订单号，与请求一致
	private String addn_inf; // 附加数据
	private String reserved_fy_settle_dt; // 富友清算日
	private String outOrderNum; //外部订单号
	
	
	public FyQueryResponse(String result_code, String result_msg) {
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

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getTrans_stat() {
		return trans_stat;
	}

	public void setTrans_stat(String trans_stat) {
		this.trans_stat = trans_stat;
	}

	public String getOrder_amt() {
		return order_amt;
	}

	public void setOrder_amt(String order_amt) {
		this.order_amt = order_amt;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getMchnt_order_no() {
		return mchnt_order_no;
	}

	public void setMchnt_order_no(String mchnt_order_no) {
		this.mchnt_order_no = mchnt_order_no;
	}

	public String getAddn_inf() {
		return addn_inf;
	}

	public void setAddn_inf(String addn_inf) {
		this.addn_inf = addn_inf;
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
		return "FyQueryResponse{" +
				"result_code='" + result_code + '\'' +
				", result_msg='" + result_msg + '\'' +
				", ins_cd='" + ins_cd + '\'' +
				", mchnt_cd='" + mchnt_cd + '\'' +
				", term_id='" + term_id + '\'' +
				", random_str='" + random_str + '\'' +
				", sign='" + sign + '\'' +
				", buyer_id='" + buyer_id + '\'' +
				", order_type='" + order_type + '\'' +
				", trans_stat='" + trans_stat + '\'' +
				", order_amt='" + order_amt + '\'' +
				", transaction_id='" + transaction_id + '\'' +
				", mchnt_order_no='" + mchnt_order_no + '\'' +
				", addn_inf='" + addn_inf + '\'' +
				", reserved_fy_settle_dt='" + reserved_fy_settle_dt + '\'' +
				", outOrderNum='" + outOrderNum + '\'' +
				'}';
	}
}
