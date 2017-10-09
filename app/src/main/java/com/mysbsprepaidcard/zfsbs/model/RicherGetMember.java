package com.mysbsprepaidcard.zfsbs.model;


import java.io.Serializable;

/**
 * Created by zf on 2017/2/23.
 */

public class RicherGetMember implements Serializable {

    private String msg;
    private String code;
    private String nickname; // 会员名称


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "RicherGetMember{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
