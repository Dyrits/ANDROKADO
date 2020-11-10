package fr.eni.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import fr.eni.tp.bo.Article;

public class InfoURLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        Article article = intent.getParcelableExtra("Article");
        TextView urlTV = findViewById(R.id.url_tv);
        urlTV.setText(article.getUrl());
    }
}