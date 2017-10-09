package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.adapter.MyShiftRoom;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Config;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.core.action.Printer;
import com.mysbsprepaidcard.zfsbs.core.myinterface.ActionCallbackListener;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoom;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoomSave;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoomShow;
import com.mysbsprepaidcard.zfsbs.myapplication.MyApplication;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.DateTimePickDialogUtil;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.SPUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class ShiftRoomShowActivity extends BaseActivity implements View.OnClickListener {


    private EditText tvStartTime;
    private EditText tvEndTime;
    private TextView title;
    private Button btnPrinter;
    private Button btnGet;
    private ShiftRoom shiftRoom;

    private List<ShiftRoomShow> shiftRoomShows = new ArrayList<>();

    private ListView lv;
    private MyShiftRoom adapter;

    private long start_time;
    private long end_time;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_shift_room_show);
        AppManager.getAppManager().addActivity(this);

//        title = (TextView) findViewById(R.id.activity_title);

        lv = (ListView) findViewById(R.id.show_shift_room_list);

        tvStartTime = (EditText) findViewById(R.id.id_start_time);
        tvEndTime = (EditText) findViewById(R.id.id_end_time);




        btnPrinter = (Button) findViewById(R.id.id_printer);
        btnPrinter.setOnClickListener(this);

        btnGet = (Button) findViewById(R.id.id_get);
        btnGet.setOnClickListener(this);


        shiftRoom = (ShiftRoom) getIntent().getExtras().getSerializable("ShiftRoom");

        start_time = getIntent().getExtras().getLong("start_time");
        end_time = getIntent().getExtras().getLong("end_time");
        type = getIntent().getExtras().getInt("type");


        tvStartTime.setText(StringUtils.timeStamp2Date1(start_time + ""));
        tvEndTime.setText(StringUtils.timeStamp2Date1(end_time + ""));




        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        ShiftRoomShowActivity.this, StringUtils.timeStamp2Date1(start_time + ""));
                dateTimePicKDialog.dateTimePicKDialog(tvStartTime);
            }
        });

        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        ShiftRoomShowActivity.this, StringUtils.timeStamp2Date1(end_time + ""));
                dateTimePicKDialog.dateTimePicKDialog(tvEndTime);
            }
        });


        if (type == Constants.PRINTER_SHIFT_ROOM){
            initTitle("班结统计");
//            title.setText("班结统计");

        }else if (type == Constants.PRINTER_SHIFT_ROOM_DAY){
            initTitle("当日统计");
//            title.setText("当日统计");
//            btnGet.setVisibility(View.GONE);
        }




        getdata(shiftRoom);

        adapter = new MyShiftRoom(this, shiftRoomShows, R.layout.activity_shift_room_item);
        lv.setAdapter(adapter);





    }


    private void getdata(ShiftRoom shiftRoom){
        if (shiftRoom != null){


            if(shiftRoom.getPay_swipe() != null) {
                setShowData("刷卡", shiftRoom.getPay_swipe().getTrade_num(),
                        shiftRoom.getPay_swipe().getReal_pay_money(),
                        shiftRoom.getPay_swipe().getReal_undo_money(),
                        shiftRoom.getPay_swipe().getCoupon_deduct(),
                        shiftRoom.getPay_swipe().getIntergral_deduct(), 1);
            }else {
                setShowData("刷卡", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if(shiftRoom.getPay_cash() != null) {
                setShowData("现金", shiftRoom.getPay_cash().getTrade_num(),
                        shiftRoom.getPay_cash().getReal_pay_money(),
                        shiftRoom.getPay_cash().getReal_undo_money(),
                        shiftRoom.getPay_cash().getCoupon_deduct(),
                        shiftRoom.getPay_cash().getIntergral_deduct(), 1);
            }else {
                setShowData("现金", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getPay_wx() != null) {
                setShowData("微信", shiftRoom.getPay_wx().getTrade_num(),
                        shiftRoom.getPay_wx().getReal_pay_money(),
                        shiftRoom.getPay_wx().getReal_undo_money(),
                        shiftRoom.getPay_wx().getCoupon_deduct(),
                        shiftRoom.getPay_wx().getIntergral_deduct(), 1);
            }else {
                setShowData("微信", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getPay_aly() != null) {
                setShowData("支付宝", shiftRoom.getPay_aly().getTrade_num(),
                        shiftRoom.getPay_aly().getReal_pay_money(),
                        shiftRoom.getPay_aly().getReal_undo_money(),
                        shiftRoom.getPay_aly().getCoupon_deduct(),
                        shiftRoom.getPay_aly().getIntergral_deduct(), 1);
            }else {
                setShowData("支付宝", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getPay_qb() != null) {
                setShowData("钱包", shiftRoom.getPay_qb().getTrade_num(),
                        shiftRoom.getPay_qb().getReal_pay_money(),
                        shiftRoom.getPay_qb().getReal_undo_money(),
                        shiftRoom.getPay_qb().getCoupon_deduct(),
                        shiftRoom.getPay_qb().getIntergral_deduct(), 1);
            }else {
                setShowData("钱包", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getPay_unswipe() != null) {
                setShowData("刷卡撤销", shiftRoom.getPay_unswipe().getTrade_num(),
                        shiftRoom.getPay_unswipe().getReal_pay_money(),
                        shiftRoom.getPay_unswipe().getReal_undo_money(),
                        shiftRoom.getPay_unswipe().getCoupon_deduct(),
                        shiftRoom.getPay_unswipe().getIntergral_deduct(), 1);
            }else {
                setShowData("刷卡撤销", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getPay_unwx() != null) {
                setShowData("微信撤销", shiftRoom.getPay_unwx().getTrade_num(),
                        shiftRoom.getPay_unwx().getReal_pay_money(),
                        shiftRoom.getPay_unwx().getReal_undo_money(),
                        shiftRoom.getPay_unwx().getCoupon_deduct(),
                        shiftRoom.getPay_unwx().getIntergral_deduct(), 1);
            }else {
                setShowData("微信撤销", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getPay_unaly() != null) {
                setShowData("支付宝撤销", shiftRoom.getPay_unaly().getTrade_num(),
                        shiftRoom.getPay_unaly().getReal_pay_money(),
                        shiftRoom.getPay_unaly().getReal_undo_money(),
                        shiftRoom.getPay_unaly().getCoupon_deduct(),
                        shiftRoom.getPay_unaly().getIntergral_deduct(), 1);
            }else {
                setShowData("支付宝撤销", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getPay_unqb() != null) {
                setShowData("钱包撤销", shiftRoom.getPay_unqb().getTrade_num(),
                        shiftRoom.getPay_unqb().getReal_pay_money(),
                        shiftRoom.getPay_unqb().getReal_undo_money(),
                        shiftRoom.getPay_unqb().getCoupon_deduct(),
                        shiftRoom.getPay_unqb().getIntergral_deduct(), 1);
            }else {
                setShowData("钱包撤销", 0,
                        "0",
                        "0",
                        "0",
                        "0", 1);
            }

            if (shiftRoom.getTotal() != null) {
                setShowData("交易总计", shiftRoom.getTotal().getTrade_num(),
                        shiftRoom.getTotal().getReal_pay_money(),
                        shiftRoom.getTotal().getReal_undo_money(),
                        shiftRoom.getTotal().getCoupon_deduct(),
                        shiftRoom.getTotal().getIntergral_deduct(), 2);
            }else {
                setShowData("交易总计", 0,
                        "0",
                        "0",
                        "0",
                        "0", 2);
            }



        }else {
            ToastUtils.CustomShow(this, "当前数据为空");
//            finish();
        }
    }



    private void setShowData(String name, int trade_num, String real_pay_money, String real_undo_money, String coupon_deduct, String Intergral_deduct, int type){
        ShiftRoomShow shiftRoomShow = new ShiftRoomShow();
        shiftRoomShow.setName(name);
        shiftRoomShow.setTrade_num(trade_num);
        shiftRoomShow.setReal_pay_money(real_pay_money);
        shiftRoomShow.setCoupon_deduct(coupon_deduct);
        shiftRoomShow.setIntergral_deduct(Intergral_deduct);
        shiftRoomShow.setReal_undo_money(real_undo_money);

        if (type == 1){
            shiftRoomShow.setTrade_num_key("交易笔数: ");
            shiftRoomShow.setReal_pay_money_key("实收金额: ");
            shiftRoomShow.setCoupon_deduct_key("优惠券抵扣金额: ");
            shiftRoomShow.setIntergral_deduct_key("积分抵扣金额: ");
        }else if (type == 2){
            shiftRoomShow.setTrade_num_key("交易总笔数: ");
            shiftRoomShow.setReal_pay_money_key("实收总金额: ");
            shiftRoomShow.setReal_undo_money_key("交易撤销总金额: ");
            shiftRoomShow.setCoupon_deduct_key("优惠券抵扣总金额: ");
            shiftRoomShow.setIntergral_deduct_key("积分抵扣总金额: ");
        }
        shiftRoomShows.add(shiftRoomShow);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_printer:
                //保存当前时间
                if (type == Constants.PRINTER_SHIFT_ROOM) {
                    SPUtils.put(this, Constants.SHIFT_ROOM_TIME, StringUtils.getCurTime());
                    saveShiftRoom(shiftRoom, start_time, end_time);
                }

                Printer.printShiftRoom(shiftRoom, start_time, end_time, type);
                break;
            case R.id.id_get:
                getShiftRoom();
                break;
        }
    }


    /**
     * 根据时间查找当前是否有这条记录
     * @param start_time
     * @param end_time
     * @return
     */
    private boolean CheckRecord(long start_time, long end_time){
        List<ShiftRoomSave> data = DataSupport.order("id desc").find(ShiftRoomSave.class);
        for (int i=0; i<data.size(); i++){
            if (data.get(i).getStart_time() == start_time && data.get(i).getEnd_time() == end_time){
                return true;
            }
        }
        return false;
    }


    /**
     * 保存班接的数据
     * @param shiftRoom
     * @param start_time
     * @param end_time
     */
    private void saveShiftRoom(ShiftRoom shiftRoom, long start_time, long end_time){

        /**
         * 如果有这条记录那么不保存
         */
        if (CheckRecord(start_time, end_time)){
            return;
        }

        CommonFunc.ShiftRoomDataDelete();
        ShiftRoomSave shiftRoomSave = new ShiftRoomSave();
        shiftRoomSave.setStart_time(start_time);
        shiftRoomSave.setEnd_time(end_time);
        Gson gson = new Gson();
        String data = gson.toJson(shiftRoom);
        shiftRoomSave.setShiftRoom(data);
        shiftRoomSave.save();

    }


    /**
     * 检测起始时间是否大于结束时间
     * @return
     * @param startTime
     * @param endTime
     */
    private boolean isCheckTime(long startTime, long endTime){
        if (startTime > endTime){
            return true;
        }
        return false;
    }



    /**
     * 班接
     */
    private void getShiftRoom() {

        int sid = MyApplication.getInstance().getLoginData().getSid();

        final long startTime = StringUtils.getdate2TimeStamp1(tvStartTime.getText().toString());
        LogUtils.e("start_time=", tvStartTime.getText().toString());
        final long endTime = StringUtils.getdate2TimeStamp1(tvEndTime.getText().toString());
        LogUtils.e("end_time", tvEndTime.getText().toString());

        if (isCheckTime(startTime, endTime)){
            ToastUtils.CustomShow(this, "起始时间大于结束时间");
            return;
        }
        this.sbsAction.shift_room(this, sid, startTime, endTime, new ActionCallbackListener<ShiftRoom>() {
            @Override
            public void onSuccess(final ShiftRoom data) {
                LogUtils.e("onSucess" + data.toString());

                start_time = startTime;
                end_time = endTime;

                shiftRoom = data;
                shiftRoomShows.clear();
                getdata(data);
                adapter = new MyShiftRoom(ShiftRoomShowActivity.this, shiftRoomShows, R.layout.activity_shift_room_item);
                lv.setAdapter(adapter);
                if (type == Constants.PRINTER_SHIFT_ROOM){
                    saveShiftRoom(shiftRoom, start_time, end_time);
                }
                Printer.printShiftRoom(shiftRoom, start_time, end_time, type);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                ToastUtils.CustomShow(ShiftRoomShowActivity.this, errorEvent+message);
            }

            @Override
            public void onFailurTimeOut(String s, String error_msg) {

            }

            @Override
            public void onLogin() {
                AppManager.getAppManager().finishAllActivity();
                if (Config.OPERATOR_UI_BEFORE) {
                    CommonFunc.startAction(ShiftRoomShowActivity.this, OperatorLoginActivity.class, false);
                } else {
                    CommonFunc.startAction(ShiftRoomShowActivity.this, OperatorLoginActivity1.class, false);
                }

            }
        });
    }


}
