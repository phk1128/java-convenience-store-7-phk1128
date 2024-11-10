package store.payment.dto;

import store.payment.domain.Payment;

public record PaymentReceipt(
        int productTotalPrice,
        int productTotalQuantity,
        int promotionDiscount,
        int memberShipDiscount,
        int paymentPrice
) {
    public static PaymentReceipt of(final Payment payment) {
        return new PaymentReceipt(
                payment.calculatePurchaseProductTotalPrice(),
                payment.calculateTotalProductQuantity(),
                payment.calculatePromotionTotalDiscount(),
                payment.getMemberShipDiscount(),
                payment.calculateTotalPrice()
        );
    }
}
