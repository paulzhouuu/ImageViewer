package com.mconnect.mobile.imageviewer.domain.interactor;

import com.mconnect.mobile.imageviewer.domain.model.Album;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;
import timber.log.Timber;

public class GetAlbumUseCaseTest extends UseCaseTest {

    private GetAlbumUseCase useCase;



    @Before
    public void setUp() {
        super.setUp();

        this.useCase = new GetAlbumUseCase(albumRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBuildUseCaseObservableReturnCorrectResult() {

        TestSubscriber<Album> subscriber = new TestSubscriber<Album>() {

            @Override
            public void onNext(Album s) {

                Timber.d("UI received %s", s.toString());
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Timber.e(e, "download album  failed");
            }
        };


        useCase.setAlbumId("DeX37");
        useCase.execute(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        Timber.i(subscriber.getOnNextEvents().toString());
    }


}