package lopez.irving.favorites.mvvm.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import lopez.irving.favorites.mvvm.viewmodel.base.BaseViewModel;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewModel == null) {
            viewModel = generateViewModel();
        }
    }


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract VM generateViewModel();
}
