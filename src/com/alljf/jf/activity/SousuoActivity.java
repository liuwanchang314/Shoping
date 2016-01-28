package com.alljf.jf.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Application.SysApplication;
import com.adapter.MyAllproductAdapter;
import com.alljf.jf.R;
import com.jsonParser.AllProductDataJson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.utils.writeDateToSdCard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2016-1-11 下午10:22:45 
 * 类说明 
 */
public class SousuoActivity extends Activity {
	private EditText mNeirong;
	private ImageView mXiangji;
	private ImageView mSousuo;
	private ImageView mBack,mHome;
	private Bitmap myBitmap=null;
	private byte[] mContent;
	private Uri originalUri;
	private File file;
	private String cachePath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sousuo);
		SysApplication.getInstance().addActivity(this);
		cachePath = Environment.getExternalStorageDirectory() + "/cacheImage";;
		initview();
		initdata();
		Intent intent=getIntent();
		String inString=intent.getStringExtra("data");
		mNeirong.setText(inString);
	}
	private void initdata() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @2016-1-11下午10:28:23
	 */
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.sousuo_back);
		mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SousuoActivity.this.finish();
			}
		});
		mHome=(ImageView) findViewById(R.id.sousuo_home);
		mHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SousuoActivity.this.finish();
			}
		});
		mNeirong=(EditText) findViewById(R.id.sousuo_search);
		mXiangji=(ImageView) findViewById(R.id.sousuo_xiangji);
		mXiangji.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final CharSequence[] items =
					{ "相册", "拍照" };
						AlertDialog dlg = new AlertDialog.Builder(SousuoActivity.this).setTitle("选择图片").setItems(items,
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
			}
		});
		mSousuo=(ImageView) findViewById(R.id.sousuo_sousuo);
		mSousuo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getdata();
			}

			
		});
		
	}
	private void getdata() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "goods");
		params.addBodyParameter("part", "get_goods_nokey");
		params.addBodyParameter("search_goodsname",mNeirong.getText().toString());
		params.addBodyParameter("goods_class", "");
		params.addBodyParameter("order", "time");
		params.addBodyParameter("limit", "");
		params.addBodyParameter("limit_start", "1");
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
		        	Log.i("网络请求下来的参数是",str);
		        	try {
						JSONObject obj=new JSONObject(str);
						String status=obj.getString("status");
						if(status.equals("1")){
							//说明请求成功了
							JSONArray array=obj.getJSONArray("data");
							if(array.length()==0){
								//说明无数据
								Toast.makeText(SousuoActivity.this,"对不起，您输入的内容不支持搜索",1).show();
							}else{
								Intent intent=new Intent(SousuoActivity.this,AllProductActivity.class);
								intent.putExtra("data",str);
								startActivity(intent);
							}
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
			if(data==null){
				
			}else{
				Toast.makeText(SousuoActivity.this,"已存储", 1).show();
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
//						myBitmap = getPicFromBytes(mContent, null);
//						// //把得到的图片绑定在控件上显示
//						mXuanzezhaopian.setImageBitmap(myBitmap);
						myBitmap=decodeFile(file);
					}
				
				} catch ( Exception e )
				{
					System.out.println(e.getMessage());
				}
			}


		} else if (requestCode == 1)
		{
			if(data==null){
				
			}else{
				Toast.makeText(SousuoActivity.this,"已存储", 1).show();
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
					}
					
				} catch ( Exception e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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
