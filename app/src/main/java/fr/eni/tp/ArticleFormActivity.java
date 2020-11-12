package fr.eni.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import fr.eni.tp.databinding.ActivityArticleFormBinding;
import fr.eni.tp.entities.Article;

public class ArticleFormActivity extends AppCompatActivity {
    protected Article article;
    AppDatabase DB;
    ActivityArticleFormBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = ActivityArticleFormBinding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        DB = AppDatabase.getAppDatabase(this);
        if ((article = getIntent().getParcelableExtra("article")) != null) { setFormData(); }
        else {
            SharedPreferences configuration = getSharedPreferences("CONFIG", MODE_PRIVATE);
            String defaultPrice = configuration.getString(ConfigurationActivity.DEFAULT_PRICE, "");
            layout.formPriceET.setText(defaultPrice);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_form,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_save) {
            saveArticle();
            Intent list = new Intent(this, ListArticlesActivity.class);
            list.putExtra("article", article);
            startActivity(list);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppDatabase.destroyDB();
    }

    protected void saveArticle() {
        if (article == null) {
            article = new Article();
            setArticle();
            article.setId(DB.articleDAO().insert(article));
        } else {
            setArticle();
            DB.articleDAO().update(article);
        }

    }

    protected void setArticle() {
        article.setName(layout.formNameET.getText().toString());
        article.setDescription(layout.formDescriptionET.getText().toString());
        article.setUrl(layout.formURLET.getText().toString());
        article.setPrice(Double.parseDouble(layout.formPriceET.getText().toString()));
        article.setRating(layout.formRatingRB.getRating());
    }

    protected void setFormData() {
        layout.formNameET.setText(article.getName());
        layout.formDescriptionET.setText(article.getDescription());
        Log.i("DYRITS", article.getUrl());
        layout.formURLET.setText(article.getUrl());
        layout.formPriceET.setText(String.valueOf(article.getPrice()));
        layout.formRatingRB.setRating(article.getRating());
    }
}