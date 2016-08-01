package com.quandoo.restaurants.ui.customers;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.quandoo.restaurants.R;
import com.quandoo.restaurants.model.Customer;
import com.quandoo.restaurants.ui.BaseActivity;
import com.quandoo.restaurants.ui.adapters.CustomersListAdapter;
import com.quandoo.restaurants.ui.tables.TablesActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public class CustomersActivity extends BaseActivity implements CustomersView, CustomersListAdapter.OnCustomerClickListener, SearchView.OnQueryTextListener {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    CustomersPresenter mCustomersPresenter;
    private CustomersListAdapter mAdapter;

    private RealmResults<Customer> mCustomerList;
    private SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initList();


    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.customers);
        setSupportActionBar(mToolbar);
    }

    private void initList() {
        mAdapter = new CustomersListAdapter();
        mAdapter.setOnCustomerClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mCustomersPresenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCustomersPresenter.clearView();
    }

    @Override
    public void showCustomers(RealmResults<Customer> customerList) {

        mCustomerList = customerList;
        mAdapter.setCustomers(customerList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCustomerClick(final int id) {
        mCustomersPresenter.onCustomerClick(id);
    }

    @Override
    public void showCustomerDetailView(int id) {
        startActivity(new Intent(this, TablesActivity.class));
    }

    @Override
    protected Object getModule() {
        return new CustomersModule();
    }

    @Override
    protected void closeRealm() {
        mCustomersPresenter.closeRealm();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setIcon(R.drawable.ic_action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        if (searchView != null) {
            searchView.setOnQueryTextListener(this);
            searchView.setSearchableInfo(searchManager.
                    getSearchableInfo(getComponentName()));
            searchView.setSubmitButtonEnabled(true);
        }
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.getFilter(query);
        mRecyclerView.setAdapter(mAdapter);

        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (TextUtils.isEmpty(newText)) {
            mCustomersPresenter.setView(this);
        }
        mAdapter.getFilter(newText);
        mRecyclerView.setAdapter(mAdapter);
        return true;
    }

}
