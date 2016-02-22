package com.mconnect.mobile.imageviewer.ui.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mconnect.mobile.imageviewer.R;
import com.mconnect.mobile.imageviewer.ui.AndroidApplication;
import com.mconnect.mobile.imageviewer.ui.internal.di.HasComponent;
import com.mconnect.mobile.imageviewer.ui.internal.di.components.ActivityComponent;
import com.mconnect.mobile.imageviewer.ui.internal.di.components.ApplicationComponent;
import com.mconnect.mobile.imageviewer.ui.internal.di.components.DaggerActivityComponent;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.ActivityModule;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.DomainModule;
import com.mconnect.mobile.imageviewer.ui.navigation.Navigator;

import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity implements HasComponent<ActivityComponent> {

    @Inject
     Navigator navigator;

    private ActivityComponent activityComponent;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        initializeActivityComponent();
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void hideStatusBar(){
        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        } else {
            finish();
        }
    }

    protected void enableBackNavigation(boolean isEnabled) {
        if (isEnabled) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
    }

    public void popToStartFragment() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentManager.executePendingTransactions();
        }
    }

    public void popFragment() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    protected void addFragmentToBackStack(int containerViewId, android.support.v4.app.Fragment fragment, String tag) {
        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(containerViewId, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
        enableBackNavigation(true);
    }
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    private void initializeActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule()).build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
