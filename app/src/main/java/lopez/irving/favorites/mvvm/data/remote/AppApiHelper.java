package lopez.irving.favorites.mvvm.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import lopez.irving.favorites.mvvm.data.remote.model.CollectionResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author irving.lopez
 * @since 19/11/2017.
 */

public class AppApiHelper implements ApiHelper {

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
        );
        return builder.build();
    }

    private static Retrofit getRetrofitInstance(String baseUrl) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public Observable<List<CollectionResponse>> getCollections() {
        return getRetrofitInstance(ApiEndPoint.BASE_URL).create(ApiHelper.class).getCollections();
    }
}
