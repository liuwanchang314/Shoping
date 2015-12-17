package com.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activity.BuyActivity;
import com.activity.R;
import com.bean.BuyCartBean;
import com.bean.CompanyNoticeBean;
import com.jsonParser.BuyCartJsonP;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.R.integer;
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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BuyCartAdapter extends BaseAdapter {
	
	private List<BuyCartBean> list;
	private Context context;
	private static boolean IsChange=false;//������¼��ʾlistview�Ĳ����Ƿ���Ҫ�����ı�
	private static boolean IsChoise=false;//������¼��ʾitem�еĵ�randobutton�Ƿ�ѡ��
	private static boolean istag=false;
	private TextView total;
	private double totalprice=0.0;
	Double price;
	Double number;
	
	public BuyCartAdapter(List<BuyCartBean> list,Context context,TextView total){
		
		this.list=list;
		this.context=context;
		this.total=total;
	}

	//�÷���������̬֪ͨlistview�����Լ�������ʾ�����ı�
	public static void isshow(){
		IsChange=true;
	}

	//�÷���������̬֪ͨlistview�����Լ�������ʾ�����ı�
	public static void unshow(){
		IsChange=false;
	}
	
	//������̬֪ͨ�༭��ʱ��randiobutton�Ƿ�ѡ��
	public static void isChoice(){
		IsChoise=true;
	}
	//������̬֪ͨ�༭��ʱ��randiobutton�Ƿ�ѡ��
	public static void unChoice(){
		IsChoise=false;
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
		if(convertView==null){
			vhs=new viewholder();
			LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView=inflater.inflate(R.layout.buyactivity_listview_item1, null);
			vhs.img=(ImageView) convertView.findViewById(R.id.buy_image);
			vhs.title=(TextView) convertView.findViewById(R.id.buy_biaoti);
			vhs.chicun=(TextView) convertView.findViewById(R.id.buy_chicun);
			vhs.yanse=(TextView) convertView.findViewById(R.id.buy_yanse);
			vhs.jiage=(TextView) convertView.findViewById(R.id.buy_jiage);
			vhs.shuliang=(TextView) convertView.findViewById(R.id.buy_shuliang);
			//���濪ʼ��ȡ��Ҫ���صĽ���
			vhs.tubiao=(CheckBox) convertView.findViewById(R.id.buy_yincang_tubiao);
			vhs.bitoti=(LinearLayout) convertView.findViewById(R.id.buy_yincang_biaoti);
			vhs.anniu=(LinearLayout) convertView.findViewById(R.id.buy_yincang_anniu);
			vhs.shanchu=(RelativeLayout) convertView.findViewById(R.id.buy_yingcang_shanchu);
			vhs.jiageheshuliang=(RelativeLayout) convertView.findViewById(R.id.buy_yingcang_jiageheshuliang);
			//������������
			vhs.add=(ImageView) convertView.findViewById(R.id.buy_jia);
			vhs.jian=(ImageView) convertView.findViewById(R.id.buy_jian);
			vhs.num=(TextView) convertView.findViewById(R.id.buy_num);
			vhs.num.setTag(position);
			convertView.setTag(vhs);
		}else{
			vhs=(viewholder) convertView.getTag();
		}
		
		if(IsChange){
			//���ȣ�ͼ����Ҫ��ʾ����
			BitmapUtils bitmapUtils=new BitmapUtils(context);
			bitmapUtils.display(vhs.img,list.get(position).getGoods_image());
			vhs.tubiao.setVisibility(View.VISIBLE);//ͼ����Ҫxianshi
			if(IsChoise){
				//Ϊȫѡʱ��
				vhs.tubiao.setChecked(true);
			}else{
				//Ϊ��ȫѡʱ��
				vhs.tubiao.setChecked(false);
				
			}
//			price=Double.parseDouble((String) vhs.jiage.getText());
//			number=Double.parseDouble((String) vhs.num.getText());
			vhs.tubiao.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					//��radiobutton��ѡ����ʱ��������Ҫ��������item����Ʒ�۸�Ȼ��֪ͨ��activity��
					if(isChecked){
						Toast.makeText(context, "��ѡ����",1).show();
						//���÷���������item�ļ۸񴫵ݹ�ȥ��ѡ���ͼӼ�
						//�ȸ��ݼ۸���м����ܼ�
//						double totals=price*number;
//						totalprice=totalprice+totals;
//						total.setText(totalprice+"");
					}else{
						Toast.makeText(context, "��ȡ������",1).show();
						//���÷��������м��۴���
						
					}
				}
			});
			vhs.bitoti.setVisibility(View.GONE);//������Ҫ����
			vhs.anniu.setVisibility(View.VISIBLE);//��ť������Ҫ��ʾ
			vhs.shanchu.setVisibility(View.VISIBLE);//����������Ҫ����
			vhs.shanchu.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
						//ɾ���������
					Toast.makeText(context, "��ɾ������",1).show();
					list.remove(position);//������Դ��ɾ��
					notifyDataSetChanged();//֪ͨ�����������ı�
					//���÷������ڷ������н���ɾ������
					getdata(list.get(position).getCart_id());
				}
			});
			vhs.jiageheshuliang.setVisibility(View.GONE);
			vhs.chicun.setText(list.get(position).getSpec_id());
			vhs.yanse.setText(list.get(position).getSpec_id());
			vhs.num.setText(list.get(position).getGoods_num());
			//���Ӽ���ť�������ʱ�򣬿�ʼ�����仯
			final TextView tv=vhs.num;
			vhs.add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "..��.",1).show();
					int i=Integer.parseInt(tv.getText().toString());
					i++;
					tv.setText(i+"");
				}
			});
			vhs.jian.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Toast.makeText(context, "..��..",1).show();
								int i=Integer.parseInt(tv.getText().toString());
								if(i==1){
									tv.setText(i+"");
								}else{
									i--;
									tv.setText(i+"");
								}
								
							}
						});
			
		}else{
			//Ĭ��Ϊfalse��Ҳ���ǽ���������ʾ	
			vhs.tubiao.setVisibility(View.GONE);//ͼ����Ҫ����
			vhs.bitoti.setVisibility(View.VISIBLE);//������Ҫ��ʾ
			vhs.anniu.setVisibility(View.GONE);//��ť������Ҫ���أ�
			vhs.shanchu.setVisibility(View.GONE);
			vhs.jiageheshuliang.setVisibility(View.VISIBLE);
			BitmapUtils bitmapUtils=new BitmapUtils(context);
			bitmapUtils.display(vhs.img,list.get(position).getGoods_image());
			vhs.title.setText(list.get(position).getGoods_name());
			vhs.chicun.setText(list.get(position).getSpec_id());
			vhs.yanse.setText(list.get(position).getSpec_id());
			vhs.jiage.setText(list.get(position).getGoods_price());
			vhs.jiage.setTextColor(Color.RED);
			vhs.shuliang.setText(list.get(position).getGoods_num());
		}
		
		return convertView;
		
	}
	
	static class viewholder{
		CheckBox tubiao;//ѡ��ͼ��
		ImageView img;//��ƷͼƬ
		ImageView add,jian;//�Ӽ���ť
		TextView title;//����
		TextView chicun,yanse;
		TextView jiage;
		TextView num;
		TextView shuliang;
		LinearLayout bitoti;
		LinearLayout anniu;
		RelativeLayout shanchu;
		RelativeLayout jiageheshuliang;
	}
	
	//��ȡ����
		private void getdata(String id) {
			// TODO Auto-generated method stub
			RequestParams params = new RequestParams();
			// ֻ�����ַ�������ʱĬ��ʹ��BodyParamsEntity��
			params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
			params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
			params.addBodyParameter("type", "order");
			params.addBodyParameter("part", "cart_delete");
			params.addBodyParameter("cart_id", id);
			HttpUtils http = new HttpUtils();
			http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

			        @Override
			        public void onStart() {
			        	//��ʼ����
			        }

			        @Override
			        public void onLoading(long total, long current, boolean isUploading) {
			            if (isUploading) {
			            } else {
			            }
			        }

			        @Override
			        public void onSuccess(ResponseInfo<String> responseInfo) {
			        	//����ɹ�
			        	String str=responseInfo.result;
			        	Log.i("ɾ������������", str);
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			        }
			});
		}


}
