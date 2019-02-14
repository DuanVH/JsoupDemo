package com.example.gem.jsoupdemo.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gem.jsoupdemo.R;
import com.example.gem.jsoupdemo.model.Anime;

import java.util.ArrayList;

public class AnimeActivity extends AppCompatActivity {

  private RecyclerView rvAnime;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.anime_main);

    initViews();

    setContents();
  }

  private void initViews() {
    rvAnime = findViewById(R.id.rv_anime);
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
    rvAnime.hasFixedSize();
    rvAnime.setLayoutManager(layoutManager);
  }

  private void setContents() {

  }

  private class LoadContent extends AsyncTask<String, Void, ArrayList<Anime>> {

    @Override
    protected ArrayList<Anime> doInBackground(String... strings) {
      return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Anime> anime) {
      super.onPostExecute(anime);
    }
  }

}
