package store.error.exception;

import store.error.AppException;
import store.error.ErrorMessage;

public class InvalidFormatProductAndQuantityException extends AppException {
    public InvalidFormatProductAndQuantityException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
