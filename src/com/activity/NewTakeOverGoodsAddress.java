package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.Application.SysApplication;
import com.adapter.TakeGoodsAddressAdapter;
import com.bean.ProvienceBean;
import com.bean.TakeGoodsAddressBean;
import com.jsonParser.ProvienceJsonParser;
import com.jsonParser.TakeGoodsAddressJsonpaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author JZKJ-LWC
 * @date : 2015-12-11 下午1:44:33
 * 新建收货地址界面
 */  

public class NewTakeOverGoodsAddress extends Activity{
	
	private ImageView mBack,mHome;
	private EditText mName;
	private EditText mPhone;
	private EditText mYoubian;
	private RelativeLayout mSSX;//省市县
	private RelativeLayout mJD;//街道
	private TextView mSave;//保存
	
	private String province;
	private String city;
	private String district;
	
	/**
	 * 当前省的名称
	 */
	private String mCurrentProviceName;
	private String mCurrentProviceid;
	/**
	 * 当前市的名称
	 */
	private String mCurrentCityName;
	private String mCurrentCityid;
	
	/**
	 * 当前区的名称
	 */
	private String mCurrentAreaName;
	
	private Button button;

	private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();

	private List<ProvienceBean> listP=new ArrayList<ProvienceBean>();//所有省
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_newtaskeovergoodsaddress);
		initview();
		getprovince();
		//省市县被点击的时候，弹出布局来进行处理
		mSSX.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("现在还有数据吗", province);
				//需要先弹出一个dialog，在该dialog上面来进行选择
				Dialog dialog = new Dialog(NewTakeOverGoodsAddress.this, R.style.Theme_Light_Dialog);
	            final View dialogView = LayoutInflater.from(NewTakeOverGoodsAddress.this).inflate(R.layout.choisecitysaddress,null);
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
	            
//	           

			}
		});
	}
	
	//获取用户用户名
	private String getUserName() {
			return SysApplication.getInstance().getUserInfo().getName();
		}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-11 下午2:16:13
	 */  
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.newtakeoveraddress_back);
		mHome=(ImageView) findViewById(R.id.newtakeoveraddress_home);
		mName=(EditText) findViewById(R.id.newtakeoveraddress_name);
		mPhone=(EditText) findViewById(R.id.newtakeoveraddress_phone);
		mYoubian=(EditText) findViewById(R.id.newtakeoveraddress_youzhenbianma);
		mSSX=(RelativeLayout) findViewById(R.id.newtakeoveraddress_shengshixian);
		mJD=(RelativeLayout) findViewById(R.id.newtakeoveraddress_jiedao);
		mSave=(TextView) findViewById(R.id.newtakeoveraddress_save);
	}
	//获取省
	public void getprovince(){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "system");
		params.addBodyParameter("part", "get_province_nokey");
		params.addBodyParameter("country","86");
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
		        	province=responseInfo.result;
		        	Log.i("网络请求下来的shengfen 参数是",province);
		        	
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	
	
		
		
		public void showChoose(View view)
		{
			Toast.makeText(this, mCurrentProviceName + mCurrentCityName + mCurrentAreaName, 1).show();
		}


}