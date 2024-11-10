package store.view;

import java.util.List;
import store.constant.StoreCommand;

public interface InputView {

    List<String> readProducts();

    StoreCommand readStoreCommand();
}
