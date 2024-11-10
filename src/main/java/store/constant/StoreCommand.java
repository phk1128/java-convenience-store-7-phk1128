package store.constant;

import java.util.Arrays;
import java.util.Objects;
import store.error.ErrorMessage;

public enum StoreCommand {
    YES("Y"),
    NO("N");

    private final String description;

    StoreCommand(final String description) {
        this.description = description;
    }

    public static StoreCommand findByDescription(final String description) {
        return Arrays.stream(StoreCommand.values())
                .filter(command -> Objects.equals(command.description, description))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_WRONG_INPUT.getMessage()));
    }
}
