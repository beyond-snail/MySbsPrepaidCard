package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Config;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoom;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;


public class ShiftRoomActivity extends BaseActivity {

    private LinearLayout btnShitRoom;
    private LinearLayout btnShitRoomDay;
    private LinearLayout btnShitRoomRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_shift_room);
        AppManager.getAppManager().addActivity(this);
        initTitle("班结");

        btnShitRoom = (LinearLayout) findViewById(R.id.id_ll_shiftroom);
        btnShitRoomDay = (LinearLayout) findViewById(R.id.id_ll_shiftroom_day);
        btnShitRoomRecord = (LinearLayout) findViewById(R.id.id_ll_shiftroom_record);


        btnShitRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShiftRoom();
            }
        });

        btnShitRoomDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShiftRoomDay();
            }
        });

        btnShitRoomRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonFunc.startAction(ShiftRoomActivity.this, ShiftRoomRecordActivity.class, false);
            }
        });

    }




    /**
     * 班接
     */
    private void getShiftRoom() {

        int sid = MyApplication.getInstance().getLoginData().getSid();
        final long start_time = StringUtils.getdate2TimeStamp((String) SPUtils.get(this, Constants.SHIFT_ROOM_TIME, Constants.DEFAULT_SHIFT_ROOM_TIME));
        LogUtils.e("start_time=", (String) SPUtils.get(this, Constants.SHIFT_ROOM_TIME, Constants.DEFAULT_SHIFT_ROOM_TIME));
        final long end_time = StringUtils.getdate2TimeStamp(StringUtils.getCurTime());
        LogUtils.e("end_time", StringUtils.getCurTime());
        this.sbsAction.shift_room(this, sid, start_time, end_time, new ActionCallbackListener<ShiftRoom>() {
            @Override
            public void onSuccess(final ShiftRoom data) {
                LogUtils.e("onSucess"+data.toString());


                Bundle bundle = new Bundle();
                bundle.putSerializable("ShiftRoom", data);
                bundle.putLong("start_time", start_time);
                bundle.putLong("end_time", end_time);
                bundle.putInt("type", Constants.PRINTER_SHIFT_ROOM);
                CommonFunc.startAction(ShiftRoomActivity.this, ShiftRoomShowActivity.class, bundle, false);

            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(ShiftRoomActivity.this, errorEvent+message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(ShiftRoomActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(ShiftRoomActivity.this, OperatorLoginActivity1.class, false);
                }

            }
        });
    }

    /**
     * 班接
     */
    private void getShiftRoomDay() {

        int sid = MyApplication.getInstance().getLoginData().getSid();
        final long start_time = StringUtils.getdate2TimeStamp(StringUtils.formatTime(StringUtils.getCurDate()+"000000"));
        LogUtils.e("start_time=", StringUtils.formatTime(StringUtils.getCurDate()+"000000"));
        final long end_time = StringUtils.getdate2TimeStamp(StringUtils.getCurTime());
        LogUtils.e("end_time", StringUtils.getCurTime());
        this.sbsAction.shift_room(this, sid, start_time, end_time, new ActionCallbackListener<ShiftRoom>() {
            @Override
            public void onSuccess(final ShiftRoom data) {
                LogUtils.e("onSuccess"+data.toString());

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("ShiftRoom", data);
//                        bundle.putLong("start_time", start_time);
//                        bundle.putLong("end_time", end_time);
//                        bundle.putInt("type", Constants.PRINTER_SHIFT_ROOM_DAY);
//                        startAction(ShiftRoomActivity.this, ShiftRoomShowActivity.class, bundle, false);
//                    }
//                });

                Bundle bundle = new Bundle();
                bundle.putSerializable("ShiftRoom", data);
                bundle.putLong("start_time", start_time);
                bundle.putLong("end_time", end_time);
                bundle.putInt("type", Constants.PRINTER_SHIFT_ROOM_DAY);
                CommonFunc.startAction(ShiftRoomActivity.this, ShiftRoomShowActivity.class, bundle, false);

            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(ShiftRoomActivity.this, errorEvent+message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(ShiftRoomActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(ShiftRoomActivity.this, OperatorLoginActivity1.class, false);
                }
            }
        });
    }
}
