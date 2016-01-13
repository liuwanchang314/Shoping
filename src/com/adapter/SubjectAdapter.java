package com.adapter;

import java.util.List;

import com.alljf.jf.R;
import com.bean.SubjectBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-10 下午3:23:02 
 * 类说明 
 */
public class SubjectAdapter extends BaseAdapter {

	private List<SubjectBean> list;
	private Context context;
	public SubjectAdapter(List<SubjectBean> list,Context context){
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
		viewholder vh=null;
		if(convertView==null){
			vh=new viewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.subject_listview_item,null);
			vh.title=(TextView) convertView.findViewById(R.id.subject_lv_item);
			convertView.setTag(vh);
		}else{
			vh=(viewholder) convertView.getTag();
			
		}
		vh.title.setText(list.get(position).getSubject_content().toString());
		return convertView;
	}
	static class viewholder{
		TextView title;
	}

}
