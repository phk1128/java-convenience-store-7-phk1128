package store.product.domain;

import java.util.Objects;
import store.promotion.domain.Promotion;

public class PurchaseProduct {

    private final PurchaseProductInfo purchaseProductInfo;
    private final ProductQuantity productQuantity;

    public PurchaseProduct(final PurchaseProductInfo purchaseProductInfo, final ProductQuantity productQuantity) {
        this.purchaseProductInfo = purchaseProductInfo;
        this.productQuantity = productQuantity;
    }

    public void updatePromotionQuantity(final int quantity) {
        productQuantity.updatePromotionQuantity(quantity);
    }

    public void updateNormalQuantity(final int quantity) {
        productQuantity.updateNormalQuantity(quantity);
    }

    public void updateNonPromotionQuantity(final int quantity) {
        productQuantity.updateNonPromotionQuantity(quantity);
    }

    public int getPromotionBenefitQuantity() {
        return purchaseProductInfo.promotion().getPromotionBenefitQuantity();
    }

    public boolean isInPromotionTime() {
        if (Objects.equals(purchaseProductInfo.promotion(), null)) {
            return false;
        }
        return purchaseProductInfo.promotion().isInPromotionTime();
    }

    public int calculatePromotionPrice() {
        final Promotion promotion = purchaseProductInfo.promotion();
        if (!Objects.equals(promotion, null)) {
            return purchaseProductInfo.promotionPrice() * promotion.getPromotionQuantity(getPromotionQuantity());
        }
        return 0;
    }

    public int calculatePromotionTotalPrice() {
        return purchaseProductInfo.promotionPrice() * productQuantity.getPromotionQuantity();
    }

    public int calculateNonPromotionPrice() {
        final int nonPromotionQuantity = productQuantity.getNonPromotionQuantity();
        final int normalQuantity = productQuantity.getNormalQuantity();
        return (nonPromotionQuantity * purchaseProductInfo.promotionPrice()) + (normalQuantity * purchaseProductInfo.normalPrice());
    }

    public int calculateTotalPrice() {
        final int nonPromotionQuantity = productQuantity.getNonPromotionQuantity();
        final int normalQuantity = productQuantity.getNormalQuantity();
        final int promotionQuantity = productQuantity.getPromotionQuantity();
        return (nonPromotionQuantity + promotionQuantity) * purchaseProductInfo.promotionPrice()
                + normalQuantity * purchaseProductInfo.normalPrice();
    }

    public int calculateBenefitQuantity() {
        final int promotionQuantity = productQuantity.getPromotionQuantity();
        return purchaseProductInfo.promotion().getPromotionQuantity(promotionQuantity);
    }

    public int getNormalQuantity() {
        return productQuantity.getNormalQuantity();
    }

    public int getNonPromotionQuantity() {
        return productQuantity.getNonPromotionQuantity();
    }

    public int getPromotionQuantity() {
        return productQuantity.getPromotionQuantity();
    }

    public int getBuyQuantity() {
        return purchaseProductInfo.promotion().getBuy();
    }

    public String getName() {
        return purchaseProductInfo.name();
    }

    public int getTotalQuantity() {
        return productQuantity.calculateTotalQuantity();
    }

    public boolean canReceiveMorePromotion() {
        if (Objects.equals(purchaseProductInfo.promotion(), null)) {
            return false;
        }
        return purchaseProductInfo.promotion().canPromotion(productQuantity.getPromotionQuantity());
    }

}
