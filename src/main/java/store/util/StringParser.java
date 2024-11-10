package store.util;

import java.util.Arrays;
import java.util.List;
import store.error.ErrorMessage;
import store.error.exception.InvalidWrongInputException;

public class StringParser {

    private static final String BLANK = "";
    private static final int SPLIT_LIMIT = -1;

    private StringParser() {

    }

    public static String removePattern(final String value, final String regex) {
        return value.replaceAll(regex, BLANK);
    }

    public static List<String> parseToTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, SPLIT_LIMIT))
                .toList();
    }

    public static int parseToNumber(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("데이터 초기화 과정에서 문제가 발생했습니다. 애플리케이션 재실행 해주세요.");
        }
    }
}
