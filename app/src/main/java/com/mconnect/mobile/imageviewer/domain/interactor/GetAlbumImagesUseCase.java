package com.mconnect.mobile.imageviewer.domain.interactor;

import com.mconnect.mobile.imageviewer.domain.executor.PostExecutionThread;
import com.mconnect.mobile.imageviewer.domain.executor.ThreadExecutor;
import com.mconnect.mobile.imageviewer.domain.model.Album;
import com.mconnect.mobile.imageviewer.domain.model.Image;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.domain.repository.ImageRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by pzhou on 2016-02-20.
 */
public class GetAlbumImagesUseCase extends  UseCase{
    private AlbumRepository albumRepository;
    private ImageRepository imageRepository;
    private String albumId;
    private PublishSubject<String> imagesSubject;

    @Inject
    public GetAlbumImagesUseCase(AlbumRepository albumRepository, ImageRepository imageRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.albumRepository = albumRepository;
        this.imageRepository = imageRepository;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }


    @Override
    protected Observable buildUseCaseObservable() {

        return albumRepository.getAlbum(this.albumId).flatMap(new Func1<Album, Observable<String>>() {
            @Override
            public Observable<String> call(Album album) {
                imagesSubject =  PublishSubject.create();
                Observable<String>[] imageDownloadObservables = new Observable[album.getImages().size()];
                for (int i = 0; i < album.getImages().size(); i++) {
                    imageDownloadObservables[i] = imageRepository.getImage(album.getImages().get(i).getLink())
                                                                 .subscribeOn(Schedulers.from(threadExecutor));

                }

                return Observable.merge(imageDownloadObservables);
            }
        });
    }

}
