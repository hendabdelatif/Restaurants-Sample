package com.quandoo.restaurants;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public class RestaurantsApplication extends Application {

    public static RestaurantsApplication sInstance;
    private ObjectGraph mApplicationGraph;

    public static RestaurantsApplication getInstance() {
        if (sInstance == null)
            sInstance = new RestaurantsApplication();
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        initRealmConfiguration();
        initApplicationGraph();
    }

    /**
     * Initialise Realm Configurations
     */
    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    private void initApplicationGraph() {
        mApplicationGraph = ObjectGraph.create(new ApplicationModule());
    }

    public static void injectModules(final Object object, final Object... modules) {
        sInstance.mApplicationGraph.plus(modules).inject(object);
    }

    /**
     * Get A Generic Context
     *
     * @return
     */
    public Context getAppContext() {
        return sInstance;
    }


}
