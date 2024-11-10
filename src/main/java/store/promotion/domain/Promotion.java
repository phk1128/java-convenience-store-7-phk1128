package store.promotion.domain;

public class Promotion {

    private final PromotionType type;
    private final PromotionDateTime dateTime;
    private final int buy;
    private final int get;


    public Promotion(final PromotionType type, final PromotionDateTime dateTime, final int buy, final int get) {
        this.type = type;
        this.buy = buy;
        this.get = get;
        this.dateTime = dateTime;
    }

    public int getRemainingQuantity(final int quantity) {
        if (dateTime.isInTime()) {
            return quantity % (buy + get);
        }
        return 0;
    }

    public String getPromotionName() {
        return type.getName();
    }

    public int getPromotionQuantity(final int quantity) {
        if (dateTime.isInTime()) {
            return quantity / (buy + get);
        }
        return 0;
    }

    public boolean isInPromotionTime() {
        return dateTime.isInTime();
    }

    public int getPromotionBenefitQuantity() {
        return get;
    }

    public boolean canPromotion(final int quantity) {
        return quantity >= buy
                && quantity % (buy + get) != 0
                && dateTime.isInTime();
    }

    public int getBuy() {
        return buy;
    }

    public PromotionType getType() {
        return type;
    }
}
