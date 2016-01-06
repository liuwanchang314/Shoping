package com.alljf.jf.activity;



import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alljf.jf.R;


public class OrderActivity extends Activity implements OnClickListener{
	
	
	private ImageView mBack;//返回
	private ImageView mHome;//主页
	private LinearLayout mTitle;//标题布局
	private LinearLayout mFlag;//标记布局
	private LinearLayout mAdd;//添加fragment的布局
	private TextView[] mtitles;//用来存储顶部标题栏数据
	private TextView[] mflages;//用来存储动画烂
	private Fragment[] mfragments;//用来存储fragment
	private ActionBar actionbar;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order);
		initview();
		inittitleview();
		initflagview();
//		initfragments();
		//默认，第一个标题是被选中的，首先展示的是第一个fragment，所以
		mtitles[0].setTextColor(Color.BLUE);
		mflages[0].setBackgroundColor(Color.BLUE);
		FragmentTransaction mtransaction=getFragmentManager().beginTransaction();
		mtransaction.add(R.id.activity_order_layout_addfragment,mfragments[0]);
		mtransaction.commit();
		onclicktitle();
		
	}
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-8 上午10:30:09
	 * 给列表项添加监听
	 */  
	private void onclicktitle() {
		// TODO Auto-generated method stub
			mtitles[0].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//1，需要将相应的动画栏内容改变，以及相应的布局中的fragment切换
					changeother();
					change(0);
					//改变其他标题以及动画的颜色
					
				}
			});
			mtitles[1].setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								//1，需要将相应的动画栏内容改变，以及相应的布局中的fragment切换
								changeother();
								change(1);
								//改变其他标题以及动画的颜色
							}
						});
			mtitles[2].setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								//1，需要将相应的动画栏内容改变，以及相应的布局中的fragment切换
								changeother();
								change(2);
								//改变其他标题以及动画的颜色
							}
						});
			mtitles[3].setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								//1，需要将相应的动画栏内容改变，以及相应的布局中的fragment切换
								changeother();
								change(3);
								//改变其他标题以及动画的颜色
							}
						});
			mtitles[4].setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								//1，需要将相应的动画栏内容改变，以及相应的布局中的fragment切换
								changeother();
								change(4);
								//改变其他标题以及动画的颜色
							}
						});
		}
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-8 上午10:42:59
	 * 改变其他状态
	 */  
	private void changeother() {
		// TODO Auto-generated method stub
		for(int j=0;j<5;j++){
				mtitles[j].setTextColor(Color.BLACK);
				mflages[j].setBackgroundColor(Color.WHITE);
		}
	}

	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-8 上午10:40:41
	 * 改变状态
	 */  
	@SuppressLint("NewApi")
	private void change(int i) {
		// TODO Auto-generated method stub
		mtitles[i].setTextColor(Color.BLUE);
		mflages[i].setBackgroundColor(Color.BLUE);
		FragmentTransaction mtransaction=getFragmentManager().beginTransaction();
		mtransaction.replace(R.id.activity_order_layout_addfragment,mfragments[i]);
		mtransaction.commit();
	}

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-8 上午10:05:23
	 * 用来初始化fragment的数组
	 */  
//	private void initfragments() {
//		// TODO Auto-generated method stub
//		mfragments=new Fragment[5];
//		mfragments[0]=new OrderFragment1();
//		mfragments[1]=new OrderFragment2();
//		mfragments[2]=new OrderFragment3();
//		mfragments[3]=new OrderFragment4();
//		mfragments[4]=new OrderFragment5();
//	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-8 上午10:01:52
	 * 初始化动画栏数据
	 */  
	private void initflagview() {
		// TODO Auto-generated method stub
		mflages=new TextView[5];
		for(int i=0;i<5;i++){
			mflages[i]=(TextView) mFlag.getChildAt(i);
		}

	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-8 上午9:46:57
	 * 初始化顶部标题栏数据
	 */  
	private void inittitleview() {
		// TODO Auto-generated method stub
		mtitles=new TextView[5];
		for(int i=0;i<5;i++){
			mtitles[i]=(TextView) mTitle.getChildAt(i);//从布局中拿出控件
			//默认，第一个标题是被选中的
		}
		
		
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		actionbar=getActionBar();
		actionbar.hide();
		mBack=(ImageView) findViewById(R.id.orderactivity_image_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.orderactivity_image_home);
		mHome.setOnClickListener(this);
		mTitle=(LinearLayout) findViewById(R.id.activity_order_layout_title);
		mFlag=(LinearLayout) findViewById(R.id.activity_order_layout_flag);
		mAdd=(LinearLayout) findViewById(R.id.activity_order_layout_addfragment);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.orderactivity_image_back:
			Toast.makeText(OrderActivity.this,"返回",Toast.LENGTH_SHORT).show();
			break;
		case R.id.orderactivity_image_home:
			Toast.makeText(OrderActivity.this,"主页",Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

}
