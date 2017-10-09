package com.tool.utils.dialog;

////////////////////////////////////////////////////////////////////
//                          _ooOoo_                               //
//                         o8888888o                              //
//                         88" . "88                              //
//                         (| ^_^ |)                              //
//                         O\  =  /O                              //
//                      ____/`---'\____                           //
//                    .'  \\|     |//  `.                         //
//                   /  \\|||  :  |||//  \                        //
//                  /  _||||| -:- |||||-  \                       //
//                  |   | \\\  -  /// |   |                       //
//                  | \_|  ''\---/''  |   |                       //
//                  \  .-\__  `-`  ___/-. /                       //
//                ___`. .'  /--.--\  `. . ___                     //
//              ."" '<  `.___\_<|>_/___.'  >'"".                  //
//            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
//            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
//      ========`-.____`-.___\_____/___.-`____.-'========         //
//                           `=---='                              //
//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
//              佛祖保佑       永无BUG     永不修改                  //
//                                                                //
//          佛曰:                                                  //
//                  写字楼里写字间，写字间里程序员；                   //
//                  程序人员写程序，又拿程序换酒钱。                   //
//                  酒醒只在网上坐，酒醉还来网下眠；                   //
//                  酒醉酒醒日复日，网上网下年复年。                   //
//                  但愿老死电脑间，不愿鞠躬老板前；                   //
//                  奔驰宝马贵者趣，公交自行程序员。                   //
//                  别人笑我忒疯癫，我笑自己命太贱；                   //
//                  不见满街漂亮妹，哪个归得程序员？                   //
////////////////////////////////////////////////////////////////////

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tool.utils.view.LinePathView;
import com.toollibrary.R;

import java.io.IOException;

/**********************************************************
 *                                                        *
 *                  Created by wucongpeng on 2017/4/26.        *
 **********************************************************/


public class SignDialog extends AlertDialog implements View.OnClickListener {

    private Context mContext;

    private OnClickInterface onClickInterface;

    private LinePathView pathView;

    private Button btnSure;
    private Button btnReset;




    public interface OnClickInterface {
        void onClickSure(Bitmap bitmap);

    }

    public SignDialog(Context context, OnClickInterface listener) {
        super(context, R.style.SignDialog);
        this.mContext = context;
        this.onClickInterface = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);

        //点击imageview外侧区域，动画不会消失
        setCanceledOnTouchOutside(false);

        pathView = (LinePathView) findViewById(R.id.graffitiView_2);

        btnSure = (Button) findViewById(R.id.id_btn_left);
        btnSure.setOnClickListener(this);

        btnReset = (Button) findViewById(R.id.id_btn_right);
        btnReset.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.id_btn_left) {
            if (!pathView.getTouched()){
                return;
            }
            this.dismiss();
            try {
                pathView.save();
                onClickInterface.onClickSure(pathView.getBitmap1());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (i == R.id.id_btn_right) {
            pathView.clear();
        }
    }
}
