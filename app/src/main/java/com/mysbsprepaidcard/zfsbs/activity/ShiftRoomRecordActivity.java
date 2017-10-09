package com.mysbsprepaidcard.zfsbs.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.adapter.MyShiftRoomRecordsAdapter;
import com.mysbsprepaidcard.zfsbs.common.CommonFunc;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoom;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoomSave;
import com.tool.utils.activityManager.AppManager;
import com.tool.utils.utils.LogUtils;
import com.tool.utils.utils.StringUtils;
import com.tool.utils.utils.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.List;


public class ShiftRoomRecordActivity extends BaseActivity {


    private List<ShiftRoomSave> allData;
    private ListView lv;
    private TextView showNo;
    private MyShiftRoomRecordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_shift_room_record);
        AppManager.getAppManager().addActivity(this);
        initTitle("班结记录");
        initView();
        addListener();
        initData();
        initAdapter();
    }


    private void initView() {
        lv = (ListView) findViewById(R.id.show_shift_record);
        showNo = (TextView) findViewById(R.id.show_shift_record_no);


    }

    private void initData() {
        LogUtils.e("time1:" + StringUtils.getCurTimeMills());
        // 查询前100条数据
        allData = DataSupport.order("id desc").limit(50).find(ShiftRoomSave.class);
        if (allData.size() <= 0) {

            ToastUtils.CustomShow(ShiftRoomRecordActivity.this, "没有班结记录");
            showNo.setVisibility(View.VISIBLE);
            lv.setVisibility(View.GONE);


            return;
        }
        LogUtils.e("time2:" + StringUtils.getCurTimeMills());
    }

    private void initAdapter() {

        adapter = new MyShiftRoomRecordsAdapter(ShiftRoomRecordActivity.this, allData, R.layout.activity_shiftroom_record_item);
        lv.setAdapter(adapter);


    }

    private void addListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Bundle bundle = new Bundle();
                Gson gson = new Gson();
                ShiftRoom data = gson.fromJson(allData.get(position).getShiftRoom(), ShiftRoom.class);
                bundle.putSerializable("ShiftRoom", data);
                bundle.putLong("start_time", allData.get(position).getStart_time());
                bundle.putLong("end_time", allData.get(position).getEnd_time());
                CommonFunc.startAction(ShiftRoomRecordActivity.this, ShiftRoomShowitemActivity.class, bundle, false);
            }

        });
    }


}
