package com.alljf.jf.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.bean.OrderBean;
import com.bean.SpecBean;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;



/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 上午9:13:42
 * 查看物流详情界面
 */  
public class CheckLogisticsActivity extends Activity {
	
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-9 上午9:43:1
	 * 9
	 * 需要操作的控件有如下，1返回，2主页，3物流公司标志图片，4物流状态
	 * 5物流公司名称 ，6运单编号 ，7商品名称，8商品图片，9商品尺寸，10商品颜色
	 * 11商品价格，12商品数量，13物流详情listview 
	 */  
	
	private ImageView mBack;//返回
	private ImageView mHome;//主页
	private ImageView mBiaozhi;//标志
	private TextView mSdate;//状态
	private TextView mCompanyName;//物流公司名称
	private TextView mNumber;//编号
	private TextView mProductName;//商品名称
	private ImageView mProductPic;//商品图片
	private TextView mProductDimens;//尺寸
	private TextView mProductColor;//颜色
	private TextView mProductPrice;//价格
	private TextView mProductNumber;//数量
	private ListView mListview;
	
	private SpotsDialog mdialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_checklogistics);
		SysApplication.getInstance().addActivity(this);
		Log.i("进入这里了吗","好像");
		initview();
		Intent intent=getIntent();
		OrderBean bean=(OrderBean) intent.getSerializableExtra("bean");
		if(bean.getExpress_name().equals("")){
			mCompanyName.setText(bean.getExpress_name());
		}else{
			mCompanyName.setText("暂无");
		}
		if(bean.getShipping_code().equals("")){
			mNumber.setText(bean.getShipping_code());
		}else{
			mNumber.setText("暂无");
		}
		if(bean.getOrdergoods().getGoods_name().equals("")){
			mProductName.setText(bean.getOrdergoods().getGoods_name());
		}else{
			mProductName.setText(bean.getOrdergoods().getGoods_name());
		}
		getdataspec(mProductDimens,mProductColor,bean.getOrdergoods().getSpec_id());
		mProductPrice.setText("￥："+bean.getOrdergoods().getGoods_pay_price());
		mProductNumber.setText("X："+bean.getOrdergoods().getGoods_num());
		BitmapUtils bmp=new BitmapUtils(CheckLogisticsActivity.this);
		bmp.display(mProductPic,bean.getOrdergoods().getGoods_image());
		String ship=bean.getShipping_code();
		String id=bean.getExpress_id();
		Log.i("有吗",bean.getShipping_code());
		Log.i("有吗",bean.getExpress_id());
		getdata(bean.getShipping_code(),bean.getExpress_id());
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午7:08:53
	 * 根据尺寸id，获取具体尺寸
	 */  
	private void getdataspec(final TextView chicun, final TextView yanse, String spec_id) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_spec_main");
		params.addBodyParameter("spec_id",spec_id);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        	mdialog.show();
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
		        	mdialog.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("chicun参数是",str);
		        	try {
						List<SpecBean> listspe=new ArrayList<SpecBean>();
						JSONObject obj=new JSONObject(str);
						JSONObject objs=obj.getJSONObject("data");
						JSONArray objss=objs.getJSONArray("spec_main");
						for(int i=0;i<objss.length();i++){
							SpecBean bean=new SpecBean();
							JSONObject obja=objss.getJSONObject(i);
							bean.setKey(obja.getString("key"));
							listspe.add(bean);
						}
						chicun.setText(listspe.get(1).getKey());
						yanse.setText(listspe.get(0).getKey());
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

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午7:01:19
	 * 根据快递公司id和运单编号，来获取快递信息
	 */  
	private void getdata(String shipcode,String kuaidiid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "shipping_info");
		params.addBodyParameter("express_id",kuaidiid);
		params.addBodyParameter("shipping_code", shipcode);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        	mdialog.show();
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
		        	mdialog.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("快递信息获取到了没有", str);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
		
	}
	

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-9 上午9:53:05
	 * 初始化
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mdialog=new SpotsDialog(CheckLogisticsActivity.this);
		mBack=(ImageView) findViewById(R.id.checklogistics_iamgeview_back);
		mBack.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CheckLogisticsActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.checklogistics_iamgeview_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CheckLogisticsActivity.this.finish();
				Intent intent=new Intent(CheckLogisticsActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		mBiaozhi=(ImageView) findViewById(R.id.checklogistics_iamgeview_wuliugongsibiaozhi);
		mSdate=(TextView) findViewById(R.id.checklogistics_tv_wuliuzhuangtai);
		mCompanyName=(TextView) findViewById(R.id.checklogistics_tv_wuliugongsimingchen);
		mNumber=(TextView) findViewById(R.id.checklogistics_tv_yundanbianhao);
		mProductName=(TextView) findViewById(R.id.checklogistics_tv_shangpinmingcheng);
		mProductColor=(TextView) findViewById(R.id.checklogistics_tv_yansefenlei);
		mProductDimens=(TextView) findViewById(R.id.checklogistics_tv_shiyongchuangchicun);
		mProductNumber=(TextView) findViewById(R.id.checklogistics_tv_shangpinshuliang);
		mProductPrice=(TextView) findViewById(R.id.checklogistics_tv_jiage);
		mProductPic=(ImageView) findViewById(R.id.checklogistics_imageview_shangpintupian);
		mListview=(ListView) findViewById(R.id.checklogistics_listview);
		
	}
	

}
