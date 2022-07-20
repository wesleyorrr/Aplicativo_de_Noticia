package br.com.map.myprojectfire.ui.favorites;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import br.com.map.myprojectfire.databinding.FragmentFavoritesBinding;
import br.com.map.myprojectfire.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    { favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);


 loadFovoriteNews();

        return  binding.getRoot();
    }

    private void loadFovoriteNews() {
        favoritesViewModel.loadFavoriteNews().observe(getViewLifecycleOwner(), localNews -> {
            binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvNews.setAdapter(new NewsAdapter(localNews, updateNews -> {
                favoritesViewModel.saveNews(updateNews);
                loadFovoriteNews();
            }));
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}