package com.tool.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
	
	/**
	 * ScrollView/ListView 嵌套 GridView
	 * 功能：使 GridView在内部显示全
	 * eg:
	 * 布局xml:
	 * <cn.myapp.mobile.owner.widget.MyGridView>
	 * </cn.myapp.mobile.owner.widget.MyGridView>
	 * 类文件
	 * MyGridView gv = (MyGridView) findViewById(R.id.mygridview);
	 * @author mac
	 *
	 */

public class MyGridView extends GridView {

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	
}
