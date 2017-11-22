package lopez.irving.favorites.mvvm.data;

import java.util.List;

import io.reactivex.Observable;
import lopez.irving.favorites.mvvm.data.local.database.DataBaseHelper;
import lopez.irving.favorites.mvvm.data.remote.ApiHelper;
import lopez.irving.favorites.mvvm.data.remote.model.CollectionResponse;

/**
 * @author irving.lopez
 * @since 19/11/2017.
 */

public class AppDataManager implements DataManager {

    private final ApiHelper apiHelper;
    private final DataBaseHelper dataBaseHelper;

    public AppDataManager(ApiHelper apiHelper, DataBaseHelper dataBaseHelper) {
        this.apiHelper = apiHelper;
        this.dataBaseHelper = dataBaseHelper;
    }

    @Override
    public Observable<List<CollectionResponse>> getCollections() {
        return apiHelper.getCollections();
    }
}
