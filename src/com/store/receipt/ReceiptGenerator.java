package com.store.receipt;

/**
 * Created by rajeev on 12/8/18.
 */

import java.math.BigDecimal;
import java.util.Collection;

public class ReceiptGenerator {

    private Collection<ReceiptItem> purchases;
    private BigDecimal taxes;
    private BigDecimal total;

    @SuppressWarnings("unused")
    private ReceiptGenerator() { }

    public ReceiptGenerator(Collection<ReceiptItem> purchases, BigDecimal taxes, BigDecimal total) {
        this.purchases = purchases;
        this.taxes = taxes;
        this.total = total;
    }

    public Collection<ReceiptItem> getPurchases() {
        return purchases;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void printReceipt() {
        purchases.stream().forEach(System.out::println);
        System.out.println("Sales Tax: "+taxes);
        System.out.println("Total: "+total);
    }

}