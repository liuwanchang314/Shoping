package com.adapter;

import java.util.ArrayList;
import java.util.List;

import com.activity.R;
import com.bean.Logistics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LogisticsAdapter extends BaseAdapter{
	
	private Context mcontext;
	private List<Logistics> mList = new ArrayList<Logistics>();
	private LayoutInflater minflater;
	
	public LogisticsAdapter(Context context, List<Logistics> List) {
		this.mcontext = context;
		minflater = LayoutInflater.from(context);
		this.mList = List;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (null != mList) {
			return mList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (null != mList) {
			return mList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHold viewHold;
		if (convertView == null) {
			viewHold = new ViewHold();
			convertView = minflater.inflate(R.layout.item_roate, null);
			viewHold.imageView = (ImageView) convertView.findViewById(R.id.circle);
			viewHold.tvAdress = (TextView) convertView.findViewById(R.id.tvAdress);
			viewHold.tvTime = (TextView) convertView.findViewById(R.id.tvTime);
			viewHold.line = (View)convertView.findViewById(R.id.line);
			convertView.setTag(viewHold);
		}else {
			viewHold = (ViewHold) convertView.getTag();
		}
		if (position == 0) {
			 viewHold.line.setVisibility(View.VISIBLE);
			 viewHold.imageView.setImageResource(R.drawable.item_show);
		}
		viewHold.tvAdress.setText(mList.get(position).getMaddress());
		viewHold.tvTime.setText(mList.get(position).getMtime());
		return convertView;
	}

	private final static class ViewHold{
		ImageView imageView;
		View line;
		TextView tvAdress;
		TextView tvTime;
	}
}
