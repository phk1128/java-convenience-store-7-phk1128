package store.util;

import java.util.regex.Pattern;
import store.error.ErrorMessage;
import store.error.exception.InvalidFormatProductAndQuantityException;

public class StringValidator {

    private static final Pattern PATTERN = Pattern.compile("^\\[[가-힣a-zA-Z]+-\\d+]$");

    private StringValidator() {

    }

    public static void validateFormat(final String value, final ErrorMessage errorMessage) {
        if (isInvalidFormat(value)) {
            throw new InvalidFormatProductAndQuantityException(errorMessage);
        }
    }

    private static boolean isInvalidFormat(final String value) {
        return !PATTERN.matcher(value).matches();
    }
}
