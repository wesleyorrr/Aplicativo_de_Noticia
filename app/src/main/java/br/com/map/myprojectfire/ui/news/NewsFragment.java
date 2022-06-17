package br.com.map.myprojectfire.ui.news;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import br.com.map.myprojectfire.MainActivity;
import br.com.map.myprojectfire.databinding.FragmentNewsBinding;
import br.com.map.myprojectfire.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {


    private FragmentNewsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {

            binding.rvNews.setAdapter(new NewsAdapter(news,updateNews ->{
                MainActivity activity =(MainActivity) getActivity();
                if (activity != null) {
                    activity.getDb().newsDao().save(updateNews);

                }


            }));

        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
        switch (state){
            case DOING:
                // iserir loding
                break;
            case DONE:
                //finalizar loading
                break;
            case ERROR:
                // caso erro finalizar loading


        }

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}