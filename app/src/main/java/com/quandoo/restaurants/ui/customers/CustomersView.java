package com.quandoo.restaurants.ui.customers;

import com.quandoo.restaurants.model.Customer;

import io.realm.RealmResults;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public interface CustomersView {

    void showCustomers(RealmResults<Customer> customerList);

    void showCustomerDetailView(int id);


    class EmptyCustomersListView implements CustomersView {

        @Override
        public void showCustomers(final RealmResults<Customer> customers) {

        }

        @Override
        public void showCustomerDetailView(final int id) {

        }

    }
}
