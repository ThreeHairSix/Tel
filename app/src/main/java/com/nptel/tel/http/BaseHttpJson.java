package com.nptel.tel.http;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * 请求基础类
 */
public abstract class BaseHttpJson {
    protected JSONObject json =new JSONObject();

    protected abstract JSONObject setJson(JSONObject json);

    protected abstract void success(String results);

    protected abstract void fail(String results);

    protected void httpPost(String url) {
        HttpRequestPost.getRequest().post( url,setJson(json), new HttpRequestPost.HttpRequestListener() {

                    @Override
                    public void HttpRequestSuccessListener(String results) {
                        JSONObject json200;
                        try {
                            json200 = new JSONObject(results);
                            int code = json200.optInt("Code");
                            if (code != 200) {
                                fail(json200.optString("Msg"));
                            } else {
                                success(results);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            fail(e.getMessage());
                        }

                    }

                    @Override
                    public void HttpRequestFailListener(String result) {
                        fail(result);
                    }
                });
    }

}
