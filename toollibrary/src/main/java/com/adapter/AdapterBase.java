package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AdapterBase<T> extends BaseAdapter {
	
	protected List<T> mData;
	protected Context mContext;
	protected LayoutInflater mInflater;
	
	public AdapterBase(final List<T> data, final Context context ){
		this.mData  = data;
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
	}
	
	/* 
	 * <p>Title: getCount</p>
	 * <p>Description: </p>
	 * @return data count 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return mData  == null ? 0: mData.size();
	}
	
	/* 
	 * <p>Title: getItem</p>
	 * <p>Description: </p>
	 * @param position
	 * @return item at position 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return mData  == null ? null: mData.get(position);
	}
	
	/* 
	 * <p>Title: getItemId</p>
	 * <p>Description: </p>
	 * @param position
	 * @return
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	/* 
	 * <p>Title: getView</p>
	 * <p>Description: </p>
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);

}
