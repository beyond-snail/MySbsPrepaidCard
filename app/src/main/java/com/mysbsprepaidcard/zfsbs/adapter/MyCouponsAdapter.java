package com.mysbsprepaidcard.zfsbs.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.mysbsprepaidcard.R;
import com.mysbsprepaidcard.zfsbs.model.Coupons;
import com.tool.utils.utils.StringUtils;

import java.util.List;


public class MyCouponsAdapter extends CommonAdapter<Coupons> {

	private static final String TAG = "MyCouponsAdapter";
	private List<Coupons> datas;

	@SuppressLint("UseSparseArrays")
	public MyCouponsAdapter(Context context, List<Coupons> datas, int layoutId) {
		super(context, datas, layoutId);
		this.datas = datas;
	}

	@Override
	public void convert(final Context context, final ViewHolder holder, final Coupons coupons) {
		holder.setText(R.id.id_coupon_amount, StringUtils.formatIntMoney(coupons.getMoney()) + "元券").setText(
				R.id.id_coupon_name, coupons.getName() + " (" + (coupons.isCanMultiChoose() ? "可多选" : "不可多选") + ")");
		final CheckBox cBox = (CheckBox) (holder.getView(R.id.id_isUsed));
		if (cBox != null) {
			cBox.setChecked(coupons.isChecked());

			cBox.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// 如果当前优惠券不可复选 ，那么 设置当前优惠券 选中 其他优惠券 不可选
					if (!coupons.isCanMultiChoose() && !coupons.isChecked()) {
						coupons.setChecked(cBox.isChecked());
						CouponsResetCheckUI(coupons);
//						ToastUtils.CustomShow(context, "该券不可与其他共用");
					} else if (!coupons.isCanMultiChoose() && coupons.isChecked()) {
						coupons.setChecked(cBox.isChecked());
						notifyDataSetChanged();
					} else if (coupons.isCanMultiChoose() && !coupons.isChecked()) {
						if (!CheckAllIsChecked(context)) {
							coupons.setChecked(cBox.isChecked());
							notifyDataSetChanged();
						} else {
							coupons.setChecked(cBox.isChecked());
							CouponsResetCheckUI(coupons);
						}
					} else if (coupons.isCanMultiChoose() && coupons.isChecked()) {
						coupons.setChecked(cBox.isChecked());
						notifyDataSetChanged();
					}

				}
			});
		}
	}
	

	protected boolean CheckAllIsChecked(Context context) {
		for (int i = 0; i < datas.size(); i++) {
			if (!datas.get(i).isCanMultiChoose() && datas.get(i).isChecked()) {
				CouponsResetCheckUI(datas.get(i));
//				ToastUtils.CustomShow(context, "该券不可与其他共用");
				return true;
			}
		}
		return false;
	}

	// 更新UI
	protected void CouponsResetCheckUI(Coupons coupons) {
		for (int i = 0; i < datas.size(); i++) {
			if (datas.get(i) != coupons) {
				datas.get(i).setChecked(false);
			}
		}
		notifyDataSetChanged();
	}


}
