package com.mysbsprepaidcard.zfsbs.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.action.LoginAction;
import com.mysbsprepaidcard.zfsbs.core.myinterface.UiAction;
import com.tool.utils.utils.NetUtils;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;


public class OperatorLoginActivity extends BaseActivity implements OnClickListener, OnTouchListener {

    private Button btnOperatorLogin;
    private EditText edPassWord;

    private LoginAction loginAction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_login);
//        AppManager.getAppManager().addActivity(this);
        initView();
        initData();
        addListener();
    }


    private void initData() {
        loginAction = new LoginAction(this, uiAction);
    }

    private void initView() {
        btnOperatorLogin = (Button) findViewById(R.id.id_login);
        edPassWord = (EditText) findViewById(R.id.password);
        edPassWord.setText("");
        edPassWord.setSelection(edPassWord.getText().length());
        edPassWord.setOnTouchListener(this);

    }

    private void addListener() {
        btnOperatorLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_login:
                login();
                break;
            default:
                break;
        }
    }

    /**
     * 密码框获取焦点
     */
    private void getFocus() {
        edPassWord.postDelayed(new Runnable() {
            @Override
            public void run() {
                edPassWord.setFocusable(true);
                edPassWord.setFocusableInTouchMode(true);
                edPassWord.requestFocus();
                edPassWord.findFocus();
            }
        }, 500);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPassWord.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        getFocus();
        return true;
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            login();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    private UiAction uiAction = new UiAction() {

        @Override
        public void UiResultAction(Activity context, Class<?> cls, Bundle bundle, int requestCode) {
            CommonFunc.startResultAction(context, cls, bundle, requestCode);
        }

        @Override
        public void UiAction(Activity context, Class<?> cls, Bundle bundle, boolean flag) {
            CommonFunc.startAction(context, cls, bundle, flag);
        }

        @Override
        public void UiAction(Activity context, Class<?> cls, boolean flag) {
            CommonFunc.startAction(context, cls, flag);
        }
    };

    private void login() {
        String pass = (String) SPUtils.get(this, Constants.OPERATOR_PASS, Constants.DEFAULT_OPERATOR_PASS);
        if (NetUtils.isConnected(OperatorLoginActivity.this)) {
            if (TextUtils.isEmpty(edPassWord.getText().toString())) {
                ToastUtils.CustomShow(this, "请输入密码");
                getFocus();
                return;
            }
            if (StringUtils.isEquals(pass, edPassWord.getText().toString())) {
                // 判断当天是否再次签到
                if (!CommonFunc.isLogin(this, Constants.SBS_LOGIN_TIME, Constants.DEFAULT_SBS_LOGIN_TIME)) {
                    // 直接进入主界面
                    CommonFunc.startAction(this, SaleMainActivity.class, true);
                    return;
                }
                loginAction.loginAction();
            } else {
                ToastUtils.CustomShow(this, "密码错误");
                getFocus();
            }

        } else {
            ToastUtils.CustomShow(this, "请打开网络");
            getFocus();
        }
    }








}
