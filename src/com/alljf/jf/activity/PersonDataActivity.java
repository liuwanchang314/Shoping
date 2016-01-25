package com.alljf.jf.activity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Application.SysApplication;
import com.Extension.RoundImageView;
import com.alljf.jf.R;
import com.bean.PersonDataBean;
import com.jsonParser.PersonDataJsonprser;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.utils.writeDateToSdCard;


/**
 * @author JZKJ-LWC
 * @date : 2015-12-9 下午1:33:15
 * 个人资料界面
 */  

public class PersonDataActivity extends Activity implements OnClickListener{
	
	private ImageView mBack,mHome;
	private RoundImageView mHeadPic;//自定义圆形头像
	private TextView mPhone;//电话
	private TextView mEmail;//邮箱
	private LinearLayout mAnquan;//安全信息
	private LinearLayout mPayinfo;//支付
	private LinearLayout mCard;//名片
	private String UserName;
	private Uri originalUri;
	private byte[] mContent;
	private String cachePath;
	private File file;
	private Bitmap myBitmap=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_persondata);
		SysApplication.getInstance().addActivity(this);
		initview();
		cachePath = Environment.getExternalStorageDirectory() + "/cacheImage";
		UserName=getUserName();
		Log.i("获取到username没有",UserName);
		GetData();
	}
	/**
	 * @author JZKJ-LWC
	 * @date : 2015-12-14 下午9:56:46
	 * 获取登陆数据
	 */  
	private void GetData() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		params.addBodyParameter("id", "8d7d8ee069cb0cbbf816bbb65d56947e");
		params.addBodyParameter("key", "71d1dd35b75718a722bae7068bdb3e1a");
		params.addBodyParameter("type", "user");
		params.addBodyParameter("part", "get_userinfo");
		params.addBodyParameter("username", UserName);
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
		        	final PersonDataBean bean=PersonDataJsonprser.getbean(str);
		        	mPhone.setText(bean.getTel());
		        	mEmail.setText(bean.getEmail());
		        	String imageurl=bean.getHead_img();
		        	if(imageurl.equals("")||imageurl==null){
		        		//不做任何操作，选择默认图片
		        	}else{
		        		BitmapUtils bitmapUtils=new BitmapUtils(PersonDataActivity.this);
		        		bitmapUtils.display(mHeadPic, imageurl);
		        	}
		        	mCard.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(PersonDataActivity.this,TwoDimensionCodeActivity.class);
							intent.putExtra("bean",bean);
							startActivity(intent);
						}
					});
		        }
		        @Override
		        public void onFailure(HttpException error, String msg) {
		        }
		});
	}

	//获取用户用户名
	private String getUserName() {
		return SysApplication.getInstance().getUserInfo().getName();
	}
	@SuppressLint("NewApi")
	private void initview() {
		// TODO Auto-generated method stub
		mBack=(ImageView) findViewById(R.id.persondata_back);
		mBack.setOnClickListener(this);
		mHome=(ImageView) findViewById(R.id.persondata_home);
		mHome.setOnClickListener(this);
		mHeadPic=(RoundImageView) findViewById(R.id.persondata_touxiang);
		mHeadPic.setOnClickListener(this);
		mPhone=(TextView) findViewById(R.id.persondata_shoujihao);
		mEmail=(TextView) findViewById(R.id.persondata_dianziyouxiang);
		mAnquan=(LinearLayout) findViewById(R.id.persondata_anquanxinxi);
		mAnquan.setOnClickListener(this);
		mPayinfo=(LinearLayout) findViewById(R.id.persondata_zhifumima);
		mPayinfo.setOnClickListener(this);
		mCard=(LinearLayout) findViewById(R.id.persondata_mingpian);
		mCard.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.persondata_back://返回
			PersonDataActivity.this.finish();
			break;
		case R.id.persondata_home://zhuye 
			PersonDataActivity.this.finish();
			startActivity(new Intent(PersonDataActivity.this, MainActivity.class));
			break;
		case R.id.persondata_anquanxinxi://安全
			Intent intent=new Intent(PersonDataActivity.this, ChangeSafetyActivity.class);
			intent.putExtra("username", getUserName());
			startActivity(intent);
			break;
		case R.id.persondata_zhifumima://密码
			startActivity(new Intent(PersonDataActivity.this,PayPassWordFindBackActivity.class ));
			break;
		case R.id.persondata_touxiang:
			final CharSequence[] items3 =
		{ "相册", "拍照" };
			AlertDialog dlg3 = new AlertDialog.Builder(PersonDataActivity.this).setTitle("选择图片").setItems(items3,
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
								startActivityForResult(getImage,0);
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
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,UserName+".jpg",mContent);
					file=new File(cachePath+"/"+UserName+".jpg");
					// 将字节数组转换为ImageView可调用的Bitmap对象
//					myBitmap = getPicFromBytes(mContent, null);
//					// //把得到的图片绑定在控件上显示
//					mXuanzezhaopian.setImageBitmap(myBitmap);
					myBitmap=decodeFile(file);
					mHeadPic.setImageBitmap(myBitmap);
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
					boolean b=writeDateToSdCard.writeDateTosdcard(cachePath,UserName+".jpg",mContent);
					file=new File(cachePath+"/"+UserName+".jpg");
					// 把得到的图片绑定在控件上显示
					myBitmap=decodeFile(file);
					mHeadPic.setImageBitmap(myBitmap);
				}
				
			} catch ( Exception e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
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



