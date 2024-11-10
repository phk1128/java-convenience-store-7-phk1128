package store.payment.domain;

import java.util.List;
import java.util.Objects;
import store.constant.StoreCommand;
import store.product.domain.PurchaseProduct;

public class Payment {

    private static final int MEMBER_SHIP_DISCOUNT_MAX = 8_000;
    private static final double MEMBER_SHIP_DISCOUNT_PERCENT = 0.3;
    private final List<PurchaseProduct> purchaseProducts;
    private int memberShipDiscount;

    public Payment(final List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
        memberShipDiscount = 0;
    }

    public boolean canMemberShipDiscount() {
        return true;
    }

    public void applyMemberShipDiscount(final StoreCommand command) {
        if (Objects.equals(command, StoreCommand.YES)) {
            memberShipDiscount = calculateMemberShipDiscount();
        }
    }

    public int calculateTotalPrice() {
        return calculatePurchaseProductTotalPrice() - calculatePromotionTotalDiscount() - memberShipDiscount;
    }

    public int calculateTotalProductQuantity() {
        return purchaseProducts.stream()
                .mapToInt(PurchaseProduct::getTotalQuantity)
                .sum();
    }

    public int calculatePurchaseProductTotalPrice() {
        return purchaseProducts.stream()
                .mapToInt(PurchaseProduct::calculateTotalPrice)
                .sum();
    }

    public int calculatePromotionTotalDiscount() {
        return purchaseProducts.stream()
                .mapToInt(PurchaseProduct::calculatePromotionPrice)
                .sum();
    }

    public int getMemberShipDiscount() {
        return memberShipDiscount;
    }

    private int calculateMemberShipDiscount() {
        final int discount = (int) (calculateMemberShipTargetPrice() * MEMBER_SHIP_DISCOUNT_PERCENT);
        return Math.min(discount, MEMBER_SHIP_DISCOUNT_MAX);
    }

    private int calculateMemberShipTargetPrice() {
        final int promotionTotalPrice = purchaseProducts.stream()
                .filter(product -> !product.isInPromotionTime())
                .mapToInt(PurchaseProduct::calculatePromotionTotalPrice)
                .sum();
        final int nonPromotionTotalPrice = purchaseProducts.stream()
                .mapToInt(PurchaseProduct::calculateNonPromotionPrice)
                .sum();
        return promotionTotalPrice + nonPromotionTotalPrice;
    }

}
