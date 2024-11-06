package store.util;

import java.util.Arrays;
import java.util.List;

public class StringParser {

    private StringParser() {

    }

    public static List<String> parseToTokens(final String value, final String delimiter) {
        return Arrays.stream(value.split(delimiter, -1))
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
