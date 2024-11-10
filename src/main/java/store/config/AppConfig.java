package store.config;

import store.Convenience;
import store.payment.controller.PaymentController;
import store.payment.service.PaymentService;
import store.product.controller.ProductController;
import store.product.domain.ProductRepository;
import store.product.service.ProductService;
import store.product.service.PurchaseProductGenerator;
import store.promotion.controller.PromotionController;
import store.promotion.domain.PromotionRepository;
import store.promotion.service.PromotionService;
import store.view.console.ConsoleInputView;
import store.view.console.ConsoleOutputView;
import store.view.InputView;
import store.view.OutputView;

public class AppConfig {

    public Convenience convenience() {
        return new Convenience(
                productController(),
                promotionController(),
                paymentController()
        );
    }

    public InitialDataLoader initialDataLoader() {
        return new InitialDataLoader(
                promotionRepository(),
                productRepository()
        );
    }

    private ProductRepository productRepository() {
        return ProductRepository.getInstance();
    }

    private PromotionRepository promotionRepository() {
        return PromotionRepository.getInstance();
    }

    private PurchaseProductGenerator purchaseProductGenerator() {
        return new PurchaseProductGenerator(productRepository());
    }

    private ProductService productService() {
        return new ProductService(
                productRepository(),
                purchaseProductGenerator()
        );
    }

    private PromotionService promotionService() {
        return new PromotionService(productRepository());
    }

    private PaymentService paymentService() {
        return new PaymentService(productRepository());
    }

    private InputView inputView() {
        return new ConsoleInputView();
    }

    private OutputView outputView() {
        return new ConsoleOutputView();
    }

    private ProductController productController() {
        return new ProductController(
                inputView(),
                outputView(),
                productService()
        );
    }

    private PromotionController promotionController() {
        return new PromotionController(
                inputView(),
                outputView(),
                promotionService()
        );
    }

    private PaymentController paymentController() {
        return new PaymentController(
                inputView(),
                outputView(),
                paymentService()
        );
    }


}
