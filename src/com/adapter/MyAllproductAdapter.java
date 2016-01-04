package com.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.AllProductBean;
import com.jf.storeapp.R;
import com.jf.storeapp.activity.AllProductActivity;
import com.lidroid.xutils.BitmapUtils;

public class MyAllproductAdapter extends BaseAdapter {

	private List<AllProductBean> list;
	private Context context;
	private BitmapUtils bitmaputils;
	public MyAllproductAdapter(List<AllProductBean> list,Context context){
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
		AllProductBean bean=list.get(position);
		viewhlder vh=null;
		if(convertView==null){
			//���ڿ�ʼ���ص�һ��Ļ�����
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			  if (AllProductActivity.isGridView)
	            {
	                convertView =inflater.inflate(R.layout.allproduct_gridview_item, parent, false);
	                vh=new viewhlder();
	    			vh.pic=(ImageView) convertView.findViewById(R.id.allproduct_gv_item_pic);
	    			vh.price=(TextView) convertView.findViewById(R.id.allproduct_gv_item_price);
	    			vh.title=(TextView) convertView.findViewById(R.id.allproduct_gv_item_name);
	            }
	            else
	            {
	                convertView = inflater.inflate(R.layout.allproduct_listview_item, parent, false);
	                vh=new viewhlder();
	    			vh.pic=(ImageView) convertView.findViewById(R.id.allproduct_listview_item_pic);
	    			vh.price=(TextView) convertView.findViewById(R.id.allproduct_listview_item_price);
	    			vh.title=(TextView) convertView.findViewById(R.id.allproduct_listview_item_name);
	            }
			
			convertView.setTag(vh);
		}else{
			vh=(viewhlder) convertView.getTag();
		}
		//���￪ʼ�������
		bitmaputils=new BitmapUtils(context);
		vh.price.setText(list.get(position).getPrice());
		vh.title.setText(list.get(position).getName());
		bitmaputils.display(vh.pic,"http://www.91jf.com/"+list.get(position).getImage());
		return convertView;
	}
	
	static class viewhlder{
		ImageView pic;
		TextView title;
		TextView price;
	}

}
