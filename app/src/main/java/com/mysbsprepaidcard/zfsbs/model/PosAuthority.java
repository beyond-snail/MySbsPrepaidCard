package com.mysbsprepaidcard.zfsbs.model;

public class PosAuthority {
	private int id;
	private boolean active;	//领卡激活 0 ： 否 1 ： 是
	private boolean change_pass; //修改密码
	private boolean reset_pass; //重置密码
	private boolean loss_register; //挂失
	private boolean find_register; //会员卡解挂
	private boolean card_replace; //会员卡补办
	private boolean card_search; //会员卡查询
	private boolean bind_phone; //卡号绑定手机号
	private boolean unbind_phone; //卡号解绑手机号
	private boolean saving_pay; //储值卡消费
	private boolean saving_charge; //储值卡充值
	private boolean point_exchange; //积分兑换
	private boolean active_addon; //领卡激活附加码
	private boolean point_add; //积分累加
	private boolean bank_card; //信用卡/银行卡
	private boolean electricity_coupon; //电子券
	private boolean times_buy; //记次购买
	private boolean times_custome; //记次消费
	private boolean cancel_trade; //交易撤销
	private boolean scan_pay; //是否进行扫码支付
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isChange_pass() {
		return change_pass;
	}
	public void setChange_pass(boolean change_pass) {
		this.change_pass = change_pass;
	}
	public boolean isReset_pass() {
		return reset_pass;
	}
	public void setReset_pass(boolean reset_pass) {
		this.reset_pass = reset_pass;
	}
	public boolean isLoss_register() {
		return loss_register;
	}
	public void setLoss_register(boolean loss_register) {
		this.loss_register = loss_register;
	}
	public boolean isFind_register() {
		return find_register;
	}
	public void setFind_register(boolean find_register) {
		this.find_register = find_register;
	}
	public boolean isCard_replace() {
		return card_replace;
	}
	public void setCard_replace(boolean card_replace) {
		this.card_replace = card_replace;
	}
	public boolean isCard_search() {
		return card_search;
	}
	public void setCard_search(boolean card_search) {
		this.card_search = card_search;
	}
	public boolean isBind_phone() {
		return bind_phone;
	}
	public void setBind_phone(boolean bind_phone) {
		this.bind_phone = bind_phone;
	}
	public boolean isUnbind_phone() {
		return unbind_phone;
	}
	public void setUnbind_phone(boolean unbind_phone) {
		this.unbind_phone = unbind_phone;
	}
	public boolean isSaving_pay() {
		return saving_pay;
	}
	public void setSaving_pay(boolean saving_pay) {
		this.saving_pay = saving_pay;
	}
	public boolean isSaving_charge() {
		return saving_charge;
	}
	public void setSaving_charge(boolean saving_charge) {
		this.saving_charge = saving_charge;
	}
	public boolean isPoint_exchange() {
		return point_exchange;
	}
	public void setPoint_exchange(boolean point_exchange) {
		this.point_exchange = point_exchange;
	}
	public boolean isActive_addon() {
		return active_addon;
	}
	public void setActive_addon(boolean active_addon) {
		this.active_addon = active_addon;
	}
	public boolean isPoint_add() {
		return point_add;
	}
	public void setPoint_add(boolean point_add) {
		this.point_add = point_add;
	}
	public boolean isBank_card() {
		return bank_card;
	}
	public void setBank_card(boolean bank_card) {
		this.bank_card = bank_card;
	}
	public boolean isElectricity_coupon() {
		return electricity_coupon;
	}
	public void setElectricity_coupon(boolean electricity_coupon) {
		this.electricity_coupon = electricity_coupon;
	}
	public boolean isTimes_buy() {
		return times_buy;
	}
	public void setTimes_buy(boolean times_buy) {
		this.times_buy = times_buy;
	}
	public boolean isTimes_custome() {
		return times_custome;
	}
	public void setTimes_custome(boolean times_custome) {
		this.times_custome = times_custome;
	}
	public boolean isCancel_trade() {
		return cancel_trade;
	}
	public void setCancel_trade(boolean cancel_trade) {
		this.cancel_trade = cancel_trade;
	}
	public boolean isScan_pay() {
		return scan_pay;
	}
	public void setScan_pay(boolean scan_pay) {
		this.scan_pay = scan_pay;
	}

	
	
	
}
