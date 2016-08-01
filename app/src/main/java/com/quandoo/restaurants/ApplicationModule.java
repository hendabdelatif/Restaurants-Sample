package com.quandoo.restaurants;

import com.quandoo.restaurants.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by hendabdel-latif on 7/27/16.
 */

@Module(injects = RestaurantsApplication.class, library = true)
public class ApplicationModule {

    @Provides
    public Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    public RealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }

}
