package com.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alljf.jf.R;
import com.bean.TuikuanSHBean;
import com.lidroid.xutils.BitmapUtils;

/** 
 */
public class TuiHuoAdapter extends BaseAdapter {
	private Context context;
	private List<TuikuanSHBean> list;
	public TuiHuoAdapter(List<TuikuanSHBean> list,Context context){
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
		viewholderss vh=null;
		if(convertView==null){
			vh=new viewholderss();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.tuikuanshouhou_listview_item,null);
			vh.im=(ImageView) convertView.findViewById(R.id.th_goodsimage);
			vh.title=(TextView) convertView.findViewById(R.id.th_goodname);
			vh.chicun=(TextView) convertView.findViewById(R.id.th_goodschicun);
			vh.yanse=(TextView) convertView.findViewById(R.id.th_goodsyanse);
			vh.zhuangtai=(TextView) convertView.findViewById(R.id.th_zhuangtai);
			vh.jiaoyijin=(TextView) convertView.findViewById(R.id.th_goodsjiaoyijine);
			vh.tuikuanjin=(TextView) convertView.findViewById(R.id.th_tuikuanjine);
			vh.xiangqing=(TextView) convertView.findViewById(R.id.th_xiangqing);
			convertView.setTag(vh);
		}else{
			vh=(viewholderss) convertView.getTag();
			if(list.get(position).getRefund_type().equals("1")){
				vh.zhuangtai.setText("退款");
			}else if(list.get(position).getRefund_status().equals("2")){
				vh.zhuangtai.setText("退货");
			}
			vh.title.setText(list.get(position).getGoods_name());
			vh.jiaoyijin.setText(list.get(position).getRefund_price());
			vh.tuikuanjin.setText(list.get(position).getRefund_price());
			vh.chicun.setText(list.get(position).getSpec_id());
			vh.yanse.setText(list.get(position).getSpec_id());
			BitmapUtils b=new BitmapUtils(context);
			b.display(vh.im,list.get(position).getGoods_image());
			final TextView tv=vh.xiangqing;
			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		return convertView;
	}
	
	static class viewholderss{
		ImageView im;
		TextView title;
		TextView chicun;
		TextView yanse;
		TextView zhuangtai;
		TextView jiaoyijin;
		TextView tuikuanjin;
		TextView xiangqing;
	}

}
