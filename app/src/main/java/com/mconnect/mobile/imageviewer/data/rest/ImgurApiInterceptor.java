package com.mconnect.mobile.imageviewer.data.rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ImgurApiInterceptor implements Interceptor {


    public ImgurApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", "Client-ID 3b6badf1f998f26")
                .method(original.method(), original.body());


        Request request = requestBuilder.build();

        Response response = chain.proceed(request);
        String bodyString = response.body().string();

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
    }
}
