package lopez.irving.favorites.app.scheduler;

import io.reactivex.Scheduler;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler background();
}
