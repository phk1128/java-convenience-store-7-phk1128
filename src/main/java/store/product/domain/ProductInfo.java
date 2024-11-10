package store.product.domain;

import store.promotion.domain.Promotion;

public record ProductInfo(
        String name,
        int price,
        Promotion promotion
) {
}
