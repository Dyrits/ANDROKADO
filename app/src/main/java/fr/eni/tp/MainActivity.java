package fr.eni.tp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.eni.tp.bo.Article;
import fr.eni.tp.databinding.MainActivityBinding;


public class MainActivity extends AppCompatActivity {
    protected Article article;
    MainActivityBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = MainActivityBinding.inflate(getLayoutInflater());
        View view = layout.getRoot();
        setContentView(view);
        article = getIntent().getParcelableExtra("article");
        layout.nameTV.setText(article.getName());
        layout.descriptionTV.setText(article.getDescription());
        layout.priceTV.setText(article.getPriceToString());
        layout.ratingRB.setRating(article.getRating());
        layout.boughtTB.setChecked(article.isBought());
    }

    public void handleClickTB(View view) {
        article.setBought(!article.isBought());
    }

    public void handleClickIB(View view) {
        // Toast.makeText(this, article.getUrl(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, InfoURLActivity.class);
        intent.putExtra("Article", article);
        startActivity(intent);
    }
}