package store.product.fixture;

import store.product.domain.Product;
import store.product.domain.ProductInfo;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionDateTime;
import store.promotion.domain.PromotionType;

public class ProductFixture {

    public static final Product BUY_2_GET_1_FREE_IN_TIME_PRODUCT = buy2Get1FreeInTimeProduct();
    public static final Product BUY_1_GET_1_FREE_IN_TIME_PRODUCT = buy1Get1FreeIntimeProduct();
    public static final Product BUY_2_GET_1_FREE_OUT_TIME_PRODUCT = buy2Get1FreeOutTimeProduct();
    public static final Product BUY_1_GET_1_FREE_OUT_TIME_PRODUCT = buy1Get1FreeOutTimeProduct();
    public static final Product NON_PROMOTIN_PRODUCT = generateNonPromotionProduct(10L, "콜라");


    private static Product generateNonPromotionProduct(final Long id, final String name) {
        final ProductInfo productInfo = new ProductInfo(name, 1000, null);
        return new Product(id,productInfo,10);
    }


    private static Product buy2Get1FreeInTimeProduct() {
        final PromotionType buy2Get1Free = PromotionType.BUY_2_GET_1_FREE;
        final PromotionDateTime promotionDateTime = new PromotionDateTime("0000-11-01", "9999-11-30");
        final Promotion promotion = new Promotion(buy2Get1Free, promotionDateTime, 2, 1);
        final ProductInfo productInfo = new ProductInfo("콜라", 1000, promotion);
        return new Product(1L, productInfo, 10);
    }

    private static Product buy2Get1FreeOutTimeProduct() {
        final PromotionType buy2Get1Free = PromotionType.BUY_2_GET_1_FREE;
        final PromotionDateTime promotionDateTime = new PromotionDateTime("0000-11-01","0000-11-30");
        final Promotion promotion = new Promotion(buy2Get1Free, promotionDateTime, 2, 1);
        final ProductInfo productInfo = new ProductInfo("콜라", 1000, promotion);
        return new Product(2L, productInfo, 10);
    }


    private static Product buy1Get1FreeIntimeProduct() {
        final PromotionType mdPick = PromotionType.MD_PICK;
        final PromotionDateTime promotionDateTime = new PromotionDateTime("0000-12-01", "9999-12-28");
        final Promotion promotion = new Promotion(mdPick, promotionDateTime, 1, 1);
        final ProductInfo productInfo = new ProductInfo("컵라면", 1500, promotion);
        return new Product(3L, productInfo, 10);
    }

    private static Product buy1Get1FreeOutTimeProduct() {
        final PromotionType mdPick = PromotionType.MD_PICK;
        final PromotionDateTime promotionDateTime = new PromotionDateTime("0000-12-01", "0000-12-28");
        final Promotion promotion = new Promotion(mdPick, promotionDateTime, 1, 1);
        final ProductInfo productInfo = new ProductInfo("컵라면", 1500, promotion);
        return new Product(4L, productInfo, 10);
    }
}
