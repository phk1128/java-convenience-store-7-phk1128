package store.constant;

public class OutputMessage {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String INTRO = "안녕하세요. W편의점입니다." + LINE_SEPARATOR + "현재 보유하고 있는 상품입니다.";
    public static final String PRODUCT_STATUS = "- %s %,d원 %s %s";
    public static final String PRODUCT_HAS_QUANTITY_FORMAT = "%d개";
    public static final String PRODUCT_HAS_NO_QUANTITY_FORMAT = "재고 없음";
    public static final String ASK_PURCHASE_PRODUCT = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    public static final String ASK_MEMBER_SHIP_DISCOUNT = "멤버십 할인을 받으시겠습니까? (Y/N)";
    public static final String GUIDE_PROMOTION_PRODUCT = "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)";
    public static final String GUIDE_NON_PROMOTION_PRODUCT = "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)";
    public static final String PURCHASE_PRODUCT_LINE = "==============W 편의점================";
    public static final String PURCHASE_PRODUCT_COLUMN = "상품명\t\t\t\t수량\t\t  금액";
    public static final String NAME_AND_QUANTITY_AND_PRICE = "%-16s\t%,-9d %,-6d";
    public static final String BENEFIT_PRODUCT_LINE = "=============증\t\t정===============";
    public static final String NAME_AND_QUANTITY = "%-15s\t%,-9d";
    public static final String NAME_AND_PRICE = "%-17s\t\t\t  %,-6d";
    public static final String NAME_AND_DISCOUNT = "%-17s\t\t\t  -%,-6d";
    public static final String PAYMENT_LINE = "====================================";
    public static final String TOTAL_PRICE = "총구매액";
    public static final String PROMOTION_DISCOUNT = "행사할인";
    public static final String MEMBER_SHIP_DISCOUNT = "멤버십할인";
    public static final String PAYMENT_PRICE = "내실돈";

    public static final String ASK_RETRY_PURCHASE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";

    private OutputMessage() {

    }

}
