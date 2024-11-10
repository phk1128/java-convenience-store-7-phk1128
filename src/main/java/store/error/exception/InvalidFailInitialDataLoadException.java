package store.error.exception;

import store.error.AppException;
import store.error.ErrorMessage;

public class InvalidFailInitialDataLoadException extends AppException {
    public InvalidFailInitialDataLoadException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
