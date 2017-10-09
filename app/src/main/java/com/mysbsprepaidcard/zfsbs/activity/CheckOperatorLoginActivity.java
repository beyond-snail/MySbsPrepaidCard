package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;
import com.tool.utils.view.ClearEditText;


public class CheckOperatorLoginActivity extends BaseActivity implements OnClickListener {

    private Button btnOperatorLogin;
    private ClearEditText edPassWord;
    private ClearEditText edUserName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_check_operator);
        AppManager.getAppManager().addActivity(this);
        initTitle("操作员登录");
        initView();
        addListener();
    }




    private void initView() {
        btnOperatorLogin = (Button) findViewById(R.id.id_login);
        edPassWord = (ClearEditText) findViewById(R.id.password);
        edPassWord.setText("");
        edUserName = (ClearEditText) findViewById(R.id.id_username);
        String usrName = (String) SPUtils.get(mContext, Constants.USER_NAME,"");
        edUserName.setText(usrName);
        edUserName.setSelection(edUserName.getText().length());
//        edUserName.setEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        edPassWord.setText("");
    }

    private void addListener() {
        btnOperatorLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_login:
                String psw = (String) SPUtils.get(mContext, Constants.USER_PSW, "");
                if (StringUtils.isEquals(edPassWord.getText().toString().trim(), psw)){
                    CommonFunc.startAction(CheckOperatorLoginActivity.this, RechargeActivity.class, false);
                }else{
                    ToastUtils.CustomShow(mContext, "密码错误");
                }
                break;
            default:
                break;
        }
    }










}
