package br.com.map.myprojectfire.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import java.util.List;

import br.com.map.myprojectfire.MainActivity;
import br.com.map.myprojectfire.databinding.FragmentFavoritesBinding;
import br.com.map.myprojectfire.domain.News;
import br.com.map.myprojectfire.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) { favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);



     loadFavoriteNews();

        return  binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity activity =(MainActivity) getActivity();
        List<News> favoriteNews = activity.getDb().newsDao().loandFavoriteNews();
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvNews.setAdapter(new NewsAdapter(favoriteNews, updateNews -> {
            if (activity != null) {
                activity.getDb().newsDao().save(updateNews);

                loadFavoriteNews();
            }

        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}