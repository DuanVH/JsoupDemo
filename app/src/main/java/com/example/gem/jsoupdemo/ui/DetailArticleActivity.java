package com.example.gem.jsoupdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gem.jsoupdemo.R;
import com.example.gem.jsoupdemo.utils.Constants;

public class DetailArticleActivity extends AppCompatActivity {

  private ImageView ivBanner;
  private TextView tvDescription;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    initViews();
    setContent();
  }

  private void setContent() {
    Intent intent = getIntent();
    String url = intent.getStringExtra(Constants.IMAGE_URL);
    String description = intent.getStringExtra(Constants.DESCRIPTION);
    if (null != url && !url.equals(""))
      Glide.with(this)
          .load(url)
          .asBitmap()
          .atMost()
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .animate(android.R.anim.fade_in)
          .approximate()
          .into(ivBanner);
    if (null != description && !description.equals(""))
      tvDescription.setText(description);
  }

  private void initViews() {
    ivBanner = (ImageView) findViewById(R.id.img_thumbnail_detail);
    tvDescription = (TextView) findViewById(R.id.tv_title_detail);
  }
}
