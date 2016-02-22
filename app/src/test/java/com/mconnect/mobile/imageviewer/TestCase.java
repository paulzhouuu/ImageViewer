package com.mconnect.mobile.imageviewer;

import android.content.Context;

import com.mconnect.mobile.imageviewer.BuildConfig;
import com.mconnect.mobile.imageviewer.data.log.LumberYard;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import timber.log.Timber;

/**
 * Base class for Robolectric data layer tests.
 * Inherit from this class to create a test.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(
        constants = BuildConfig.class, sdk=21
)
public abstract class TestCase {
    protected Context fakeContext;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ShadowLog.stream = System.out;
        fakeContext = RuntimeEnvironment.application.getApplicationContext();

        if (BuildConfig.DEBUG) {
            LumberYard.create(RuntimeEnvironment.application);
            LumberYard.cleanUp();

            Timber.plant(new Timber.DebugTree());
            Timber.plant(LumberYard.tree());
        }
    }
}
