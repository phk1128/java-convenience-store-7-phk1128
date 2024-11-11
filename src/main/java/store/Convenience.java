package store;

import camp.nextstep.edu.missionutils.Console;
import store.payment.controller.PaymentController;
import store.product.controller.ProductController;
import store.promotion.controller.PromotionController;
import store.state.PurchaseState;

public class Convenience {

    private final ProductController productController;
    private final PromotionController promotionController;
    private final PaymentController paymentController;

    public Convenience(final ProductController productController, final PromotionController promotionController,
                       final PaymentController paymentController) {
        this.productController = productController;
        this.promotionController = promotionController;
        this.paymentController = paymentController;
    }

    public void purchase() {
        do {
            productController.run();
            promotionController.run();
            paymentController.run();
        } while (isRetryPurchase());
        Console.close();
    }

    private boolean isRetryPurchase() {
        return PurchaseState.getInstance().isRetryPurchase();
    }
}
