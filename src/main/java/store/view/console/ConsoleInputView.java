package store.view.console;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import store.constant.StoreCommand;
import store.util.StringParser;
import store.view.InputView;

public class ConsoleInputView implements InputView {

    private static final String DELIMITER_COMMA = ",";

    @Override
    public List<String> readProducts() {
        final String input = readInput();
        return StringParser.parseToTokens(input, DELIMITER_COMMA);
    }

    @Override
    public StoreCommand readStoreCommand() {
        final String input = readInput();
        return StoreCommand.findByDescription(input);
    }

    private String readInput() {
        return Console.readLine();
    }
}
