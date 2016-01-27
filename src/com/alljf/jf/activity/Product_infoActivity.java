package com.alljf.jf.activity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.Extension.AutoScrollViewPager;
import com.Extension.XCFlowLayout;
import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.alljf.jf.activity.IndexActivity.NotifyPageChanged;
import com.bean.PIdimesionAndColor;
import com.bean.PIspec;
import com.bean.ProductInfoBean;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.jsonParser.PIjsonParser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.listenter.BaseUiListener;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.utils.ShareUtils;

public class Product_infoActivity extends Activity implements OnClickListener,OnPageChangeListener{

	private Button mBack;
	private Button mHome;
	private AutoScrollViewPager mViewpager;
	private TextView mName;//商品名称
	private ImageView mWx;//威信分享
	private ImageView mQone;
	private ImageView mPhone;
	private TextView mPrice;
	private TextView mYfei;//运费
	private TextView mLeiji;//累计售出
	private TextView mLiulan;//浏览量
	private RelativeLayout mChoice;//选择尺寸
	private WebView mBigpic;//大图
	private String[] ViewpagerData;
	private ViewGroup group;//顶部标示点布局
	private RelativeLayout layyout_add;
	private ImageView mDotTips[];
	private ImageView mImageViews[];
	private ImageAdapter mAdapter;
	private String _id;//商品id
	
