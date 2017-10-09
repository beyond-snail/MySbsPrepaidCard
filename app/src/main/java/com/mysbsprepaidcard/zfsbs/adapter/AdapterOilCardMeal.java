package com.mysbsprepaidcard.zfsbs.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adapter.AdapterBase;
import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.model.RechargeAmount;
import com.tool.utils.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdapterOilCardMeal extends AdapterBase<RechargeAmount> {

    @SuppressWarnings("unused")
    private final static String TAG = "AdapterOilCardMeal";
    private List<RechargeAmount> data = new ArrayList<RechargeAmount>();
    @SuppressWarnings("unused")
    private Context context;

    public AdapterOilCardMeal(List<RechargeAmount> data, Context context) {
        super(data, context);
        this.context = context;
        this.data = data;
    }

    @SuppressLint({"InflateParams", "NewApi", "UseValueOf" })
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_oilcard_meal, null);

            holder.discount = (TextView) convertView.findViewById(R.id.oil_s_1);
            holder.money = (TextView) convertView.findViewById(R.id.oil_s_2);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.oil_l_1);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RechargeAmount vo = data.get(position);
        if (vo.getIsdefault() == 1) {
            holder.discount.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.money.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.ll.setBackground(mContext.getResources().getDrawable(R.drawable.common_radius_rect_red));
        } else {
            holder.discount.setTextColor(mContext.getResources().getColor(R.color.gray));
            holder.money.setTextColor(mContext.getResources().getColor(R.color.gray));
            holder.ll.setBackground(mContext.getResources().getDrawable(R.drawable.common_radius_rect_black));
        }
        //取整数

        holder.discount.setText("充"+StringUtils.formatIntMoney(vo.getReal_pay_money())+"元");
        holder.money.setText("得"+StringUtils.formatIntMoney(vo.getReal_get_money())+ "元");

        return convertView;
    }

    private static final class ViewHolder {

        LinearLayout ll;
        TextView discount;
        TextView money;
    }

}
