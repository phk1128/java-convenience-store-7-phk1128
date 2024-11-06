package store.domain.product;

public enum ProductType {

    COLA("콜라"),
    CIDER("사이다"),
    ORANGE_JUICE("오렌지주스"),
    SPARKLING("탄산수"),
    WATER("물"),
    VITAMIN_WATER("비타워터"),
    POTATO_CHIPS("감자칩"),
    CHOCOLATE_BAR("초코바"),
    ENERGY_BAR("에너지바"),
    LUNCH_BOX("정식도시락"),
    CUP_NOODLE("컵라면");


    private final String name;

    ProductType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
