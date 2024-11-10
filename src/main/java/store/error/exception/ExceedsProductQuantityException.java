package store.error.exception;

import store.error.AppException;
import store.error.ErrorMessage;

public class ExceedsProductQuantityException extends AppException {
    public ExceedsProductQuantityException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
