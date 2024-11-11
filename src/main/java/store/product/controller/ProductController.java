package store.product.controller;

import java.util.ArrayList;
import java.util.List;
import store.error.ErrorMessage;
import store.product.domain.PurchaseProduct;
import store.product.dto.PurchaseProductRequest;
import store.product.service.ProductService;
import store.product.view.ProductOutputView;
import store.state.PurchaseState;
import store.util.LoopTemplate;
import store.util.StringParser;
import store.util.StringValidator;
import store.view.InputView;

public class ProductController {

    private final InputView inputView;
    private final ProductOutputView outputView;
    private final ProductService productService;

    public ProductController(final InputView inputView, final ProductOutputView outputView,
                             final ProductService productService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.productService = productService;
    }

    public void run() {
        responseProductStatuses();
        final List<PurchaseProduct> purchaseProducts = requestPurchaseProduct();
        final PurchaseState purchaseState = PurchaseState.getInstance();
        purchaseState.updatePurchaseProducts(purchaseProducts);
    }

    private void responseProductStatuses() {
        outputView.printIntro();
        outputView.printProductStatus(productService.getProductStatuses());
    }

    private List<PurchaseProduct> requestPurchaseProduct() {
        return LoopTemplate.tryCatchLoop(() -> {
            outputView.printAskPurchaseProduct();
            final List<String> products = inputView.readProducts();
            final List<PurchaseProductRequest> purchaseProductRequests = generatePurchaseProductRequests(products);
            return productService.generatePurchaseProducts(purchaseProductRequests);
        });
    }

    private List<PurchaseProductRequest> generatePurchaseProductRequests(final List<String> values) {
        final List<PurchaseProductRequest> purchaseProductRequests = new ArrayList<>();
        for (String value : values) {
            StringValidator.validateFormat(value, ErrorMessage.INVALID_FORMAT_PRODUCT_AND_QUANTITY);
            final String product = StringParser.removePattern(value.trim(), "[\\[\\]]");
            purchaseProductRequests.add(createPurchaseProductRequest(product));
        }
        return purchaseProductRequests;
    }

    private PurchaseProductRequest createPurchaseProductRequest(final String product) {
        final String[] splitProduct = product.split("-");
        final String name = splitProduct[0];
        final int quantity = StringParser.parseToNumber(splitProduct[1]);
        return new PurchaseProductRequest(name, quantity);
    }

}
