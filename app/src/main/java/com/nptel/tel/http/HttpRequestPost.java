package com.nptel.tel.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.nptel.tel.activity.TelApplication;

import org.json.JSONObject;

public class HttpRequestPost {
    HttpRequestListener listener;
    int SUCCESS = 0;
    int FAIL = 1;
    String result1;

    public static HttpRequestPost getRequest() {
        return new HttpRequestPost();
    }

    public void post(String urlStr, JSONObject params,
                     HttpRequestListener listener1) {
        listener = listener1;
        HttpRequestPost request = new HttpRequestPost();
        request.setRequestParams(urlStr, params, new HttpRequestListener() {

            @Override
            public void HttpRequestSuccessListener(String result) {
                Message msg = new Message();
                msg.what = SUCCESS;
                result1 = result;
                mHandler.sendMessage(msg);
            }

            @Override
            public void HttpRequestFailListener(String result) {
                Message msg = new Message();
                msg.what = FAIL;
                result1 = result;
                mHandler.sendMessage(msg);
            }
        });
    }

    private void setRequestParams(final String urlStr, final JSONObject params,
                                  final HttpRequestListener listener) {

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                HttpRequest request = new HttpRequest();
                request.HttpPostData(urlStr, params, listener);
            }
        }.start();
    }

    public interface HttpRequestListener {
        public void HttpRequestSuccessListener(String result);

        public void HttpRequestFailListener(String result);
    }

    Handler mHandler = new Handler(TelApplication.getInstance().getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SUCCESS) {
                listener.HttpRequestSuccessListener(result1);
            } else if (msg.what == FAIL) {
                listener.HttpRequestFailListener(result1);
            }
            super.handleMessage(msg);
        }
    };

}
