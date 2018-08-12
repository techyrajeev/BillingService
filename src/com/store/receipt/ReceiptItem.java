package com.store.receipt;
import com.store.cart.Item;

import java.math.BigDecimal;

/**
 * Created by rajeev on 12/8/18.
 */

public class ReceiptItem {

    private Item item;
    private BigDecimal taxes;
    private BigDecimal total;

    @SuppressWarnings("unused")
    private ReceiptItem() { }

    public ReceiptItem(Item item, BigDecimal taxes, BigDecimal total) {
        this.item = item;
        this.total = total;
        this.taxes = taxes;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return item.getQuantity()+" "+item.getName()+": "+this.total;
    }
}