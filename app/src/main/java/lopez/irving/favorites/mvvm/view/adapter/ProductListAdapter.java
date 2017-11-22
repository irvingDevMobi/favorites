package lopez.irving.favorites.mvvm.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import lopez.irving.favorites.R;
import lopez.irving.favorites.mvvm.view.model.CollectionUiModel;
import lopez.irving.favorites.mvvm.view.model.ProductUiModel;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_COLLECTIONS = 1;
    private static final int VIEW_TYPE_DIVIDER = 2;
    private static final int VIEW_TYPE_PRODUCT = 3;
    public static final int COLLECTIONS_INDEX = 0;
    public static final int PRODUCTS_HEADER_INDEX = 1;

    private List items;

    public ProductListAdapter() {
        this.items = new ArrayList();
    }

    public void setData(@NonNull List items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_COLLECTIONS:
                View viewCollections = inflater.inflate(R.layout.item_collections_scroll, parent, false);
                return new CollectionsScrollViewHolder(viewCollections);
            case VIEW_TYPE_DIVIDER:
                View viewProductsHeader = inflater.inflate(R.layout.item_products_header, parent, false);
                return new ProductsHeaderViewHolder(viewProductsHeader);
            case VIEW_TYPE_PRODUCT:
                View viewProduct = inflater.inflate(R.layout.item_product, parent, false);
                return new ProductViewHolder(viewProduct);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_COLLECTIONS:
                onBindCollectionScroll((CollectionsScrollViewHolder) holder);
                break;
            case VIEW_TYPE_DIVIDER:
                onBindProductsHeader((ProductsHeaderViewHolder) holder);
                break;
            case VIEW_TYPE_PRODUCT:
                onBindProduct((ProductViewHolder) holder, position);
                break;
        }

    }

    private void onBindCollectionScroll(CollectionsScrollViewHolder scrollViewHolder) {
        scrollViewHolder.collectionListAdapter.setData((List<CollectionUiModel>) items.get(COLLECTIONS_INDEX));
    }

    private void onBindProductsHeader(ProductsHeaderViewHolder headerViewHolder) {
        Integer products = (Integer) items.get(PRODUCTS_HEADER_INDEX);
        headerViewHolder.headerTitle.setText(headerViewHolder.headerTitle.getContext()
                .getString(R.string.products_header_title, products));
    }

    private void onBindProduct(ProductViewHolder productViewHolder, int position) {
        ProductUiModel uiModel = (ProductUiModel) items.get(position);
        Picasso.with(productViewHolder.image.getContext())
                .load(uiModel.getIconUrl()).into(productViewHolder.image);
        productViewHolder.conditionBadge.setImageResource(uiModel.getConditionTypeImageRes());
        productViewHolder.levelBadge.setImageResource(uiModel.getLinioPlusImageRes());
        productViewHolder.importedBadge.setVisibility(uiModel.isImported() ? View.VISIBLE : View.GONE);
        productViewHolder.freeShippingBadge.setVisibility(uiModel.isFreeShipping() ? View.VISIBLE : View.GONE);
        productViewHolder.favorite.setVisibility(uiModel.isFavorite() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == COLLECTIONS_INDEX) {
            return VIEW_TYPE_COLLECTIONS;
        } else if (position == PRODUCTS_HEADER_INDEX) {
            return VIEW_TYPE_DIVIDER;
        } else{
            return VIEW_TYPE_PRODUCT;
        }
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        ImageView conditionBadge;
        ImageView levelBadge;
        ImageView importedBadge;
        ImageView freeShippingBadge;
        ImageView favorite;

        ProductViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_item_image);
            conditionBadge = itemView.findViewById(R.id.product_item_condition);
            levelBadge = itemView.findViewById(R.id.product_item_level);
            importedBadge = itemView.findViewById(R.id.product_item_imported);
            freeShippingBadge = itemView.findViewById(R.id.product_item_free_shipping);
            favorite = itemView.findViewById(R.id.product_item_favorite);
        }
    }

    class ProductsHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView headerTitle;

        ProductsHeaderViewHolder(View itemView) {
            super(itemView);
            headerTitle = itemView.findViewById(R.id.products_header_title);
        }
    }

    class CollectionsScrollViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        CollectionListAdapter collectionListAdapter;

        CollectionsScrollViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.collections_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            collectionListAdapter = new CollectionListAdapter();
            recyclerView.setAdapter(collectionListAdapter);
        }
    }
}
