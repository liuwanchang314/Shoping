package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Extension.AutoScrollViewPager;
import com.Extension.DataService;
import com.Extension.DownLoadImage;
import com.adapter.indexListviewAdapter;
import com.other.index_product_item;
import com.utils.StringManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class IndexActivity extends Activity implements OnPageChangeListener {
	private static final String[] strs = new String[] { "first", "second",
			"third", "fourth", "fifth" };
	private String[] mPicsUrl;
	private String[] mPicsUrlid;
	private ImageView mDotTips[];
	private ImageView mImageViews[];
	private ImageAdapter mAdapter;
	private AutoScrollViewPager viewPager;
	private ViewGroup group;
	private String TAG = this.getClass().getName();
	Handler handler;
	DataService client;//网络请求获取数据的工具类
	LinearLayout mLisView;
	HashMap<String, String> list = new HashMap<String, String>();

	String filenameTemp = Environment.getExternalStorageDirectory()
			+ "/HtFinancing/cache/newsImg/";
	boolean ft = true;
	private InputMethodManager inputManager;
	private EditText edittext;
	
	private RadioGroup mRadiogroup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initradiogroup();
		edittext=(EditText) findViewById(R.id.fanslist_txt_search);
		initinputmanager(edittext);
		mLisView =  (LinearLayout) findViewById(R.id.index_product_list);
		//获取头部布局
		View view = LayoutInflater.from(this).inflate(
				R.layout.activity_carousel, null);
		//获取尾部布局
		final View view1 = LayoutInflater.from(this).inflate(
				R.layout.activity_index_button, null);
		
		group = (ViewGroup) view.findViewById(R.id.viewGroup);
		viewPager = (AutoScrollViewPager) view.findViewById(R.id.pager_id);
		mLisView.addView(view);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				String data = "";
				JSONObject jsonObject;
				try {
					if (msg.arg1 == 0) {
						jsonObject = new JSONObject(msg.obj.toString());
						data = jsonObject.getString("status");
						if (data.equals("1")) {
							jsonObject = new JSONObject(msg.obj.toString());

							JSONArray numberList = jsonObject
									.getJSONArray("data");
							mPicsUrl = new String[numberList.length()];
							mPicsUrlid = new String[numberList.length()];
							for (int i = 0; i < numberList.length(); i++) {
								String urlString = numberList
										.getJSONObject(i)
										.getString("img")
										.substring(
												2,
												numberList.getJSONObject(i)
														.getString("img")
														.length());
								mPicsUrl[i] = numberList.getJSONObject(i)
										.getString("url") + urlString;
								// mPicsUrlid[i] = numberList.getJSONObject(i)
								// .getString("Newsid");
							}
							init();
							initPager();
							
							list.clear();
							list.put("type", "goods");
							list.put("part", "index_goods_cat");
							client = new DataService(handler, 1, list);
							client.start();
						}
					}else if(msg.arg1 == 1){
						jsonObject = new JSONObject(msg.obj.toString());
						data = jsonObject.getString("status");
						if (data.equals("1")) {
							data = jsonObject.getString("data");	
							
							Log.e(TAG, data);
							//String ImgTop = jsonObject.getString("class_info");
							//JSONArray jsonArray = jsonObject.getJSONArray("class_goods");
							List<Map<String, String>> list = StringManager.getListMapByJson(data);
							Map<String, String> map = list.get(0);
							Iterator<Entry<String, String>> it = map.entrySet().iterator();
							while(it.hasNext()){
								Entry<String, String> en = it.next();
								Map<String, String> map1 = StringManager.getListMapByJson(en.getValue()).get(0);
								index_product_item  product_item = new index_product_item(IndexActivity.this);
								product_item.setDate(map1);
								mLisView.addView(product_item);
							}
							
							
							mLisView.addView(view1);
						}
					}else {
						jsonObject = new JSONObject(msg.obj.toString());
						data = jsonObject.getString("status");
						if (data.equals("1")) {
							data = jsonObject.getString("data");
							//String ImgTop = jsonObject.getString("class_info");
							//JSONArray jsonArray = jsonObject.getJSONArray("class_goods");
						}
					}
					
//					mLisView.addView(view1);
				} catch (JSONException e) {
					e.printStackTrace();
					// Toast.makeText(getApplicationContext(),
					// e.getMessage().toString(),
					// Toast.LENGTH_SHORT).show();
					// onLoad();
					// tView.setText("�����ȡ���");
					// mListView.setEmptyView(tView);

				}
			}
		};
		//这里是添加post接口参数
		list.put("type", "adv");
		list.put("part", "index_slide");
		//调用方法，开始进行网络请求
		client = new DataService(handler, 0, list);
		client.start();

