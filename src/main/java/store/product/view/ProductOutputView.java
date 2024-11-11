package store.product.view;

import java.util.List;
import store.product.dto.ProductStatus;

public interface ProductOutputView {

    void printIntro();

    void printProductStatus(final List<ProductStatus> productStatuses);

    void printAskPurchaseProduct();
}
