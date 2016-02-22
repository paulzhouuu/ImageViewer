package com.mconnect.mobile.imageviewer.domain.interactor;

import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;
import timber.log.Timber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GetAlbumImagesUseCaseTest extends UseCaseTest {

    private GetAlbumImagesUseCase useCase;



    @Before
    public void setUp() {
        super.setUp();

        this.useCase = new GetAlbumImagesUseCase(albumRepository, imageRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBuildUseCaseObservableReturnCorrectResult() {

        TestSubscriber<String> subscriber = new TestSubscriber<String>() {

            @Override
            public void onNext(String s) {

                Timber.d("UI received %s", s);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Timber.e(e, "download album images failed");
            }
        };
     //   TestScheduler testScheduler = new TestScheduler();
 //       given(mockPostExecutionThread.getScheduler()).willReturn(testScheduler);

        useCase.setAlbumId("DeX37");
        useCase.execute(subscriber);

        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        subscriber.assertCompleted();

        Timber.i(subscriber.getOnNextEvents().toString());
    }


}