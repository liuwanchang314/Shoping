package com.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alljf.jf.R;
import com.bean.BuyCartBean;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class BuyCartAdapter extends BaseAdapter {

	private List<BuyCartBean> list;
	private Context context;
	private static boolean IsChoise = false;
	private static boolean istag = false;
	private TextView total;
	private double totalprice = 0.0;
	private static HashMap<Integer, Boolean> isSelected;

	public static boolean IsChange;
	Double price;
	Double number;
	private List<Map<String, BuyCartBean>> newlists = new ArrayList<Map<String, BuyCartBean>>();

	public List<Map<String, BuyCartBean>> getNewlists() {
		return newlists;
	}

	private Map<Integer, BuyCartBean> newmap = new HashMap<Integer, BuyCartBean>();

	public Map<Integer, BuyCartBean> getNewmap() {
		return newmap;
	}

	public void setNewmap(Map<Integer, BuyCartBean> newmap) {
		this.newmap = newmap;
	}

	public void setNewlists(List<Map<String, BuyCartBean>> newlists) {
		this.newlists = newlists;
	}

	public BuyCartAdapter(List<BuyCartBean> list, Context context,
			TextView total) {
		isSelected = new HashMap<Integer, Boolean>();
		this.list = list;
		this.context = context;
		this.total = total;
		initDate();
	}

	private void initDate() {
		for (int i = 0; i < list.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		BuyCartAdapter.isSelected = isSelected;
	}

	public static void isshow() {
		IsChange = true;
	}

	public static void unshow() {
		IsChange = false;
	}

	public static void isChoice() {
		IsChoise = true;
	}

	public static void unChoice() {
		IsChoise = false;
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
		viewholder vhs;
		final Map<String, BuyCartBean> map = new HashMap<String, BuyCartBean>();
		if (convertView == null) {
			vhs = new viewholder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.buyactivity_listview_item1,
					null);
			vhs.img = (ImageView) convertView.findViewById(R.id.buy_image);
			vhs.title = (TextView) convertView.findViewById(R.id.buy_biaoti);
			vhs.chicun = (TextView) convertView.findViewById(R.id.buy_chicun);
			vhs.yanse = (TextView) convertView.findViewById(R.id.buy_yanse);
			vhs.jiage = (TextView) convertView.findViewById(R.id.buy_jiage);
			vhs.shuliang = (TextView) convertView
					.findViewById(R.id.buy_shuliang);
			vhs.tubiao = (CheckBox) convertView
					.findViewById(R.id.buy_yincang_tubiao);
			vhs.bitoti = (LinearLayout) convertView
					.findViewById(R.id.buy_yincang_biaoti);
			vhs.anniu = (LinearLayout) convertView
					.findViewById(R.id.buy_yincang_anniu);
			vhs.shanchu = (RelativeLayout) convertView
					.findViewById(R.id.buy_yingcang_shanchu);
			vhs.jiageheshuliang = (RelativeLayout) convertView
					.findViewById(R.id.buy_yingcang_jiageheshuliang);
			vhs.add = (ImageView) convertView.findViewById(R.id.buy_jia);
			vhs.jian = (ImageView) convertView.findViewById(R.id.buy_jian);
			vhs.num = (TextView) convertView.findViewById(R.id.buy_num);
			vhs.num.setTag(position);
			convertView.setTag(vhs);
		} else {
			vhs = (viewholder) convertView.getTag();
		}

		if (IsChange) {
			BitmapUtils bitmapUtils = new BitmapUtils(context);
			bitmapUtils.display(vhs.img, list.get(position).getGoods_image());
			vhs.tubiao.setVisibility(View.VISIBLE);// ͼ����Ҫxianshi
			vhs.tubiao.setTag(position);
			vhs.tubiao.setChecked(getIsSelected().get(position));
			vhs.tubiao
					.setOnCheckedChangeListener(new CheckBoxChangedListener());
			vhs.bitoti.setVisibility(View.GONE);// ������Ҫ����
			vhs.anniu.setVisibility(View.VISIBLE);// ��ť������Ҫ��ʾ
			vhs.shanchu.setVisibility(View.VISIBLE);// ����������Ҫ����
			vhs.shanchu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					list.remove(position);// �����Դ��ɾ��
					notifyDataSetChanged();// ֪ͨ����������ı�
					// ���÷������ڷ������н���ɾ�����
					getdata(list.get(position).getCart_id());
				}
			});
			vhs.jiageheshuliang.setVisibility(View.GONE);
			vhs.chicun.setText(list.get(position).getSpec_id());
			vhs.yanse.setText(list.get(position).getSpec_id());
			vhs.num.setText(list.get(position).getGoods_num());
			final TextView tv = vhs.num;
			vhs.add.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int i = Integer.parseInt(tv.getText().toString());
					i++;
					list.get(position).setGoods_num(i + "");
					tv.setText(i + "");
					getdataadd(list.get(position).getCart_id(), 1 + "");
				}
			});
			vhs.jian.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int i = Integer.parseInt(tv.getText().toString());
					if (i == 1) {
						list.get(position).setGoods_num(i + "");
						tv.setText(i + "");
					} else {
						i--;
						tv.setText(i + "");
						list.get(position).setGoods_num(i + "");
						getdatajianqu(list.get(position).getCart_id(), 1 + "");
					}

				}
			});

		} else {
			vhs.tubiao.setVisibility(View.GONE);// ͼ����Ҫ����
			vhs.bitoti.setVisibility(View.VISIBLE);// ������Ҫ��ʾ
			vhs.anniu.setVisibility(View.GONE);// ��ť������Ҫ���أ�
			vhs.shanchu.setVisibility(View.GONE);
			vhs.jiageheshuliang.setVisibility(View.VISIBLE);
			BitmapUtils bitmapUtils = new BitmapUtils(context);
			bitmapUtils.display(vhs.img, list.get(position).getGoods_image());
			vhs.title.setText(list.get(position).getGoods_name());
			vhs.chicun.setText(list.get(position).getSpec_id());
			vhs.yanse.setText(list.get(position).getSpec_id());
			vhs.jiage.setText(list.get(position).getGoods_price());
			vhs.jiage.setTextColor(Color.RED);
			vhs.shuliang.setText(list.get(position).getGoods_num());
		}

		return convertView;

	}

	static class viewholder {
		CheckBox tubiao;// ѡ��ͼ��
		ImageView img;// ��ƷͼƬ
		ImageView add, jian;// �Ӽ���ť
		TextView title;// ����
		TextView chicun, yanse;
		TextView jiage;
		TextView num;
		TextView shuliang;
		LinearLayout bitoti;
		LinearLayout anniu;
		RelativeLayout shanchu;
		RelativeLayout jiageheshuliang;
	}

	private void getdata(String id) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "cart_delete");
		params.addBodyParameter("cart_id", id);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, "http://www.91jf.com/api.php",
				params, new RequestCallBack<String>() {

					@Override
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
						} else {
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String str = responseInfo.result;
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}

	// CheckBoxѡ��ı������
	private final class CheckBoxChangedListener implements
			OnCheckedChangeListener {
		public void onCheckedChanged(CompoundButton cb, boolean flag) {
			int position = (Integer) cb.getTag();
			getIsSelected().put(position, flag);
			BuyCartBean bean = list.get(position);
			bean.setChoosed(flag);
		}
	}

	private void getdataadd(String id, String num) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "cart_add_num");
		params.addBodyParameter("cart_id", id);
		params.addBodyParameter("num", num);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, "http://www.91jf.com/api.php",
				params, new RequestCallBack<String>() {

					@Override
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
						} else {
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String str = responseInfo.result;
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}
	private void getdatajianqu(String id, String num) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "cart_sub_num");
		params.addBodyParameter("cart_id", id);
		params.addBodyParameter("num", num);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, "http://www.91jf.com/api.php",
				params, new RequestCallBack<String>() {

					@Override
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
						} else {
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String str = responseInfo.result;
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}

	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-27 ����10:13:48 ��ݹ��id����ȡ��Ʒ�ĳߴ��Լ���ɫ
	 */
	private void getdatachicun(String id, TextView chicun, TextView yanse) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "cart_sub_num");
		params.addBodyParameter("cart_id", id);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST, "http://www.91jf.com/api.php",
				params, new RequestCallBack<String>() {

					@Override
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
						} else {
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// ����ɹ�
						String str = responseInfo.result;
					}

					@Override
					public void onFailure(HttpException error, String msg) {
					}
				});
	}

}
