package com.mconnect.mobile.imageviewer.ui;

import com.mconnect.mobile.imageviewer.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * MainThread (UI Thread) implementation based on a {@link rx.Scheduler}
 * which will execute actions on the Android UI thread
 */
@Singleton
public class UIThread implements PostExecutionThread {

    private Scheduler defaultScheduler = null;
    @Inject
    public UIThread() {
        defaultScheduler = AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler getScheduler() {
        return defaultScheduler;
    }

    @Override
    public void setScheduler(Scheduler scheduler) {
        this.defaultScheduler = scheduler;
    }
}
