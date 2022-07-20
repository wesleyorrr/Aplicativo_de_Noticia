package br.com.map.myprojectfire.ui.news;


import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.map.myprojectfire.data.NewsRepository;
import br.com.map.myprojectfire.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<>();


    public NewsViewModel() {
        this.findsNews();

    }

    public void findsNews() {
        state.setValue(State.DOING);
        NewsRepository.getInstance().getRemoteApi().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    state.setValue(State.DONE);
                } else {
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable error) {

                error.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    public void saveNews(News news) {
        AsyncTask.execute(() -> NewsRepository.getInstance().getLocaldb().newsDao().save(news));


    }

    public LiveData<List<News>> getNews() {
        return news;
    }

    public LiveData<State> getState() {
        return this.state;
    }
}