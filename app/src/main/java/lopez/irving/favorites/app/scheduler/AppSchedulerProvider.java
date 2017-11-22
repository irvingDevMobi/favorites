package lopez.irving.favorites.app.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class AppSchedulerProvider implements SchedulerProvider {

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler background() {
        return Schedulers.io();
    }
}
