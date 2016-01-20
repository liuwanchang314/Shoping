package com.alljf.jf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.Extension.RoundImageView;
import com.alljf.jf.R;
import com.bean.PersonDataBean;
import com.jsonParser.PersonDataJsonprser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class UserActivity extends Activity implements OnClickListener {

	private RelativeLayout mFinancelayout;// 财务明细
	private RelativeLayout mRecharge;// 充值
	private RelativeLayout mAddress;// 收货地址
	private RelativeLayout mInformation;// 个人信息
	private RelativeLayout mOrder;// 全部订单
	private ImageView mErweima;// 二维码图片
	private RelativeLayout mEnd;// 退出当前账号
	private RelativeLayout mComplaint;// 我的投诉
	private RelativeLayout mTixian;// 提现
	private Button button1,button2,button3,button4,button5;
	private RoundImageView mheader;
	private TextView name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_user);
		SysApplication.getInstance().addActivity(this);
		initview();
		getData();
	}

	/**
	 * @2016-1-20下午9:28:49
	 * 获取用户资料，进行设置
	 */
	private void getData() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				RequestParams params = new RequestParams();
				// 只包含字符串参数时默认使用BodyParamsEntity，
				params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
				params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
				params.addBodyParameter("type", "user");
				params.addBodyParameter("part", "get_userinfo");
				params.addBodyParameter("username", SysApplication.getInstance().getUserInfo().getName());
				HttpUtils http = new HttpUtils();
				http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

				        @Override
				        public void onStart() {
				        	//开始请求
				        }

				        @Override
				        public void onLoading(long total, long current, boolean isUploading) {
				            if (isUploading) {
				            } else {
				            }
				        }

				        @Override
				        public void onSuccess(ResponseInfo<String> responseInfo) {
				        	//请求成功
				        	String str=responseInfo.result;
				        	Log.i("网络请求下来的参数是",str);
				        	PersonDataBean bean=PersonDataJsonprser.getbean(str);
				        	name.setText(bean.getCname());
				        	String imageurl=bean.getHead_img();
				        	if(imageurl.equals("")||imageurl==null){
				        		//不做任何操作，选择默认图片
				        	}else{
				        		BitmapUtils bitmapUtils=new BitmapUtils(UserActivity.this);
				        		bitmapUtils.display(mheader, imageurl);
				        	}
				        }

				        @Override
				        public void onFailure(HttpException error, String msg) {
				        }
				});
	}

	private void initview() {
		// TODO Auto-generated method stub

		mheader=(RoundImageView) findViewById(R.id.user_img_head);
		name=(TextView) findViewById(R.id.textView_username);
		mFinancelayout = (RelativeLayout) findViewById(R.id.horder_lt_list5);
		mFinancelayout.setOnClickListener(this);
		mRecharge = (RelativeLayout) findViewById(R.id.horder_lt_list4);
		mRecharge.setOnClickListener(this);
		mAddress = (RelativeLayout) findViewById(R.id.horder_lt_list2);
		mAddress.setOnClickListener(this);
		mInformation = (RelativeLayout) findViewById(R.id.horder_lt_list1);
		mInformation.setOnClickListener(this);
		mOrder = (RelativeLayout) findViewById(R.id.horder_lt_list);
		mOrder.setOnClickListener(this);
		mErweima = (ImageView) findViewById(R.id.user_erweima);
		mErweima.setOnClickListener(this);
		mEnd = (RelativeLayout) findViewById(R.id.horder_lt_list6);
		mEnd.setOnClickListener(this);
		mComplaint = (RelativeLayout) findViewById(R.id.horder_lt_list3);
		mComplaint.setOnClickListener(this);
		mTixian = (RelativeLayout) findViewById(R.id.horder_lt_list_tixian);
		mTixian.setOnClickListener(this);
		button1=(Button) findViewById(R.id.user_tab_rb_1);
		button1.setOnClickListener(this);
		button2=(Button) findViewById(R.id.user_tab_rb_2);
		button2.setOnClickListener(this);
		button3=(Button) findViewById(R.id.user_tab_rb_3);
		button3.setOnClickListener(this);
		button4=(Button) findViewById(R.id.user_tab_rb_4);
		button4.setOnClickListener(this);
		button5=(Button) findViewById(R.id.user_tab_rb_5);
		button5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.horder_lt_list5:// 财务明细
			// Toast.makeText(UserActivity.this, "财务明细",
			// Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, FinanInfoActivity.class));
			break;
		case R.id.horder_lt_list4:// 充值
			// Toast.makeText(UserActivity.this, "充值",
			// Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, RechargeActivity.class));
			break;
		case R.id.horder_lt_list2:// 收货地址
			// Toast.makeText(UserActivity.this, "收货地址",
			// Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this,
					TakeGoodsAddressActivity.class));
			break;
		case R.id.horder_lt_list1:// 个人信息
			// Toast.makeText(UserActivity.this, "个人信息",
			// Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this,
					PersonDataActivity.class));
			break;
		case R.id.user_erweima:// 二维码
			// Toast.makeText(UserActivity.this, "二维码",
			// Toast.LENGTH_SHORT).show();
			Intent intens = new Intent(UserActivity.this,
					TwoDimensionCodeActivity.class);
			startActivity(intens);
			break;
		case R.id.horder_lt_list6:// 退出当前账号
			// Toast.makeText(UserActivity.this, "退出当前账号",
			// Toast.LENGTH_SHORT).show();
			SysApplication.getInstance().logOut();
			startActivity(new Intent(UserActivity.this,MainActivity.class));
			break;
		case R.id.horder_lt_list:// 全部订单
			// Toast.makeText(UserActivity.this, "全部订单",
			// Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, ZJorderActivity.class));
			break;
		case R.id.horder_lt_list3:// 我的投诉
			// Toast.makeText(UserActivity.this, "投诉",
			// Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, ComplaintActivity.class));
			break;
		case R.id.horder_lt_list_tixian:// 体现
			// Toast.makeText(UserActivity.this,"提现",
			// Toast.LENGTH_SHORT).show();
			startActivity(new Intent(UserActivity.this, WithDrawAcivity.class));
			break;
		case R.id.user_tab_rb_1:// 带付款
			Intent intent = new Intent(UserActivity.this, ZJorderActivity.class);
			startActivity(intent);
			break;
		case R.id.user_tab_rb_2:// 待发货
			Intent intent1 = new Intent(UserActivity.this,
					ZJorderActivity.class);
			startActivity(intent1);
			break;
		case R.id.user_tab_rb_3:// 待收货
			Intent intent2 = new Intent(UserActivity.this,
					ZJorderActivity.class);
			startActivity(intent2);
			break;
		case R.id.user_tab_rb_4:// 待评价
			Intent intent3 = new Intent(UserActivity.this,
					ZJorderActivity.class);
			startActivity(intent3);
			break;
		case R.id.user_tab_rb_5:// 退款售后
			Intent intent4 = new Intent(UserActivity.this,
					TuiKuanYuSHActivity.class);
			startActivity(intent4);
			break;
		default:
			break;
		}
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

}
