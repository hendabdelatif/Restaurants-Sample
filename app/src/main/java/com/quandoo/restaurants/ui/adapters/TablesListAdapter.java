package com.quandoo.restaurants.ui.adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quandoo.restaurants.R;
import com.quandoo.restaurants.RestaurantsApplication;
import com.quandoo.restaurants.model.sharedpreference.SharedPreferencesManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Hend on 7/31/2016.
 */
public class TablesListAdapter extends RecyclerView.Adapter<TablesListAdapter.ViewHolder> {

    public static String PREF_ID = "prefId";

    private boolean isAvailable = true;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private List<Boolean> tablesList;

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.textTable.setText("Table " + position);

        isAvailable = tablesList.get(position);

        if (isAvailable) {
            selectedItems.put(position, true);
        } else {
            selectedItems.delete(position);
        }

        getAvailableTablesFromPreferences(holder, position);

        if (selectedItems.get(position)) {
            isAvailable = true;
            holder.cardView.setSelected(true);
            holder.cardView.setCardBackgroundColor(Color.YELLOW);

        } else {
            isAvailable = false;
            holder.cardView.setSelected(false);
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }
        setAvailableTablesToPreferences(position, isAvailable);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (selectedItems.get(position, false)) {
                    isAvailable = false;
                    selectedItems.delete(position);
                    holder.cardView.setSelected(false);
                    holder.cardView.setCardBackgroundColor(Color.WHITE);
                } else {
                    isAvailable = true;
                    selectedItems.put(position, true);
                    holder.cardView.setSelected(true);
                    holder.cardView.setCardBackgroundColor(Color.YELLOW);
                }
                setAvailableTablesToPreferences(position, isAvailable);
            }
        });
    }

    private void setAvailableTablesToPreferences(int position, boolean available) {
        SharedPreferencesManager.getInstance(RestaurantsApplication.getInstance().getAppContext()).addBoolean(PREF_ID + position, available);
    }

    private void getAvailableTablesFromPreferences(ViewHolder holder, int position) {

        boolean selected = SharedPreferencesManager.getInstance(RestaurantsApplication.getInstance().getAppContext()).getBooleanValue(PREF_ID + position, selectedItems.get(position, false));

        if (selected) {
            selectedItems.put(position, true);
            holder.cardView.setSelected(true);
            holder.cardView.setCardBackgroundColor(Color.YELLOW);
        } else {
            selectedItems.delete(position);
            holder.cardView.setSelected(false);
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return tablesList.size();
    }

    public void setTables(final List<Boolean> tables) {
        tablesList = tables;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.card_view)
        CardView cardView;
        @Bind(R.id.text_table)
        TextView textTable;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setClickable(true);
        }
    }
}
