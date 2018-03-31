package jandroid.android.jeffsaa.jandroid_toolkit.Utils;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import jandroid.android.jeffsaa.jandroid_toolkit.Models.RequestParameters;
import jandroid.android.jeffsaa.jandroid_toolkit.Models.RestClient;
import jandroid.android.jeffsaa.jandroid_toolkit.Models.RestHandler;
import jandroid.android.jeffsaa.jandroid_toolkit.Models.RestHeader;

public class Rest {

    private static final String BASE_URL = jandroid.android.jeffsaa.jandroid_toolkit.BuildConfig.SERVER_URL;

    private static RestClient client = new RestClient();

    public static void get(String url, RequestParameters params,
                           String server_token_field, String server_token_value,
                           Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.get(getAbsoluteUrl(url), params, getResponseHandler(responseHandler));
    }

    public static void get(String url, RequestParameters params, RestHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, getResponseHandler(responseHandler));
    }

    public static void post(String url, RequestParameters params,
                            String server_token_field, String server_token_value,
                            Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.post(getAbsoluteUrl(url), params, getResponseHandler(responseHandler));
    }

    public static void post(String url, RequestParameters params, RestHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, getResponseHandler(responseHandler));
    }

    public static void delete(String url, RequestParameters params,
                            String server_token_field, String server_token_value,
                            Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.delete(getAbsoluteUrl(url), params, getResponseHandler(responseHandler));
    }

    public static void delete(String url, RequestParameters params, RestHandler responseHandler){
        client.delete(url, params, getResponseHandler(responseHandler));
    }

    public static void put(String url, RequestParameters params,
                            String server_token_field, String server_token_value,
                            Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.put(getAbsoluteUrl(url), params, getResponseHandler(responseHandler));
    }

    public static void put(String url, RequestParameters params, RestHandler responseHandler){
        client.put(url, params, getResponseHandler(responseHandler));
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static String getAuthToken(Context context, String server_token_value){
        return PreferManager.get(context, server_token_value, "");
    }

    private static RestHeader[] toRestHeaders(Header[] hs){
        RestHeader[] headers = new RestHeader[hs.length];
        for (int i = 0; i < hs.length; i++)
            headers[i] = new RestHeader(hs[i]);
        return  headers;
    }

    private static AsyncHttpResponseHandler getResponseHandler(final RestHandler restHandler){
        return new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                restHandler.onSuccess(statusCode, toRestHeaders(headers), responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                restHandler.onFailure(statusCode, toRestHeaders(headers), responseBody, error);
            }
        };
    }

}