package store.promotion.view.console;

import store.promotion.dto.NonPromotionProduct;
import store.promotion.dto.PromotionProduct;
import store.promotion.view.PromotionOutputView;

public class ConsolePromotionOutputView implements PromotionOutputView {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String GUIDE_PROMOTION_PRODUCT = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    public static final String GUIDE_NON_PROMOTION_PRODUCT = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";

    @Override
    public void printGuideNonPromotionProduct(final NonPromotionProduct nonPromotionProduct) {
        printlnMessage(LINE_SEPARATOR + String.format(GUIDE_NON_PROMOTION_PRODUCT, nonPromotionProduct.name(),
                nonPromotionProduct.quantity()));
    }

    @Override
    public void printGuidePromotionProduct(final PromotionProduct promotionProduct) {
        printlnMessage(LINE_SEPARATOR + String.format(GUIDE_PROMOTION_PRODUCT, promotionProduct.name(),
                promotionProduct.quantity()));
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }
}
