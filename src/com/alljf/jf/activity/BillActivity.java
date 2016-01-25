package com.alljf.jf.activity;

import java.util.List;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.adapter.BillAdapter;
import com.alljf.jf.R;
import com.bean.BillBean;
import com.customview.Mylistview;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.jsonParser.BillJsonPaser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.other.NetReceiver;

public class BillActivity extends Activity {
	
	private RelativeLayout mSureUserNewBill;//是否使用新发票
	private LinearLayout mFapiaoFenlei;//发票分类部分
	private ImageView mImPTim;//普通发票旁边的图标
	private TextView mTvPTtv;//普通发票按钮
	private ImageView mImZZim;//增值发票旁边
	private TextView mTvZZtv;//增值发票
	private boolean mchoiceis=false;
	private ImageView mChoise;//是否使用新发票图标
	private LinearLayout mZengzhi;//增值切换部分
	private LinearLayout mPutong;//普通切换部分
	private TextView mTvgeren;//个人按钮
	private TextView mTvdanwei;//单位按钮
	private ImageView mImgeren;//个人图标
	private ImageView mIndanwei;//单位图标
	private RelativeLayout mDanweimingchen;//单位名称部分
	
	//以下开始发票内容，这里需要动态获取
	private TextView mMinxi;//明细
	private ImageView mMixinIM;

	private TextView mYinliao;//饮料
	private ImageView mYinliaoIM;
	
	private TextView mZhuangxiu;//装修材料
	private ImageView mZhuangxiuIM;
	
	private TextView mXuesheng;//学生用品
	private ImageView mXueshenIM;
	private EditText Mdanweimingheng;//单位名称
	private EditText mShibiehao;//识别号
	private EditText mZhucedizhi;//注册地址
	private EditText mZhucedianhua;//注册电话
	private EditText mKaihuyinhang;//开户银行
	private EditText mYinhangzhhanghao;//银行账号
	private EditText mShoupiaorenxingming;//受票人姓名
	private EditText mShoupiarenshojihao;//受票人手机号
	private TextView mShoupiaorenshengfen;//售票人身份
	private EditText Mshoupiaodizhhi;//售票地址
	
	private String content;//发票内容
	private int PZtag=0;//用来标记是普通还是增值
	private int tag=0;//用来标记是个人还是单位
	
	private TextView baocunfapiao;
	
	private String fapiaotaitou;
	
	private EditText danweimingcheng;
	
	private Mylistview listview;
	
	private BillAdapter adapter;
	
	private TextView buxuyaofapiao;
	private ImageView mBack,mHome;
	
