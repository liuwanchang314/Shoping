package com.alljf.jf.activity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.adapter.SubjectAdapter;
import com.alljf.jf.R;
import com.bean.OrderBean;
import com.bean.SubjectBean;
import com.example.sportsdialogdemo.dialog.SpotsDialog;
import com.jsonParser.SubjectJsonparser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.utils.writeDateToSdCard;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2015-12-29 下午11:07:38 
 * 类说明 
 */
public class TousuActivity extends Activity implements OnClickListener{
	private ImageView mback,mhome;
	/**
	 * 选择投诉的按钮
	 */
	private RelativeLayout mXuanzetousu;
	private String SubjectId=new String();
	private ImageView mGoodsImage;
	private TextView mGoodsName;
	private TextView mGoodsPrice;
	private TextView mGoodsNum;
	private ImageView mXuanzezhaopian,mXuanzezhaopian2,mXuanzezhaopian3;
	private Bitmap myBitmap=null;
	private byte[] mContent;
	private TextView mTousuzhuti;
	private TextView mTousuTijiao;
	private OrderBean bean;
	private EditText mYijianlan;
	private String cachePath;
	private Uri originalUri;
	private SpotsDialog mdialog;
	private File file;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_tousu);
		cachePath = Environment.getExternalStorageDirectory() + "/cacheImage";;
		SysApplication.getInstance().addActivity(this);
		initview();
		Intent intent=getIntent();
		bean=(OrderBean) intent.getSerializableExtra("bean");
		getdata(bean.getOrdergoods().getSpec_id());
		BitmapUtils bmp=new BitmapUtils(TousuActivity.this);
		bmp.display(mGoodsImage,bean.getOrdergoods().getGoods_image());
		mGoodsName.setText(bean.getOrdergoods().getGoods_name());
		mGoodsNum.setText(bean.getOrdergoods().getGoods_num());
		mGoodsPrice.setText(bean.getOrdergoods().getGoods_pay_price());
	}
	/**
	 * @2015-12-30下午6:12:20
	 * @author JZKJ-LWC
	 * 初始化
	 */
	private void initview() {
		// TODO Auto-generated method stub
		SpotsDialog.TAG=R.style.SpotsDialogDefault;
		mdialog=new SpotsDialog(TousuActivity.this);
		mback=(ImageView) findViewById(R.id.tousu_back);
		mback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TousuActivity.this.finish();
			}
		});
		mhome=(ImageView) findViewById(R.id.tousu_home);
		mhome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TousuActivity.this.finish();
				startActivity(new Intent(TousuActivity.this,MainActivity.class));
			}
		});
		mXuanzetousu=(RelativeLayout) findViewById(R.id.tousu_xuanzezhuti);
		mXuanzetousu.setOnClickListener(this);
		mGoodsImage=(ImageView) findViewById(R.id.tousu_goods_image);
		mGoodsName=(TextView) findViewById(R.id.tousu_goods_title);
		mGoodsPrice=(TextView) findViewById(R.id.tousu_shangpinjiage);
		mGoodsNum=(TextView) findViewById(R.id.tousu_shangpinshuliang);
		mXuanzezhaopian=(ImageView) findViewById(R.id.tousu_dianjixuanzezhaopian);
		mXuanzezhaopian.setOnClickListener(this);
		mXuanzezhaopian2=(ImageView) findViewById(R.id.tousu_dianjixuanzezhaopian2);
		mXuanzezhaopian2.setOnClickListener(this);
		mXuanzezhaopian3=(ImageView) findViewById(R.id.tousu_dianjixuanzezhaopian3);
		mXuanzezhaopian3.setOnClickListener(this);
		mTousuzhuti=(TextView) findViewById(R.id.tousu_zhuti);
		mTousuTijiao=(TextView) findViewById(R.id.tousu_tijiao);
		mTousuTijiao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//调用方法，进行投诉
				String orderid=bean.getOrder_id();
				String username=SysApplication.getInstance().getUserInfo().getName();
				String subid=SubjectId;
				String subcon=mYijianlan.getText().toString();
				String goodsid=bean.getOrdergoods().getGoods_id();
