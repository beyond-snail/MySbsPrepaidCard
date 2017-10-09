package com.mysbsprepaidcard.zfsbs.activity;
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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.config.Config;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;


/**********************************************************
 * *
 * Created by wucongpeng on 2017/2/10.        *
 **********************************************************/


public class YxfManagerActivity extends BaseActivity implements View.OnClickListener{

    private Button btnYxfSet;
    private EditText etMerchant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yxf_manager);
//        AppManager.getAppManager().addActivity(this);

        btnYxfSet = (Button) findViewById(R.id.id_sure);
        etMerchant = (EditText) findViewById(R.id.id_set_merchant_id);

        btnYxfSet.setOnClickListener(this);

        etMerchant.setText((CharSequence) SPUtils.get(this, Config.YXF_MERCHANT_ID, Config.YXF_DEFAULT_MERCHANTID));
        etMerchant.setSelection(((CharSequence) SPUtils.get(this, Config.YXF_MERCHANT_ID, Config.YXF_DEFAULT_MERCHANTID)).length());
//        etMerchant.setText("");
//        etMerchant.setSelection("".length());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.id_sure) {
            CheckEditData();
        }
    }

    private void CheckEditData() {

        if (StringUtils.isEmpty(etMerchant.getText().toString().trim())){
            ToastUtils.CustomShow(this, "请输入商户ID");
            return;
        }

        SPUtils.put(this, Config.YXF_MERCHANT_ID, etMerchant.getText().toString().trim());

        finish();
    }
}
