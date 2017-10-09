package com.mysbsprepaidcard.zfsbs.model;

public class FyMicropayRequest {
	private String version;// 版本号
	private String ins_cd; // 机构号，在富友的唯一代码
	private String mchnt_cd; // 商户号， 富友分配给二级商户的商户号
	private String term_id; // 终端号，富友分配的终端设备号
	private String random_str; // 随机字符串
	private String sign; // 签名
	private String type; // 订单类型：ALYPAY, WECHAT
	private String goods_des; // 商品描述，商品或支付单简要描述
	private String goods_detail; // 商品详情， 商品名称明细
	private String addn_inf; // 附加数据
	private String mchnt_order_no; // 商户订单号，商户系统内部的订单号
	private String curr_type; // 货币类型,默认人民币：CNY
	private int amount; // 总金额, 订单总金额，单位为分
	private String term_ip; // 终端IP
	private String txn_begin_ts;// 交易起始时间, 订单生成时间，格式为yyyyMMddHHmmss
	private String goods_tag; // 商品标记
	private String auth_code; // 扫码支付授权码，设备读取用户的条码或者二维码 信息
	private String sence; // 支付场景,默认1；条码支付-1 声波支付-2
	private String reserved_sub_appid; // 子商户公众号id, 子商户配置多个公众号时必填
	private String reserved_limit_pay; // 限制支付,no_credit:不能使用信用卡
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getGoods_des() {
		return goods_des;
	}

	public void setGoods_des(String goods_des) {
		this.goods_des = goods_des;
	}



	public String getAddn_inf() {
		return addn_inf;
	}

	public void setAddn_inf(String addn_inf) {
		this.addn_inf = addn_inf;
	}

	public String getMchnt_order_no() {
		return mchnt_order_no;
	}

	public void setMchnt_order_no(String mchnt_order_no) {
		this.mchnt_order_no = mchnt_order_no;
	}

	public String getCurr_type() {
		return curr_type;
	}

	public void setCurr_type(String curr_type) {
		this.curr_type = curr_type;
	}



	public String getTerm_ip() {
		return term_ip;
	}

	public void setTerm_ip(String term_ip) {
		this.term_ip = term_ip;
	}

	public String getTxn_begin_ts() {
		return txn_begin_ts;
	}

	public void setTxn_begin_ts(String txn_begin_ts) {
		this.txn_begin_ts = txn_begin_ts;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getSence() {
		return sence;
	}

	public void setSence(String sence) {
		this.sence = sence;
	}

	public String getReserved_sub_appid() {
		return reserved_sub_appid;
	}

	public void setReserved_sub_appid(String reserved_sub_appid) {
		this.reserved_sub_appid = reserved_sub_appid;
	}

	public String getReserved_limit_pay() {
		return reserved_limit_pay;
	}

	public void setReserved_limit_pay(String reserved_limit_pay) {
		this.reserved_limit_pay = reserved_limit_pay;
	}

	public String getOutOrderNum() {
		return outOrderNum;
	}

	public void setOutOrderNum(String outOrderNum) {
		this.outOrderNum = outOrderNum;
	}

	@Override
	public String toString() {
		return "FyMicropayRequest{" +
				"version='" + version + '\'' +
				", ins_cd='" + ins_cd + '\'' +
				", mchnt_cd='" + mchnt_cd + '\'' +
				", term_id='" + term_id + '\'' +
				", random_str='" + random_str + '\'' +
				", sign='" + sign + '\'' +
				", type='" + type + '\'' +
				", goods_des='" + goods_des + '\'' +
				", goods_detail='" + goods_detail + '\'' +
				", addn_inf='" + addn_inf + '\'' +
				", mchnt_order_no='" + mchnt_order_no + '\'' +
				", curr_type='" + curr_type + '\'' +
				", amount=" + amount +
				", term_ip='" + term_ip + '\'' +
				", txn_begin_ts='" + txn_begin_ts + '\'' +
				", goods_tag='" + goods_tag + '\'' +
				", auth_code='" + auth_code + '\'' +
				", sence='" + sence + '\'' +
				", reserved_sub_appid='" + reserved_sub_appid + '\'' +
				", reserved_limit_pay='" + reserved_limit_pay + '\'' +
				", outOrderNum='" + outOrderNum + '\'' +
				'}';
	}
}
