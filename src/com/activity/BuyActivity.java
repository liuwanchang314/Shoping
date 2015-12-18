package com.activity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Application.SysApplication;
import com.adapter.BuyCartAdapter;
import com.bean.BuyCartBean;
import com.jsonParser.BuyCartJsonP;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BuyActivity extends Activity implements OnClickListener{

	private TextView mBack;//返回按钮
	private TextView mHome;//主页按钮
	private ImageView mNameXuanDing;//选定名称
	private TextView mChange;//编辑
	private TextView mCName;//公司名称
	private ListView mListview;
	private TextView mQuanxuan;//全选
	private TextView mTotalPrice;//总价
	private TextView mJiesuan;//结算
	
	private String CallBackString;//网络请求回的数据
	BuyCartAdapter adapter;
	private ImageView im_quanzuantubiao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_buy);
		initview();
		getdata();
		//解析
		
		
	}
	//获取用户名
	private String getUserName() {
			return SysApplication.getInstance().getUserInfo().getName();
			}
	//获取数据
	private void getdata() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "cart_list_nokey");
		params.addBodyParameter("user_name",getUserName());
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
		        	CallBackString=responseInfo.result;
		        	Log.i("解析前网络请求有数据吗？",CallBackString);
		        	List<BuyCartBean> list=BuyCartJsonP.getlist(CallBackString);
		        	//准备适配器
		        	adapter=new BuyCartAdapter(list, BuyActivity.this,mTotalPrice);
		        	mListview.setAdapter(adapter);
		        	mChange.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if(mChange.getText().equals("编辑")){
								mChange.setText("完成");
								mNameXuanDing.setBackgroundResource(R.drawable.quanquan);
								//在这里第二步，通知listview发生改变
								BuyCartAdapter.isshow();
								adapter.notifyDataSetChanged();
								//全选按钮需要显示出来
								mQuanxuan.setVisibility(View.VISIBLE);
								im_quanzuantubiao.setVisibility(View.VISIBLE);
								
							}else{
								mChange.setText("编辑");
								mNameXuanDing.setBackgroundResource(R.drawable.gougou);
								BuyCartAdapter.unshow();
								adapter.notifyDataSetChanged();
								mQuanxuan.setVisibility(View.GONE);
								im_quanzuantubiao.setVisibility(View.GONE);
							}
							
						}
					});
		        	
		        	//当全选被点击的时候，还是同理，调用方法，通知listview里面的item的radiobutton发生改变
		        	mQuanxuan.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							//调用方法
							if(mQuanxuan.getText().toString().equals("全选")){
								mQuanxuan.setText("取消");
								im_quanzuantubiao.setBackgroundResource(R.drawable.gougou);
								BuyCartAdapter.isChoice();
								adapter.notifyDataSetChanged();
							}else{
								mQuanxuan.setText("全选");
								im_quanzuantubiao.setBackgroundResource(R.drawable.quanquan);
								BuyCartAdapter.unChoice();
								adapter.notifyDataSetChanged();
							}
						}
					});
		        	mJiesuan.setOnClickListener(new OnClickListener() {
		    			
		    			@Override
		    			public void onClick(View v) {
		    				// TODO Auto-generated method stub
		    				//首先，这里需要拿到购物车中被选中的商品的数据
		    				List<Map<String, BuyCartBean>> newlist=adapter.getNewlists();
		    				List<BuyCartBean> listss=new ArrayList<BuyCartBean>();
		    				for(int i=0;i<newlist.size();i++){
		    					Map<String,BuyCartBean> map=newlist.get(i);
		    					BuyCartBean bean=map.get("bean");
		    					listss.add(bean);
		    				}
		    				Map<Integer, BuyCartBean> map=adapter.getNewmap();
		    				Log.i("现在拿到选定的数据了吗liss",newlist.size()+"");
		    				Log.i("现在拿到选定的数据了吗map",map.size()+"");
		    				//跳转到结算界面
		    				//
		    				Intent intent=new Intent(BuyActivity.this,PayMoneyActivity.class);
		    				intent.putExtra("list", (Serializable)listss);
		    				startActivity(intent);
		    			}
		    		});
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}


	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-7 下午1:49:29
	 * 
	 */
	public void initview(){
		mBack=(TextView) findViewById(R.id.buyactivity_top_textview_back);
		mHome=(TextView) findViewById(R.id.buyactivity_top_textview_home);
		mNameXuanDing=(ImageView) findViewById(R.id.buyactivity_imageview_gongsimingchengxuanding);
		mChange=(TextView) findViewById(R.id.buyactivity_textview_change);
		mCName=(TextView) findViewById(R.id.buyactivity_textview_gongsimingcheng);
		mListview=(ListView) findViewById(R.id.butactivity_listview);
		mQuanxuan=(TextView) findViewById(R.id.buyactivity_radiobutton_quanxuan);
		mTotalPrice=(TextView) findViewById(R.id.buyactivity_textview_totalprice);
		mJiesuan=(TextView) findViewById(R.id.buyacttivity_textview_jiesuan);
		im_quanzuantubiao=(ImageView) findViewById(R.id.image_quanxuan);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
	
	//定义一个方法，在adapter中，当radiobuutton的状态发生改变的时候，都调用一次该方法，并且将该item的价格传递过来
	public static void setdata(double d){
		
	}


}
