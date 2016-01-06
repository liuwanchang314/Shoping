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
import com.bean.KuaiDiBean;

public class KuaiDiAdapter extends BaseAdapter {
	
	private List<KuaiDiBean> list;
	private Context context;
	
	public KuaiDiAdapter(List<KuaiDiBean> list,Context context){
		
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
			convertView=inflater.inflate(R.layout.kuaidigongsis_listview_item, null);
			vh.title=(TextView) convertView.findViewById(R.id.kuaidigongsi_title);
			vh.tubiao=(ImageView) convertView.findViewById(R.id.kuaidigongsi_tubiao);
			convertView.setTag(vh);
		}else{
			vh=(viewholder) convertView.getTag();
		}
		if(list.get(position).isIschecked()){
			vh.tubiao.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.checkbox_check));
		}else{
			vh.tubiao.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.checkbox_uncheck));
		}
		vh.title.setText(list.get(position).getExpress_title());
		return convertView;
	}
	static class viewholder{
		TextView title;
		ImageView tubiao;
	}
	

}
