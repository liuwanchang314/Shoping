package com.adapter;

import java.util.List;

import com.activity.R;
import com.bean.TakeGoodsAddressBean;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TakeGoodsAddressAdapter extends BaseAdapter {
	
	private Context context;
	private List<TakeGoodsAddressBean> list;
	
	public TakeGoodsAddressAdapter(List<TakeGoodsAddressBean> list,Context context){
		this.context=context;
		this.list=list;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewHolder vh=null;
		if(convertView==null){
			vh=new viewHolder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.takegoodsaddress_listview_item,null);
			vh.name=(TextView) convertView.findViewById(R.id.takegoodsaddress_lv_name);
			vh.address=(TextView) convertView.findViewById(R.id.takegoodsaddress_lv_dizhi);
			vh.phone=(TextView) convertView.findViewById(R.id.takegoodsaddress_lv_dianhua);
			vh.tubiao=(ImageView) convertView.findViewById(R.id.takegoodsaddress_lv_tubiao);
			vh.layout=(RelativeLayout) convertView.findViewById(R.id.takegoodsaddress_lv_layout);
			convertView.setTag(vh);
		}
		else{
			vh=(viewHolder) convertView.getTag();
		}
		//先取出标记，来判断是否是默认地址
		String defaults=list.get(position).getDefaults();
		if(defaults.equals("1")){
			//说明是默认地址
			vh.name.setText(list.get(position).getReceive_name());
			//图标显示
			vh.tubiao.setVisibility(View.VISIBLE);
			vh.address.setText("[默认]"+list.get(position).getArea_info());
			vh.phone.setText(list.get(position).getMob_phone());
			vh.layout.setBackgroundColor(Color.GRAY);
		}else{
			vh.name.setText(list.get(position).getReceive_name());
			vh.address.setText(list.get(position).getArea_info());
			vh.phone.setText(list.get(position).getMob_phone());
		}
		
		
		return convertView;
	}
	
	static class viewHolder{
		TextView name;
		TextView phone;
		TextView address;
		ImageView tubiao;
		RelativeLayout layout;
	}
	
	

}
