package fr.eni.tp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import fr.eni.tp.bo.Article;


public class MainActivity extends AppCompatActivity {

    protected Article article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameTv =  findViewById(R.id.name_tv);
        TextView descriptionTV = findViewById(R.id.description_tv);
        TextView priceTV = findViewById(R.id.price_tv);
        RatingBar ratingRB = findViewById(R.id.rating_rb);
        ToggleButton boughtTB = findViewById(R.id.bought_tb);
        article = generateArticle();
        nameTv.setText(article.getName());
        descriptionTV.setText(article.getDescription());
        priceTV.setText(article.getPriceToString());
        ratingRB.setRating((float) article.getRating());
        boughtTB.setChecked(article.isBought());
    }

    protected Article generateArticle() {
        return new Article(
                "Pain au chocolat",
                "Viennoiserie au beurre et au chocolat.",
                "http://www.painauchocolat.fr",
                1.5,
                3,
                false);
    }

    public void handleClickTB(View view) {
        article.setBought(!article.isBought());
    }

    public void handleClickIB(View view) {
        Toast.makeText(this, article.getUrl(), Toast.LENGTH_SHORT).show();
    }
}