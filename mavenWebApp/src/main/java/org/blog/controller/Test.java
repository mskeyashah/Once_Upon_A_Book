package org.blog.controller;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static final int HTTP_OK = 200;
    public static final String SERVER_URL = "http://localhost:8080/mavenWebApp/";

    public static String doGet(final String url) throws RuntimeException,IOException {

        final HttpClient httpClient = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
        HttpGet httpget = new HttpGet(SERVER_URL + url);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        Scanner s = new Scanner(instream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return result;
    }


}