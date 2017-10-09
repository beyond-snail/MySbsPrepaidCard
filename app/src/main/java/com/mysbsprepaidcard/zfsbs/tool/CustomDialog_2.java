package com.mysbsprepaidcard.zfsbs.tool;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mysbsprepaidcard.R;

/**
 * Created by zf on 2017/2/24.
 */

public class CustomDialog_2 extends Dialog implements View.OnClickListener {

    private int layoutRes;// 布局文件
    private Context context;
    private CustomDialog_2.onClickLeftListener leftLister;
    private CustomDialog_2.onClickRightListener rightLister;

    private TextView etInputName;
    private Button btnLeft;
    private Button btnRight;
    private TextView activity_title;

    public CustomDialog_2(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * 提问框 Listener
     */
    public interface onClickLeftListener {
        abstract void onClickLeft(CustomDialog_2 dialog, String result);
    }

    /**
     * 提问框 Listener
     */
    public interface onClickRightListener {
        abstract void onClickRight(CustomDialog_2 dialog);
    }

    /**
     * 自定义布局的构造方法
     *
     * @param context
     * @param resLayout
     */
    public CustomDialog_2(Context context, int resLayout, CustomDialog_2.onClickLeftListener leftLister, CustomDialog_2.onClickRightListener rightListener) {
        super(context, R.style.ConponsDialog);
        this.context = context;
        this.layoutRes = resLayout;
        this.leftLister = leftLister;
        this.rightLister = rightListener;
        this.setContentView(layoutRes);
        initView();
    }

    /**
     * 自定义主题及布局的构造方法
     *
     * @param context
     * @param theme
     * @param resLayout
     */
    public CustomDialog_2(Context context, int theme, int resLayout, CustomDialog_2.onClickLeftListener leftLister, CustomDialog_2.onClickRightListener rightLister) {
        super(context, R.style.ConponsDialog);
        this.context = context;
        this.layoutRes = resLayout;
        this.leftLister = leftLister;
        this.rightLister = rightLister;
        this.setContentView(layoutRes);
        initView();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setGravity(Gravity.CENTER); //显示在底部

        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth()-20; //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);


        addListener();
    }

    public void setRightButtonVisible(boolean bool){
        if (bool) {
            btnRight.setVisibility(View.GONE);
        }else{
            btnRight.setVisibility(View.VISIBLE);
        }

    }
    public  void setMessage(String message){
        etInputName.setText(message);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        activity_title.setText(title);
    }

    private void initView() {
        etInputName = (TextView) findViewById(R.id.id_phoneNo);
        btnLeft = (Button) findViewById(R.id.id_left);
        btnRight = (Button) findViewById(R.id.id_right);
        activity_title=(TextView)findViewById(R.id.activity_title);
    }

    private void addListener() {
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_left:
                if (etInputName.getText().toString().length() > 0){
                    leftLister.onClickLeft(this, etInputName.getText().toString());
                }
                break;
            case R.id.id_right:
                rightLister.onClickRight(this);
                break;
            default:
                break;
        }
    }

}
