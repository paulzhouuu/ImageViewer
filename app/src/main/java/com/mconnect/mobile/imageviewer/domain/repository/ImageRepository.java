package com.mconnect.mobile.imageviewer.domain.repository;


import com.mconnect.mobile.imageviewer.domain.model.Album;

import rx.Observable;


public interface ImageRepository {
    Observable<String> getImage(String imageUrl);
}
