package store.promotion.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.config.InitialDataLoader;
import store.constant.StoreCommand;
import store.product.domain.ProductRepository;
import store.product.domain.PurchaseProduct;
import store.product.fixture.PurchaseProductFixture;
import store.promotion.domain.PromotionRepository;
import store.promotion.dto.NonPromotionProduct;
import store.promotion.dto.PromotionProduct;

class PromotionServiceTest {

    private PromotionService promotionService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        PromotionRepository promotionRepository = new PromotionRepository();
        final InitialDataLoader initialDataLoader = new InitialDataLoader(promotionRepository, productRepository);
        initialDataLoader.initialize();
        promotionService = new PromotionService(productRepository);
    }

    @DisplayName("구매 의사가 없는 경우 비프로모션 해당 상품들을 구매 상품에서 제외한다.")
    @Test
    void applyNonPromotionTest() {
        //given
        final StoreCommand storeCommand = StoreCommand.NO;
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //when
        promotionService.applyNonPromotion(storeCommand, purchaseInTimeProduct);

        //then
        assertAll(
                () -> assertThat(purchaseInTimeProduct.getNonPromotionQuantity()).isZero(),
                () -> assertThat(purchaseInTimeProduct.getNormalQuantity()).isZero()
        );
    }

    @DisplayName("프로모션을 받을 경우 해당 상품 수량을 1개 더 추가한다.")
    @Test
    void applyPromotionTest() {
        //given
        final StoreCommand storeCommand = StoreCommand.YES;
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;
        final int promotionQuantity = purchaseInTimeProduct.getPromotionQuantity();

        //when
        promotionService.applyPromotion(storeCommand, purchaseInTimeProduct);

        //then
        assertThat(purchaseInTimeProduct.getPromotionQuantity()).isEqualTo(promotionQuantity + 1);
    }

    @DisplayName("프로모션을 받을 수 있는지 확인한다.")
    @Test
    void canReceiveMoreProductTest() {
        //given
        final PromotionProduct promotionProduct1 = new PromotionProduct("콜라", 2);
        final PromotionProduct promotionProduct2 = null;

        //when
        final boolean result1 = promotionService.canReceiveMoreProduct(promotionProduct1);
        final boolean result2 = promotionService.canReceiveMoreProduct(promotionProduct2);

        //then
        assertAll(
                () -> assertThat(result1).isTrue(),
                () -> assertThat(result2).isFalse()
        );
    }

    @DisplayName("프로모션을 받을 수 없는지 확인한다.")
    @Test
    void canNotReceivePromotionTest() {
        //given
        final NonPromotionProduct nonPromotionProduct1 = new NonPromotionProduct(true, "콜라", 3);
        final NonPromotionProduct nonPromotionProduct2 = new NonPromotionProduct(false, "사이다", 2);

        //when
        final boolean result1 = promotionService.canNotReceivePromotion(nonPromotionProduct1);
        final boolean result2 = promotionService.canNotReceivePromotion(nonPromotionProduct2);

        //then
        assertAll(
                () -> assertThat(result1).isTrue(),
                () -> assertThat(result2).isFalse()
        );
    }

    @DisplayName("구매 상품에서 프로모션 증정 상품을 가져온다.")
    @Test
    void getPromotionProductTest() {
        //given
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //when
        final PromotionProduct promotionProduct = promotionService.getPromotionProduct(purchaseInTimeProduct);

        //then
        assertThat(promotionProduct.quantity()).isEqualTo(purchaseInTimeProduct.getPromotionBenefitQuantity());

    }

    @DisplayName("구매 상품에서 비프로모션 상품을 가져온다.")
    @Test
    void getNonPromotionProductTest() {
        //given
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //when
        final NonPromotionProduct nonPromotionProduct = promotionService.getNonPromotionProduct(purchaseInTimeProduct);

        //
        assertThat(nonPromotionProduct.quantity()).isEqualTo(
                purchaseInTimeProduct.getNonPromotionQuantity() + purchaseInTimeProduct.getNormalQuantity());
    }
}
