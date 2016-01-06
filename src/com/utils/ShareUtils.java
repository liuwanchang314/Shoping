package com.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.alljf.jf.CommonConstants;
import com.alljf.jf.R;
import com.listenter.BaseUiListener;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

public class ShareUtils {

	/**
	 * 分享给qq好友
	 * @param mTencent
	 * @param mContext
	 * @param listener
	 * @param title
	 * @param summary
	 * @param target_url
	 * @param img_url
	 * @param appname
	 */
	public static void shareMegToQQfriend(Tencent mTencent, Activity mContext,IUiListener listener,
			String title,String summary,String target_url,String img_url,String appname){
		  final Bundle params = new Bundle();
		    params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		    params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
		    params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  summary);
		    params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  target_url);
		    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,img_url);
		    params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  appname);
		    mTencent.shareToQQ(mContext, params, listener);
	}
	/**
	 * 分享到qq空间
	 * @param mTencent
	 * @param activity
	 * @param img_url_list
	 * @param title
	 * @param summary
	 * @param target_url
	 * @param listener
	 */
	public static void shareMegToQzone(Tencent mTencent,Activity activity,ArrayList<String> img_url_list,String title,
			String summary,String target_url,BaseUiListener listener){
		  final Bundle params = new Bundle();
		params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT );
	    params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//必填
	    params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, summary);//选填
	    params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, target_url);//必填
	    params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, img_url_list);
	    mTencent.shareToQzone(activity, params, listener);
	}
	
	public static void shareWebToWX(Context context, IWXAPI api, String url,String title, String des,
			boolean isFri){
//		WXWebpageObject webpage = new WXWebpageObject();
//		webpage.webpageUrl = url;
//		WXMediaMessage msg = new WXMediaMessage(webpage);
//		msg.title = title;
//		msg.description = des;
//		Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
//		msg.thumbData = bmpToByteArray(thumb, true);
//		
//		SendMessageToWX.Req req = new SendMessageToWX.Req();
//		req.transaction = buildTransaction("webpage");
//		req.message = msg;
//		req.scene = isFri ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//		api.sendReq(req);
		
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = url;
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = title;
		msg.description = des;
		Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		msg.thumbData = bmpToByteArray(thumb, true);
		
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene = isFri ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
		req.openId = CommonConstants.WXAPP_ID;
		api.sendReq(req);
		
		
	}
	
	
	public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}
		
		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private static String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
}
