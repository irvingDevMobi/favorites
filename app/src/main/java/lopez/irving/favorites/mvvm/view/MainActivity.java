package lopez.irving.favorites.mvvm.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

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

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.main_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, GRID_COLUMNS);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == COLLECTIONS_INDEX || position == PRODUCTS_HEADER_INDEX) ? GRID_COLUMNS : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        subscribeToUiModel();
    }

    private void subscribeToUiModel() {
        AppSchedulerProvider schedulerProvider = new AppSchedulerProvider();
        viewModel.getUiModel()
                .subscribeOn(schedulerProvider.background())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Consumer<MainUiModel>() {
                    @Override
                    public void accept(MainUiModel mainUiModel) throws Exception {
                        fillRecyclerView(mainUiModel);
                    }
                });
    }

    private void fillRecyclerView(@NonNull MainUiModel mainUiModel) {
        List<Object> list = new ArrayList<>();
        list.add(mainUiModel.getCollections());
        list.add(mainUiModel.getTotalProducts());
        list.addAll(mainUiModel.getAllProducts());
        ProductListAdapter adapter = new ProductListAdapter(list);
        recyclerView.setAdapter(adapter);
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
