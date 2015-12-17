package com.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activity.BuyActivity;
import com.activity.R;
import com.bean.BuyCartBean;
import com.bean.CompanyNoticeBean;
import com.jsonParser.BuyCartJsonP;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.R.integer;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BuyCartAdapter extends BaseAdapter {
	
	private List<BuyCartBean> list;
	private Context context;
	private static boolean IsChange=false;//用来记录标示listview的布局是否需要发生改变
	private static boolean IsChoise=false;//用来记录标示item中的的randobutton是否被选定
	private static boolean istag=false;
	private TextView total;
	private double totalprice=0.0;
	Double price;
	Double number;
	
	public BuyCartAdapter(List<BuyCartBean> list,Context context,TextView total){
		
		this.list=list;
		this.context=context;
		this.total=total;
	}

	//该方法用来动态通知listview布局以及数据显示发生改变
	public static void isshow(){
		IsChange=true;
	}

	//该方法用来动态通知listview布局以及数据显示发生改变
	public static void unshow(){
		IsChange=false;
	}
	
	//用来动态通知编辑的时候randiobutton是否被选定
	public static void isChoice(){
		IsChoise=true;
	}
	//用来动态通知编辑的时候randiobutton是否被选定
	public static void unChoice(){
		IsChoise=false;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewholder vhs;
		if(convertView==null){
			vhs=new viewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.buyactivity_listview_item1, null);
			vhs.img=(ImageView) convertView.findViewById(R.id.buy_image);
			vhs.title=(TextView) convertView.findViewById(R.id.buy_biaoti);
			vhs.chicun=(TextView) convertView.findViewById(R.id.buy_chicun);
			vhs.yanse=(TextView) convertView.findViewById(R.id.buy_yanse);
			vhs.jiage=(TextView) convertView.findViewById(R.id.buy_jiage);
			vhs.shuliang=(TextView) convertView.findViewById(R.id.buy_shuliang);
			//下面开始获取需要隐藏的界面
			vhs.tubiao=(CheckBox) convertView.findViewById(R.id.buy_yincang_tubiao);
			vhs.bitoti=(LinearLayout) convertView.findViewById(R.id.buy_yincang_biaoti);
			vhs.anniu=(LinearLayout) convertView.findViewById(R.id.buy_yincang_anniu);
			vhs.shanchu=(RelativeLayout) convertView.findViewById(R.id.buy_yingcang_shanchu);
			vhs.jiageheshuliang=(RelativeLayout) convertView.findViewById(R.id.buy_yingcang_jiageheshuliang);
			//控制数量部分
			vhs.add=(ImageView) convertView.findViewById(R.id.buy_jia);
			vhs.jian=(ImageView) convertView.findViewById(R.id.buy_jian);
			vhs.num=(TextView) convertView.findViewById(R.id.buy_num);
			vhs.num.setTag(position);
			convertView.setTag(vhs);
		}else{
			vhs=(viewholder) convertView.getTag();
		}
		
		if(IsChange){
			//首先，图标需要显示出来
			BitmapUtils bitmapUtils=new BitmapUtils(context);
			bitmapUtils.display(vhs.img,list.get(position).getGoods_image());
			vhs.tubiao.setVisibility(View.VISIBLE);//图标需要xianshi
			if(IsChoise){
				//为全选时候
				vhs.tubiao.setChecked(true);
			}else{
				//为不全选时候
				vhs.tubiao.setChecked(false);
				
			}
//			price=Double.parseDouble((String) vhs.jiage.getText());
//			number=Double.parseDouble((String) vhs.num.getText());
			vhs.tubiao.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					//当radiobutton被选定的时候，这里需要计算出这个item的商品价格，然后通知到activity中
					if(isChecked){
						Toast.makeText(context, "被选定了",1).show();
						//调用方法，将该item的价格传递过去，选定就加价
						//先根据价格进行计算总价
//						double totals=price*number;
//						totalprice=totalprice+totals;
//						total.setText(totalprice+"");
					}else{
						Toast.makeText(context, "被取消了了",1).show();
						//调用方法，进行减价处理
						
					}
				}
			});
			vhs.bitoti.setVisibility(View.GONE);//标题需要隐藏
			vhs.anniu.setVisibility(View.VISIBLE);//按钮部分需要显示
			vhs.shanchu.setVisibility(View.VISIBLE);//数量部分需要隐藏
			vhs.shanchu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
						//删除被点击了
					Toast.makeText(context, "被删除了了",1).show();
					list.remove(position);//从数据源中删除
					notifyDataSetChanged();//通知适配器发生改变
					//调用方法，在服务器中进行删除数据
					getdata(list.get(position).getCart_id());
				}
			});
			vhs.jiageheshuliang.setVisibility(View.GONE);
			vhs.chicun.setText(list.get(position).getSpec_id());
			vhs.yanse.setText(list.get(position).getSpec_id());
			vhs.num.setText(list.get(position).getGoods_num());
			//当加减按钮被点击的时候，开始发生变化
			final TextView tv=vhs.num;
			vhs.add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "..加.",1).show();
					int i=Integer.parseInt(tv.getText().toString());
					i++;
					tv.setText(i+"");
				}
			});
			vhs.jian.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Toast.makeText(context, "..减..",1).show();
								int i=Integer.parseInt(tv.getText().toString());
								if(i==1){
									tv.setText(i+"");
								}else{
									i--;
									tv.setText(i+"");
								}
								
							}
						});
			
		}else{
			//默认为false，也就是界面正常显示	
			vhs.tubiao.setVisibility(View.GONE);//图标需要隐藏
			vhs.bitoti.setVisibility(View.VISIBLE);//标题需要显示
			vhs.anniu.setVisibility(View.GONE);//按钮部分需要隐藏；
			vhs.shanchu.setVisibility(View.GONE);
			vhs.jiageheshuliang.setVisibility(View.VISIBLE);
			BitmapUtils bitmapUtils=new BitmapUtils(context);
			bitmapUtils.display(vhs.img,list.get(position).getGoods_image());
			vhs.title.setText(list.get(position).getGoods_name());
			vhs.chicun.setText(list.get(position).getSpec_id());
			vhs.yanse.setText(list.get(position).getSpec_id());
			vhs.jiage.setText(list.get(position).getGoods_price());
			vhs.jiage.setTextColor(Color.RED);
			vhs.shuliang.setText(list.get(position).getGoods_num());
		}
		
		return convertView;
		
	}
	
	static class viewholder{
		CheckBox tubiao;//选择图标
		ImageView img;//商品图片
		ImageView add,jian;//加减按钮
		TextView title;//标题
		TextView chicun,yanse;
		TextView jiage;
		TextView num;
		TextView shuliang;
		LinearLayout bitoti;
		LinearLayout anniu;
		RelativeLayout shanchu;
		RelativeLayout jiageheshuliang;
	}
	
	//获取数据
		private void getdata(String id) {
			// TODO Auto-generated method stub
			RequestParams params = new RequestParams();
			// 只包含字符串参数时默认使用BodyParamsEntity，
			params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
			params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
			params.addBodyParameter("type", "order");
			params.addBodyParameter("part", "cart_delete");
			params.addBodyParameter("cart_id", id);
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
			        	Log.i("删除掉了数据吗", str);
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			        }
			});
		}


}
