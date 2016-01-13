
package com.alljf.jf.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.pay.ali.Ali_Pay;


public class PayForActivity extends Activity implements OnClickListener {

	private ImageView mBack;
	private ImageView mHome;
	private TextView mMoneyNum;
	private TextView mOrderNum;
	private TextView mPeisongWay;
	private RelativeLayout MzhifuBG;
	private RelativeLayout McaifutongBG;
	private RelativeLayout MweixinBG;
	private ImageView mZhifubao;
	private ImageView mCaifutong;
	private ImageView mWeixin;
	private TextView mSureButton;
	private String payTAG="zw";//用来记录当前用户选择了哪种支付方式
	private TextView forgetPassword;//忘记密码
	private TextView yanzheng;//验证
	private TextView quxiao;//取消
	private TextView queren;//确认
	private EditText mima;//密码框
	private TextView yue;
	private TextView xuzhifu;
	private String price;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_payfor);
		SysApplication.getInstance().addActivity(this);
		initview();
		//首先分析，需要从之前界面传递过来的数据有
		/**
		 * 1,需付款金额，2，订单号，3配送方式
		 * */
		Intent intent=getIntent();
		price=intent.getStringExtra("price");
		String psfs=intent.getStringExtra("fhfs");
		String order=intent.getStringExtra("order");
		mMoneyNum.setText(price);
		mPeisongWay.setText(psfs);
		mOrderNum.setText(order);
		mZhifubao.setOnClickListener(this);
		mCaifutong.setOnClickListener(this);
		mWeixin.setOnClickListener(this);
		mSureButton.setOnClickListener(this);
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-21 下午9:13:05
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.payfor_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PayForActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.payfor_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PayForActivity.this.finish();
				startActivity(new Intent(PayForActivity.this,MainActivity.class));
			}
		});
		mMoneyNum=(TextView) findViewById(R.id.payfor_moneynum);
		mOrderNum=(TextView) findViewById(R.id.payfor_dingdanhao);
		mPeisongWay=(TextView) findViewById(R.id.payfor_peisongfangshi);
		MzhifuBG=(RelativeLayout) findViewById(R.id.payfor_zhifubao_bg_zhifubao);
		MzhifuBG.setBackgroundColor(Color.WHITE);
		mZhifubao=(ImageView) findViewById(R.id.payfor_zhifubao);
		McaifutongBG=(RelativeLayout) findViewById(R.id.payfor_caifutong_bg_caifutong);
		McaifutongBG.setBackgroundColor(Color.WHITE);
		mCaifutong=(ImageView) findViewById(R.id.payfor_caifutong);
		MweixinBG=(RelativeLayout) findViewById(R.id.payfor_weixin_bg_weixin);
		MweixinBG.setBackgroundColor(Color.WHITE);
		mWeixin=(ImageView) findViewById(R.id.payfor_weixin);
		mSureButton=(TextView) findViewById(R.id.payfor_querentijiao);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.payfor_zhifubao:
			payTAG="zfb";
			MzhifuBG.setBackgroundColor(Color.GRAY);
			McaifutongBG.setBackgroundColor(Color.WHITE);
			MweixinBG.setBackgroundColor(Color.WHITE);
			break;
		case R.id.payfor_caifutong:
			payTAG="cft";
			MzhifuBG.setBackgroundColor(Color.WHITE);
			McaifutongBG.setBackgroundColor(Color.GRAY);
			MweixinBG.setBackgroundColor(Color.WHITE);
			break;
		case R.id.payfor_weixin:
			payTAG="wx";
			MzhifuBG.setBackgroundColor(Color.WHITE);
			McaifutongBG.setBackgroundColor(Color.WHITE);
			MweixinBG.setBackgroundColor(Color.GRAY);
			break;
		case R.id.payfor_querentijiao:
			if(payTAG.equals("zw")&&payTAG!=null){
				Toast.makeText(PayForActivity.this,"请选择支付方式",1).show();
			}else if(payTAG.equals("zfb")){
				//在这里进行相应的支付操作
				Log.i("当前选择了支付宝支付", payTAG);
				Ali_Pay ali_Pay = new Ali_Pay(PayForActivity.this);
//				String []sa = price.split(".");
//				int p = Integer.parseInt(sa[0])*100+Integer.parseInt(sa[1]);
				ali_Pay.pay("支付", mOrderNum.getText().toString(), price);
			}else if(payTAG.equals("cft")){
				//财付通的操作,这里到时候需要换一张图片
				Log.i("当前选择了财付通支付", payTAG);
				//需要先弹出一个dialog，在该dialog上面来进行选择
				 final Dialog dialog = new Dialog(PayForActivity.this, R.style.Theme_Light_Dialog);
	            View dialogView = LayoutInflater.from(PayForActivity.this).inflate(R.layout.activity_surepayfor,null);
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
	            forgetPassword=(TextView) dialogView.findViewById(R.id.tv_wangjimima);
	            yanzheng=(TextView) dialogView.findViewById(R.id.tv_yanzheng);
	            mima=(EditText) dialogView.findViewById(R.id.ed_mima);
	            yue=(TextView) dialogView.findViewById(R.id.tv_dangqianyue);
	            queren=(TextView) dialogView.findViewById(R.id.tv_queren);
	            quxiao=(TextView) dialogView.findViewById(R.id.tv_quxiao);
	            xuzhifu=(TextView) dialogView.findViewById(R.id.tv_xuzhifu);
	            xuzhifu.setText(price);
	            yanzheng.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						getdatayanzhenmima(mima.getText().toString());//获取密码进行验证
					}
				});
				
			}else if(payTAG.equals("wx")){
				//微信操作
				Log.i("当前选择了微信支付", payTAG);
			}
				
			break;

		default:
			break;
		}
	}
	//验证支付密码
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午11:00:44
	 * 用来验证用户输入的支付密码是否正确，如果正确，
	 */  
		public void getdatayanzhenmima(String str){
			RequestParams params = new RequestParams();
			// 只包含字符串参数时默认使用BodyParamsEntity，
			params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
			params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
			params.addBodyParameter("type", "user");
			params.addBodyParameter("part", "pay_password");
			params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
			params.addBodyParameter("password",str);
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
			        	Log.i("tixian请求下来的参数是",str);
			        	try {
							JSONObject obj=new JSONObject(str);
							String status=obj.getString("check_status");
							if(status!=null&&status.equals("1")){
								//正确
								RequestParams params = new RequestParams();
								// 只包含字符串参数时默认使用BodyParamsEntity，
								params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
								params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
								params.addBodyParameter("type", "finance");
								params.addBodyParameter("part", "user_finance_91");
								params.addBodyParameter("user_name", SysApplication.getInstance().getUserInfo().getName());
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
								        	Log.i("caiwu 请求下来的参数是",str);
								        	
//								        	这里接口有问题，需要改动
								        	try {
												JSONObject obj=new JSONObject(str);
												JSONObject objs=obj.getJSONObject("data");
												String banace=objs.getString("user_balance");
												yue.setText(banace);
												String user_conbalance=objs.getString("user_conbalance");
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
								        }
			
								        @Override
								        public void onFailure(HttpException error, String msg) {
								        }
								});
							}else if(status!=null&&status.equals("0")){
								//错误
								Toast.makeText(PayForActivity.this,"验证失败",1).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        	
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			        }
			});
		}

}
