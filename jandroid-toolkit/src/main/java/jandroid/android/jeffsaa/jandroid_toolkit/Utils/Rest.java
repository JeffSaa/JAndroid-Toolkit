package jandroid.android.jeffsaa.jandroid_toolkit.Utils;

import android.content.Context;
import android.content.pm.PackageManager;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.onehilltech.metadata.ManifestMetadata;

import cz.msebera.android.httpclient.Header;
import jandroid.android.jeffsaa.jandroid_toolkit.BuildConfig;
import jandroid.android.jeffsaa.jandroid_toolkit.Models.*;

public class Rest {

    private static RestClient client = new RestClient();

    public static void get(String url, RequestParameters params,
                           String server_token_field, String server_token_value,
                           Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.get(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    public static void get(String url, RequestParameters params, Context context, RestHandler responseHandler) {
        client.get(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    public static void post(String url, RequestParameters params,
                            String server_token_field, String server_token_value,
                            Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.post(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    public static void post(String url, RequestParameters params, Context context, RestHandler responseHandler) {
        client.post(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    public static void delete(String url, RequestParameters params,
                              String server_token_field, String server_token_value,
                              Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.delete(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    public static void delete(String url, RequestParameters params, Context context, RestHandler responseHandler) {
        client.delete(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    public static void put(String url, RequestParameters params,
                           String server_token_field, String server_token_value,
                           Context context, RestHandler responseHandler) {
        params.put(server_token_field, getAuthToken(context, server_token_value));
        client.put(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    public static void put(String url, RequestParameters params, Context context, RestHandler responseHandler) {
        client.put(getAbsoluteUrl(url, context), params, getResponseHandler(responseHandler));
    }

    private static String getAbsoluteUrl(String relativeUrl, Context context) {
        try {
            ManifestMetadata metadata = ManifestMetadata.get(context);
            if (BuildConfig.DEBUG)
                return metadata.getValue("JAndroid-Toolkit-Server-Development-URL") + relativeUrl;
            else
                return metadata.getValue("JAndroid-Toolkit-Server-Production-URL") + relativeUrl;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return relativeUrl;
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