package store.promotion.controller;

import java.util.List;
import store.constant.StoreCommand;
import store.product.domain.PurchaseProduct;
import store.promotion.dto.NonPromotionProduct;
import store.promotion.dto.PromotionProduct;
import store.promotion.service.PromotionService;
import store.promotion.view.PromotionOutputView;
import store.state.PurchaseState;
import store.util.LoopTemplate;
import store.view.InputView;

public class PromotionController {

    private final InputView inputView;
    private final PromotionOutputView outputView;
    private final PromotionService promotionService;

    public PromotionController(final InputView inputView, final PromotionOutputView outputView,
                               final PromotionService promotionService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.promotionService = promotionService;
    }

    public void run() {
        final List<PurchaseProduct> purchaseProducts = PurchaseState.getInstance().getPurchaseProducts();
        for (PurchaseProduct product : purchaseProducts) {
            checkPromotion(product);
            checkNonPromotion(product);
        }
    }

    private void checkPromotion(final PurchaseProduct product) {
        final PromotionProduct promotionProduct = promotionService.getPromotionProduct(product);
        if (promotionService.canReceiveMoreProduct(promotionProduct)) {
            final StoreCommand storeCommand = requestPromotionStoreCommand(promotionProduct);
            promotionService.applyPromotion(storeCommand, product);
        }

    }

    private void checkNonPromotion(final PurchaseProduct product) {
        final NonPromotionProduct nonPromotionProduct = promotionService.getNonPromotionProduct(product);
        if (promotionService.canNotReceivePromotion(nonPromotionProduct)) {
            final StoreCommand storeCommand = requestNonPromotionStoreCommand(nonPromotionProduct);
            promotionService.applyNonPromotion(storeCommand, product);
        }
    }

    private StoreCommand requestPromotionStoreCommand(final PromotionProduct promotionProduct) {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printGuidePromotionProduct(promotionProduct);
            return inputView.readStoreCommand();
        });
    }

    private StoreCommand requestNonPromotionStoreCommand(final NonPromotionProduct nonPromotionProduct) {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printGuideNonPromotionProduct(nonPromotionProduct);
            return inputView.readStoreCommand();
        });
    }

}
