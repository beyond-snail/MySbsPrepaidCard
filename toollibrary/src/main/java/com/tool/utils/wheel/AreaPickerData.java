package com.tool.utils.wheel;
import java.util.HashMap;
import java.util.Map;


import android.content.Context;


/**
 * 初始化数据  日期选择
 * @author wlb
 *
 */
public class AreaPickerData {
	
	@SuppressWarnings("unused")
	private Context mContext;
	
	/**
	 * 等同 年
	 */
	protected String[] mProvinceDatas = {"2011","2012","2013","2014","2015","2016","2017"
            ,"2018","2019","2020","2021","2022","2023"};
	/**
	 * 等同 月
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * 等同 日
	 */
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
	
	/**
	 * 当前选中 年
	 */
	protected String mCurrentProviceName;
	/**
	 * 当前选中 月
	 */
	protected String mCurrentCityName;
	/**
	 * 当前选中 日
	 */
	protected String mCurrentDistrictName ="";
	
	
	public AreaPickerData(Context context) {
		this.mContext = context;
		initProvinceDatas();
	}
	
    protected void initProvinceDatas(){
        String[] yue = {"01","02","03","04","05","06","07"
                ,"08","09","10","11","12"};
        String[] day_31 = {"01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30","31"};
        String[] day_30 = {"01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29","30"};;
        String[] day_29 = {"01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28","29"};;
        String[] day_28 = {"01","02","03","04","05","06","07","08","09","10",
                "11","12","13","14","15","16","17","18","19","20",
                "21","22","23","24","25","26","27","28"};
        for(int i=0;i<mProvinceDatas.length;i++){
            mCitisDatasMap.put(mProvinceDatas[i], yue);
            int year = Integer.parseInt(mProvinceDatas[i]);
            
            if(year%100!=0&&year%4==0){
                mDistrictDatasMap.put("02", day_29);   
            }else if(year%400==0){
                mDistrictDatasMap.put("02", day_29);   
            }else{
                mDistrictDatasMap.put("02", day_28);   
            }
            
        }
        
        for(int j=0;j<yue.length;j++){
            if(j==0||j==2||j==4||j==6||j==7||j==9||j==11){
                mDistrictDatasMap.put(yue[j], day_31);   
            }
            if(j==3||j==5||j==8||j==10){
                mDistrictDatasMap.put(yue[j], day_30);   
            }
        }
	}

}
