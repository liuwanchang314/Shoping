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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.alljf.jf.R;



/**
 * 
 * @author zhy
 * 
 */
public class CitiesActivity extends Activity implements OnWheelChangedListener {
	/**
	 * ��ȫ���ʡ�������Ϣ��json�ĸ�ʽ���棬������ɺ�ֵΪnull
	 */
	private JSONObject mJsonObj;
	/**
	 * ʡ��WheelView�ؼ�
	 */
	private WheelView mProvince;
	/**
	 * �е�WheelView�ؼ�
	 */
	private WheelView mCity;
	/**
	 * ���WheelView�ؼ�
	 */
	private WheelView mArea;

	/**
	 * ����ʡ
	 */
	private String[] mProvinceDatas;
	List<ProvienceBean> list = new ArrayList<ProvienceBean>();
	/**
	 * key - ʡ value - ��s
	 */
	private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - �� values - ��s
	 */
	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	/**
	 * ��ǰʡ�����
	 */
	private String mCurrentProviceName;
	/**
	 * ��ǰ�е����
	 */
	private String mCurrentCityName;
	/**
	 * ��ǰ������
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
				Log.i("ʡ����", Pname);
				Log.i("������", Cname);
				Log.i("������", Dname);
				Log.i("ʡid��", Pid);
				Log.i("ʡid��", Cid);
				Log.i("ʡid��", Did);
				//�������ֵ�ش�
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
	 * ��ݵ�ǰ���У�������WheelView����Ϣ
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
	 * ��ݵ�ǰ��ʡ��������WheelView����Ϣ
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
	 * �������Json������ɺ��ͷ�Json������ڴ�
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
				// ����city��ʱ�򣬷�����һ������
				JSONArray arraycity = objs.getJSONArray("city");
				// ��ɴ洢���еļ���
				List<CityBean> listcity=new ArrayList<CityBean>();
				String[] mCitiesDatas = new String[arraycity.length()];
				// �����ڲ�������obj
				for (int j = 0; j < arraycity.length(); j++) {
					JSONObject objcity = arraycity.getJSONObject(j);
					// ��ɳ���ʵ�����
					CityBean citybean = new CityBean();
					citybean.setId(objcity.getString("id"));
					citybean.setName(objcity.getString("name"));
					mCitiesDatas[j] = objcity.getString("name");
					// ��������district��ʱ�򣬷���������һ������
					JSONArray arraydistrict = objcity.getJSONArray("district");
					// ��Ҫ��ɴ洢����ļ���
					List<DirectBean> listdistrict=new ArrayList<DirectBean>();
//					String[] mAreasDatas = new String[arraydistrict.length()];
					for (int h = 0; h < arraydistrict.length(); h++) {
						// �����ڲ���ʵ����
						DirectBean directbean = new DirectBean();
						JSONObject objdirect = arraydistrict.getJSONObject(h);
						directbean.setId(objdirect.getString("id"));
						directbean.setName(objdirect.getString("name"));
						// �����������ӵ�������
						listdistrict.add(directbean);
//						mAreasDatas[h] = objdirect.getString("name");
					}
					// �����������������ԣ�Ҳ���Ǽ������õ��ж���
					citybean.setList(listdistrict);
//					mAreaDatasMap.put(citybean.getName(), mAreasDatas);
					// ���ҽ�����ӵ�������
					listcity.add(citybean);
				}
				// ��ʡ�������еļ������õ�ʡ�ļ���������
				bean.setList(listcity);
				mCitisDatasMap.put(objs.getString("name"), mCitiesDatas);
				// �������ʡ��ӵ����ʡ�ļ�����
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
	 * ��assert�ļ����ж�ȡʡ�����json�ļ���Ȼ��ת��Ϊjson����
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
	 * change�¼��Ĵ���
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
