package lopez.irving.favorites.mvvm.view.adapter;

import android.support.annotation.NonNull;
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

/**
 * @author irving.lopez
 * @since 21/11/2017.
 */

public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.CollectionViewHolder> {

    private List<CollectionUiModel> collections;

    public CollectionListAdapter() {
        collections = new ArrayList<>();
    }

    public void setData(@NonNull List<CollectionUiModel> collections) {
        this.collections = collections;
        notifyDataSetChanged();
    }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View collection = inflater.inflate(R.layout.item_collection, parent, false);
        return new CollectionViewHolder(collection);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        CollectionUiModel uiModel = collections.get(position);
        Picasso.with(holder.firstImage.getContext()).load(uiModel.getImagesUrls()[0]).into(holder.firstImage);
        Picasso.with(holder.secondImage.getContext()).load(uiModel.getImagesUrls()[1]).into(holder.secondImage);
        Picasso.with(holder.thirdImage.getContext()).load(uiModel.getImagesUrls()[2]).into(holder.thirdImage);
        holder.name.setText(uiModel.getCollectionName());
        holder.products.setText(uiModel.getSize());
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder {

        ImageView firstImage;
        ImageView secondImage;
        ImageView thirdImage;
        TextView name;
        TextView products;

        CollectionViewHolder(View itemView) {
            super(itemView);

            firstImage = itemView.findViewById(R.id.collection_item_image_1);
            secondImage = itemView.findViewById(R.id.collection_item_image_2);
            thirdImage = itemView.findViewById(R.id.collection_item_image_3);
            name = itemView.findViewById(R.id.collection_item_name);
            products = itemView.findViewById(R.id.collection_item_products);
        }
    }
}
