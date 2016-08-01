package com.quandoo.restaurants.ui.customers;

import com.quandoo.restaurants.ApplicationModule;
import com.quandoo.restaurants.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hendabdel-latif on 7/27/16.
 */

@Module(injects = CustomersActivity.class, addsTo = ApplicationModule.class)
public class CustomersModule {

    @Provides
    CustomersPresenter provideMyListPresenter(final RealmService realmService) {
        return new CustomersPresenterImpl(realmService);
    }
}
