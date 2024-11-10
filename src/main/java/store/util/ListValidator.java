package store.util;

import java.util.List;
import store.error.ErrorMessage;
import store.error.exception.InvalidWrongInputException;

public class ListValidator {

    private ListValidator() {

    }

    public static <T> void validateDuplicate(final List<T> values, final ErrorMessage errorMessage) {
        if (isDuplicate(values)) {
            throw new InvalidWrongInputException(errorMessage);
        }
    }

    public static <T> boolean isDuplicate(final List<T> values) {
        return values.stream()
                .distinct()
                .count() != values.size();
    }
}
