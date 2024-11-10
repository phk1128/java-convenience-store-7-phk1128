package store.promotion.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PromotionDateTime {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private final String startDate;
    private final String endDate;

    public PromotionDateTime(final String startDate, final String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isInTime() {
        final LocalDateTime now = DateTimes.now();
        final String nowDate = now.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return nowDate.compareTo(startDate) >= 0
                && nowDate.compareTo(endDate) <= 0;
    }

}
