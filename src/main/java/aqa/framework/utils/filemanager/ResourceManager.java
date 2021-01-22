package aqa.framework.utils.filemanager;

import java.net.URL;
import java.util.Objects;

/**
 * Class-wrapper for working with resources files. Uses relative path.
 */
public final class ResourceManager {

    private final String path;

    /**
     * Constructor
     *
     * @param resourceName Name of resource
     */
    public ResourceManager(final String resourceName) {
        URL url = getClass().getClassLoader().getResource(resourceName);
        path = Objects.requireNonNull(url).getPath();
    }

    public String getPath() {
        return path;
    }
}
