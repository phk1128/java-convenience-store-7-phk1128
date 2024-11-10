package store.promotion.domain;

import java.util.Arrays;
import java.util.Objects;

public enum PromotionType {

    BUY_2_GET_1_FREE("탄산2+1"),
    MD_PICK("MD추천상품"),
    FLASH_DISCOUNT("반짝할인"),
    NONE("");

    private final String name;

    PromotionType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PromotionType findByName(final String name) {
        return Arrays.stream(PromotionType.values())
                .filter(type -> Objects.equals(type.name, name))
                .findAny()
                .orElse(NONE);
    }
}
