package com.activity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Product_infoActivity extends Activity implements OnClickListener{

	private ActionBar actionbar;
	private Button mBack;
	private Button mHome;
	private ViewPager mViewpager;
	private TextView mName;//商品名称
	private ImageView mWx;//威信分享
	private ImageView mQone;
	private ImageView mPhone;
	private TextView mPrice;
	private TextView mYfei;//运费
	private TextView mLeiji;//累计售出
	private TextView mLiulan;//浏览量
	private RelativeLayout mChoice;//选择尺寸
	private ImageView mBigpic;//大图
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_info);
		initview();
		getid();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-12 下午4:56:54
	 */  
	private void getid() {
		// TODO Auto-generated method stub
		//得到id
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		String id=bundle.getString("id");
		Log.i("传递过来的id是多少",id);
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		actionbar=getActionBar();
		actionbar.hide();
		mBack=(Button) findViewById(R.id.producinfo_back);
		mBack.setOnClickListener(this);
		mHome=(Button) findViewById(R.id.producinfo_home);
		mHome.setOnClickListener(this);
		mViewpager=(ViewPager) findViewById(R.id.producinfo_viewpager);
		mName=(TextView) findViewById(R.id.producinfo_name);
		mWx=(ImageView) findViewById(R.id.producinfo_weixinfenxiang);
		mWx.setOnClickListener(this);
		mQone=(ImageView) findViewById(R.id.producinfo_Qonefeixiang);
		mQone.setOnClickListener(this);
		mPhone=(ImageView) findViewById(R.id.producinfo_paizhao);
		mPhone.setOnClickListener(this);
		mPrice=(TextView) findViewById(R.id.producinfo_jiage);
		mYfei=(TextView) findViewById(R.id.producinfo_yunfei);
		mLeiji=(TextView) findViewById(R.id.producinfo_leijishouchu);
		mLiulan=(TextView) findViewById(R.id.producinfo_liulanliang);
		mChoice=(RelativeLayout) findViewById(R.id.productinfo_chicunxuanze);
		mChoice.setOnClickListener(this);
		mBigpic=(ImageView) findViewById(R.id.productinfo_datu);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.producinfo_back:
			Toast.makeText(Product_infoActivity.this, "返回",Toast.LENGTH_SHORT).show();
			break;
		case R.id.producinfo_home:
			Toast.makeText(Product_infoActivity.this, "主页",Toast.LENGTH_SHORT).show();
			break;
		case R.id.producinfo_weixinfenxiang:
			Toast.makeText(Product_infoActivity.this, "威信",Toast.LENGTH_SHORT).show();
			break;
		case R.id.producinfo_Qonefeixiang:
			Toast.makeText(Product_infoActivity.this, "qq控件",Toast.LENGTH_SHORT).show();
			break;
		case R.id.producinfo_paizhao:
			Toast.makeText(Product_infoActivity.this, "拍照",Toast.LENGTH_SHORT).show();
			break;
		case R.id.productinfo_chicunxuanze:
			Toast.makeText(Product_infoActivity.this, "选择尺寸",Toast.LENGTH_SHORT).show();
			//需要先弹出一个dialog，在该dialog上面来进行选择
			 final Dialog dialog = new Dialog(Product_infoActivity.this, R.style.Theme_Light_Dialog);
            View dialogView = LayoutInflater.from(Product_infoActivity.this).inflate(R.layout.activity_product_info_item,null);
            //获得dialog的window窗口
            Window window = dialog.getWindow();
            //设置dialog在屏幕底部
            window.setGravity(Gravity.BOTTOM);
            //设置dialog弹出时的动画效果，从屏幕底部向上弹出
            window.setWindowAnimations(R.style.dialogStyle);
            window.getDecorView().setPadding(0, 0, 0, 0);
            //获得window窗口的属性
            android.view.WindowManager.LayoutParams lp = window.getAttributes();
            //设置窗口宽度为充满全屏
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //设置窗口高度为包裹内容
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //将设置好的属性set回去
            window.setAttributes(lp);
            //将自定义布局加载到dialog上
            dialog.setContentView(dialogView);
            
            dialog.show();
            
            
            
			break;

		default:
			break;
		}
	}
	

}
