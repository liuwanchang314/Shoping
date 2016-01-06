/**
 * 选择图片方式.
 * @author intBird 20140213.
 * 
 */
package com.chooseimg;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.alljf.jf.R;



public class BarChooseImg extends Activity {

	public static String cameraTakedImgUrl = "";
	public static String info = "", param = "", title = "";
	public static int maxImg = 1, maxWidth = 100, requestCode = 3, maxHeight = 100;//
	public boolean isShow = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bar_choose_img);
//		initActivity("图片选择方式", 2, 0, 0, );
		init();
	}

	@Override
	public void onBackPressed() {
		closePanel();
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		closePanel();
	}

	private void init() {
		// 调用示例--
		// Intent intent=new Intent();
		// Bundle bundle=new Bundle();
		// bundle.putInt("maxImg", 5);
		// bundle.putInt("maxWidth", 5);
		// bundle.putInt("maxHeight", 5);
		// bundle.putString("param", "");
		// intent.putExtra("param", bundle);
		// intent.setClass(this, BarChooseImgAct.class);
		// --
		Bundle bundle = getIntent().getExtras().getBundle("param");
//		BarChooseImg.requestCode = bundle.getInt("requestCode");
		BarChooseImg.info = bundle.getString("info");
		BarChooseImg.maxImg = bundle.getInt("maxImg");
		BarChooseImg.maxWidth = bundle.getInt("maxWidth");
		BarChooseImg.maxHeight = bundle.getInt("maxHeight");
		BarChooseImg.param = bundle.getString("param");
		title = bundle.getString("title");
		TextView titleView = (TextView) findViewById(R.id.title_text);
		if (title != null)
			titleView.setText(title);
		// 打开相机
		findViewById(R.id.camera).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openCamera(BarChooseImg.this);
			}
		});
		
		//点击根布局后,页面finish;
		findViewById(R.id.view_camera).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// 打开相册选图片
		findViewById(R.id.camera_files).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openImgChoose();
			}
		});

		// 取消
		findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closePanel();
			}
		});
	}

	public void closePanel() {
		BarChooseImg.this.finish();
		isShow = false;
	}

	public void clearParam() {
		param = "";
		maxImg = 1;
		maxWidth = maxHeight = 100;
	}

	/**
	 * 选择图片等按钮事件
	 */
	public static void openCamera(Activity act) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			// 创建一个新的图片文件;
			try {
				// 图片地址;
				cameraTakedImgUrl = Environment.getExternalStorageDirectory() + "/cache/" + System.currentTimeMillis() + ".jpg";
				File file = new File(Environment.getExternalStorageDirectory() + "/cache/");
				
				if(!file.exists())
					file.mkdirs();
				File imgFile = new File(cameraTakedImgUrl);
				imgFile.createNewFile();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 存储图片,查看谷歌官方文档说明;
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imgFile));

				act.startActivityForResult(intent, requestCode);
			} catch (IOException e) {
//				e.printStackTrace();
//				StringManager.reportError("相机拍照", e);
			}
		} else {
			Toast.makeText(act, "请插入sd卡再拍照", Toast.LENGTH_LONG).show();
//			Tools.showToast(act, "请插入sd卡再拍照");
			return;
		}
		// closePanel();
	}

	public void openImgChoose() {
		if (maxImg > 1) {
			Intent intent = new Intent(BarChooseImg.this, ChooseImage.class);
			startActivityForResult(intent, maxImg);
		} else {
			Intent intent = new Intent();
			// 图片类型
			intent.setType("image/*");
			// 允许用户选择特殊种类(如照片、录音)的数据，并返回
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent, requestCode);
		}
		// closePanel();
	}
}
