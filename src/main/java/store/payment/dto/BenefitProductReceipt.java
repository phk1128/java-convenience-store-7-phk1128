package store.payment.dto;

import store.product.domain.PurchaseProduct;

public record BenefitProductReceipt(
        String name,
        int quantity
) {
    public static BenefitProductReceipt of(final PurchaseProduct product) {
        return new BenefitProductReceipt(
                product.getName(),
                product.calculateBenefitQuantity()
        );
    }
}
