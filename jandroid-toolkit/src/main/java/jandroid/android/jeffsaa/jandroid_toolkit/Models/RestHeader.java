package jandroid.android.jeffsaa.jandroid_toolkit.Models;

import cz.msebera.android.httpclient.Header;

public class RestHeader {

    private String name, value;

    public RestHeader(Header header) {

        this.name = header.getName();
        this.value = header.getValue();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}