//		mLisView.setAdapter(new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, strs));

		

	}
	
	

	private void initradiogroup() {
		// TODO Auto-generated method stub
		mRadiogroup=(RadioGroup) findViewById(R.id.index_tab_rg_menu);
		mRadiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.index_tab_rb_1://全部商品
					Toast.makeText(IndexActivity.this,"全部商品",Toast.LENGTH_SHORT).show();
					startActivity(new Intent(IndexActivity.this,AllProductActivity.class));
					break;
				case R.id.index_tab_rb_2://产品分类
					Toast.makeText(IndexActivity.this,"产品分类",Toast.LENGTH_SHORT).show();
					startActivity(new Intent(IndexActivity.this,ProductClassNumberActivity.class));
					break;
				case R.id.index_tab_rb_3://公司公告
					Toast.makeText(IndexActivity.this,"公司公告",Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(IndexActivity.this,CompanyNoticeActivity.class);
					startActivity(intent);
					break;
				case R.id.index_tab_rb_4://下架公告
					Toast.makeText(IndexActivity.this,"下架公告",Toast.LENGTH_SHORT).show();
					startActivity(new Intent(IndexActivity.this,SoldOutActivity.class));
					break;
				default:
					break;
				}
			}
		});
	}




	private void init() {

		mDotTips = new ImageView[mPicsUrl.length];
		int length = mDotTips.length;
		group.removeAllViews();
		for (int i = 0; i < length; i++) {
			ImageView image = new ImageView(IndexActivity.this);

			image.setLayoutParams(new LayoutParams(20, 20));
			mDotTips[i] = image;

			if (i == 0) {
				mDotTips[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				mDotTips[i]
						.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			params.rightMargin = 10;

			group.addView(image, params);

		}

		mImageViews = new ImageView[length];
		for (int i = 0; i < length; i++) {
			ImageView img = new ImageView(IndexActivity.this);
//			Bitmap bitmap = DownLoadImage.GetLocalOrNetBitmap(mPicsUrl[i]);
//			img.setImageBitmap(bitmap);
			DownLoadImage.GetLocalOrNetBitmap(IndexActivity.this, img, mPicsUrl[i]);
			// img.setTag(mPicsUrlid[i]);
			img.setScaleType(ScaleType.FIT_XY);
			mImageViews[i] = img;

			mImageViews[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO �Զ���ɵķ������
					String aString = v.getTag().toString();
					// Intent intent = new Intent(NewsActivity.this,
					// NewPageActivity.class);
					// intent.putExtra("newsid", aString);
					// intent.putExtra("newtitle", t.getText().toString());
					// startActivity(intent);
				}
			});
		}

	}

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-7 上午9:59:28
	 * 初始化软键盘
	 */  
	public void initinputmanager(EditText edittext){
		inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(edittext, 0);

	}
	private void initPager() {
		mAdapter = new ImageAdapter(this, mImageViews);
		viewPager.setOnPageChangeListener(this);
		viewPager.setPageChangeListener(new NotifyPageChanged() {

			@Override
			public void notifyChange(int pos) {
				setDotBackground(pos);
			}
		});

		viewPager.setAdapter(mAdapter);
		viewPager.setPageCount(mImageViews == null ? 0 : mImageViews.length);

		viewPager.startAutoScroll();
	}

	protected static class ImageAdapter extends PagerAdapter {
		ImageView[] mImaes;

		ImageAdapter(IndexActivity newsActivity, ImageView[] img) {
			this.mImaes = img;
		}

		@Override
		public int getCount() {

			// return mImaes.length + 1;
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return (arg0 == arg1);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {

			((ViewPager) container)
					.removeView(mImaes[position % mImaes.length]);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView img = mImaes[position % mImaes.length];
			((ViewPager) container).removeView(img);
			((ViewPager) container).addView(img);
			return img;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		Log.d("luke", "onPageScrollStateChanged");
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		Log.d("luke", "onPageScrolled");
	}

	@Override
	public void onPageSelected(int arg0) {
		setDotBackground(arg0 % mImageViews.length);
	}

	private void setDotBackground(int pos) {
		int length = mDotTips.length;
		int mPos = pos % length;

		for (int i = 0; i < length; i++) {
			if (i == mPos) {
				mDotTips[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				mDotTips[i]
						.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

	public interface NotifyPageChanged {
		public void notifyChange(int pos);
	}
}
