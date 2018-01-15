package com.helloworld.greendaodemo_master;

import android.app.Application;

import com.facebook.stetho.Stetho;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lihui1 on 2017/10/24.
 */

public class GreenDaoApp extends Application{

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "students-db");

        Database db = helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();

        Stetho.initializeWithDefaults(this);
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }
}
