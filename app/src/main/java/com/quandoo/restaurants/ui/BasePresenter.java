package com.quandoo.restaurants.ui;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public interface BasePresenter<T> {

    void setView(T view);

    void clearView();

    void closeRealm();
}
