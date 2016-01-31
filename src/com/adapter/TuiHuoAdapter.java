package com.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alljf.jf.R;
import com.bean.SpecBean;
import com.bean.TuikuanSHBean;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/** 
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
			
		}
		
		if(list.get(position).getRefund_type().equals("1")){
			vh.zhuangtai.setText("退款");
		}else if(list.get(position).getRefund_status().equals("2")){
			vh.zhuangtai.setText("退货");
		}
		vh.title.setText(list.get(position).getGoods_name());
		vh.jiaoyijin.setText(list.get(position).getRefund_price());
		vh.tuikuanjin.setText(list.get(position).getRefund_price());
//		vh.chicun.setText(list.get(position).getSpec_id());
		getdataspec(vh.chicun,vh.yanse,list.get(position).getSpec_id());
//		vh.yanse.setText(list.get(position).getSpec_id());
		BitmapUtils b=new BitmapUtils(context);
		b.display(vh.im,list.get(position).getGoods_image());
		final TextView tv=vh.xiangqing;
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
	
	/**
	 * @2016-1-16下午6:37:34
	 * 根据商品尺寸id来获取真正的尺寸值
	 */
	private void getdataspec(final TextView chicun, final TextView yanse, String spec_id) {
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

}
