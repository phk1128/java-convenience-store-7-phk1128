package store;

import static camp.nextstep.edu.missionutils.test.Assertions.assertNowTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConvenienceTest extends NsTest {

    @Test
    @DisplayName("프로모션 상품의 혜택을 받지 않아 멤버십 할인이 적용된다.")
    void nonPromotionProductMemberShipApplyTest() {
        assertNowTest(() -> {
            run("[감자칩-1]", "N", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains("멤버십할인-450", "내실돈1,050");
        }, LocalDate.of(2024, 11, 12).atStartOfDay());

    }

    @Test
    @DisplayName("프로모션 상품의 혜택을 받으면 멤버십 할인이 적용되지 않는다.")
    void promotionProductMemberShipApplyTest() {
        assertNowTest(() -> {
            run("[감자칩-1]", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains("감자칩1", "감자칩2", "멤버십할인-0",
                    "총구매액23,000", "행사할인-1,500", "내실돈1,500");
        }, LocalDate.of(2024, 11, 12).atStartOfDay());

    }

    @Test
    @DisplayName("일반 상품에는 멤버십 할인이 적용된다.")
    void normalProductMemberShipApplyTest() {
        assertNowTest(() -> {
            run("[물-2]", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains("멤버십할인-300", "내실돈700");
        }, LocalDate.of(2024, 11, 12).atStartOfDay());

    }

    @Test
    @DisplayName("A,B 프로모션 상품 중 B는 프로모션 혜택을 받지 않아 B만 멤버십 혜택이 적용된다.")
    void shouldApplyOnlyMembershipToNonPromotionalProduct() {
        assertNowTest(() -> {
            run("[감자칩-1],[콜라-2]", "Y", "N", "Y", "Y", "N");
            assertThat(output().replaceAll("\\s", "")).contains("감자칩1", "감자칩2", "콜라2",
                    "총구매액45,000", "행사할인-1,500",
                    "멤버십할인-600", "내실돈2,900");
        }, LocalDate.of(2024, 11, 12).atStartOfDay());

    }


    @DisplayName("프로모션 재고가 먼저 차감 된다.")
    @Test
    void decreasePromotionProductQuantityTest() {
        assertSimpleTest(() -> {
            run("[콜라-3]", "N", "Y", "[물-1]", "N", "N");
            assertThat(output()).contains(
                    "- 콜라 1,000원 10개 탄산2+1",
                    "- 콜라 1,000원 10개",
                    "- 사이다 1,000원 8개 탄산2+1",
                    "- 사이다 1,000원 7개",
                    "- 오렌지주스 1,800원 9개 MD추천상품",
                    "- 오렌지주스 1,800원 재고 없음",
                    "- 탄산수 1,200원 5개 탄산2+1",
                    "- 탄산수 1,200원 재고 없음",
                    "- 물 500원 10개",
                    "- 비타민워터 1,500원 6개",
                    "- 감자칩 1,500원 5개 반짝할인",
                    "- 감자칩 1,500원 5개",
                    "- 초코바 1,200원 5개 MD추천상품",
                    "- 초코바 1,200원 5개",
                    "- 에너지바 2,000원 5개",
                    "- 정식도시락 6,400원 8개",
                    "- 컵라면 1,700원 1개 MD추천상품",
                    "- 컵라면 1,700원 10개",
                    "- 콜라 1,000원 7개 탄산2+1",
                    "- 콜라 1,000원 10개",
                    "- 사이다 1,000원 8개 탄산2+1",
                    "- 사이다 1,000원 7개",
                    "- 오렌지주스 1,800원 9개 MD추천상품",
                    "- 오렌지주스 1,800원 재고 없음",
                    "- 탄산수 1,200원 5개 탄산2+1",
                    "- 탄산수 1,200원 재고 없음",
                    "- 물 500원 10개",
                    "- 비타민워터 1,500원 6개",
                    "- 감자칩 1,500원 5개 반짝할인",
                    "- 감자칩 1,500원 5개",
                    "- 초코바 1,200원 5개 MD추천상품",
                    "- 초코바 1,200원 5개",
                    "- 에너지바 2,000원 5개",
                    "- 정식도시락 6,400원 8개",
                    "- 컵라면 1,700원 1개 MD추천상품",
                    "- 컵라면 1,700원 10개"
            );
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

}
