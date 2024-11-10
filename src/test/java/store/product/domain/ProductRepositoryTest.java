package store.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.error.ErrorMessage;
import store.product.fixture.ProductFixture;

class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = ProductRepository.getInstance();
    }


    @DisplayName("상품 이름으로 프로모션 상품을 찾는데 성공한다.")
    @Test
    void pass_findPromotionProductByProductNameTest() {
        //given
        final Product product = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;
        final String name = product.getName();

        //when
        productRepository.save(product.getId(), product);
        final Product promotionProductByProductName = productRepository.findPromotionProductByProductName(name);

        //then
        assertThat(product).isEqualTo(promotionProductByProductName);
    }

    @DisplayName("상품 이름으로 프로모션 상품을 찾는데 실패한다.")
    @Test
    void fail_findPromotionProductByProductNameTest() {
        //given
        final Product product = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;

        //when
        productRepository.save(product.getId(), product);

        //then
        assertThatIllegalArgumentException().isThrownBy(
                        () -> productRepository.findPromotionProductByProductName("없는상품"))
                .withMessageContaining(ErrorMessage.INVALID_FAIL_DATA_READ_FROM_REPOSITORY.getMessage());
    }

    @DisplayName("상품 이름으로 일반 상품을 찾는데 성공한다.")
    @Test
    void pass_findProductByProductNameTest() {
        //given
        final Product product = ProductFixture.NON_PROMOTIN_PRODUCT;
        final String name = product.getName();

        //when
        productRepository.save(product.getId(), product);
        final Product productByProductName = productRepository.findProductByProductName(name);

        //then
        assertThat(product).isEqualTo(productByProductName);
    }

    @DisplayName("상품 이름으로 일반 상품을 찾는데 실패한다.")
    @Test
    void fail_findProductByProductNameTest() {
        //given
        final Product product = ProductFixture.NON_PROMOTIN_PRODUCT;

        //when
        productRepository.save(product.getId(), product);

        //given
        assertThatIllegalArgumentException().isThrownBy(() -> productRepository.findProductByProductName("없는상품"))
                .withMessageContaining(ErrorMessage.INVALID_FAIL_DATA_READ_FROM_REPOSITORY.getMessage());
    }

    @DisplayName("일반 상품이 존재한다")
    @Test
    void true_existNormalProductByProductNameTest() {
        //given
        final Product product = ProductFixture.NON_PROMOTIN_PRODUCT;

        //when
        productRepository.save(product.getId(), product);

        //then
        assertThat(productRepository.existNormalProductByProductName(product.getName())).isTrue();

    }

    @DisplayName("일반 상품이 존재하지 않는다.")
    @Test
    void false_existNormalProductByProductNameTest() {
        //given
        final Product product = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;

        //when
        productRepository.save(product.getId(), product);

        //then
        assertThat(productRepository.existNormalProductByProductName(product.getName())).isFalse();

    }

    @DisplayName("프로모션 상품이 존재한다.")
    @Test
    void true_existPromotionProductByProductNameTest() {
        //given
        final Product product = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;

        //when
        productRepository.save(product.getId(), product);

        //then
        assertThat(productRepository.existPromotionProductByProductName(product.getName())).isTrue();
    }

    @DisplayName("프로모션 상품이 존재하지 않는다.")
    @Test
    void false_existPromotionProductByProductNameTest() {
        //given
        final Product product = ProductFixture.NON_PROMOTIN_PRODUCT;

        //when
        productRepository.save(product.getId(), product);

        //then
        assertThat(productRepository.existPromotionProductByProductName(product.getName())).isFalse();
    }

    @DisplayName("상품이 존재한다.")
    @Test
    void existByProductNameTest() {
        //given
        final Product product = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;

        //when
        productRepository.save(product.getId(), product);

        //then
        assertThat(productRepository.existByProductName(product.getName())).isTrue();
    }

    @DisplayName("같은 이름의 일반 상품 프로모션 상품 수량을 카운팅 한다.")
    @Test
    void countByProductNameTest() {
        //given
        final Product product1 = ProductFixture.BUY_2_GET_1_FREE_IN_TIME_PRODUCT;
        final Product product2 = ProductFixture.NON_PROMOTIN_PRODUCT;
        final int totalQuantity = product1.getQuantity() + product2.getQuantity();

        //when
        productRepository.save(product1.getId(),product1);
        productRepository.save(product2.getId(),product2);

        //then
        assertThat(productRepository.countByProductName(product1.getName())).isEqualTo(totalQuantity);
    }
}
