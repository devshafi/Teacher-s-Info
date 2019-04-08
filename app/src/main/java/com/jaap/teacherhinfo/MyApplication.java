package com.jaap.teacherhinfo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initializing Realm in Application Level
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("tidb.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }
}
