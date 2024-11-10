package store.error.exception;

import store.error.AppException;
import store.error.ErrorMessage;

public class NotFoundProductException extends AppException {
    public NotFoundProductException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
