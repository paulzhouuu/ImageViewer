package com.mconnect.mobile.imageviewer.data.repository;

import com.mconnect.mobile.imageviewer.TestCase;
import com.mconnect.mobile.imageviewer.data.file.FileManager;
import com.mconnect.mobile.imageviewer.data.rest.FileApi;
import com.mconnect.mobile.imageviewer.data.rest.ImgurApi;
import com.mconnect.mobile.imageviewer.ui.internal.di.modules.DataModule;
import com.squareup.okhttp.OkHttpClient;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by pzhou on 16-02-11.
 */
public class RepositoryTestCase extends TestCase {
    protected DataModule dataModule = new DataModule();

    protected FileManager fileManager;
    protected FileApi fileApi;
    protected OkHttpClient fileApiClient;
    protected ImgurApi imgurApi;
    protected OkHttpClient restApiClient;
    @Before
    public void setUp() {
        super.setUp();

        fileManager = dataModule.provideFileManager(fakeContext);
        fileApiClient = dataModule.provideFileApiHttpClient();
        fileApi = dataModule.provideFileApi(dataModule.provideFileApiRetrofit(fileApiClient));
        restApiClient = dataModule.provideRestApiHttpClient();
        imgurApi = dataModule.provideRestApi(dataModule.provideRestApiRetrofit(restApiClient));

    }

    @Test
    public void dummy(){
        assertThat(1, is(1));
    }

}
