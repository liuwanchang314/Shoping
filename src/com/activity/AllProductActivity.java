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
import com.other.InternerIsConnection;
import com.other.NetReceiver;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

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
	private ImageView listimage;
	private ConnectivityManager manager;//网络管理器对象
	//http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2013/0626/1392.html
	private boolean Tag=true;//定义一个标记，默认用来控制显示方式
	private boolean isconnection;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_allproduct);
		isconnecions();
		initmanager();
		initview();
		if(isconnection){
			//说明网络是联通的
			GetData();
		}else{
			Toast.makeText(AllProductActivity.this,"网络异常",1).show();
		}
		
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午9:49:45
	 * 用于实时检测网络是否连接
	 */  
	private void isconnecions() {
		// TODO Auto-generated method stub
		NetReceiver mReceiver = new NetReceiver();
	    IntentFilter mFilter = new IntentFilter();
		 mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mReceiver, mFilter);
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午9:11:36
	 * 初始化网络管理对象
	 */  
	private void initmanager() {
		// TODO Auto-generated method stub
		manager =(ConnectivityManager) AllProductActivity.this.getSystemService(AllProductActivity.this.CONNECTIVITY_SERVICE);//获得网络连接的管理者对象
		isconnection=InternerIsConnection.network(manager,AllProductActivity.this);
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-10 下午10:07:28
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mTubiaoqiehuan=(ImageView) findViewById(R.id.allproduct_gridview_or_listview);
		listimage=(ImageView) findViewById(R.id.allproduct_gridview_or_listviews);
		mTubiaoqiehuan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//目前没有写数据库，所以从网络再次获取，到数据库写好，则在这里从数据库获取数据来加载
					isGridView=!isGridView;
					if(isGridView){
						//说明是listview
						mTubiaoqiehuan.setVisibility(View.GONE);
						listimage.setVisibility(View.VISIBLE);
					}
					GetData();
			}
		});
		listimage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//目前没有写数据库，所以从网络再次获取，到数据库写好，则在这里从数据库获取数据来加载
					isGridView=!isGridView;
					if(isGridView){
						//说明是listview
//						mTubiaoqiehuan.setVisibility(View.GONE);
//						listimage.setVisibility(View.VISIBLE);
					}else{
						mTubiaoqiehuan.setVisibility(View.VISIBLE);
						listimage.setVisibility(View.GONE);
					}
					GetData();
			}
		});
		mGridview=(PullToRefreshGridView) findViewById(R.id.allproduct_grid);
		mGridview.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				// Do work to refresh the list here.
				GetData();
			}

			public void onLoading() {
//				new GetDataTask().execute();
			}
		});
		mListview=(PullToRefreshListView) findViewById(R.id.allproduct_list);
		mListview.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				// Do work to refresh the list here.
				//刷新时回调
				GetData();
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
			    		mGridview.onRefreshComplete();
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
			    		mListview.onRefreshComplete();
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
