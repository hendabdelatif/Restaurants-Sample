package com.quandoo.restaurants.ui.tables;

import com.quandoo.restaurants.ApplicationModule;
import com.quandoo.restaurants.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Hend on 7/31/2016.
 */

@Module(injects = TablesActivity.class, addsTo = ApplicationModule.class)
public class TablesModule {

    @Provides
    TablesPresenter provideTablesPresenter(final RealmService realmService) {
        return new TablesPresenterImpl(realmService);
    }
}
