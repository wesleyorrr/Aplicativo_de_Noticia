package br.com.map.myprojectfire.ui.news;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import br.com.map.myprojectfire.data.remote.NewsApi;

import br.com.map.myprojectfire.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsViewModel extends ViewModel {

    public  enum State{
        DOING,DONE,ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();
    private final  NewsApi api;


    public NewsViewModel( ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wesleyorrr.github.io/simulator-Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    api = retrofit.create(NewsApi.class);



this.findsNews();

    }

 private void findsNews() {
        state.setValue(State.DOING);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()){
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                }else {
                    state.setValue(State.ERROR);                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

              t.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public LiveData<List<News>> getNews()  {
        return news;
    }
    public LiveData<State> getState()  { return this.state; }
}