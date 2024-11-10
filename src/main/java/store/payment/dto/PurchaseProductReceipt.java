package store.payment.dto;

import store.product.domain.PurchaseProduct;

public record PurchaseProductReceipt(
        String name,
        int quantity,
        int price
) {

    public static PurchaseProductReceipt of(final PurchaseProduct product) {
        return new PurchaseProductReceipt(
                product.getName(),
                product.getTotalQuantity(),
                product.calculateTotalPrice()
        );
    }

}
