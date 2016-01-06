/**
 * intbird 20140225
 */
package com.acore.logic;

import java.util.Iterator;
import java.util.LinkedHashMap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;


/**
 * @author S_arige
 * 2016年1月6日：上午10:14:34
 */
public class RecycleBitmap {
	private static Bitmap nopicBitmap = null;

	/**
	 * 清理map中的bitmap;
	 * @param imgCache ImageCacheMap
	 * @param maxSize 允许添加的最大图片数量
	 * @param freeSize 释放掉图片的数量;
	 */
	public static void recycleMapCache(LinkedHashMap<String, Bitmap> imgCache,
			int maxSize, int freeSize) {
		// 超出最大容量时清理
		if (imgCache.values().size() > maxSize) {
			synchronized (imgCache) {
				Iterator<String> it = imgCache.keySet().iterator();
				while (it.hasNext() && (imgCache.keySet().size() > freeSize)) {
					Bitmap bmp = imgCache.get(it.next());
					if (bmp != null && !bmp.isRecycled()) {
						bmp.recycle();
						bmp = null;
					}
					it.remove();
				}
			}
		}
	}

	/**
	 * 清理View中的ImagView被BitMap占用的内存;
	 * @param mapViews  一个View的合集
	 */
//	public static void recycle(final Map<View, int[]> mapViews) {
//		synchronized (mapViews) {
//			ReqInternet.runThread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(200);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					Iterator<View> it = mapViews.keySet().iterator();
//					while (it.hasNext()) {
//						// 获取布局
//						final View view = it.next();
//						if (view == null)
//							return;
//						// 获取要布局内要回收的ids;
//						final int[] recycleIds = mapViews.get(view);
//
//						// 如果是listView,先找到每个item布局文件和里面对应的id并清理
//						if ((view instanceof GridView)
//								|| (view instanceof ListView)) {
//							recycleAbsList((AbsListView) view, recycleIds);
//						}
//
//						// 如果是ImageView,直接回收;
//						else if (view instanceof ImageView) {
//							recycleImageView(view);
//						}
//						// 如果是ViewGroup,重要提示:每个item在ViewGroup的第二层;
//						else if ((view instanceof ViewGroup)) {
//							recycleViewGroup((ViewGroup) view, recycleIds);
//						}
//					}
//					System.gc();
//				}
//			});
//		}
//	}

	/**
	 * 回收继承自AbsListView的类,如GridView,ListView等
	 * @param absView
	 * @param recycleIds 要清理的Id的集合;
	 */
	public static void recycleAbsList(AbsListView absView, int[] recycleIds) {
		if (absView == null || recycleIds == null)
			return;
		// 线程同步处理
		synchronized (absView) {
			int numOfVisView = absView.getLastVisiblePosition() - absView.getFirstVisiblePosition() + 1;
			for (int i = 0; i <= numOfVisView; i++) {
				// add your code
				View view = absView.getChildAt(i);
				for (int count = 0; count < recycleIds.length; count++) {
					if (view != null) {
						recycleImageView(view.findViewById(recycleIds[count]));
					}
				}
			}
		}
	}

	/**
	 * 回收继承自AbsListView的类,如GridView,ListView等
	 * @param absView
	 * @param recycleIds 要清理的Id的集合;
	 */
	public static void recycleViewGroup(ViewGroup layout, int[] recycleIds) {
		if (layout == null || recycleIds == null)
			return;
		synchronized (layout) {
			for (int i = 0; i < layout.getChildCount(); i++) {
				View subView = layout.getChildAt(i);
				if (subView instanceof ViewGroup) {
					for (int count = 0; count < recycleIds.length; count++) {
						recycleImageView(subView.findViewById(recycleIds[count]));
					}
				} else {
					if (subView instanceof ImageView) {
						recycleImageView(subView);
					}
				}
			}
		}
	}

	/**
	 * 回收ImageView占用的图像内存;
	 * @param view
	 */
	public static void recycleImageView(View view) {
		if (view == null)
			return;
		if (view instanceof ImageView) {
			BitmapDrawable drawable = (BitmapDrawable) ((ImageView) view)
					.getDrawable();
			if (drawable != null) {
				try {
//					nopicBitmap = ((BitmapDrawable) view.getResources().getDrawable(R.drawable.i_nopic)).getBitmap();
					Bitmap bmp = drawable.getBitmap();
					if (bmp != null  && !bmp.isRecycled()) {
//						 StringManager.print("d", "回收图片："+view.getTag());
						bmp.recycle();
						bmp = null;
						try {
							((ImageView) view).setImageBitmap(null);
						} catch (Exception e) {
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
