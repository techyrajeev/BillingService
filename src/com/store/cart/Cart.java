package com.store.cart;

/**
 * Created by rajeev on 12/8/18.
 */

import java.util.ArrayList;
import java.util.Collection;

public class Cart {

    private Collection<Item> items = new ArrayList<>();

    public Collection<Item> getItems() {
        return items;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void resetCart() {
        items = new ArrayList<>();
    }


}