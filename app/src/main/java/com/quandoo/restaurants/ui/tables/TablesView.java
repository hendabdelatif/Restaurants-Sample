package com.quandoo.restaurants.ui.tables;

import java.util.List;

/**
 * Created by Hend on 7/31/2016.
 */
public interface TablesView {

    void showTables(List<Boolean> tablesList);

    void highlightTable();

    class EmptyCustomersListView implements TablesView {

        @Override
        public void showTables(List<Boolean> tablesList) {

        }

        @Override
        public void highlightTable() {

        }
    }
}
