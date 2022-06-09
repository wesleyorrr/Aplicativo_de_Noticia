package br.com.map.myprojectfire.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.map.myprojectfire.databinding.NewsItemBinding;
import br.com.map.myprojectfire.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> news;

    public NewsAdapter(List<News> news){
        this.news = news;
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
        News news  =  this.news.get(position);
        holder.binding.tvtitle.setText(news.getTitle());
        holder.binding.tvdescription.setText(news.getDescription());



        Picasso.get()
                .load(news.getImage())
                .fit()
                .into(holder.binding.tvThumbinail);

        holder.binding.btOpenlink.setOnClickListener(view -> {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(i);
        });
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

}
