package store.error;

public enum ErrorMessage {

    INVALID_NOT_FOUND_PRODUCT("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INVALID_EXCEEDS_PRODUCT_QUANTITY("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_FORMAT_PRODUCT_AND_QUANTITY("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
    INVALID_WRONG_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),
    INVALID_FAIL_INITIAL_DATA_LOAD("데이터 초기화에 실패하였습니다. 다시 실행해 주세요"),
    INVALID_FAIL_DATA_READ_FROM_REPOSITORY("레파지토리에서 데이터 읽기를 실패 하였습니다. 다시 실행해 주세요");


    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
