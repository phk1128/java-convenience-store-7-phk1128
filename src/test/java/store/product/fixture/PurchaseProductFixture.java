package store.product.fixture;

import java.util.List;
import store.product.domain.PurchaseProductInfo;
import store.product.domain.ProductQuantity;
import store.product.domain.PurchaseProduct;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionDateTime;
import store.promotion.domain.PromotionType;

public class PurchaseProductFixture {

    public static final PurchaseProduct PURCHASE_IN_TIME_PRODUCT = generatePurchaseProduct("0000-11-01", "9999-11-11", "콜라");
    public static final List<PurchaseProduct> PURCHASE_PRODUCTS = List.of(
            generatePurchaseProduct("0000-11-01", "9999-11-11", "콜라"),
            generatePurchaseProduct("0000-11-01", "9999-11-11", "사이다"),
            generatePurchaseProduct("0000-11-01", "9999-11-11", "에너지바")
    );


    private static PurchaseProduct generatePurchaseProduct(final String startTime, final String endTime, final String name) {
        final Promotion promotion = new Promotion(PromotionType.BUY_2_GET_1_FREE,
                new PromotionDateTime(startTime, endTime), 2,
                1);
        final PurchaseProductInfo purchaseProductInfo = new PurchaseProductInfo(name, 1000, 1000
                , promotion);
        final ProductQuantity productQuantity = new ProductQuantity(1, 5, 1);
        return new PurchaseProduct(purchaseProductInfo, productQuantity);

    }
}
