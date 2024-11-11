package store.product.view.console;

import java.util.List;
import java.util.stream.Collectors;
import store.product.dto.ProductStatus;
import store.product.view.ProductOutputView;

public class ConsoleProductOutputView implements ProductOutputView {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String INTRO = "안녕하세요. W편의점입니다." + LINE_SEPARATOR + "현재 보유하고 있는 상품입니다.";
    public static final String PRODUCT_STATUS = "- %s %,d원 %s %s";
    public static final String PRODUCT_HAS_QUANTITY_FORMAT = "%d개";
    public static final String PRODUCT_HAS_NO_QUANTITY_FORMAT = "재고 없음";
    public static final String ASK_PURCHASE_PRODUCT = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";

    @Override
    public void printIntro() {
        printlnMessage(LINE_SEPARATOR + INTRO + LINE_SEPARATOR);
    }

    @Override
    public void printProductStatus(final List<ProductStatus> productStatuses) {
        final String message = productStatuses.stream()
                .map(productStatus -> String.format(PRODUCT_STATUS, productStatus.name(), productStatus.price(),
                        convertToQuantityFormat(productStatus), productStatus.promotion()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        printlnMessage(message);
    }

    @Override
    public void printAskPurchaseProduct() {
        printlnMessage(LINE_SEPARATOR + ASK_PURCHASE_PRODUCT);
    }

    private String convertToQuantityFormat(final ProductStatus productStatus) {
        if (productStatus.quantity() == 0) {
            return PRODUCT_HAS_NO_QUANTITY_FORMAT;
        }
        return String.format(PRODUCT_HAS_QUANTITY_FORMAT, productStatus.quantity());
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }
}
