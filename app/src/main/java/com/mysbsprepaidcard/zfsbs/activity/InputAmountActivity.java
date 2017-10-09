package com.mysbsprepaidcard.zfsbs.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Config;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.CouponsResponse;
import com.mysbsprepaidcard.zfsbs.model.MemberTransAmountResponse;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.dialog.MemberNoDialog;
import com.tool.utils.dialog.MemberNoDialog1;
import com.tool.utils.msrcard.MsrCard;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;


public class InputAmountActivity extends BaseActivity implements OnClickListener {

    public static final int REQUEST_CAPTURE = 0;

    private TextView tKey1;
    private TextView tKey2;
    private TextView tKey3;
    private TextView tKey4;
    private TextView tKey5;
    private TextView tKey6;
    private TextView tKey7;
    private TextView tKey8;
    private TextView tKey9;
    private TextView tKey0;
    private TextView tkey00;

    private TextView tKeyblack;
    private TextView tKeyCaculate;
    private TextView tAmount;

    private int amount = 0;

    private int app_type = 0;

    private String g_phone; //输入的手机号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_input_amount);
        AppManager.getAppManager().addActivity(this);
        initTitle("输入金额");
        initView();
        addLinstener();
        initData();

    }

    private void initData() {

        //是否是赢消费
        app_type = (int) SPUtils.get(this, Config.APP_TYPE, Config.DEFAULT_APP_TYPE);//getIntent().getBooleanExtra("yxf", false);

    }



    private void initView() {

        tKey0 = (TextView) findViewById(R.id.id_key_0);
        tkey00 = (TextView) findViewById(R.id.id_key_00);
        tKey1 = (TextView) findViewById(R.id.id_key_1);
        tKey2 = (TextView) findViewById(R.id.id_key_2);
        tKey3 = (TextView) findViewById(R.id.id_key_3);
        tKey4 = (TextView) findViewById(R.id.id_key_4);
        tKey5 = (TextView) findViewById(R.id.id_key_5);
        tKey6 = (TextView) findViewById(R.id.id_key_6);
        tKey7 = (TextView) findViewById(R.id.id_key_7);
        tKey8 = (TextView) findViewById(R.id.id_key_8);
        tKey9 = (TextView) findViewById(R.id.id_key_9);

        tKeyblack = (TextView) findViewById(R.id.id_key_back);
        tKeyCaculate = (TextView) findViewById(R.id.id_key_caculate);
        tAmount = (TextView) findViewById(R.id.id_tv_amount);
    }

    private void addLinstener() {
        tKey0.setOnClickListener(this);
        tkey00.setOnClickListener(this);
        tKey1.setOnClickListener(this);
        tKey2.setOnClickListener(this);
        tKey3.setOnClickListener(this);
        tKey4.setOnClickListener(this);
        tKey5.setOnClickListener(this);
        tKey6.setOnClickListener(this);
        tKey7.setOnClickListener(this);
        tKey8.setOnClickListener(this);
        tKey9.setOnClickListener(this);
        tKeyblack.setOnClickListener(this);
        tKeyCaculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_key_0:
                setTextAmount(0);
                break;
            case R.id.id_key_00:
                setTextDouble();
                break;
            case R.id.id_key_1:
                setTextAmount(1);
                break;
            case R.id.id_key_2:
                setTextAmount(2);
                break;
            case R.id.id_key_3:
                setTextAmount(3);
                break;
            case R.id.id_key_4:
                setTextAmount(4);
                break;
            case R.id.id_key_5:
                setTextAmount(5);
                break;
            case R.id.id_key_6:
                setTextAmount(6);
                break;
            case R.id.id_key_7:
                setTextAmount(7);
                break;
            case R.id.id_key_8:
                setTextAmount(8);
                break;
            case R.id.id_key_9:
                setTextAmount(9);
                break;
            case R.id.id_key_back:
                amount = amount / 10;
                tAmount.setText(StringUtils.formatAmount(amount));
                break;
            case R.id.id_key_caculate:
                Caculate();
                break;

            default:
                break;
        }
    }

    private void Caculate() {

        //判断签到
        if (CommonFunc.isLogin(this, Constants.SBS_LOGIN_TIME, Constants.DEFAULT_SBS_LOGIN_TIME)){
            AppManager.getAppManager().finishAllActivity();
            if (Config.OPERATOR_UI_BEFORE) {
                CommonFunc.startAction(InputAmountActivity.this, OperatorLoginActivity.class, false);
            } else {
                CommonFunc.startAction(InputAmountActivity.this, OperatorLoginActivity1.class, false);
            }

            return;
        }

        if (amount > 0 && amount <= 999999999) {
            if (app_type == Config.APP_HD) {
                isHdInputMemberNo();
            } else {
                isInputMemberNo();
            }
        } else {
            if (amount > 999999999) {
                ToastUtils.CustomShow(this, "金额过大");
            } else {
                ToastUtils.CustomShow(this, "请输入支付金额");
            }
        }
    }


    private void setTextAmount(int digital) {
        if (amount < 100000000) {
            amount = amount * 10 + digital;
            tAmount.setText(StringUtils.formatAmount(amount));
        }
    }

    private void setTextDouble() {
        if (amount < 100000000) {
            amount = amount * 10;
            if (amount < 100000000) {
                amount = amount * 10;
                tAmount.setText(StringUtils.formatAmount(amount));
            } else {
                amount = amount / 10;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CAPTURE:
                // 处理扫描结果（在界面上显示）
                String phoneNo = data.getStringExtra(CaptureActivity.SCAN_RESULT);
                MemberNoDialog.setMemberNo(phoneNo);
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void isInputMemberNo() {
        MemberNoDialog dialog = new MemberNoDialog(this, R.layout.activity_member_no2, new MemberNoDialog.OnClickInterface() {

            @Override
            public void onResultScanContent() {
                CommonFunc.startResultAction(InputAmountActivity.this, CaptureActivity.class, null, REQUEST_CAPTURE);
            }

            @Override
            public void onClickRight() {
                if (app_type == Config.APP_Richer_e) {

                } else {
                    MemberNoDialog.setMemberNo("");

                    MemberTransAmountResponse member = new MemberTransAmountResponse();
                    member.setRealMoney(amount);
                    member.setTradeMoney(amount);
                    CommonFunc.setBackMemberInfo(InputAmountActivity.this, member);
                    CommonFunc.startAction(InputAmountActivity.this, ZfPayActivity.class, true);
                }

            }

            @Override
            public void onClickLeft(String result) {
                if (app_type == Config.APP_SBS) {
                    memberInfoAction(result);
                } else if (app_type == Config.APP_YXF) {

                    MemberTransAmountResponse member = new MemberTransAmountResponse();
                    member.setRealMoney(amount);
                    member.setTradeMoney(amount);
                    member.setMemberCardNo(result);
                    CommonFunc.setBackMemberInfo(InputAmountActivity.this, member);
                    CommonFunc.startAction(InputAmountActivity.this, ZfPayActivity.class, true);

                } else if (app_type == Config.APP_Richer_e) {
//                    Richer_memberInfoAction(result);
                }

            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                MsrCard.getMsrCard(InputAmountActivity.this).closeMsrCard();
            }
        });
        dialog.setCancelable(true);
        dialog.show();

    }


    private void isHdInputMemberNo() {
        MemberNoDialog1 dialog = new MemberNoDialog1(this, R.layout.activity_member_no1, new MemberNoDialog1.OnClickInterface() {

            @Override
            public void onResultScanContent() {
            }

            @Override
            public void onClickMid() {
                MemberNoDialog1.setMemberNo("");

                MemberTransAmountResponse member = new MemberTransAmountResponse();
                member.setRealMoney(amount);
                member.setTradeMoney(amount);
                CommonFunc.setBackMemberInfo(InputAmountActivity.this, member);
                //是否会员
                SPUtils.put(InputAmountActivity.this, Config.isHdMember, false);
                CommonFunc.startAction(InputAmountActivity.this, ZfPayActivity.class, true);
            }

            @Override
            public void onClickRight() {
                SPUtils.put(InputAmountActivity.this, Config.isHdMember, true);
//                goHdRegister();

            }

            @Override
            public void onClickLeft(String result) {
                SPUtils.put(InputAmountActivity.this, Config.isHdMember, true);
//                goHdQuery(result);

            }
        });


        dialog.setCancelable(true);
        dialog.show();
        MemberNoDialog1.isHideScanIcon(true);
        MemberNoDialog1.setBtnRightText("注册");
        MemberNoDialog1.setEtInputNoText("请输入手机号");

    }

    /**
     * 注册
     */
    private void goHdRegister() {
//        Bundle bundle = new Bundle();
//        bundle.putInt("amount", amount);
//        CommonFunc.startAction(InputAmountActivity.this, HdRegisterActivity.class, bundle, true);
    }

    /**
     * 去海鼎查询
     * @param result
     */
//    private void goHdQuery(String result) {
//        HdAction.hdQuery(InputAmountActivity.this, result, new HdAction.HdCallResult(){
//            @Override
//            public void onSuccess(String data) {
//                MemberTransAmountResponse member = new MemberTransAmountResponse();
//                member.setRealMoney(amount);
//                member.setTradeMoney(amount);
//                member.setPhone(data);
//                CommonFunc.setBackMemberInfo(InputAmountActivity.this, member);
//                CommonFunc.startAction(InputAmountActivity.this, ZfPayActivity.class, true);
//            }
//
//            @Override
//            public void onFailed(String errorCode, String message) {
//                ToastUtils.CustomShow(InputAmountActivity.this, errorCode+"#"+message);
//            }
//        });
//    }

    /**
     * 商博士-会员信息
     * @param phone
     */
    private void memberInfoAction(String phone) {
        int sid = MyApplication.getInstance().getLoginData().getSid();

        this.sbsAction.getMemberInfo(this, sid, phone, amount, new ActionCallbackListener<CouponsResponse>() {
            @Override
            public void onSuccess(CouponsResponse data) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("member", data);
                bundle.putString("amount", tAmount.getText().toString());
                CommonFunc.startAction(InputAmountActivity.this, MemberActivity.class, bundle, true);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(InputAmountActivity.this, message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(InputAmountActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(InputAmountActivity.this, OperatorLoginActivity1.class, false);
                }
            }
        });
    }

//    private void Richer_memberInfoAction(String phone) {
//        g_phone = phone;
//        RicherQb.getMemberInfo(this, phone, new ActionCallbackListener<RicherGetMember>() {
//            @Override
//            public void onSuccess(RicherGetMember data) {
//
//                CustomDialog_2 custom = new CustomDialog_2(InputAmountActivity.this, R.layout.activity_custom_dialog, new CustomDialog_2.onClickLeftListener() {
//                    @Override
//                    public void onClickLeft(CustomDialog_2 dialog, String result) {
//                        dialog.dismiss();
//                        printerData.setCardNo(g_phone);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("amount", tAmount.getText().toString());
//                        bundle.putString("phone", g_phone);
//
//                        MemberTransAmountResponse member = new MemberTransAmountResponse();
//                        member.setRealMoney(amount);
//                        member.setTradeMoney(amount);
//                        member.setMemberCardNo(g_phone);
//                        CommonFunc.setBackMemberInfo(InputAmountActivity.this, member);
//                        CommonFunc.startAction(InputAmountActivity.this, ZfPayActivity.class, bundle, true);
//
//                    }
//                }, new CustomDialog_2.onClickRightListener() {
//                    @Override
//                    public void onClickRight(CustomDialog_2 dialog) {
//                        dialog.dismiss();
//                    }
//                });
//                custom.setTitle(g_phone);
//                custom.setMessage("会员名称：" + data.getNickname());
//                custom.setCancelable(true);
//                custom.show();
//
//            }
//
//            @Override
//            public void onFailure(String errorEvent, String message) {
//                if (message.contains("网络异常")) {
//                    ToastUtils.CustomShow(InputAmountActivity.this, message);
//                    return;
//                }
//
//
//                CustomDialog_2 custom = new CustomDialog_2(InputAmountActivity.this, R.layout.activity_custom_dialog, new CustomDialog_2.onClickLeftListener() {
//                    @Override
//                    public void onClickLeft(CustomDialog_2 dialog, String result) {
//                        dialog.dismiss();
//                    }
//                }, new CustomDialog_2.onClickRightListener() {
//                    @Override
//                    public void onClickRight(CustomDialog_2 dialog) {
//                        dialog.dismiss();
//                    }
//                });
//                custom.setTitle(g_phone);
//                custom.setMessage("非会员手机号不能进行交易");
//                custom.setCancelable(false);
//                custom.setRightButtonVisible(true);
//                custom.show();
//            }
//
//            @Override
//            public void onLogin() {
//                AppManager.getAppManager().finishAllActivity();
//
//                if (Config.OPERATOR_UI_BEFORE) {
//                    CommonFunc.startAction(InputAmountActivity.this, OperatorLoginActivity.class, false);
//                } else {
//                    CommonFunc.startAction(InputAmountActivity.this, OperatorLoginActivity1.class, false);
//                }
//            }
//
//            @Override
//            public void onFailurTimeOut(String s, String error_msg) {
//
//            }
//        });
//    }
}
