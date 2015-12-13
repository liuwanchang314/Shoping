package com.adapter;

import java.util.List;

import com.activity.R;
import com.bean.CompanyNoticeBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CompanyNoticeAdapter extends BaseAdapter {
	
	private List<CompanyNoticeBean> list;
	private Context context;
	
	public CompanyNoticeAdapter(List<CompanyNoticeBean> list,Context context){
		
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		CompanyNoticeBean bean=list.get(position);
		viewholder vh=null;
		if(convertView==null){
			vh=new viewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.companynotice_listview_item, null);
			vh.title=(TextView) convertView.findViewById(R.id.companyntice_istview_item_content);
			convertView.setTag(vh);
		}else{
			vh=(viewholder) convertView.getTag();
		}
		vh.title.setText(list.get(position).getArticle_title());
		return convertView;
	}
	
	
	static class viewholder{
		TextView title;
	}

}
