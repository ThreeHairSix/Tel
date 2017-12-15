package com.nptel.tel.http;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    private HttpRequestPost.HttpRequestListener listener;
    private static String strJson = "";
    private static byte[] content;

    /**
     *
     */
    public synchronized void HttpPostData(String url,
                                          JSONObject json, HttpRequestPost.HttpRequestListener listener) {
        this.listener = listener;

        try {
            strJson = json.toString();
            content = strJson.getBytes("utf-8");
            URL my_url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(true);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "*/*");
            connection.setReadTimeout(30 * 1000);
            connection.setConnectTimeout(30 * 1000);
            connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());
            out.write(content, 0, content.length);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = "";
            String returnLine = "";
            while ((line = reader.readLine()) != null) {
                returnLine += line;
            }
            reader.close();
            connection.disconnect();
            JSONObject json1 = new JSONObject(returnLine);
            int code1 = json1.optInt("Code");
            if (code1 == 200) {
                listener.HttpRequestSuccessListener(returnLine);
            } else {
                sendListener(json1.optString("Msg"));
                setExceptionHttp(url, strJson, returnLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendListener("请求失败，请重试");
            setExceptionHttp(url, strJson, e.getMessage());
        } catch (OutOfMemoryError e) {
            sendListener("请求失败，请重试");
        }
    }

    private void sendListener(String msg) {
        if (listener != null) {
            listener.HttpRequestFailListener(msg);
        }
    }

    private void setExceptionHttp(String url, String str, String msg) {
        if (!url.contains("AddException")) {
        }
    }

}
