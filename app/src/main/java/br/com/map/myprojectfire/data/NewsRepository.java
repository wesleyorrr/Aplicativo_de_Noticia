package br.com.map.myprojectfire.data;

import androidx.room.Room;

import br.com.map.myprojectfire.App;
import br.com.map.myprojectfire.data.local.AppDatabase;
import br.com.map.myprojectfire.data.remote.NewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsRepository {

    private static final String REMOTE_API_URL = "https://wesleyorrr.github.io/simulator-Api/";
    private static final String LOCAL_DB_NAME = "soccer-news";
    private NewsApi remoteApi;
    private AppDatabase localDb;

    public NewsApi getRemoteApi() {
        return remoteApi;
    }

    public AppDatabase getLocalDb() {
        return localDb;
    }
    //endregion

    //region Singleton: garante uma instância única dos atributos relacionados ao Retrofit e Room.
    private NewsRepository () {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), AppDatabase.class, LOCAL_DB_NAME).build();
    }

    public static NewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final NewsRepository INSTANCE = new NewsRepository();
    }
}
