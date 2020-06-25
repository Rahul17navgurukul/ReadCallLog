package com.rahulkashyap.calldetailsfromrecentcalllog;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private static App instance;
    public static synchronized App getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
    }




}
