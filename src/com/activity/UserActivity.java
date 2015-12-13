package com.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class UserActivity extends Activity implements OnClickListener,OnCheckedChangeListener{

	private RelativeLayout mFinancelayout;//财务明细
	private RelativeLayout mRecharge;//充值
	private RelativeLayout mAddress;//收货地址
	private RelativeLayout mInformation;//个人信息
	private RelativeLayout mOrder;//全部订单
	private ImageView mErweima;//二维码图片
	private RelativeLayout mEnd;//退出当前账号
	private RelativeLayout mComplaint;//我的投诉
	private RelativeLayout mTixian;//提现
	private RadioGroup mRadiogroup;//菜单项
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		initview();
	}
	private void initview() {
		// TODO Auto-generated method stub
		
		mFinancelayout=(RelativeLayout) findViewById(R.id.horder_lt_list5);
		mFinancelayout.setOnClickListener(this);
		mRecharge=(RelativeLayout) findViewById(R.id.horder_lt_list4);
		mRecharge.setOnClickListener(this);
		mAddress=(RelativeLayout) findViewById(R.id.horder_lt_list2);
		mAddress.setOnClickListener(this);
		mInformation=(RelativeLayout) findViewById(R.id.horder_lt_list1);
		mInformation.setOnClickListener(this);
		mOrder=(RelativeLayout) findViewById(R.id.horder_lt_list);
		mOrder.setOnClickListener(this);
		mErweima=(ImageView) findViewById(R.id.user_erweima);
		mErweima.setOnClickListener(this);
		mRadiogroup=(RadioGroup) findViewById(R.id.user_tab_rg_menu);
		mRadiogroup.setOnCheckedChangeListener(this);
		mEnd=(RelativeLayout) findViewById(R.id.horder_lt_list6);
		mEnd.setOnClickListener(this);
		mComplaint=(RelativeLayout) findViewById(R.id.horder_lt_list3);
		mComplaint.setOnClickListener(this);
		mTixian=(RelativeLayout) findViewById(R.id.horder_lt_list_tixian);
		mTixian.setOnClickListener(this);
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.user_tab_rb_1://带付款
			Toast.makeText(UserActivity.this, "代付款", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.user_tab_rb_2://待发货
			Toast.makeText(UserActivity.this, "待发货", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.user_tab_rb_3://待收货
			Toast.makeText(UserActivity.this, "待收货", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.user_tab_rb_4://待评价
			Toast.makeText(UserActivity.this, "待评价", Toast.LENGTH_SHORT).show();
			
			break;
		case R.id.user_tab_rb_5://退款售后
			Toast.makeText(UserActivity.this, "退款售后", Toast.LENGTH_SHORT).show();
			
			break;

		default:
			break;
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.horder_lt_list5://财务明细
			Toast.makeText(UserActivity.this, "财务明细", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, FinanInfoActivity.class));
			break;
		case R.id.horder_lt_list4://充值
			Toast.makeText(UserActivity.this, "充值", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, RechargeActivity.class));
			break;
		case R.id.horder_lt_list2://收货地址
			Toast.makeText(UserActivity.this, "收货地址", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, TakeGoodsAddressActivity.class));
			break;
		case R.id.horder_lt_list1://个人信息
			Toast.makeText(UserActivity.this, "个人信息", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this,PersonDataActivity.class));
			break;
		case R.id.user_erweima://二维码
			Toast.makeText(UserActivity.this, "二维码", Toast.LENGTH_SHORT).show();
			break;
		case R.id.horder_lt_list6://退出当前账号
			Toast.makeText(UserActivity.this, "退出当前账号", Toast.LENGTH_SHORT).show();
			break;
		case R.id.horder_lt_list://全部订单
			Toast.makeText(UserActivity.this, "全部订单", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, OrderActivity.class));
			break;
		case R.id.horder_lt_list3://我的投诉
			Toast.makeText(UserActivity.this, "投诉", Toast.LENGTH_SHORT).show();
			break;
		case R.id.horder_lt_list_tixian://体现
			Toast.makeText(UserActivity.this,"提现", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, WithDrawAcivity.class));
			break;
		default:
			break;
		}
	}


}
