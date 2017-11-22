package lopez.irving.favorites.utils;

import java.util.Collection;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
