package store.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.config.InitialDataLoader;
import store.error.ErrorMessage;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.product.dto.PurchaseProductRequest;
import store.product.service.ProductService;
import store.product.service.PurchaseProductGenerator;
import store.promotion.domain.PromotionRepository;

class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = ProductRepository.getInstance();
        PromotionRepository promotionRepository = PromotionRepository.getInstance();
        final InitialDataLoader initialDataLoader = new InitialDataLoader(promotionRepository, productRepository);
        initialDataLoader.initialize();
        final PurchaseProductGenerator purchaseProductGenerator = new PurchaseProductGenerator(productRepository);
        productService = new ProductService(productRepository, purchaseProductGenerator);
    }

    @AfterEach
    void cleanUp() {
        productRepository.clear();
    }


    @DisplayName("구매 제품을 가져온다.")
    @Test
    void getPurchasePromotionProductTest() {
        //given
        final List<PurchaseProductRequest> purchaseProductRequests = List.of(
                new PurchaseProductRequest("콜라", 11),
                new PurchaseProductRequest("오렌지주스", 1),
                new PurchaseProductRequest("물", 10)
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

    @Test
    @DisplayName("상품 이름이 존재하지 않아 예외가 발생한다.")
    void validateProductNameTest() {
        //given
        final List<PurchaseProductRequest> purchaseProductRequests = List.of(
                new PurchaseProductRequest("없는 상품 입니다.", 1));

        //should
        assertThatIllegalArgumentException().isThrownBy(
                        () -> productService.generatePurchaseProducts(purchaseProductRequests))
                .withMessageContaining(ErrorMessage.INVALID_NOT_FOUND_PRODUCT.getMessage());

    }

    @Test
    @DisplayName("수량을 초과하여 예외가 발생한다.")
    void validateQuantityTest() {
        //given
        final List<PurchaseProductRequest> purchaseProductRequests = List.of(
                new PurchaseProductRequest("물", 1111111111));

        //should
        assertThatIllegalArgumentException().isThrownBy(
                        () -> productService.generatePurchaseProducts(purchaseProductRequests))
                .withMessageContaining(ErrorMessage.INVALID_EXCEEDS_PRODUCT_QUANTITY.getMessage());
    }

    @Test
    @DisplayName("상품이름이 중복되어 예외가 발생한다.")
    void validateDuplicateTest() {
        //given
        final List<PurchaseProductRequest> purchaseProductRequests = List.of(
                new PurchaseProductRequest("물", 1),
                new PurchaseProductRequest("물", 1));

        //should
        assertThatIllegalArgumentException().isThrownBy(
                        () -> productService.generatePurchaseProducts(purchaseProductRequests))
                .withMessageContaining(ErrorMessage.INVALID_WRONG_INPUT.getMessage());

    }

}
