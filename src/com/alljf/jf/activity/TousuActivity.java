package com.alljf.jf.activity;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Application.SysApplication;
import com.adapter.SubjectAdapter;
import com.alljf.jf.R;
import com.bean.OrderBean;
import com.bean.SubjectBean;
import com.jsonParser.SubjectJsonparser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-29 下午11:07:38 
 * 类说明 
 */
public class TousuActivity extends Activity implements OnClickListener{
	private ImageView mback,mhome;
	/**
	 * 选择投诉的按钮
	 */
	private RelativeLayout mXuanzetousu;
	private String SubjectId=null;
	private ImageView mGoodsImage;
	private TextView mGoodsName;
	private TextView mGoodsPrice;
	private TextView mGoodsNum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tousu);
		SysApplication.getInstance().addActivity(this);
		initview();
		Intent intent=getIntent();
		OrderBean bean=(OrderBean) intent.getSerializableExtra("bean");
		getdata(bean.getOrdergoods().getSpec_id());
		BitmapUtils bmp=new BitmapUtils(TousuActivity.this);
		bmp.display(mGoodsImage,bean.getOrdergoods().getGoods_image());
		mGoodsName.setText(bean.getOrdergoods().getGoods_name());
		mGoodsNum.setText(bean.getOrdergoods().getGoods_num());
		mGoodsPrice.setText(bean.getOrdergoods().getGoods_pay_price());
	}
	/**
	 * @2015-12-30下午6:12:20
	 * @author JZKJ-LWC
	 * 初始化
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mback=(ImageView) findViewById(R.id.tousu_back);
		mback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TousuActivity.this.finish();
			}
		});
		mhome=(ImageView) findViewById(R.id.tousu_home);
		mhome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TousuActivity.this.finish();
				startActivity(new Intent(TousuActivity.this,MainActivity.class));
			}
		});
		mXuanzetousu=(RelativeLayout) findViewById(R.id.tousu_xuanzezhuti);
		mXuanzetousu.setOnClickListener(this);
		mGoodsImage=(ImageView) findViewById(R.id.tousu_goods_image);
		mGoodsName=(TextView) findViewById(R.id.tousu_goods_title);
		mGoodsPrice=(TextView) findViewById(R.id.tousu_shangpinjiage);
		mGoodsNum=(TextView) findViewById(R.id.tousu_shangpinshuliang);
		
	}
	
	/**
	 * @2015-12-30下午6:15:13
	 * @author JZKJ-LWC
	 * 根据specid来获取真正的颜色值与尺寸值
	 */
	public void getdata(String id){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_spec_main");
		params.addBodyParameter("spec_id", id);
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
		        	Log.i("获取的规格id是", str);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tousu_xuanzezhuti:
			//调用方法，获取主题
			RequestParams params = new RequestParams();
			// 只包含字符串参数时默认使用BodyParamsEntity，
			params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
			params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
			params.addBodyParameter("type", "system");
			params.addBodyParameter("part", "complain_subject_nokey");
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
			        	Log.i("投诉获取的数据是", str);
			        	final List<SubjectBean> list=SubjectJsonparser.getlist(str);
			        	 final Dialog dialog = new Dialog(TousuActivity.this, R.style.Theme_Light_Dialog);
				            View dialogView = LayoutInflater.from(TousuActivity.this).inflate(R.layout.subjectwindow,null);
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
				            SubjectAdapter adapter=new SubjectAdapter(list, TousuActivity.this);
				            ListView lv=(ListView) dialogView.findViewById(R.id.subjectlistview);
				            lv.setAdapter(adapter);
				            lv.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int arg2, long arg3) {
									// TODO Auto-generated method stub
									SubjectId=list.get(arg2).getSubject_id();
									dialog.dismiss();
								}
							});
			        
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			        }
			});
			break;

		default:
			break;
		}
	}
	

	/**
	 * {
    "api": "APISUCCESS",
    "data": {
        "spec_name": [
            {
                "id": 11,
                "name": "颜色分类"
            },
            {
                "id": 10,
                "name": "适用床尺寸"
            }
        ],
        "spec_price": "12.00",
        "spec_storage": "86",
        "spec_salenum": "0",
        "spec_main": [
            {
                "id": 51,
                "key": null
            },
            {
                "id": 41,
                "key": null
            }
        ]
    },
    "status": 1
}
	 * */

}
