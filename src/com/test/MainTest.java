package com.test;

import com.store.billing.BillingService;
import com.store.billing.TaxCalculator;
import com.store.cart.Cart;
import com.store.cart.Item;
import com.store.receipt.ReceiptGenerator;
import com.store.receipt.ReceiptItem;
import org.junit.*;
import org.junit.Before; 
import org.junit.After;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;


/** 
* Main Tester. 
* 
* @author <Rajeev>
* @since <pre>Aug 12, 2018</pre> 
* @version 1.0 
*/ 
public class MainTest {
    public static final BigDecimal taxRate = new BigDecimal(20.0);

    BillingService billingService = null;
    Cart cart = null;

    @Before
public void before() throws Exception {
   billingService =  BillingService.init(new TaxCalculator(taxRate));
   cart = new Cart();
} 

@After
public void after() throws Exception {
    billingService = null;
    cart = null;
} 

@Test
public void testTaxCalculation() throws Exception {
        int itemQty = 2;
    Item item1 = new Item("Book", Item.ItemType.BOOK, itemQty, new BigDecimal(20.0));

    BigDecimal item1Tax = item1.getPrice().multiply(taxRate).divide(new BigDecimal(100))
            .multiply(new BigDecimal(20)).setScale(0, RoundingMode.UP).divide(new BigDecimal(20)).setScale(2);

    cart.addItem(item1);

    ReceiptGenerator receiptGenerator = billingService.buyItems(cart);

    List<ReceiptItem> items = (List<ReceiptItem>) receiptGenerator.getPurchases();
    assertEquals(item1Tax.multiply(new BigDecimal(itemQty)), items.get(0).getTaxes());

}


@Test
public void testTaxCalculationMedical() throws Exception {
        int itemQty = 2;
    Item item1 = new Item("box of headache pills", Item.ItemType.MEDICAL, itemQty, new BigDecimal(20.0));

    cart.addItem(item1);

    ReceiptGenerator receiptGenerator = billingService.buyItems(cart);

    List<ReceiptItem> items = (List<ReceiptItem>) receiptGenerator.getPurchases();
    assertEquals(new BigDecimal(0.00).setScale(2), items.get(0).getTaxes());

}


@Test
public void testItemsValidity() throws Exception {
    Item item4 = new Item("box of headache pills", Item.ItemType.MEDICAL, 1, new BigDecimal(20.0));
    Item item1 = new Item("Book", Item.ItemType.BOOK, 2, new BigDecimal(20.0));
    Item item2 = new Item("Chai", Item.ItemType.FOOD, 1, new BigDecimal(5.0));
    Item item3 = new Item("Bira", Item.ItemType.FOOD, 1, new BigDecimal(190.0));

    Cart cart = new Cart();
    cart.addItem(item1);
    cart.addItem(item2);
    cart.addItem(item3);
    cart.addItem(item4);

    ReceiptGenerator receiptGenerator = billingService.buyItems(cart);

    List<ReceiptItem> items = (List<ReceiptItem>) receiptGenerator.getPurchases();

    assertEquals(cart.getItems().size(), items.size());

}


} 
