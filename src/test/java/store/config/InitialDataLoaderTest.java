package store.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.product.domain.Product;
import store.product.domain.ProductRepository;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionRepository;

class InitialDataLoaderTest {

    private PromotionRepository promotionRepository;
    private ProductRepository productRepository;
    private InitialDataLoader initialDataLoader;

    @BeforeEach
    void setUp() {
        promotionRepository = PromotionRepository.getInstance();
        productRepository = ProductRepository.getInstance();
        initialDataLoader = new InitialDataLoader(promotionRepository, productRepository);
    }

    @DisplayName("데이터 초기화를 성공한다.")
    @Test
    void initializeTest() {
        //when
        initialDataLoader.initialize();
        final List<Promotion> promotions = promotionRepository.findAll();
        final List<Product> products = productRepository.findAll();

        //then
        assertAll(
                () -> assertThat(promotions).isNotEmpty(),
                () -> assertThat(products).isNotEmpty(),
                () -> assertThat(promotions).hasSize(3),
                () -> assertThat(products).hasSize(16)
        );

    }
}
