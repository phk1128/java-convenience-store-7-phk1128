package store.payment.controller;

import java.util.List;
import store.constant.StoreCommand;
import store.payment.domain.Payment;
import store.payment.dto.BenefitProductReceipt;
import store.payment.dto.PaymentReceipt;
import store.payment.dto.PurchaseProductReceipt;
import store.payment.service.PaymentService;
import store.product.domain.PurchaseProduct;
import store.state.PurchaseState;
import store.util.LoopTemplate;
import store.view.InputView;
import store.view.OutputView;

public class PaymentController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PaymentService paymentService;

    public PaymentController(final InputView inputView, final OutputView outputView,
                             final PaymentService paymentService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.paymentService = paymentService;
    }

    public void run() {
        final List<PurchaseProduct> purchaseProducts = PurchaseState.getInstance().getPurchaseProducts();
        final Payment payment = new Payment(purchaseProducts);
        checkMemberShip(payment);
        paymentService.decreaseProductQuantity(purchaseProducts);
        responsePurchaseProductReceipt(purchaseProducts);
        responseBenefitProductReceipt(purchaseProducts);
        responsePaymentReceipt(payment);
        checkRetryPurchase();
    }

    private void checkRetryPurchase() {
        final StoreCommand storeCommand = requestRetryPurchaseCommand();
        paymentService.updateRetryPurchase(storeCommand);
    }

    private StoreCommand requestRetryPurchaseCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskRetryPurchase();
            return inputView.readStoreCommand();
        });
    }

    private void responsePurchaseProductReceipt(final List<PurchaseProduct> purchaseProducts) {
        final List<PurchaseProductReceipt> purchaseProductReceipt = paymentService.getPurchaseProductReceipt(
                purchaseProducts);
        outputView.printPurchaseProductReceipts(purchaseProductReceipt);
    }

    private void responseBenefitProductReceipt(final List<PurchaseProduct> purchaseProducts) {
        final List<BenefitProductReceipt> benefitProductReceipt = paymentService.getBenefitProductReceipt(
                purchaseProducts);
        outputView.printBenefitProductReceipts(benefitProductReceipt);
    }

    private void responsePaymentReceipt(final Payment payment) {
        final PaymentReceipt paymentReceipt = PaymentReceipt.of(payment);
        outputView.printPaymentReceipt(paymentReceipt);
    }

    private void checkMemberShip(final Payment payment) {
        if (payment.canMemberShipDiscount()) {
            final StoreCommand storeCommand = requestMemberShipStoreCommand();
            payment.applyMemberShipDiscount(storeCommand);
        }
    }

    private StoreCommand requestMemberShipStoreCommand() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskMemberShipDiscount();
            return inputView.readStoreCommand();
        });
    }
}
