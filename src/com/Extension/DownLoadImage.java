package com.Extension;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StrictMode;

public class DownLoadImage {

	static String filenameTemp = "";
	static String filename = "my.png";

	public DownLoadImage() {
		// TODO ���ㄧ�����������芥�板����
	}

	public static void DownLoadImg(String res, int type) {
		if (type == 1 || type == 2) {
			filenameTemp = Environment.getExternalStorageDirectory()
					+ "/HtFinancing/cache/newsImg/";
		} else if (type == 0) {
			filenameTemp = Environment.getExternalStorageDirectory()
					+ "/HtFinancing/cache/newsImg/";
		}
		String data = null;
		JSONObject jsonObject = null;
		JSONArray numberList = null;
		try {
			jsonObject = new JSONObject(res);
		} catch (JSONException e) {
			// TODO ���ㄧ������ catch ��
			e.printStackTrace();
		}
		try {
			data = jsonObject.getString("msg");
		} catch (JSONException e) {
			// TODO ���ㄧ������ catch ��
			e.printStackTrace();
		}
		if (data.equals("success")) {
			try {
				jsonObject = new JSONObject(res);
			} catch (JSONException e) {
				// TODO ���ㄧ������ catch ��
				e.printStackTrace();
			}
			try {
				if (type == 0) {
					jsonObject = new JSONObject(res);
					data = jsonObject.getString("data");
					jsonObject = new JSONObject(data);
					if (fileIsExists(filenameTemp + filename)) {
						return;
					}
					byte[] datas = getImage(jsonObject.getString("HeadImage"));
					FileOutputStream fops = new FileOutputStream(filenameTemp
							+ filename);
					fops.write(datas);
					fops.flush();
					fops.close();
				} else if (type == 1) {
					numberList = jsonObject.getJSONArray("data");
					for (int i = 0; i < numberList.length(); i++) {

						String url = numberList.getJSONObject(i).getString(
								"ImgUrl");

						if (url.equals("")) {
							continue;
						}
						filename = getFileName(url);
						if (fileIsExists(filenameTemp + filename)) {
							continue;
						}
						byte[] datas = getImage(url);
						FileOutputStream fops = new FileOutputStream(
								filenameTemp + filename);
						fops.write(datas);
						fops.flush();
						fops.close();
					}
				} else if (type == 2) {
					JSONArray array = jsonObject.getJSONArray("data");
					JSONArray numberList1 = array.getJSONObject(0)
							.getJSONArray("data");
					for (int i = 0; i < numberList1.length(); i++) {

						String url = numberList1.getJSONObject(i).getString(
								"ImgUrl");

						if (url.equals("")) {
							continue;
						}
						filename = getFileName(url);
						if (fileIsExists(filenameTemp + filename)) {
							continue;
						}
						byte[] datas = getImage(url);
						FileOutputStream fops = new FileOutputStream(
								filenameTemp + filename);
						fops.write(datas);
						fops.flush();
						fops.close();
					}
				}
			} catch (JSONException e) {
				// TODO ���ㄧ������ catch ��
				e.printStackTrace();
			} catch (Exception e) {
				// TODO ���ㄧ������ catch ��
				e.printStackTrace();
			}
		}
	}

	public static byte[] getImage(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setConnectTimeout(5 * 1000);
		conn.setRequestMethod("GET");
		InputStream inStream = conn.getInputStream();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return readStream(inStream);
		}
		return null;
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inStream.close();
		return outStream.toByteArray();
	}

	public static boolean fileIsExists(String url) {
		try {
			File f = new File(url);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	static int startIndex = 0;

	public static String getFileName(String str) {
		getIndex(str, 0);
		String FileName = str.substring(startIndex + 1, str.length());
		return FileName;
	}

	public static void getIndex(String _str, int _startIndex) {
		startIndex = _startIndex;
		int count = _str.indexOf("/", _startIndex + 1);
		if (count != -1) {
			getIndex(_str, count);
		}
		// return startIndex;
	}

	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressLint("NewApi")
	public static Bitmap GetLocalOrNetBitmap(String url) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());
		Bitmap bitmap = null;
		InputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(

			new URL(url).openStream(), 2 * 1024);
			final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
			out = new BufferedOutputStream(dataStream, 2 * 1024);
			copy(in, out);
			out.flush();
			byte[] data = dataStream.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			data = null;
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte[] b = new byte[2 * 1024];
		int read;
		while ((read = in.read(b)) != -1) {
			out.write(b, 0, read);
		}
	}

}
