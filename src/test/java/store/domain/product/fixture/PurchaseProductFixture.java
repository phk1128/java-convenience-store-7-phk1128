package store.domain.product.fixture;

import java.util.List;
import store.product.domain.PurchaseProductInfo;
import store.product.domain.ProductQuantity;
import store.product.domain.PurchaseProduct;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionDateTime;
import store.promotion.domain.PromotionType;

public class PurchaseProductFixture {

    public static final Promotion PROMOTION_BUY_2_GET_1_FREE = new Promotion(PromotionType.BUY_2_GET_1_FREE,
            new PromotionDateTime("2024-11-02", "2024-12-21"), 2,
            1);

    public static final PurchaseProductInfo PRODUCT_INFO = new PurchaseProductInfo("콜라", 1000, 1000
            , PROMOTION_BUY_2_GET_1_FREE);

    public static final ProductQuantity PRODUCT_QUANTITY = new ProductQuantity(0, 5, 0);

    public static final List<PurchaseProduct> PURCHASE_PRODUCTS = List.of(
            new PurchaseProduct(PRODUCT_INFO, PRODUCT_QUANTITY)
    );
}
