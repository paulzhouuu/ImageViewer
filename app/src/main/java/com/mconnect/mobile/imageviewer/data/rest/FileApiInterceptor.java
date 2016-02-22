package com.mconnect.mobile.imageviewer.data.rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * Created by pzhou on 16-01-01.
 */
public class FileApiInterceptor implements Interceptor {


    public FileApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        String urlString = original.urlString();

        Request.Builder requestBuilder = original.newBuilder()

                .method(original.method(), original.body());


        Request request = requestBuilder.build();


        Response response = chain.proceed(request);


        return response;
    }
}
