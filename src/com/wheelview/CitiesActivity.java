package com.wheelview;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author zhy
 * 
 */
public class CitiesActivity extends Activity implements OnWheelChangedListener {
	/**
	 * 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
	 */
	private JSONObject mJsonObj;
	/**
	 * 省的WheelView控件
	 */
	private WheelView mProvince;
	/**
	 * 市的WheelView控件
	 */
	private WheelView mCity;
	/**
	 * 区的WheelView控件
	 */
	private WheelView mArea;

	/**
	 * 所有省
	 */
	private String[] mProvinceDatas;
	List<ProvienceBean> list = new ArrayList<ProvienceBean>();
	/**
	 * key - 省 value - 市s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区s
	 */
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	/**
	 * 当前省的名称
	 */
	private String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	private String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	private String mCurrentAreaName = "";
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.citys);

		initJsonData();

		mProvince = (WheelView) findViewById(R.id.id_province);
		mCity = (WheelView) findViewById(R.id.id_city);
		mArea = (WheelView) findViewById(R.id.id_area);
		btn = (Button) findViewById(R.id.sure);
		initDatas();

		mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this,
				mProvinceDatas));
		mProvince.addChangingListener(this);
		mCity.addChangingListener(this);
		mArea.addChangingListener(this);

		mProvince.setVisibleItems(8);
		mCity.setVisibleItems(8);
		mArea.setVisibleItems(8);
		updateCities();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String provinceId = list.get(mProvince.getCurrentItem()).getId() ;
				Log.v("123", "provinceId "+ provinceId);
				if(mCitisDatasMap.get(mCurrentProviceName).length > 0){
					List<CityBean> listCity = list.get(mProvince.getCurrentItem()).getList();
					String cityId = listCity.get(mCity.getCurrentItem()).getId();
					Log.v("123", "cityId "+ cityId);
				}
				
				if(mCitisDatasMap.get(mCurrentProviceName).length > 0){
					List<DirectBean> lisrArea = list.get(mProvince.getCurrentItem()).getList().get(mCity.getCurrentItem()).getList();
					if(lisrArea.size() > 0){
						String areaId = lisrArea.get(mArea.getCurrentItem()).getId();
						Log.v("123", "areaId "+ areaId);
					}
					
				}
				String Pname=list.get(mProvince.getCurrentItem()).getName();
				String Cname=list.get(mProvince.getCurrentItem()).getList().get(mCity.getCurrentItem()).getName();
				String Dname=list.get(mProvince.getCurrentItem()).getList().get(mCity.getCurrentItem()).getList().get(mArea.getCurrentItem()).getName();
				String Pid=list.get(mProvince.getCurrentItem()).getId();
				String Cid=list.get(mProvince.getCurrentItem()).getList().get(mCity.getCurrentItem()).getId();
				String Did=list.get(mProvince.getCurrentItem()).getList().get(mCity.getCurrentItem()).getList().get(mArea.getCurrentItem()).getId();
				Log.i("省名是", Pname);
				Log.i("市名是", Cname);
				Log.i("县名是", Dname);
				Log.i("省id是", Pid);
				Log.i("省id是", Cid);
				Log.i("省id是", Did);
				//将这六个值回传
				Intent intent=new Intent();
				intent.putExtra("pname", Pname);
				intent.putExtra("cname", Cname);
				intent.putExtra("dname", Dname);
				intent.putExtra("pid", Pid);
				intent.putExtra("cid", Cid);
				intent.putExtra("did", Did);
				CitiesActivity.this.setResult(2, intent);
				CitiesActivity.this.finish();
			}
		});

	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mCity.getCurrentItem();
		String[] areas = new String[]{""};
		if(list.get(mProvince.getCurrentItem()).getList().size() > 0){
			List<DirectBean> lisrArea = list.get(mProvince.getCurrentItem()).getList().get(pCurrent).getList();
			String[] mAreasDatas = new String[lisrArea.size()];
			if(lisrArea.size() > 0){
//				mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
//				String[] areas = mAreaDatasMap.get(mCurrentCityName);
				for(int i = 0; i < lisrArea.size(); i++){
					mAreasDatas[i] = lisrArea.get(i).getName();
				}
//				if (areas == null) {
//					areas = new String[] { "" };
//				}
				mArea.setViewAdapter(new ArrayWheelAdapter<String>(this, mAreasDatas));
				mArea.setCurrentItem(0);
			}else{
				mArea.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
				mArea.setCurrentItem(0);
			}
		}else{
			mArea.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
			mArea.setCurrentItem(0);
		}
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mCity.setCurrentItem(0);
		updateAreas();
	}

	/**
	 * 解析整个Json对象，完成后释放Json对象的内存
	 */
	private void initDatas() {
		try {
			JSONArray array = mJsonObj.getJSONArray("data");
			mProvinceDatas = new String[array.length()];
			for (int i = 0; i < array.length(); i++) {
				ProvienceBean bean = new ProvienceBean();
				JSONObject objs = array.getJSONObject(i);
				bean.setId(objs.getString("id"));
				bean.setName(objs.getString("name"));
				// 当到city的时候，发现是一个集合
				JSONArray arraycity = objs.getJSONArray("city");
				// 生成存储城市的集合
				List<CityBean> listcity=new ArrayList<CityBean>();
				String[] mCitiesDatas = new String[arraycity.length()];
				// 集合内部现在是obj
				for (int j = 0; j < arraycity.length(); j++) {
					JSONObject objcity = arraycity.getJSONObject(j);
					// 生成城市实体对象
					CityBean citybean = new CityBean();
					citybean.setId(objcity.getString("id"));
					citybean.setName(objcity.getString("name"));
					mCitiesDatas[j] = objcity.getString("name");
					// 当解析到district的时候，发现是又是一个集合
					JSONArray arraydistrict = objcity.getJSONArray("district");
					// 需要生成存储县区的集合
					List<DirectBean> listdistrict=new ArrayList<DirectBean>();
//					String[] mAreasDatas = new String[arraydistrict.length()];
					for (int h = 0; h < arraydistrict.length(); h++) {
						// 发现内部是实体类
						DirectBean directbean = new DirectBean();
						JSONObject objdirect = arraydistrict.getJSONObject(h);
						directbean.setId(objdirect.getString("id"));
						directbean.setName(objdirect.getString("name"));
						// 将县区对象添加到集合中
						listdistrict.add(directbean);
//						mAreasDatas[h] = objdirect.getString("name");
					}
					// 将市所属的县区属性，也就是集合设置到市对象
					citybean.setList(listdistrict);
//					mAreaDatasMap.put(citybean.getName(), mAreasDatas);
					// 并且将市添加到集合中
					listcity.add(citybean);
				}
				// 将省所属的市的集合设置到省的集合属性中
				bean.setList(listcity);
				mCitisDatasMap.put(objs.getString("name"), mCitiesDatas);
				// 最后将所有省添加到存放省的集合中
				list.add(bean);
				mJsonObj=null;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			mProvinceDatas[i] = list.get(i).getName();
		}
	}

	/**
	 * 从assert文件夹中读取省市区的json文件，然后转化为json对象
	 */
	private void initJsonData() {
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = getAssets().open("area_new.txt");
			int len = -1;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				sb.append(new String(buf, 0, len, "gbk"));
			}
			is.close();
			mJsonObj = new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * change事件的处理
	 */
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mProvince) {
			updateCities();
		} else if (wheel == mCity) {
			updateAreas();
		} else if (wheel == mArea) {
//			mCurrentAreaName = mAreaDatasMap.get(mCurrentCityName)[newValue];
		}
	}

	public void showChoose(View view) {
		Toast.makeText(this,
				mCurrentProviceName + mCurrentCityName + mCurrentAreaName, 1)
				.show();
	}
}
