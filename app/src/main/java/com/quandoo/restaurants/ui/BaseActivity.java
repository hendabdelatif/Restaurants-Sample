package com.quandoo.restaurants.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.quandoo.restaurants.RestaurantsApplication;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestaurantsApplication.injectModules(this, getModule());
    }

    @Override
    protected void onDestroy() {
        closeRealm();
        super.onDestroy();
    }

    protected abstract Object getModule();

    protected abstract void closeRealm();
}
