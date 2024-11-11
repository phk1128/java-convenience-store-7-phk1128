package store.payment.view.console;

import java.util.List;
import java.util.stream.Collectors;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PaymentReceipt;
import store.payment.dto.PurchaseProductReceipt;
import store.payment.view.PaymentOutputView;

public class ConsolePaymentOutputView implements PaymentOutputView {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String ASK_MEMBER_SHIP_DISCOUNT = "멤버십 할인을 받으시겠습니까? (Y/N)";
    public static final String PURCHASE_PRODUCT_LINE = "==============W 편의점================";
    public static final String PURCHASE_PRODUCT_COLUMN = "상품명\t\t\t\t수량\t\t  금액";
    public static final String NAME_AND_QUANTITY_AND_PRICE = "%-16s\t%,-9d %,-6d";
    public static final String BENEFIT_PRODUCT_LINE = "=============증\t\t정===============";
    public static final String NAME_AND_QUANTITY = "%-15s\t%,-9d";
    public static final String NAME_AND_PRICE = "%-17s\t\t\t  %,-6d";
    public static final String NAME_AND_DISCOUNT = "%-17s\t\t\t -%,-6d";
    public static final String PAYMENT_LINE = "====================================";
    public static final String TOTAL_PRICE = "총구매액";
    public static final String PROMOTION_DISCOUNT = "행사할인";
    public static final String MEMBER_SHIP_DISCOUNT = "멤버십할인";
    public static final String PAYMENT_PRICE = "내실돈";
    public static final String ASK_RETRY_PURCHASE = "감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)";

    @Override
    public void printAskMemberShipDiscount() {
        printlnMessage(LINE_SEPARATOR + ASK_MEMBER_SHIP_DISCOUNT);
    }

    @Override
    public void printPurchaseProductReceipts(final List<PurchaseProductReceipt> purchaseProductReceipts) {
        printlnMessage(LINE_SEPARATOR + PURCHASE_PRODUCT_LINE + LINE_SEPARATOR + PURCHASE_PRODUCT_COLUMN);
        final String message = purchaseProductReceipts.stream()
                .map(receipt -> String.format(NAME_AND_QUANTITY_AND_PRICE, receipt.name(), receipt.quantity(),
                        receipt.price()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        if (!message.isBlank()) {
            printlnMessage(message);
        }
    }

    @Override
    public void printBenefitProductReceipts(final List<BenefitProductReceipt> benefitProductReceipts) {
        printlnMessage(BENEFIT_PRODUCT_LINE);
        final String message = benefitProductReceipts.stream()
                .map(receipt -> String.format(NAME_AND_QUANTITY, receipt.name(), receipt.quantity()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        if (!message.isBlank()) {
            printlnMessage(message);
        }
    }

    @Override
    public void printPaymentReceipt(final PaymentReceipt paymentReceipt) {
        printlnMessage(PAYMENT_LINE);
        printlnMessage(String.format(NAME_AND_QUANTITY_AND_PRICE, TOTAL_PRICE, paymentReceipt.productTotalQuantity(),
                paymentReceipt.productTotalPrice()));
        printlnMessage(String.format(NAME_AND_DISCOUNT, PROMOTION_DISCOUNT, paymentReceipt.promotionDiscount()));
        printlnMessage(String.format(NAME_AND_DISCOUNT, MEMBER_SHIP_DISCOUNT, paymentReceipt.memberShipDiscount()));
        printlnMessage(String.format(NAME_AND_PRICE, PAYMENT_PRICE, paymentReceipt.paymentPrice()));
    }

    @Override
    public void printAskRetryPurchase() {
        printlnMessage(LINE_SEPARATOR + ASK_RETRY_PURCHASE);
    }

    private void printlnMessage(final String message) {
        System.out.println(message);
    }

}
