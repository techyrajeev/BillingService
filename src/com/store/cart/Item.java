package com.store.cart;

/**
 * Created by rajeev on 12/8/18.
 */
import java.math.BigDecimal;

public class Item {

    public enum ItemType {
        BOOK, FOOD, MEDICAL, OTHER;
    }

    private String name;
    private ItemType type;
    private int quantity;
    private BigDecimal price;

    public Item(String name, ItemType type, int quantity, BigDecimal price) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
