package com.quandoo.restaurants.ui.customers;

import com.quandoo.restaurants.ui.BasePresenter;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public interface CustomersPresenter extends BasePresenter<CustomersView> {

    void onCustomerClick(int id);
}
