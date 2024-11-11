package store.payment.view;

import java.util.List;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PaymentReceipt;
import store.payment.dto.PurchaseProductReceipt;

public interface PaymentOutputView {

    void printAskMemberShipDiscount();

    void printPurchaseProductReceipts(final List<PurchaseProductReceipt> purchaseProductReceipts);

    void printBenefitProductReceipts(final List<BenefitProductReceipt> benefitProductReceipts);

    void printPaymentReceipt(final PaymentReceipt paymentReceipt);

    void printAskRetryPurchase();

}
