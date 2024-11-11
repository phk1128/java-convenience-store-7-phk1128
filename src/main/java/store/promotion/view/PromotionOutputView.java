package store.promotion.view;

import store.promotion.dto.NonPromotionProduct;
import store.promotion.dto.PromotionProduct;

public interface PromotionOutputView {

    void printGuidePromotionProduct(final PromotionProduct promotionProduct);

    void printGuideNonPromotionProduct(final NonPromotionProduct nonPromotionProduct);
}
