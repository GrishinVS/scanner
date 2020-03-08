package com.grishinvs.scanner.configuration;

/**
 * Загрузчик конфигурации
 */
public interface ConfigurationLoader {

    /**
     * Загружает конфигурацию
     *
     * @return конфигурация
     */
    Configuration loadConfiguration();

}
