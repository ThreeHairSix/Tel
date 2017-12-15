package com.nptel.tel.http;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 上传工具类流上传
 */
public class UploadUtil {
	private static UploadUtil uploadUtil;
	private static final String BOUNDARY = UUID.randomUUID().toString(); // 边界标识
																			// 随机生成
	private static final String PREFIX = "--";
	private static final String LINE_END = "\r\n";
	private static final String CONTENT_TYPE = "multipart/form-data"; // 内容类型
	private int readTimeOut = 30 * 1000; // 读取超时
	private int connectTimeout = 30 * 1000; // 超时时间
	// private static final String CHARSET = "utf-8"; // 设置编码
	private ExecutorService executorService;
	/** 上传成功 **/
	public static final int UPLOAD_SUCCESS_CODE = 1;
	/** 服务器出错 **/
	public static final int UPLOAD_SERVER_ERROR_CODE = 3;
	public static final Object OBJ = new Object();

	/**
	 * 单例模式获取上传工具类
	 */
	public static UploadUtil getInstance() {
		if (null == uploadUtil) {
			synchronized (OBJ) {
				if (null == uploadUtil) {
					uploadUtil = new UploadUtil();
					uploadUtil.executorService = Executors
							.newFixedThreadPool(1);
				}
			}
		}
		return uploadUtil;
	}

	/**
	 * android上传文件到服务器
	 * @param RequestURL
	 *            请求的URL
	 */
	public void uploadFile(final Map<String, File> fileParam,
			final String RequestURL, final Map<String, String> param) {

		executorService.execute(new Runnable() {
			public void run() {
				try {
					toUploadFile(fileParam, RequestURL, param);
				} catch (EOFException e) {
					e.printStackTrace();
					String str = e.getMessage();
				}
			}
		});
	}

