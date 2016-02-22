package com.mconnect.mobile.imageviewer.data.repository;

import com.mconnect.mobile.imageviewer.data.rest.ImgurApi;
import com.mconnect.mobile.imageviewer.domain.model.Album;
import com.mconnect.mobile.imageviewer.domain.model.AlbumResource;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class AlbumDataRepository implements AlbumRepository {

    private ImgurApi imgurApi;

    @Inject
    public AlbumDataRepository(ImgurApi imgurApi) {

        this.imgurApi = imgurApi;

    }


    @Override
    public Observable<Album> getAlbum(String albumId) {
        return imgurApi.getAlbum(albumId).map(new Func1<AlbumResource, Album>() {
            @Override
            public Album call(AlbumResource albumResource) {
                Album album = albumResource.getAlbum();
                return album;
            }
        });
    }
}