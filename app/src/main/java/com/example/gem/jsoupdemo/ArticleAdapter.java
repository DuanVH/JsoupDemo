package com.example.gem.jsoupdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {

  private Activity activity;
  private ArrayList<Article> listArticles;

  public ArticleAdapter(Activity activity, ArrayList<Article> listArticles) {
    this.activity = activity;
    this.listArticles = listArticles;
  }

  @NonNull
  @Override
  public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
    return new ArticleHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ArticleHolder holder, final int position) {
    final Article article = listArticles.get(position);
    holder.tvTitle.setText(article.getTitle());
    Glide.with(activity)
        .load(article.getThumbnail())
        .asBitmap()
        .atMost()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .animate(android.R.anim.fade_in)
        .approximate()
        .into(holder.ivThumbnail);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
//        activity.startActivity(new Intent(activity, DetailArticleActivity.class).putExtra("Article", article));
        Intent intent = new Intent(activity, DetailArticleActivity.class);
        intent.putExtra(Constants.IMAGE_URL, listArticles.get(position).getThumbnail());
        intent.putExtra(Constants.DESCRIPTION, listArticles.get(position).getDescription());
        intent.putExtra(Constants.URL_DETAIL, listArticles.get(position).getUrl());
        activity.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return listArticles.size();
  }


  class ArticleHolder extends RecyclerView.ViewHolder {
    private ImageView ivThumbnail;
    private TextView tvTitle;

    public ArticleHolder(View itemView) {
      super(itemView);
      ivThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
      tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    }
  }
}
