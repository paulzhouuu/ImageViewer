package com.mconnect.mobile.imageviewer.ui.internal.di.modules;

import com.mconnect.mobile.imageviewer.domain.executor.PostExecutionThread;
import com.mconnect.mobile.imageviewer.domain.executor.ThreadExecutor;
import com.mconnect.mobile.imageviewer.domain.interactor.GetAlbumUseCase;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.ui.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides concrete implementations of the Abstract class "UseCase", for MVP presenters.
 */
@Module
public class DomainModule {

    public DomainModule() {
    }

    @Provides
    @PerActivity
    GetAlbumUseCase provideGetAlbumUseCase(AlbumRepository albumRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetAlbumUseCase(albumRepository, threadExecutor, postExecutionThread);
    }

/*  //Below pattern also works.  Maybe more cumbersome, not sure what the advantages are.
    @Provides
    @PerActivity
    GetStubEntityDetails provideGetStubEntityDetailsUseCase(StubEntityRepository stubEntityRepository,
                                                            ThreadExecutor threadExecutor,
                                                            PostExecutionThread postExecutionThread) {
        return new GetStubEntityDetails(stubEntityRepository, threadExecutor, postExecutionThread);
    }
*/
}