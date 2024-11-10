package store.promotion.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PromotionRepository {

    private final Map<Long, Promotion> repository = new ConcurrentHashMap<>();

    public static PromotionRepository getInstance() {
        return BillPughSingleton.INSTANCE;
    }

    public void save(final Long id, final Promotion promotion) {
        repository.put(id, promotion);
    }

    public Optional<Promotion> findByPromotionType(final PromotionType type) {
        return repository.values().stream()
                .filter(promotion -> Objects.equals(promotion.getType(), type))
                .findAny();
    }

    public List<Promotion> findAll() {
        return repository.values().stream()
                .toList();
    }

    private PromotionRepository() {

    }

    private static class BillPughSingleton {
        private static final PromotionRepository INSTANCE = new PromotionRepository();
    }
}
