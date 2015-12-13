package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checklogistics);
		
		initview();
	}

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-9 上午9:53:05
	 * 初始化
	 */  
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.checklogistics_iamgeview_back);
		mHome=(ImageView) findViewById(R.id.checklogistics_iamgeview_home);
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
