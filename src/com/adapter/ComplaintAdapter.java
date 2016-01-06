package com.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alljf.jf.R;
import com.alljf.jf.activity.TousuXiangqingActivity;
import com.bean.ComplaintBean;


public class ComplaintAdapter extends BaseAdapter {
	
	private List<ComplaintBean> list;
	private Context context;
	public ComplaintAdapter(List<ComplaintBean> list,Context context){
		
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
		ComplaintBean bean=list.get(position);
		viewholder vh=null;
		if(convertView==null){
			vh=new viewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.complaint_listview_item, null);
			vh.tousubianhao=(TextView) convertView.findViewById(R.id.complaint_listview_item_dingdanhao);
			vh.neirong=(TextView) convertView.findViewById(R.id.complaint_listview_item_neirong);
			vh.time=(TextView) convertView.findViewById(R.id.complaint_listview_item_time);
			vh.chakanxiangqing=(TextView) convertView.findViewById(R.id.complaint_listview_item_chakanxiangqing);
			vh.zhuangtai=(TextView) convertView.findViewById(R.id.complaint_listview_item_zhuangtai);
			convertView.setTag(vh);
		}else{
			vh=(viewholder) convertView.getTag();
		}
		vh.tousubianhao.setText(list.get(position).getComplain_sn());
		vh.neirong.setText(list.get(position).getComplain_subject_content());
		vh.time.setText(list.get(position).getComplain_datetime());
		vh.zhuangtai.setText(list.get(position).getAccused_id());
		vh.chakanxiangqing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context, TousuXiangqingActivity.class);
				context.startActivity(intent);
			}
		});
		return convertView;
	}
	
	static class viewholder{
		TextView tousubianhao;
		TextView time;
		TextView neirong;
		TextView chakanxiangqing;
		TextView zhuangtai;
	}
	

}
