package com.mconnect.mobile.imageviewer.data.repository;

import com.mconnect.mobile.imageviewer.data.file.FileManager;
import com.mconnect.mobile.imageviewer.data.rest.FileApi;
import com.mconnect.mobile.imageviewer.data.rest.ImgurApi;
import com.mconnect.mobile.imageviewer.domain.model.Album;
import com.mconnect.mobile.imageviewer.domain.model.AlbumResource;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.domain.repository.ImageRepository;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import timber.log.Timber;

@Singleton
public class ImageDataRepository implements ImageRepository {

    private FileApi fileApi;
    private FileManager fileManager;
    @Inject
    public ImageDataRepository(FileApi fileApi, FileManager fileManager) {

        this.fileApi = fileApi;
        this.fileManager = fileManager;

    }


    @Override
    public Observable<String> getImage(final String imageUrl) {
        Timber.d("get Image %s started", imageUrl);
        return fileApi.getFile(imageUrl).map(new Func1<ResponseBody, String>() {
            @Override
            public String call(ResponseBody responseBody) {
                String filePath = null;
                try {
                    filePath = fileManager.saveByteStream(imageUrl, responseBody.bytes());
                } catch (IOException e) {
                    Timber.e(e, "Get response body bytes failed");
                }
                Timber.d("get Image %s finished", imageUrl);
                return filePath;
            }
        });
    }
}