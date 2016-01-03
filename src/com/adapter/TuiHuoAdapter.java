package com.adapter;

import java.util.List;

import com.activity.R;
import com.bean.TuikuanSHBean;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-3 下午9:29:21 
 * 类说明 
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
			if(list.get(position).getRefund_status().equals("1")){
				vh.zhuangtai.setText("待处理");
			}else if(list.get(position).getRefund_status().equals("2")){
				vh.zhuangtai.setText("处理中");
			}else if(list.get(position).getRefund_status().equals("3")){
				vh.zhuangtai.setText("已完成");
			}
			vh.title.setText(list.get(position).getGoods_name());
			vh.jiaoyijin.setText(list.get(position).getRefund_price());
			vh.tuikuanjin.setText(list.get(position).getRefund_price());
			vh.chicun.setText(list.get(position).getSpec_id());
			vh.yanse.setText(list.get(position).getSpec_id());
			BitmapUtils b=new BitmapUtils(context);
			b.display(vh.im,list.get(position).getGoods_image());
			vh.xiangqing.setText("查看详情");
			final TextView tv=vh.xiangqing;
			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "详情",1).show();
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
