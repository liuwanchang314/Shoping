///**
// * 浏览相机图片.
// * @author intBird 20140213.
// * 
// */
//package com.chooseimg;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import android.app.Activity;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.media.MediaScannerConnection;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.provider.BaseColumns;
//import android.provider.MediaStore;
//import android.provider.MediaStore.Images.Media;
//import android.provider.MediaStore.MediaColumns;
//import android.util.Log;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.acore.logic.RecycleBitmap;
//import com.acore.tools.ToolsImage;
//import com.alljf.jf.R;
//import com.utils.ToolsDevice;
//
//
//
//public class ChooseImage extends Activity {
//
//	/**
//	 * 如果重新打开时清空,则为当前路径下选择的图片数量;(默认不清空)为所有路径下选择的图片总数量;
//	 */
//	public static ArrayList<String> listAllChoosedUrl = new ArrayList<String>();
//	/**
//	 * 内存对象缓存
//	 */
//	public static LinkedHashMap<String, Bitmap> imgCache = new LinkedHashMap<String, Bitmap>();
//	/**
//	 * 需要打开的文件路径,多次打开路径时每次修改它即可;
//	 */
//	public static String currentPathYouGived = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera";
//	/**
//	 * 处理返回按键到哪个界面,选择相册和查看图片;
//	 */
//	public static boolean lastActivityIsFolder = false, lastActivityIsShow = false;
//	
//	private boolean isChoseTwo = false;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.choose_img);
////		initActivity("常用相册",2, 0, R.layout.c_view_bar_title_special_back, R.layout.choose_img);
//		init();
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		// 如果是从查看图片后返回过来的,那么不刷新页面
//		if (lastActivityIsShow) {
//			//progressBar.setVisibility(View.GONE);
//			lastActivityIsShow = false;
//		} else {
//			// 清空数据;
//			nextWork();
//			// 开始加载新的路径图片;
//			startGetImgs();
//		}
//		// 如果是从查看相册返回的,结束相册时不显示该Activity;
//		if (lastActivityIsFolder) {
//			ChooseImage.this.finish();
//			lastActivityIsFolder = false;
//		}
//	}
//	
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
////		super.onActivityResult(requestCode, resultCode, data);
//		if (resultCode == RESULT_OK && data == null) {
//			// 处理拍照的图片;
//			data = new Intent();
//		} else if (data == null){
//			lastActivityIsShow = true;
//			return;
//		}
//		Uri uri = data.getData();
//		try {
//			// 如果是打开相机拍照成功的;用游标从Media.DATA中获取;
//			if (uri == null && resultCode == RESULT_OK) {
//				currentPathYouGived = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera";
//				lastActivityIsShow = false;
//				ContentResolver cr = getContentResolver();
//				
//				String myImageUrl = MediaStore.Images.Media.insertImage(cr, BarChooseImg.cameraTakedImgUrl, "", "");
//
//				currentPathYouGived = getParentPathByImgUri(myImageUrl);
//				new File(BarChooseImg.cameraTakedImgUrl).delete();
////				Log.i("FRJ","2222currentPathYouGived:" + currentPathYouGived);
//				if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2){
//					MediaScannerConnection.scanFile(this, new String[] {Environment.getExternalStorageDirectory().getPath()}, null, null);
//				}else{
//					sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, 
//							Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
//				}
//				isChoseTwo = true;
//			}
//		}catch(Exception e){
//			lastActivityIsShow = true;
//			Toast.makeText(this, "图片保存失败", Toast.LENGTH_LONG).show();
////			Tools.showToast(this, "图片保存失败");
////			StringManager.reportError("相机拍照,保存到相册失败", e);
//		}
////		finish();
//	}
//	
//	private String getParentPathByImgUri(String sUri){
//		Uri uri2 = Uri.parse(sUri);
//		String[] proj = { MediaStore.Images.Media.DATA };   
//		Cursor actualimagecursor = managedQuery(uri2,proj,null,null,null);  
//		int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);   
//		actualimagecursor.moveToFirst();   
//		String img_path = actualimagecursor.getString(actual_image_column_index); 
////		Log.i("FRJ","img_path:" + img_path);
//		File file = new File(img_path);
//		return file.getParentFile().getAbsolutePath();
//	}
//	
//	private String getMediaPath(){
//		String[] projection = {MediaStore.Images.Media._ID,MediaStore.Images.Media.DATA};  
//		Cursor cursor = managedQuery( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.Media._ID); 
//		int fileColum = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//		String path = Environment.getExternalStorageDirectory().toString();
//		if (cursor.moveToFirst()) { 
//		    path = cursor.getString(fileColum); 
//		    File file = new File(path);
//		    path = file.getParentFile().getAbsolutePath();
//		}
////		StringManager.print("d", "getMediaPath:" + path);
//		return path;
//	}
//
//	@Override
//	public void onBackPressed() {
//		// 回相册;
//		stopLoading();
//		Intent intent = new Intent();
//		intent.setClass(ChooseImage.this, ChooseImageFolder.class);
//		startActivity(intent);
//	}
//
//	/**
//	 * 初始化组件和事件;
//	 */
//	private void init() {
//		// 设置加载中动画
////		progressBar.setVisibility(View.VISIBLE);
//
//		// 显示选择图片数量
////		count_ChooseImg = (TextView) findViewById(R.id.count_ChooseImg);
////		if(count_ChooseImg == null){
////			Log.i("tag", "listAllChoosedUrl   is  null");
////		}
////		count_ChooseImg.setText(listAllChoosedUrl.size() + "/" + BarChooseImg.maxImg);
//		// 显示文件夹名称;
////		tv_title = (TextView) findViewById(R.id.title);
////		tv_title.setOnClickListener(new OnClickListener() {
////			@Override
////			public void onClick(View v) {
////				Toast.makeText(getApplicationContext(), "" + currentPathYouGived, Toast.LENGTH_LONG).show();
//////				Tools.showToast(getApplicationContext(), "" + currentPathYouGived);
////			}
////		});
////		findViewById(R.id.leftImgBtn).setVisibility(View.GONE);
////		// 去相册
////		TextView backToAlbums = (TextView) findViewById(R.id.leftText);
////		backToAlbums.setText("相册列表");
////		backToAlbums.setVisibility(View.VISIBLE);
////		backToAlbums.setOnClickListener(new OnClickListener() {
////			@Override
////			public void onClick(View v) {
////				ChooseImage.this.onBackPressed();
////			}
////		});
//
//		// 取消选择;
////		Button btn_cacelChoose = (Button) findViewById(R.id.rightBtn1);
////		btn_cacelChoose.setText("取消");
////		btn_cacelChoose.setBackgroundColor(Color.parseColor("#000000"));
////		btn_cacelChoose.setVisibility(View.VISIBLE);
////		btn_cacelChoose.setOnClickListener(new OnClickListener() {
////			@Override
////			public void onClick(View v) {
//////				Log.i("chooseimage", "quxiao  bei dianji");
////				ChooseImage.this.finish();
////				clearAllChoosed();
////			}
////		});
//		// 提交所选
////		TextView submitChoose = (TextView) findViewById(R.id.tv_sumbitChooseImg);
////		submitChoose.setOnClickListener(new OnClickListener() {
////			@Override
////			public void onClick(View v) {
////				if (listAllChoosedUrl.size() == 0) {
////					Toast.makeText(getApplicationContext(), "没有选择图片!", Toast.LENGTH_LONG).show();
//////					Tools.showToast(getApplicationContext(), "没有选择图片!");
////					return;
////				}
////				// 点击确定时停止加载线程;
////				stopLoading();
////				// 选择完成后所有图片的地址
////				Intent data = new Intent();
////				setResult(BarChooseImg.maxImg, data);
////				imgCache.clear();
////				finish();
////			}
////		});
//		gridView = (GridView) findViewById(R.id.gridView);
//		if(recycleViews == null){
//			Log.i("tag", "recycleViews  is  null");
//		}
//		recycleViews.put(gridView, new int[] { R.id.iv_item_chooseimg_big });//, R.id.iv_item_chooseimg_small
//		gridView.setOnScrollListener(gridViewSroll);
//		// 设置图片宽度,图片高度;
//		ImgWidth = ToolsDevice.getWindowPx(this).widthPixels / imgSizeByScreen;
//		ImgHeight = ImgWidth;
//
//		// 初始化常用文件夹列表;
//		MaxNumOfenFolders = initOfenUsedFolders();
//		// 初始化数据;
//		nextWork();
//		// 初始化handler;
//		startReceiveWok();
//		listAllChoosedUrl.clear();
//	}
//
//	// 清理数据,clearDataForNextWork;
//	private void nextWork() {
//		// 文件路径改变时需要自动加载;
//		FirstVisibleItem = 0;
//		VisibleItemCount = 0;
//		// 重新记录当前的parentPath的文件列表;
//		listAllImagesInGivedPath.clear();
//		// 只保留当前路径下选择的图片;
//		// listAllChoosedUrl = new ArrayList<String>();
//	}
//
//	/**
//	 * 定义一个用于接收消息的handler,并开始加载图片;
//	 */
//	private void startReceiveWok() {
//		// 接收图片完成通知;
//		handler = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				super.handleMessage(msg);
//				// 所有真实图片加载完成;
//				if (msg.what == MSG_HAS_GET_ALL_IMAGE) {
//					// 若有路径,并且该路径有图片,适配器器为该路径文件数据
//					if (currentPathYouGived != null && listAllImagesInGivedPath != null && listAllImagesInGivedPath.size() > 0) {
//						tv_title.setText(currentPathYouGived.substring(currentPathYouGived.lastIndexOf("/") + 1, currentPathYouGived.length()));
//						//TODO 加载缩略图成功,显示1111
//						doBackGroundWorkOk(listAllImagesInGivedPath);
//					}
//					// 如果当前路径没有图片,加载下一个路径;
//					if (currentPathYouGived != null && (listAllImagesInGivedPath == null || listAllImagesInGivedPath.size() == 0)) {
//						// 判断给定的相册列表数据;
//						if (NumPlusByOfenFoldersNum < MaxNumOfenFolders) {
//							// 从给定的常用列表中依次选择一个路径开始加载;
//							currentPathYouGived = listOfenUsedFolder.get(NumPlusByOfenFoldersNum);
//							NumPlusByOfenFoldersNum++;
//							startGetImgs();
//						} else {
//							// 如果给定的列表没有任何可以显示的图片,并且所有相册里有图片,则加载所有图片;
////							progressBar.setVisibility(View.GONE);
//							if (listAllImagesInPhone != null && listAllImagesInPhone.size() != 0) {
//								tv_title.setText("所有图片");
//								//TODO 加载缩略图成功,显示2222
//								doBackGroundWorkOk(listAllImagesInPhone);
//							} else {
//								tv_title.setText("没有图片");
//								Toast.makeText(getApplicationContext(), "图库中没有可显示的图片.", Toast.LENGTH_LONG).show();
////								Tools.showToast(getApplicationContext(), "图库中没有可显示的图片.");
//							}
//						}
//					}
//				}
//			}
//		};
//	}
//
//	/**
//	 * 定义手机常用的保存相册的文件夹列表,优先级依次排列;
//	 * 
//	 * @return
//	 */
//	private int initOfenUsedFolders() {
//		String sdcard = Environment.getExternalStorageDirectory().getPath();
//		listOfenUsedFolder = new ArrayList<String>();
//		listOfenUsedFolder.add(sdcard + "/DCIM");
//		listOfenUsedFolder.add(sdcard + "/my pictures");
//		listOfenUsedFolder.add(sdcard + "/pictures");
//		listOfenUsedFolder.add(sdcard + "/照片");
//		listOfenUsedFolder.add(sdcard + "/我的照片");
//		return listOfenUsedFolder.size();
//	}
//
//	// 点击大图片事件;
//	public void bigImgClick(final View v) {
//		String url = v.getTag().toString();
//		if(url.equals(UploadSubject.CAMRAICON_UNIQID)){
////			StringManager.print("d", "点击了相机");
//			if(listAllChoosedUrl.size() < BarChooseImg.maxImg)
//				BarChooseImg.openCamera(this);
//			else
//				Toast.makeText(this, "最多选" + BarChooseImg.maxImg + "张图片", Toast.LENGTH_LONG).show();
////				Tools.showToast(this, "最多选" + BarChooseImg.maxImg + "张图片");
//		}else{
////			StringManager.print("d", "点击了其他");
//			
//			smallImgClick(v);
////			Intent intent = new Intent();
////			intent.putExtra("url", url);
////			intent.setClass(ChooseImage.this, ChooseImgShow.class);
////			startActivity(intent);
//		}
//	}
//
//	// 打钩勾小图片点击事件;
//	public void smallImgClick(View v) {
//		final String image_url = v.getTag().toString();
//		// 添加或移除选中的图片;
//		if (!listAllChoosedUrl.contains(image_url)) {
//			// 允许选择的最大图片数量;
//			if (listAllChoosedUrl.size() < BarChooseImg.maxImg) {
////				Log.i("ra", image_url+"image_url");
//				if(ToolsImage.isQualified(image_url,3)){
////					((ImageView) v).setImageResource(R.drawable.i_ico_ok);
//					// 点击选择的图片总数量
//					listAllChoosedUrl.add(image_url);
////					count_ChooseImg.setText(listAllChoosedUrl.size() + "/" + BarChooseImg.maxImg);
//					
//					
//					
//					
//					
//					if (listAllChoosedUrl.size() == 0) {
//						Toast.makeText(getApplicationContext(), "没有选择图片!", Toast.LENGTH_LONG).show();
////						Tools.showToast(getApplicationContext(), "没有选择图片!");
//						return;
//					}
//					// 点击确定时停止加载线程;
//					stopLoading();
//					// 选择完成后所有图片的地址
//					Intent data = new Intent();
//					setResult(BarChooseImg.maxImg, data);
//					imgCache.clear();
////					listAllChoosedUrl.clear();
//					finish();
//				}else{
//					Toast.makeText(getApplicationContext(), "此图太长或太宽不成比例", Toast.LENGTH_LONG).show();
////					Tools.showToast(getApplicationContext(),"此图太长或太宽不成比例");
//				}
//			} else {
//				Toast.makeText(getApplicationContext(), "选择的图片多于" + BarChooseImg.maxImg + "张!", Toast.LENGTH_LONG).show();
////				Tools.showToast(getApplicationContext(), "选择的图片多于" + BarChooseImg.maxImg + "张!");
//			}
//		} else {
//			((ImageView) v).setImageResource(R.drawable.i_ico_nook);
//			// 点击选择的图片总数量
//			listAllChoosedUrl.remove(image_url);
////			count_ChooseImg.setText(listAllChoosedUrl.size() + "/" + BarChooseImg.maxImg);
//		}
//	}
//	
//	/**
//	 * 开启线程加载图片;
//	 */
//	private void startGetImgs() {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				// 所有图片加载完.同时填充一个指定了其路径的图片列表 listAllImagesInPath;
//				listAllImagesInPhone = getImageUrl();
//				handler.sendEmptyMessage(MSG_HAS_GET_ALL_IMAGE);
//			}
//		}).start();
//	}
//
//	/**
//	 * 获取全部真实图片地址;
//	 */
//	private ArrayList<HashMap<String, String>> getImageUrl() {
//		ArrayList<HashMap<String, String>> listmapImageUrl = new ArrayList<HashMap<String, String>>();
//		
//		//添加相机数据
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("image_url", UploadSubject.CAMRAICON_UNIQID);
////		StringManager.print("d", "添加相机图片 image_url");
//		// 总图片数量;
//		listmapImageUrl.add(map2);
//		listAllImagesInGivedPath.add(map2);
//		
//		ContentResolver cr = getContentResolver();
//		// 缩略图id,图片id,缩略图地址,
//		String[] projection = { BaseColumns._ID, MediaColumns.DATA, MediaColumns.TITLE, MediaColumns.DISPLAY_NAME, MediaColumns.SIZE };
//		Cursor cur = cr.query(Media.EXTERNAL_CONTENT_URI, projection, null, null, BaseColumns._ID + "  DESC");
//		if(cur != null){
//			int id, size;
//			String imageUrl, imageDisplayName, imageTitle;
//			int _idColumn = cur.getColumnIndex(BaseColumns._ID);
//			int _sizeColumn = cur.getColumnIndex(MediaColumns.SIZE);
//			int _imageUrlColumn = cur.getColumnIndex(MediaColumns.DATA);
//			int _imageDisColumn = cur.getColumnIndex(MediaColumns.DISPLAY_NAME);
//			int _imageTitleColumn = cur.getColumnIndex(MediaColumns.TITLE);
//
//			if (cur.moveToFirst()) {
//				do {
//					id = cur.getInt(_idColumn);
//					size = cur.getInt(_sizeColumn);
//					imageUrl = cur.getString(_imageUrlColumn);
//					imageDisplayName = cur.getString(_imageDisColumn);
//					imageTitle = cur.getString(_imageTitleColumn);
//
//					HashMap<String, String> map = new HashMap<String, String>();
//					map.put("id", id + "");
//					map.put("size", size + "");
//					map.put("image_url", imageUrl);
//					map.put("image_name", imageDisplayName);
//					map.put("image_title", imageTitle);
//					// 总图片数量;
//					listmapImageUrl.add(map);
//					// 获取给定路径下图片数量
//					getImagesInCurrentPath("image_url", imageUrl);
//				} while (cur.moveToNext());
//				cur.close();
//			}
//		}
//		return listmapImageUrl;
//	}
//
//	/**
//	 * 获取当前url所在目录的所有文件;
//	 * 
//	 * @param path
//	 * @param key
//	 */
//	private void getImagesInCurrentPath(String key, String path) {
//		if (currentPathYouGived == null || path.length() == 0 || !path.contains(File.separator))
//			return;
//		// 如果当前这个url的父目录和获取到的目录相同;
//		String parent = path.substring(0, path.lastIndexOf(File.separator));
//		if (parent.toLowerCase().equals(currentPathYouGived.toLowerCase())) {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put(key, path);
//			listAllImagesInGivedPath.add(map);
//		}
//	}
//
//	/**
//	 * 所有缩略图加载完成后才开始显示数据;
//	 */
//	private void doBackGroundWorkOk(ArrayList<HashMap<String, String>> dataForAdapter) {
////		StringManager.print("d", "dataForAdapter size: " + dataForAdapter.size());
//		
//		//相机返回后默认被选中
////		if(isChoseTwo){
////			HashMap<String, String> hash = dataForAdapter.get(1);
////			String url = hash.get("image_url");
////			listAllChoosedUrl.add(url);
////			isChoseTwo = false;
////			count_ChooseImg.setText(listAllChoosedUrl.size() + "/" + BarChooseImg.maxImg);
////		}
//		
//		
////		StringManager.print("d", "dataForAdapter size: " + dataForAdapter.toString());
////		progressBar.setVisibility(View.GONE);
//		AdapterChooseImg adapter = new AdapterChooseImg(this, gridView, dataForAdapter, R.layout.choose_img_item, new String[] { "image_url" },
//				new int[] { R.id.iv_item_chooseimg_big });
//		recycleViews.put(gridView, new int[] { R.id.iv_item_chooseimg_big });
//		gridView.setAdapter(adapter);
//		// 初始化自动加载一些图片,如果总数小于20,加载所有,大于20,只加载前20张;
//		if (FirstVisibleItem == 0 && VisibleItemCount == 0) {
//			int urlCount = dataForAdapter.size() / dataForAdapter.get(0).values().size();
//			if (urlCount < 20)
//				begingLoadingImage(0, urlCount);
//			else
//				begingLoadingImage(0, 20);
//		}
//	}
//
//	/**
//	 * 开始加载指定范围内的图片;
//	 * @param firstVisiable ：从哪开始加载
//	 * @param visiablleCount ；加载多少个
//	 */
//	private void begingLoadingImage(int firstVisiable, int visiablleCount) {
//		// 当停止滚动时,停止之前所有的任务,同时获取当前页面的所有url,将这些url进行加载
//		try {
//			exeService.shutdownNow();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		exeService = Executors.newFixedThreadPool(1);
//
//		// 开始新的加载;
//		for (int imgVisiableIndex = firstVisiable; imgVisiableIndex < firstVisiable + visiablleCount; imgVisiableIndex++) {
//			String realImgUrl = "";
//			// 如果给定的路径有图片;
//			if (listAllImagesInGivedPath != null && listAllImagesInGivedPath.size() > 0) {
//				realImgUrl = listAllImagesInGivedPath.get(imgVisiableIndex).get("image_url");
//			}
//			// 如果给定的路径么有图片,加载的是所有图片;
//			else {
//				realImgUrl = listAllImagesInPhone.get(imgVisiableIndex).get("image_url");
//			}
//			if (realImgUrl.length() == 0)
//				return;
//			//TODO 加载图片
//			// 开始加载内存图片;
//			if (imgCache.containsKey(realImgUrl)) {
//				Bitmap bmp = imgCache.get(realImgUrl);
//				if (bmp != null) {
//					ImageView imgView = (ImageView) gridView.findViewWithTag(realImgUrl);
//					if (imgView != null) {
////						if(realImgUrl.equals(UploadSubject.CAMRAICON_UNIQID)){
////							int padding = (int)getResources().getDimension(R.dimen.dp_10);
////							imgView.setPadding(padding, padding, padding, padding);
////						}else{
////							int padding = 0;
////							imgView.setPadding(padding, padding, padding, padding);
////						}
//						imgView.setImageBitmap(bmp);
//					}
//				}
//			}
//			// 开始从本地加载新的图片;
//			else {
//				AnsycGetBitmap(realImgUrl, ImgWidth, ImgHeight, new LoadBmpCallBack() {
//					@Override
//					public void loadBmpOver(String url, Bitmap bitmap) {
//						ImageView imgView = (ImageView) gridView.findViewWithTag(url);
//						if (imgView != null) {
////							if(url.equals(UploadSubject.CAMRAICON_UNIQID)){
////								int padding = (int)getResources().getDimension(R.dimen.dp_10);
////								imgView.setPadding(padding, padding, padding, padding);
////							}else{
////								int padding = 0;
////								imgView.setPadding(padding, padding, padding, padding);
////							}
//							imgView.setImageBitmap(bitmap);
//							imgCache.put(url, bitmap);
//							RecycleBitmap.recycleMapCache(imgCache, 100, 50);
//						}
//					}
//				});
//			}
//		}
//	}
//
//	/**
//	 * 飞速滑动时查询内存;
//	 * @param firstVisiable
//	 * @param visiablleCount
//	 */
//	private void begingLoadingImgFling(int firstVisiable, int visiablleCount) {
//		for (int imgVisiableIndex = firstVisiable; imgVisiableIndex < firstVisiable + visiablleCount; imgVisiableIndex++) {
//			String realImgUrl = "";
//			// 如果给定的路径有图片;
//			if (listAllImagesInGivedPath != null && listAllImagesInGivedPath.size() > 0) {
//				realImgUrl = listAllImagesInGivedPath.get(imgVisiableIndex).get("image_url");
//			}
//			// 如果给定的路径没有图片,加载的是所有图片;
//			else {
//				realImgUrl = listAllImagesInPhone.get(imgVisiableIndex).get("image_url");
//			}
//			if (realImgUrl.length() == 0)
//				return;
//			// 开始内存加载
//			if (imgCache.containsKey(realImgUrl)) {
//				Bitmap bmp = imgCache.get(realImgUrl);
//				if (bmp != null) {
//					ImageView imgView = (ImageView) gridView.findViewWithTag(realImgUrl);
//					if (imgView != null) {
//						imgView.setImageBitmap(bmp);
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * 使用线程创建一个bitmap对象;
//	 * @param url 本地原图的文件地址;
//	 * @param width 要压缩成的宽度
//	 * @param height 要压缩成的高度
//	 * @param bmpCallBack
//	 */
//	private void AnsycGetBitmap(final String url, final int width, final int height, final LoadBmpCallBack bmpCallBack) {
//		final Handler handlerBmp = new Handler() {
//			@Override
//			public void handleMessage(Message message) {
//
//				@SuppressWarnings("unchecked")
//				HashMap<String, Object> hashMap = (HashMap<String, Object>) message.obj;
//				String url = (String) hashMap.get("url");
//				Bitmap bitmap = (Bitmap) hashMap.get("bitmap");
//				bmpCallBack.loadBmpOver(url, bitmap);
//			}
//		};
//
//		Runnable run = new Runnable() {
//			@Override
//			public void run() {
//				Bitmap bitmap;
//				//如果是相机
//				if(url.equals(UploadSubject.CAMRAICON_UNIQID)){
//					bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.i_ico_upload);
//				}else{
//					bitmap = ToolsImage.imgPathToBitmap(url, width, height, true);
//				}
//				Message msg = new Message();
//				HashMap<String, Object> hashMap = new HashMap<String, Object>();
//				hashMap.put("url", url);
//				hashMap.put("bitmap", bitmap);
//				msg.obj = hashMap;
//				handlerBmp.sendMessage(msg);
//				hashMap = null;
//			}
//		};
//		exeService.execute(run);
//	}
//
//	/**
//	 * 停止滑动时加载屏幕可视区域,飞速滑动时加载内存;
//	 */
//	private OnScrollListener gridViewSroll = new OnScrollListener() {
//		@Override
//		public void onScrollStateChanged(final AbsListView view, int scrollState) {
//			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
//				begingLoadingImage(FirstVisibleItem, VisibleItemCount);
//			}
//		}
//
//		@Override
//		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//			if (firstVisibleItem != FirstVisibleItem || visibleItemCount != VisibleItemCount) {
//				begingLoadingImgFling(firstVisibleItem, visibleItemCount);
//			}
//			FirstVisibleItem = firstVisibleItem;
//			VisibleItemCount = visibleItemCount;
//		}
//	};
//
//	/**
//	 * 终止当前线程
//	 */
//	private void stopLoading() {
//		try {
//			// 终止加载;
//			exeService.shutdownNow();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		exeService = null;
//		System.gc();
//	}
//
//	/**
//	 * 清除选择的图片;
//	 */
//	public static void clearAllChoosed() {
//		if (ChooseImage.listAllChoosedUrl != null || ChooseImage.listAllChoosedUrl.size() >= 0)
//			ChooseImage.listAllChoosedUrl.clear();
//	}
//
//	/**
//	 * 压缩图片完成之后的回调函数;
//	 */
//	public interface LoadBmpCallBack {
//		public void loadBmpOver(String url, Bitmap bitmap);
//	}
//
//	private TextView tv_title;//, count_ChooseImg
//	private GridView gridView;
//	// GirdView中可见区域
//	private int FirstVisibleItem = 0, VisibleItemCount = 0;
//	// 缩略图图片宽高;
//	private int ImgWidth = 100, ImgHeight = 100, imgSizeByScreen = 3;
//	// 常用文件夹列表;
//	private ArrayList<String> listOfenUsedFolder;
//	// 如果常用列表的第一个没有,则比对列表中的下一个;
//	private int NumPlusByOfenFoldersNum = 0, MaxNumOfenFolders = 0;
//	// 查询所有图片,接受消息:所有图片加载完成
//	private Handler handler;
//	// 已经加载完成所有图片
//	private int MSG_HAS_GET_ALL_IMAGE = 1;
//	// 整个屏幕,一个线程;
//	private ExecutorService exeService = Executors.newFixedThreadPool(1);
//	/**
//	 *  所有图片地址;
//	 */
//	private ArrayList<HashMap<String, String>> listAllImagesInPhone;
//	/**
//	 *  所有在指定目录下的图片地址;
//	 */
//	private ArrayList<HashMap<String, String>> listAllImagesInGivedPath = new ArrayList<HashMap<String, String>>();
//
//}