	private XCFlowLayout xDimesion;
	private XCFlowLayout xColor;
	private Button edit;
	private String dimesion=null;
	private String colors=null;
	List<PIdimesionAndColor> list_yanse;
	List<PIdimesionAndColor> list_chicun;
	private BaseUiListener listener = new BaseUiListener();
	int a=0;
	int b=0;
	private Tencent tencent;
	private Map<Integer, View> maps;
	private Map<Integer, View> map_yanse;
	private SpotsDialog mdialog_info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_product_info);
		SysApplication.getInstance().addActivity(this);
		initview();
		_id=getid();
		getdata(_id);
		
		
		
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-14 上午9:35:57
	 * 获取网络数据
	 */  
	public void getdata(String id) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "goods_main_nokey");
		params.addBodyParameter("goods_id",id);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        	mdialog_info.show();
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
		        	mdialog_info.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("网络请求下来的参数是",str);
		        	final ProductInfoBean bean=PIjsonParser.getbean(str);
		        	//调用方法，获取viewpager的数据源
		        	if(bean.getGoods_image_thumb().equals("")||bean.getGoods_image_thumb()==null){
			        	ViewpagerData[0]=bean.getGoods_image();
		        	}else{
		        		String[] strs=getpath(bean.getGoods_image_thumb());
			        	ViewpagerData=new String[strs.length+1];
			        	ViewpagerData[0]=bean.getGoods_image();
			        	for(int i=1;i<=strs.length;i++){
			        		ViewpagerData[i]=strs[i-1];
			        	}
		        	}
		        	
		        	//现在数据源已经准备好了
		        	Log.i("viewpager中的数据源有多少个",ViewpagerData.length+"");
		        	mDotTips = new ImageView[ViewpagerData.length];//计算需要生成的表示点数量
		        	int length = mDotTips.length;
		    		group.removeAllViews();
		    		for (int i = 0; i < length; i++) {
		    			ImageView image = new ImageView(Product_infoActivity.this);

		    			image.setLayoutParams(new LayoutParams(20, 20));
		    			mDotTips[i] = image;

		    			if (i == 0) {
		    				mDotTips[i]
		    						.setBackgroundResource(R.drawable.page_indicator_focused);
		    			} else {
		    				mDotTips[i]
		    						.setBackgroundResource(R.drawable.page_indicator_unfocused);
		    			}

		    			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		    					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		    			params.rightMargin = 10;

		    			group.addView(image, params);
		    			mImageViews = new ImageView[length];
		    			for (int H = 0; H < length; H++) {
		    				ImageView img = new ImageView(Product_infoActivity.this);
		    				BitmapUtils bm=new BitmapUtils(Product_infoActivity.this);
		    				bm.display(img,"http://www.91jf.com"+ViewpagerData[H]);
		    				img.setScaleType(ScaleType.FIT_XY);
		    				mImageViews[H] = img;

		    				mImageViews[H].setOnClickListener(new OnClickListener() {

		    					@Override
		    					public void onClick(View v) {
		    						String aString = v.getTag().toString();
		    					}
		    				});
		    			}
		    			initPager();
		    			mName.setText(bean.getGoods_name());
		    			mLeiji.setText("累计售出"+bean.getSale_num()+"件");
		    			mLeiji.setTextColor(Color.RED);
		    			mLiulan.setText("浏览量"+bean.getCollect_num());
		    			mPrice.setText(bean.getGoods_price_interval());
		    			mPrice.setTextColor(Color.RED);
		    			mBigpic.loadDataWithBaseURL(null,bean.getGoods_content(), "text/html", "utf-8", null);
		    			//因为在点击选择尺寸之后需要带一些数据到弹出的dialog中，所以选择尺寸的监听应该放在这里
		    			mChoice.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
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
					            
					            //颜色集合
					            list_yanse=new ArrayList<PIdimesionAndColor>();
					            list_chicun=new ArrayList<PIdimesionAndColor>();
					            List<PIspec> list_guige=(List<PIspec>) bean.getList();
					            
					            for(int g=0;g<list_guige.size();g++){
					            	if(list_guige.get(g).getId().equals("10")){
					            		//说明是尺寸
					            		list_chicun=list_guige.get(g).getList();
					            	}else if(list_guige.get(g).getId().equals("11")){
					            		//说明是颜色
					            		list_yanse=list_guige.get(g).getList();
					            	}
					            }
					            Log.i("现在尺寸有多少",list_chicun.size()+"");
					            Log.i("现在颜色有多少",list_yanse.size()+"");
					            //商品id
					            Log.i("商品id现在是多少",_id);
					            Log.i("现在图片链接是多少", bean.getGoods_image());
					            Log.i("现在商品价格是多少",bean.getGoods_price());
					            Log.i("库存量是多少", bean.getGoods_status());
					            
					            //现在在这里开始给添加标签
					            xDimesion=(XCFlowLayout) dialogView.findViewById(R.id.proii_xcf_chi);
					            xColor=(XCFlowLayout) dialogView.findViewById(R.id.proii_xcf_color);
					            final MarginLayoutParams ml= new MarginLayoutParams(
					                    LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
					            ml.leftMargin = 5;
					            ml.rightMargin = 5;
					            ml.topMargin = 5;
					            ml.bottomMargin = 5;
					            maps=new HashMap<Integer, View>();
					            final int size=list_chicun.size();
					            for(int i=0; i < list_chicun.size(); i++){
					                final TextView view = new TextView(Product_infoActivity.this);
					                view.setText(list_chicun.get(i).getValue());
					                view.setTextColor(Color.WHITE);
					                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
					                maps.put(i,view);
					                view.setOnClickListener(new OnClickListener() {
					    				
					    				public void onClick(View v) {
					    					// TODO Auto-generated method stub
					    					Toast.makeText(Product_infoActivity.this,view.getText(),Toast.LENGTH_SHORT).show();
					    					//先将标记只为被点击的下表
					    					changeimagebg(xDimesion, size);
					    					addview(xDimesion, maps, size, view.getText().toString(), ml);
					    					//获取id
					    					for(int j=0;j<list_chicun.size();j++){
					    						if(list_chicun.get(j).getValue().equals(view.getText().toString())){
					    							dimesion=list_chicun.get(j).getId();
					    						}
					    					}
					    				}
					    			});
					                xDimesion.addView(view,ml);
					            }
					            map_yanse=new HashMap<Integer, View>();
					            final int nums=list_yanse.size();
					            for(b = 0; b < list_yanse.size(); b ++){
					                final TextView view = new TextView(Product_infoActivity.this);
					                view.setText(list_yanse.get(b).getValue());
					                view.setTextColor(Color.WHITE);
					                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
					                map_yanse.put(b,view);
					                view.setOnClickListener(new OnClickListener() {
					    				
					    				public void onClick(View v) {
					    					// TODO Auto-generated method stub
					    					changeimagebg(xColor, nums);
					    					addview(xColor, map_yanse, nums, view.getText().toString(), ml);
					    					for(int j=0;j<list_yanse.size();j++){
					    						if(list_yanse.get(j).getValue().equals(view.getText().toString())){
					    							colors=list_yanse.get(j).getId();
					    						}
					    					}
					    				}
					    			});
					                xColor.addView(view,ml);
					            }
					            //设置图片log
					            ImageView imlooge=(ImageView) dialogView.findViewById(R.id.proii_img_logo);
					            BitmapUtils bitmapUtils=new BitmapUtils(Product_infoActivity.this);
					            bitmapUtils.display(imlooge,"http://www.91jf.com"+bean.getGoods_image());
					            //设置价格
					            TextView tv_price=(TextView) dialogView.findViewById(R.id.proii_tv_money); 
					            tv_price.setText("￥"+bean.getGoods_price());
					            tv_price.setTextColor(Color.RED);
					            //关闭
					            ImageView im=(ImageView) dialogView.findViewById(R.id.proii_close);
					            im.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
											//当点击关闭 时候，是否需要从这里拿到相应的规格参数呢？
										dialog.dismiss();
									}
								});
					            Button add=(Button) dialogView.findViewById(R.id.btn_cart_add);
					            Button reduce=(Button) dialogView.findViewById(R.id.btn_cart_reduce);
					            edit=(Button) dialogView.findViewById(R.id.btn_cart_num_edit);
					            add.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										int num=Integer.parseInt(edit.getText().toString());
										num++;
										edit.setText(num+"");
									}
								});
					        	reduce.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										int num=Integer.parseInt(edit.getText().toString());
										num--;
										edit.setText(num+"");
									}
								});				
								Button 	addGouwuche=(Button) dialogView.findViewById(R.id.login_btn_submits);
								SpotsDialog.TAG=R.style.SpotsDialogDefault_addProduct;
								final SpotsDialog newdialog=new SpotsDialog(Product_infoActivity.this);
								addGouwuche.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										//先判断用户是否已经登陆了，如果没有，则提示登录，如果是，则调用方法，直接进行添加到
										//购物车，这里需要注意的是
										if(SysApplication.getInstance().getIsLogin()){
											//说明已经登陆,需要将数据提交到远程
											//需要提交的参数1username,2商品id,3规格id,4goodsname,5,购买数量
//											String username=SysApplication.getInstance().getUserInfo().getName();//用户名
											String id=_id;//商品id
//											String goodsname=bean.getGoods_name();//商品名称
//											String buynum=edit.getText().toString();//购买数量
											String guige=colors+","+dimesion;
//											
//											Log.i("要加入了，现在的username是", username);
//											Log.i("要加入了，现在的id是", id);
//											Log.i("要加入了，现在的goodsname是", goodsname);
//											Log.i("要加入了，现在的buynum是", buynum);
//											Log.i("要加入了，现在的guige是", guige);
											//现在在这里获取商品的规格id
											RequestParams params = new RequestParams();
											// 只包含字符串参数时默认使用BodyParamsEntity，
											params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
											params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
											params.addBodyParameter("type", "goods");
											params.addBodyParameter("part", "get_spec_id");
											params.addBodyParameter("goods_id",id);
											params.addBodyParameter("up_id",guige);
											HttpUtils http = new HttpUtils();
											http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

											        @Override
											        public void onStart() {
											        	//开始请求
											        	newdialog.show();
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
											        	newdialog.dismiss();
											        	String str=responseInfo.result;
											        	Log.i("网络请求下来规格id 参数是",str);
											        	/**
											        	 			* {
																    "api": "APISUCCESS",
																    "storage": "85",
																    "spec_id": "398345",
																    "price": "12.00",
																    "status": 1
																}
											        	 * 
											        	 * */
											        	try {
															JSONObject obj=new JSONObject(str);
															String guiges=obj.getString("spec_id");
															String username=SysApplication.getInstance().getUserInfo().getName();//用户名
															String id=_id;//商品id
															String goodsname=bean.getGoods_name();//商品名称
															String buynum=edit.getText().toString();//购买数量
												        	Log.i("要加入了，现在的username是", username);
															Log.i("要加入了，现在的id是", id);
															Log.i("要加入了，现在的goodsname是", goodsname);
															Log.i("要加入了，现在的buynum是", buynum);
															Log.i("要加入了，现在的guige是", guiges);
															//调用方法，加入购物车
															addgouwuche(username,id,goodsname,buynum,guiges);
														} catch (JSONException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
											        	
											        }

											        @Override
											        public void onFailure(HttpException error, String msg) {
											        }
											});
											
										}else{
											AlertDialog.Builder builder=new AlertDialog.Builder(Product_infoActivity.this);
											builder.setTitle("登录提示");
											builder.setMessage("由于您当前状态为未登录状态，暂不能添加到购物车，请登陆！");
											builder.setPositiveButton("登录",new DialogInterface.OnClickListener() {
												
												@Override
												public void onClick(DialogInterface dialog, int which) {
													// TODO Auto-generated method stub
													Intent intent = new Intent(Product_infoActivity.this, LoginActivity.class);
													startActivity(intent);
												}
											});
											
											builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
												
												@Override
												public void onClick(DialogInterface dialog, int which) {
													// TODO Auto-generated method stub
													
												}
											});
											builder.create().show();
										}
									}

									
								});
					            
							}
						});
		        }
		   }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	private void setDotBackground(int pos) {
		int length = mDotTips.length;
		int mPos = pos % length;

		for (int i = 0; i < length; i++) {
			if (i == mPos) {
				mDotTips[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				mDotTips[i]
						.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-12 下午4:56:54
	 */  
	private String getid() {
		// TODO Auto-generated method stub
		//得到id
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		String id=bundle.getString("id");
		Log.i("传递过来的id是多少",id);
		return id;
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		SpotsDialog.TAG=R.style.SpotsDialogDefault;
		mdialog_info=new SpotsDialog(Product_infoActivity.this);
		mdialog_info.setCanceledOnTouchOutside(true);
		mBack=(Button) findViewById(R.id.producinfo_back);
		mBack.setOnClickListener(this);
		mHome=(Button) findViewById(R.id.producinfo_home);
		mHome.setOnClickListener(this);
		mViewpager=(AutoScrollViewPager) findViewById(R.id.producinfo_viewpager);
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
		mBigpic=(WebView) findViewById(R.id.productinfo_datu);
		layyout_add=(RelativeLayout) findViewById(R.id.productinfo_jiarugouwuche);
		layyout_add.setOnClickListener(this);
		WebSettings webSettings = mBigpic.getSettings();
		webSettings.setSavePassword(false);
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportZoom(false);
		webSettings.setUseWideViewPort(true); 
		webSettings.setLoadWithOverviewMode(true); 
		group=(ViewGroup) findViewById(R.id.pi_viewGroup);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.producinfo_back:
			Product_infoActivity.this.finish();
			break;
		case R.id.producinfo_home:
			Product_infoActivity.this.finish();
			startActivity(new Intent(Product_infoActivity.this,MainActivity.class));
			break;
		case R.id.producinfo_weixinfenxiang:
			IWXAPI api = WXAPIFactory.createWXAPI(Product_infoActivity.this, CommonConstants.WXAPP_ID);
//			api.registerApp(CommonConstants.WXAPP_ID);
			ShareUtils.shareWebToWX(Product_infoActivity.this, api, "www.baidu.com", "....", "sss", true);
			break;
		case R.id.producinfo_Qonefeixiang:
			tencent = Tencent.createInstance(CommonConstants.QQ_ID, Product_infoActivity.this);
			ShareUtils.shareMegToQzone(tencent, Product_infoActivity.this, new ArrayList<String>(), "title", "summary", "http://www.baidu.com", listener);
			break;
		case R.id.producinfo_paizhao:
			Toast.makeText(Product_infoActivity.this,"暂不支持，请期待新版本", 1).show();
			break;
		case R.id.productinfo_jiarugouwuche:
					AlertDialog.Builder dialog=new AlertDialog.Builder(Product_infoActivity.this);
					dialog.setTitle("温馨提示");
					dialog.setMessage("请先选择尺寸以及颜色之后再添加宝贝哦");
					dialog.create().show();
//					dialog.setPositiveButton("确定",dialo)
			break;
		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(null != tencent){
			tencent.onActivityResultData(requestCode,resultCode,data,listener);	
		}
		
	}
	private String[] getpath(String str){
		String[] str1 = str.split(",");
		return str1;
	}
	
	private void initPager() {
		mAdapter = new ImageAdapter(Product_infoActivity.this, mImageViews);
		mViewpager.setOnPageChangeListener(Product_infoActivity.this);
		mViewpager.setPageChangeListener(new NotifyPageChanged() {

			@Override
			public void notifyChange(int pos) {
				setDotBackground(pos);
			}
		});

		mViewpager.setAdapter(mAdapter);
		mViewpager.setPageCount(mImageViews == null ? 0 : mImageViews.length);

		mViewpager.startAutoScroll();
	}

	protected static class ImageAdapter extends PagerAdapter {
		ImageView[] mImaes;

		ImageAdapter(Product_infoActivity newsActivity, ImageView[] img) {
			this.mImaes = img;
		}

		@Override
		public int getCount() {

			// return mImaes.length + 1;
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return (arg0 == arg1);
		}

		@Override
		public void destroyItem(View container, int position, Object object) {

			((ViewPager) container)
					.removeView(mImaes[position % mImaes.length]);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView img = mImaes[position % mImaes.length];
			((ViewPager) container).removeView(img);
			((ViewPager) container).addView(img);
			return img;
		}

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		Log.d("luke", "onPageScrollStateChanged");
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		Log.d("luke", "onPageScrolled");
	}

	@Override
	public void onPageSelected(int arg0) {
		setDotBackground(arg0 % mImageViews.length);
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-16 上午11:32:38
	 * //发起网络请求，传入参数，加入购车
	 */  
	private void addgouwuche(String username,
			String id, String goodsname,
			String buynum, String guige) {
		
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "add_cart");
		params.addBodyParameter("user_name",username);
		params.addBodyParameter("goods_id",id);
		params.addBodyParameter("spec_id",guige);
		params.addBodyParameter("buy_num",buynum);
		params.addBodyParameter("goods_name",goodsname);
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
		        	Log.i("网络请求下来的shengfen 参数是",str);
		        	String tag = null;
					try {
						JSONObject obj=new JSONObject(str);
						tag = obj.getString("status");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	if(tag!=null&&tag.equals("0")){
		        		//加入失败
		        		Toast.makeText(Product_infoActivity.this,"加入失败",Toast.LENGTH_SHORT).show();
		        	}else if(tag!=null&&tag.equals("1")){
		        		//加成功
		        		AlertDialog.Builder dialog=new AlertDialog.Builder(Product_infoActivity.this);
		        		dialog.setTitle("温馨提示");
		        		dialog.setMessage("您挑选的商品已经加入购物车，请进入购物车进行结算");
		        		dialog.setPositiveButton("去购物车",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							Intent intent=new Intent(Product_infoActivity.this,BuyActivity.class);
							startActivity(intent);
							}
						})
		        		;
		        		dialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
		        		dialog.create().show();
		        	}
		        	
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        	Toast.makeText(Product_infoActivity.this,"加入失败",1).show();
		        }
		});
	}
	

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-26 下午6:49:48
	 * 用来给taglayout动态的添加标签布局以及设置标签颜色
	 */  
	public void addview(XCFlowLayout layout,Map<Integer, View> map,
			int size,String tag,MarginLayoutParams ml) {
		layout.removeAllViews();
		for(int i=0;i<size;i++){
			TextView view=(TextView) map.get(i);
			if(view.getText().toString().equals(tag)){
				view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bgchange));
				layout.addView(view);
			}else{
				layout.addView(view);
			}
			
		}
		
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 下午2:35:28
	 * 用来还原xlayout中的子view的标签背景
	 */  
	public void changeimagebg(XCFlowLayout layout,int size){
		
		for(int i=0;i<size;i++){
			layout.getChildAt(i).setBackgroundDrawable(getResources()
					.getDrawable(R.drawable.textview_bg));
		}
		
	}

	

}
