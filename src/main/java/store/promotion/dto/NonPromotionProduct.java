package store.promotion.dto;

public record NonPromotionProduct(
        boolean doesNotReceivePromotion,
        String name,
        int quantity
) {
}
