package com.alljf.jf.activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.Application.SysApplication;
import com.Extension.AutoScrollViewPager;
import com.Extension.DataService;
import com.Extension.DownLoadImage;
import com.alljf.jf.R;
import com.customview.RefreshableView;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.other.index_product_item;
import com.utils.StringManager;

public class IndexActivity extends Activity implements OnPageChangeListener ,RefreshableView.RefreshListener,OnClickListener{
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
	
	private LinearLayout indeLayout;
	/**
	 * 可下拉刷新的scrollview
	 * */
	private RefreshableView mRefreshableView;
	/**
	 * 进度条
	 */
	private SpotsDialog mdialog;
	/**
	 * 按钮
	 */
	private Button button1,button2,button3,button4;
	/**
	 * 底部布局
	 */
	private View view_bottom;
	/**
	 * 域名string[]
	 */
	private String[] mUrl;
	Handler handlerR = new Handler() {//刷新界面的线程
		public void handleMessage(Message message) {
			super.handleMessage(message);
			mRefreshableView.finishRefresh();
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		SysApplication.getInstance().addActivity(this);
		SpotsDialog.TAG=R.style.SpotsDialogDefault;
		mdialog=new SpotsDialog(IndexActivity.this);
		mdialog.setCanceledOnTouchOutside(true);
		mdialog.show();
		mRefreshableView = (RefreshableView) findViewById(R.id.refresh_root);
		mRefreshableView.setRefreshListener(this);
		edittext=(EditText)mRefreshableView.findViewById(R.id.fanslist_txt_search);
		button1=(Button) mRefreshableView.findViewById(R.id.index_tab_rb_1);
		button1.setOnClickListener(this);
		button2=(Button) mRefreshableView.findViewById(R.id.index_tab_rb_2);
		button2.setOnClickListener(this);
		button3=(Button) mRefreshableView.findViewById(R.id.index_tab_rb_3);
		button3.setOnClickListener(this);
		button4=(Button) mRefreshableView.findViewById(R.id.index_tab_rb_4);
		button4.setOnClickListener(this);
		initinputmanager(edittext);
		mLisView =  (LinearLayout)mRefreshableView.findViewById(R.id.index_product_list);
		//获取头部布局
		View view = LayoutInflater.from(this).inflate(
				R.layout.activity_carousel, null);
		//获取尾部布局
		view_bottom = LayoutInflater.from(this).inflate(
				R.layout.activity_index_button, null);
		Button buttons=(Button) view_bottom.findViewById(R.id.index_btn);
		buttons.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(IndexActivity.this,AllProductActivity.class);
				startActivity(intent);
			}
		});
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
						mdialog.dismiss();
						jsonObject = new JSONObject(msg.obj.toString());
						data = jsonObject.getString("status");
						if (data.equals("1")) {
							jsonObject = new JSONObject(msg.obj.toString());

							JSONArray numberList = jsonObject
									.getJSONArray("data");
							mPicsUrl = new String[numberList.length()];
							mUrl=new String[numberList.length()];
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
								mUrl[i]=numberList.getJSONObject(i)
										.getString("url");
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
							
							
							mLisView.addView(view_bottom);
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
	private void init() {

		indeLayout=(LinearLayout) findViewById(R.id.index_lt_search);
		indeLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(IndexActivity.this,SousuoActivity.class).putExtra("data", edittext.getText().toString()));
			}
		});
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
			final String str=mPicsUrl[i];
			final String string=mUrl[i];
			mImageViews[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
//					Toast.makeText(IndexActivity.this,"被点击了",1).show();
//					Intent intent=new Intent(IndexActivity.this,AllProductActivity.class);
//					startActivity(intent);
//					Log.i("路径是",str);
					Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(string));  
					it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");  
					IndexActivity.this.startActivity(it);  

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

	/**
	 * 
	 * 当界面下拉的时候执行
	 * */
	@Override
	public void onRefresh(RefreshableView view) {
		// TODO Auto-generated method stub
		//这里只是伪处理了一下
		handlerR.sendEmptyMessageDelayed(1, 2000);
		//这里是添加post接口参数
		mLisView.removeView(view_bottom);
		mdialog.show();
		list.put("type", "adv");
		list.put("part", "index_slide");
		//调用方法，开始进行网络请求
		client = new DataService(handler, 0, list);
		client.start();
	}
	
	
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "再点一次，退出程序",
							Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
					SysApplication.getInstance().exit();
				}
			}
		return true;
	
	}



	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
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
}
