package aqa.framework.utils.properties;

import aqa.framework.logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

import static java.lang.String.format;

/**
 * Class-wrapper for working with aqa.properties-files. Uses relative path.
 */
public final class PropertiesResourceManager {

    private Properties properties = new Properties();

    private final Logger logger = Logger.getInstance();

    /**
     * Default Constructor
     */
    public PropertiesResourceManager() {
        properties = new Properties();
    }

    /**
     * Constructor
     *
     * @param resourceName Name of resource
     */
    public PropertiesResourceManager(final String resourceName) {
        properties = appendFromResource(properties, resourceName);
    }

    /**
     * Constructor
     *
     * @param resourceNames Names of resource
     */
    public PropertiesResourceManager(final String... resourceNames) {
        Arrays.stream(resourceNames).forEach(resourceName -> appendFromResource(properties, resourceName));
    }

    /**
     * Constructor for creation of one object from two aqa.properties-files
     *
     * @param defaultResourceName Default Resource Name
     * @param resourceName        Resource Name
     */
    public PropertiesResourceManager(final String defaultResourceName, final String resourceName) {
        this(defaultResourceName);
        properties = appendFromResource(new Properties(properties), resourceName);
    }

    /**
     * Merging of two aqa.properties-files (parameters from the second override parameters from the first)
     *
     * @param objProperties Properties
     * @param resourceName  Resource Name
     * @return Properties
     */
    private Properties appendFromResource(final Properties objProperties, final String resourceName) {
        try (InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (inStream != null) {
                objProperties.load(new InputStreamReader(inStream, StandardCharsets.UTF_8));
            } else {
                logger.error(format("Resource \"%1$s\" could not be found", resourceName));
            }
        } catch (IOException e) {
            logger.fatal("Error occurred during appending resource file", e);
        }
        return objProperties;
    }

    /**
     * Merging of two aqa.properties-files (parameters from the second override parameters from the first)
     *
     * @param resourceName Resource Name
     * @return Properties
     */
    public PropertiesResourceManager appendFromResource(final String resourceName) {
        appendFromResource(this.properties, resourceName);
        return this;
    }

    /**
     * Get value by key
     *
     * @param key Key
     * @return Value
     */
    public String getProperty(final String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    /**
     * Get value by key
     *
     * @param key          Key
     * @param defaultValue Default Value
     * @return Value
     */
    public String getProperty(final String key, final String defaultValue) {
        return System.getProperty(key, this.properties.getProperty(key, defaultValue));
    }

    /**
     * Sets the property
     *
     * @param key   Key
     * @param value Value
     */
    public void setProperty(final String key, final String value) {
        properties.setProperty(key, value);
    }
}
