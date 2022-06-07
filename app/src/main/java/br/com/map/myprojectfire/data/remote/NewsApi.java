package br.com.map.myprojectfire.data.remote;

import java.util.List;

import br.com.map.myprojectfire.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    @GET("news.json")
    Call<List<News>> getNews();
}
