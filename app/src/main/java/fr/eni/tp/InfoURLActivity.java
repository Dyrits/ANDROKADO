package fr.eni.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fr.eni.tp.bo.Article;
import fr.eni.tp.databinding.ActivityInfoUrlBinding;

public class InfoURLActivity extends AppCompatActivity {

    ActivityInfoUrlBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityInfoUrlBinding.inflate(getLayoutInflater());
        View view = layout.getRoot();
        setContentView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        Article article = intent.getParcelableExtra("Article");
        layout.urlTV.setText(article.getUrl());
    }
}