	private void toUploadFile(Map<String, File> fileParam, String RequestURL,
			Map<String, String> param) throws EOFException {
		String result = null;
		String path = null;
		HttpURLConnection conn = null;
		InputStream is = null;
		DataOutputStream dos = null;
		try {
			URL url = new URL(RequestURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(readTimeOut);
			conn.setConnectTimeout(connectTimeout);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("Connection", "close");
			conn.setRequestProperty("Charset", "utf-8"); // 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			// conn.setRequestProperty("Accept-Encoding", "*");
			// conn.setRequestProperty("Content-type",
			// "application/x-java-serialized-object");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
					+ BOUNDARY);
			/**
			 * 当文件不为空，把文件包装并且上传
			 */
			dos = new DataOutputStream(conn.getOutputStream());
			StringBuffer sb = null;
			String params = "";

			/***
			 * 以下是用于上传参数
			 */
			if (param != null && param.size() > 0) {
				Iterator<String> it = param.keySet().iterator();
				// LogUtil.e(it.toString());
				while (it.hasNext()) {
					sb = null;
					sb = new StringBuffer();
					String key = it.next();
					String value = param.get(key);
					sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
					sb.append("Content-Disposition: form-data; name=\"")
							.append(key).append("\"").append(LINE_END)
							.append(LINE_END);
					sb.append(value).append(LINE_END);
					params = sb.toString();
					dos.write(params.getBytes());
				}
			}

			sb = null;
			params = null;
			if (fileParam != null && fileParam.size() > 0) {
				Iterator<String> it = fileParam.keySet().iterator();
				while (it.hasNext()) {
					sb = null;
					params = null;
					sb = new StringBuffer();
					String fileKey = it.next();
					File file = fileParam.get(fileKey);
					if (TextUtils.isEmpty(path) || !path.endsWith(".mp4")) {
						path = file.getPath();
					}
					/**
					 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
					 * filename是文件的名字，包含后缀名的 比如:abc.png
					 */
					sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
					sb.append("Content-Disposition:form-data; name=\""
							+ fileKey + "\"; filename=\"" + file.getName()
							+ "\"" + LINE_END);
					sb.append("Content-Type:image/pjpeg, */*" + LINE_END); // 这里配置的Content-type很重要的
																			// ，用于服务器端辨别文件的类型的
					sb.append(LINE_END);
					params = sb.toString();
					sb = null;
					dos.write(params.getBytes());
					/** 上传文件 */
					String str = file.toString().toLowerCase();
//					if (str.endsWith(".jpg") || str.endsWith(".png")) {
//						ShwBitMapTools t = new ShwBitMapTools();
//						is = t.scal(file.toString());
//					} else {
						is = new FileInputStream(file);
//					}
					if (is == null) {
						dos.flush();
						return;
					}
					byte[] bytes = new byte[1024 * 1024];
					int len = 0;
					while ((len = is.read(bytes)) != -1) {
						dos.write(bytes, 0, len);
					}
					// is.reset();
					is.close();
					dos.write(LINE_END.getBytes());
				}
			}

			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
					.getBytes();
			dos.write(end_data);
			dos.flush();
			/**
			 * 获取响应码 200=成功 当响应成功，获取响应的流
			 */
			int res = conn.getResponseCode();

			if (res == 200) {
				InputStream input = conn.getInputStream();
				StringBuffer sb1 = new StringBuffer();
				int ss;
				while ((ss = input.read()) != -1) {
					sb1.append((char) ss);
				}
				result = sb1.toString();
				// LogUtil.e("UploadUtil", "上传结果==》" + result);
				// ~/updatetem/2016/09/09/084655001747407.jpg
				Message msg = new Message();
				try {
					JSONObject json = new JSONObject(result);
					int code = json.optInt("Code");
					if (code == 200) {
						msg.what = UPLOAD_SUCCESS_CODE;
						json.put("path", path);
						result = json.toString();
						msg.obj = result;
					} else {
						msg.what = UPLOAD_SERVER_ERROR_CODE;
						msg.obj = path;
					}
				} catch (JSONException e) {
					msg.what = UPLOAD_SERVER_ERROR_CODE;
					e.printStackTrace();
					msg.obj = path;
				}
				handler.sendMessage(msg);
			} else {
				Message msg = new Message();
				msg.what = UPLOAD_SERVER_ERROR_CODE;
				msg.obj = path;
				handler.sendMessage(msg);
			}
		} catch (MalformedURLException e) {
			String msg1 = e.getMessage();
			Message msg = new Message();
			msg.what = UPLOAD_SERVER_ERROR_CODE;
			msg.obj = path;
			handler.sendMessage(msg);
			e.printStackTrace();
		} catch (IOException e) {
			String msg1 = e.getMessage();
			Message msg = new Message();
			msg.what = UPLOAD_SERVER_ERROR_CODE;
			msg.obj = path;
			handler.sendMessage(msg);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				if (dos != null) {
					try {
						dos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				conn.disconnect();
			}
		}
	}

	public static interface OnUploadProcessListener {
		/**
		 * 上传成功
		 * 
		 * @param responseCode
		 * @param message
		 */
		public void upLoadSuccess(int responseCode, String message);

		/**
		 * 上传失败
		 * 
		 * @param responseCode
		 * @param message
		 */
		public void upLoadFail(int responseCode, String message);

	}

	private OnUploadProcessListener onUploadProcessListener;

	public void setOnUploadProcessListener(
			OnUploadProcessListener onUploadProcessListener) {
		this.onUploadProcessListener = onUploadProcessListener;
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (uploadUtil == null) {
				return;
			}
			switch (msg.what) {
			case UPLOAD_SUCCESS_CODE:
				uploadUtil.onUploadProcessListener.upLoadSuccess(msg.what,
						String.valueOf(msg.obj));
				break;
			case UPLOAD_SERVER_ERROR_CODE:
				uploadUtil.onUploadProcessListener.upLoadFail(msg.what,
						String.valueOf(msg.obj));
				break;
			}
		}
	};
}
