package com.activity;

import java.util.ArrayList;
import java.util.List;

import com.adapter.MyAllproductAdapter;
import com.bean.AllProductBean;
import com.customview.PullToRefreshBase.OnRefreshListener;
import com.customview.PullToRefreshGridView;
import com.customview.PullToRefreshListView;
import com.jsonParser.AllProductDataJson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


/**
 * @author JZKJ-LWC
 * @date : 2015-12-10 下午9:17:31
 * 全部商品界面
 */  
@SuppressLint("NewApi")
public class AllProductActivity extends Activity {
	
	private ImageView mTubiaoqiehuan;
	private  PullToRefreshGridView  mGridview;
	private PullToRefreshListView mListview;
	private ActionBar mactionbar;
	private String Mjsonstring=null;
	private MyAllproductAdapter mmyAllproductAdapter;
	private List<AllProductBean> list=new ArrayList<AllProductBean>();
	public static Boolean isGridView= true;
	//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2013/0626/1392.html
	private boolean Tag=true;//定义一个标记，默认用来控制显示方式
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allproduct);
		initview();
		GetData();
		
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午10:07:28
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mactionbar=getActionBar();
		mactionbar.hide();

		mTubiaoqiehuan=(ImageView) findViewById(R.id.allproduct_tuwenbiebiao);
		mTubiaoqiehuan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//目前没有写数据库，所以从网络再次获取，到数据库写好，则在这里从数据库获取数据来加载
					isGridView=!isGridView;
					GetData();
			}
		});
		mGridview=(PullToRefreshGridView) findViewById(R.id.allproduct_grid);
		mGridview.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				// Do work to refresh the list here.
			}

			public void onLoading() {
//				new GetDataTask().execute();
			}
		});
		mGridview.onRefreshComplete();
		mListview=(PullToRefreshListView) findViewById(R.id.allproduct_list);
		mListview.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				// Do work to refresh the list here.
				//刷新时回调
			}

			public void onLoading() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	public void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_goods_nokey");
		params.addBodyParameter("search_goodsname","");
		params.addBodyParameter("goods_class", "");
		params.addBodyParameter("order", "time");
		params.addBodyParameter("limit", "");
		params.addBodyParameter("limit_start", "1");
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
		        	list=AllProductDataJson.GetProductData(str);
		        	Log.i("这一步走了没有？",str+"现在有多少数据"+list.size()+"");
		        		//说明要宫格显示
		        	if(isGridView){
		        		mGridview.setVisibility(View.VISIBLE);
		        		mmyAllproductAdapter=new MyAllproductAdapter(list,AllProductActivity.this);
			    		GridView gv=mGridview.getRefreshableView();
			    		gv.setAdapter(mmyAllproductAdapter);
			    		gv.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								String id=list.get(arg2).getId();//商品id
								Bundle budle=new Bundle();
								budle.putString("id",id);
								Intent intent=new Intent(AllProductActivity.this,Product_infoActivity.class);
								intent.putExtras(budle);
								startActivity(intent);
							}
						});
			    		mListview.setVisibility(View.GONE);
		        	}else{
		        		mListview.setVisibility(View.VISIBLE);
		        		mmyAllproductAdapter=new MyAllproductAdapter(list,AllProductActivity.this);
			    		ListView lv=mListview.getRefreshableView();
			    		lv.setAdapter(mmyAllproductAdapter);
			    		mGridview.setVisibility(View.GONE);
			    		lv.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								//在这里开始打开商品详情
								String id=list.get(arg2).getId();//商品id
								Bundle budle=new Bundle();
								budle.putString("id",id);
								Intent intent=new Intent(AllProductActivity.this,Product_infoActivity.class);
								intent.putExtras(budle);
								startActivity(intent);
							}
						});
		        	}
		        		
		        		
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	

}
