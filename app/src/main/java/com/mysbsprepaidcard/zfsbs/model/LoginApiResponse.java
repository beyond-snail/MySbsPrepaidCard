package com.mysbsprepaidcard.zfsbs.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

public class LoginApiResponse extends DataSupport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5060863143246134147L;
	private int id;
	private int sid; // 商户id
	private String merchantNo; // 商户号
	private String terminalNo; // 商户终端号
	private String licenseNo; // 营业执照
	private String accountName; // 收款账户
	private String accountBank; // 收款银行
	private String accountNo; // 收款账号
	private String terminalName; // 终端名称
	private String activeCode; // 激活码
	private String other; // 授权码
	private String fyMerchantNo; //扫码商户号
	private String fyMerchantName; //扫码商户名称
	private String scanPayType; //扫码支付通道
	private String operatList; // 权限列表
	private List<OperatorBean> operator_list; //操作员信息
	private String operatorListData; //操作员数据json字串
	private boolean isDownMasterKey; // 下载主密钥
	private int keyIndex; // 密钥索引
	private boolean isActive; // 激活码的状态
	private boolean isCurrent; // 是否为当前使用的标识
	private boolean isCheckLogin; //检测当天是否签到
	private String activateCodeMerchantNo; // 激活码商户号
	private String activateCodemerchantName; // 激活码商户名字
	private String activateMerchantNanme; //激活码名称/终端名称(这个有可能POS返回的是商户名称)

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getFyMerchantNo() {
		return fyMerchantNo;
	}

	public void setFyMerchantNo(String fyMerchantNo) {
		this.fyMerchantNo = fyMerchantNo;
	}

	public String getFyMerchantName() {
		return fyMerchantName;
	}

	public void setFyMerchantName(String fyMerchantName) {
		this.fyMerchantName = fyMerchantName;
	}

	public String getScanPayType() {
		return scanPayType;
	}

	public void setScanPayType(String scanPayType) {
		this.scanPayType = scanPayType;
	}

	public String getOperatList() {
		return operatList;
	}

	public void setOperatList(String operatList) {
		this.operatList = operatList;
	}

	public List<OperatorBean> getOperator_list() {
		return operator_list;
	}

	public void setOperator_list(List<OperatorBean> operator_list) {
		this.operator_list = operator_list;
	}

	public String getOperatorListData() {
		return operatorListData;
	}

	public void setOperatorListData(String operatorListData) {
		this.operatorListData = operatorListData;
	}

	public boolean isDownMasterKey() {
		return isDownMasterKey;
	}

	public void setDownMasterKey(boolean isDownMasterKey) {
		this.isDownMasterKey = isDownMasterKey;
	}

	public int getKeyIndex() {
		return keyIndex;
	}

	public void setKeyIndex(int keyIndex) {
		this.keyIndex = keyIndex;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCurrent() {
		return isCurrent;
	}

	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public boolean isCheckLogin() {
		return isCheckLogin;
	}

	public void setCheckLogin(boolean isCheckLogin) {
		this.isCheckLogin = isCheckLogin;
	}

	public String getActivateCodeMerchantNo() {
		return activateCodeMerchantNo;
	}

	public void setActivateCodeMerchantNo(String activateCodeMerchantNo) {
		this.activateCodeMerchantNo = activateCodeMerchantNo;
	}

	public String getActivateCodemerchantName() {
		return activateCodemerchantName;
	}

	public void setActivateCodemerchantName(String activateCodemerchantName) {
		this.activateCodemerchantName = activateCodemerchantName;
	}

	public String getActivateMerchantNanme() {
		return activateMerchantNanme;
	}

	public void setActivateMerchantNanme(String activateMerchantNanme) {
		this.activateMerchantNanme = activateMerchantNanme;
	}


	@Override
	public String toString() {
		return "LoginApiResponse{" +
				"id=" + id +
				", sid=" + sid +
				", merchantNo='" + merchantNo + '\'' +
				", terminalNo='" + terminalNo + '\'' +
				", licenseNo='" + licenseNo + '\'' +
				", accountName='" + accountName + '\'' +
				", accountBank='" + accountBank + '\'' +
				", accountNo='" + accountNo + '\'' +
				", terminalName='" + terminalName + '\'' +
				", activeCode='" + activeCode + '\'' +
				", other='" + other + '\'' +
				", fyMerchantNo='" + fyMerchantNo + '\'' +
				", fyMerchantName='" + fyMerchantName + '\'' +
				", scanPayType='" + scanPayType + '\'' +
				", operatList='" + operatList + '\'' +
				", operator_list=" + operator_list +
				", operatorListData='" + operatorListData + '\'' +
				", isDownMasterKey=" + isDownMasterKey +
				", keyIndex=" + keyIndex +
				", isActive=" + isActive +
				", isCurrent=" + isCurrent +
				", isCheckLogin=" + isCheckLogin +
				", activateCodeMerchantNo='" + activateCodeMerchantNo + '\'' +
				", activateCodemerchantName='" + activateCodemerchantName + '\'' +
				", activateMerchantNanme='" + activateMerchantNanme + '\'' +
				'}';
	}
}
