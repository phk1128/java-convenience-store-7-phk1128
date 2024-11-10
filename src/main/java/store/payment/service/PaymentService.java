package store.payment.service;

import java.util.List;
import java.util.Objects;
import store.constant.StoreCommand;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PurchaseProductReceipt;
import store.product.domain.Product;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.state.PurchaseState;

public class PaymentService {

    private static final int ZERO = 0;
    private final ProductRepository productRepository;

    public PaymentService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void updateRetryPurchase(final StoreCommand command) {
        final PurchaseState purchaseState = PurchaseState.getInstance();
        if (Objects.equals(command, StoreCommand.YES)) {
            purchaseState.updateRetryPurchase(true);
        }
        if (Objects.equals(command, StoreCommand.NO)) {
            purchaseState.updateRetryPurchase(false);
        }
    }

    public List<BenefitProductReceipt> getBenefitProductReceipt(final List<PurchaseProduct> products) {
        return products.stream()
                .filter(PurchaseProduct::isInPromotionTime)
                .filter(product -> product.calculateBenefitQuantity() > ZERO)
                .map(BenefitProductReceipt::of)
                .toList();
    }

    public List<PurchaseProductReceipt> getPurchaseProductReceipt(final List<PurchaseProduct> products) {
        return products.stream()
                .filter(product -> product.getTotalQuantity() != ZERO)
                .map(PurchaseProductReceipt::of)
                .toList();
    }

    public void decreaseProductQuantity(final List<PurchaseProduct> products) {
        for (PurchaseProduct product : products) {
            decreaseNormalProductQuantity(product);
            decreasePromotionProductQuantity(product);
        }
    }

    private void decreaseNormalProductQuantity(final PurchaseProduct product) {
        final String name = product.getName();
        if (productRepository.existNormalProductByProductName(name)) {
            final Product productByProductName = productRepository.findProductByProductName(name);
            final Long id = productByProductName.getId();
            productByProductName.updateQuantity(productByProductName.getQuantity() - product.getNormalQuantity());
            productRepository.save(id, productByProductName);
        }
    }

    private void decreasePromotionProductQuantity(final PurchaseProduct product) {
        final String name = product.getName();
        if (productRepository.existPromotionProductByProductName(name)) {
            final Product productByProductName = productRepository.findPromotionProductByProductName(name);
            final Long id = productByProductName.getId();
            final int quantity = product.getPromotionQuantity() + product.getNonPromotionQuantity();
            productByProductName.updateQuantity(productByProductName.getQuantity() - quantity);
            productRepository.save(id, productByProductName);
        }
    }

}
