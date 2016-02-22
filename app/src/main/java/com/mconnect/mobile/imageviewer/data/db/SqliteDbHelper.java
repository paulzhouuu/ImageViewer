package com.mconnect.mobile.imageviewer.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
@Singleton
public class SqliteDbHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "imageviwer.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<Resource, String> resourceDao = null;


    @Inject
    public SqliteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Timber.i("onCreate");
            TableUtils.createTable(connectionSource, Resource.class);


        } catch (SQLException e) {
            Timber.e(e, "Can't create database");
            throw new RuntimeException(e);
        }

    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Timber.i("onUpgrade");
            TableUtils.dropTable(connectionSource, Resource.class, true);

            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Timber.e(e, "Can't drop databases");
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO)
     */
    public Dao<Resource, String> getResourceDao()  {
        if (resourceDao == null) {
            try {
                resourceDao = getDao(Resource.class);
            } catch (SQLException e) {
                Timber.e(e, "Getting Resource Dao failed");
            }

        }
        return resourceDao;
    }



    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        resourceDao = null;


    }
}

