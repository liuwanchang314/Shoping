package com.adapter;

import java.util.List;

import com.activity.R;
import com.bean.BuyCartBean;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PayMoneyAdatper extends BaseAdapter {

	private List<BuyCartBean> list;
	private Context context;
	public PayMoneyAdatper(List<BuyCartBean> list,Context contexts){
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
		payviewholder vh=null;
		if(arg1==null){
			vh=new payviewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			arg1=inflater.inflate(R.layout.paymoney_activity_listview_item,null);
			vh.goodsname=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_shangpingmingcheng);
			vh.goodschicun=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_chicun);
			vh.goodsyanse=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_yanse);
			vh.goodsjiage=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_jiage);
			vh.goodsshuliang=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_shuliang);
			vh.goodsimg=(ImageView) arg1.findViewById(R.id.paymoney_listviewa_item_picture);
			arg1.setTag(vh);
			
		}else{
			vh=(payviewholder) arg1.getTag();
		}
		vh.goodsname.setText(list.get(arg0).getGoods_name());
		vh.goodschicun.setText(list.get(arg0).getSpec_id());
		vh.goodsyanse.setText(list.get(arg0).getSpec_id());
		vh.goodsjiage.setText(list.get(arg0).getGoods_price());
		vh.goodsjiage.setTextColor(Color.RED);
		vh.goodsshuliang.setText(list.get(arg0).getGoods_num());
		vh.goodsshuliang.setTextColor(Color.RED);
		BitmapUtils bmp=new BitmapUtils(context);
		bmp.display(vh.goodsimg,list.get(arg0).getGoods_image());
		return arg1;
	}
	
	static class payviewholder{
		TextView goodsname;
		TextView goodschicun;
		TextView goodsyanse;
		TextView goodsjiage;
		TextView goodsshuliang;
		ImageView goodsimg;
	}

}
