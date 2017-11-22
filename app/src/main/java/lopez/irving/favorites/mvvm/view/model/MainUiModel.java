package lopez.irving.favorites.mvvm.view.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class MainUiModel {

    private List<CollectionUiModel> collections;
    private List<ProductUiModel> allProducts;
    private int totalProducts;

    public MainUiModel(@NonNull List<CollectionUiModel> collections,
                       @NonNull List<ProductUiModel> allProducts) {
        this.collections = collections;
        this.allProducts = allProducts;
        this.totalProducts = allProducts.size();
    }

    public List<CollectionUiModel> getCollections() {
        return collections;
    }

    public List<ProductUiModel> getAllProducts() {
        return allProducts;
    }

    public int getTotalProducts() {
        return totalProducts;
    }
}
