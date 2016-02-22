package com.mconnect.mobile.imageviewer.ui;

import android.app.Application;

import com.mconnect.mobile.imageviewer.BuildConfig;
import com.mconnect.mobile.imageviewer.data.log.LumberYard;
import com.mconnect.mobile.imageviewer.ui.internal.di.components.ApplicationComponent;
import com.mconnect.mobile.imageviewer.ui.internal.di.components.DaggerApplicationComponent;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.ApplicationModule;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.DataModule;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.DomainModule;

import timber.log.Timber;

//import com.squareup.leakcanary.LeakCanary;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeLogging();
        this.initializeInjector();
//        this.initializeLeakDetection();
    }

    private void initializeLogging() {
        if (BuildConfig.DEBUG) {
            LumberYard.create(this);
            LumberYard.cleanUp();

            Timber.plant(new Timber.DebugTree());
            Timber.plant(LumberYard.tree());

        }
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
