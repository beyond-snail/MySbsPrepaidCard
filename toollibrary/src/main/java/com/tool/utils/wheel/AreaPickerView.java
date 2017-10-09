package com.tool.utils.wheel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tool.utils.wheel.adapters.ArrayWheelAdapter;
import com.toollibrary.R;


/**
 * 自定义一个选择日期的滚轮
 * @author wlb
 *
 */
public class AreaPickerView extends LinearLayout implements OnWheelChangedListener {

	@SuppressWarnings("unused")
	private static final String TAG = "AreaPickerView";
	private Context mContext;
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	
	private AreaPickerData data;
	private boolean isdate = false;
	
	public AreaPickerView(Context context) {
		this(context, null, null, null);
	}
	
	/**
	 * ���캯��
	 * @param context 
	 * @param defaultSelectedProvince Ĭ��ѡ��ʡ��
	 * @param defaultSelectedCity Ĭ��ѡ�г���
	 * @param defaultSelectedDistrict  Ĭ��ѡ����
	 */
	@SuppressLint("InflateParams")
	public AreaPickerView(Context context, String defaultSelectedProvince,
			String defaultSelectedCity, String defaultSelectedDistrict) {
		super(context);
		this.mContext = context;
		
		View view = LayoutInflater.from(mContext).inflate(R.layout.areapicker2,
				null);
		addView(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		initViews();
		initListener();
		initData(defaultSelectedProvince, defaultSelectedCity, defaultSelectedDistrict);
	}
	
	@SuppressLint("InflateParams")
	public AreaPickerView(Context context, String defaultSelectedProvince,
            String defaultSelectedCity, String defaultSelectedDistrict,boolean isdate) {
        super(context);
        this.mContext = context;
        this.isdate = isdate;
        
        View view = LayoutInflater.from(mContext).inflate(R.layout.areapicker2,
                null);
        addView(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        initViews();
        initListener();
        initData(defaultSelectedProvince, defaultSelectedCity, defaultSelectedDistrict);
    }

	private void initViews() {
		mViewProvince = (WheelView) findViewById(R.id.id_province);
		mViewCity = (WheelView) findViewById(R.id.id_city);
		mViewDistrict = (WheelView) findViewById(R.id.id_district);
		
		if(isdate){//只有年月
		    mViewDistrict.setVisibility(View.GONE);
		}
	}

	private void initListener() {
    	// ���change�¼�
    	mViewProvince.addChangingListener(this);
    	// ���change�¼�
    	mViewCity.addChangingListener(this);
    	// ���change�¼�
    	mViewDistrict.addChangingListener(this);
    }

	private void initData(String defaultSelectedProvince,
			String defaultSelectedCity, String defaultSelectedDistrict) {
		
		data = new AreaPickerData(mContext);
		data.mCurrentProviceName = defaultSelectedProvince;// ����Ĭ��ѡ��ʡ��
		data.mCurrentCityName = defaultSelectedCity;// ����Ĭ��ѡ�г���
		data.mCurrentDistrictName = defaultSelectedDistrict;// ����Ĭ��ѡ����
		
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mContext, data.mProvinceDatas));
		// ����Ĭ��ѡ��ʡ��
		if (defaultSelectedProvince != null
				&& defaultSelectedProvince.trim().length() > 0) {
			int index = findIndex(data.mProvinceDatas, defaultSelectedProvince);
			if (index > -1) {
				mViewProvince.setCurrentItem(index);
			}
		}
		// ���ÿɼ���Ŀ����
		mViewProvince.setVisibleItems(3);
		mViewCity.setVisibleItems(3);
		mViewDistrict.setVisibleItems(3);
		updateCities();
	}
	
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			data.mCurrentDistrictName = data.mDistrictDatasMap.get(data.mCurrentCityName)[newValue];
		
		}
	}
	
	/**
	 * ���ݵ�ǰ���У�������WheelView����Ϣ
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		data.mCurrentCityName = data.mCitisDatasMap.get(data.mCurrentProviceName)[pCurrent];
		String[] areas = data.mDistrictDatasMap.get(data.mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		
		@SuppressWarnings("rawtypes")
		ArrayWheelAdapter a = new ArrayWheelAdapter<String>(mContext, areas);
//		a.setItemResource(R.layout.textview_line);
//		a.setItemTextResource(R.id.text);
		mViewDistrict.setViewAdapter(a);
		// ����Ĭ��ѡ����
		if (data.mCurrentDistrictName != null
				&& data.mCurrentDistrictName.trim().length() > 0) {
			int index = findIndex(areas, data.mCurrentDistrictName);
			if (index > -1) {
				mViewDistrict.setCurrentItem(index);
			} else {
				mViewDistrict.setCurrentItem(0);
			}
		}
		int dCurrent = mViewDistrict.getCurrentItem();
		data.mCurrentDistrictName = data.mDistrictDatasMap.get(data.mCurrentCityName)[dCurrent];
	
	}

	/**
	 * ���ݵ�ǰ��ʡ��������WheelView����Ϣ
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		data.mCurrentProviceName = data.mProvinceDatas[pCurrent];
		String[] cities = data.mCitisDatasMap.get(data.mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(mContext, cities));
		// ����Ĭ��ѡ�г���
		if (data.mCurrentCityName != null
				&& data.mCurrentCityName.trim().length() > 0) {
			int index = findIndex(cities, data.mCurrentCityName);
			if (index > -1) {
				mViewCity.setCurrentItem(index);
			} else {
				mViewCity.setCurrentItem(0);
			}
		}
		updateAreas();
	}
	
	/**
	 * ������arraysѰ���ַ���s���ֵĵ�һ��λ���±꣬�Ҳ�������-1
	 * @param arrays
	 * @param s
	 * @return
	 */
	private int findIndex(String[] arrays, String s) {
		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * ��ǰʡ������
	 * @return
	 */
	public String getCurrentProviceName() {
		return data.mCurrentProviceName;
	}
	
	/**
	 * ��ǰ�е�����
	 * @return
	 */
	public String getCurrentCityName() {
		return data.mCurrentCityName;
	}
	
	/**
	 * ��ǰ��������
	 * @return
	 */
	public String getCurrentDistrictName() {
		return data.mCurrentDistrictName;
	}
	
}
