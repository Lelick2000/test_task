package framework.configurations;

import aqa.framework.utils.properties.PropertiesResourceManager;

/**
 * Класс для работы с различными конфигурациями окружений.
 */
public class ProjectConfiguration {
    private static volatile ProjectConfiguration instance;
    private final PropertiesResourceManager projectConfig;

    /**
     * Конструктор по умолчанию, получающий значение текущего окружения из основного конфигурационного файла.
     */
    private ProjectConfiguration() {
        projectConfig = new PropertiesResourceManager(ResourcePath.PROJECT_SELENIUM.getPath(),
                ResourcePath.PROJECT_PARAMETERS_PROPERTIES.getPath());
    }

    /**
     * Метод, возвращающий единственный потоконезависимый экземпляр класса. Если экземпляр не создан - создает новый.
     *
     * @return экземпляр класса.
     */
    public static ProjectConfiguration getInstance() {
        ProjectConfiguration localInstance = instance;
        if (localInstance == null) {
            synchronized (ProjectConfiguration.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ProjectConfiguration();
                }
            }
        }
        return localInstance;
    }

    /**
     * Получение конфигурационного значения по ключу.
     *
     * @param key ключ для поиска конфигурационного значения.
     * @return Получает проперти, если не установлен системный параметр, но если системный параметр присутствует,
     * то получет системный параметр.
     */
    public String getPropertyIfSystemEnvAbsent(final String key) {
        return System.getenv(key) != null ? System.getenv(key) : projectConfig.getProperty(key);
    }

    /**
     * Получение конфигурационного значения по ключу.
     *
     * @param key ключ для поиска конфигурационного значения.
     * @return конфигурационное значение.
     */
    public String getProperty(final String key) {
        return projectConfig.getProperty(key);
    }

    /**
     * Получение значение ожидания - строковое значение приведенное к типу Long.
     *
     * @param timeoutKey ключ для поиска значения в конфигурации.
     * @return конфигурационное значение.
     */
    public Long getTimeout(final String timeoutKey) {
        return Long.valueOf(projectConfig.getProperty(timeoutKey));
    }

}
