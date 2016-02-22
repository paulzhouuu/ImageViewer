package com.mconnect.mobile.imageviewer.domain.interactor;

import com.mconnect.mobile.imageviewer.TestCase;
import com.mconnect.mobile.imageviewer.data.executor.JobExecutor;
import com.mconnect.mobile.imageviewer.data.repository.AlbumDataRepository;
import com.mconnect.mobile.imageviewer.data.repository.ImageDataRepository;
import com.mconnect.mobile.imageviewer.data.repository.RepositoryTestCase;
import com.mconnect.mobile.imageviewer.domain.executor.PostExecutionThread;
import com.mconnect.mobile.imageviewer.domain.executor.ThreadExecutor;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.domain.repository.ImageRepository;
import com.mconnect.mobile.imageviewer.ui.UIThread;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.DataModule;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.DomainModule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.Subscriber;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

public class UseCaseTest extends RepositoryTestCase {
    protected DataModule dataModule = new DataModule();
    protected AlbumRepository albumRepository;
    protected ImageRepository imageRepository;

    protected ThreadExecutor mockThreadExecutor;

    protected PostExecutionThread mockPostExecutionThread;

    @Before
    public void setUp() {
        super.setUp();

        mockThreadExecutor = new JobExecutor();
        mockPostExecutionThread = new UIThread();
        mockPostExecutionThread.setScheduler(Schedulers.trampoline());
        albumRepository = dataModule.provideAlbumRepository(new AlbumDataRepository(imgurApi));
        imageRepository = dataModule.provideItemRepository(new ImageDataRepository(fileApi,fileManager ));
    }

    @Test
    public void dummy(){
        assertThat(1, is(1));
    }
}