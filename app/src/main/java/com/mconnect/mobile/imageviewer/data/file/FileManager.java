package com.mconnect.mobile.imageviewer.data.file;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by pzhou on 2016-02-12.
 */

@Singleton
public class FileManager {
    private Context context;
    @Inject
    public FileManager(Context context) {
        this.context = context;
    }

    public String getAppFolderPath() {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.dataDir;
        } catch (PackageManager.NameNotFoundException e) {
            Timber.e(e, "get App folder failed");
            return "";
        }
    }

    public File getAppFolder() {
        String appFolderPath = getAppFolderPath();

        if (!appFolderPath.equals("")){
            return new File(appFolderPath);
        }else {
            return null;
        }
    }
    public String getFileNameFromUrl(String urlString){
        String filename = urlString.substring(urlString.lastIndexOf("/")+1);

        return filename;
    }
    public String saveByteStream(String urlString, byte[] bytes){
        String fileName = getFileNameFromUrl(urlString);
        File path = getAppFolder();
        File file = new File(path, fileName);
        try {


            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(bytes);
            bos.flush();
            bos.close();
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            Timber.e(e, "saving file %s not found", fileName);
        } catch (IOException e) {
            Timber.e(e, "saving byte array for file %s failed", fileName);
        }
        return null;
    }
}
