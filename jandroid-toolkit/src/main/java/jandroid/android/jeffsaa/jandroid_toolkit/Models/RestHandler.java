package jandroid.android.jeffsaa.jandroid_toolkit.Models;

public interface RestHandler{

    void onSuccess(int statusCode, RestHeader[] headers, byte[] response);
    void onFailure(int statusCode, RestHeader[] headers, byte[] errorResponse, Throwable e);

}