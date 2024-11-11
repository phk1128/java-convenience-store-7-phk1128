package store.product.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.product.fixture.PurchaseProductFixture;

class PurchaseProductTest {


    @DisplayName("프로모션 기간 중이다.")
    @Test
    void isInPromotionTimeTest() {
        //given
        final PurchaseProduct purchaseProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //should
        assertThat(purchaseProduct.isInPromotionTime()).isTrue();

    }

    @DisplayName("증정 상품 가격을 계산한다.")
    @Test
    void calculatePromotionPriceTest() {
        //given
        final int price = 1000;
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //when
        final int promotionPrice = purchaseInTimeProduct.calculatePromotionPrice();

        //then
        assertThat(promotionPrice).isEqualTo(price);
    }

    @DisplayName("프로모션 상품 총 가격을 계산한다.")
    @Test
    void calculatePromotionTotalPriceTest() {
        //given
        final int price = 1000;
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //when
        final int promotionQuantity = purchaseInTimeProduct.getPromotionQuantity();
        final int promotionPrice = purchaseInTimeProduct.calculatePromotionTotalPrice();

        //then
        assertThat(promotionPrice).isEqualTo(promotionQuantity * price);

    }

    @DisplayName("비프로모션 상품 총 가격을 계산한다.")
    @Test
    void calculateNonPromotionPriceTest() {
        //given
        final int price = 1000;
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //when
        final int nonPromotionQuantity = purchaseInTimeProduct.getNonPromotionQuantity();
        final int normalQuantity = purchaseInTimeProduct.getNormalQuantity();
        final int promotionPrice = purchaseInTimeProduct.calculateNonPromotionPrice();

        //then
        assertThat(promotionPrice).isEqualTo(price * (nonPromotionQuantity + normalQuantity));
    }

    @DisplayName("총 상품 가격을 계산한다.")
    @Test
    void calculateTotalPriceTest() {
        //given
        final int price = 1000;
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //when
        final int totalQuantity = purchaseInTimeProduct.getTotalQuantity();
        final int totalPrice = purchaseInTimeProduct.calculateTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(totalQuantity * price);
    }


    @DisplayName("프로모션 받을 수 있는 수량 조건을 충족하여 프로모션을 받을 수 있다.")
    @Test
    void canReceiveMorePromotion() {
        //given
        final PurchaseProduct purchaseInTimeProduct = PurchaseProductFixture.PURCHASE_IN_TIME_PRODUCT;

        //should
        assertThat(purchaseInTimeProduct.canReceiveMorePromotion()).isTrue();
    }
}
