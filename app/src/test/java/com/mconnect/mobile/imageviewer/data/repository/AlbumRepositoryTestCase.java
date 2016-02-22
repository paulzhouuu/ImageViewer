package com.mconnect.mobile.imageviewer.data.repository;

import com.mconnect.mobile.imageviewer.domain.model.Album;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;
import timber.log.Timber;

/**
 * Created by pzhou on 2016-02-20.
 */
public class AlbumRepositoryTestCase extends RepositoryTestCase {
    private AlbumRepository albumRepository;


    @Before
    public void setUp() {
        super.setUp();
        albumRepository = new AlbumDataRepository(imgurApi);

    }
    @Test
    public void testGetAlbum(){
        TestSubscriber<Album> subscriber = new TestSubscriber<Album>() {

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Timber.e(e, "download album failed");
            }
        };

        albumRepository.getAlbum("DeX37").subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        Timber.i(subscriber.getOnNextEvents().toString());

    }
}
