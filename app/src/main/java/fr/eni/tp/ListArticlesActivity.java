package fr.eni.tp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.eni.tp.adapter.ArticleAdapter;
import fr.eni.tp.databinding.ActivityRecyclerViewBinding;
import fr.eni.tp.entities.Article;

public class ListArticlesActivity extends AppCompatActivity {
    List<Article> articles = new ArrayList<>();
    AppDatabase DB;
    ActivityRecyclerViewBinding layout;
    RecyclerView recyclerView;
    ArticleAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View.OnClickListener onClickV = new View.OnClickListener() {
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
        layout = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        recyclerView = layout.getRoot();
        setContentView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DB = AppDatabase.getAppDatabase(this);
        SharedPreferences configuration = getSharedPreferences("CONFIG", MODE_PRIVATE);
        boolean sorted = configuration.getBoolean(ConfigurationActivity.SORT_PRICE, false);
        articles = sorted ? DB.articleDAO().getSorted() : DB.articleDAO().get();
        loadRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_list,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_configuration:
                startActivity(new Intent(this, ConfigurationActivity.class));
                break;
            case R.id.item_add:
                startActivity(new Intent(this, ArticleFormActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppDatabase.destroyDB();
    }

    protected void loadRecyclerView() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArticleAdapter(articles, onClickV);
        recyclerView.setAdapter(adapter);
    }
}