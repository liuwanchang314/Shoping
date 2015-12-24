package com.adapter;

import java.util.List;

import com.activity.R;
import com.bean.BillBean;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BillAdapter extends BaseAdapter {
	
	private List<BillBean> list;
	private Context context;
	
	public BillAdapter(){
		
	}
	public BillAdapter(List<BillBean> list,Context context){
		
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
		BillBean bean=list.get(position);
		viewholder vh=null;
		if(convertView==null){
			vh=new viewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.bill_listview_item, null);
			vh.title=(TextView) convertView.findViewById(R.id.bill_item_title);
			vh.im=(ImageView) convertView.findViewById(R.id.bill_item_im);
			vh.delete=(TextView) convertView.findViewById(R.id.bill_item_delete);
			convertView.setTag(vh);
		}else{
			vh=(viewholder) convertView.getTag();
		}
		vh.title.setText(list.get(position).getInv_title()+list.get(position).getCompany_name()+list.get(position).getInv_content());
		vh.delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list.remove(position);
				notifyDataSetChanged();
				delete(list.get(position).getInv_id());
			}
		});
		
		return convertView;
	}
	
	
	static class viewholder{
		TextView title;
		ImageView im;
		TextView delete;
	}
	
	private void delete(String id) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "delete_invoice");
		params.addBodyParameter("invoice", id);
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
		        	Log.i("删除掉了数据吗", str);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

}