//				File file=new File(originalUri.toString());
				if(subid.equals("")){
					Toast.makeText(TousuActivity.this,"请选择投诉主题", 1).show();
				}else{
//					if(originalUri==null){
//					}else{
//						getdaTousu(orderid,username,subid,subcon,goodsid);
//					}
					getdaTousu(orderid,username,subid,subcon,goodsid);
					
				}
				
			}
		});
		mYijianlan=(EditText) findViewById(R.id.tousu_yijianlan);
	}
	
	/**
	 * @2015-12-30下午6:15:13
	 * @author JZKJ-LWC
	 * 根据specid来获取真正的颜色值与尺寸值
	 */
	public void getdata(String id){
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_spec_main");
		params.addBodyParameter("spec_id", id);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        	mdialog.show();
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
		        	mdialog.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("获取的规格id是", str);
		        }

		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tousu_xuanzezhuti:
			//调用方法，获取主题
			RequestParams params = new RequestParams();
			// 只包含字符串参数时默认使用BodyParamsEntity，
			params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
			params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
			params.addBodyParameter("type", "system");
			params.addBodyParameter("part", "complain_subject_nokey");
			HttpUtils http = new HttpUtils();
			http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

			        @Override
			        public void onStart() {
			        	//开始请求
			        	SpotsDialog.TAG=R.style.SpotsDialogDefault;
			        	mdialog.show();
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
			        	mdialog.dismiss();
			        	String str=responseInfo.result;
			        	Log.i("投诉获取的数据是", str);
			        	final List<SubjectBean> list=SubjectJsonparser.getlist(str);
			        	 final Dialog dialog = new Dialog(TousuActivity.this, R.style.Theme_Light_Dialog);
				            View dialogView = LayoutInflater.from(TousuActivity.this).inflate(R.layout.subjectwindow,null);
				            //获得dialog的window窗口
				            Window window = dialog.getWindow();
				            //设置dialog在屏幕底部
				            window.setGravity(Gravity.BOTTOM);
				            //设置dialog弹出时的动画效果，从屏幕底部向上弹出
				            window.setWindowAnimations(R.style.dialogStyle);
				            window.getDecorView().setPadding(0, 0, 0, 0);
				            //获得window窗口的属性
				            android.view.WindowManager.LayoutParams lp = window.getAttributes();
				            //设置窗口宽度为充满全屏
				            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
				            //设置窗口高度为包裹内容
				            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
				            //将设置好的属性set回去
				            window.setAttributes(lp);
				            //将自定义布局加载到dialog上
				            dialog.setContentView(dialogView);
				            dialog.show();
				            SubjectAdapter adapter=new SubjectAdapter(list, TousuActivity.this);
				            ListView lv=(ListView) dialogView.findViewById(R.id.subjectlistview);
				            lv.setAdapter(adapter);
				            lv.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int arg2, long arg3) {
									// TODO Auto-generated method stub
									SubjectId=list.get(arg2).getSubject_id();
									mTousuzhuti.setText(list.get(arg2).getSubject_content());
									dialog.dismiss();
								}
							});
			        
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			        }
			});
			break;
		case R.id.tousu_dianjixuanzezhaopian:
			final CharSequence[] items =
		{ "相册", "拍照" };
			AlertDialog dlg = new AlertDialog.Builder(TousuActivity.this).setTitle("选择图片").setItems(items,
					new DialogInterface.OnClickListener()
					{
						public void onClick ( DialogInterface dialog , int item )
						{
							// 这里item是根据选择的方式，
							// 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
							if (item == 1)
							{
								Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
								startActivityForResult(getImageByCamera, 1);
							} else
							{
								Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
								getImage.addCategory(Intent.CATEGORY_OPENABLE);
								getImage.setType("image/jpeg");
								startActivityForResult(getImage, 0);
							}
						}
					}).create();
			dlg.show();
			
			break;
		case R.id.tousu_dianjixuanzezhaopian2:
			final CharSequence[] items2 =
		{ "相册", "拍照" };
			AlertDialog dlg2 = new AlertDialog.Builder(TousuActivity.this).setTitle("选择图片").setItems(items2,
					new DialogInterface.OnClickListener()
					{
						public void onClick ( DialogInterface dialog , int item )
						{
							// 这里item是根据选择的方式，
							// 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
							if (item == 1)
							{
								Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
								startActivityForResult(getImageByCamera, 2);
							} else
							{
								Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
								getImage.addCategory(Intent.CATEGORY_OPENABLE);
								getImage.setType("image/jpeg");
								startActivityForResult(getImage, 22);
							}
						}
					}).create();
			dlg2.show();
			
			break;
		case R.id.tousu_dianjixuanzezhaopian3:
			final CharSequence[] items3 =
		{ "相册", "拍照" };
			AlertDialog dlg3 = new AlertDialog.Builder(TousuActivity.this).setTitle("选择图片").setItems(items3,
					new DialogInterface.OnClickListener()
					{
						public void onClick ( DialogInterface dialog , int item )
						{
							// 这里item是根据选择的方式，
							// 在items数组里面定义了两种方式，拍照的下标为1所以就调用拍照方法
							if (item == 1)
							{
								Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
								startActivityForResult(getImageByCamera, 3);
							} else
							{
								Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
								getImage.addCategory(Intent.CATEGORY_OPENABLE);
								getImage.setType("image/jpeg");
								startActivityForResult(getImage,33);
							}
						}
					}).create();
			dlg3.show();
			
			break;

		default:
			break;
		}
	}
	@ Override
	protected void onActivityResult ( int requestCode , int resultCode , Intent data )
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		ContentResolver resolver = getContentResolver();
		/**
		 * 因为两种方式都用到了startActivityForResult方法，
		 * 这个方法执行完后都会执行onActivityResult方法， 所以为了区别到底选择了那个方式获取图片要进行判断，
		 * 这里的requestCode跟startActivityForResult里面第二个参数对应
		 */
		if (requestCode == 0)
		{
			try
			{
				// 获得图片的uri
				originalUri = data.getData();
				if(originalUri==null){
					
				}else{
					// 将图片内容解析成字节数组
					mContent = readStream(resolver.openInputStream(Uri.parse(originalUri.toString())));
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,"123456.jpg",mContent);
					file=new File(cachePath+"/"+"123456.jpg");
					// 将字节数组转换为ImageView可调用的Bitmap对象
//					myBitmap = getPicFromBytes(mContent, null);
//					// //把得到的图片绑定在控件上显示
//					mXuanzezhaopian.setImageBitmap(myBitmap);
					myBitmap=decodeFile(file);
					mXuanzezhaopian.setImageBitmap(myBitmap);
				}
			
			} catch ( Exception e )
			{
				System.out.println(e.getMessage());
			}

		} else if (requestCode == 1)
		{
			try
			{
				super.onActivityResult(requestCode, resultCode, data);
				Bundle extras = data.getExtras();
				myBitmap = (Bitmap) extras.get("data");
				if(myBitmap==null){
					
				}else{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					myBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
					mContent = baos.toByteArray();
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,"123456.jpg",mContent);
					file=new File(cachePath+"/"+"123456.jpg");
					// 把得到的图片绑定在控件上显示
					myBitmap=decodeFile(file);
					mXuanzezhaopian.setImageBitmap(myBitmap);
				}
				
			} catch ( Exception e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (requestCode == 22)
		{
			try
			{
				// 获得图片的uri
				originalUri = data.getData();
				if(originalUri==null){
					
				}else{
					// 将图片内容解析成字节数组
					mContent = readStream(resolver.openInputStream(Uri.parse(originalUri.toString())));
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,"1234567.jpg",mContent);
					file=new File(cachePath+"/"+"1234567.jpg");
					// 将字节数组转换为ImageView可调用的Bitmap对象
//					myBitmap = getPicFromBytes(mContent, null);
//					// //把得到的图片绑定在控件上显示
//					mXuanzezhaopian.setImageBitmap(myBitmap);
					myBitmap=decodeFile(file);
					mXuanzezhaopian2.setImageBitmap(myBitmap);
				}
			
			} catch ( Exception e )
			{
				System.out.println(e.getMessage());
			}

		}else if (requestCode == 2)
		{
			try
			{
				super.onActivityResult(requestCode, resultCode, data);
				Bundle extras = data.getExtras();
				myBitmap = (Bitmap) extras.get("data");
				if(myBitmap==null){
					
				}else{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					myBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
					mContent = baos.toByteArray();
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,"1234567.jpg",mContent);
					file=new File(cachePath+"/"+"1234567.jpg");
					// 把得到的图片绑定在控件上显示
					myBitmap=decodeFile(file);
					mXuanzezhaopian2.setImageBitmap(myBitmap);
				}
				
			} catch ( Exception e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (requestCode == 33)
		{
			try
			{
				// 获得图片的uri
				originalUri = data.getData();
				if(originalUri==null){
					
				}else{
					// 将图片内容解析成字节数组
					mContent = readStream(resolver.openInputStream(Uri.parse(originalUri.toString())));
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,"12345678.jpg",mContent);
					file=new File(cachePath+"/"+"12345678.jpg");
					// 将字节数组转换为ImageView可调用的Bitmap对象
//					myBitmap = getPicFromBytes(mContent, null);
//					// //把得到的图片绑定在控件上显示
//					mXuanzezhaopian.setImageBitmap(myBitmap);
					myBitmap=decodeFile(file);
					mXuanzezhaopian3.setImageBitmap(myBitmap);
				}
			
			} catch ( Exception e )
			{
				System.out.println(e.getMessage());
			}

		}else if (requestCode == 3)
		{
			try
			{
				super.onActivityResult(requestCode, resultCode, data);
				Bundle extras = data.getExtras();
				myBitmap = (Bitmap) extras.get("data");
				if(myBitmap==null){
					
				}else{
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					myBitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
					mContent = baos.toByteArray();
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,"12345678.jpg",mContent);
					file=new File(cachePath+"/"+"12345678.jpg");
					// 把得到的图片绑定在控件上显示
					myBitmap=decodeFile(file);
					mXuanzezhaopian3.setImageBitmap(myBitmap);
				}
				
			} catch ( Exception e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * @2016-1-11下午9:12:58
	 * 进行投诉
	 * 
	 */
	private void getdaTousu(String orderid,String username,String subid,String subcon,String goodsid) {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "order");
		params.addBodyParameter("part", "do_complain");
		params.addBodyParameter("order_id", orderid);
		params.addBodyParameter("user_name", username);
		params.addBodyParameter("complain_subject", subid);
		params.addBodyParameter("complain_content",subcon);
		params.addBodyParameter("goods_values",goodsid);
		params.addBodyParameter("input_complain_pic1",file);
		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,"http://www.91jf.com/api.php",params,new RequestCallBack<String>() {

		        @Override
		        public void onStart() {
		        	//开始请求
		        	mdialog.show();
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
		        	mdialog.dismiss();
		        	String str=responseInfo.result;
		        	Log.i("投诉完毕以后的返回结果是", str);
		        	try {
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("status");
						if(status.equals("1")){
							Toast.makeText(TousuActivity.this,"投诉成功",1).show();
							TousuActivity.this.finish();
						}
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
	
	public static Bitmap getPicFromBytes ( byte[] bytes , BitmapFactory.Options opts )
	{
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}

	public static byte[] readStream ( InputStream inStream ) throws Exception
	{
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1)
		{
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;

	}
	
	/** 
	39.     * 把字节数组保存为一个文件  
	40.     * @EditTime 2007-8-13 上午11:45:56  
	41.     */   
	    public  File getFileFromBytes(byte[] b, String outputFile) {   
	        BufferedOutputStream stream = null;   
	        File file = null;   
	        try {   
	           file = new File(outputFile);   
	            FileOutputStream fstream = new FileOutputStream(file);   
            stream = new BufferedOutputStream(fstream);   
	            stream.write(b);   
	       } catch (Exception e) {   
	            e.printStackTrace();   
	        } finally {   
	            if (stream != null) {   
	                try {   
	                    stream.close();   
	                } catch (IOException e1) {   
	                    e1.printStackTrace();   
	               }   
	            }   
	        }   
	        return file;   
	   }   


	  //decodes image and scales it to reduce memory consumption

	    private Bitmap decodeFile(File f){

	       try {
	           //Decode image size
	           BitmapFactory.Options o = new BitmapFactory.Options();
	           o.inJustDecodeBounds = true;
	           BitmapFactory.decodeStream(new FileInputStream(f),null,o);
	           //The new size we want to scale to
	           final int REQUIRED_HEIGHT=800;
	           final int REQUIRED_WIDTH=480;
	           //Find the correct scale value. It should be the power of 2.
	           int width_tmp=o.outWidth, height_tmp=o.outHeight;
	           System.out.println(width_tmp+"  "+height_tmp);
	           Log.w("===", (width_tmp+"  "+height_tmp));
	           int scale=1;
	           while(true){
	               if(width_tmp/2<REQUIRED_WIDTH && height_tmp/2<REQUIRED_HEIGHT)
	                   break;
	               width_tmp/=2;
	               height_tmp/=2;
	               scale++;
	               Log.w("===", scale+"''"+width_tmp+"  "+height_tmp);
	           }
	           //Decode with inSampleSize
	           BitmapFactory.Options o2 = new BitmapFactory.Options();
	           o2.inSampleSize=scale;
	           return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
	       } catch (FileNotFoundException e) {}
	       return null;
	    }


	     

}
