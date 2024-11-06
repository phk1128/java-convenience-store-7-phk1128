package store.config;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import store.domain.product.Product;
import store.domain.product.ProductRepository;
import store.domain.promotion.Promotion;
import store.domain.promotion.PromotionDateTime;
import store.domain.promotion.PromotionRepository;
import store.domain.promotion.PromotionType;
import store.util.StringParser;

public class InitialDataLoader {

    private final static String PATH_PROMOTIONS = "promotions.md";
    private final static String PATH_PRODUCTS = "products.md";
    private final static String DELIMITER_COMMA = ",";
    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;
    private Long PROMOTION_SEQUENCE = 1L;
    private Long PRODUCT_SEQUENCE = 1L;

    public InitialDataLoader(final PromotionRepository promotionRepository, final ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    public void initialize() {
        saveAllPromotions();
        saveAllProducts();
    }

    //name,buy,get,start_date,end_date
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

    //name,price,quantity,promotion
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
        final Product product = createProduct(tokens);
        productRepository.save(PRODUCT_SEQUENCE++, product);
    }

    private Product createProduct(final List<String> tokens) {
        final String promotionName = tokens.get(3);
        final Promotion promotion = promotionRepository.findByPromotionType(PromotionType.findByName(promotionName))
                .orElse(null);
        final int price = StringParser.parseToNumber(tokens.get(1));
        final int quantity = StringParser.parseToNumber(tokens.get(2));
        final Product product = new Product(tokens.get(0), price, promotion);
        product.updateQuantity(quantity);
        return product;
    }


    private void savePromotion(final String line) {
        final List<String> tokens = StringParser.parseToTokens(line, DELIMITER_COMMA);
        final Promotion promotion = createPromotion(tokens);
        promotionRepository.save(PROMOTION_SEQUENCE++, promotion);
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
            throw new IllegalArgumentException("파일 읽기를 실패하였습니다. 어플리케이션 종료 후 다시 실행해주세요.");
        }
    }
}
