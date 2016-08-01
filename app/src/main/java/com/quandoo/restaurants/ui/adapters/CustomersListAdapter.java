package com.quandoo.restaurants.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quandoo.restaurants.R;
import com.quandoo.restaurants.model.Customer;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Case;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public class CustomersListAdapter extends RecyclerView.Adapter<CustomersListAdapter.ViewHolder> implements RealmChangeListener {

    private RealmResults<Customer> mCustomers;
    private OnCustomerClickListener mOnCustomerClickListener;

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Customer customer = mCustomers.get(position);

        holder.mTextFirstName.setText(customer.getCustomerFirstName());
        holder.mTextLastName.setText(customer.getCustomerLastName());
        holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mOnCustomerClickListener != null) {
                    mOnCustomerClickListener.onCustomerClick(customer.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCustomers.size();
    }

    @Override
    public void onChange() {
        notifyDataSetChanged();
    }

    public void setOnCustomerClickListener(final OnCustomerClickListener onCustomerClickListener) {
        mOnCustomerClickListener = onCustomerClickListener;
    }

    public void setCustomers(final RealmResults<Customer> customers) {
        mCustomers = customers;
        mCustomers.addChangeListener(this);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.layout_item_container)
        LinearLayout mLayoutItem;
        @Bind(R.id.text_first_name)
        TextView mTextFirstName;
        @Bind(R.id.text_last_name)
        TextView mTextLastName;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnCustomerClickListener {
        void onCustomerClick(int id);
    }

    public void getFilter(String query) {
        RealmResults<Customer> realmResults = mCustomers.where().contains("customerFirstName", query, Case.INSENSITIVE).findAll();
        setCustomers(realmResults);
    }


}
