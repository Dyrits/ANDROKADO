package fr.eni.tp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.adapter.ArticleAdapter;
import fr.eni.tp.bo.Article;
import fr.eni.tp.databinding.ActivityListArticlesBinding;

public class ListArticlesActivity extends AppCompatActivity {
    ActivityListArticlesBinding layout;
    List<Article> articles = new ArrayList<>();
    RecyclerView recyclerView;
    ArticleAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View.OnClickListener onClickTV = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            int position = Integer.parseInt(view.getTag().toString());
            Intent intent = new Intent(ListArticlesActivity.this, MainActivity.class);
            intent.putExtra("article", articles.get(position));
            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityListArticlesBinding.inflate(getLayoutInflater());
        recyclerView = layout.getRoot();
        setContentView(recyclerView);
        setArticles();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticleAdapter(articles, onClickTV);
        recyclerView.setAdapter(adapter);
    }

    // Set the articles:
    private void setArticles() {
        String description = "Instrument de musique";
        articles.add(new Article("Guitare", description, "http://www.guitare.fr", 100, 5));
        articles.add(new Article("Basse", description, "http://www.basse.fr", 125, 4));
        articles.add(new Article("Flûte", description, "http://www.flute.fr", 17.95, 2));
    }
}