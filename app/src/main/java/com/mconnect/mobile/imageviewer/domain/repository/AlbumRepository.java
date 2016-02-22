package com.mconnect.mobile.imageviewer.domain.repository;


import com.mconnect.mobile.imageviewer.domain.model.Album;

import rx.Observable;


public interface AlbumRepository {
    Observable<Album> getAlbum(String albumId);
}
