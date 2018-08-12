package com.store.billing;

import com.store.cart.Cart;
import com.store.receipt.ReceiptItem;
import com.store.cart.Item;
import com.store.receipt.ReceiptGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by rajeev on 12/8/18.
 */
public class BillingService {
    private static BillingService instance = null;
    private TaxCalculator taxCalculator = null;

    private BillingService(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public static BillingService getInstance() {
        if(instance == null) {
            throw new AssertionError("Please initialize BillingService first");
        }

        return instance;
    }

    public synchronized static BillingService init(TaxCalculator taxCalculator) {
        if (instance != null) {
            throw new AssertionError("You already initialized BillingService");
        }

        instance = new BillingService(taxCalculator);
        return instance;
    }

    public ReceiptGenerator buyItems(Cart cart) {
        Collection<ReceiptItem> purchases = cart.getItems().stream().map(this::buyItem).collect(Collectors.toCollection(ArrayList::new));
        BigDecimal taxes = purchases.stream().map(purchase -> purchase.getTaxes()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal total = purchases.stream().map(purchase -> purchase.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new ReceiptGenerator(purchases, taxes, total);
    }

    private ReceiptItem buyItem(Item item) {
        BigDecimal quantityAsBigDecimal = new BigDecimal(item.getQuantity());
        BigDecimal taxes = taxCalculator.calculateTaxes(item).multiply(quantityAsBigDecimal);
        BigDecimal total = item.getPrice().multiply(quantityAsBigDecimal).add(taxes);
        return new ReceiptItem(item, taxes, total);
    }
}
