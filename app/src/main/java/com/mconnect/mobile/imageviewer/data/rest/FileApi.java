package com.mconnect.mobile.imageviewer.data.rest;

import com.squareup.okhttp.ResponseBody;


import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.Url;
import rx.Observable;


public interface FileApi {

    @GET
    Observable<ResponseBody> getFile(@Url String url);


}

