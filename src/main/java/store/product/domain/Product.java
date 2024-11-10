package store.product.domain;

import store.domain.promotion.Promotion;

public class Product {

    private final String name;
    private final int price;
    private final Promotion promotion;
    private int quantity;

    public Product(final String name, final int price, final Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
    }

    public void updateQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public int getQuantity() {
        return quantity;
    }
}
