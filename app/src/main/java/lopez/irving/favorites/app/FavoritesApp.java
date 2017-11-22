package lopez.irving.favorites.app;

import android.app.Application;

import lopez.irving.favorites.mvvm.data.AppDataManager;
import lopez.irving.favorites.mvvm.data.DataManager;
import lopez.irving.favorites.mvvm.data.local.database.AppDataBaseHelper;
import lopez.irving.favorites.mvvm.data.local.database.DataBaseHelper;
import lopez.irving.favorites.mvvm.data.remote.AppApiHelper;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class FavoritesApp extends Application {

    private static FavoritesApp instance;

    //TODO: inject
    private DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        AppApiHelper apiHelper = new AppApiHelper();
        AppDataBaseHelper dataBaseHelper = new AppDataBaseHelper();

        dataManager = new AppDataManager(apiHelper, dataBaseHelper);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
