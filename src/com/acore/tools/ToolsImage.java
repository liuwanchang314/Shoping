package com.acore.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;



public class ToolsImage {
	// 获取优化内存的图片模式
	public static BitmapFactory.Options getBitmapOpt() {
		// 配置bitmap，防止内存溢出
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		return opt;
	}

	/**
	 * 图片圆角转换
	 * @param res 当前资源类
	 * @param drawable
	 * @param type 1全圆角，2仅上半部分圆角
	 * @param pixels 圆角大小
	 * @return
	 */
	public static Bitmap toRoundCorner(Resources res, Bitmap bitmap, int type, int pixels) {
		Bitmap output = null;
		if (pixels == 0)
			return bitmap;
		try {
			int width = bitmap.getWidth(), height = bitmap.getHeight();
			output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, width, height);
			final RectF rectF = new RectF(rect);
			final float roundPx = pixels;
			paint.setAntiAlias(true);
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(0xff424242);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
			switch (type) {
			// 仅上半部分圆角
			case 2:
				canvas.drawRect(0, height - pixels, width, height, paint);
				break;
			}
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);
		} catch (Exception e) {
		} catch (Error e) {
		}
		return output;
	}

	/**
	 * 将图片文件转换成bitmap
	 * @param imgPath 图片文件路径
	 * @param width 宽度
	 * @param height 高度
	 * @param isThumbnail 是否根据高宽生成缩略图
	 * @return
	 */
	@SuppressLint("NewApi")
	public static Bitmap imgPathToBitmap(String imgPath, int width, int height, boolean isThumbnail) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = getBitmapOpt();
		options.inJustDecodeBounds = true;
		// 获取这个图片的宽和高，注意此处的bitmap为null
		bitmap = BitmapFactory.decodeFile(imgPath, options);
		options.inJustDecodeBounds = false;
		// 计算缩放比
		int h = options.outHeight;
		int w = options.outWidth;
		int be = 1;
		if (width > 0 && w > width) {
			be = (int) (w / width + 0.5);
		}
		if (height > 0 && h > height) {
			int be2 = (int) (h / height + 0.5);
			if (be2 > be)
				be = be2;
		}
		options.inSampleSize = be;
		bitmap = BitmapFactory.decodeFile(imgPath, options);
		int degree = getPictureDegree(imgPath);
		// 如果有角度上的旋转,则纠正图片;
		if (degree != 0) {
			bitmap = correctImage(degree, bitmap);
		}
		// 生成固定尺寸的缩略图
		if (isThumbnail) {
			bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		}
		return bitmap;
	}
	
	/**
	 * 将Bitmap转换成InputStream
	 * 
	 * @param bitmap
	 * @param  最大kb值
	 * @return
	 */
	public static InputStream bitmapToInputStream(Bitmap bitmap, int kb) {
		int options = 99;
		InputStream is = null;
		byte[] theByte = null;
		int num = 1;
		Bitmap oldBitmap = bitmap;
		// 压缩
		while (bitmap != null && options >= 0) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				boolean isOk = bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
				while(!isOk && num < 2){
//					StringManager.reportError("bitmap.compress error:" + num, null);
					options = 99;
					bitmap = oldBitmap;
					isOk = bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
					num ++;
				}
				if(!isOk){
					oldBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				}
				theByte = baos.toByteArray();
				// StringManager.print("d", bitmap.getWidth() + "*" + bitmap.getHeight() + "正在压缩质量"
				// + options + "结果" + (theByte.length / 1024));
				if (theByte.length / 1024 < kb || kb == 0 || options <= 3 || !isOk) {
					is = new ByteArrayInputStream(theByte);
					baos.close();
					break;
				}
				baos.close();
				options -= 1000 / options;
				if (options <= 0)
					options = 3;
			} catch (Exception e) {
//				StringManager.reportError("图片压缩", e);
				break;
			}
		}
		return is;
	}

	// 将InputStream 转换成 Bitmap
	public static Bitmap inputStreamTobitmap(InputStream is) {
		return BitmapFactory.decodeStream(is, null, getBitmapOpt());
	}

	// 等比缩小图片并设置到imageView中，zoom强制等比适应宽或高
	public static LayoutParams setImgViewByWH(ImageView img, Bitmap bitmap, int width, int height, boolean zoom) {
		LayoutParams lp = img.getLayoutParams();
		if (bitmap == null)
			return lp;
		if (height > 0 && width > 0 && zoom) {
			lp.height = height;
			lp.width = width;
		} else if (width > 0 && bitmap.getWidth() > 0) {
			lp.height = bitmap.getHeight() * width / bitmap.getWidth();
			lp.width = width;
		} else if (height > 0 && bitmap.getHeight() > 0) {
			lp.height = height;
			lp.width = bitmap.getWidth() * height / bitmap.getHeight();
		}
		if (height > 0 || width > 0) {
			img.setLayoutParams(lp);
		}
		img.setImageBitmap(bitmap);
		return lp;
	}

