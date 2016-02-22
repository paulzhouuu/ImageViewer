package com.mconnect.mobile.imageviewer.ui.internal.di.modules;

import android.app.Activity;

import com.mconnect.mobile.imageviewer.ui.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module(includes = {
        DomainModule.class})
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }


}
