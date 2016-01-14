package com.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.alljf.jf.R;
import com.alljf.jf.activity.CheckLogisticsActivity;
import com.alljf.jf.activity.FaBiaoPingJiaActivity;
import com.alljf.jf.activity.PayForActivity;
import com.alljf.jf.activity.SureTakeGoodsActivity;
import com.alljf.jf.activity.TousuActivity;
import com.bean.OrderBean;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class OrderAdatper extends BaseAdapter {

	private List<OrderBean> list;
	private Context context;
	public OrderAdatper(List<OrderBean> list,Context contexts){
		this.list=list;
		context=contexts;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		orderviewholder vh=null;
		if(arg1==null){
			vh=new orderviewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			arg1=inflater.inflate(R.layout.order_fragment_listview_items,null);
			vh.goodsname=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_shangpingmingcheng);
			vh.goodschicun=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_chicun);
			vh.goodsimg=(ImageView) arg1.findViewById(R.id.orderfragment_listviewa_item_picture);
			vh.goodsyanse=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_yansefenlei);
			vh.goodsjiage=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_jiage);
			vh.dingdanfenglei=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_fenleimingcheng);
			vh.goodsshuliang=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_shuliang);
			vh.pingjia=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_pingjia);
			vh.shanchudingdan=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_shanchudingdan);
			vh.chakanwuliu=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_chakanwuliu);
			arg1.setTag(vh);
			
		}else{
			vh=(orderviewholder) arg1.getTag();
		}
		if(list.get(arg0).getOrder_status().equals("10")){
			vh.dingdanfenglei.setText("等待买家付款");
			vh.pingjia.setText("付款");
			final String price=list.get(arg0).getOrdergoods().getGoods_price();
			final String order=list.get(arg0).getOrder_sn();
			final String fhfs=list.get(arg0).getShipping_mode();
			final String pay=list.get(arg0).getPay_sn();
			final String orderid=list.get(arg0).getOrder_id();
			final TextView tv=vh.pingjia;
			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(tv.getText().equals("付款")){
						Intent intent=new Intent(context,PayForActivity.class);
						intent.putExtra("price",price);
						intent.putExtra("fhfs",fhfs);
						intent.putExtra("order",order);
						intent.putExtra("pay", pay);
						intent.putExtra("orderid",orderid);
						context.startActivity(intent);
					}
				}
			});
			vh.chakanwuliu.setText("取消订单");
			final TextView tvs=vh.chakanwuliu;
			final String orderids=list.get(arg0).getOrder_id();
			tvs.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(tvs.getText().equals("取消订单")){
						AlertDialog.Builder dialog=new AlertDialog.Builder(context);
						dialog.setTitle("温馨提示");
						dialog.setMessage("您确定要取消该笔订单么？");
						dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								getdata(orderids);
								list.remove(arg0);
								notifyDataSetChanged();
							}
						});
						dialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						});
						dialog.create().show();
					}
				}
			});
			vh.shanchudingdan.setVisibility(View.INVISIBLE);
			vh.goodsname.setText(list.get(arg0).getOrdergoods().getGoods_name());
			vh.goodschicun.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsyanse.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsjiage.setText(list.get(arg0).getOrdergoods().getGoods_price());
			vh.goodsshuliang.setText(list.get(arg0).getOrdergoods().getGoods_num());
			BitmapUtils bmp=new BitmapUtils(context);
			bmp.display(vh.goodsimg,list.get(arg0).getOrdergoods().getGoods_image());
		}else if(list.get(arg0).getOrder_status().equals("20")){
			vh.dingdanfenglei.setText("买家已付款");
			vh.pingjia.setText("投诉");
			final TextView tousu=vh.pingjia;
			tousu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					if(tousu.getText().equals("投诉")){
						Intent intent=new Intent(context, TousuActivity.class);
						intent.putExtra("bean", list.get(arg0));
						context.startActivity(intent);
						}
				}
			});
			vh.chakanwuliu.setText("查看物流");
			final TextView chakanwuliu=vh.chakanwuliu;
			chakanwuliu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					if(chakanwuliu.getText().equals("查看物流")){
						Intent intent=new Intent(context,CheckLogisticsActivity.class);
						intent.putExtra("bean",list.get(arg0));
						context.startActivity(intent);
					}
					
				}
			});
			vh.shanchudingdan.setVisibility(View.INVISIBLE);
			vh.goodsname.setText(list.get(arg0).getOrdergoods().getGoods_name());
			vh.goodschicun.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsyanse.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsjiage.setText(list.get(arg0).getOrdergoods().getGoods_price());
			vh.goodsshuliang.setText(list.get(arg0).getOrdergoods().getGoods_num());
			BitmapUtils bmp=new BitmapUtils(context);
			bmp.display(vh.goodsimg,list.get(arg0).getOrdergoods().getGoods_image());
		}else if(list.get(arg0).getOrder_status().equals("30")){
			vh.dingdanfenglei.setText("卖家已发货");
			vh.pingjia.setText("查看物流");
			final TextView ckwl=vh.pingjia;
			ckwl.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					if(ckwl.getText().equals("查看物流"));
					Intent intent=new Intent(context,CheckLogisticsActivity.class);
					intent.putExtra("bean",list.get(arg0));
					context.startActivity(intent);
				}
			});
			vh.chakanwuliu.setText("确认收货");
			final TextView qrsh=vh.chakanwuliu;
			qrsh.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(context, SureTakeGoodsActivity.class);
					intent.putExtra("order",list.get(arg0).getOrder_sn());
					context.startActivity(intent);
				}
			});
			vh.shanchudingdan.setText("投诉");
			final TextView tousu=vh.shanchudingdan;
			tousu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(tousu.getText().equals("投诉")){
						Intent intent=new Intent(context, TousuActivity.class);
						intent.putExtra("bean", list.get(arg0));
						context.startActivity(intent);
						}
				}
			});
			vh.goodsname.setText(list.get(arg0).getOrdergoods().getGoods_name());
			vh.goodschicun.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsyanse.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsjiage.setText(list.get(arg0).getOrdergoods().getGoods_price());
			vh.goodsshuliang.setText(list.get(arg0).getOrdergoods().getGoods_num());
			BitmapUtils bmp=new BitmapUtils(context);
			bmp.display(vh.goodsimg,list.get(arg0).getOrdergoods().getGoods_image());
		}else if(list.get(arg0).getOrder_status().equals("40")){
			vh.dingdanfenglei.setText("交易成功");
			vh.pingjia.setText("查看物流");
			final TextView wuliu=vh.pingjia;
			wuliu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(context,CheckLogisticsActivity.class);
					intent.putExtra("bean",list.get(arg0));
					context.startActivity(intent);
				}
			});
			vh.chakanwuliu.setText("投诉");
			final TextView tousu=vh.chakanwuliu;
			tousu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(tousu.getText().equals("投诉")){
						Intent intent=new Intent(context, TousuActivity.class);
						intent.putExtra("bean", list.get(arg0));
						context.startActivity(intent);
						}
				}
			});
			vh.shanchudingdan.setText("评价");
			final TextView pingjia=vh.shanchudingdan;
			pingjia.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(pingjia.getText().equals("评价")){
						Intent intent=new Intent(context, FaBiaoPingJiaActivity.class);
						intent.putExtra("bean", list.get(arg0));
						context.startActivity(intent);
						}
				}
			});
			vh.goodsname.setText(list.get(arg0).getOrdergoods().getGoods_name());
			vh.goodschicun.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsyanse.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsjiage.setText(list.get(arg0).getOrdergoods().getGoods_price());
			vh.goodsshuliang.setText(list.get(arg0).getOrdergoods().getGoods_num());
			BitmapUtils bmp=new BitmapUtils(context);
			bmp.display(vh.goodsimg,list.get(arg0).getOrdergoods().getGoods_image());
		}
		return arg1;
	}
	
	static class orderviewholder{
		TextView dingdanfenglei;
		TextView goodschicun;
		TextView goodsyanse;
		TextView goodsjiage;
		TextView goodsname;
		TextView goodsshuliang;
		ImageView goodsimg;
		TextView pingjia;
		TextView chakanwuliu;
		TextView shanchudingdan;
	}
	
	public void getdata(String id){
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "cancer_order");
		params.addBodyParameter("user_name",SysApplication.getInstance().getUserInfo().getName());
		params.addBodyParameter("order_id", id);
		params.addBodyParameter("state_info", "");
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        }

		        @Override
		        public void onLoading(long total, long current, boolean isUploading) {
		            if (isUploading) {
		            } else {
		            }
		        }

		        @Override
		        public void onSuccess(ResponseInfo<String> responseInfo) {
		        	String str=responseInfo.result;
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

}