//	// 获取圆角矩形背景图
//	public static Drawable getRoundBackground(Context context, String color) {
//		float dimenH = 22;
//		float dimenW = 80;
//		if (!context.getResources().getString(R.dimen.dp_22).equals("22.0dip")) {
//			dimenH = (float) 35.3;
//			dimenW = 128;
//		}
//		int height = ToolsDevice.dp2px(context, dimenH), width = ToolsDevice.dp2px(
//				context, dimenW), round = ToolsDevice.dp2px(context, 3);
//		// 新建一个新的输出图片
//		Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//		Canvas canvas = new Canvas(output);
//		// 新建一个矩形
//		RectF outerRect = new RectF(0, 0, width, height);
//		// 产生一个红色的圆角矩形 或者任何有色颜色，不能是透明！
//		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//		paint.setColor(Color.parseColor(color));
//		canvas.drawRoundRect(outerRect, round, round, paint);
//		return new BitmapDrawable(context.getResources(), output);
//	}

	/**
	 * 根据角度值,重新构造一个正向的图片;
	 * 
	 * @param angle
	 * @param bitmap
	 * @return
	 */
	public static Bitmap correctImage(int angle, Bitmap bitmap) {
		Matrix mx = new Matrix();
		mx.postRotate(angle);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, mx, true);
		return resizedBitmap;
	}

	/**
	 * 获取图片的旋转角度
	 * @param path 图片路径
	 * @return 返回已经旋转的角度值
	 */
	public static int getPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
			Log.i("tag", "nulll" + orientation);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return degree;
		
	}

	/**
	 * 判断图片是否长宽成比
	 * @param path  : 图片路径
	 * @param scale  : 比例
	 * @return
	 */
	public static boolean isQualified(String path, int scale) {
		// 配置bitmap，防止内存溢出
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		int wi = options.outWidth;
		int hei = options.outHeight;
		// Log.i("FRJ","wi:" + wi + "  hei:" + hei);
		if (wi / hei >= scale || hei / wi >= scale) {
			return false;
		}
		return true;
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}
	
	/**
	 * 高斯模糊，一般方向模糊10，迭代度7
	 * @param bmp		图片
	 * @param hRadius	水平方向模糊度
	 * @param vRadius	竖直方向模糊度
	 * @param iterations模糊迭代度
	 * @return
	 */
    public static Bitmap BoxBlurFilter(Bitmap bmp,int hRadius,int vRadius,int iterations){
//        return bmp;
    	try{
	    	int width = bmp.getWidth();
	        int height = bmp.getHeight();
	        int[] inPixels = new int[width * height];
	        int[] outPixels = new int[width * height];
        	Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
        	bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
        	for (int i = 0; i < iterations; i++) {
        		blur(inPixels, outPixels, width, height, hRadius);
        		blur(outPixels, inPixels, height, width, vRadius);
        	}
        	blurFractional(inPixels, outPixels, width, height, hRadius);
        	blurFractional(outPixels, inPixels, height, width, vRadius);
        	if(bitmap == null)
        		return bmp;
        	bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);
        	return bitmap;
        }catch(Exception e){
//        	StringManager.reportError("图片高斯模糊异常", e);
        	return bmp;
        }
        catch(Error e){
        	return bmp;
        }
    }
    private static void blur(int[] in, int[] out, int width, int height,
            int radius) {
        int widthMinus1 = width - 1;
        int r = radius;
        int tableSize = 2 * r + 1;
        int divide[] = new int[256 * tableSize];
 
        for (int i = 0; i < 256 * tableSize; i++)
            divide[i] = i / tableSize;
 
        int inIndex = 0;
 
        for (int y = 0; y < height; y++) {
            int outIndex = y;
            int ta = 0, tr = 0, tg = 0, tb = 0;
 
            for (int i = -r; i <= r; i++) {
                int rgb = in[inIndex + clamp(i, 0, width - 1)];
                ta += (rgb >> 24) & 0xff;
                tr += (rgb >> 16) & 0xff;
                tg += (rgb >> 8) & 0xff;
                tb += rgb & 0xff;
            }
 
            for (int x = 0; x < width; x++) {
                out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16)
                        | (divide[tg] << 8) | divide[tb];
 
                int i1 = x + r + 1;
                if (i1 > widthMinus1)
                    i1 = widthMinus1;
                int i2 = x - r;
                if (i2 < 0)
                    i2 = 0;
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i2];
 
                ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                outIndex += height;
            }
            inIndex += width;
        }
    }
 
    private static void blurFractional(int[] in, int[] out, int width,
            int height, float radius) {
        radius -= (int) radius;
        float f = 1.0f / (1 + 2 * radius);
        int inIndex = 0;
 
        for (int y = 0; y < height; y++) {
            int outIndex = y;
 
            out[outIndex] = in[0];
            outIndex += height;
            for (int x = 1; x < width - 1; x++) {
                int i = inIndex + x;
                int rgb1 = in[i - 1];
                int rgb2 = in[i];
                int rgb3 = in[i + 1];
 
                int a1 = (rgb1 >> 24) & 0xff;
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = rgb1 & 0xff;
                int a2 = (rgb2 >> 24) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;
                int a3 = (rgb3 >> 24) & 0xff;
                int r3 = (rgb3 >> 16) & 0xff;
                int g3 = (rgb3 >> 8) & 0xff;
                int b3 = rgb3 & 0xff;
                a1 = a2 + (int) ((a1 + a3) * radius);
                r1 = r2 + (int) ((r1 + r3) * radius);
                g1 = g2 + (int) ((g1 + g3) * radius);
                b1 = b2 + (int) ((b1 + b3) * radius);
                a1 *= f;
                r1 *= f;
                g1 *= f;
                b1 *= f;
                out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
                outIndex += height;
            }
            //莫名的会数组溢出
            try{
            	out[outIndex] = in[width - 1];
            }
            catch(Error error){
            }
            inIndex += width;
        }
    }
 
    private static int clamp(int x, int a, int b) {
        return (x < a) ? a : (x > b) ? b : x;
    }
    
	/**
	 * 删除长期存储图片
	 * @param imgUrl
	 */
//	public static void delImg(String imgUrl) {
//		if (imgUrl.length() == 0)
//			return;
//		String name = StringManager.toMD5(imgUrl, false);
//		FileManager.delSDFile(FileUtil.save_long + "/" + name, 0);
//	}

	/**
	 * 长期存储图片到本地，需在多线程中调用
	 * @param imgUrl
	 * @param type
	 */
//	public static void saveImg(String imgUrl, String type) {
//		if (imgUrl.length() == 0)
//			return;
//		String name = StringManager.toMD5(imgUrl, false);
//		// 图片不存在则下载
//		if (FileManager.ifFileModify("sd", type + "/" + name, -1) == null) {
//			ReqInternet.loadImageFromUrl(imgUrl, null, type);
//		}
//	}

}
