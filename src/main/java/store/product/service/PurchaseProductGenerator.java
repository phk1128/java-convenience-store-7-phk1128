package store.product.service;

import java.util.ArrayList;
import java.util.List;
import store.product.domain.Product;
import store.product.domain.ProductQuantity;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.product.domain.PurchaseProductInfo;
import store.product.dto.PurchaseProductRequest;
import store.promotion.domain.Promotion;

public class PurchaseProductGenerator {

    private static final int ZERO = 0;
    private final ProductRepository productRepository;

    public PurchaseProductGenerator(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<PurchaseProduct> createPurchaseProduct(final List<PurchaseProductRequest> requests) {
        final List<PurchaseProduct> products = new ArrayList<>();
        for (PurchaseProductRequest request : requests) {
            final int nonPromotionQuantity = getNonPromotionQuantity(request);
            final int normalQuantity = calculateNormalQuantity(request);
            final int promotionQuantity = request.quantity() - normalQuantity - nonPromotionQuantity;
            final PurchaseProduct product = new PurchaseProduct(getProductInfo(request),
                    new ProductQuantity(normalQuantity, promotionQuantity, nonPromotionQuantity));
            products.add(product);
        }
        return products;
    }

    private int calculateNormalQuantity(final PurchaseProductRequest request) {
        if (!productRepository.existNormalProductByProductName(request.name())) {
            return ZERO;
        }
        if (!productRepository.existPromotionProductByProductName(request.name())) {
            return request.quantity();
        }
        final String name = request.name();
        final Product promotionProduct = productRepository.findPromotionProductByProductName(name);
        int normalQuantity = request.quantity() - promotionProduct.getQuantity();
        return Math.max(normalQuantity, ZERO);
    }

    private int getNonPromotionQuantity(final PurchaseProductRequest request) {
        if (!productRepository.existPromotionProductByProductName(request.name())) {
            return ZERO;
        }
        return calculateNonPromotionQuantity(request);
    }

    private int calculateNonPromotionQuantity(final PurchaseProductRequest request) {
        final Product promotionProduct = productRepository.findPromotionProductByProductName(request.name());
        final int minQuantity = Math.min(request.quantity(), promotionProduct.getQuantity());
        int nonPromotionQuantity = promotionProduct.calculateRemainingQuantity(minQuantity);
        if (request.quantity() < promotionProduct.getQuantity()
                && nonPromotionQuantity >= promotionProduct.getBuyQuantity()) {
            nonPromotionQuantity = ZERO;
        }
        return nonPromotionQuantity;
    }


    private PurchaseProductInfo getProductInfo(final PurchaseProductRequest request) {
        final String name = request.name();
        final int normalPrice = getNormalPrice(request);
        final int promotionPrice = getPromotionPrice(request);
        final Promotion promotion = getPromotion(request);
        return new PurchaseProductInfo(name, normalPrice, promotionPrice, promotion);
    }

    private Promotion getPromotion(final PurchaseProductRequest request) {
        if (productRepository.existPromotionProductByProductName(request.name())) {
            return productRepository.findPromotionProductByProductName(request.name()).getPromotion();
        }
        return null;
    }

    private int getNormalPrice(final PurchaseProductRequest request) {
        if (productRepository.existNormalProductByProductName(request.name())) {
            return productRepository.findProductByProductName(request.name()).getPrice();
        }
        return ZERO;
    }

    private int getPromotionPrice(final PurchaseProductRequest request) {
        if (productRepository.existPromotionProductByProductName(request.name())) {
            return productRepository.findPromotionProductByProductName(request.name()).getPrice();
        }
        return ZERO;
    }
}
