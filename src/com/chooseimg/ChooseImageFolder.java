///**
// * 打开相册浏览.
// * @author intBird 20140213.
// * 
// */
//package com.chooseimg;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
//import android.R.color;
//import android.app.Activity;
//import android.content.ContentResolver;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.provider.BaseColumns;
//import android.provider.MediaStore.Images.Media;
//import android.provider.MediaStore.MediaColumns;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.Button;
//import android.widget.GridView;
//
//public class ChooseImageFolder extends Activity {
//	
//	/**
//	 * 临时缓存文件夹的缩略图;
//	 */
//	public static LinkedHashMap<String, Bitmap> imgCache = new LinkedHashMap<String, Bitmap>();
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		initActivity("相册列表", 2,0,R.layout.c_view_bar_title, R.layout.choose_img_folder);
//		init();
//	}
//
//	@Override
//	public void onBackPressed() {
//		super.onBackPressed();
//		ChooseImage.lastActivityIsFolder = true;
//		finish();
//		//清理选择图片模块内存缓存;
//		RecycleBitmap.recycleMapCache(imgCache, -1, -1);
//		RecycleBitmap.recycleMapCache(ChooseImage.imgCache, -1, -1);
//	}
//	private void init() {
////		progressBar.setVisibility(View.VISIBLE);
//		// 取消选择;
//		findViewById(R.id.ll_back).setVisibility(View.GONE);
//		btn_cacel = (Button) findViewById(R.id.rightBtn1);
//		btn_cacel.setText("取消");
//		btn_cacel.setVisibility(View.VISIBLE);
//		btn_cacel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
////				Log.i("chooseimagefolder", "quxiao  bei dianji");
//				ChooseImage.lastActivityIsFolder = true;
//				ChooseImageFolder.this.onBackPressed();
//			}
//		});
//		gridView = (GridView) findViewById(R.id.gv_chooseImgFolder);
//		recycleViews.put(gridView, new int[]{R.id.iv_item_folderImg});
//		gridView.setSelector(new ColorDrawable(color.holo_green_light));
//		gridView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				String selectPath = listAllImageFolder.get(position).get("folder_path");
//				ChooseImage.currentPathYouGived = selectPath;
//				ChooseImage.lastActivityIsShow = false;
//				ChooseImageFolder.this.finish();
//			}
//		});
//		
//		handler = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				super.handleMessage(msg);
//				if (msg.what == CH_HAS_GET_ALL_IMAGE) {
//					// 缩略图和图片都加载完成,给予GridView数据适配器;
//					doInBackGroundOk();
//				}
//			}
//		};
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				getImageUrlListMap();
//				handler.sendEmptyMessage(CH_HAS_GET_ALL_IMAGE);
//			}
//		}).start();
//	}
//
//	/**
//	 * 表示后台搜索所以图片已经完成,并设置GridView的适配器
//	 */
//	private void doInBackGroundOk() {
////		progressBar.setVisibility(View.GONE);
//		adapter = new AdapterLittleImg(getApplicationContext(),listAllImageFolder, 
//				R.layout.choose_img_folder_item,
//				new String[]{"image_url", "folder_name"}, 
//				new int[]{R.id.iv_item_folderImg, R.id.tv_item_folderName});
//		recycleViews.put(gridView, new int[]{R.id.iv_item_folderImg});
//		gridView.setAdapter(adapter);
//	}
//
//	/**
//	 * 获取全部真实图片地址.每次都要查,[拍了一张图片,返回查看要有,不能缓存上一次不存在的情况];
//	 */
//	private ArrayList<HashMap<String, String>> getImageUrlListMap() {
//		ArrayList<HashMap<String, String>> listmapImageUrl = new ArrayList<HashMap<String, String>>();
//		ContentResolver cr = getContentResolver();
//		// 缩略图id,图片id,缩略图地址,
//		String[] projection = {BaseColumns._ID, MediaColumns.DATA,
//				MediaColumns.TITLE, MediaColumns.DISPLAY_NAME,
//				MediaColumns.SIZE};
//		Cursor cur = cr.query(Media.EXTERNAL_CONTENT_URI, projection, null,
//				null, BaseColumns._ID + " DESC");
//
//		if(cur != null){
//			int id, size;
//			String imageUrl, imageDisplayName, imageTitle;
//
//			int _idColumn = cur.getColumnIndex(BaseColumns._ID);
//			int _sizeColumn = cur.getColumnIndex(MediaColumns.SIZE);
//			int _imageUrlColumn = cur.getColumnIndex(MediaColumns.DATA);
//			int _imageDisColumn = cur.getColumnIndex(MediaColumns.DISPLAY_NAME);
//			int _imageTitleColumn = cur.getColumnIndex(MediaColumns.TITLE);
//
//			int width = ToolsDevice.getWindowPx(this).widthPixels / imgSizeByScreen;
//			int height = width;
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
//					listmapImageUrl.add(map);
//
//					// 获取文件的父目录的完整路径;
//					String parentFolderPath = imageUrl.substring(0,imageUrl.lastIndexOf(File.separator));
//					//如果之前没有这个目录,添加该唯一目录;
//					if (!listFolder.contains(parentFolderPath)) {
//						listFolder.add(parentFolderPath);
//						//获取文件父目录的名字,去除完整路径
//						String parentFolderName = parentFolderPath.substring(parentFolderPath.lastIndexOf(File.separator) + 1,parentFolderPath.length()).replace(Environment.getExternalStorageDirectory().getPath(), "");
//						int imgNum=0;
//						//如果map文件名数量中有此地址;直接获取,
//						if(mapFolderNameNum.containsKey(parentFolderPath)){
//							imgNum=mapFolderNameNum.get(parentFolderPath);
//						}//如果没有,遍历文件数量;
//						else  imgNum= getFiles(parentFolderPath);
//						map.put("folder_name", parentFolderName + "(" +imgNum+ ")");
//						map.put("folder_path", parentFolderPath);
//						listAllImageFolder.add(map);
//						if(imgCache.size()==0||!imgCache.containsKey(imageUrl)){
//							imgCache.put(imageUrl, ToolsImage.imgPathToBitmap(imageUrl,width, height, true));
//							RecycleBitmap.recycleMapCache(imgCache,50,30);
//						}
//					}
//				} while (cur.moveToNext());
//				cur.close();
//			}
//		}
//		return listmapImageUrl;
//	}
//
//	/**
//	 * 根据路径获取里面的图片数量;
//	 * @param fileDir
//	 * @return
//	 */
//	private int getFiles(String fileDir) {
//		File file = new File(fileDir);
//		if(file.exists()&&file.isDirectory()){
//			File[] files = file.listFiles();
//			int num = 0;
//			for (File f : files) {
//				if (!f.isHidden() && file.canRead() && file.length() > 0) {
//					String filePath = f.getPath();
//					if (filePath.toLowerCase().endsWith(".jpg")|| filePath.toLowerCase().endsWith(".jpeg")|| filePath.toLowerCase().endsWith(".png")) {
//						num++;
//					}
//				}
//			}
//			mapFolderNameNum.put(fileDir, num);
//			return num;
//		}
//		else return 1;
//	}
//
//	/**
//	 * 获取所有文件夹;
//	 */
//	private ArrayList<HashMap<String, String>> listAllImageFolder = new ArrayList<HashMap<String, String>>();
//	/**
//	 * 用于控制文件夹数量的添加;
//	 */
//	private ArrayList<String> listFolder = new ArrayList<String>();
//	
//	private Map<String,Integer > mapFolderNameNum=new HashMap<String, Integer>();
//	/**
//	 * 显示文件夹的视图;
//	 */
//	private GridView gridView;
//
//	private AdapterLittleImg adapter;
//	private Button btn_cacel;
//	
//	//图片比例;
//	private int imgSizeByScreen=2;
//	/**
//	 * 消息接收;
//	 */
//	private Handler handler;
//	/**
//	 * 消息;已经加载完成所有图片;
//	 */
//	private int CH_HAS_GET_ALL_IMAGE = 1;
//
//}
