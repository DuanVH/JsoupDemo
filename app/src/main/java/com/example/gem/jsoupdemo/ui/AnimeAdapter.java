package com.example.gem.jsoupdemo.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gem.jsoupdemo.R;
import com.example.gem.jsoupdemo.model.Anime;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeHolder> {

  private List<Anime> animeList;
  private Context context;

  public AnimeAdapter(List<Anime> animeList, Context context) {
    this.animeList = animeList;
    this.context = context;
  }

  @NonNull
  @Override
  public AnimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_item, parent, false);
    return new AnimeHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull AnimeHolder holder, int position) {
    Anime anime = animeList.get(position);
    Glide.with(context)
        .load(anime.getUrlBanner())
        .asBitmap()
        .into(holder.mBanner);
    holder.mName.setText(anime.getName());
    holder.mEpisode.setText(anime.getEpisode());
    holder.mView.setText(anime.getView());
  }

  @Override
  public int getItemCount() {
    return animeList.size();
  }

  class AnimeHolder extends RecyclerView.ViewHolder {

    private ImageView mBanner;
    private TextView mName;
    private TextView mEpisode;
    private TextView mView;

    public AnimeHolder(View itemView) {
      super(itemView);
      mBanner = itemView.findViewById(R.id.iv_banner);
      mName = itemView.findViewById(R.id.tv_name);
      mEpisode = itemView.findViewById(R.id.tv_episode);
      mView = itemView.findViewById(R.id.tv_view);
    }
  }
}
