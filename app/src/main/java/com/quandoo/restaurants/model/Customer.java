package com.quandoo.restaurants.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hendabdel-latif on 7/27/16.
 */
public class Customer extends RealmObject {

    @PrimaryKey
    private Integer id;
    private String customerFirstName;
    private String customerLastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

}
