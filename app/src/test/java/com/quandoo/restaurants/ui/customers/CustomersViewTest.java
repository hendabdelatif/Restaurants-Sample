package com.quandoo.restaurants.ui.customers;

import android.test.InstrumentationTestCase;

import com.quandoo.restaurants.ApplicationModule;
import com.quandoo.restaurants.model.realm.RealmService;

import org.junit.Before;
import org.junit.Test;

import io.realm.Realm;

/**
 * Created by hendabdel-latif on 8/1/16.
 */
public class CustomersViewTest extends InstrumentationTestCase {

    private Realm realm = null;
    private ApplicationModule applicationModule = null;
    private RealmService realmService = null;

    @Before
    public void setup() {

        applicationModule = new ApplicationModule();
        realm = applicationModule.provideRealm();
        realmService = applicationModule.provideRealmService(realm);

    }

    @Test
    public void testShowCustomers() throws Exception {

        assertNotNull(realmService.getAllCustomers());

    }

}