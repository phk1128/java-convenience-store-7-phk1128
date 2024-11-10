package store.error.exception;

import store.error.AppException;
import store.error.ErrorMessage;

public class InvalidWrongInputException extends AppException {
    public InvalidWrongInputException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
