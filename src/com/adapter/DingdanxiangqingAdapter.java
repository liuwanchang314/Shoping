package com.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout.Spec;
import android.widget.ImageView;
import android.widget.TextView;

import com.alljf.jf.R;
import com.alljf.jf.activity.CheckLogisticsActivity;
import com.alljf.jf.activity.DingDanXQActivity;
import com.alljf.jf.activity.FaBiaoPingJiaActivity;
import com.alljf.jf.activity.PayForActivity;
import com.alljf.jf.activity.ShenQingTuiKuanActivity;
import com.bean.OrderBean;
import com.bean.SpecBean;
import com.jsonParser.OrderDataJsonParser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class DingdanxiangqingAdapter extends BaseAdapter {
	
	private List<OrderBean> list;
	private Context context;
	public DingdanxiangqingAdapter(List<OrderBean> list,Context context){
		
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
			convertView=inflater.inflate(R.layout.dingdanxiangqing_listview_item, null);
			vh.title=(TextView) convertView.findViewById(R.id.dingdan_goodname);
			vh.chicun=(TextView) convertView.findViewById(R.id.dingdan_goodschicun);
			vh.im=(ImageView) convertView.findViewById(R.id.dingdan_goodsimage);
			vh.num=(TextView) convertView.findViewById(R.id.dingdan_goodsshuliang);
			vh.price=(TextView) convertView.findViewById(R.id.dingdan_goodsjiage);
			vh.yanse=(TextView) convertView.findViewById(R.id.dingdan_goodsyanse);
			vh.gezhong=(TextView) convertView.findViewById(R.id.dingdan_goodsgezhong);
			vh.yunfei=(TextView) convertView.findViewById(R.id.dingdan_goodsyunfei);
			vh.shifukuan=(TextView) convertView.findViewById(R.id.dingdan_goodsshifukuan);
			vh.pingjia=(TextView) convertView.findViewById(R.id.dingdan_goodsdianhua);
			
			convertView.setTag(vh);
		}else{
			
			vh=(viewholder) convertView.getTag();
			vh.title.setText(list.get(position).getOrdergoods().getGoods_name());
			getdata(vh.chicun,vh.yanse,list.get(position).getOrdergoods().getSpec_id());
			vh.num.setText("X:"+list.get(position).getOrdergoods().getGoods_num());
			vh.price.setText("￥:"+list.get(position).getOrdergoods().getGoods_pay_price());
			vh.shifukuan.setText(list.get(position).getOrdergoods().getGoods_pay_price());
			vh.yunfei.setText(list.get(position).getShipping_price());
			if(list.get(position).getOrder_status().equals("10")){
				vh.gezhong.setText("付款");
				final String price=list.get(position).getOrdergoods().getGoods_price();
				final String order=list.get(position).getOrder_sn();
				final String fhfs=list.get(position).getShipping_mode();
				vh.gezhong.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(context, PayForActivity.class);
						intent.putExtra("price",price);
						intent.putExtra("fhfs",fhfs);
						intent.putExtra("order",order);
						context.startActivity(intent);
					}
				});
			}else if(list.get(position).getOrder_status().equals("20")){
				vh.gezhong.setText("退款");
				vh.gezhong.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(context, ShenQingTuiKuanActivity.class);
						intent.putExtra("bean",list.get(position));
						intent.putExtra("title","tk");
						context.startActivity(intent);
					}
				});
			}else if(list.get(position).getOrder_status().equals("30")){
				vh.gezhong.setText("退货");
				vh.gezhong.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(context, ShenQingTuiKuanActivity.class);
						intent.putExtra("bean",list.get(position));
						intent.putExtra("title","th");
						context.startActivity(intent);
					}
				});
			}else if(list.get(position).getOrder_status().equals("40")){
				vh.gezhong.setText("申请售后");
				vh.gezhong.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(context, ShenQingTuiKuanActivity.class);
						intent.putExtra("bean",list.get(position));
						intent.putExtra("title","sh");
						context.startActivity(intent);
					}
				});
			}
			BitmapUtils bpm=new BitmapUtils(context);
			bpm.display(vh.im,list.get(position).getOrdergoods().getGoods_image());
		}
		return convertView;
	}
	
	
	/**
	 * @2016-1-16下午6:37:34
	 * 根据商品尺寸id来获取真正的尺寸值
	 */
	private void getdata(final TextView chicun, final TextView yanse, String spec_id) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_spec_main");
		params.addBodyParameter("spec_id",spec_id);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        }

		        @Override
		        public void onLoading(long total, long current, boolean isUploading) {
		            if (isUploading) {
		            } else {
		            }
		        }

		        @Override
		        public void onSuccess(ResponseInfo<String> responseInfo) {
		        	//请求成功
		        	String str=responseInfo.result;
		        	Log.i("chicun参数是",str);
		        	try {
						List<SpecBean> listspe=new ArrayList<SpecBean>();
						JSONObject obj=new JSONObject(str);
						JSONObject objs=obj.getJSONObject("data");
						JSONArray objss=objs.getJSONArray("spec_main");
						for(int i=0;i<objss.length();i++){
							SpecBean bean=new SpecBean();
							JSONObject obja=objss.getJSONObject(i);
							bean.setKey(obja.getString("key"));
							listspe.add(bean);
						}
						chicun.setText(listspe.get(1).getKey());
						yanse.setText(listspe.get(0).getKey());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }
		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}


	static class viewholder{
		TextView title;
		ImageView im;
		TextView price;
		TextView num;
		TextView chicun;
		TextView yanse;
		TextView yunfei;
		TextView shifukuan;
		TextView gezhong;
		TextView pingjia;
	}
	
	
	

}
