package lopez.irving.favorites.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class ViewModelProviderFactory<VM> implements ViewModelProvider.Factory {

    private VM viewModel;

    public ViewModelProviderFactory(VM viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(viewModel.getClass())) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
