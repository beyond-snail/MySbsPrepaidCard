package com.mysbsprepaidcard.zfsbs.adapter;

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

import android.content.Context;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.model.ShiftRoomSave;
import com.tool.utils.utils.StringUtils;

import java.util.List;


/**********************************************************
 * *
 * Created by wucongpeng on 2016/11/9.        *
 **********************************************************/


public class MyShiftRoomRecordsAdapter extends CommonAdapter<ShiftRoomSave> {

    private static final String TAG = "MyShiftRoomRecordsAdapter";
    private List<ShiftRoomSave> datas;

    public MyShiftRoomRecordsAdapter(Context context, List<ShiftRoomSave> datas, int layoutId){
        super(context, datas, layoutId);
        this.datas = datas;
    }

    @Override
    public void convert(Context context, ViewHolder holder, ShiftRoomSave shiftRoomSave) {
        holder.setText(R.id.id_starttime, StringUtils.timeStamp2Date1(shiftRoomSave.getStart_time() + ""))
                .setText(R.id.id_endTime, StringUtils.timeStamp2Date1(shiftRoomSave.getEnd_time() + ""));
    }


}
