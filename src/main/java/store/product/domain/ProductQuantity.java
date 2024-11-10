package store.product.domain;

public class ProductQuantity {

    private int normalQuantity;
    private int promotionQuantity;
    private int nonPromotionQuantity;

    public ProductQuantity(final int normalQuantity, final int promotionQuantity, final int nonPromotionQuantity) {
        this.normalQuantity = normalQuantity;
        this.promotionQuantity = promotionQuantity;
        this.nonPromotionQuantity = nonPromotionQuantity;
    }

    public int calculateTotalQuantity() {
        return normalQuantity + promotionQuantity + nonPromotionQuantity;
    }

    public int getNormalQuantity() {
        return normalQuantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getNonPromotionQuantity() {
        return nonPromotionQuantity;
    }

    public void updateNormalQuantity(final int normalQuantity) {
        this.normalQuantity = normalQuantity;
    }

    public void updatePromotionQuantity(final int promotionQuantity) {
        this.promotionQuantity = promotionQuantity;
    }

    public void updateNonPromotionQuantity(final int nonPromotionQuantity) {
        this.nonPromotionQuantity = nonPromotionQuantity;
    }
}
