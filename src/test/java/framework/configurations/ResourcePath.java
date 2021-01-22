package framework.configurations;

public enum ResourcePath {
    PROJECT_DRIVER_OPTIONS("config/driver/driverOptions.json"),
    PROJECT_SELENIUM("config/driver/selenium.properties"),
    PROJECT_PARAMETERS_PROPERTIES("config/project.properties");

    private final String alias;

    ResourcePath(String alias) {
        this.alias = alias;
    }

    public String getPath() {
        return alias;
    }
}
