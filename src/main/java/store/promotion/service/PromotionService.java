package store.promotion.service;

import java.util.Objects;
import store.constant.StoreCommand;
import store.product.domain.Product;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.promotion.dto.NonPromotionProduct;
import store.promotion.dto.PromotionProduct;

public class PromotionService {

    private static final int ZERO = 0;
    private final ProductRepository productRepository;

    public PromotionService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void applyNonPromotion(final StoreCommand command, final PurchaseProduct product) {
        if (Objects.equals(command, StoreCommand.NO)) {
            subtractNormalAndNonPromotion(product);
        }
    }

    public void applyPromotion(final StoreCommand command, final PurchaseProduct product) {
        if (Objects.equals(command, StoreCommand.YES)) {
            addProductQuantity(product);
        }
        if (Objects.equals(command, StoreCommand.NO)) {
            subtractPromotionQuantity(product);
        }
    }

    public boolean canReceiveMoreProduct(final PromotionProduct promotionProduct) {
        return !Objects.equals(promotionProduct, null);
    }

    public boolean canNotReceivePromotion(final NonPromotionProduct nonPromotionProduct) {
        return nonPromotionProduct.doesNotReceivePromotion();
    }

    public PromotionProduct getPromotionProduct(final PurchaseProduct product) {
        if (isReceivedMoreProduct(product)) {
            return new PromotionProduct(product.getName(), product.getPromotionBenefitQuantity());
        }
        return null;
    }

    public NonPromotionProduct getNonPromotionProduct(final PurchaseProduct product) {
        final int nonPromotionQuantity = product.getNonPromotionQuantity();
        final int normalQuantity = product.getNormalQuantity();
        return new NonPromotionProduct(doesNotReceivePromotion(product), product.getName(),
                nonPromotionQuantity + normalQuantity);
    }

    private void addProductQuantity(final PurchaseProduct product) {
        if (isReceivedMoreProduct(product)) {
            int quantity = product.getPromotionQuantity();
            product.updatePromotionQuantity(quantity + 1);
        }
    }

    private boolean isReceivedMoreProduct(final PurchaseProduct product) {
        final String name = product.getName();
        if (!productRepository.existPromotionProductByProductName(name)) {
            return false;
        }
        final int quantity = product.getTotalQuantity();
        final Product promotionProduct = productRepository.findPromotionProductByProductName(name);
        final int remainingQuantity = promotionProduct.calculateRemainingQuantity(product.getPromotionQuantity());
        return promotionProduct.getQuantity() > quantity && promotionProduct.canReceiveMorePromotion(remainingQuantity);
    }

    private boolean doesNotReceivePromotion(final PurchaseProduct product) {
        final String name = product.getName();
        if (!productRepository.existPromotionProductByProductName(name)) {
            return false;
        }
        return (product.getNonPromotionQuantity() > ZERO && product.isInPromotionTime())
                || (product.getNormalQuantity() > ZERO && product.isInPromotionTime());
    }

    private void subtractPromotionQuantity(final PurchaseProduct product) {
        if (product.canReceiveMorePromotion()) {
            final int promotionQuantity = product.getPromotionQuantity();
            final int buyQuantity = product.getBuyQuantity();
            product.updatePromotionQuantity(promotionQuantity - buyQuantity);
            product.updateNonPromotionQuantity(buyQuantity);
        }
    }

    private void subtractNormalAndNonPromotion(final PurchaseProduct product) {
        product.updateNormalQuantity(ZERO);
        product.updateNonPromotionQuantity(ZERO);
    }

}
