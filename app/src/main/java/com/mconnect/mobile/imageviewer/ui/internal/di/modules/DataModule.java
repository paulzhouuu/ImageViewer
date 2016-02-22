package com.mconnect.mobile.imageviewer.ui.internal.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mconnect.mobile.imageviewer.data.db.SqliteDbHelper;
import com.mconnect.mobile.imageviewer.data.file.FileManager;
import com.mconnect.mobile.imageviewer.data.repository.AlbumDataRepository;
import com.mconnect.mobile.imageviewer.data.repository.ImageDataRepository;
import com.mconnect.mobile.imageviewer.data.rest.FileApi;
import com.mconnect.mobile.imageviewer.data.rest.FileApiInterceptor;
import com.mconnect.mobile.imageviewer.data.rest.ImgurApi;
import com.mconnect.mobile.imageviewer.data.rest.ImgurApiInterceptor;
import com.mconnect.mobile.imageviewer.domain.repository.AlbumRepository;
import com.mconnect.mobile.imageviewer.domain.repository.ImageRepository;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class DataModule {

    @Provides
    @Singleton
    public AlbumRepository provideAlbumRepository(AlbumDataRepository factory) {
        return factory;
    }

    @Provides
    @Singleton
    public ImageRepository provideItemRepository(ImageDataRepository repository) {
        return repository;
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .create();
    }
    @Provides
    @Singleton
    @Named("FileApi")
    public OkHttpClient provideFileApiHttpClient() {
        OkHttpClient okClient = new OkHttpClient();
        okClient.interceptors().add(new FileApiInterceptor());

        return okClient;
    }

    @Provides
    @Singleton
    @Named("FileApi")
    public Retrofit provideFileApiRetrofit(@Named("FileApi")OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://toremove.com/")
                .addConverterFactory(GsonConverterFactory.create())  //can add more Converters.  *ORDER MATTERS*
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


    }
    @Provides
    @Singleton
    public FileApi provideFileApi(@Named("FileApi")Retrofit retrofit) {
        return retrofit.create(FileApi.class);
    }
    @Provides
    @Singleton
    public OkHttpClient provideRestApiHttpClient() {

        OkHttpClient okClient = new OkHttpClient();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClient.interceptors().add(loggingInterceptor);
        okClient.interceptors().add(new ImgurApiInterceptor());

        return okClient;
    }

    @Provides
    @Singleton
    public Retrofit provideRestApiRetrofit( OkHttpClient okHttpClient  /*Gson gson*/) {
        return new Retrofit.Builder()
                .baseUrl("https://api.imgur.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())  //can add more Converters.  *ORDER MATTERS*
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    public ImgurApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(ImgurApi.class);
    }

    @Provides
    @Singleton
    public SharedPreferences provideDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    @Provides
    @Singleton
    public FileManager provideFileManager(Context context) {
        return new FileManager(context);
    }

    @Provides
    @Singleton
    public SqliteDbHelper provideSqliteDbHelper(Context context) {
        return new SqliteDbHelper(context);
    }
}
