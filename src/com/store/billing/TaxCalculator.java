package com.store.billing;

/**
 * Created by rajeev on 12/8/18.
 */

import com.store.cart.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class TaxCalculator {

    private static final BigDecimal TWENTY = new BigDecimal(20);
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private final BigDecimal basicTaxes;

    public TaxCalculator(BigDecimal basicTaxes) {
        this.basicTaxes = basicTaxes;
    }

    public BigDecimal calculateTaxes(Item item) {

        BigDecimal taxes = BigDecimal.ZERO;

        if (!item.getType().equals(Item.ItemType.MEDICAL)) {
            taxes = taxes.add(item.getPrice().multiply(basicTaxes).divide(ONE_HUNDRED));
        }

        return roundTaxes(taxes);
    }

    private BigDecimal roundTaxes(BigDecimal taxes) {
        return taxes.multiply(TWENTY).setScale(0, RoundingMode.UP).divide(TWENTY).setScale(2);
    }

}