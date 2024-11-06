package store.domain.promotion;

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

    public int getBuy() {
        return buy;
    }

    public int getGet() {
        return get;
    }

    public PromotionType getType() {
        return type;
    }
}
