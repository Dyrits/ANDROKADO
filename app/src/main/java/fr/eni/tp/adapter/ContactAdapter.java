package fr.eni.tp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.tp.databinding.CardListContactBinding;
import fr.eni.tp.entities.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    List<Contact> contacts;
    View.OnClickListener onClickV;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView phoneTextView;
        public CardView cardView;

        public MyViewHolder(CardListContactBinding layout, View.OnClickListener onClickTV) {
            super(layout.getRoot());
            this.cardView = layout.getRoot();
            this.nameTextView = layout.contactNameTV;
            this.phoneTextView = layout.contactPhoneTV;
            cardView.setOnClickListener(onClickTV);
        }
    }

    public ContactAdapter(List<Contact> contacts, TextView.OnClickListener onClickV) {
        this.contacts = contacts;
        this.onClickV = onClickV;
    }

    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Use built-in "simple_list_item_1" TextView layout:
        CardListContactBinding layout = CardListContactBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(layout, this.onClickV);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nameTextView.setText(contacts.get(position).getName());
        holder.phoneTextView.setText(contacts.get(position).getPhone());
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
