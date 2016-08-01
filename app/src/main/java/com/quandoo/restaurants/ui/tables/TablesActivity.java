package com.quandoo.restaurants.ui.tables;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.quandoo.restaurants.R;
import com.quandoo.restaurants.ui.BaseActivity;
import com.quandoo.restaurants.ui.adapters.TablesListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hend on 7/31/2016.
 */
public class TablesActivity extends BaseActivity implements TablesView {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Inject
    TablesPresenter mTablesPresenter;
    private TablesListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tables);
        ButterKnife.bind(this);

        setupActionBar();
        initList();

    }

    private void initList() {
        mAdapter = new TablesListAdapter();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTablesPresenter.setView(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        mTablesPresenter.clearView();

    }

    @Override
    public void showTables(List<Boolean> tablesList) {
        mAdapter.setTables(tablesList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void highlightTable() {
        mTablesPresenter.onTableClick();

    }

    @Override
    protected Object getModule() {
        return new TablesModule();
    }

    @Override
    protected void closeRealm() {
        mTablesPresenter.closeRealm();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
