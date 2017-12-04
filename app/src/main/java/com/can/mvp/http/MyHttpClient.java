package com.can.mvp.http;

import com.can.mvp.util.LogUtil;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;

/**
 * Created by can on 2017/12/4.
 * 网络请求类
 */


public class MyHttpClient {
    public static String API_URL = "";
    public static KJHttp client;
    public static String Cookie = "";

    public MyHttpClient() {
    }

    public static void initHttp(String host) {
        API_URL = "http://" + host + "/";
        HttpConfig httpConfig = new HttpConfig();
        HttpConfig.TIMEOUT = 25000;
        client = new KJHttp();
        client.setConfig(httpConfig);
    }

    public static KJHttp getHttpClient() {
        return client;
    }

    public static void get(String partUrl, MyResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求方式：GET 无参");
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl));
    }

    public static void get(String partUrl, HttpParams params, MyResponseHandler handler) {
        params.putHeaders("cookie", Cookie);
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求方式：GET 有参");
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求参数 ：" + params.getUrlParams().toString());
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl));
    }

    public static void post(String partUrl, HttpParams params, MyResponseHandler handler) {
        params.putHeaders("cookie", Cookie);
        client.post(getAbsoluteApiUrl(partUrl), params, handler);
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求方式：POST");
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求参数 ：" + params.getUrlParams().toString());
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求头  ：" + params.getHeaders());
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求URL：" + getAbsoluteApiUrl(partUrl));
    }

    public static void postJsonUpServer(String url, String jsonStr, MyResponseHandler responseHandler) {
        HttpParams params = new HttpParams();
        LogUtil.i(LogUtil.CP, MyHttpClient.class + "上传的 json :" + jsonStr);
        params.putJsonParams(jsonStr);
        client.jsonPost(url, params, responseHandler);
    }

    public static String getAbsoluteApiUrl(String partUrl) {
        String url = API_URL + partUrl;
        return url;
    }

    public static void getAbsoluteUrl(String partUrl, MyResponseHandler handler) {
        client.get(partUrl, handler);
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求方式：GET 无参");
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求URL：" + partUrl);
    }

    public static void postAbsoluteUrl(String url, HttpParams params, MyResponseHandler handler) {
        params.putHeaders("cookie", Cookie);
        client.post(url, params, handler);
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求方式：POST");
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求参数 ：" + params.getUrlParams().toString());
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求头  ：" + params.getHeaders());
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求URL：" + url);
        LogUtil.d(LogUtil.CP, MyHttpClient.class + " 请求json：" + params.getJsonParams());
    }

    public static String getApiUrl() {
        return API_URL;
    }
}

