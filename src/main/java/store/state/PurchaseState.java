package store.state;

import java.util.ArrayList;
import java.util.List;
import store.product.domain.PurchaseProduct;

public class PurchaseState {

    private List<PurchaseProduct> purchaseProducts = new ArrayList<>();
    private boolean isRetryPurchase;

    public static PurchaseState getInstance() {
        return BillPughSingleton.INSTANCE;
    }

    public void updatePurchaseProducts(final List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public List<PurchaseProduct> getPurchaseProducts() {
        return purchaseProducts;
    }

    public void updateRetryPurchase(final boolean isRetryPurchase) {
        this.isRetryPurchase = isRetryPurchase;
    }

    public boolean isRetryPurchase() {
        return isRetryPurchase;
    }

    private PurchaseState() {
    }

    private static class BillPughSingleton {
        private static final PurchaseState INSTANCE = new PurchaseState();
    }
}
