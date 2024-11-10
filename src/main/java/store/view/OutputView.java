package store.view;

import java.util.List;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PaymentReceipt;
import store.payment.dto.PurchaseProductReceipt;
import store.promotion.dto.NonPromotionProduct;
import store.product.dto.ProductStatus;
import store.promotion.dto.PromotionProduct;

public interface OutputView {

    void printIntro();

    void printProductStatus(final List<ProductStatus> productStatuses);

    void printAskPurchaseProduct();

    void printGuideNonPromotionProduct(final NonPromotionProduct nonPromotionProduct);

    void printAskMemberShipDiscount();

    void printGuidePromotionProduct(final PromotionProduct promotionProduct);

    void printPurchaseProductReceipts(final List<PurchaseProductReceipt> purchaseProductReceipts);

    void printBenefitProductReceipts(final List<BenefitProductReceipt> benefitProductReceipts);

    void printPaymentReceipt(final PaymentReceipt paymentReceipt);

    void printAskRetryPurchase();
}
