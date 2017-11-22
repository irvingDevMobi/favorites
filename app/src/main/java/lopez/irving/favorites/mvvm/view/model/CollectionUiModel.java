package lopez.irving.favorites.mvvm.view.model;

import android.support.annotation.NonNull;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class CollectionUiModel {

    private String [] imagesUrls;
    private String collectionName;
    private String  size;

    public CollectionUiModel(@NonNull String [] imagesUrls, @NonNull String collectionName, String size) {
        this.imagesUrls = imagesUrls;
        this.collectionName = collectionName;
        this.size = size;
    }

     @NonNull
    public String[] getImagesUrls() {
        return imagesUrls;
    }

    @NonNull
    public String getCollectionName() {
        return collectionName;
    }

    public String  getSize() {
        return size;
    }
}
