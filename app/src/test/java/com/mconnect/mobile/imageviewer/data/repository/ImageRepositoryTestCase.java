package com.mconnect.mobile.imageviewer.data.repository;

import com.mconnect.mobile.imageviewer.domain.model.Album;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.domain.repository.ImageRepository;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;
import timber.log.Timber;

/**
 * Created by pzhou on 2016-02-20.
 */
public class ImageRepositoryTestCase extends RepositoryTestCase {
    private ImageRepository repository;


    @Before
    public void setUp() {
        super.setUp();
        repository = new ImageDataRepository(fileApi, fileManager);

    }
    @Test
    public void testGetImage(){
        TestSubscriber<String> subscriber = new TestSubscriber<String>() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Timber.e(e, "download image failed");
            }
        };

        repository.getImage("http://i.imgur.com/OK802G4.jpg").subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        Timber.i(subscriber.getOnNextEvents().toString());

    }
}
