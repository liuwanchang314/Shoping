package com.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alljf.jf.R;
import com.bean.OrderBean;
import com.lidroid.xutils.BitmapUtils;

public class DingdanxiangqingAdapter extends BaseAdapter {
	
	private List<OrderBean> list;
	private Context context;
	public DingdanxiangqingAdapter(List<OrderBean> list,Context context){
		
		this.list=list;
		this.context=context;
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
		viewholder vh=null;
		if(convertView==null){
			vh=new viewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.dingdanxiangqing_listview_item, null);
			vh.title=(TextView) convertView.findViewById(R.id.dingdan_goodname);
			vh.chicun=(TextView) convertView.findViewById(R.id.dingdan_goodschicun);
			vh.im=(ImageView) convertView.findViewById(R.id.dingdan_goodsimage);
			vh.num=(TextView) convertView.findViewById(R.id.dingdan_goodsshuliang);
			vh.price=(TextView) convertView.findViewById(R.id.dingdan_goodsjiage);
			vh.yanse=(TextView) convertView.findViewById(R.id.dingdan_goodsyanse);
			vh.gezhong=(TextView) convertView.findViewById(R.id.dingdan_goodsgezhong);
			vh.yunfei=(TextView) convertView.findViewById(R.id.dingdan_goodsyunfei);
			vh.shifukuan=(TextView) convertView.findViewById(R.id.dingdan_goodsshifukuan);
			vh.pingjia=(TextView) convertView.findViewById(R.id.dingdan_goodsdianhua);
			
			convertView.setTag(vh);
		}else{
			
			vh=(viewholder) convertView.getTag();
			vh.title.setText(list.get(position).getOrdergoods().getGoods_name());
			vh.chicun.setText(list.get(position).getOrdergoods().getSpec_id());
			vh.yanse.setText(list.get(position).getOrdergoods().getSpec_id());
			vh.num.setText(list.get(position).getOrdergoods().getGoods_num());
			vh.price.setText(list.get(position).getOrdergoods().getGoods_pay_price());
			vh.shifukuan.setText(list.get(position).getOrdergoods().getGoods_pay_price());
			vh.yunfei.setText(list.get(position).getShipping_price());
			//������Ҫ�ж�һ��״̬�����Ǵ�ʱ����ݣ�����ֱ��
			if(list.get(position).getOrder_status().equals("10")){
				vh.gezhong.setText("����");
			}else if(list.get(position).getOrder_status().equals("20")){
				vh.gezhong.setText("�˿�");
			}else if(list.get(position).getOrder_status().equals("30")){
				vh.gezhong.setText("�˻�");
			}else if(list.get(position).getOrder_status().equals("40")){
				vh.gezhong.setText("�����ۺ�");
			}
			BitmapUtils bpm=new BitmapUtils(context);
			bpm.display(vh.im,list.get(position).getOrdergoods().getGoods_image());
		}
		return convertView;
	}
	
	
	static class viewholder{
		TextView title;
		ImageView im;
		TextView price;
		TextView num;
		TextView chicun;
		TextView yanse;
		TextView yunfei;
		TextView shifukuan;
		TextView gezhong;
		TextView pingjia;
	}
	

}
