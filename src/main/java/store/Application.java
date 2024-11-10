package store;

import store.config.AppConfig;
import store.config.InitialDataLoader;

public class Application {
    public static void main(String[] args) {
        final AppConfig appConfig = new AppConfig();
        final InitialDataLoader initialDataLoader = appConfig.initialDataLoader();
        initialDataLoader.initialize();
        final Convenience convenience = appConfig.convenience();
        convenience.purchase();
    }
}
