package com.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class orderactivitys extends Activity {

	private ViewPager mPager;//页卡内容
    private List<View> listViews; // Tab页面列表
    private ImageView cursor1;// 动画图片
    private ImageView cursor2;// 动画图片
    private ImageView cursor3;// 动画图片
    private ImageView cursor4;// 动画图片
    private ImageView cursor5;// 动画图片
    private TextView t1, t2, t3,t4,t5;// 页卡头标
    private ImageView[] imagess=new ImageView[5];
    
    private ListView listview_all;
    private ListView listview_daifukuan;
    private ListView listview_daifahuo;
    private ListView listview_daishouhuo;
    private ListView listview_daipingjia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_orders);
		InitImageView();
		InitTextView();
//		InitViewPager();
		//这里需要明白的是，需要获取
	}
	
	private void InitTextView() {
        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);
        t4 = (TextView) findViewById(R.id.text4);
        t5 = (TextView) findViewById(R.id.text5);
        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));
        t4.setOnClickListener(new MyOnClickListener(3));
        t5.setOnClickListener(new MyOnClickListener(4));
    }
	public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;
 
        public MyOnClickListener(int i) {
            index = i;
        }
 
        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };
    
    /**
     * 初始化ViewPager
*/
    private void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.vPager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.orderactivity1, null));
        listview_all=(ListView) mInflater.inflate(R.layout.orderactivity1, null).findViewById(R.id.order_all);
        listViews.add(mInflater.inflate(R.layout.orderactivity2, null));
        listview_daifukuan=(ListView) mInflater.inflate(R.layout.orderactivity2, null).findViewById(R.id.order_daifukuan);
        listViews.add(mInflater.inflate(R.layout.orderactivity3, null));
        listview_daifahuo=(ListView) mInflater.inflate(R.layout.orderactivity3, null).findViewById(R.id.order_daifahuo);
        listViews.add(mInflater.inflate(R.layout.orderactivity4, null));
        listview_daishouhuo=(ListView) mInflater.inflate(R.layout.orderactivity4, null).findViewById(R.id.order_daishouhuo);
        listViews.add(mInflater.inflate(R.layout.orderactivity5, null));
        listview_daipingjia=(ListView) mInflater.inflate(R.layout.orderactivity5, null).findViewById(R.id.order_daipingjia);
        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }
    
    
    /**
     * ViewPager适配器
*/
    public class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;
 
        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }
 
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }
 
        @Override
        public void finishUpdate(View arg0) {
        }
 
        @Override
        public int getCount() {
            return mListViews.size();
        }
 
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }
 
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }
 
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }
 
        @Override
        public Parcelable saveState() {
            return null;
        }
 
        @Override
        public void startUpdate(View arg0) {
        }
    }
    
    public void setid(ImageView[] image,int i){
    	ImageView[] images=image;
    	
    	for(int j=0;j<images.length;j++){
    		if(j==i){
    			images[j].setVisibility(View.VISIBLE);
    		}else{
    			images[j].setVisibility(View.INVISIBLE);
    		}
    	}
    }
    /**
     * 初始化动画
*/
    private void InitImageView() {
        cursor1 = (ImageView) findViewById(R.id.cursor1);
        imagess[0]=cursor1;
        cursor2 = (ImageView) findViewById(R.id.cursor2);
        imagess[1]=cursor2;
        cursor3 = (ImageView) findViewById(R.id.cursor3);
        imagess[2]=cursor3;
        cursor4 = (ImageView) findViewById(R.id.cursor4);
        imagess[3]=cursor4;
        cursor5 = (ImageView) findViewById(R.id.cursor5);
        imagess[4]=cursor5;
        imagess[0].setVisibility(View.VISIBLE);
        imagess[1].setVisibility(View.INVISIBLE);
        imagess[2].setVisibility(View.INVISIBLE);
        imagess[3].setVisibility(View.INVISIBLE);
        imagess[4].setVisibility(View.INVISIBLE);
    }
    
    /**
     * 页卡切换监听
*/
    public class MyOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageSelected(int arg0) {
//            Animation animation = null;
            switch (arg0) {
            
            case 0:
            	//在这里准备数据源，然后加载到控件中
            	setid(imagess, 0);
                break;
            case 1:
            	setid(imagess, 1);
                break;
            case 2:
            	setid(imagess, 2);
                break;
            case 3:
            	setid(imagess, 3);
                break;
            case 4:
            	setid(imagess, 4);
                break;
            }
        }
 
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
 
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }






}
