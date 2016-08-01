package com.quandoo.restaurants.ui.tables;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quandoo.restaurants.model.realm.RealmService;
import com.quandoo.restaurants.ui.utils.Utilities;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Hend on 7/31/2016.
 */
public class TablesPresenterImpl implements TablesPresenter {

    private final RealmService mRealmService;
    private TablesView tablesView = new TablesView.EmptyCustomersListView();

    public TablesPresenterImpl(final RealmService realmService) {
        mRealmService = realmService;

        loadTablesFromJsonString();
    }

    @Override
    public void onTableClick() {
        if (tablesView != null) {
            tablesView.highlightTable();
        }
    }

    @Override
    public void setView(final TablesView view) {
        tablesView = view;
        tablesView.showTables(loadTablesFromJsonString());
    }

    @Override
    public void clearView() {
        tablesView = new TablesView.EmptyCustomersListView();
    }

    @Override
    public void closeRealm() {
        mRealmService.closeRealm();
    }

    public List<Boolean> loadTablesFromJsonString() {

        final String json = Utilities.getDataFromAsset("table-map.json");

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Boolean>>() {
        }.getType();
        List<Boolean> tablesList = (List<Boolean>) gson.fromJson(json, listType);

        return tablesList;
    }

}
