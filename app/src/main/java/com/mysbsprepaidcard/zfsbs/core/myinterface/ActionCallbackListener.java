package com.mysbsprepaidcard.zfsbs.core.myinterface;

public interface ActionCallbackListener<T> {
	/**
     * 成功时调用
     *
     * @param data 返回的数据
     */
    public void onSuccess(T data);

    /**
     * 失败时调用
     *
     * @param errorEvent 错误码
     * @param message    错误信息
     */
    public void onFailure(String errorEvent, String message);

    public void onFailurTimeOut(String s, String error_msg);

    /**
     * 登录失效时重新登录
     */
    public void onLogin();

}
