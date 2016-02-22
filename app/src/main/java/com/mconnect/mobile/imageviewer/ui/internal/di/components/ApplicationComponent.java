package com.mconnect.mobile.imageviewer.ui.internal.di.components;

import android.content.Context;

import com.mconnect.mobile.imageviewer.data.file.FileManager;
import com.mconnect.mobile.imageviewer.domain.executor.PostExecutionThread;
import com.mconnect.mobile.imageviewer.domain.executor.ThreadExecutor;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.ApplicationModule;
import com.mconnect.mobile.imageviewer.ui.navigation.Navigator;
import com.mconnect.mobile.imageviewer.ui.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constrains this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    Navigator getNavigator();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    AlbumRepository getAlbumRepository();

    FileManager fileManager();
}
