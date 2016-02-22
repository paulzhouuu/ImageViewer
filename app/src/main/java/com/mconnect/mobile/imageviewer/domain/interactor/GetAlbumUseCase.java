package com.mconnect.mobile.imageviewer.domain.interactor;

import com.mconnect.mobile.imageviewer.domain.executor.PostExecutionThread;
import com.mconnect.mobile.imageviewer.domain.executor.ThreadExecutor;
import com.mconnect.mobile.imageviewer.domain.model.Album;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.domain.repository.ImageRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by pzhou on 2016-02-20.
 */
public class GetAlbumUseCase extends  UseCase{
    private AlbumRepository albumRepository;
    private String albumId;

    @Inject
    public GetAlbumUseCase(AlbumRepository albumRepository,  ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.albumRepository = albumRepository;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }


    @Override
    protected Observable buildUseCaseObservable() {

        return albumRepository.getAlbum(this.albumId);
    }
}
