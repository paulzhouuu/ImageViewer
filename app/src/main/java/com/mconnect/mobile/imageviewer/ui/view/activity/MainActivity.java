package com.mconnect.mobile.imageviewer.ui.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.mconnect.mobile.imageviewer.R;
import com.mconnect.mobile.imageviewer.domain.interactor.DefaultSubscriber;
import com.mconnect.mobile.imageviewer.domain.interactor.GetAlbumUseCase;
import com.mconnect.mobile.imageviewer.domain.model.Album;
import com.mconnect.mobile.imageviewer.ui.view.adapter.RecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ActionBarDrawerToggle drawerToggle;


    @Bind(R.id.activity_main_drawer)
    protected DrawerLayout drawerLayout;


    private RecyclerView.LayoutManager layoutManager;
    @Bind(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Inject
     GetAlbumUseCase getAlbumUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);
        ButterKnife.bind(this);

        initToolbar();
        initNavigationDrawer();
        initRecycleView();
    }

    private void initRecycleView() {
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        getAlbumUseCase.setAlbumId("DeX37");
        getAlbumUseCase.execute(new DefaultSubscriber<Album>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "getAlbum failed");
            }

            @Override
            public void onNext(Album a) {
                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, a.getImages());
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }


    private void initNavigationDrawer() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0) {
            public void onDrawerClosed(View drawerView) {
                supportInvalidateOptionsMenu();
                drawerToggle.syncState();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                drawerToggle.syncState();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerToggle.isDrawerIndicatorEnabled()) {
                    drawerToggle.onOptionsItemSelected(item);
                } else {
                    onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
