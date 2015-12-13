package com.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import com.activity.R;
import com.lidroid.xutils.BitmapUtils;
import com.other.CommonConstants;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class indexListviewAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, String>> list = new ArrayList<Map<String,String>>();
	private BitmapUtils bitmapUtils ;
	
	public indexListviewAdapter(Context context,List<Map<String, String>> list) {
		this.context = context;
		this.list = list;
		bitmapUtils = new BitmapUtils(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHold vh;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.product_item_lv_item, null);
			vh = new ViewHold();
			vh.img = (ImageView) convertView.findViewById(R.id.proItem_img);
			vh.name_tv = (TextView) convertView.findViewById(R.id.proItem_txt);
			vh.price_tv = (TextView) convertView.findViewById(R.id.proItem_money);
//			vh.img1 = (ImageView) convertView.findViewById(R.id.proItem_img1);
//			vh.name_tv1 = (TextView) convertView.findViewById(R.id.proItem_txt1);
//			vh.price_tv1 = (TextView) convertView.findViewById(R.id.proItem_money1);
			convertView.setTag(vh);
		}else{
			vh = (ViewHold) convertView.getTag();
		}
		Map<String, String> map = list.get(position);
		
		bitmapUtils.display(vh.img, CommonConstants.APP_IMG_URL+map.get("goods_image"));
		vh.name_tv.setText(map.get("goods_name"));
		vh.price_tv.setText(map.get("goods_price"));
		return convertView;
	}

	class ViewHold{
		TextView name_tv,price_tv;//name_tv1,price_tv1;
		ImageView img;//img1;
	}
}
