package lopez.irving.favorites.mvvm.viewmodel;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import lopez.irving.favorites.R;
import lopez.irving.favorites.app.scheduler.SchedulerProvider;
import lopez.irving.favorites.mvvm.data.DataManager;
import lopez.irving.favorites.mvvm.data.remote.model.CollectionResponse;
import lopez.irving.favorites.mvvm.data.remote.model.ProductResponse;
import lopez.irving.favorites.mvvm.view.model.CollectionUiModel;
import lopez.irving.favorites.mvvm.view.model.MainUiModel;
import lopez.irving.favorites.mvvm.view.model.ProductUiModel;
import lopez.irving.favorites.mvvm.viewmodel.base.BaseViewModel;
import lopez.irving.favorites.utils.CollectionUtils;

/**
 * @author irving.lopez
 * @since 20/11/2017.
 */

public class MainViewModel extends BaseViewModel {

    private static final int IMAGES_VISIBLE = 3;
    private static final String EMPTY = "";
    private static final String NEW = "new";

    private PublishSubject<MainUiModel> uiModelPublishSubject;
    private BehaviorSubject<Boolean> loadingIndicatorSubject;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        uiModelPublishSubject = PublishSubject.create();
        loadingIndicatorSubject = BehaviorSubject.create();
        fetchFavorites();
    }

    private void fetchFavorites() {
        getCompositeDisposable().add(getDataManager()
                .getCollections()
                .subscribeOn(getSchedulerProvider().background())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<CollectionResponse>>() {
                    @Override
                    public void accept(List<CollectionResponse> collectionResponses) throws Exception {
                        List<CollectionUiModel> collectionUiModels = transformToCollectionsUI(collectionResponses);
                        List<ProductUiModel> productUiModels = transformToProductsUi(collectionResponses);
                        if (CollectionUtils.isEmpty(collectionUiModels)
                                || CollectionUtils.isEmpty(productUiModels)) {
                            uiModelPublishSubject.onError(new Throwable());
                        } else {
                            uiModelPublishSubject.onNext(new MainUiModel(collectionUiModels, productUiModels));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        uiModelPublishSubject.onError(new Throwable());
                    }
                })
        );
    }

    @NonNull
    public Observable<MainUiModel> getUiModel() {
        return uiModelPublishSubject.hide()
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        loadingIndicatorSubject.onNext(true);
                    }
                })
                .doOnNext(new Consumer<MainUiModel>() {
                    @Override
                    public void accept(MainUiModel mainUiModel) throws Exception {
                        loadingIndicatorSubject.onNext(false);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        loadingIndicatorSubject.onNext(false);
                    }
                })
                ;
    }

    @NonNull
    public Observable<Boolean> getLoadingIndicatorVisibility() {
        return loadingIndicatorSubject.hide();
    }

    @NonNull
    private List<CollectionUiModel> transformToCollectionsUI(@NonNull List<CollectionResponse> collectionResponses) {
        List<CollectionUiModel> collectionsUi = new ArrayList<>();
        for (CollectionResponse collection : collectionResponses) {
            if (collection.getProducts() != null) {
                List<ProductResponse> products = new ArrayList<>(collection.getProducts().values());
                String[] images = new String[IMAGES_VISIBLE];
                for (int i = 0; i < IMAGES_VISIBLE && i < products.size(); i++) {
                    images[i] = products.get(i).getImage();
                }
                collectionsUi.add(new CollectionUiModel(images,
                        collection.getName() != null ? collection.getName() : EMPTY,
                        "" + products.size()));
            }
        }
        return collectionsUi;
    }

    private List<ProductUiModel> transformToProductsUi(@NonNull List<CollectionResponse> collectionResponses) {
        List<ProductUiModel> productsUi = new ArrayList<>();
        for (CollectionResponse collection : collectionResponses) {
            if (collection.getProducts() != null) {
                List<ProductResponse> products = new ArrayList<>(collection.getProducts().values());
                productsUi.addAll(transformToProductUiList(products));
            }
        }
        return productsUi;
    }

    @NonNull
    private List<ProductUiModel> transformToProductUiList(@NonNull List<ProductResponse> responseList) {
        List<ProductUiModel> productsUi = new ArrayList<>();
        for (ProductResponse productResponse : responseList) {
            productsUi.add(transformToProductUi(productResponse));
        }
        return productsUi;
    }

    @NonNull
    private ProductUiModel transformToProductUi(@NonNull ProductResponse response) {
        int conditionTypeRes = R.drawable.ic_nd_ic_refurbished_30;
        if (response.getConditionType() != null && response.getConditionType().equals(NEW)) {
            conditionTypeRes = R.drawable.ic_nd_ic_new_30;
        }
        return new ProductUiModel(response.getImage() != null ? response.getImage() : EMPTY,
                response.getLinioPlusLevel() == 1 ? R.drawable.ic_nd_ic_plus_30 : R.drawable.ic_nd_ic_plus_48_30,
                conditionTypeRes, response.isImported(), response.isFreeShipping(), true);
    }
}
