package lopez.irving.favorites.mvvm.data.remote;

import java.util.List;

import io.reactivex.Observable;
import lopez.irving.favorites.mvvm.data.remote.model.CollectionResponse;
import retrofit2.http.GET;

/**
 * @author irving.lopez
 * @since 19/11/2017.
 */

public interface ApiHelper {

    @GET("3c5f25d5ee955ee9a6e493ac57884b9c/raw/5fab9af8e1f4db60419ba3a8da9f138cbb3a8461/Wish%2520lists")
    Observable<List<CollectionResponse>> getCollections();
}
