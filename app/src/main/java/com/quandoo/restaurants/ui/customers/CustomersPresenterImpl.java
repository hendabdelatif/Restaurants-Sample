package com.quandoo.restaurants.ui.customers;

import android.os.Handler;

import com.quandoo.restaurants.RestaurantsApplication;
import com.quandoo.restaurants.model.realm.RealmService;
import com.quandoo.restaurants.model.sharedpreference.SharedPreferencesManager;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public class CustomersPresenterImpl implements CustomersPresenter {

    private final RealmService mRealmService;
    private CustomersView customersView = new CustomersView.EmptyCustomersListView();
    final Handler handler = new Handler();

    public CustomersPresenterImpl(final RealmService realmService) {
        mRealmService = realmService;

        startBackgroundService();
    }

    @Override
    public void onCustomerClick(int position) {
        if (customersView != null) {
            customersView.showCustomerDetailView(position);
        }
    }

    /**
     * Start Background Service to clear table reservations every 10 minutes.
     */
    public void startBackgroundService() {
        final Runnable runnable = new Runnable() {
            public void run() {
                handler.postDelayed(this, 1000 * 10 * 60); // 1000*10*60 every 10 minutes
                SharedPreferencesManager.getInstance(RestaurantsApplication.getInstance().getAppContext()).clear();
            }
        };
        handler.postDelayed(runnable, 1000 * 10 * 60);
    }

    @Override
    public void setView(final CustomersView view) {
        customersView = view;
        customersView.showCustomers(mRealmService.getAllCustomers());
    }

    @Override
    public void clearView() {
        customersView = new CustomersView.EmptyCustomersListView();
    }

    @Override
    public void closeRealm() {
        mRealmService.closeRealm();
    }


}
