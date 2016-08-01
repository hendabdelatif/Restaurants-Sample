package com.quandoo.restaurants;

import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

import com.quandoo.restaurants.ui.tables.TablesActivity;

/**
 * Created by hendabdel-latif on 8/1/16.
 */
public class ReserveTableTest extends ActivityInstrumentationTestCase2<TablesActivity> {

    RecyclerView mRecyclerView;

    public ReserveTableTest() {
        super(TablesActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
    }

    public void testFocusOnTables() throws Exception {

        assertTrue(mRecyclerView.hasFocus());
        getInstrumentation().sendCharacterSync(KeyEvent.KEYCODE_DPAD_CENTER);

    }

}
