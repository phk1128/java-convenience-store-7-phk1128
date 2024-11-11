package store.product.domain;

import java.util.Objects;
import store.promotion.domain.Promotion;

public class Product {

    private static final String BLANK = "";
    private static final int ZERO  = 0;
    private final long id;
    private final ProductInfo productInfo;
    private int quantity;

    public Product(final Long id, final ProductInfo productInfo, final int quantity) {
        this.id = id;
        this.productInfo = productInfo;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public void updateQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public String getPromotionName() {
        final Promotion promotion = productInfo.promotion();
        if (Objects.equals(promotion, null)) {
            return BLANK;
        }
        return promotion.getPromotionName();
    }

    public boolean canReceiveMorePromotion(final int quantity) {
        return productInfo.promotion().canPromotion(quantity);
    }


    public int calculateRemainingQuantity(final int quantity) {
        final Promotion promotion = productInfo.promotion();
        if (!Objects.equals(promotion, null)) {
            return promotion.getRemainingQuantity(quantity);
        }
        return ZERO;
    }

    public int getBuyQuantity() {
        return productInfo.promotion().getBuy();
    }

    public int getPrice() {
        return productInfo.price();
    }

    public String getName() {
        return productInfo.name();
    }

    public Promotion getPromotion() {
        return productInfo.promotion();
    }

    public int getQuantity() {
        return quantity;
    }
}
