package com.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alljf.jf.R;
import com.bean.BuyCartBean;
import com.bean.SpecBean;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class PayMoneyAdatper extends BaseAdapter {

	private List<BuyCartBean> list;
	private Context context;
	public PayMoneyAdatper(List<BuyCartBean> list,Context contexts){
		this.list=list;
		context=contexts;
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
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		payviewholder vh=null;
		if(arg1==null){
			vh=new payviewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			arg1=inflater.inflate(R.layout.paymoney_activity_listview_item,null);
			vh.goodsname=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_shangpingmingcheng);
			vh.goodschicun=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_chicun);
			vh.goodsyanse=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_yanse);
			vh.goodsjiage=(TextView) arg1.findViewById(R.id.paymoney_listviewa_item_jiage);
			vh.goodsshuliang=(TextView) arg1.findViewById(R.id.orderfragment_listviewa_item_shuliang);
			vh.goodsimg=(ImageView) arg1.findViewById(R.id.paymoney_listviewa_item_picture);
			arg1.setTag(vh);
			
		}else{
			vh=(payviewholder) arg1.getTag();
		}
		vh.goodsname.setText(list.get(arg0).getGoods_name());
		getdataspec(vh.goodschicun,vh.goodsyanse,list.get(arg0).getSpec_id());
		vh.goodschicun.setText(list.get(arg0).getSpec_id());
		vh.goodsyanse.setText(list.get(arg0).getSpec_id());
		vh.goodsjiage.setText("￥:"+list.get(arg0).getGoods_price());
		vh.goodsshuliang.setText("X:"+list.get(arg0).getGoods_num());
		BitmapUtils bmp=new BitmapUtils(context);
		bmp.display(vh.goodsimg,list.get(arg0).getGoods_image());
		return arg1;
	}
	
	static class payviewholder{
		TextView goodsname;
		TextView goodschicun;
		TextView goodsyanse;
		TextView goodsjiage;
		TextView goodsshuliang;
		ImageView goodsimg;
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
