package lopez.irving.favorites.mvvm.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import lopez.irving.favorites.R;
import lopez.irving.favorites.app.FavoritesApp;
import lopez.irving.favorites.app.scheduler.AppSchedulerProvider;
import lopez.irving.favorites.mvvm.view.adapter.ProductListAdapter;
import lopez.irving.favorites.mvvm.view.base.BaseActivity;
import lopez.irving.favorites.mvvm.view.model.MainUiModel;
import lopez.irving.favorites.mvvm.viewmodel.MainViewModel;

import static lopez.irving.favorites.mvvm.view.adapter.ProductListAdapter.COLLECTIONS_INDEX;
import static lopez.irving.favorites.mvvm.view.adapter.ProductListAdapter.PRODUCTS_HEADER_INDEX;

public class MainActivity extends BaseActivity<MainViewModel> {

    private static final int GRID_COLUMNS = 2;

    private ProductListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle(R.string.empty);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.main_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, GRID_COLUMNS);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == COLLECTIONS_INDEX || position == PRODUCTS_HEADER_INDEX) ? GRID_COLUMNS : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ProductListAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void bindViewModel() {
        AppSchedulerProvider schedulerProvider = new AppSchedulerProvider();
        compositeDisposable.add(viewModel.getUiModel()
                .subscribeOn(schedulerProvider.background())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Consumer<MainUiModel>() {
                    @Override
                    public void accept(MainUiModel mainUiModel) throws Exception {
                        fillRecyclerView(mainUiModel);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showErrorMessage();
                    }
                }));
        compositeDisposable.add(viewModel.getLoadingIndicatorVisibility()
                .subscribeOn(schedulerProvider.background())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean visible) throws Exception {
                        findViewById(R.id.main_progress).setVisibility(visible ? View.VISIBLE : View.GONE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showErrorMessage();
                    }
                }));
    }

    private void fillRecyclerView(@NonNull MainUiModel mainUiModel) {
        List<Object> list = new ArrayList<>();
        list.add(mainUiModel.getCollections());
        list.add(mainUiModel.getTotalProducts());
        list.addAll(mainUiModel.getAllProducts());
        adapter.setData(list);
    }

    private void showErrorMessage() {
        Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public MainViewModel generateViewModel() {
        // TODO: get from DI
        return new MainViewModel(((FavoritesApp) getApplication()).getDataManager(), new AppSchedulerProvider());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
}
