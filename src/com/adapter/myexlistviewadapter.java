package com.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activity.R;

import android.content.Context;
import android.graphics.Color;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myexlistviewadapter extends BaseExpandableListAdapter {

	//在这里需要有三个参数，上下文对象，组数据源，子组数据源
	private List<Map<String, String>> groupData;
	private List<List<Map<String, String>>> childData ;
	private Context context;
	private static final String G_TEXT = "g_text";
	private static final String C_TEXT1 = "c_text1";
	
	public myexlistviewadapter(List<Map<String, String>> groupData,
			List<List<Map<String, String>>> childData,Context context){
		
		this.groupData=groupData;
		this.childData=childData;
		this.context=context;
		
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childData.get(groupPosition).get(childPosition).get(C_TEXT1).toString();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
    		View view = convertView;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.productclass_childview, null);	
			}
			   	final TextView title = (TextView) view.findViewById(R.id.productclass_listview_childtext);
					title.setText(childData.get(groupPosition).get(childPosition).get(C_TEXT1).toString());			
			 
			return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return childData.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return groupData.get(groupPosition).get(G_TEXT).toString();
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groupData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.productclass_groupview, null);
		}

			TextView title = (TextView) view.findViewById(R.id.productclass_listview_grouptext);
			title.setText(getGroup(groupPosition).toString());

			ImageView image=(ImageView) view.findViewById(R.id.productclass_listview_groupimageview);
			ImageView imags=(ImageView) view.findViewById(R.id.productclass_listview_groupimageviewshutiao);
			imags.setVisibility(View.GONE);
			if(isExpanded){
				imags.setVisibility(View.VISIBLE);
				title.setTextColor(Color.RED);
				image.setBackgroundResource(R.drawable.down);
			}
			else{
				imags.setVisibility(View.GONE);
				title.setTextColor(Color.BLACK);}
				image.setBackgroundResource(R.drawable.left);
			
		return view;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
