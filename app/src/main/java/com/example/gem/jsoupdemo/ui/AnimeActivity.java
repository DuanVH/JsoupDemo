package com.example.gem.jsoupdemo.ui;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gem.jsoupdemo.R;
import com.example.gem.jsoupdemo.model.Anime;
import com.example.gem.jsoupdemo.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class AnimeActivity extends AppCompatActivity {

  private RecyclerView rvAnime;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.anime_main);

    rvAnime = findViewById(R.id.rv_anime);
    configListContent(2);
    setContents();
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      Log.e("DUAN_LOG", "onConfigurationChanged: LANDSCAPE");
    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
      Log.e("DUAN_LOG", "onConfigurationChanged: PORTRAIT");
    }
  }

  private void configListContent(int column) {
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, column);
    rvAnime.hasFixedSize();
    rvAnime.setLayoutManager(layoutManager);
  }

  private void setContents() {
    new LoadContent().execute(Constants.ROOT_VUI_GHE.concat(Constants.NEWEST_EPISODE));
  }

  private class LoadContent extends AsyncTask<String, Void, ArrayList<Anime>> {

    @Override
    protected ArrayList<Anime> doInBackground(String... strings) {
      Document document = null;
      ArrayList<Anime> animeArrayList = new ArrayList<>();
      try {
        document = (Document) Jsoup.connect(strings[0]).get();
        if (document != null) {
          Elements elements = document.select("section.tray > div.tray-item");
          for (Element item : elements) {
            Anime anime = new Anime();
            Element sub = item.getElementsByTag("a").first();
            Element subImage = item.getElementsByTag("img").first();
            if (subImage != null)
              anime.setUrlBanner(subImage.attr("data-src"));
            if (sub != null) {
              anime.setName(sub.getElementsByTag("div").get(1).text());
              anime.setEpisode(sub.getElementsByTag("div").get(2).getElementsByTag("span").get(0).text());
              anime.setView(sub.getElementsByTag("div").get(2).getElementsByTag("span").get(1).text());
            }
            /*add to list*/
            animeArrayList.add(anime);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return animeArrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Anime> anime) {
      super.onPostExecute(anime);
      rvAnime.setAdapter(new AnimeAdapter(anime, AnimeActivity.this));
    }
  }

}
