package store.payment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.StoreCommand;
import store.product.domain.PurchaseProduct;
import store.product.fixture.PurchaseProductFixture;

class PaymentTest {

    @DisplayName("사용자가 멤버십 할인을 희망하여 할인이 적용된다.")
    @Test
    void yes_applyMemberShipDiscountTest() {
        //given
        final StoreCommand storeCommand = StoreCommand.YES;
        final List<PurchaseProduct> purchaseProducts = PurchaseProductFixture.PURCHASE_PRODUCTS;
        final Payment payment = new Payment(purchaseProducts);

        //when
        payment.applyMemberShipDiscount(storeCommand);
        final int memberShipDiscount = payment.getMemberShipDiscount();

        //then
        assertThat(memberShipDiscount).isNotZero();
    }

    @DisplayName("사용자가 멤버십 할인을 거절하여 할인이 적용되지 않는다.")
    @Test
    void no_applyMemberShipDiscountTest() {
        //given
        final StoreCommand storeCommand = StoreCommand.NO;
        final List<PurchaseProduct> purchaseProducts = PurchaseProductFixture.PURCHASE_PRODUCTS;
        final Payment payment = new Payment(purchaseProducts);

        //when
        payment.applyMemberShipDiscount(storeCommand);
        final int memberShipDiscount = payment.getMemberShipDiscount();

        //then
        assertThat(memberShipDiscount).isZero();
    }

    @DisplayName("상품 지불할 금액을 계산 한다.")
    @Test
    void calculateTotalPriceTest() {
        //given
        final int totalProductPrice = 21000;
        final int promotionDiscount = 3000;
        final List<PurchaseProduct> purchaseProducts = PurchaseProductFixture.PURCHASE_PRODUCTS;
        final Payment payment = new Payment(purchaseProducts);

        //when
        final int totalPrice = payment.calculateTotalPrice();

        //given
        assertThat(totalPrice).isEqualTo(totalProductPrice - promotionDiscount);
    }

    @DisplayName("구매 상품의 총수량을 계산한다.")
    @Test
    void calculateTotalProductQuantityTest() {
        //given
        final int totalProductQuantity = 21;
        final List<PurchaseProduct> purchaseProducts = PurchaseProductFixture.PURCHASE_PRODUCTS;
        final Payment payment = new Payment(purchaseProducts);

        //when
        final int quantity = payment.calculateTotalProductQuantity();

        //given
        assertThat(quantity).isEqualTo(totalProductQuantity);
    }

    @DisplayName("구매 상품 총 금액을 계산 한다.")
    @Test
    void calculatePurchaseProductTotalPriceTest() {
        //given
        final int totalProductPrice = 21000;
        final List<PurchaseProduct> purchaseProducts = PurchaseProductFixture.PURCHASE_PRODUCTS;
        final Payment payment = new Payment(purchaseProducts);

        //when
        final int price = payment.calculatePurchaseProductTotalPrice();

        //given
        assertThat(price).isEqualTo(totalProductPrice);
    }

    @DisplayName("프로모션 할인 금액을 계산한다.")
    @Test
    void calculatePromotionTotalDiscountTest() {
        //given
        final int promotionDiscount = 3000;
        final List<PurchaseProduct> purchaseProducts = PurchaseProductFixture.PURCHASE_PRODUCTS;
        final Payment payment = new Payment(purchaseProducts);

        //when
        final int discount = payment.calculatePromotionTotalDiscount();

        //given
        assertThat(discount).isEqualTo(promotionDiscount);
    }

}
