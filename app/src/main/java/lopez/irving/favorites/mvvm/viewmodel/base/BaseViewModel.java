package lopez.irving.favorites.mvvm.viewmodel.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import lopez.irving.favorites.app.scheduler.SchedulerProvider;
import lopez.irving.favorites.mvvm.data.DataManager;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public abstract class BaseViewModel extends ViewModel {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    private CompositeDisposable compositeDisposable;

    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = new CompositeDisposable();
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }
}
