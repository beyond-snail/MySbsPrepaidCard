package com.mysbsprepaidcard.zfsbs.model;

import java.io.Serializable;

public class ActivateApiResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private int terminalld; // 终端ID
	private String merchantNo; // 商户号
	private String merchantName; // 商户名字
	private String terminalName; //激活码名称/终端名称
//	private int isSuccess; //激活成功失败

//	public int getTerminalld() {
//		return terminalld;
//	}
//
//	public void setTerminalld(int terminalld) {
//		this.terminalld = terminalld;
//	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

//	public int getIsSuccess() {
//		return isSuccess;
//	}
//
//	public void setIsSuccess(int isSuccess) {
//		this.isSuccess = isSuccess;
//	}


	@Override
	public String toString() {
		return "ActivateApiResponse{" +
				"merchantNo='" + merchantNo + '\'' +
				", merchantName='" + merchantName + '\'' +
				", terminalName='" + terminalName + '\'' +
				'}';
	}
}
