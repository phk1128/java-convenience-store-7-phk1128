package store.product.service;

import java.util.ArrayList;
import java.util.List;
import store.error.ErrorMessage;
import store.error.exception.ExceedsProductQuantityException;
import store.error.exception.InvalidWrongInputException;
import store.error.exception.NotFoundProductException;
import store.product.domain.Product;
import store.product.domain.ProductInfo;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.product.dto.ProductStatus;
import store.product.dto.PurchaseProductRequest;
import store.util.ListValidator;

public class ProductService {

    private static final int INTEGER_ZERO = 0;
    private static final Long LONG_ZERO = 0L;
    private final ProductRepository productRepository;
    private final PurchaseProductGenerator purchaseProductGenerator;

    public ProductService(final ProductRepository productRepository,
                          final PurchaseProductGenerator purchaseProductGenerator) {
        this.productRepository = productRepository;
        this.purchaseProductGenerator = purchaseProductGenerator;
    }

    public List<PurchaseProduct> generatePurchaseProducts(final List<PurchaseProductRequest> requests) {
        validate(requests);
        return purchaseProductGenerator.createPurchaseProduct(requests);
    }

    public List<ProductStatus> getProductStatuses() {
        final List<ProductStatus> productStatuses = new ArrayList<>();
        final List<Product> products = productRepository.findAll();
        for (Product product : products) {
            final ProductStatus productStatus = ProductStatus.of(product);
            productStatuses.add(productStatus);
            addNormalProduct(product, productStatuses);
        }
        return productStatuses;
    }

    private void validate(final List<PurchaseProductRequest> requests) {
        validateProductDuplicate(requests);
        for (PurchaseProductRequest request : requests) {
            validateProductName(request);
            validateProductQuantity(request);
        }
    }

    private void addNormalProduct(final Product product, final List<ProductStatus> productStatuses) {
        if (hasNoNormalProduct(product)) {
            final ProductInfo productInfo = new ProductInfo(product.getName(), product.getPrice(), null);
            productStatuses.add(ProductStatus.of(new Product(LONG_ZERO, productInfo, INTEGER_ZERO)));
        }
    }

    private boolean hasNoNormalProduct(final Product product) {
        final String name = product.getName();
        return productRepository.existPromotionProductByProductName(name)
                && !productRepository.existNormalProductByProductName(name);
    }

    private void validateProductName(final PurchaseProductRequest request) {
        if (!productRepository.existByProductName(request.name())) {
            throw new NotFoundProductException(ErrorMessage.INVALID_NOT_FOUND_PRODUCT);
        }
    }

    private void validateProductQuantity(final PurchaseProductRequest request) {
        final int count = productRepository.countByProductName(request.name());
        final int quantity = request.quantity();
        if (count < quantity) {
            throw new ExceedsProductQuantityException(ErrorMessage.INVALID_EXCEEDS_PRODUCT_QUANTITY);
        }
        if (quantity <= INTEGER_ZERO) {
            throw new InvalidWrongInputException(ErrorMessage.INVALID_WRONG_INPUT);
        }
    }

    private void validateProductDuplicate(final List<PurchaseProductRequest> requests) {
        ListValidator.validateDuplicate(requests, ErrorMessage.INVALID_WRONG_INPUT);
    }
}
