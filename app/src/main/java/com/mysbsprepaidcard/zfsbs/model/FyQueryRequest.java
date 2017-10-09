package com.mysbsprepaidcard.zfsbs.model;

public class FyQueryRequest {
	private String version; // 版本号
	private String ins_cd; // 机构号,接入机构在富友的唯一代码
	private String mchnt_cd; // 富友分配的商户号
	private String term_id; // 富友分配终端号
	private String order_type;// 订单类型:ALIPAY , WECHAT
	private String mchnt_order_no; // 商户系统内部的订单号
	private String random_str; // 随机字符串，不长于32位
	private String sign; // 签名，详见签名生成算法
	private String outOrderNum; //外部订单号

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

	public String getOutOrderNum() {
		return outOrderNum;
	}

	public void setOutOrderNum(String outOrderNum) {
		this.outOrderNum = outOrderNum;
	}

	@Override
	public String toString() {
		return "FyQueryRequest{" +
				"version='" + version + '\'' +
				", ins_cd='" + ins_cd + '\'' +
				", mchnt_cd='" + mchnt_cd + '\'' +
				", term_id='" + term_id + '\'' +
				", order_type='" + order_type + '\'' +
				", mchnt_order_no='" + mchnt_order_no + '\'' +
				", random_str='" + random_str + '\'' +
				", sign='" + sign + '\'' +
				", outOrderNum='" + outOrderNum + '\'' +
				'}';
	}
}
