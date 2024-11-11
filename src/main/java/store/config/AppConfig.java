package store.config;

import store.Convenience;
import store.payment.controller.PaymentController;
import store.payment.service.PaymentService;
import store.payment.view.PaymentOutputView;
import store.payment.view.console.ConsolePaymentOutputView;
import store.product.controller.ProductController;
import store.product.domain.ProductRepository;
import store.product.service.ProductService;
import store.product.service.PurchaseProductGenerator;
import store.product.view.ProductOutputView;
import store.product.view.console.ConsoleProductOutputView;
import store.promotion.controller.PromotionController;
import store.promotion.domain.PromotionRepository;
import store.promotion.service.PromotionService;
import store.promotion.view.PromotionOutputView;
import store.promotion.view.console.ConsolePromotionOutputView;
import store.view.InputView;
import store.view.console.ConsoleInputView;

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

    private ProductOutputView productOutputView() {
        return new ConsoleProductOutputView();
    }

    private PromotionOutputView promotionOutputView() {
        return new ConsolePromotionOutputView();
    }

    private PaymentOutputView paymentOutputView() {
        return new ConsolePaymentOutputView();
    }

    private ProductController productController() {
        return new ProductController(
                inputView(),
                productOutputView(),
                productService()
        );
    }

    private PromotionController promotionController() {
        return new PromotionController(
                inputView(),
                promotionOutputView(),
                promotionService()
        );
    }

    private PaymentController paymentController() {
        return new PaymentController(
                inputView(),
                paymentOutputView(),
                paymentService()
        );
    }


}
