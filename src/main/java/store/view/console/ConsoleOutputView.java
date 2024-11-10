package store.view.console;

import static store.constant.OutputMessage.*;

import java.util.List;
import java.util.stream.Collectors;
import store.constant.OutputMessage;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PaymentReceipt;
import store.payment.dto.PurchaseProductReceipt;
import store.promotion.dto.NonPromotionProduct;
import store.promotion.dto.PromotionProduct;
import store.product.dto.ProductStatus;
import store.view.OutputView;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printIntro() {
        printlnMessage(OutputMessage.LINE_SEPARATOR + INTRO + LINE_SEPARATOR);
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

    @Override
    public void printGuideNonPromotionProduct(final NonPromotionProduct nonPromotionProduct) {
        printlnMessage(LINE_SEPARATOR + String.format(GUIDE_NON_PROMOTION_PRODUCT, nonPromotionProduct.name(),
                nonPromotionProduct.quantity()));
    }

    @Override
    public void printAskMemberShipDiscount() {
        printlnMessage(LINE_SEPARATOR + ASK_MEMBER_SHIP_DISCOUNT);
    }

    @Override
    public void printGuidePromotionProduct(final PromotionProduct promotionProduct) {
        printlnMessage(LINE_SEPARATOR + String.format(GUIDE_PROMOTION_PRODUCT, promotionProduct.name(),
                promotionProduct.quantity()));
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
