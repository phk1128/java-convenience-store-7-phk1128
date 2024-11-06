package store.domain.product;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import store.domain.promotion.PromotionType;

public class ProductRepository {

    private final Map<Long, Product> repository = new ConcurrentHashMap<>();

    public void save(final Long id, final Product product) {
        repository.put(id, product);
    }


    public Optional<Product> findByProductNameAndPromotionType(final String name, final PromotionType type) {
        return repository.values().stream()
                .filter(product -> isMatchedProductName(name, product))
                .filter(product -> isMatchedProductType(type, product))
                .findAny();
    }

    public List<Product> findAll() {
        return repository.values().stream()
                .toList();
    }


    public int countQuantityByProductName(final String name) {
        return repository.values().stream()
                .filter(product -> isMatchedProductName(name, product))
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public int countQuantityByProductNameAndPromotionType(final String name, final PromotionType type) {
        return repository.values().stream()
                .filter(product -> isMatchedProductName(name, product))
                .filter(product -> isMatchedProductType(type, product))
                .mapToInt(Product::getQuantity)
                .sum();
    }

    private boolean isMatchedProductType(final PromotionType type, final Product product) {
        return Objects.equals(product.getPromotion().getType(), type);
    }

    private boolean isMatchedProductName(final String name, final Product product) {
        return Objects.equals(product.getName(), name);
    }

}
