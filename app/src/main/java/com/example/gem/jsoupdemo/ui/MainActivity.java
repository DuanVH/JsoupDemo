package com.example.gem.jsoupdemo.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gem.jsoupdemo.model.Article;
import com.example.gem.jsoupdemo.R;
import com.example.gem.jsoupdemo.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recycler;
  private ArticleAdapter articleAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recycler = findViewById(R.id.rv_category);
    configRecyclerView();
    new DownloadTask().execute(Constants.ROOT_URL.concat(Constants.MY_URL));
  }

  private void configRecyclerView() {
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
//    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//    ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
    recycler.hasFixedSize();
    recycler.setLayoutManager(layoutManager);
  }

  private class DownloadTask extends AsyncTask<String, Void, ArrayList<Article>> {

    @Override
    protected ArrayList<Article> doInBackground(String... strings) {
      Document document = null;
      ArrayList<Article> listArticle = new ArrayList<>();
      try {
        document = Jsoup.connect(strings[0]).get();
        if (document != null) {
          //Lấy  html có thẻ như sau: div#latest-news > div.row > div.col-md-6 hoặc chỉ cần dùng  div.col-md-6
          Elements sub = document.select("div#latest-news > div.row > div.col-md-6");
          for (Element element : sub) {
            Article article = new Article();
            Element titleSubject = element.getElementsByTag("h3").first();
            Element imgSubject = element.getElementsByTag("img").first();
            Element linkSubject = element.getElementsByTag("a").first();
            Element description = element.getElementsByTag("h4").first();
            //Parse to model
            if (titleSubject != null) {
              String title = titleSubject.text();
              article.setTitle(title);
            }
//            if (imgSubject != null) {
//              String src = imgSubject.attr("src");
//              article.setImageUrl(src);
//            }
            if (linkSubject != null) {
              String link = linkSubject.attr("href");
              String imageUrl = linkSubject.getElementsByTag("img").first().attr("src");
              article.setUrl(link);
              article.setImageUrl(imageUrl);
            }
            if (description != null) {
              String des = description.text();
              article.setDescription(des);
            }
            //Add to list
            listArticle.add(article);
          }
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
      return listArticle;
    }

    @Override
    protected void onPostExecute(ArrayList<Article> articles) {
      super.onPostExecute(articles);
      //Setup data recyclerView
      articleAdapter = new ArticleAdapter(MainActivity.this,articles);
      recycler.setAdapter(articleAdapter);
    }
  }
}
