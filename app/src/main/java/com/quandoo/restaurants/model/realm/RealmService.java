package com.quandoo.restaurants.model.realm;

import com.quandoo.restaurants.model.Customer;
import com.quandoo.restaurants.ui.utils.Utilities;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by hendabdel-latif on 7/30/2016.
 */
public class RealmService {

    private final Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;

        loadCustomersFromJsonString();
    }

    public void closeRealm() {
        mRealm.close();
    }

    public RealmResults<Customer> getAllCustomers() {
        return mRealm.allObjects(Customer.class);
    }

    public void loadCustomersFromJsonString() {

        final String json = Utilities.getDataFromAsset("customer-list.json");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createOrUpdateAllFromJson(Customer.class, json);
            }
        });
    }

}
