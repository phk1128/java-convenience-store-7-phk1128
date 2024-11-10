package store.product.dto;

import store.product.domain.Product;

public record ProductStatus(
        String name,
        int price,
        int quantity,
        String promotion
) {

    public static ProductStatus of(final Product product) {
        return new ProductStatus(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getPromotionName()
        );
    }
}
