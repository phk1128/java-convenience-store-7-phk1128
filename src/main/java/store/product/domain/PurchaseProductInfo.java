package store.product.domain;

import store.promotion.domain.Promotion;

public record PurchaseProductInfo(
        String name,
        int normalPrice,
        int promotionPrice,
        Promotion promotion
) {
}
