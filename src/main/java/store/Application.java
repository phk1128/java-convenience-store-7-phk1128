package store;

import store.config.AppConfig;
import store.config.InitialDataLoader;
import store.product.domain.ProductRepository;
import store.promotion.domain.PromotionRepository;

public class Application {
    public static void main(String[] args) {
        final PromotionRepository promotionRepository = new PromotionRepository();
        final ProductRepository productRepository = new ProductRepository();
        final AppConfig appConfig = new AppConfig(productRepository, promotionRepository);
        final InitialDataLoader initialDataLoader = appConfig.initialDataLoader();
        initialDataLoader.initialize();
        final Convenience convenience = appConfig.convenience();
        convenience.purchase();
    }
}