	/**
	 * 进度条
	 */
	private SpotsDialog mdialog;
	/**
	 * 标记是否选定使用新的发票信息
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_billinfo);
		ViewUtils.inject(BillActivity.this);
		isconnecions();
		SysApplication.getInstance().addActivity(this);
		initview();
		//首先需要获取用户发票记录，然后展示到listview中
		getdatas();
		
		mSureUserNewBill.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//需要将发票分类显示出来
				if(mchoiceis){
					mChoise.setBackgroundResource(R.drawable.quanquan);
					mFapiaoFenlei.setVisibility(View.GONE);
					mZengzhi.setVisibility(View.GONE);
					mPutong.setVisibility(View.GONE);
					mchoiceis=false;
					
				}else{
					mChoise.setBackgroundResource(R.drawable.gougou);
					mFapiaoFenlei.setVisibility(View.VISIBLE);
					mchoiceis=true;
				}
			}
		});
		//当普通或者增值发票被点击的时候，显示其相对应的界面发票被点击的时候
		mTvPTtv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//先将标记副职
				PZtag=1;
				//首先隐藏相对的界面
				mImPTim.setBackgroundResource(R.drawable.gougou);
				mImZZim.setBackgroundResource(R.drawable.quanquan);
				mTvPTtv.setTextColor(Color.RED);
				mTvZZtv.setTextColor(Color.BLACK);
				mZengzhi.setVisibility(View.GONE);
				mPutong.setVisibility(View.VISIBLE);
			}
		});
		mTvZZtv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PZtag=2;
				mImPTim.setBackgroundResource(R.drawable.quanquan);
				mImZZim.setBackgroundResource(R.drawable.gougou);
				mTvPTtv.setTextColor(Color.BLACK);
				mTvZZtv.setTextColor(Color.RED);
				mPutong.setVisibility(View.GONE);
				mZengzhi.setVisibility(View.VISIBLE);
				
			}
		});
		//个人被点击，单位图标变暗，显示个人部分
		mTvgeren.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tag=1;
				fapiaotaitou=mTvgeren.getText().toString();
				mImgeren.setBackgroundResource(R.drawable.gougou);
				mTvgeren.setTextColor(Color.RED);
				mTvdanwei.setTextColor(Color.BLACK);	
				mIndanwei.setBackgroundResource(R.drawable.quanquan);
				mDanweimingchen.setVisibility(View.GONE);
				
			}
		});
		mTvdanwei.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tag=2;
				fapiaotaitou=mTvdanwei.getText().toString();
				mImgeren.setBackgroundResource(R.drawable.quanquan);
				mTvgeren.setTextColor(Color.BLACK);
				mTvdanwei.setTextColor(Color.RED);	
				mIndanwei.setBackgroundResource(R.drawable.gougou);
				mDanweimingchen.setVisibility(View.VISIBLE);
			}
		});
		//这里开始判断了，
		mMinxi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				content=(String) mMinxi.getText();
				mMixinIM.setBackgroundResource(R.drawable.gougou);
				mXueshenIM.setBackgroundResource(R.drawable.quanquan);
				mYinliaoIM.setBackgroundResource(R.drawable.quanquan);
				mZhuangxiuIM.setBackgroundResource(R.drawable.quanquan);
			}
		});
		mYinliao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				content=(String) mYinliao.getText();
				mYinliaoIM.setBackgroundResource(R.drawable.gougou);
				mMixinIM.setBackgroundResource(R.drawable.quanquan);
				mXueshenIM.setBackgroundResource(R.drawable.quanquan);
				mZhuangxiuIM.setBackgroundResource(R.drawable.quanquan);
			}
		});
		mZhuangxiu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				content=(String) mZhuangxiu.getText();
				mYinliaoIM.setBackgroundResource(R.drawable.quanquan);
				mMixinIM.setBackgroundResource(R.drawable.quanquan);
				mXueshenIM.setBackgroundResource(R.drawable.quanquan);
				mZhuangxiuIM.setBackgroundResource(R.drawable.gougou);
			}
		});
		mXuesheng.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				content=(String) mXuesheng.getText();
				mYinliaoIM.setBackgroundResource(R.drawable.quanquan);
				mMixinIM.setBackgroundResource(R.drawable.quanquan);
				mXueshenIM.setBackgroundResource(R.drawable.gougou);
				mZhuangxiuIM.setBackgroundResource(R.drawable.quanquan);
			}
		});
		baocunfapiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//先判断是否需要使用新发票
				if(!mchoiceis){
					Toast.makeText(BillActivity.this,"请填写发票信息", 1).show();
				}else{
					
					if(PZtag==1){
						//说明现在需要保存普通发票
//						Log.i("现在内容有没有",content);
						String username=SysApplication.getInstance().getUserInfo().getName();
						String leibie=PZtag+"";
						String title=fapiaotaitou;
						String danweimingchengs=danweimingcheng.getText().toString();
						if(tag==1){
							//说明是个人
							getdata(username, leibie, title, content, "", "", "", "", "", "", "", "", "", "");
							getdatas();
							new BillAdapter().notifyDataSetChanged();
						}
						else if(tag==2){
							//说明是单位
							getdata(username, leibie, title, content,danweimingchengs, "", "", "", "", "", "", "", "", "");
							getdatas();
							new BillAdapter().notifyDataSetChanged();
						}
					}else if(PZtag==2){
						//说明现在需要添加增值发票
						String username=SysApplication.getInstance().getUserInfo().getName();
						String leibie=PZtag+"";
						String danweimingchengss=null;
							danweimingchengss=Mdanweimingheng.getText().toString();
						String nashuirenshibiehao=null;
							nashuirenshibiehao=mShibiehao.getText().toString();
						String zhucedizhi=null;
							zhucedizhi=mZhucedizhi.getText().toString();
						String zhucedianhua=null;
							zhucedianhua=mZhucedianhua.getText().toString();
						String kaihuyinhang=null;
							kaihuyinhang=mKaihuyinhang.getText().toString();
						String yinhangzhanghao=null;
							yinhangzhanghao=mYinhangzhhanghao.getText().toString();
						String shoupiaorenxingming=null;
							shoupiaorenxingming=mShoupiaorenxingming.getText().toString();
						String shoupiaorenshoujihao=null;
						shoupiaorenshoujihao=mShoupiarenshojihao.getText().toString();
						
						String shoupiaorendizhi=null;
						String songpiaodizhi=null;
						
						getdata(username, leibie,"","", danweimingchengss, nashuirenshibiehao, zhucedizhi, zhucedianhua, kaihuyinhang, yinhangzhanghao, 
								shoupiaorenxingming, shoupiaorenshoujihao, shoupiaorendizhi, songpiaodizhi);
						
						getdatas();
						new BillAdapter().notifyDataSetChanged();
						
					}
				}
		}
		});
		
		buxuyaofapiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.putExtra("title",buxuyaofapiao.getText().toString());
				BillActivity.this.setResult(4,intent);
				BillActivity.this.finish();
			}
		});
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
	 * @date : 2015-12-18 下午5:23:56
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		SpotsDialog.TAG=R.style.SpotsDialogDefault_UPbill;
		mdialog=new SpotsDialog(BillActivity.this);
		mdialog.setCanceledOnTouchOutside(true);
		mSureUserNewBill=(RelativeLayout) findViewById(R.id.bill_shiyongxinfapiao);
		mFapiaoFenlei=(LinearLayout) findViewById(R.id.bill_yincang_fapiaofenlei);
		mImPTim=(ImageView) findViewById(R.id.bill_yincang_fapiaofenlei_pt_im);
		mTvPTtv=(TextView) findViewById(R.id.bill_yincang_fapiaofenlei_pt_tv);
		mImZZim=(ImageView) findViewById(R.id.bill_yincang_fapiaofenlei_zz_im);
		mTvZZtv=(TextView) findViewById(R.id.bill_yincang_fapiaofenlei_zz_tv);
		mChoise=(ImageView) findViewById(R.id.bill_checks);
		mZengzhi=(LinearLayout) findViewById(R.id.bill_yincang_zengzhi);
		mPutong=(LinearLayout) findViewById(R.id.bill_yincang_putong);
		mTvgeren=(TextView) findViewById(R.id.bill_layout_geren_tv);
		mTvdanwei=(TextView) findViewById(R.id.bill_layout_danwei_tv);
		mIndanwei=(ImageView) findViewById(R.id.bill_layout_danwei_im);
		mImgeren=(ImageView) findViewById(R.id.bill_layout_geren_im);
		mDanweimingchen=(RelativeLayout) findViewById(R.id.bill_layout_danweimingcheng);
		mMinxi=(TextView) findViewById(R.id.bill_putong_mingxi_tv);
		mMixinIM=(ImageView) findViewById(R.id.bill_putong_mingxi_im);
		mYinliao=(TextView) findViewById(R.id.bill_putong_yinliao_tv);
		mYinliaoIM=(ImageView) findViewById(R.id.bill_putong_yinliao_im);
		mZhuangxiu=(TextView) findViewById(R.id.bill_putong_zhuangxiucailiao_tv);
		mZhuangxiuIM=(ImageView) findViewById(R.id.bill_putong_zhuangxiucailiao_im);
		mXuesheng=(TextView) findViewById(R.id.bill_putong_xueshengyongpin_tv);
		mXueshenIM=(ImageView) findViewById(R.id.bill_putong_xueshengyongpin_im);
		baocunfapiao=(TextView) findViewById(R.id.bill_baocunfapiao);
		danweimingcheng=(EditText) findViewById(R.id.bill_layout_danweimingcheng_ed);
		Mdanweimingheng=(EditText) findViewById(R.id.bill_z_danweimin);
		mShibiehao=(EditText) findViewById(R.id.bill_z_shibiaohao);
		mZhucedizhi=(EditText) findViewById(R.id.bill_z_zhucedizhi);
		mZhucedianhua=(EditText) findViewById(R.id.bill_z_zhucedianhua);
		mKaihuyinhang=(EditText) findViewById(R.id.bill_z_kaihuyinhang);
		mYinhangzhhanghao=(EditText) findViewById(R.id.bill_z_yinhangzhanghao);
		mShoupiaorenxingming=(EditText) findViewById(R.id.bill_z_shouppiaorenxinming);
		mShoupiarenshojihao=(EditText) findViewById(R.id.bill_z_shouppiaorenshoujihao);
		mShoupiaorenshengfen=(TextView) findViewById(R.id.bill_z_shouppiaorendizhi);
		Mshoupiaodizhhi=(EditText) findViewById(R.id.bill_z_songpiaodizhi);
		listview=(Mylistview) findViewById(R.id.bill_listview);
		buxuyaofapiao=(TextView) findViewById(R.id.bill_buyaofapiao);
		mBack=(ImageView) findViewById(R.id.bill_back);
		mHome=(ImageView) findViewById(R.id.bill_home);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BillActivity.this.finish();
			}
		});
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BillActivity.this.finish();
				Intent intent=new Intent(BillActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
	}
	
	
	public void getdata(String username,String leibie,String title,String content,
			String companyname,String code,String address,String dianhua,String yinhang,
			String yinhangzhanghu,String shoupiaoname,String shoupiaodianhua,String shoupiaoshenfen,
			String songpiaodizhi){
		SpotsDialog.TAG=R.style.SpotsDialogDefault_UPsave;
		final SpotsDialog dialog=new SpotsDialog(BillActivity.this);
		dialog.setCanceledOnTouchOutside(false);
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "add_invoice");
		params.addBodyParameter("username",username);
		params.addBodyParameter("inv_state",leibie);
		params.addBodyParameter("inv_title",title);
		params.addBodyParameter("inv_content",content);
		params.addBodyParameter("inv_company",companyname);
		params.addBodyParameter("inv_code", code);
		params.addBodyParameter("inv_reg_address",address);
		params.addBodyParameter("inv_reg_phone",dianhua);
		params.addBodyParameter("inv_reg_bname",yinhang);
		params.addBodyParameter("inv_reg_baccount",yinhangzhanghu);
		params.addBodyParameter("inv_rec_name",shoupiaoname);
		params.addBodyParameter("inv_rec_mobphone",shoupiaodianhua);
		params.addBodyParameter("inv_rec_province",shoupiaoshenfen);
		params.addBodyParameter("inv_goto_address",songpiaodizhi);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        	dialog.show();
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
		        	dialog.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("加入进去了没有", str);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	
	public void getdatas(){
		
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "invoice_nokey");
		params.addBodyParameter("username",SysApplication.getInstance().getUserInfo().getName());
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
		        	Log.i("发票数据获取了没有", str);
		        	final List<BillBean> list=BillJsonPaser.getlist(str);
		        	adapter=new BillAdapter(list, BillActivity.this);
		        	listview.setAdapter(adapter);
		        	listview.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
						ImageView im=(ImageView) arg1.findViewById(R.id.bill_item_im);
						im.setBackgroundResource(R.drawable.checkbox_check);
						Intent intent=new Intent();
						intent.putExtra("title",list.get(arg2).getInv_title()+list.get(arg2).getInv_content());
						intent.putExtra("fapiaoid",list.get(arg2).getInv_id());
						BillActivity.this.setResult(4,intent);
						BillActivity.this.finish();
						}
					});
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
		

}
