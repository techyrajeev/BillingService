package com.test;

import com.store.billing.BillingService;
import com.store.billing.TaxCalculator;
import com.store.cart.Cart;
import com.store.cart.Item;
import com.store.receipt.ReceiptGenerator;

import java.math.BigDecimal;

public class Main {

    public static final BigDecimal taxRate = new BigDecimal(20.0);
    public static void main(String[] args) {
        Item item1 = new Item("Book", Item.ItemType.BOOK, 2, new BigDecimal(20.0));
        Item item2 = new Item("Chai", Item.ItemType.FOOD, 1, new BigDecimal(5.0));
        Item item3 = new Item("Bira", Item.ItemType.FOOD, 1, new BigDecimal(190.0));

        Cart cart = new Cart();
        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);

        BillingService billingService = BillingService.init(new TaxCalculator(taxRate));
        ReceiptGenerator receiptGenerator = billingService.buyItems(cart);
        receiptGenerator.printReceipt();
    }
}
