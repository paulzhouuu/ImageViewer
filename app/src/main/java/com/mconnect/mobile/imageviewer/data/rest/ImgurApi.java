package com.mconnect.mobile.imageviewer.data.rest;

import com.mconnect.mobile.imageviewer.domain.model.AlbumResource;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ImgurApi {

    @GET ("3/album/{id}")
    Observable<AlbumResource> getAlbum(
            @Path("id") String albumId);


}

