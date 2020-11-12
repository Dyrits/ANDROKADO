package fr.eni.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import fr.eni.tp.entities.Article;
import fr.eni.tp.databinding.ActivityInfoUrlBinding;

public class InfoURLActivity extends AppCompatActivity {

    ActivityInfoUrlBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityInfoUrlBinding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        Article article = intent.getParcelableExtra("Article");
        layout.urlTV.setText(article.getUrl());
    }
}