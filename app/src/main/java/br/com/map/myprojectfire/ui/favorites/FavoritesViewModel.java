package br.com.map.myprojectfire.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.map.myprojectfire.data.NewsRepository;
import br.com.map.myprojectfire.domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {

    }

   public LiveData<List<News>> loadFavoriteNews() {

        return NewsRepository.getInstance().getLocalDb().newsDao().loandFavoriteNews();
    }

    public void saveNews(News news) {
        AsyncTask.execute(() -> NewsRepository.getInstance().getLocalDb().newsDao().save(news));

    }


}