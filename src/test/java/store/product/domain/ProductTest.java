package store.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionDateTime;
import store.promotion.domain.PromotionType;

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
        final PromotionType buy2Get1Free = PromotionType.BUY_2_GET_1_FREE;
        final PromotionDateTime promotionDateTime1 = new PromotionDateTime("2024-11-01", "2024-11-30");
        final Promotion promotion1 = new Promotion(buy2Get1Free, promotionDateTime1, 2, 1);
        final ProductInfo productInfo1 = new ProductInfo("콜라", 1000, promotion1);
        final Product product1 = new Product(1L, productInfo1, 10);
        final PromotionType mdPick = PromotionType.MD_PICK;
        final PromotionDateTime promotionDateTime2 = new PromotionDateTime("2024-12-01", "2024-12-28");
        final Promotion promotion2 = new Promotion(mdPick, promotionDateTime2, 1, 1);
        final ProductInfo productInfo2 = new ProductInfo("컵라면", 1500, promotion2);
        final Product product2 = new Product(2L, productInfo2, 10);

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
        final PromotionType buy2Get1Free = PromotionType.BUY_2_GET_1_FREE;
        final PromotionDateTime promotionDateTime = new PromotionDateTime("2024-11-01", "2024-11-30");
        final Promotion promotion = new Promotion(buy2Get1Free, promotionDateTime, 2, 1);
        final ProductInfo productInfo = new ProductInfo("콜라", 1000, promotion);
        final Product product = new Product(1L, productInfo, 10);

        //when
        final int quantity = product.calculateRemainingQuantity(7);

        //then
        assertThat(quantity).isEqualTo(1);
    }
}
