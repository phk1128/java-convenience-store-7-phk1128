package store.promotion.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @DisplayName("프로모션 계산 후 남은 수량을 가져온다.")
    @Test
    void getRemainingQuantityTest() {
        //given
        final PromotionDateTime promotionDateTime1 = new PromotionDateTime("0000-00-00", "9999-99-99");
        final Promotion promotion1 = new Promotion(PromotionType.BUY_2_GET_1_FREE, promotionDateTime1, 2, 1);
        final PromotionDateTime promotionDateTime2 = new PromotionDateTime("0000-00-00", "0000-00-00");
        final Promotion promotion2 = new Promotion(PromotionType.BUY_2_GET_1_FREE, promotionDateTime2, 2, 1);

        //when
        final int remainingQuantity1 = promotion1.getRemainingQuantity(7);
        final int remainingQuantity2 = promotion1.getRemainingQuantity(6);
        final int remainingQuantity3 = promotion2.getRemainingQuantity(7);

        //then
        assertAll(
                () -> assertThat(remainingQuantity1).isEqualTo(1),
                () -> assertThat(remainingQuantity2).isZero(),
                () -> assertThat(remainingQuantity3).isZero()
        );

    }

    @DisplayName("프로모션 받을 수 있는 수량을 가져온다.")
    @Test
    void getPromotionQuantityTest() {
        //given
        final PromotionDateTime promotionDateTime1 = new PromotionDateTime("0000-00-00", "9999-99-99");
        final Promotion promotion1 = new Promotion(PromotionType.BUY_2_GET_1_FREE, promotionDateTime1, 2, 1);
        final PromotionDateTime promotionDateTime2 = new PromotionDateTime("0000-00-00", "0000-00-00");
        final Promotion promotion2 = new Promotion(PromotionType.BUY_2_GET_1_FREE, promotionDateTime2, 2, 1);

        //when
        final int promotionQuantity1 = promotion1.getPromotionQuantity(6);
        final int promotionQuantity2 = promotion1.getPromotionQuantity(7);
        final int promotionQuantity3 = promotion2.getPromotionQuantity(6);

        //then
        assertAll(
                () -> assertThat(promotionQuantity1).isEqualTo(2),
                () -> assertThat(promotionQuantity2).isEqualTo(2),
                () -> assertThat(promotionQuantity3).isZero()
        );
    }

    @DisplayName("프로모션을 받을 수 있는지 확인한다.")
    @Test
    void canPromotionTest() {
        //given
        final PromotionDateTime promotionDateTime1 = new PromotionDateTime("0000-00-00", "9999-99-99");
        final Promotion promotion1 = new Promotion(PromotionType.BUY_2_GET_1_FREE, promotionDateTime1, 2, 1);
        final PromotionDateTime promotionDateTime2 = new PromotionDateTime("0000-00-00", "0000-00-00");
        final Promotion promotion2 = new Promotion(PromotionType.BUY_2_GET_1_FREE, promotionDateTime2, 2, 1);

        //when
        final boolean result1 = promotion1.canPromotion(2);
        final boolean result2 = promotion1.canPromotion(3);
        final boolean result3 = promotion2.canPromotion(2);

        //then
        assertAll(
                () -> assertThat(result1).isTrue(),
                () -> assertThat(result2).isFalse(),
                () -> assertThat(result3).isFalse()
        );
    }
}
