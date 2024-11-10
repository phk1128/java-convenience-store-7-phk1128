package store.config;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import store.error.ErrorMessage;
import store.error.exception.InvalidFailInitialDataLoadException;
import store.product.domain.Product;
import store.product.domain.ProductInfo;
import store.product.domain.ProductRepository;
import store.promotion.domain.Promotion;
import store.promotion.domain.PromotionDateTime;
import store.promotion.domain.PromotionRepository;
import store.promotion.domain.PromotionType;
import store.util.StringParser;

public class InitialDataLoader {

    private static final String PATH_PROMOTIONS = "promotions.md";
    private static final String PATH_PRODUCTS = "products.md";
    private static final String DELIMITER_COMMA = ",";
    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;
    private Long promotionSequence = 1L;
    private Long productSequence = 1L;

    public InitialDataLoader(final PromotionRepository promotionRepository, final ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    public void initialize() {
        saveAllPromotions();
        saveAllProducts();
    }

    private void saveAllPromotions() {
        final Queue<String> fileLines = readFileLines(PATH_PROMOTIONS);
        if (fileLines.isEmpty()) {
            return;
        }
        fileLines.poll();
        while (!fileLines.isEmpty()) {
            final String line = fileLines.poll();
            savePromotion(line);
        }
    }


    private void saveAllProducts() {
        final Queue<String> fileLines = readFileLines(PATH_PRODUCTS);
        if (fileLines.isEmpty()) {
            return;
        }
        fileLines.poll();
        while (!fileLines.isEmpty()) {
            final String line = fileLines.poll();
            saveProduct(line);
        }
    }

    private void saveProduct(final String line) {
        final List<String> tokens = StringParser.parseToTokens(line, DELIMITER_COMMA);
        final Long id = productSequence++;
        final Product product = createProduct(id,tokens);
        productRepository.save(id, product);
    }

    private Product createProduct(final Long id, final List<String> tokens) {
        final String promotionName = tokens.get(3);
        final Promotion promotion = promotionRepository.findByPromotionType(PromotionType.findByName(promotionName))
                .orElse(null);
        final int price = StringParser.parseToNumber(tokens.get(1));
        final int quantity = StringParser.parseToNumber(tokens.get(2));
        final ProductInfo productInfo = new ProductInfo(tokens.get(0), price, promotion);
        return new Product(id, productInfo, quantity);
    }


    private void savePromotion(final String line) {
        final List<String> tokens = StringParser.parseToTokens(line, DELIMITER_COMMA);
        final Promotion promotion = createPromotion(tokens);
        promotionRepository.save(promotionSequence++, promotion);
    }

    private Promotion createPromotion(final List<String> tokens) {
        final PromotionType type = PromotionType.findByName(tokens.get(0));
        final int buy = StringParser.parseToNumber(tokens.get(1));
        final int get = StringParser.parseToNumber(tokens.get(2));
        final PromotionDateTime promotionDateTime = new PromotionDateTime(tokens.get(3), tokens.get(4));
        return new Promotion(type, promotionDateTime, buy, get);
    }


    private Queue<String> readFileLines(final String path) {
        try {
            URI uri = getClass().getClassLoader().getResource(path).toURI();
            return new ArrayDeque<>(Files.readAllLines(Paths.get(uri)));
        } catch (Exception e) {
            throw new InvalidFailInitialDataLoadException(ErrorMessage.INVALID_FAIL_INITIAL_DATA_LOAD);
        }
    }
}
