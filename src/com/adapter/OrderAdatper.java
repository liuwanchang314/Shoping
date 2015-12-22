package com.adapter;

import java.util.List;

import com.activity.PayForActivity;
import com.activity.R;
import com.bean.BuyCartBean;
import com.bean.OrderBean;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
	public View getView(int arg0, View arg1, ViewGroup arg2) {
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
		//���ж���������
		if(list.get(arg0).getOrder_status().equals("10")){
			//���ڴ�����״̬
			vh.dingdanfenglei.setText("�ȴ���Ҹ���");
			vh.pingjia.setText("����");
			final String price=list.get(arg0).getOrdergoods().getGoods_price();
			final String order=list.get(arg0).getOrder_sn();
			final String fhfs=list.get(arg0).getShipping_mode();
			vh.pingjia.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(context,PayForActivity.class);
					intent.putExtra("price",price);
					intent.putExtra("fhfs",fhfs);
					intent.putExtra("order",order);
					context.startActivity(intent);
				}
			});
			vh.chakanwuliu.setText("ȡ������");
			vh.shanchudingdan.setVisibility(View.INVISIBLE);
			vh.goodsname.setText(list.get(arg0).getOrdergoods().getGoods_name());
			vh.goodschicun.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsyanse.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsjiage.setText(list.get(arg0).getOrdergoods().getGoods_price());
			vh.goodsshuliang.setText(list.get(arg0).getOrdergoods().getGoods_num());
			BitmapUtils bmp=new BitmapUtils(context);
			bmp.display(vh.goodsimg,list.get(arg0).getOrdergoods().getGoods_image());
		}else if(list.get(arg0).getOrder_status().equals("20")){
			//���ڴ�����״̬
			vh.dingdanfenglei.setText("����Ѹ���");
			vh.pingjia.setText("Ͷ��");
			vh.chakanwuliu.setText("�鿴����");
			vh.shanchudingdan.setVisibility(View.INVISIBLE);
			vh.goodsname.setText(list.get(arg0).getOrdergoods().getGoods_name());
			vh.goodschicun.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsyanse.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsjiage.setText(list.get(arg0).getOrdergoods().getGoods_price());
			vh.goodsshuliang.setText(list.get(arg0).getOrdergoods().getGoods_num());
			BitmapUtils bmp=new BitmapUtils(context);
			bmp.display(vh.goodsimg,list.get(arg0).getOrdergoods().getGoods_image());
		}else if(list.get(arg0).getOrder_status().equals("30")){
			//���ڴ��ջ�״̬
			vh.dingdanfenglei.setText("�����ѷ���");
			vh.pingjia.setText("�鿴����");
			vh.chakanwuliu.setText("ȷ���ջ�");
			vh.shanchudingdan.setText("Ͷ��");
			vh.goodsname.setText(list.get(arg0).getOrdergoods().getGoods_name());
			vh.goodschicun.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsyanse.setText(list.get(arg0).getOrdergoods().getSpec_id());
			vh.goodsjiage.setText(list.get(arg0).getOrdergoods().getGoods_price());
			vh.goodsshuliang.setText(list.get(arg0).getOrdergoods().getGoods_num());
			BitmapUtils bmp=new BitmapUtils(context);
			bmp.display(vh.goodsimg,list.get(arg0).getOrdergoods().getGoods_image());
		}else if(list.get(arg0).getOrder_status().equals("40")){
			//���ڴ�����״̬
			vh.dingdanfenglei.setText("���׳ɹ�");
			vh.pingjia.setText("�鿴����");
			vh.chakanwuliu.setText("Ͷ��");
			vh.shanchudingdan.setText("����");
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

}
