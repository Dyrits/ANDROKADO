package fr.eni.tp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.tp.entities.Article;
import fr.eni.tp.databinding.CardListItemBinding;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
    List<Article> articles;
    View.OnClickListener onClickTV;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RatingBar ratingBar;
        public CardView cardView;

        public MyViewHolder(CardListItemBinding layout, View.OnClickListener onClickTV) {
            super(layout.getRoot());
            this.cardView = layout.getRoot();
            this.textView = layout.CVNameTV;
            this.ratingBar = layout.CVRatingRB;
            cardView.setOnClickListener(onClickTV);
        }
    }

    public ArticleAdapter(List<Article> articles, TextView.OnClickListener onClickTV) {
        this.articles = articles;
        this.onClickTV = onClickTV;
    }

    @Override
    public ArticleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Use built-in "simple_list_item_1" TextView layout:
        CardListItemBinding layout = CardListItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(layout, this.onClickTV);
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
