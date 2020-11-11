package fr.eni.tp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.tp.bo.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
    List<Article> articles;
    TextView.OnClickListener onClickTV;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(TextView textView, TextView.OnClickListener onClickTV) {
            super(textView);
            this.textView = textView;
            textView.setOnClickListener(onClickTV);
        }
    }

    public ArticleAdapter(List<Article> articles, TextView.OnClickListener onClickTV) {
        this.articles = articles;
        this.onClickTV = onClickTV;
    }

    @Override
    public ArticleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Use built-in "simple_list_item_1" TextView layout:
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(textView, this.onClickTV);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(articles.get(position).getName());
        holder.textView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
