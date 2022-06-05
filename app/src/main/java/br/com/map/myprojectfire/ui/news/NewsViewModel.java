package br.com.map.myprojectfire.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.map.myprojectfire.domain.News;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<News>> news;

    public NewsViewModel() {
      this.news = new MutableLiveData<>();

        //TODO mocks de noticia
      List<News> news = new ArrayList<>();
      news.add(new News("Santander oferece 50 mil bolsas para cursos de programação","Para participar do programa, não é necessário ter qualquer graduação ou formação em tecnologia"));
        news.add(new News("Santander abre safra de resultados dos bancos. Veja o que esperar","Taxas de juros mais altas impactam de forma mais evidente os números do setor, em especial a qualidade dos ativos"));
        news.add(new News("Santander começa a financiar imóveis na planta. Veja como funciona","Nova linha terá condições similares às oferecidas pelo banco no financiamento tradicional, com juros a partir de 9,49% ao ano"));
    this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}