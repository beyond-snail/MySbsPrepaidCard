package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wucongpeng on 16/6/27.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mlayoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId){
        this.mContext = context;
        this.mDatas = datas;
        this.mlayoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mlayoutId, position);

        convert(mContext, holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void convert(Context context, ViewHolder holder, T t);
}
