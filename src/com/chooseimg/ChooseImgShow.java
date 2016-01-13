///**
// * 根据地址,查看图片.
// * @author intBird 20140213.
// * 
// */
//package com.chooseimg;
//
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.Window;
//import android.widget.ImageView;
//
//import com.alljf.jf.R;
//import com.utils.ToolsDevice;
//
//@SuppressLint("NewApi")
//public class ChooseImgShow extends Activity {
//	private ImageView imageView;
//	private Handler handler;
//	private int mWidth, mHeight;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.choose_img_show);
////		setCommonStyle();
//		
////		rl.removeView(viewTouYing);
//		init();
//	}
//	@Override
//	public void onBackPressed() {
//		//清理View
////		RecycleBitmap.recycleImageView(imageView);
//		ChooseImage.lastActivityIsShow=true;
//		finish();
//	}
//	
//	private void init() {
//		// 获取屏幕大小,图片填满屏幕;
//		mWidth = ToolsDevice.getWindowPx(this).widthPixels;
//		mHeight = ToolsDevice.getWindowPx(this).heightPixels;
//		
//		imageView = (ImageView) findViewById(R.id.iv_showChoosedImgs);
//		imageView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				ChooseImage.lastActivityIsShow=true;
//				finish();
//			}
//		});
////		recycleViews.put(imageView, null);
////		loadImage();
//	}
//
////	private void loadImage() {
//////		progressBar.setVisibility(View.VISIBLE);
////		handler = new Handler() {
////			@Override
////			public void handleMessage(Message msg) {
////				if (msg.what == 2) {
//////					progressBar.setVisibility(View.GONE);
////					Bitmap bitmap=(Bitmap) msg.obj;
////					imageView.setImageBitmap(bitmap);
////				}
////				else{
////					Toast.makeText(ChooseImgShow.this, "加载大图失败", Toast.LENGTH_LONG).show();
//////					Tools.showToast(ChooseImgShow.this, "加载大图失败");
////					ChooseImage.lastActivityIsShow=true;
////					finish();
////				}
////			}
////		};
////
////		new Thread(new Runnable() {
////
////			@Override
////			public void run() {
////				Message msg = new Message();
////				msg.what = 2;
////				if(getIntent().getExtras().get("url")!=null){
////					String url=getIntent().getExtras().get("url").toString();
////					if(url.indexOf("http")==0){
////						Bitmap bitmap=ReqInternet.loadImageFromUrl(url, null, FileUtil.save_cache);
////						if(bitmap==null) msg.what=0;
////						else msg.obj = bitmap;
////					}
////					else{
////						msg.obj = ToolsImage.imgPathToBitmap(getIntent().getExtras()
////								.get("url").toString(), mWidth, mHeight,false);
////					}
////				}
////				else msg.what=0;
////				handler.sendMessage(msg);
////			}
////		}).start();
////	}
//}
