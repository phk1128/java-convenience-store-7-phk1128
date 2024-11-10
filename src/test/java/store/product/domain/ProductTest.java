package store.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.product.fixture.ProductFixture;

class ProductTest {


    @DisplayName("프로모션이 없는 경우 프로모션 이름을 빈문자열로 가져온다.")
    @Test
    void getPromotionNameTest() {
        //given
        final ProductInfo productInfo = new ProductInfo("콜라", 1000, null);
        final Product product = new Product(1L, productInfo, 10);

        //when
        final String promotionName = product.getPromotionName();

        //given
        assertThat(promotionName).isBlank();
    }

    @DisplayName("프로모션 기간 중이고 상품 수량이 buy와 같을 경우 프로모션을 더 받을 수 있다.")
    @Test
    void canReceiveMorePromotionTest() {
        //given
        final Product product1 = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;
        final Product product2 = ProductFixture.BUY_1_GET_1_FREE_OUT_TIME_PRODUCT;

        //when
        final boolean result1 = product1.canReceiveMorePromotion(product1.getBuyQuantity());
        final boolean result2 = product2.canReceiveMorePromotion(product2.getBuyQuantity());

        //then
        assertAll(
                () -> assertThat(result1).isTrue(),
                () -> assertThat(result2).isFalse()
        );
    }

    @DisplayName("프로모션 적용 후 남은 수량을 계산한다.")
    @Test
    void calculateRemainingQuantityTest() {
        //given
        final Product product = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;

        //when
        final int quantity = product.calculateRemainingQuantity(7);

        //then
        assertThat(quantity).isEqualTo(1);
    }
}
