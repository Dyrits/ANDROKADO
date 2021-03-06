package fr.eni.tp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import fr.eni.tp.entities.Article;
import fr.eni.tp.databinding.MainActivityBinding;


public class MainActivity extends AppCompatActivity {
    protected Article article;
    MainActivityBinding layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(layout.getRoot());
        article = getIntent().getParcelableExtra("article");
        layout.nameTV.setText(article.getName());
        layout.descriptionTV.setText(article.getDescription());
        layout.priceTV.setText(article.getPriceToString());
        layout.ratingRB.setRating(article.getRating());
        layout.boughtTB.setChecked(article.isBought());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_details,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item_edit:
                Intent edit = new Intent(this, ArticleFormActivity.class);
                edit.putExtra("article", article);
                startActivity(edit);
                break;
            case R.id.item_send:
                Intent send = new Intent(this, ListContactsActivity.class);
                send.putExtra("article", article);
                startActivity(send);
        }
        return super.onOptionsItemSelected(item);
    }

    public void handleClickTB(View view) {
        article.setBought(!article.isBought());
    }

    public void handleClickIB(View view) {
        // Toast.makeText(this, article.getUrl(), Toast.LENGTH_SHORT).show();
        Intent info = new Intent(this, InfoURLActivity.class);
        info.putExtra("Article", article);
        startActivity(info);
    }
}