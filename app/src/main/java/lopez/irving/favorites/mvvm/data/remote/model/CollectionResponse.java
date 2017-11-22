package lopez.irving.favorites.mvvm.data.remote.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author irving.lopez
 * @since 19/11/2017.
 */

public class CollectionResponse {

    private long id;
    private String name;
    private String description;
    @SerializedName("default")
    private boolean isDefault;
    private LinkedHashMap<String, ProductResponse> products;

    public long getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public boolean isDefault() {
        return isDefault;
    }

    @Nullable
    public LinkedHashMap<String, ProductResponse> getProducts() {
        return products;
    }
}
