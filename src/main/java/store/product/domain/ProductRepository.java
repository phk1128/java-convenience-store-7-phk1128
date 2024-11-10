package store.product.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import store.error.ErrorMessage;
import store.error.exception.InvalidFailDataReadFromRepositoryException;

public class ProductRepository {

    private final Map<Long, Product> repository = new ConcurrentHashMap<>();

    public static ProductRepository getInstance() {
        return BillPughSingleton.INSTANCE;
    }

    public void clear() {
        repository.clear();
    }

    public void save(final Long id, final Product product) {
        repository.put(id, product);
    }

    public Product findPromotionProductByProductName(final String name) {
        return repository.values().stream()
                .filter(product -> isMatchedProductName(name, product))
                .filter(this::isNotNullPromotion)
                .findAny()
                .orElseThrow(() -> new InvalidFailDataReadFromRepositoryException(
                        ErrorMessage.INVALID_FAIL_DATA_READ_FROM_REPOSITORY));
    }

    public Product findProductByProductName(final String name) {
        return repository.values().stream()
                .filter(product -> isMatchedProductName(name, product))
                .filter(this::isNullPromotion)
                .findAny()
                .orElseThrow(() -> new InvalidFailDataReadFromRepositoryException(
                        ErrorMessage.INVALID_FAIL_DATA_READ_FROM_REPOSITORY));
    }


    public boolean existNormalProductByProductName(final String name) {
        return repository.values().stream()
                .anyMatch(product -> isMatchedProductName(name, product) && isNullPromotion(product));
    }

    public boolean existPromotionProductByProductName(final String name) {
        return repository.values().stream()
                .anyMatch(product -> isMatchedProductName(name, product) && isNotNullPromotion(product));
    }

    public boolean existByProductName(final String name) {
        return repository.values().stream()
                .anyMatch(product -> isMatchedProductName(name, product));
    }

    public int countByProductName(final String name) {
        return repository.values().stream()
                .filter(product -> isMatchedProductName(name, product))
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public List<Product> findAll() {
        return repository.values().stream()
                .toList();
    }

    private boolean isNotNullPromotion(final Product product) {
        return !Objects.equals(product.getPromotion(), null);
    }

    private boolean isNullPromotion(final Product product) {
        return Objects.equals(product.getPromotion(), null);
    }

    private boolean isMatchedProductName(final String name, final Product product) {
        return Objects.equals(product.getName(), name);
    }

    private ProductRepository() {

    }

    private static class BillPughSingleton {
        private static final ProductRepository INSTANCE = new ProductRepository();
    }

}
