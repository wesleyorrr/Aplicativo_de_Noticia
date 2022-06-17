package br.com.map.myprojectfire.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.map.myprojectfire.R;
import br.com.map.myprojectfire.databinding.NewsItemBinding;
import br.com.map.myprojectfire.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> news;
    private NewsListener favoritelistener;


    public NewsAdapter(List<News> news,NewsListener favoritelistener){
        this.news = news;
        this.favoritelistener = favoritelistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         Context context = holder.itemView.getContext();
        News news  =  this.news.get(position);
        holder.binding.tvtitle.setText(news.title);
        holder.binding.tvdescription.setText(news.description);

//imagens e tratamento com picasso

        Picasso.get()
                .load(news.image)
                .fit()
                .into(holder.binding.tvThumbinail);

        // abrir links


        holder.binding.btOpenlink.setOnClickListener(view -> {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            context.startActivity(i);
        });

        // compartilhar

        holder.binding.ivShare.setOnClickListener(view ->{

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, news.title);
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            context.startActivity(Intent.createChooser(i,"Share via"));

        });
            // fucionalide de favorite

        holder.binding.ivFavorite.setOnClickListener(view ->{
            news.favorite = !news.favorite;
            this.favoritelistener.onfavorite(news);
            notifyItemChanged(position);
        });

      int favoriteColor = news.favorite ? R.color.vermelho : R.color.cinza;
        holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));

    }

    @Override
    public int getItemCount() {return this.news.size(); }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    public interface NewsListener{
       void onfavorite(News news);
    }

}
