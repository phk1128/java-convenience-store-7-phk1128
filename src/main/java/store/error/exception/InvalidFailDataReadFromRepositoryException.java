package store.error.exception;

import store.error.AppException;
import store.error.ErrorMessage;

public class InvalidFailDataReadFromRepositoryException extends AppException {
    public InvalidFailDataReadFromRepositoryException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
