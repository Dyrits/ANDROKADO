package fr.eni.tp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.tp.databinding.CardListArticleBinding;
import fr.eni.tp.entities.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
    List<Article> articles;
    View.OnClickListener onClickV;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RatingBar ratingBar;
        public CardView cardView;

        public MyViewHolder(CardListArticleBinding layout, View.OnClickListener onClickTV) {
            super(layout.getRoot());
            this.cardView = layout.getRoot();
            this.textView = layout.articleNameTV;
            this.ratingBar = layout.articleRatingRB;
            cardView.setOnClickListener(onClickTV);
        }
    }

    public ArticleAdapter(List<Article> articles, TextView.OnClickListener onClickV) {
        this.articles = articles;
        this.onClickV = onClickV;
    }

    @Override
    public ArticleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Use built-in "simple_list_item_1" TextView layout:
        CardListArticleBinding layout = CardListArticleBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(layout, this.onClickV);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(articles.get(position).getName());
        holder.ratingBar.setRating(articles.get(position).getRating());
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
