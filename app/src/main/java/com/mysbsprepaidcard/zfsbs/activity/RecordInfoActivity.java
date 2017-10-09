package com.mysbsprepaidcard.zfsbs.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.myokhttp.util.LogUtils;
import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.config.Constants;
import com.mysbsprepaidcard.zfsbs.model.SbsPrinterData;
import com.mysbsprepaidcard.zfsbs.model.TransUploadResponse;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RecordInfoActivity extends BaseActivity implements View.OnClickListener {

    private List<SbsPrinterData> allData;
    private ListView lv;
    private TextView showNo;
    private TextView tj;
    private MyRecordsAdapter adapter;
    private int index = 0;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_recordprinter);
        AppManager.getAppManager().addActivity(this);
        initTitle("交易记录");
        initView();
        addListener();
//		testData();


        initData();
        initAdapter();


    }

    private void initView() {
        lv = (ListView) findViewById(R.id.show_record);
        showNo = (TextView) findViewById(R.id.show_record_no);

//        tj = (TextView) findViewById(R.id.id_tj);
//        tj.setOnClickListener(this);
    }

    private void initData() {
        LogUtils.e("time1:" + StringUtils.getCurTimeMills());
        // 查询前100条数据
        allData = DataSupport.order("id desc").limit(200).find(SbsPrinterData.class);
        if (allData.size() <= 0) {

            ToastUtils.CustomShow(RecordInfoActivity.this, "没有交易记录");
            showNo.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);


            return;
        }
        LogUtils.e("time2:" + StringUtils.getCurTimeMills());
    }

    private void initAdapter() {

        adapter = new MyRecordsAdapter(RecordInfoActivity.this, allData, R.layout.activity_record_item);
        lv.setAdapter(adapter);


    }

    private void addListener() {
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("Record", allData.get(position));
                index = position;
                CommonFunc.startResultAction(RecordInfoActivity.this, RecordItemInfoActivity.class, bundle, REQUEST_CODE);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            boolean uploadFlag = data.getBooleanExtra("uploadFlag", false);
            boolean isRefund = data.getBooleanExtra("isRefund", false);
            boolean isRefundUpload = data.getBooleanExtra("isRefundUpload", false);
            int backAmt = data.getIntExtra("backAmt", 0);
            String refund_order_no = data.getStringExtra("refund_order_no");
            String flowNo = data.getStringExtra("flowNo");
            int total_point = data.getIntExtra("total_points", 0);
            TransUploadResponse response = (TransUploadResponse) data.getSerializableExtra("printer");
            if (response != null) {
                allData.get(index).setPoint_url(response.getPoint_url());
                allData.get(index).setPoint(response.getPoint());
                allData.get(index).setPointCurrent(response.getPointCurrent());
                allData.get(index).setCoupon(response.getCoupon());
                allData.get(index).setTitle_url(response.getTitle_url());
                allData.get(index).setMoney(response.getMoney());
            }

            if (StringUtils.isEmpty(flowNo)){
                allData.get(index).setFlowNo(flowNo);
            }
            allData.get(index).setPointCurrent(total_point);

            if (StringUtils.isEmpty(refund_order_no)){
                allData.get(index).setRefund_order_no(refund_order_no);
            }

            allData.get(index).setBackAmt(backAmt);

            if (uploadFlag == true) {
                allData.get(index).setUploadFlag(false);
            }

            if (isRefund == true) {
                allData.get(index).setRefund(true);

            }

            if (isRefundUpload == true) {
                allData.get(index).setRefundUpload(true);
            }

            adapter.notifyDataSetChanged();
        }
    }


    @SuppressLint("NewApi")
    private void testData() {
        // List<PrinterData> list = new ArrayList<>();
        // LogUtils.e("before: "+new Date().getTime());
        // for (int i = 0; i < 120; i++) {
        // PrinterData printerDataTest = new PrinterData();
        // printerDataTest.setMerchantName(
        // (String) SPUtils.get(this, Constants.MERCHANT_NAME,
        // Constants.DEFALULT_MERCHANT_NAME));
        // printerDataTest.setTerminalId(android.os.Build.SERIAL);
        // printerDataTest.setOperatorNo("01");
        // printerDataTest.setDateTime(StringUtils.getCurTime());
        // printerDataTest.setAmount(StringUtils.formatIntMoney(i) + "元");
        // printerDataTest.setPayType(Constants.PAY_WAY_CASH);
        // list.add(printerDataTest);
        // }
        //// LogUtils.e("before: "+new Date().getTime());
        // PrinterData.saveAll(list);
        // LogUtils.e("after: "+new Date().getTime());
//		 DataSupport.deleteAll(PrinterData.class);
//		 DataSupport.deleteAll(TransUploadRequest.class);
//		 DataSupport.deleteAll(LoginApiResponse.class);

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.id_tj:
//                getData();
//
//                break;
//        }
    }

    private void getData() {

        int flotAmount = 0;
        int smAlyAmount = 0;
        int smWxAmount = 0;
        int undoAmount = 0;
        int cashAmount = 0;
        int totalAmount = 0;

        Date currDate = StringUtils.getDateFromString(StringUtils.getCurTime(), "yyyy-MM-dd");
        LogUtils.e(StringUtils.getDateFormate(currDate) + " " + currDate.getTime());
        for (int i = 0; i < allData.size(); i++) {
            Date recordDate = StringUtils.getDateFromString(allData.get(i).getDateTime(), "yyyy-MM-dd");
//            LogUtils.e("recordDate:"+StringUtils.getDateFormate(recordDate)+" "+recordDate.getTime());

            if (currDate.getTime() == recordDate.getTime()) {
                BigDecimal big = new BigDecimal(allData.get(i).getAmount());
                int amount = big.multiply(new BigDecimal(100)).intValue();
                if (allData.get(i).getPayType() == Constants.PAY_WAY_FLOT) {
                    flotAmount += amount;
                } else if (allData.get(i).getPayType() == Constants.PAY_WAY_ALY) {
                    smAlyAmount += amount;
                } else if (allData.get(i).getPayType() == Constants.PAY_WAY_WX) {
                    smWxAmount += amount;
                } else if (allData.get(i).getPayType() == Constants.PAY_WAY_UNDO) {
                    undoAmount += amount;
                } else if (allData.get(i).getPayType() == Constants.PAY_WAY_CASH) {
                    cashAmount += amount;
                }

            }

        }

        totalAmount = flotAmount + smAlyAmount + smWxAmount + cashAmount - undoAmount;

        Bundle data = new Bundle();
        data.putInt("flotAmount", flotAmount);
        data.putInt("smAlyAmount", smAlyAmount);
        data.putInt("smWxAmount", smWxAmount);
        data.putInt("undoAmount", undoAmount);
        data.putInt("cashAmount", cashAmount);
        data.putInt("totalAmount", totalAmount);

        CommonFunc.startAction(this, ShowInfoCurrentDayData.class, data, false);
    }
}
