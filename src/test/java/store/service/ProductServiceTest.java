package store.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.config.InitialDataLoader;
import store.product.domain.ProductRepository;
import store.product.dto.PurchaseProductRequest;
import store.product.domain.PurchaseProduct;
import store.product.service.PurchaseProductGenerator;
import store.promotion.domain.PromotionRepository;
import store.product.service.ProductService;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        final ProductRepository productRepository = ProductRepository.getInstance();
        final PromotionRepository promotionRepository = PromotionRepository.getInstance();
        final InitialDataLoader initialDataLoader = new InitialDataLoader(promotionRepository, productRepository);
        initialDataLoader.initialize();
        final PurchaseProductGenerator purchaseProductGenerator = new PurchaseProductGenerator(productRepository);
        productService = new ProductService(productRepository, purchaseProductGenerator);
    }


    @DisplayName("구매 제품을 가져온다.")
    @Test
    void getPurchasePromotionProductTest() {
        //given
        final List<PurchaseProductRequest> purchaseProductRequests = List.of(
                new PurchaseProductRequest("콜라", 11),
                new PurchaseProductRequest("오렌지주스", 1),
                new PurchaseProductRequest("물",10)
        );
        //when
        final List<PurchaseProduct> purchaseProducts = productService.generatePurchaseProducts(purchaseProductRequests);
        final PurchaseProduct cola = purchaseProducts.getFirst();
        final PurchaseProduct orangeJuice = purchaseProducts.get(1);
        final PurchaseProduct water = purchaseProducts.get(2);
        //then
        assertAll(
                () -> assertThat(cola.getPromotionQuantity()).isEqualTo(9),
                () -> assertThat(orangeJuice.getPromotionQuantity()).isEqualTo(1),
                () -> assertThat(cola.getNonPromotionQuantity()).isEqualTo(1),
                () -> assertThat(water.getPromotionQuantity()).isZero(),
                () -> assertThat(water.getNormalQuantity()).isEqualTo(10)
        );

    }

}
